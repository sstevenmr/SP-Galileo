package com.example.data;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demo3.R;

public class ImageAdapter extends BaseAdapter {
	int[] arrayFlags = new int[]{R.drawable.brazil,
			R.drawable.mexico,
			R.drawable.colombia,
			R.drawable.argentina,
			R.drawable.peru,
			R.drawable.venezuela,
			R.drawable.chile,
			R.drawable.ecuador,
			R.drawable.guatemala,
			R.drawable.cuba};
	String[] arrayCountries = new String[]{"Brasil","Mexico",
			"Colombia","Argentina","peru","Venezuela","chile",
			"Ecuador","Guatemala","Cuba"};
	private Resources resources;
	private LayoutInflater inflater;

	public ImageAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.resources = context.getResources();
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayFlags.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = inflater.inflate(R.layout.grid_image, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
			holder.imgFlag = (ImageView) convertView.findViewById(R.id.imageflag);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtName.setText(arrayCountries[position]);
		holder.imgFlag.setImageResource(arrayFlags[position]);
		return convertView;
	}
	static class ViewHolder{
		public TextView txtName;
		public ImageView imgFlag;	
	}
	
}
