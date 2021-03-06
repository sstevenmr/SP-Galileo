package com.example.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class MainActivity extends FragmentActivity implements OnClickListener,NoticeDialogListener{
	Button btnParse ;
	Button btnPhoto ;
	Button btnUpdate; 
	ImageAdapter adapter; 
	ArrayList<Image> imagesArray;
	public static RequestQueue requestQueue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this,  "cedTUHxhF0o8fQ2a8QBOE1SYUV9MpqiclRAIpMa6", "waZUazedW0bHLqRlI7mWSg2x5t0RerR8KI9pKJYm");
		setContentView(R.layout.activity_main);
		requestQueue = Volley.newRequestQueue(this);
		GridView gridView =(GridView) findViewById(R.id.grid);
		imagesArray = new ArrayList<Image>();
	    adapter = new ImageAdapter(this,imagesArray);
		gridView.setAdapter(adapter);
		btnParse =  (Button) findViewById(R.id.btnParse);
		btnUpdate =  (Button) findViewById(R.id.btnUpdate);
		btnPhoto = (Button) findViewById(R.id.btnPhoto);
		
		btnPhoto.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
		btnParse.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == btnPhoto.getId()){
			new PhotoDialogFragment().show(getSupportFragmentManager(),"");
		}else if(v.getId() == btnUpdate.getId()){
			APICall();
		}else if(v.getId() == btnParse.getId()){
			parse();
		}
	}

	private void parse() {
		// TODO Auto-generated method stub
		ParseObject test = new ParseObject("Prueba");
		test.put("nombre", "Steven");
		test.saveInBackground();
		Log.e("TAG","Guardando....");
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Prueba");
		query.getInBackground("kVQbrj8DU5", new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject arg0, ParseException arg1) {
				// TODO Auto-generated method stub
				if(arg0 != null){
					Toast.makeText(getApplicationContext(),arg0.getString("nombre"), Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void showNotificaction(){
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher).setContentTitle(getString(R.string.txt_notif_title)).setContentText(getString(R.string.txt_notif_subtitle));
		NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		Intent result = new Intent(this,CameraActivity.class);
		TaskStackBuilder stackBuilder  = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(CameraActivity.class);
		stackBuilder.addNextIntent(result);
		PendingIntent resultPendintIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(resultPendintIntent);
		manager.notify(1, builder.build());
		
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
							imagesArray.add(image);
						}
					}
					adapter.notifyDataSetChanged();
					showNotificaction();
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
