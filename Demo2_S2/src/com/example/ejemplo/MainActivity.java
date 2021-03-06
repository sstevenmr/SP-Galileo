package com.example.ejemplo;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements OnItemClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String[] arrayContries = new String[]{
				"Brasil","Mexico","Colombia",
				"Argentina", "Peru","Venezuela",
				"Chile","Ecuador","Guatemala","Cuba"
		};
		ArrayList<String> contries = new ArrayList<String>(Arrays.asList(arrayContries));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contries);
		ListView list = (ListView) findViewById(R.id.listView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		// TODO Auto-generated method stub
		String country = adapterView.getItemAtPosition(position).toString();
		if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){
				FragmentManager manager = getSupportFragmentManager();
				CountryInfoFragment fragment =(CountryInfoFragment) manager.findFragmentById(R.id.fragmentCountryInfo);
				fragment.loadWebViewContent(country);
		}else{
			Intent intent = new Intent(this, CountryDetailActivity.class);
			intent.putExtra(CountryDetailActivity.COUNTRY, country);
			startActivity(intent);
		}
		
	}

}
