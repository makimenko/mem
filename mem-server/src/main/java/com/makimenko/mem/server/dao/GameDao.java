package com.makimenko.mem.server.dao;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameStep;
import com.makimenko.mem.server.model.GameType;
import com.makimenko.mem.server.model.Question;
import com.makimenko.mem.server.model.VisualContent;

@Component
public class GameDao {

	private static final Logger log = LoggerFactory.getLogger(GameDao.class);

	@Autowired
	private EventsDao eventsDao;

	public Game createGame(GameType gameType) {
		log.info("Creating new game: {}", gameType);
		Game game = new Game();
		eventsDao.getEvents().forEach(event -> {
			event.getGroups().forEach(group -> {
				GameStep step = new GameStep();
				List<VisualContent> contents = group.getVisualContents();
				if (contents != null && !contents.isEmpty()) {
					// TODO: Randomize
					step.setMedia(contents.get(0).getMedia());
				}
				List<Question> questions = group.getQuestions();
				if (questions != null && !questions.isEmpty()) {
					// TODO: Randomize
					step.setInputQuestion(questions.get(0));
					log.debug("Add gameplay step: {}", step);
					game.addGameStepsItem(step);
				}
			});
		});
		return game;
	}

}
