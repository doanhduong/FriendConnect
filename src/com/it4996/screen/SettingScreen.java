package com.it4996.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it4996.base.BaseFragment;
import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;
import com.it4996.network.UserApi;

public class SettingScreen extends BaseFragment {

	private TextView tvEditProfile, tvChangePassword, tvNotification, tvLogout,
			tvAbout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.setting, null);
		}

		tvEditProfile = (TextView) containerView
				.findViewById(R.id.setting_edit_profile_tv);
		tvChangePassword = (TextView) containerView
				.findViewById(R.id.setting_change_password_tv);
		tvNotification = (TextView) containerView
				.findViewById(R.id.setting_notification_settup_tv);
		tvLogout = (TextView) containerView
				.findViewById(R.id.setting_logout_tv);
		tvAbout = (TextView) containerView
				.findViewById(R.id.setting_about_app_tv);

		tvEditProfile.setOnClickListener(this);
		tvChangePassword.setOnClickListener(this);
		tvNotification.setOnClickListener(this);
		tvLogout.setOnClickListener(this);
		tvAbout.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setTitleBar(StaticScreen.SETTING_SCREEN);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == tvLogout) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					UserApi api = new UserApi(fcFrgAtv);
					api.logout();
					fcFrgAtv.finish();
				}
			}).start();
		}
	}
}
