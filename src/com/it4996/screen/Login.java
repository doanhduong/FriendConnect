package com.it4996.screen;

import android.os.Bundle;

import com.it4996.base.BaseFragment;
import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;

public class Login extends BaseFragment{
	private FriendConnectFragmentActivity baseActivity;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		baseActivity = (FriendConnectFragmentActivity) getActivity();
	
		if(containerView==null){
			containerView = inflater.inflate(R.layout.menu_left, null);
		}
	}

}
