package com.iste.itnu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends Activity
{
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	ImageView view;
	AnimationDrawable frameAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		view = (ImageView) findViewById(R.id.imageAnimation);

		// Setting animation_list.xml as the background of the image view
		view.setBackgroundResource(R.drawable.animation_list);

		// Typecasting the Animation Drawable
		frameAnimation = (AnimationDrawable) view.getBackground();
		Thread timer = new Thread(){
			public void run() {
				try{
					sleep(1400);
				} catch(InterruptedException e){
					e.printStackTrace();
				} finally{
					Intent opennext = new Intent(Splash.this, MainActivity.class);
					startActivity(opennext);
				}
				
			}
		};
		timer.start();
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
	    super.onWindowFocusChanged(hasFocus);
	      if (hasFocus) {
		// Starting the animation when in Focus
		frameAnimation.start();
		} else {
	        // Stoping the animation when not in Focus
		frameAnimation.stop();
	      }
	}
	
}