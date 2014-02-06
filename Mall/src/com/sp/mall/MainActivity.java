package com.sp.mall;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;

public class MainActivity extends Activity {
	ListView lista;
	ArrayAdapter<String> adaptador;
	ArrayList<String> stores;
    Intent intent = null;
   
    Button btnImage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         btnImage = (Button) findViewById(R.id.button1);
         ButtonListener listener = new ButtonListener();
         btnImage.setOnClickListener(listener);
         this.listGenerator();
        
    }
 
    public void addData(){
        stores.add("Tienda de Zapatos"); 
        stores.add("Tienda de Ropa"); 
        stores.add("Tienda de Libros"); 
        stores.add("Tienda de Tecnologia"); 
    }
    
    public void listGenerator(){
	 	 stores = new ArrayList<String>();
	 	 this.addData();
		 lista = (ListView) findViewById(R.id.listView1);
		 adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, stores);
		 lista.setAdapter(adaptador);
		 lista.setTextFilterEnabled(true);
		 lista.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
						intent = new Intent(getApplicationContext(),DetailsActivity.class);
						intent.putExtra(DetailsActivity.QUERY, (int)position);
						startActivity(intent);
		        }
		 });
    }   
    class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			  Intent  intent = new Intent(getApplicationContext(),ImageActivity.class);
			 // intent.putExtra(ImageActivity.QUERY,"");
			  startActivity(intent);
		}
		
	
	
}
}