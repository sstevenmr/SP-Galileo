/**
 * 
 */
package com.sp.mall.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.sp.mall.R;
import com.sp.mall.data.DBAdapter;
	
public class CommentFragment extends Fragment {
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, 
		        Bundle savedInstanceState) {
		        View view = inflater.inflate(R.layout.activity_image, container, false);
		      /*  lista = (ListView) view.findViewById(R.id.listViewComments);
		        dbAdapter = new DBAdapter(view.getContext());
		        adaptador = lista.getAdapter();*/
		       
		        return view;
		    }
	 
}
