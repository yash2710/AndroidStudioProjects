package com.itnu.mos_cl101;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Chapter1 extends ActionBarActivity implements OnClickListener {

	TextView question;
	ImageView img;
	Random r = new Random();
	Integer i;
	int cnt = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chapter1);

		img = (ImageView) findViewById(R.id.iv);

		question = (TextView) findViewById(R.id.tv_question);
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

		i = 0;

		switch (i) {
		case 0:
			a = r.nextInt(5) + 1;
			question.setText("Calculate maximum bending stress across the cross section for the beam subjected to"
					+ " loading as shown in figure. Also calculate bending stress at a section "
					+ a + "m from left hand support.");
//			img.setImageResource(R.drawable.bs_q2);
			img.setVisibility(View.GONE);
			break;
		case 1:
			a = r.nextInt(5) + 1;
			b = r.nextInt(4) + 1;
			c = r.nextInt(150) + 10;
			d = r.nextInt(700) + 40;
			question.setText("Draw bending stress distribution diagram at the section of maximum bending moment across the section\n"
					+ "a = "
					+ a
					+ "m, b = "
					+ b
					+ "m, c = "
					+ c
					+ "kN, d = "
					+ d + "mm");
			img.setImageResource(R.drawable.bs_q2);
			img.setVisibility(View.VISIBLE);
			break;
		case 2:
			a = r.nextInt(5) + 1;
			b = r.nextInt(5) + 1;
			c = r.nextInt(90) + 10;
			d = r.nextInt(100) + 10;
			e = r.nextInt(300) + 100;
			f = r.nextInt(80) + 20;
			g = r.nextInt(170) + 80;
			h = r.nextInt(80) + 20;
			j = r.nextInt(80) + 20;
			k = r.nextInt(20) + 10;
			l = r.nextInt(20) + 10;
			question.setText("Draw bending stress distribution diagram at the point of maximum bending moment across the cross section \n"
					+ "For force diagram, a = "
					+ a
					+ "m, b = "
					+ b
					+ "m, c = "
					+ c
					+ "kN, d ="
					+ d
					+ "kN\n"
					+ "For cross section, a = "
					+ e
					+ "mm, b = "
					+ f
					+ "mm, c = "
					+ g
					+ "mm, d ="
					+ h
					+ "mm, e = "
					+ j
					+ "mm\n"
					+ "For diagram, a = "
					+ (k + r.nextInt(100)/100)
					+ "b = "
					+ (l + r.nextInt(100)/100));
			img.setImageResource(R.drawable.bs_q3);
			img.setVisibility(View.VISIBLE);
			break;
		case 3:
			a = r.nextInt(50) + 10;
			b = r.nextInt(200) + 150;
			c = r.nextInt(50) + 10;
			d = r.nextInt(150) + 60;
			e = r.nextInt(70) + 30;
			question.setText("A beam of T shaped cross section shown in figure is subjected to bending moment about X-X axis due to moment of "
					+ a
					+ "kNm. Find the bending stress of the beam.\n"
					+ "For cross section, a = "
					+ b
					+ "mm, b = "
					+ c
					+ "mm, c = " + d + "mm, d = " + e + "mm");
			img.setImageResource(R.drawable.bs_q4);
			img.setVisibility(View.VISIBLE);
			break;

		case 4:
			a = r.nextInt(120) + 10;
			b = r.nextInt(7) + 1;
			c = r.nextInt(7) + 1;
			d = r.nextInt(7) + 1;
			e = r.nextInt(70) + 10;
			f = r.nextInt(70) + 20;
			g = r.nextInt(170) + 80;
			h = r.nextInt(30) + 5;
			j = r.nextInt(150) + 20;
			k = r.nextInt(30) + 2;
			l = r.nextInt(180) + 80;
			question.setText("A beam having I shaped cross section is subjected to Bending Moment about X-X axis due to moment of "
					+ a
					+ "kN\n"
					+ "For force diagram, a = "
					+ b
					+ "m, b = "
					+ c
					+ "m, c = "
					+ d
					+ "m, d = "
					+ e
					+ "kN, e = "
					+ f
					+ "kN\n"
					+ "For cross section, a = "
					+ g
					+ "mm, b = "
					+ h
					+ "mm, c = " + j + "mm, d = " + k + "mm, e = " + l + "mm");
			img.setImageResource(R.drawable.bs_q5);
			img.setVisibility(View.VISIBLE);
			break;

		case 5:
			a = r.nextInt(5) + 1;
			b = r.nextInt(7) + 1;
			c = r.nextInt(7) + 1;
			d = r.nextInt(50) + 10;
			e = r.nextInt(300) + 100;
			f = r.nextInt(70) + 20;
			g = r.nextInt(170) + 80;
			h = r.nextInt(30) + 5;
			question.setText("Draw the Bending Stress distribution diagram at the point of maximum Bending moment across the cross section.\n"
					+ "For force diagram, a ="
					+ a
					+ "m, b = "
					+ b
					+ "m, c = "
					+ c
					+ "m, d = "
					+ d
					+ "kN, \nFor cross section, a = "
					+ e
					+ "mm, b = "
					+ f
					+ "mm, c = " + g + "mm, d = " + h + "mm");
			img.setImageResource(R.drawable.bs_q6);
			img.setVisibility(View.VISIBLE);
			break;
        default:
            break;

		}

		cnt++;
        i++;

	}

}
