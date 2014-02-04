package com.sp.mall;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	public static final String QUERY = "query";
	public static final int POSITION = 1;
	ArrayList<Store> datos;
	public Store store = null;
	public TextView txtStore;
	public TextView txtAdress;
	public TextView txthoraryI;
	public TextView txthoraryF;
	public TextView txtPhoneNumber;
	public TextView txtWebSite;
	public TextView txtEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);		
		this.createStore();
		this.changeTextView();
		this.call();
		
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
	public void createStore(){
		datos = new ArrayList<Store>();
		datos.add(new Store("Tienda de Zapatos","435 Mayfield Ave, Stanford, CA","51328976","10:00","8:00","www.kzaptos.com","kzaptos@blitmall.com"));
		datos.add( new Store("Tienda de Ropa","436 Mayfield Ave, Stanford, CA","51328977","10:00","8:00","www.kropa.com","kropa@blitmall.com"));
		datos.add( new Store("Tienda de Libros","437 Mayfield Ave, Stanford, CA","51328979","10:00","8:00","www.klibros.com","klibros@blitmall.com"));	
	 	datos.add(new Store("Tienda de Tecnologia","438 Mayfield Ave, Stanford, CA","51328980","10:00","8:00","www.ktecnologia.com","ktecnologia@blitmall.com"));
	}
	
	

	public void changeTextView(){
		Intent intent = getIntent();
		int posDatos = intent.getIntExtra(QUERY, POSITION);
		txtStore = (TextView) findViewById(R.id.textView1);
		txtAdress = (TextView) findViewById(R.id.textView2);
		txthoraryI = (TextView) findViewById(R.id.textView3);
		txthoraryF = (TextView) findViewById(R.id.textView4);
		txtPhoneNumber = (TextView) findViewById(R.id.textView5);
		txtWebSite = (TextView) findViewById(R.id.textView6);
		txtEmail = (TextView) findViewById(R.id.textView7);
		
		txtStore.setText(datos.get(posDatos).getStore());
		Linkify.addLinks(txtStore, Linkify.ALL);
		txtStore.setTypeface(null, Typeface.BOLD);
		txtAdress.setText(datos.get(posDatos).getAdress());
		Linkify.addLinks(txtAdress, Linkify.ALL);
		txthoraryI.setText(datos.get(posDatos).getHoraryI());
		Linkify.addLinks(txthoraryI, Linkify.ALL);
		txthoraryF.setText(datos.get(posDatos).getHoraryF());
		Linkify.addLinks(txthoraryF, Linkify.ALL);
		txtPhoneNumber.setText(datos.get(posDatos).getPhoneNumber());
		Linkify.addLinks(txtPhoneNumber, Linkify.ALL);
		txtWebSite.setText(datos.get(posDatos).getWebSite());
		Linkify.addLinks(txtWebSite, Linkify.ALL);
		txtEmail.setText(datos.get(posDatos).getEmail());
		Linkify.addLinks(txtEmail, Linkify.ALL);
		
	}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
