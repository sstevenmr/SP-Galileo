package com.sp.mall.data;

import java.util.ArrayList;
import java.util.Hashtable;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sp.mall.data.Photo;
//import com.sp.mall.data.ImageAdapter.ViewHolder;
import com.sp.mall.R;
import com.sp.mall.fragments.ComunityFragment;

public class ImageAdapter extends BaseAdapter {
	public ArrayList<Integer> arrayImage = new ArrayList<Integer>();
	public ArrayList<String> arrayStringMall = new ArrayList<String>();
	//private Resources resources;
	public static int codigo=0;
	Hashtable<Integer, Bitmap> bitmaps = new Hashtable<Integer,Bitmap>();
	ViewHolder holder;
	private ArrayList<Photo> dataArray;
	
	private ImageLoader imageLoader;
	private LayoutInflater inflater;
	
	public ImageAdapter(Context context,ArrayList<Photo> dataArray){
		this.dataArray = dataArray;
		this.inflater = LayoutInflater.from(context);
		this.imageLoader = new ImageLoader(ComunityFragment.requestQueue, new BitmapLRUCache());
	}

	@Override
	public int getCount() {
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		Photo current = dataArray.get(position);
		codigo = dataArray.size();
			if(convertView ==null){
				convertView = inflater.inflate(R.layout.list_image, null);
				holder = new ViewHolder();
				holder.txtName = (TextView) convertView.findViewById(R.id.txtComment);
				holder.imgFlag = (NetworkImageView) convertView.findViewById(R.id.imageflag);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();	
			
			}
			if(current.getCommentList()!=null){
				holder.txtName.setText(current.getDescripcion());
				holder.imgFlag.setImageUrl(current.getURL(), imageLoader);
			}else{
				holder.txtName.setText(current.getDescripcion());
				holder.imgFlag.setImageBitmap(resizeBitmap(400, 400, current.getURL()));
			}
			this.notifyDataSetChanged();
		return convertView;
	}
	
	static class ViewHolder{
		public ImageView imgMall;
		public TextView txtName;
		public NetworkImageView imgFlag;	
	}
	public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth,int reqHight){
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res,resId,options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId,options);
	}
	
	public Bitmap resizeBitmap(int targetW, int targetH,String path) {
	    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
	    bmOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, bmOptions);
	    int photoW = bmOptions.outWidth;
	    int photoH = bmOptions.outHeight;

	    int scaleFactor = 1;
	    if ((targetW > 0) || (targetH > 0)) {
	            scaleFactor = Math.min(photoW/targetW, photoH/targetH);        
	    }

	    bmOptions.inJustDecodeBounds = false;
	    bmOptions.inSampleSize = scaleFactor;
	    bmOptions.inPurgeable = true;

	    return BitmapFactory.decodeFile(path, bmOptions);            
	}
	
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {

	        // Calculate ratios of height and width to requested height and width
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio  = Math.round((float) width / (float) reqWidth);

	        // Choose the smallest ratio as inSampleSize value, this will guarantee
	        // a final image with both dimensions larger than or equal to the
	        // requested height and width.
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }

	    return inSampleSize;
	}
}
