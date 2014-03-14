package com.sp1.demoS4;

import android.app.Dialog;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.sp1.demoS4.fragments.ErrorDialogFragment;
import com.sp1.demoS4.fragments.PlacesFragment;


public class MainActivity extends FragmentActivity implements OnConnectionFailedListener{

	public static final int CONNECTION_FAILURE_REQUEST = 9000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	@Override
	protected void onResume() {	
		super.onResume();
		FragmentManager manager = getSupportFragmentManager();
		PlacesFragment fragment = (PlacesFragment) manager.findFragmentById(R.id.fragmentMap);
		if(servicesConected()){
			manager.beginTransaction().show(fragment).commit();
		}else{
			manager.beginTransaction().hide(fragment).commit();
		}
	}

	private boolean servicesConected() {
		int code = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(code == ConnectionResult.SUCCESS){
			return true;
		}else{
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(code, this, 0);
			if(dialog != null){
				ErrorDialogFragment fragment = new ErrorDialogFragment();
				fragment.setDialog(dialog);
				fragment.show(getSupportFragmentManager(), "error");
			}
			return false;
		}
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		if(connectionResult.hasResolution()){
			try{
				connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_REQUEST);
			}catch(IntentSender.SendIntentException e){
				Log.e("ERROR", Log.getStackTraceString(e));
			}
		}else{
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, CONNECTION_FAILURE_REQUEST);
			if(dialog != null){
				ErrorDialogFragment fragment = new ErrorDialogFragment();
				fragment.setDialog(dialog);
				fragment.show(getSupportFragmentManager(), "error");
			}
			
		}
	}

}
