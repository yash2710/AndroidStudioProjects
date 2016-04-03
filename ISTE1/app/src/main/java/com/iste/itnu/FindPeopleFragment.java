package com.iste.itnu;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FindPeopleFragment extends Fragment {


	ListView list;
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
	Integer[] imageId = { R.drawable.p_ts, R.drawable.vp_ds, R.drawable.gs_sp,
			R.drawable.js_cp, R.drawable.js_sd, R.drawable.md_as,
			R.drawable.md_sb, R.drawable.t_ch, R.drawable.t_rt,
			R.drawable.ah_so, R.drawable.ah_mp, R.drawable.ch_tg,
			R.drawable.ch_ds, R.drawable.e_kj, R.drawable.eh_pp,
			R.drawable.eh_vs, R.drawable.hr_ks, R.drawable.hr_vk,
			R.drawable.lh_hp, R.drawable.lh_hp, R.drawable.lh_hp,
			R.drawable.oh_dp, R.drawable.oh_pm, R.drawable.pro_bd,
			R.drawable.sch_as, R.drawable.s_as,R.drawable.th_sk,R.drawable.th_vt };

	public FindPeopleFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
         
        return rootView;
    }
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		second adapter = new second(getActivity(), names, post, imageId);
		list = (ListView)getActivity().findViewById(R.id.list);
		list.setAdapter(adapter);
	}
}
