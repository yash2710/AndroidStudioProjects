package com.iste.itnu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WhatsHotFragment extends Fragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		galleryadapter adapter = new galleryadapter();
		ViewPager myPager = (ViewPager) getActivity().findViewById(R.id.customviewpager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(0);
	}


	public WhatsHotFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_whats_hot, container, false);
         
        return rootView;
    }
	
}
