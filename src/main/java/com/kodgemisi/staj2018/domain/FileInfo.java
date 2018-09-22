package com.kodgemisi.staj2018.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FileInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String filename;

	private String url;

	@OneToOne
	private JobApplication jobApplication;

	public FileInfo(String filename, String url, JobApplication jobApplication) {
		this.filename = filename;
		this.url = url;
		this.jobApplication = jobApplication;
	}
}
