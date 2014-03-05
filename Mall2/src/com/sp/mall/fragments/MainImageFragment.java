package com.sp.mall.fragments;

import com.sp.mall.R;
import com.sp.mall.R.id;
import com.sp.mall.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainImageFragment extends Fragment {
	ViewPager viewpager;
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		FragmentImageAdapter adapter = new FragmentImageAdapter(getChildFragmentManager());
		viewpager.setAdapter(adapter);
	}

	public MainImageFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_image_pager, container,false);
		viewpager = (ViewPager) view.findViewById(R.id.pager);
		return view;
	}

}
