package com.it4996.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.it4996.common.FragmentController;
import com.it4996.common.ImageLoaderInit;
import com.it4996.common.MySlidingMenu;
import com.it4996.friendconnect.R;
import com.it4996.object.TitleBar;
import com.it4996.screen.StartScreen;

public class FriendConnectFragmentActivity extends FragmentActivity {

	private TitleBar titleBar;
	private MySlidingMenu menu;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);

		// Khoi tao ImageLoader
		new ImageLoaderInit(this).init();

		FragmentController.replaceDontAddToBackStack(this, new StartScreen());

		titleBar = (TitleBar) findViewById(R.id.titleBar);
		menu = new MySlidingMenu(this);

	}

	public MySlidingMenu getSlidingMenu() {
		return menu;
	}

	public TitleBar getTitleBar() {
		return titleBar;
	}

}
