package com.itnu.mos_cl101;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Yash on 11-Jan-15.
 */
public class theory extends Fragment {

    public theory() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView l = (ListView) getActivity().findViewById(R.id.list);
        TheoryListAdapter th = new TheoryListAdapter(getActivity(),getResources().getStringArray(R.array.theory_ch1_titles),
                getResources().getStringArray(R.array.theory_ch1));
        l.setAdapter(th);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.i("Fragment","inside");
        View rootView = inflater.inflate(R.layout.theory, container, false);
        return rootView;
    }
}
