package com.kodgemisi.staj2018.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

// TODO: 24.08.2018 : incele

public interface FileStorage {
	void store(MultipartFile file);
	Resource loadFile(String filename);
	void deleteAll();
	void init();

}