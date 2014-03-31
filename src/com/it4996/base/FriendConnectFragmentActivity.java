package com.it4996.base;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it4996.adapter.ButtonOfMenuLeftAdapter;
import com.it4996.friendconnect.R;
import com.it4996.friendconnect.StartScreen;
import com.it4996.object.MenuLeft;
import com.it4996.object.TitleBar;
import com.it4996.screen.ConnectScreen;
import com.it4996.screen.ContactScreen;
import com.it4996.screen.HomeScreen;
import com.it4996.screen.MessageScreen;
import com.it4996.screen.NotificationScreen;
import com.it4996.screen.SettingScreen;
import com.it4996.screen.User;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class FriendConnectFragmentActivity extends FragmentActivity implements
		OnClickListener, OnTouchListener {

	private TitleBar titleBar;
	private SlidingMenu menu;
	public RelativeLayout menu_left_userInfo;
	private ImageView menu_left_avatar;
	private TextView menu_left_username;
	private ListView menu_left_listBtn;
	private ButtonOfMenuLeftAdapter btnMenuLeftAdapter;

	private boolean isFirstCheckMenu = false;

	public final int BTN_HOME = 0;
	public final int BTN_MESSAGE = 1;
	public final int BTN_CONTACT = 2;
	public final int BTN_CONNECT = 3;
	public final int BTN_NOTIFICATION = 4;
	public final int BTN_SETTING = 5;
	public final int BTN_USER = 6;

	public int isCurrentScr = BTN_HOME;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.main);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, new StartScreen()).commit();
		setCustomTitleBar();
		setSlidingMenu();
	}

	public void setCustomTitleBar() {
		titleBar = (TitleBar) findViewById(R.id.titleBar1);
		setTextTitleBar("TRANG CHỦ");
		
		titleBar.getTitle_bar_menu_left().setOnClickListener(this);
	}

	public void setSlidingMenu() {
		// init SlidingMenu
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidth(30);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffset(100);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu_left);

		MenuLeft menuHome = new MenuLeft(R.drawable.ic_home, "Trang Chủ", "20",
				false);
		MenuLeft menuMessage = new MenuLeft(R.drawable.ic_message, "Tin Nhắn",
				"20", false);
		MenuLeft menuContact = new MenuLeft(R.drawable.ic_contact, "Danh Bạ",
				"20", false);
		MenuLeft menuConnect = new MenuLeft(R.drawable.ic_connect, "Kết nối",
				"20", false);
		MenuLeft menuNotification = new MenuLeft(R.drawable.ic_notification,
				"Thông báo", "20", true);
		MenuLeft menuSetting = new MenuLeft(R.drawable.ic_setting, "Cài Đặt",
				"20", false);

		ArrayList<MenuLeft> arrMenuLeft = new ArrayList<MenuLeft>();
		arrMenuLeft.add(menuHome);
		arrMenuLeft.add(menuMessage);
		arrMenuLeft.add(menuContact);
		arrMenuLeft.add(menuConnect);
		arrMenuLeft.add(menuNotification);
		arrMenuLeft.add(menuSetting);

		menu_left_userInfo = (RelativeLayout) findViewById(R.id.menu_layout_userinfo);
		menu_left_avatar = (ImageView) findViewById(R.id.menu_left_avatar);
		menu_left_username = (TextView) findViewById(R.id.menu_left_username);
		menu_left_listBtn = (ListView) findViewById(R.id.menu_left_listbtn);

		btnMenuLeftAdapter = new ButtonOfMenuLeftAdapter(this, arrMenuLeft);
		menu_left_listBtn.setAdapter(btnMenuLeftAdapter);

		// Set event click menu
		menu_left_userInfo.setOnClickListener(this);

		menu_left_listBtn.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				menu.toggle();
				displayView(position);
			}
		});

		// Set background button menu when start the first
		// HomeScreen
		titleBar.setOnTouchListener(this);
		findViewById(R.id.fragment_container).setOnTouchListener(this);
		titleBar.getTitle_bar_menu_left().setOnTouchListener(this);
	}

	// Set background of Selected button
	private void setBackgroundButtonMenu(boolean btnUser, int position) {
		if (btnUser) {
			menu_left_userInfo.setActivated(true);
			for (int i = 0; i < menu_left_listBtn.getCount(); i++) {
				View item = menu_left_listBtn.getChildAt(i);
				item.setActivated(false);
			}
		} else {
			menu_left_userInfo.setActivated(false);
			for (int i = 0; i < menu_left_listBtn.getCount(); i++) {
				View v = menu_left_listBtn.getChildAt(i);
				if (position == i) {
					v.setActivated(true);
				} else {
					v.setActivated(false);
				}
			}
		}
	}

	public void setTextTitleBar(String text) {
		titleBar.getTitle_bar_tv().setText(text);
	}


	// Display Screen
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new HomeScreen();
			setTextTitleBar("TRANG CHỦ");
			break;
		case 1:
			fragment = new MessageScreen();
			setTextTitleBar("TIN NHẮN");
			break;
		case 2:
			fragment = new ContactScreen();
			setTextTitleBar("DANH BẠ");
			break;
		case 3:
			fragment = new ConnectScreen();
			setTextTitleBar("KẾT NỐI");
			break;
		case 4:
			fragment = new NotificationScreen();
			setTextTitleBar("THÔNG BÁO");
			break;
		case 5:
			fragment = new SettingScreen();
			setTextTitleBar("CÀI ĐẶT");
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			// Chuyen fragment
			fragmentManager.beginTransaction()
					.replace(R.id.fragment_container, fragment).commit();

			setBackgroundButtonMenu(false, position);
		} else {
			// error in creating fragment
			Log.e("FriendConnect", "Error in creating fragment");
		}
	}

	public TitleBar getTitleBar() {
		return titleBar;
	}

	public SlidingMenu getMenu() {
		return menu;
	}

	@Override
	public void onClick(View v) {
		// if (v == titleBar.getTitle_bar_menu_left()) {
		// menu.toggle();
		// }

		menu.toggle();
		if (v == menu_left_userInfo) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, new User()).commit();

			setBackgroundButtonMenu(true, -1);
			setTextTitleBar("TRANG CÁ NHÂN");
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (v == titleBar || v.getId() == R.id.fragment_container
				|| v == titleBar.getTitle_bar_menu_left()) {
			if (!isFirstCheckMenu) {
				displayView(BTN_HOME);
				isFirstCheckMenu = true;
			}
		}
		return false;
	}
}
