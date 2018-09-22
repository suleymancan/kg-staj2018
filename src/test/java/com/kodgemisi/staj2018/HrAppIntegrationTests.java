package com.kodgemisi.staj2018;

import com.kodgemisi.staj2018.domain.JobApplication;
import com.kodgemisi.staj2018.service.JobApplicationService;
import com.kodgemisi.staj2018.service.JobListingService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties= {"spring.profiles.active=dev"})
public class HrAppIntegrationTests {

	@Autowired
	private JobListingService jobListingService;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	

	//OK.
	@Test
	public void showJobListingApp() {
		List<JobApplication> jopApp= jobListingService.showJobListingApp((long) 1);
		MatcherAssert.assertThat(jopApp.size(), Matchers.equalTo(3));
	}
	
	
}
