package com.makimenko.mem.server.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makimenko.mem.server.model.UploadLocation;

import io.swagger.annotations.*;

import org.assertj.core.util.Strings;
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

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-21T21:51:26.393+02:00")

@CrossOrigin("*")
@Controller
public class UploadApiController implements UploadApi {

	private static final Logger log = LoggerFactory.getLogger(UploadApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Value("${upload.dir:public/upload}")
	private String uploadDir;

	@Value("${upload.url:/upload/}")
	private String uploadUrl;

	@org.springframework.beans.factory.annotation.Autowired
	public UploadApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<UploadLocation> uploadPost(
			@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile file) {

		log.info("Uploading file: {}", file.getOriginalFilename());
		if (!file.isEmpty()) {

			try {
				// Creating the directory to store file
				File dir = new File(uploadDir+File.separator+file.getContentType());
				if (!dir.exists()) {
					dir.mkdirs();
				}

				// Create the file on server
				String name = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				byte[] bytes = file.getBytes();
				stream.write(bytes);
				stream.close();

				log.info("Server File Location=" + serverFile.getAbsolutePath());

				UploadLocation location = new UploadLocation();
				// TODO: fixme
				location.setUrl(uploadUrl +file.getContentType()+"/"+ name);
				return new ResponseEntity<UploadLocation>(location, HttpStatus.OK);
			} catch (Exception e) {
				log.warn("Failed to updaload file", e);
				return new ResponseEntity<UploadLocation>(HttpStatus.EXPECTATION_FAILED);
			}
		} else {
			log.warn("File name and/or content are empty");
			return new ResponseEntity<UploadLocation>(HttpStatus.NO_CONTENT);
		}

	}

}
