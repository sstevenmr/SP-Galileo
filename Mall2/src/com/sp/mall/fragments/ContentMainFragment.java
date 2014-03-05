package com.sp.mall.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.sp.mall.activities.MainActivity;
import com.sp.mall.R;
import com.sp.mall.fragments.*;

public class ContentMainFragment extends Fragment implements TabListener {
		ListView lista;
		ArrayAdapter<String> adaptador;
		ArrayList<String> stores;
	    Intent intent = null;
	    FragmentManager manager;
	    private Fragment[] fragments = new Fragment[]{new ListadoFragment(), new MapFragment()};
	    Button btnImage;    	
	    @Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			 ActionBar actionbar =((MainActivity) getActivity()).getSupportActionBar();
	         actionbar.setDisplayHomeAsUpEnabled(true);
	 		 actionbar.setHomeButtonEnabled(true);
	         manager  = getActivity().getSupportFragmentManager();
	         manager.beginTransaction().add(R.id.mainContent,fragments[0]).show(fragments[0]).add(R.id.mainContent,fragments[1]).hide(fragments[1]).commit();
	         actionbar.addTab(actionbar.newTab().setText("Listado").setTabListener( this));
			 actionbar.addTab(actionbar.newTab().setText("Mapa").setTabListener( this));
	    
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			return inflater.inflate(R.layout.fragment_content, container,false);
		}
	        
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction ft) {
		}
		@Override
		public void onTabSelected(Tab arg0, FragmentTransaction ft) {
			Fragment toHide = null;
			Fragment toShow = null;
			switch (arg0.getPosition()) {
			case 0:
				toHide = fragments[1];
				toShow = fragments[0];
				break;
			case 1:
				toHide = fragments[0];
				toShow = fragments[1];
				break;
			default:
				break;
			}
			ft.hide(toHide);
			ft.show(toShow);		
		}
		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}


