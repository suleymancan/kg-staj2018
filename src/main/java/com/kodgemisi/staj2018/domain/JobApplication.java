package com.kodgemisi.staj2018.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
public class JobApplication {

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty(message = "name bos bırakılamaz.")
	private String name;

	@Email(message = "email hatalı.")
	private String email;

	@Pattern(regexp = "[0-9\\s]{11}", message = "phone hatalı.")
	private String phone;

	@NotEmpty(message = "address alanı boş bırakılamaz.")
	private String address;

	@NotEmpty(message = "thoughtsOnJob alanı boş bırakılamaz.")
	private String thoughtsOnJob;

	@ManyToOne
	private JobListing jobListing;

	@OneToOne(mappedBy = "jobApplication", cascade = CascadeType.ALL, orphanRemoval = true)
	private FileInfo fileInfo;

	public JobApplication(String name, String email, String phone, String address, String thoughtsOnJob, JobListing jobListing) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.thoughtsOnJob = thoughtsOnJob;
		this.jobListing = jobListing;
	}

}
