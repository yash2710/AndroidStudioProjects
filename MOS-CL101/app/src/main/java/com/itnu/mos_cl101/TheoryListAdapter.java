package com.itnu.mos_cl101;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Yash on 11-Jan-15.
 */
public class TheoryListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] Title;
    private final String[] theory;

    public TheoryListAdapter(Activity context2, String[] names2, String[] post2) {
        // TODO Auto-generated constructor stub
        super(context2, R.layout.theory_list, names2);
        this.context = context2;
        this.Title = names2;
        this.theory = post2;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.theory_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.theory_list_title);
        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.theory_content);

//        Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(), "Roboto-Bold.ttf");
        txtTitle.setText(Title[position]);
//        txtTitle.setTypeface(tf);
//        Typeface tg = Typeface.createFromAsset(this.getContext().getAssets(), "Roboto-Light.ttf");
        txtTitle1.setText(theory[position]);
//        txtTitle.setTypeface(tg);
        return rowView;
    }

}
