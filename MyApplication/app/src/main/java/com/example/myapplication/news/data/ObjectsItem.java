package com.example.myapplication.news.data;

import com.google.gson.annotations.SerializedName;

public class ObjectsItem{

	@SerializedName("date")
	private String date;

	@SerializedName("images")
	private String images;

	@SerializedName("title")
	private String title;

	@SerializedName("id_blog")
	private String idBlog;

	@SerializedName("descriptions")
	private String descriptions;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setImages(String images){
		this.images = images;
	}

	public String getImages(){
		return images;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setIdBlog(String idBlog){
		this.idBlog = idBlog;
	}

	public String getIdBlog(){
		return idBlog;
	}

	public void setDescriptions(String descriptions){
		this.descriptions = descriptions;
	}

	public String getDescriptions(){
		return descriptions;
	}

	@Override
 	public String toString(){
		return 
			"ObjectsItem{" + 
			"date = '" + date + '\'' + 
			",images = '" + images + '\'' + 
			",title = '" + title + '\'' + 
			",id_blog = '" + idBlog + '\'' + 
			",descriptions = '" + descriptions + '\'' + 
			"}";
		}
}