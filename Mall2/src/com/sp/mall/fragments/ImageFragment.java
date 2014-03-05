package com.sp.mall.fragments;

import com.sp.mall.R;
import com.sp.mall.R.id;
import com.sp.mall.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageFragment extends Fragment {
	public final static String RESOURCE = "resource";
	public ImageFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_image, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
		Bundle args = getArguments();
		imageView.setImageResource(args.getInt(RESOURCE));
		return view;
	}

}
