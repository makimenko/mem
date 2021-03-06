package com.makimenko.mem.server.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.makimenko.mem.server.exception.MemException;
import com.makimenko.mem.server.exception.MemNoDataFoundException;
import com.makimenko.mem.server.model.Event;

@Component
public class EventsDao {
	private static final Logger log = LoggerFactory.getLogger(EventsDao.class);

	@Value("${database.filePath:data/events.json}")
	private String filePath;

	private Gson gson;
	private File file;
	private List<Event> events;

	Type EVENTS_TYPE = new TypeToken<List<Event>>() {
	}.getType();

	@PostConstruct
	public void init() throws IOException {
		log.debug("Initializing");
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		file = new File(filePath);
		load();
	}

	public void load() {
		log.debug("Loading data from file {}", file.getName());
		try {
			if (!file.exists()) {
				log.debug("File not existing, creating new empty file");
				file.getParentFile().mkdirs();
				file.createNewFile();
				events = new ArrayList<>();
				save();
			}
			Reader fileReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
			JsonReader reader = new JsonReader(fileReader);

			events = gson.fromJson(reader, EVENTS_TYPE);
		} catch (Exception e) {
			throw new MemException("Unable to load data from file", e);
		}
	}

	public void save() {
		log.debug("Save events to file {}", file.getName());

		try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
			gson.toJson(events, writer);
		} catch (Exception e) {
			throw new MemException("Unable to save file", e);
		}
	}

	public List<Event> getEvents() {
		log.debug("DAO: Get all events");
		return events;
	}

	public void insertEvent(Event event) {
		log.debug("Insert new event: " + event);
		event.setUuid(UUID.randomUUID().toString());
		events.add(event);
	}

	public void updateEvent(Event event) {
		log.debug("Update existing event: " + event);
		Event existingEvent = findEvent(event.getUuid());
		int index = events.indexOf(existingEvent);
		events.set(index, event);
	}

	public Event findEvent(String uuid) {
		Event event = events.stream().filter(i -> i.getUuid().equals(uuid)).findFirst().get();
		if (event == null) {
			String msg = "Event wuth UUID [" + uuid + "] was not found!";
			log.warn(msg);
			throw new MemNoDataFoundException(msg);
		}
		return event;
	}

}
