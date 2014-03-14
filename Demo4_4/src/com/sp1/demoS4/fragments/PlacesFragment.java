package com.sp1.demoS4.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sp1.demoS4.R;

public class PlacesFragment extends SupportMapFragment implements OnMapLongClickListener {
	private GoogleMap map;
	private Bundle savedInstanceSaved;
	public static final LatLng GUATEMALA = new LatLng(14.62, -90.56);
	private HashMap<String, Marker> markers = new HashMap<String, Marker>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.savedInstanceSaved = savedInstanceState;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setupMap();
	}
	private void setupMap() {
		if(map == null){
			map = getMap();
			if(map != null){
				if(savedInstanceSaved == null){
					map.moveCamera(CameraUpdateFactory.newLatLngZoom(GUATEMALA, 10));
					map.setOnMapLongClickListener(this);
					map.setMyLocationEnabled(true);
				}
				map.getUiSettings().setZoomControlsEnabled(false);
			}
		}
	}
	@Override
	public void onMapLongClick(LatLng arg0) {
		String date = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()).format(Calendar.getInstance().getTime());
		String time = new SimpleDateFormat("HH:mm",Locale.getDefault()).format(Calendar.getInstance().getTime());
		String title = getString(R.string.txt_marker_title);
		String snipet = getString(R.string.txt_marker_snipet);
		MarkerOptions options = new MarkerOptions().position(arg0).title(title).snippet(snipet);
		Marker marker = map.addMarker(options);
		markers.put(title, marker);
	}
	
	
	
	
}
