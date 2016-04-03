package com.iste.itnu;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SponserFragment extends Fragment implements OnClickListener{

	ImageView a1,a2,a3,a4,a5,a6,a7,a8,a9,a0;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		a1 = (ImageView)getActivity().findViewById(R.id.imageView1);
		a2 = (ImageView)getActivity().findViewById(R.id.imageView2);
		a3 = (ImageView)getActivity().findViewById(R.id.imageView3);
		a4 = (ImageView)getActivity().findViewById(R.id.imageView4);
		a5 = (ImageView)getActivity().findViewById(R.id.imageView5);
		a6 = (ImageView)getActivity().findViewById(R.id.imageView6);
		a7 = (ImageView)getActivity().findViewById(R.id.imageView7);
		a8 = (ImageView)getActivity().findViewById(R.id.imageView8);
		a9 = (ImageView)getActivity().findViewById(R.id.imageView9);
		a0 = (ImageView)getActivity().findViewById(R.id.imageView0);
		a1.setOnClickListener(this);
		a2.setOnClickListener(this);
		a3.setOnClickListener(this);
		a4.setOnClickListener(this);
		a5.setOnClickListener(this);
		a6.setOnClickListener(this);
		a7.setOnClickListener(this);
		a8.setOnClickListener(this);
		a9.setOnClickListener(this);
		a0.setOnClickListener(this);
		
	}

	public SponserFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.sponsers, container, false);

		return rootView;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Uri u;
		Intent i;
		switch (arg0.getId()) {
		case R.id.imageView1:
			u = Uri.parse("http://www.carrerline.org");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView2:
			u = Uri.parse("http://www.gujaratnre.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView3:
			u = Uri.parse("http://www.infibeam.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView4:
			u = Uri.parse("http://www.jamboree.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView5:
			/*u = Uri.parse("www.carrerline.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);*/
			break;
		case R.id.imageView6:
			u = Uri.parse("http://www.kyrion.in");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView7:
			u = Uri.parse("http://www.ongcindia.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView8:
			u = Uri.parse("http://www.techdefence.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView9:
			u = Uri.parse("http://www.torrentpower.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
		case R.id.imageView0:
			u = Uri.parse("http://www.vcceducation.com");
			i = new Intent(Intent.ACTION_VIEW,u);
			startActivity(i);
			break;
			

		default:
			break;
		}
	}
	
}
