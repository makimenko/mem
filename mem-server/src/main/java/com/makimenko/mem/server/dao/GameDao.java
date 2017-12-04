package com.makimenko.mem.server.dao;

import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;

public interface GameDao {

	Game createGame(GameOption gameOption);

}
