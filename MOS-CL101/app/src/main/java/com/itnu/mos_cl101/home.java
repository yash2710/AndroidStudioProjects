package com.itnu.mos_cl101;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Yash on 11-Jan-15.
 */
public class home extends Fragment {

    public home(){
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final String[] classes = { "Chapter1", "Chapter2"};
        String[] display = { "Bending Stress in Beams", "SFD and BMD"};

        ListView l;

        l = (ListView) getActivity().findViewById(R.id.chapter_list);
        l.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_expandable_list_item_1, display));
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class c;
                try {
                    c = Class.forName("com.itnu.mos_cl101." + classes[position]);
                    Intent i = new Intent(getActivity(), c);
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.numericals,container,false);
        return rootView;
    }
}
