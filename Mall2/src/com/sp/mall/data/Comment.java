package com.sp.mall.data;

public class Comment {
	private String comment;
	public static int idAux;
	private int id=0;
	public Comment(String comment,int id) {
		// TODO Auto-generated constructor stub
		this.setComment(comment);
		this.id = id;
	}
	public Comment(){
	}
	public String getComment() {
		return comment;
	}
	public void setId(int d) {
		this.id = d;
	}
	public int gettId() {
		return id;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String toString(){
		return comment.toString();
	}

}
