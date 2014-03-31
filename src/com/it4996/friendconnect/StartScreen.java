package com.it4996.friendconnect;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.object.Animation;
import com.it4996.object.Font;
import com.it4996.screen.HomeScreen;

public class StartScreen extends Fragment{
	private FriendConnectFragmentActivity activity;
	   private final long DELAY = 1500;
	    private Timer splashTimer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.start_activity, null);
		
		activity = (FriendConnectFragmentActivity)getActivity();
		final TextView txt = (TextView) v.findViewById(R.id.welc_title);
		txt.setTypeface(new Font(activity).getFont());

		final ImageView logo = (ImageView) v.findViewById(R.id.welc_logo);
		final ImageView ray = (ImageView) v.findViewById(R.id.ray);
		
		Animation animation = new Animation(activity);
		ray.startAnimation(animation.rotate());
		txt.startAnimation(animation.fadeOutAnimation());
		
		if(activity!=null){
			
		} else {
			
		    splashTimer = new Timer();
		    splashTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				
			}
		    }, DELAY);
		    
		    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeScreen()).commit();
		    activity.getTitleBar().setVisibility(View.INVISIBLE);
		}
		return v;
	}


}
