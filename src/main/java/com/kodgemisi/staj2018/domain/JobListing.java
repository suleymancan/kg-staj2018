package com.kodgemisi.staj2018.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class JobListing {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "jobTitle bos bırakılamaz.")
	private String jobTitle;

	@NotEmpty(message = "jobDescription boş bırakılamaz.")
	private String jobDescription;


	@Min(value = 1, message = "numberOfPeopleToHire  min. 1 olmalıdır.")
	private Integer numberOfPeopleToHire;


	@DateTimeFormat(pattern="dd/MM/yyyy")
	@FutureOrPresent(message = "tarih bugün ve sonrası olmalıdır.")
	private LocalDate lastAppDate;


	@OneToMany(mappedBy="jobListing", cascade=CascadeType.ALL,  orphanRemoval=true)
	Set<JobApplication> jobApp = new HashSet<>();


	public JobListing(@NotEmpty(message = "jobTitle bos bırakılamaz.") String jobTitle, @NotEmpty(message = "jobDescription boş bırakılamaz.") String jobDescription, @Min(value = 1, message = "numberOfPeopleToHire  min. 1 olmalıdır.") int numberOfPeopleToHire,  @FutureOrPresent LocalDate lastAppDate) {
		this.jobTitle = jobTitle;
		this.jobDescription = jobDescription;
		this.numberOfPeopleToHire = numberOfPeopleToHire;
		this.lastAppDate = lastAppDate;
	}

	public JobListing() {
	}
}
