package com.makimenko.mem.server.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.makimenko.mem.server.dao.DatabaseDao;
import com.makimenko.mem.server.exception.MemNoDataFoundException;
import com.makimenko.mem.server.model.Event;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-17T15:15:20.914+02:00")

@CrossOrigin("*")
@Controller
public class EventApiController implements EventApi {

	private static final Logger log = LoggerFactory.getLogger(EventApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private DatabaseDao databaseDao;

	@org.springframework.beans.factory.annotation.Autowired
	public EventApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> eventUuidDelete(
			@ApiParam(value = "Event UUID to add to the database", required = true) @PathVariable("uuid") String uuid) {
		try {
			Event event = databaseDao.findEvent(uuid);
			log.debug("Removing event: {} - {}", event.getUuid(), event.getName());
			databaseDao.getEvents().remove(event);
			databaseDao.save();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (MemNoDataFoundException e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Event> eventUuidGet(
			@ApiParam(value = "Unique identifier of Event", required = true) @PathVariable("uuid") String uuid) {
		try {
			Event event = databaseDao.findEvent(uuid);
			return new ResponseEntity<Event>(event, HttpStatus.OK);
		} catch (MemNoDataFoundException e) {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Event> eventPost(
			@ApiParam(value = "Event to add to the database", required = true) @Valid @RequestBody Event event) {
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("application/json") && event != null) {
			try {
				if (Strings.isNullOrEmpty(event.getUuid())) {
					databaseDao.insertEvent(event);
				} else {
					databaseDao.updateEvent(event);
				}
				databaseDao.save();
				return new ResponseEntity<Event>(event, HttpStatus.OK);
			} catch (MemNoDataFoundException e) {
				return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
		}
	}

}
