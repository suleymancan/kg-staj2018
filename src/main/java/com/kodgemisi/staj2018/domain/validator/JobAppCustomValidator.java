package com.kodgemisi.staj2018.domain.validator;

import com.kodgemisi.staj2018.domain.JobApplicationDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class JobAppCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return JobApplicationDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JobApplicationDTO jobApplicationDTO = (JobApplicationDTO) target;
		if (jobApplicationDTO.getName().equalsIgnoreCase("cacık")) {
			errors.rejectValue("name", null, "Cacık yazamazsınız.");
		}

	}
}
