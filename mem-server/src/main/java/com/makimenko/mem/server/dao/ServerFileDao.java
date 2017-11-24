package com.makimenko.mem.server.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.makimenko.mem.server.exception.MemException;
import com.makimenko.mem.server.exception.MemNoDataFoundException;
import com.makimenko.mem.server.exception.MemStorgeFileNotFoundException;
import com.makimenko.mem.server.model.Event;
import com.makimenko.mem.server.model.UploadLocation;

@Component
public class ServerFileDao {
	private static final Logger log = LoggerFactory.getLogger(ServerFileDao.class);

	@Value("${upload.dir:data/upload}")
	private String uploadDir;

	public File getFile(String fileName) {
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			// Creating the directory to store files
			dir.mkdirs();
		}
		File file = new File(dir.getAbsolutePath() + File.separator + fileName);
		return file;
	}

	public File getUploadTarget(MultipartFile multipartFile) {
		String fileName = multipartFile.getOriginalFilename();
		File file = getFile(fileName);
		if (!file.exists()) {
			return file;
		} else if (file.length() == multipartFile.getSize()) {
			// already exists and size matched
			return file;
		} else if (file.length() != multipartFile.getSize()) {
			fileName = UUID.randomUUID().toString() + "_" + fileName;
			file = getFile(multipartFile.getOriginalFilename());
			return file;
		} else {
			throw new MemException("Can not determine upload destination");
		}
	}

	public UploadLocation getUploadLocation(File file) {
		UploadLocation location = new UploadLocation();
		try {
			String encoded = URLEncoder.encode(file.getName(), "UTF-8");
			location.setUrl(encoded);
		} catch (UnsupportedEncodingException e) {
			log.error("Failed to encode location url", e);
			throw new MemException(e);
		}
		log.debug("Upload Location=" + location);
		return location;
	}

	public Resource loadAsResource(String locationUrl) {
		// TODO: implement
		File file = getFile(locationUrl);
		if (!file.exists()) {
			log.warn("File not found: {}", file.getAbsolutePath());
			throw new MemStorgeFileNotFoundException("File not found: " + file.getAbsolutePath());
		}
		Resource resource = new FileSystemResource(file);
		return resource;
	}

}
