package com.example.data;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ejemplo.R;
import com.example.fragments.FlagFragment;
public class FlagPagerAdapter extends FragmentPagerAdapter {
	
	private int[] arrayFlags = new int[]{
			R.drawable.brazil,
			R.drawable.mexico,
			R.drawable.colombia,
			R.drawable.argentina,
			R.drawable.peru,
			R.drawable.venezuela,
			R.drawable.chile,
			R.drawable.ecuador,
			R.drawable.guatemala,
			R.drawable.cuba
	};
	public FlagPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
	
		Fragment fragment = new FlagFragment();
		Bundle args = new Bundle();
		args.putInt(FlagFragment.RESOURCE,arrayFlags[position]);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {	
		return arrayFlags.length;
	}

}
