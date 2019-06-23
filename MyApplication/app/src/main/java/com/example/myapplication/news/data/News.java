package com.example.myapplication.news.data;

import java.util.List;
import com.google.gson.annotations.SerializedName;
public class News{

	@SerializedName("Objects")
	private List<ObjectsItem> objects;

	@SerializedName("Error")
	private boolean error;

	public void setObjects(List<ObjectsItem> objects){
		this.objects = objects;
	}

	public List<ObjectsItem> getObjects(){
		return objects;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"News{" + 
			"objects = '" + objects + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}