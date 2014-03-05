package com.sp.mall.fragments;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sp.mall.R;
import com.sp.mall.data.Comment;
import com.sp.mall.data.Helper;
import com.sp.mall.data.ImageAdapter;
import com.sp.mall.data.Photo;

public class ComunityFragment extends Fragment { 
	private static final int LOAD_IMAGE =1;
	private static final int CAMERA = 2;
	ImageButton btnFromCamera;
	ImageAdapter adapter=null;
	View view;
	ListView list;
	PhotoDialogFragment dialog;
	ArrayList<Photo> imagesArray;
	Photo image;
	String  photoPath;
	ArrayList<Comment> test = new ArrayList<Comment>();
	public static RequestQueue requestQueue;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
	        Bundle savedInstanceState) { 
	        view = inflater.inflate(R.layout.comunity_fragment,container, false);
	    	requestQueue = Volley.newRequestQueue(getActivity());
	        list = (ListView) view.findViewById(R.id.listViewP);
	        imagesArray = new ArrayList<Photo>();
	        adapter = new ImageAdapter(getActivity(),imagesArray);
	        list.setAdapter(adapter);
	        btnFromCamera = (ImageButton) view.findViewById(R.id.btnFromCamera);
	        dialog = new PhotoDialogFragment();
	        btnFromCamera.setOnClickListener(new OnClickListener() {
	    				@Override
	    				public void onClick(View v) {
	    					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    					builder.setTitle("Menu");
	    					ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
	    					adaptador.add("Tomar Fotografia");
	    					adaptador.add("Desde la galeria");
	    					builder.setAdapter(adaptador, new DialogInterface.OnClickListener() {
	    						
	    						@Override
	    						public void onClick(DialogInterface dialog, int which) {
	    							int code = 0;
	    							Intent intent = null;
	    							if(which==0){
	    								 intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    								 code = CAMERA; 
	    								 File photo = setupFile();
	    								 photoPath = photo.getAbsolutePath();
	    								 intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
	    							}else if(which==1){
	    								intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	    								code = LOAD_IMAGE;
	    							}
	    								startActivityForResult(intent, code);
	    						}
	    					});
	    				builder.create().show();
	    				}
	    			});
	        APICall();
	        return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String bitmap = "";
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Calendar.getInstance().getTime());
		switch(requestCode){
		case LOAD_IMAGE:
			if (resultCode == getActivity().RESULT_OK)
				bitmap = fromGallery(data);
			break;
		case CAMERA:
			if (resultCode == getActivity().RESULT_OK)
				bitmap = fromCamera(data);
			break;
			
		}
		imagesArray.add(new Photo(bitmap,timeStamp,null,"20"));
	//	adapter.setArrays(bitmap.hashCode(),text,bitmap);	
	}
	
	private void APICall() {
		// TODO Auto-generated method stub
		String url = Helper.getRecentMediaUrl("guatemala");
		view.findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
		Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>(){
			@Override
			public void onResponse(JSONObject arg0) {
				// TODO Auto-generated method stub
				view.findViewById(R.id.progressBar1).setVisibility(View.GONE);
			    view.findViewById(R.id.listViewP).setVisibility(view.VISIBLE);
				JSONArray data;
				try{
					data = arg0.getJSONArray("data");
					for(int i =0;i<data.length();i++){
						JSONObject element = data.getJSONObject(i);
						String type = element.getString("type");
						if(type.equals("image")){
							JSONObject user = element.getJSONObject("user");
							JSONObject images = element.getJSONObject("images");
							JSONObject standardResolution = images.getJSONObject("standard_resolution");
							String userName = user.getString("username");
							String imgUrl = standardResolution.getString("url");		
							test.add(new Comment("hola"));
							image = new Photo(imgUrl,userName,test,"20");
							imagesArray.add(image);
							
						}
						
					}
					adapter.notifyDataSetChanged();
				}catch(JSONException e){
					Log.e("ERROR", Log.getStackTraceString(e));
				}
				Log.e("Respuesta ", arg0.toString());
				
			}};
			
		JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null, listener, null);
		requestQueue.add(request);
	}
	public String fromGallery(Intent data){
		//Bitmap bitmap=null;
		String picturePath="";
		if(data != null){
			Uri selectedImage = data.getData();
			String[] filePathColumn = {MediaStore.Images.Media.DATA};
			Cursor cursor =getActivity().getContentResolver().query(selectedImage,filePathColumn,null,null,null);
			if(cursor.moveToFirst()){
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				picturePath = cursor.getString(columnIndex);
				cursor.close();	
				// bitmap = picturePath;		
				//ImageView img = (ImageView)view.findViewById(R.id.imageView1);	
				//img.setImageBitmap(bit);
				
				
			}
		}
		return picturePath;
	}
	public String fromCamera(Intent data){
		Intent mediaScan = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
		File file = new File(photoPath);
		Uri contentUri = Uri.fromFile(file);
		mediaScan.setData(contentUri);
		getActivity().sendBroadcast(mediaScan);
		return photoPath;
	}
	
	
	private File setupFile() {
		// TODO Auto-generated method stub
		File albumDir;
		String albumName = "ejemplo";
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO){
			albumDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
			
		}else{
			albumDir = new File(Environment.getExternalStorageDirectory()+"/dcim/"+albumName);
		}
		albumDir.mkdirs();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(Calendar.getInstance().getTime());
		String imageFileName = "IMG_"+timeStamp+".jpg";
		File image = new File(albumDir + "/" + imageFileName);
		return image;
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
		
}

