package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.demo3.R;

public class CameraActivity extends Activity implements OnClickListener{
	private static final int LOAD_IMAGE = 2;
	private static final int CAMERA = 1;
	Button btnFromGallery;
	Button btnFromCamera;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		btnFromCamera = (Button) findViewById(R.id.btnFromCamera);
		btnFromGallery = (Button) findViewById(R.id.btnFromGallery);
		btnFromGallery.setOnClickListener(this);
		btnFromCamera.setOnClickListener(this);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
			case LOAD_IMAGE:
				if(resultCode==RESULT_OK){
					fromGallery(data);
				}
				break;
			case CAMERA:
				if(resultCode == RESULT_OK){
					System.out.print("hola");
					fromCamera(data);
					
				}
				break;
		}
		
	}
public void fromCamera(Intent data){
	Bundle extras = data.getExtras();
	if(extras != null){
		ImageView img = (ImageView) findViewById(R.id.img);
		img.setImageBitmap((Bitmap)extras.get("data"));
	}
	
}
public void fromGallery(Intent data){
	if(data != null ){
		Uri selectImage = data.getData();
		String[] filePathColumn = {MediaStore.Images.Media.DATA};
		Cursor cursor = getContentResolver().query(selectImage, filePathColumn, null, null, null);
		if(cursor.moveToFirst()){
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			ImageView img = (ImageView) findViewById(R.id.img);
			img.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		}
			
	}
}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent intent = null;
		int code = 0;
		if(arg0.getId() == btnFromGallery.getId()){
			intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			code = LOAD_IMAGE;
		}else if(arg0.getId() == btnFromCamera.getId()){
			intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			code = CAMERA;
		}
		
		startActivityForResult(intent, code);
	}

}
