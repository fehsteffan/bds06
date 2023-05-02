package com.devsuperior.movieflix.entities.dto;

import java.io.Serializable;

import com.devsuperior.movieflix.entities.Movie;

public class MovieDTO2 implements Serializable {	
	private static final long serialVersionUID = 1L;	
	
	private Long id;
	private String title;
	private String subTitle;
	private Integer year;
	private String imgUrl;

	
	public MovieDTO2() {	
	}		
	
	public MovieDTO2(Movie entity) {		
		id = entity.getId();
		title = entity.getTitle();
		subTitle = entity.getSubTitle();
		year = entity.getYear();
		imgUrl = entity.getImgUrl();		
		
	}	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubTitle() {
		return subTitle;
	}


	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}	
	
}


