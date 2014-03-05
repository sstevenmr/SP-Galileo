package com.sp.mall.data;

import java.util.ArrayList;

public class Store {
	private String name;
	private String address;
	private String phoneNumber;
	private String horary;
	private String webSite;
	private String email;
	private ArrayList<Comment> commentList;
	private String favoriteNum;
	private String geography;

	public Store(String name,String address,String phone,String horary,String website, String email,ArrayList<Comment> comment,String favoriteNum,String ubicacion){
		this.setName(name);
		this.setAddress(address);
		this.setPhoneNumber(phone);
		this.setHorary(horary);
		this.setWebSite(website);
		this.setEmail(email);
		this.commentList = comment;
		this.setFavoriteNum(favoriteNum);
		this.setGeography(ubicacion);
	}

	public String getName() {
		return name;
	}

	public void setName(String nombre) {
		this.name = nombre;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHorary() {
		return horary;
	}

	public void setHorary(String horary) {
		this.horary = horary;
	}

	public String getFavoriteNum() {
		return favoriteNum;
	}

	public void setFavoriteNum(String favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

	public String getGeography() {
		return geography;
	}

	public void setGeography(String geography) {
		this.geography = geography;
	}

	public ArrayList<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
