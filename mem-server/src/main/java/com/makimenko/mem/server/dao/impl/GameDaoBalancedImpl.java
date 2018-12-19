package com.makimenko.mem.server.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.makimenko.mem.server.dao.EventsDao;
import com.makimenko.mem.server.dao.GameDao;
import com.makimenko.mem.server.exception.MemException;
import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;

@Component
public class GameDaoBalancedImpl implements GameDao {

	private static final Logger log = LoggerFactory.getLogger(GameDaoBalancedImpl.class);

	@Autowired
	private EventsDao eventsDao;

	@Override
	public Game createGame(GameOption gameOption) {
		throw new MemException("Not implemented");
	}

}
