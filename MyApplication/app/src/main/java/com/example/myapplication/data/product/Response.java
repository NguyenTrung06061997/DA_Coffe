package com.example.myapplication.data.product;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response implements Serializable {

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
			"Response{" + 
			"objects = '" + objects + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}