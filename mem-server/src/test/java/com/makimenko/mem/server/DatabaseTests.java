package com.makimenko.mem.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.makimenko.mem.server.dao.EventsDao;
import com.makimenko.mem.server.model.Event;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = { "com.makimenko.mem.server" })
public class DatabaseTests {

	@Autowired
	private EventsDao dao;

	@Test
	public void testSave() {
		int before = dao.getEvents().size();

		Event event = new Event();
		event.setName("My super event");
		dao.insertEvent(event);
		dao.save();

		assertEquals(before + 1, dao.getEvents().size());
	}

}
