package com.example.data;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.activities.MainActivity;
import com.example.demo3.BitmapLRUCache;
import com.example.demo3.R;

public class ImageAdapter extends BaseAdapter {
	private ImageLoader imageLoader;
	private LayoutInflater inflater;
	private ArrayList<Image> dataArray;
	
	public ImageAdapter(Context context,ArrayList<Image> dataArray) {
		// TODO Auto-generated constructor stub
		this.dataArray = dataArray;
		this.inflater = LayoutInflater.from(context);
		this.imageLoader = new ImageLoader(MainActivity.requestQueue, new BitmapLRUCache());
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataArray.size();
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
		Image current = dataArray.get(position);
		if(convertView==null){
			convertView = inflater.inflate(R.layout.grid_image, null);
			holder = new ViewHolder();
			holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
			holder.imgFlag = (NetworkImageView) convertView.findViewById(R.id.imageflag);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txtName.setText(current.getUserName());
		holder.imgFlag.setImageUrl(current.getImgUrl(), imageLoader);
		return convertView;
	}
	static class ViewHolder{
		public TextView txtName;
		public NetworkImageView imgFlag;	
	}
	
}
