package com.iste.itnu;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class home extends Fragment {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		TextView t = (TextView)getActivity().findViewById(R.id.textView1);
		Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(), "Roboto-Thin.ttf");
		t.setTypeface(tf);
	}

	public home() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.mainhome, container, false);

		return rootView;
	}
}
