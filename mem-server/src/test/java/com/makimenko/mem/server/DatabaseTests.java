package com.makimenko.mem.server;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.makimenko.mem.server.dao.DatabaseDao;
import com.makimenko.mem.server.model.Event;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableSwagger2
@ComponentScan(basePackages = { "com.makimenko.mem.server" })
public class DatabaseTests {

	@Autowired
	private DatabaseDao dao;

	@Test
	public void testSave() {
		System.out.println("Saving....");
		Event event = new Event();
		event.setName("My super event");
		dao.insertEvent(event);

		System.out.println("**** " + dao.findEvents().size());
		assertTrue(dao.findEvents().size() > 0);
	}
}
