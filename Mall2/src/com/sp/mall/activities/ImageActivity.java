package com.sp.mall.activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sp.mall.R;
import com.sp.mall.data.Comment;
import com.sp.mall.data.Photo;

public class ImageActivity extends FragmentActivity {
	public static String QUERY = "query";
	ListView lista;
	TextView comment;
	ArrayAdapter<String> adaptador;
	ArrayList<Photo> photos; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_fragment);
		lista = (ListView) findViewById(R.id.listViewComments);
		comment = (TextView) findViewById(R.id.editTextComment);
		adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
		getData();
		ArrayList<Comment> comentarios = photos.get(photos.size()-1).getCommentList();
		for(int i=0;i<comentarios.size(); i++){
			adaptador.add(comentarios.get(i).toString());
		}
		lista.setAdapter(adaptador);
		sendComment();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void getData(){
		StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open(
                    "jsonData.json")));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String myjsonstring = sb.toString();
        Gson gson = new Gson();
        Type typePhotos = new TypeToken<List<Photo>>(){}.getType();
        photos = gson.fromJson(myjsonstring, typePhotos);   
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_share:
				return shareImage();
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public boolean shareImage(){
		try{
			Uri uri = Uri.parse("android.resource://com.sp.mall/"+R.drawable.img1);
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
			shareIntent.setType("image/jpeg");
			startActivity(Intent.createChooser(shareIntent, getResources().getText(R.string.action_share)));
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public void sendComment(){
		Button btnCall = (Button) findViewById(R.id.btnSendComment);
		btnCall.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				adaptador.add(comment.getText().toString());
				adaptador.notifyDataSetChanged();
			}
		});
	}
}
