package com.kodgemisi.staj2018.controller;

import com.kodgemisi.staj2018.domain.FileInfo;
import com.kodgemisi.staj2018.domain.JobApplication;
import com.kodgemisi.staj2018.domain.JobApplicationDTO;
import com.kodgemisi.staj2018.domain.JobListing;
import com.kodgemisi.staj2018.domain.validator.JobAppCustomValidator;
import com.kodgemisi.staj2018.service.FileStorage;
import com.kodgemisi.staj2018.service.JobApplicationService;
import com.kodgemisi.staj2018.service.JobListingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequestMapping("/app")
@AllArgsConstructor
public class JobApplicationController {

	private final FileStorage fileStorage;

	private final JobListingService jobListingService;

	private final JobApplicationService jobApplicationService;

	private final JobAppCustomValidator jobAppCustomValidator;


	@InitBinder
	protected void addCustomJobAppValidator(WebDataBinder webDataBinder){
		webDataBinder.addValidators(this.jobAppCustomValidator);
	}

	@GetMapping
	public String getIndex(Model model) {
		model.addAttribute("jobs", jobListingService.findJobListing());
		return "jobapp/jobsApp";
	}

	@GetMapping("/{id}")
	public String showJobListing(@PathVariable("id") Long id, Model model) {

		JobListing jobListing = jobListingService.findJob(id);

		model.addAttribute("jobListing", jobListing);
		return "jobapp/showJobApp";

	}

	@GetMapping("/{id}/basvur")
	public String getJobApp(@PathVariable("id") Long id, Model model) {
		model.addAttribute("jobApplicationDTO", new JobApplicationDTO());
		model.addAttribute("id", id);
		return "jobapp/newJobApp";
	}

	//modelAttrıbute neden kaldırdım?
	@PostMapping("/{id}/basvur")
	public String postJobApp(@Valid JobApplicationDTO jobApplicationDTO, BindingResult bindingResult, @PathVariable("id") Long id,
			@RequestParam("uploadfile") MultipartFile file) {
		fileStorage.store(file);
		//// TODO: 24.08.2018 : view'da set edilebilir.
		JobListing jobListing = jobListingService.findJob(id);
		jobApplicationDTO.setJobListing(jobListing);
		if (bindingResult.hasErrors()) {
			return "jobapp/newJobApp";
		}
		else {
			JobApplication jobApplication = jobApplicationService.createJopApplication(jobApplicationDTO);
			String url = "home/kodGemisi/staj2018/filestorage/" + file.getOriginalFilename();
			FileInfo fileInfo = new FileInfo(file.getOriginalFilename(), url, jobApplication);
			jobApplicationService.createFileInfo(fileInfo);
			return "redirect:/app";
		}

	}

}
