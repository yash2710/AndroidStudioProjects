package com.itnu.mos_cl101;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Chapter2 extends ActionBarActivity implements OnClickListener {

	TextView question, dims;
	ImageView img;
	Random r = new Random();
	Integer i;
	int cnt = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chapter2);

		question = (TextView) findViewById(R.id.tv_question);
		dims = (TextView) findViewById(R.id.tv_dims);
		img = (ImageView) findViewById(R.id.iv);
		Button nxtq = (Button) findViewById(R.id.bt_nxt);

		que();

		nxtq.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		que();
	}

	void que() {

		Integer a, b, c, d, e, f, g, h, j, k, l;
		TextView qno = (TextView) findViewById(R.id.tv_qno);

		qno.setText("Question - " + cnt);

		i = r.nextInt(4);
		question.setText("Draw the SFD and BMD for the system shown below");
		switch (i) {
		case 0:
			a = r.nextInt(5) + 1;
			b = r.nextInt(5) + 1;
			c = r.nextInt(50) + 5;
			d = r.nextInt(50) + 5;
			dims.setText("a = " + a + "m, b = " + b + "m, c = " + c
					+ "kN, d = " + d + "kN");
			img.setImageResource(R.drawable.sfd_bmd_q1);
			break;
		case 1:
			a = r.nextInt(5) + 1;
			b = r.nextInt(5) + 1;
			c = r.nextInt(5) + 1;
			d = r.nextInt(50) + 5;
			e = r.nextInt(50) + 5;
			dims.setText("a = " + a + "m, b = " + b + "m, c = " + c + "m, d = "
					+ d + "kN/m, e = " + e + "kN");
			img.setImageResource(R.drawable.sfd_bmd_q2);
			break;
		case 2:

		case 3:
			a = r.nextInt(5);
			b = r.nextInt(5) + 1;
			c = r.nextInt(5) + 1;
			d = r.nextInt(5);
			e = r.nextInt(60) - 30;
			f = r.nextInt(50) - 25;
			g = r.nextInt(10) + 1;
			dims.setText("a = " + a + "m, b = " + b + "m, c = " + c + "m, d = "
					+ d + "m, e = " + e + "kN, f = " + f + "kN, UDL = " + g
					+ "kN/m");
			img.setImageResource(R.drawable.sfd_bmd_q3);
			break;
		case 4:
			a = r.nextInt(5);
			b = r.nextInt(5) + 1;
			c = r.nextInt(5) + 1;
			d = r.nextInt(5);
			e = r.nextInt(60) +10;
			f = r.nextInt(50) + 15;
			g = r.nextInt(60) + 10;
			dims.setText("a = " + a + "m, b = " + b + "m, c = " + c + "m, d = "
					+ d + "m, e = " + e + "kN, f = " + f + "kN, g = " + g
					+ "kN");
			img.setImageResource(R.drawable.sfd_bmd_q5);
			break;
		case 5:
			dims.setText("");
			break;
		}

		cnt++;

	}

}
