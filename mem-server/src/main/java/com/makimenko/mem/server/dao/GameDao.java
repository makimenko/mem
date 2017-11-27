package com.makimenko.mem.server.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makimenko.mem.server.exception.MemException;
import com.makimenko.mem.server.model.Answer;
import com.makimenko.mem.server.model.Event;
import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;
import com.makimenko.mem.server.model.GameStep;
import com.makimenko.mem.server.model.GameType;
import com.makimenko.mem.server.model.Question;
import com.makimenko.mem.server.model.VisualContent;

@Component
public class GameDao {

	private static final Logger log = LoggerFactory.getLogger(GameDao.class);

	// TODO: Game Option
	private final int MAX_QUESTIONS = 10;
	private final int MAX_OPTIONS = 4;

	@Autowired
	private EventsDao eventsDao;

	public Game createGame(GameOption gameOption) {
		log.info("Creating new game: {}", gameOption);

		if (gameOption.getGameType() == GameType.SIMPLE) {

			List<GameStep> allCombinations = new ArrayList<>();

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
											.filter(a -> a.isExpected() != null && a.isExpected().booleanValue())
											.count();
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
										while (shuffledAnswers.size() > MAX_OPTIONS - 1) {
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

			// TODO: consider game option

			Game game = new Game();
			int numQuestions = Math.min(MAX_QUESTIONS, allCombinations.size());
			for (int i = 0; i < numQuestions; i++) {
				game.addGameStepsItem(allCombinations.get(i));
			}

			log.debug("Game with {} questions created", game.getGameSteps().size());
			return game;
		} else

		{
			throw new MemException("Unknown Game Type");
		}

	}

}
