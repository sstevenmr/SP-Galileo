package com.example.data;

public class Helper {

	public final static String INSTAGRAM_API_KEY = "9b90341097b64eb0bf419c6d6ab0da5a";
	public final static String BASE_API_URL = "https://api.instagram.com/v1";
	public static String getRecentMediaUrl(String tag){
		return BASE_API_URL + "/tags/" + tag + "/media/recent?client_id=" + INSTAGRAM_API_KEY;
	}
}
