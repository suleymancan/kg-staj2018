package com.kodgemisi.staj2018.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class JobApplicationDTO {

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

	private JobListing jobListing;

}
