package com.makimenko.mem.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makimenko.mem.server.dao.ServerFileDao;
import com.makimenko.mem.server.exception.MemStorgeFileNotFoundException;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-24T17:30:05.527+02:00")

@Controller
public class DownloadApiController implements DownloadApi {

	private static final Logger log = LoggerFactory.getLogger(DownloadApiController.class);

	private final ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	private ServerFileDao serverFileDao;

	@org.springframework.beans.factory.annotation.Autowired
	public DownloadApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Resource> downloadUrlGet(
			@ApiParam(value = "File Location", required = true) @PathVariable("url") String url) {

		log.debug("downloadUrlGet {}", url);
		Resource file = serverFileDao.loadAsResource(url);
		ResponseEntity<Resource> response = ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
		return response;
	}

	@ExceptionHandler(MemStorgeFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(MemStorgeFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}
