package com.example.ejemplo;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements OnItemClickListener{
	public String country = "";
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
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean landScape = getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE;
		MenuItem share = menu.getItem(menu.size() - 1 );
		share.setVisible(landScape);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		// TODO Auto-generated method stub
		country = adapterView.getItemAtPosition(position).toString();
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
