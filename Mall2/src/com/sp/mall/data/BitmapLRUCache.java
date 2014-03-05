package com.sp.mall.data;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;


public class BitmapLRUCache extends LruCache<String, Bitmap> implements ImageCache{
	private static final int CACHE_SIZE_BYTES = 4 * 1024 * 1024;   
	public BitmapLRUCache() {
		super(CACHE_SIZE_BYTES);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public Bitmap getBitmap(String arg0) {
		// TODO Auto-generated method stub
		return get(arg0);
	}

	@Override
	public void putBitmap(String arg0, Bitmap arg1) {
		// TODO Auto-generated method stub
		put(arg0,arg1);
	}
	
	@Override
	protected int sizeOf(String key,Bitmap value){
		return value.getRowBytes()*value.getHeight() ;
	}
}
