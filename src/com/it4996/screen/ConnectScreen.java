package com.it4996.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;

public class ConnectScreen extends BaseFragment {

	private LinearLayout addFriend;
	private LinearLayout requestFriend;
	private LinearLayout hideAddFriend;
	private LinearLayout searchUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.connect, null);
		}

		addFriend = (LinearLayout) containerView
				.findViewById(R.id.connect_add_friend_layout);
		requestFriend = (LinearLayout) containerView
				.findViewById(R.id.connect_request_friend_layout);
		hideAddFriend = (LinearLayout) containerView
				.findViewById(R.id.connect_hide_add_friend_layout);
		searchUser = (LinearLayout) containerView
				.findViewById(R.id.connect_search_user_layout);

		addFriend.setOnClickListener(this);
		requestFriend.setOnClickListener(this);
		hideAddFriend.setOnClickListener(this);
		searchUser.setOnClickListener(this);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setTitleBar(StaticScreen.CONNECT_SCREEN);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
