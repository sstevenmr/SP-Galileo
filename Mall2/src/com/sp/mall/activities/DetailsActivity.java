package com.sp.mall.activities;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sp.mall.R;
import com.sp.mall.data.Comment;
import com.sp.mall.data.Store;

public class DetailsActivity extends FragmentActivity {
	public static final String QUERY = "query";
	public static final int POSITION = 1;
	public Store store = null;
	public TextView txtStore;
	public TextView txtAdress;
	public TextView txthorary;
	public TextView txtPhoneNumber;
	public TextView txtWebSite;
	public TextView txtEmail;
	int posDatos;
	ListView lista;
	TextView comment;
	ArrayAdapter<String> adaptador;
	List<Store> stores;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);	
		this.getData();
		this.changeTextView();
		this.call();
		lista = (ListView) findViewById(R.id.listViewComments);
		comment = (TextView) findViewById(R.id.editTextComment);
		adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
		lista.setAdapter(adaptador);
		ArrayList<Comment> comentarios = stores.get(POSITION).getCommentList();
		for(int i=0;i<comentarios.size(); i++){
			adaptador.add(comentarios.get(i).toString());
		}
		sendComment();
		
	}
	
	public void call(){
		Button btnCall = (Button) findViewById(R.id.btnCall);
		btnCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_DIAL);
				intent.setData(Uri.parse("tel:"+txtPhoneNumber.getText()));
				startActivity(intent);
				
			}
		});
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
        Type typeStores = new TypeToken<List<Store>>(){}.getType();
        stores = gson.fromJson(myjsonstring, typeStores);   
	}

	public void changeTextView(){
		Intent intent = getIntent();
	    posDatos = intent.getIntExtra(QUERY, POSITION);
		txtStore = (TextView) findViewById(R.id.textView1);
		txtAdress = (TextView) findViewById(R.id.textView2);
		txthorary = (TextView) findViewById(R.id.textView3);
		txtPhoneNumber = (TextView) findViewById(R.id.textView5);
		txtWebSite = (TextView) findViewById(R.id.textView6);
		txtEmail = (TextView) findViewById(R.id.textView7);
		
		txtStore.setText(stores.get(posDatos).getName());
		Linkify.addLinks(txtStore, Linkify.ALL);
		txtStore.setTypeface(null, Typeface.BOLD);
		txtAdress.setText(stores.get(posDatos).getAddress());
		Linkify.addLinks(txtAdress, Linkify.ALL);
		txthorary.setText(stores.get(posDatos).getHorary());
		Linkify.addLinks(txthorary, Linkify.ALL);
		txtPhoneNumber.setText(stores.get(posDatos).getPhoneNumber());
		Linkify.addLinks(txtPhoneNumber, Linkify.ALL);
		txtWebSite.setText(stores.get(posDatos).getWebSite());
		Linkify.addLinks(txtWebSite, Linkify.ALL);
		txtEmail.setText(stores.get(posDatos).getEmail());
		Linkify.addLinks(txtEmail, Linkify.ALL);
		
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_share:
				return shareText();
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public boolean shareText(){
		try{
			String msg =getString(R.string.text_share,stores.get(posDatos).getName(),
					  stores.get(posDatos).getAddress(),
					  stores.get(posDatos).getHorary(),
					  stores.get(posDatos).getPhoneNumber(),
					  stores.get(posDatos).getWebSite(),
					  stores.get(posDatos).getEmail());
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_TEXT,msg);
			intent.setType("text/plain");
			startActivity(Intent.createChooser(intent, getString(R.string.action_share)));
			return true;
		}catch(Exception e){
			return false;
		}
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
