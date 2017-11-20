package com.makimenko.mem.server.api;

import com.makimenko.mem.server.dao.DatabaseDao;
import com.makimenko.mem.server.model.Event;
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

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-17T15:15:20.914+02:00")

@CrossOrigin("*")
@Controller
public class EventsApiController implements EventsApi {

	private static final Logger log = LoggerFactory.getLogger(EventsApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private DatabaseDao databaseDao;

	@org.springframework.beans.factory.annotation.Autowired
	public EventsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<List<Event>> eventsGet() {
		log.info("eventsGet...");
		return new ResponseEntity<List<Event>>(databaseDao.getEvents(), HttpStatus.OK);
	}

}
