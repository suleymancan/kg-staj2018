package com.kodgemisi.staj2018.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Getter
@Setter
public class JobListingDTO {

	@NotEmpty(message = "jobTitle bos bırakılamaz.")
	private String jobTitle;

	@NotEmpty(message = "jobDescription boş bırakılamaz.")
	private String jobDescription;

	@Min(value = 1, message = "numberOfPeopleToHire  min. 1 olmalıdır.")
	private Integer numberOfPeopleToHire;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@FutureOrPresent(message = "tarih bugün ve sonrası olmalıdır.")
	private LocalDate lastAppDate;

}
