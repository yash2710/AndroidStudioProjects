package com.iste.itnu;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ContactFragment extends Fragment implements OnClickListener{
	
	TextView query,title;
	EditText msg;
	Button send;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		title = (TextView)getActivity().findViewById(R.id.textView1);
		query = (TextView)getActivity().findViewById(R.id.textView4);
		
		msg = (EditText)getActivity().findViewById(R.id.editText3);
		
		send = (Button)getActivity().findViewById(R.id.button1);
		Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(), "Roboto-Regular.ttf");
		Typeface tg = Typeface.createFromAsset(this.getActivity().getAssets(), "Roboto-Bold.ttf");
		title.setTypeface(tg);
		query.setTypeface(tf);
		msg.setTypeface(tf);
		send.setTypeface(tg);		
		send.setOnClickListener(this);
	}

	public ContactFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_contactus, container, false);
         
        return rootView;
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String mes = msg.getText().toString();

		 Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
		    emailIntent.setData(Uri.parse("mailto:" + "11bit001@nirmauni.ac.in")); 
		    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "[ISTEApp]Query/FeedBack");
		    emailIntent.putExtra(Intent.EXTRA_TEXT, mes);
		    try {
		        startActivity(Intent.createChooser(emailIntent, "Send email using..."));
		    } catch (android.content.ActivityNotFoundException ex) {
		        Toast.makeText(getActivity().getApplicationContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
		    }
	}
	
	
}
