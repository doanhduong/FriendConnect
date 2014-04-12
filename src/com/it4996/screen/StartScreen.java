package com.it4996.screen;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.it4996.base.BaseFragment;
import com.it4996.common.FragmentController;
import com.it4996.friendconnect.R;
import com.it4996.object.Animation;
import com.it4996.object.Font;

public class StartScreen extends BaseFragment {
	private final long DELAY = 10500;
	private Timer splashTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(containerView == null){
			containerView = inflater.inflate(R.layout.start_activity, null);
		}
		
		setTitleBar(StaticScreen.START_SCREEN);
		//fcFrgAtv.getSlidingMenu().setSlidingEnabled(false);
		
		final TextView txt = (TextView) containerView.findViewById(R.id.welc_title);
		txt.setTypeface(new Font(fcFrgAtv).getFont());

		final ImageView logo = (ImageView) containerView.findViewById(R.id.welc_logo);
		final ImageView ray = (ImageView) containerView.findViewById(R.id.ray);

		Animation animation = new Animation(fcFrgAtv);
		ray.startAnimation(animation.rotate());
		txt.startAnimation(animation.fadeOutAnimation());

		splashTimer = new Timer();
		splashTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				
			}
		}, DELAY);
		logo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					FragmentController.replaceDontAddToBackStack(fcFrgAtv, new Login());	
			}
		});
	}
}
