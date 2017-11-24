package com.makimenko.mem.server.dao;

import java.io.File;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.makimenko.mem.server.exception.MemException;
import com.makimenko.mem.server.model.UploadLocation;

@Component
public class ServerFileDao {
	private static final Logger log = LoggerFactory.getLogger(ServerFileDao.class);

	@Value("${upload.dir}")
	private String uploadDir;

	public String getEncodedFileName(String fileName) {
		return fileName;
	}

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
		location.setUrl(file.getName());
		log.debug("Upload Location=" + location);
		return location;
	}

}
