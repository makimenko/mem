package com.makimenko.mem.server.api;

import com.makimenko.mem.server.dao.GameDao;
import com.makimenko.mem.server.model.Game;
import com.makimenko.mem.server.model.GameOption;
import com.makimenko.mem.server.model.GameType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-25T11:32:24.000+02:00")

@CrossOrigin("*")
@Controller
public class GameApiController implements GameApi {

	private static final Logger log = LoggerFactory.getLogger(GameApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private GameDao gameDao;

	@org.springframework.beans.factory.annotation.Autowired
	public GameApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Game> gameNewPost(
			@ApiParam(value = "Game Options", required = true) @Valid @RequestBody GameOption gameOption) {
		log.info("Requsted new game");
		Game game = gameDao.createGame(gameOption.getGameType());
		return new ResponseEntity<Game>(game, HttpStatus.OK);
	}

}
