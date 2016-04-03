package com.iste.itnu;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MagazineFragment extends Fragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		TextView t = (TextView)getActivity().findViewById(R.id.textevent);
		TextView t1 = (TextView)getActivity().findViewById(R.id.textevent1);
		ImageView i = (ImageView)getActivity().findViewById(R.id.imageevent);
		t.setText("ARCLIGHTZ one of the best magazines of NIRMA; shows everyone our strengths, potentials and past years achievements. Every year ISTE brings to the readers - ARCLIGHTZ, which provides them with a myriad of articles; sci-fi stories, poems, paintings, photographs, art work etc. The magazine comprises of articles from the various fields of technology, social awareness and achievers section. It is another feather in ISTEs cap suggesting the in genuine creativity that ISTE always aims at coming up with.");
		t1.setText("ARCLIGHTZ");
		i.setImageResource(R.drawable.arclightz);
		Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(), "Roboto-Regular.ttf");
		t.setTypeface(tf);
		Typeface tf1 = Typeface.createFromAsset(this.getActivity().getAssets(), "Roboto-Bold.ttf");
		t1.setTypeface(tf1);
	}

	public MagazineFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.eventdesc, container, false);

        return rootView;
    }
	
}
