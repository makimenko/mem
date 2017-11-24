package com.makimenko.mem.server.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makimenko.mem.server.dao.DatabaseDao;
import com.makimenko.mem.server.model.Event;

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

	public ResponseEntity<List<Event>> eventsFindGet() {
		log.info("Returning events with summary/basic information");
		List<Event> shortList = new ArrayList<>();
		databaseDao.getEvents().forEach(i -> {
			Event shortEvent = new Event();
			shortEvent.setUuid(i.getUuid());
			shortEvent.setName(i.getName());
			shortEvent.setSubTitle(i.getSubTitle());
			shortEvent.setThumbnail(i.getThumbnail());
			shortEvent.setDescription(i.getDescription());
			shortList.add(shortEvent);
		});
		log.trace("Total number of events = {}", shortList.size());
		return new ResponseEntity<List<Event>>(shortList, HttpStatus.OK);
	}

}
