package com.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.example.data.ImageAdapter;
import com.example.demo3.R;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridView =(GridView) findViewById(R.id.grid);
		gridView.setAdapter(new ImageAdapter(this));
		Button btnPhoto = (Button) findViewById(R.id.btnPhoto);
		btnPhoto.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
