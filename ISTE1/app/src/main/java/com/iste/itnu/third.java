package com.iste.itnu;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class third extends ArrayAdapter<String>{
private final Activity context;
private final  String[] names;
private final Integer[] imageId;

public third(Activity context2, String[] names2, Integer[] imageId2) {
	// TODO Auto-generated constructor stub
	super(context2, R.layout.third, names2);
	this.context = context2;
	this.names = names2;
	this.imageId = imageId2;
}
@Override
public View getView(int position, View view, ViewGroup parent) {
LayoutInflater inflater = context.getLayoutInflater();
View rowView= inflater.inflate(R.layout.third, null, true);
TextView txtTitle = (TextView) rowView.findViewById(R.id.tv1);
ImageView imageView = (ImageView) rowView.findViewById(R.id.iv1);
Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(), "Roboto-Regular.ttf");
txtTitle.setText(names[position]);
txtTitle.setTypeface(tf);
imageView.setImageResource(imageId[position]);
return rowView;
}
}