package com.it4996.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;

public class ContactScreen extends BaseFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.contact, null);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setTitleBar(StaticScreen.CONTACT_SCREEN);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
