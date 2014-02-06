package com.example.ejemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

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
		getMenuInflater().inflate(R.menu.country_detail, menu);
		return true;
	}

	public String getCountry() {
		return country;
	}

}
