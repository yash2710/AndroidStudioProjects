package com.iste.itnu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class PhotosFragment extends Fragment {

	String[] names = { "Tejanshu Salaria", "Dhruv Shah", "Swapnil Prajapati",
			"Chintan Parikh", "Smit Doshi", "Anshu Shah", "Shrishti Bhuvania",
			"Hasika", "Raj Thakkar", "Surbhi Ora", "Mohak Patel",
			"Tanya gupta", "Dhruvil Shah", "Kratika Jain", "Preksha Pattani",
			"Vishal Shah", "Kinjal Shah", "Vivek Kumar", "Piyush Bishnoi",
			"Hardik Panchal", "Darshan Gokalgandhi", "Dinky Patel",
			"Pratik Mantri", "Binal Dutt", "Abha Sanghvi", "Aditi Srivastav",
			"Shubham Kamdar", "Vasu Tayal" };
	String[] post = { "President", "Vice-President", "General Secretary",
			"Joint Secretary", "Joint Secretary", "Managing Director",
			"Managing Director", "Treasurer", "Treasurer",
			"Administrative Head", "Administrative Head", "Cultural Head",
			"Cultural Head", "Editor", "Executive Head", "Executive Head",
			"Executive Head", "Human Resources Head", "Human Resources Head",
			"Logistics Head", "Logistics Head", "Marketing Head",
			"Organising Head", "Organising Head", "PRO", "Socio-Cultural Head",
			"SpokesPerson", "Technical Head", "Technical Head" };
	Integer[] image = { R.drawable.p_ts, R.drawable.vp_ds, R.drawable.gs_sp,
			R.drawable.js_cp, R.drawable.js_sd, R.drawable.md_as,
			R.drawable.md_sb, R.drawable.t_ch, R.drawable.t_rt,
			R.drawable.ah_so, R.drawable.ah_mp, R.drawable.ch_tg,
			R.drawable.ch_ds, R.drawable.e_kj, R.drawable.eh_pp,
			R.drawable.eh_vs, R.drawable.hr_ks, R.drawable.hr_vk,
			R.drawable.lh_hp, R.drawable.lh_hp, R.drawable.lh_hp,
			R.drawable.oh_dp, R.drawable.oh_pm, R.drawable.pro_bd,
			R.drawable.sch_as, R.drawable.s_as,R.drawable.th_sk,R.drawable.th_vt };

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		// get the listview
		expListView = (ExpandableListView) getActivity().findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(getActivity().getApplicationContext(), listDataHeader,listDataChild,childpost,childImg);

		// setting list adapter
		expListView.setAdapter(listAdapter);
	}

	 private void prepareListData() {
	        listDataHeader = new ArrayList<String>();
	        listDataChild = new HashMap<String, List<String>>();
	        childpost = new HashMap<String, List<String>>();
	        childImg = new HashMap<String, List<Integer>>();
	        
	        // Adding child data
	        listDataHeader.add("Current Board");
	        listDataHeader.add("Advisory Board");
	        //listDataHeader.add("Coming Soon..");
	 
	        // Adding child data
	        List<String> top250 = new ArrayList<String>();
	        List<String> top251 = new ArrayList<String>();
	        List<Integer> top252 = new ArrayList<Integer>();
	        /*top250.add("The Shawshank Redemption");
	        top250.add("The Godfather");
	        top250.add("The Godfather: Part II");
	        top250.add("Pulp Fiction");
	        top250.add("The Good, the Bad and the Ugly");
	        top250.add("The Dark Knight");
	        top250.add("12 Angry Men");
	 */
	        for(int i =0;i<names.length;i++)
	        {
	        	top250.add(names[i]);
	        	top251.add(post[i]);
	        	top252.add(image[i]);
	        }
	        List<String> nowShowing = new ArrayList<String>();
	        nowShowing.add("The Conjuring");
	        nowShowing.add("Despicable Me 2");
	        nowShowing.add("Turbo");
	        nowShowing.add("Grown Ups 2");
	        nowShowing.add("Red 2");
	        nowShowing.add("The Wolverine");
	 
	        List<String> comingSoon = new ArrayList<String>();
	        comingSoon.add("2 Guns");
	        comingSoon.add("The Smurfs 2");
	        comingSoon.add("The Spectacular Now");
	        comingSoon.add("The Canyons");
	        comingSoon.add("Europa Report");
	 
	        listDataChild.put(listDataHeader.get(0), top250);
	        listDataChild.put(listDataHeader.get(0), top251);
	        
	        // Header, Child data
	        listDataChild.put(listDataHeader.get(1), nowShowing);
	        //listDataChild.put(listDataHeader.get(2), comingSoon);
	    }

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	HashMap<String, List<String>> childpost;
	HashMap<String, List<Integer>> childImg;

	public PhotosFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// expListView = (ExpandableListView) findViewById(R.id.lvExp);
		View rootView = inflater.inflate(R.layout.fragment_photos, container,
				false);

		return rootView;
	}

}
