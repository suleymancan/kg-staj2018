package com.kodgemisi.staj2018.service;

import com.kodgemisi.staj2018.domain.JobApplication;
import com.kodgemisi.staj2018.domain.JobListing;
import com.kodgemisi.staj2018.domain.JobListingDTO;

import java.util.List;

public interface JobListingService {
	List<JobListing> findJobListing();
	JobListing findJob(Long id);
	void createJobListing(JobListingDTO jobListingDTO);
	void updateJobListing(JobListing jobListing);
	void deleteJobListing(Long id);
	List<JobApplication> showJobListingApp(Long id);


}
