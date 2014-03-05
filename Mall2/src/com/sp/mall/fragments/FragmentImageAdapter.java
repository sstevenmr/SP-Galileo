package com.sp.mall.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sp.mall.R;

public class FragmentImageAdapter extends FragmentPagerAdapter {
	private int[] arrayImage = new int[]{
			R.drawable.img1,
			R.drawable.mall1,
			R.drawable.mall2,
			R.drawable.mall3,
			R.drawable.mall4,
			
	};
	public FragmentImageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		
		Fragment fragment = new ImageFragment();
		Bundle args = new Bundle();
		args.putInt(ImageFragment.RESOURCE, arrayImage[arg0]);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayImage.length;
	}

}
