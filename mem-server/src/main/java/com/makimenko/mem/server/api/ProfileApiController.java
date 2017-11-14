package com.makimenko.mem.server.api;

import com.makimenko.mem.server.model.Profile;

import io.swagger.annotations.*;

import org.joda.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;

@Controller
public class ProfileApiController implements ProfileApi {

	public ResponseEntity<List<Profile>> getAllProfiles() {
		List<Profile> list = new ArrayList<>();
		Profile profile1 = new Profile();
		profile1.setName("Michael");
		profile1.setActive(true);
		profile1.setBirthDate(new LocalDate());
		profile1.setUserCode("user1");
		list.add(profile1);

		Profile profile2 = new Profile();
		profile2.setName("Alex");
		profile2.setActive(false);
		profile2.setBirthDate(new LocalDate());
		profile2.setUserCode("user2");
		list.add(profile2);

		ResponseEntity<List<Profile>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;
	}

	public ResponseEntity<Void> profilePost(
			@ApiParam(value = "Create new profile", required = true) @Valid @RequestBody Profile profile) {
		// do some magic!
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}