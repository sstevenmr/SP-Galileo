package com.sp.mall.data;

import java.util.ArrayList;

public class Photo {
	private String URL;
	private String descripcion;
	private ArrayList<Comment>commentList;
	private String favoriteNum;
	public Photo(String url, String descripcion,ArrayList<Comment> comments,String favoriteNum) {
		this.URL = url;
		this.descripcion = descripcion;
		this.commentList = comments;
		this.favoriteNum = favoriteNum;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}
	public String getFavoriteNum() {
		return favoriteNum;
	}
	public void setFavoriteNum(String favoriteNum) {
		this.favoriteNum = favoriteNum;
	}

}
