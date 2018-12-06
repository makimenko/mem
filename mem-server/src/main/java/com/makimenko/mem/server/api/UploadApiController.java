package com.makimenko.mem.server.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makimenko.mem.server.dao.ServerFileDao;
import com.makimenko.mem.server.exception.MemException;
import com.makimenko.mem.server.model.UploadLocation;

import io.swagger.annotations.ApiParam;


@CrossOrigin("*")
@Controller
public class UploadApiController implements UploadApi {

	private static final Logger log = LoggerFactory.getLogger(UploadApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private ServerFileDao serverFileDao;

	@Autowired
	public UploadApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<UploadLocation> uploadPost(
			@ApiParam(value = "file detail") @Valid @RequestPart("file") MultipartFile multipartFile) {
		log.info("Uploading file: {}", multipartFile.getOriginalFilename());
		if (!multipartFile.isEmpty()) {
			try {
				File serverFile = serverFileDao.getUploadTarget(multipartFile);
				log.debug("Server File Location=" + serverFile.getAbsolutePath());
				try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
					byte[] bytes = multipartFile.getBytes();
					stream.write(bytes);
					stream.close();
				}
				UploadLocation location = serverFileDao.getUploadLocation(serverFile);
				return new ResponseEntity<UploadLocation>(location, HttpStatus.OK);
			} catch (Exception e) {
				log.warn("Failed to updaload file", e);
				return new ResponseEntity<UploadLocation>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			log.warn("File name and/or content are empty");
			return new ResponseEntity<UploadLocation>(HttpStatus.NO_CONTENT);
		}
	}

}
