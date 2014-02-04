package com.sp.mall;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ImageActivity extends Activity {
	public static String QUERY = "query";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
		return true;
	}

}
