package com.makimenko.mem.server.api;

import com.makimenko.mem.server.api.ProfileApi;
import com.makimenko.mem.server.model.Profile;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.threeten.bp.LocalDate;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-15T15:41:35.284+02:00")

@CrossOrigin(origins = "*")
@Controller
public class ProfileApiController implements ProfileApi {

	private static final Logger log = LoggerFactory.getLogger(ProfileApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@org.springframework.beans.factory.annotation.Autowired
	public ProfileApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<List<Profile>> getAllProfiles() {
		List<Profile> list = new ArrayList<>();
		Profile profile1 = new Profile();
		profile1.setName("Michael");
		profile1.setActive(true);
		profile1.setBirthDate(LocalDate.now());
		profile1.setUserCode("user1");
		list.add(profile1);

		Profile profile2 = new Profile();
		profile2.setName("Alex");
		profile2.setActive(false);
		profile2.setBirthDate(LocalDate.now());
		profile2.setUserCode("user2");
		list.add(profile2);

		ResponseEntity<List<Profile>> response = new ResponseEntity<>(list, HttpStatus.OK);
		return response;

	}

	public ResponseEntity<Void> profilePost(
			@ApiParam(value = "Create new profile", required = true) @Valid @RequestBody Profile profile) {
		String accept = request.getHeader("Accept");
		return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
	}

}
