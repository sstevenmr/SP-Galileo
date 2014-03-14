package com.sp1.demoS4.fragments;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class PlacesFragment extends SupportMapFragment {
	private GoogleMap map;
	private Bundle savedInstanceSaved;
	public static final LatLng GUATEMALA = new LatLng(14.62, -90.56);
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
				}
				map.getUiSettings().setZoomControlsEnabled(false);
			}
		}
	}
	
	
	
	
}
