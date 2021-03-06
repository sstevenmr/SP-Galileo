package com.example.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.data.Helper;
import com.example.data.Image;
import com.example.data.ImageAdapter;
import com.example.demo3.R;
import com.example.fragments.PhotoDialogFragment;
import com.example.fragments.PhotoDialogFragment.NoticeDialogListener;

public class MainActivity extends FragmentActivity implements OnClickListener,NoticeDialogListener{
	Button btnPhoto ;
	Button btnUpdate; 
	RequestQueue requestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridView =(GridView) findViewById(R.id.grid);
		gridView.setAdapter(new ImageAdapter(this));
		btnUpdate =  (Button) findViewById(R.id.btnUpdate);
		btnPhoto = (Button) findViewById(R.id.btnPhoto);
		btnPhoto.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
		requestQueue = Volley.newRequestQueue(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == btnPhoto.getId()){
			new PhotoDialogFragment().show(getSupportFragmentManager(),"");
		}else if(v.getId() == btnUpdate.getId()){
			APICall();
		}
		
	}

	private void APICall() {
		// TODO Auto-generated method stub
		String url = Helper.getRecentMediaUrl("guatemala");
		findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
		btnUpdate.setEnabled(false);
		Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>(){

			@Override
			public void onResponse(JSONObject arg0) {
				// TODO Auto-generated method stub
				findViewById(R.id.progressBar).setVisibility(View.GONE);
				findViewById(R.id.grid).setVisibility(View.VISIBLE);
				btnUpdate.setEnabled(true);
				JSONArray data;
				ArrayList<Image> imageArray = new ArrayList<Image>();
				try{
					data = arg0.getJSONArray("data");
					for(int i =0;i<data.length();i++){
						JSONObject element = data.getJSONObject(i);
						String type = element.getString("type");
						if(type.equals("image")){
							JSONObject user = element.getJSONObject("user");
							JSONObject images = element.getJSONObject("images");
							JSONObject standardResolution = images.getJSONObject("standard_resolution");
							String userName = user.getString("username");
							String imgUrl = standardResolution.getString("url");
							Image image = new Image();
							image.setImgUrl(imgUrl);
							image.setUserName(userName);
							imageArray.add(image);
						}
					}
					for(Image image:imageArray){
						Log.e("TAG", image.getImgUrl());
					}
				}catch(JSONException e){
					Log.e("ERROR", Log.getStackTraceString(e));
				}
				Log.e("Respuesta ", arg0.toString());
				
			}};
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, listener, null);
		requestQueue.add(request);
	}

	@Override
	public void onDialogPositiveClick() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,CameraActivity.class);
		startActivity(intent);
	}

	@Override
	public void onDialogNegativeClick() {
		// TODO Auto-generated method stub
		Toast.makeText(this,"Hizo click in no", Toast.LENGTH_SHORT).show();
	}	
}
