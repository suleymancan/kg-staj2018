package com.kodgemisi.staj2018.service;

import com.kodgemisi.staj2018.domain.FileInfo;
import com.kodgemisi.staj2018.domain.JobApplication;
import com.kodgemisi.staj2018.domain.JobApplicationDTO;

public interface JobApplicationService {

	JobApplication createJopApplication(JobApplicationDTO jobApplicationDTO);

	void createFileInfo(FileInfo fileInfo);
}
