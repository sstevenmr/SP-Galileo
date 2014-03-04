package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.data.FlagPagerAdapter;
import com.example.ejemplo.R;

public class CountriesFlagFragment extends Fragment {
	ViewPager viewPager;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		FlagPagerAdapter adapter = new FlagPagerAdapter(getChildFragmentManager());
		viewPager.setAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_contries_flags, container,false);
		viewPager = (ViewPager)view.findViewById(R.id.pager);
		return view;
	}

	
}
