package com.iste.itnu;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class second extends ArrayAdapter<String>{
private final Activity context;
private final  String[] names;
private final String[] post;
private final Integer[] imageId;

public second(Activity context2, String[] names2, String[] post2,
		Integer[] imageId2) {
	// TODO Auto-generated constructor stub
	super(context2, R.layout.second, names2);
	this.context = context2;
	this.names = names2;
	this.post=post2;
	this.imageId = imageId2;
}
@Override
public View getView(int position, View view, ViewGroup parent) {
LayoutInflater inflater = context.getLayoutInflater();
View rowView= inflater.inflate(R.layout.second, null, true);
TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
TextView txtTitle1 = (TextView) rowView.findViewById(R.id.txt1);
ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(), "Roboto-Bold.ttf");
txtTitle.setText(names[position]);
txtTitle.setTypeface(tf);
Typeface tg = Typeface.createFromAsset(this.getContext().getAssets(), "Roboto-Light.ttf");
txtTitle1.setText(post[position]);
txtTitle.setTypeface(tg);
imageView.setImageResource(imageId[position]);
return rowView;
}
}