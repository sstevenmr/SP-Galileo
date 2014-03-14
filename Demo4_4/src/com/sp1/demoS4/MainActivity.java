package com.sp1.demoS4;

import android.app.Dialog;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.sp1.demoS4.fragments.ErrorDialogFragment;
import com.sp1.demoS4.fragments.PlacesFragment;


public class MainActivity extends FragmentActivity implements OnConnectionFailedListener,ConnectionCallbacks,LocationListener{

	private LocationClient locationClient;
	private LocationRequest locationRequest;
	public static final int CONNECTION_FAILURE_REQUEST = 9000;
	public static final int MILLISECONDS_PER_SECOND = 1000;
	public static final long UPDATE_INTERVAL_IN_MILLISECONDS =  MILLISECONDS_PER_SECOND * 5;
	public static long FAST_INTERVAL_CEILING_IN_MILLISECONDS = MILLISECONDS_PER_SECOND * 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		locationClient = new LocationClient(this, this, this);
		locationRequest = LocationRequest.create();
		
		locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
		locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		locationRequest.setFastestInterval(FAST_INTERVAL_CEILING_IN_MILLISECONDS);
	
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
	protected void onStart() {
		super.onStart();
		locationClient.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if(locationClient.isConnected()){
			locationClient.removeLocationUpdates(this);
		}
		locationClient.disconnect();
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

	@Override
	public void onConnected(Bundle arg0) {
	updateLocation(locationClient.getLastLocation());
	locationClient.requestLocationUpdates(locationRequest,this);
		
	}

	private void updateLocation(Location currentLocation) {
			TextView txtLatLng = (TextView) findViewById(R.id.txtLatLng);
			String 	latLng = "No disponible";
			if(currentLocation != null){
				latLng = getString(R.string.txt_lat_lng,currentLocation.getLatitude(),currentLocation.getLongitude());
			}
			txtLatLng.setText(latLng);
	}

	@Override
	public void onDisconnected() {
	}

	@Override
	public void onLocationChanged(Location arg0) {
		//Log.e("ACTUALIZACION", arg0.toString());
		updateLocation(arg0);
		
	}

}
