package com.kodgemisi.staj2018.controller;

import com.kodgemisi.staj2018.domain.JobApplication;
import com.kodgemisi.staj2018.domain.JobListing;
import com.kodgemisi.staj2018.domain.JobListingDTO;
import com.kodgemisi.staj2018.service.FileStorage;
import com.kodgemisi.staj2018.service.JobListingService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


// // TODO: 23.08.2018 : metot isimleri güncellenebilir.
// TODO: 24.08.2018 : post islemlerinden sonra success/error message yapılabilir, redirectAttributes
// TODO: 23.08.2018 : controller detaylı incelenmeli. (ozellikle login olayı)
// TODO: 24.08.2018 : th:href'te '/string/'+expressionLanguage kullanımı yerine, string/__expressionLanguage__/ kullanılabilir.

@Controller
@RequestMapping("/hr")
@AllArgsConstructor
public class JobListingController {

	private final JobListingService jobListingService;

	private final FileStorage fileStorage;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}



	@GetMapping
	public String getIndex(Model model) {
		model.addAttribute("jobs", jobListingService.findJobListing());
		return "joblisting/jobs";
	}

	@GetMapping("/new")
	public String getJobListingForm(Model model) {
		model.addAttribute("jobListingDTO", new JobListingDTO());
		return "joblisting/newJob";
	}
	// // TODO: 23.08.2018 :  bir onceki path'e post yapılabilirdi. REST De-facto.
	@PostMapping("/new")
	public String postJobListingForm(@Valid JobListingDTO jobListingDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "joblisting/newJob";
		}
		else {
			jobListingService.createJobListing(jobListingDTO);
			return "redirect:/hr";
		}

	}

	@GetMapping("/{id}")
	public String showJobListing(@PathVariable("id") Long id, Model model) {

		JobListing jobListing = jobListingService.findJob(id);

		model.addAttribute("jobListing", jobListing);
		return "joblisting/showJob";

	}

	@GetMapping("/{id}/update")
	public String getUpdateJobListing(@PathVariable("id") Long id, Model model) {
		JobListing jobListing = jobListingService.findJob(id);
		model.addAttribute("jobListing", jobListing);
		return "joblisting/updateJob";
	}


	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public String postUpdateJobListing(@Valid @ModelAttribute JobListing jobListing, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "joblisting/updateJob";
		}
		else {
			jobListingService.updateJobListing(jobListing);
			return "redirect:/hr";
		}
	}

	@DeleteMapping("/{id}/delete")
	public String deleteJobListing(@PathVariable("id") Long id) {
		jobListingService.deleteJobListing(id);
		return "redirect:/hr";
	}

	@RequestMapping(value = "/{id}/basvuranlar", method = RequestMethod.GET)
	public String showJobListingApp(@PathVariable("id") Long id, Model model) {
		List<JobApplication> jobApp = jobListingService.showJobListingApp(id);
		model.addAttribute("jobApp", jobApp);

		return "joblisting/showJobListingApp";
	}

	/*
	 * Download Files
	 */
	// TODO: 24.08.2018 : incele
	@GetMapping("/files/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
		Resource file = fileStorage.loadFile(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

}
