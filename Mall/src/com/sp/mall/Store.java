package com.sp.mall;

public class Store {
	private String store;
	private String address;
	private String phoneNumber;
	private String horaryI;
	private String horaryF;
	private String webSite;
	private String email;
	public Store(String n,String d,String t,String hi,String hf,String w, String e){
		this.store = n;
		this.address=d;
		this.phoneNumber=t;
		this.horaryI = hi;
		this.horaryF = hf;
		this.webSite=w;
		this.email=e;
	}
	
	public String getStore(){
		return this.store.toString();
	}
	
	public String getAdress(){
		return this.address.toString();
	}
	
	public String getPhoneNumber(){
		return  this.phoneNumber.toString();
	}
	public String getHoraryI(){
		return  this.horaryI.toString();
	}
	public String getHoraryF(){
		return  this.horaryF.toString();
	}
	public String getWebSite(){
		return  this.webSite.toString();
	}
	public String getEmail(){
		return  this.email.toString();
	}
}
