package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ejemplo.R;

public class CountryDetailActivity extends FragmentActivity {
	private String country = "country";
	public static final String COUNTRY = "Country";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_country_detail);
		Intent intent = getIntent();
		country = intent.getStringExtra(COUNTRY);
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
			if(!country.equals("")){
				String url ="http://es.m.wikipedia.org/wiki/"+country;
				Intent intent = new Intent();
				String msg = getString(R.string.msg_share,country,url);
				intent.setAction(Intent.ACTION_SEND);
				intent.putExtra(Intent.EXTRA_TEXT,msg );
				intent.setType("text/plain");
				startActivity(Intent.createChooser(intent, getString(R.string.action_share)));
				}
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}

	public String getCountry() {
		return country;
	}

}
