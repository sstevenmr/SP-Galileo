package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.data.ImageAdapter;
import com.example.demo3.R;
import com.example.fragments.PhotoDialogFragment;
import com.example.fragments.PhotoDialogFragment.NoticeDialogListener;

public class MainActivity extends FragmentActivity implements OnClickListener,NoticeDialogListener{

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
		new PhotoDialogFragment().show(getSupportFragmentManager(),"");
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
