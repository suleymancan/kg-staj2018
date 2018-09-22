package com.kodgemisi.staj2018;

import com.kodgemisi.staj2018.dao.JobApplicationRepository;
import com.kodgemisi.staj2018.dao.JobListingRepository;
import com.kodgemisi.staj2018.domain.JobApplication;
import com.kodgemisi.staj2018.domain.JobListing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class JobAppRepositoryTest {

	@Autowired
	private JobListingRepository jobListingRepository;
	
	
	@Autowired 
	JobApplicationRepository jobApplicationRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	//fail: BASARILI GIBI GOZUKUYOR AMA ROLLBACK
	//false positive (flush'suz)
	
	//@Test(expected=PersistenceException.class) bekledigimiz hata. OK
	@Test
	public void testCreateJobListing() {
		JobListing jobListing=new JobListing();
		jobListing.setJobTitle(null);
		jobListing.setJobDescription(null);
		jobListingRepository.save(jobListing);
		entityManager.flush();
	}
	
	@Test
	public void testCreateJobApp() {
		JobApplication jobApp=new JobApplication();
		jobApp.setName(null);
		jobApp.setEmail(null);
		jobApplicationRepository.save(jobApp);
		entityManager.flush();
	}
	
}
