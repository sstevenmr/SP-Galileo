package com.example.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.data.ImageAdapter;
import com.example.demo3.R;
import com.example.fragments.PhotoDialogFragment;
import com.example.fragments.PhotoDialogFragment.NoticeDialogListener;

public class MainActivity extends FragmentActivity implements OnClickListener,NoticeDialogListener{
	Button btnPhoto ;
	Button btnUpdate; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GridView gridView =(GridView) findViewById(R.id.grid);
		gridView.setAdapter(new ImageAdapter(this));
		btnUpdate =  (Button) findViewById(R.id.btnUpdate);
		btnPhoto = (Button) findViewById(R.id.btnPhoto);
		btnPhoto.setOnClickListener(this);
		btnUpdate.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == btnPhoto.getId()){
			new PhotoDialogFragment().show(getSupportFragmentManager(),"");
		}else if(v.getId() == btnUpdate.getId()){
			new APITask().execute();
		}
		
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
	class APITask extends AsyncTask<Void,Void,Void>{
		ProgressBar progressbar;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			progressbar = (ProgressBar) findViewById(R.id.progressBar);
			progressbar.setVisibility(View.VISIBLE);
			btnUpdate.setEnabled(false);
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try{
				Thread.sleep(2000);
			}catch(InterruptedException e){
				
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progressbar.setVisibility(View.GONE);
			findViewById(R.id.grid).setVisibility(View.VISIBLE);
			btnUpdate.setEnabled(true);
		}

		
	}
	
}
