package com.makimenko.mem.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.dizitart.no2.Document;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.mapper.JacksonMapper;
import org.dizitart.no2.mapper.NitriteMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.makimenko.mem.server.model.BaseEntity;
import com.makimenko.mem.server.model.Event;

@Component
public class DatabaseDao {

	@Value("${database.filePath:events.db}")
	private String filePath;

	@Value("${database.user:username}")
	private String user;

	@Value("${database.password:Welcome1}")
	private String password;

	private final String EVENTS_COLLECTION = "events";

	private Nitrite db;
	private NitriteCollection eventsCollection;
	private NitriteMapper nitriteMapper;

	@PostConstruct
	public void init() {
		this.db = Nitrite.builder().compressed().filePath(filePath).openOrCreate(user, password);
		this.eventsCollection = db.getCollection(EVENTS_COLLECTION);
		this.nitriteMapper = new JacksonMapper();
	}

	public void insertEvent(Event event) {
		event.setUuid(UUID.randomUUID().toString());
		Document eventsDocument = nitriteMapper.asDocument(event);
		eventsCollection.insert(eventsDocument);
		//db.commit();
	}

	public List<Event> findEvents() {
		List<Event> result = new ArrayList<>();
		eventsCollection.find().forEach(document -> {
			Event event = nitriteMapper.asObject(document, Event.class);
			result.add(event);
		});
		return result;
	}

}
