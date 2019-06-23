package com.example.myapplication.data.product;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ObjectsItem implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("id_product")
	private String idProduct;

	@SerializedName("quantity")
	private String quantity;

	@SerializedName("price")
	private String price;

	@SerializedName("available")
	private String available;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private String discount;

	@SerializedName("id_type")
	private String idType;

	@SerializedName("sale_off")
	private String saleOff;

	@SerializedName("product_name")
	private String productName;

	@SerializedName("statuss")
	private String statuss;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setIdProduct(String idProduct){
		this.idProduct = idProduct;
	}

	public String getIdProduct(){
		return idProduct;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setAvailable(String available){
		this.available = available;
	}

	public String getAvailable(){
		return available;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setIdType(String idType){
		this.idType = idType;
	}

	public String getIdType(){
		return idType;
	}

	public void setSaleOff(String saleOff){
		this.saleOff = saleOff;
	}

	public String getSaleOff(){
		return saleOff;
	}

	public void setProductName(String productName){
		this.productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setStatuss(String statuss){
		this.statuss = statuss;
	}

	public String getStatuss(){
		return statuss;
	}

	@Override
 	public String toString(){
		return 
			"ObjectsItem{" + 
			"image = '" + image + '\'' + 
			",id_product = '" + idProduct + '\'' + 
			",quantity = '" + quantity + '\'' + 
			",price = '" + price + '\'' + 
			",available = '" + available + '\'' + 
			",description = '" + description + '\'' + 
			",discount = '" + discount + '\'' + 
			",id_type = '" + idType + '\'' + 
			",sale_off = '" + saleOff + '\'' + 
			",product_name = '" + productName + '\'' + 
			",statuss = '" + statuss + '\'' + 
			"}";
		}
}