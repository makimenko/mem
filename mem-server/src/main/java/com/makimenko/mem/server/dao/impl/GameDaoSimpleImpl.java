package com.makimenko.mem.server.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makimenko.mem.server.dao.EventsDao;
import com.makimenko.mem.server.dao.GameDao;
import com.makimenko.mem.server.model.Answer;
import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;
import com.makimenko.mem.server.model.GameStep;
import com.makimenko.mem.server.model.Question;
import com.makimenko.mem.server.model.VisualContent;

@Component
public class GameDaoSimpleImpl implements GameDao {

    private static final Logger log = LoggerFactory.getLogger(GameDaoSimpleImpl.class);

    private List<GameStep> allCombinations;

    @Autowired
    private EventsDao eventsDao;

    @Override
    public Game createGame(GameOption gameOption) {
        log.info("Requested new game: {}", gameOption);

        if (gameOption.getMaxOptions() == null) {
            gameOption.setMaxOptions(4);
        }

        if (gameOption.getMaxQuestions() == null) {
            gameOption.setMaxQuestions(20);
        }

        log.info("Creating simple new game: {}", gameOption);
        // TODO: consider game option
        Game game = new Game();
        getGameSteps(gameOption).forEach(i -> game.addGameStepsItem(i));

        log.debug("Game with {} questions created", game.getGameSteps().size());
        return game;
    }

    private List<GameStep> getGameSteps(GameOption gameOption) {
        log.debug("Pick {} game steps", gameOption);
        if (this.allCombinations == null || this.allCombinations.size() < gameOption.getMaxQuestions()) {
            reloadAllCombinations(gameOption);
        }
        List<GameStep> result = new ArrayList<>();
        int size = this.allCombinations.size();
        for (int i = 0; i < Math.min(gameOption.getMaxQuestions(), size); i++) {
            result.add(this.allCombinations.get(0));
            this.allCombinations.remove(0);
        }
        return result;
    }

    private synchronized void reloadAllCombinations(GameOption gameOption) {
        log.debug("Reload combinations");
        this.allCombinations = new ArrayList<>();

        eventsDao.getEvents().stream().forEach(event -> {
            log.debug("Processing event: {}", event.getName());
            event.getGroups().stream().forEach(group -> {
                log.debug("Processing group: {}", group.getName());

                List<VisualContent> contents = group.getVisualContents();
                List<Question> questions = group.getQuestions();

                // IF question exists
                if (questions != null && !questions.isEmpty()) {
                    if (contents == null) {
                        contents = new ArrayList<>();
                    }
                    if (contents.size() == 0) {
                        contents.add(new VisualContent());
                    }

                    contents.forEach(content -> {
                        questions.forEach(question -> {
                            List<Answer> answers = question.getAnswers();

                            // If at least 2 question options exists
                            if (answers != null && answers.size() > 1) {

                                long numCorrectAnswers = answers.stream()
                                        .filter(a -> a.isExpected() != null && a.isExpected().booleanValue()).count();
                                // If correct answer exists
                                if (numCorrectAnswers == 1) {

                                    Answer correctAnswer = answers.stream()
                                            .filter(a -> a.isExpected() != null && a.isExpected().booleanValue())
                                            .findFirst().orElse(null);
                                    List<Answer> shuffledAnswers = answers.stream()
                                            .filter(i -> i.isExpected() == null || !i.isExpected().booleanValue())
                                            .collect(Collectors.toList());
                                    Collections.shuffle(shuffledAnswers);

                                    // remove options randomly
                                    while (shuffledAnswers.size() > gameOption.getMaxOptions() - 1) {
                                        shuffledAnswers.remove(shuffledAnswers.size() - 1);
                                    }
                                    shuffledAnswers.add(correctAnswer);
                                    Collections.shuffle(shuffledAnswers);

                                    Question shuffledQuestion = new Question();
                                    shuffledQuestion.setUuid(question.getUuid());
                                    shuffledQuestion.setName(question.getName());
                                    shuffledQuestion.setAnswers(shuffledAnswers);

                                    GameStep gameStep = new GameStep();
                                    gameStep.setInputQuestion(shuffledQuestion);
                                    if (content.getMedia() != null) {
                                        gameStep.setMedia(content.getMedia());
                                    }

                                    allCombinations.add(gameStep);
                                } else {
                                    log.warn("Question [{}] has {} expected answers", question.getName(),
                                            numCorrectAnswers);
                                }
                            } else {
                                log.warn("Question [{}] does not contain answer options");
                            }
                        });
                    });
                } else {
                    log.warn("Group [{}] dont have questions", group.getName());
                }

            });
        });
        log.debug("{} unique combinations found", allCombinations.size());

        Collections.shuffle(allCombinations);
        log.debug("Shuffling complted");
    }

}
