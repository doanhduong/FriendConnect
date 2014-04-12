package com.it4996.common;

import java.util.ArrayList;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it4996.adapter.ButtonOfMenuLeftAdapter;
import com.it4996.base.BaseFragment;
import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;
import com.it4996.object.MenuLeft;
import com.it4996.screen.ConnectScreen;
import com.it4996.screen.ContactScreen;
import com.it4996.screen.HomeScreen;
import com.it4996.screen.MessageScreen;
import com.it4996.screen.NotificationScreen;
import com.it4996.screen.SettingScreen;
import com.it4996.screen.StaticScreen;
import com.it4996.screen.UserScreen;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MySlidingMenu implements OnClickListener {
	private SlidingMenu menu;
	private FriendConnectFragmentActivity fcFrgAtv;
	private RelativeLayout menu_left_userInfo;
	private ImageView menu_left_avatar;
	private TextView menu_left_username;
	private ListView menu_left_listBtn;
	private ButtonOfMenuLeftAdapter btnMenuLeftAdapter;

	public MySlidingMenu(FriendConnectFragmentActivity fcFrgAtv) {
		this.fcFrgAtv = fcFrgAtv;
		init();
	}

	public void init() {
		menu = new SlidingMenu(fcFrgAtv);
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidth(30);
		menu.setShadowDrawable(R.drawable.shadow);
		menu.setBehindOffset(100);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(fcFrgAtv, SlidingMenu.SLIDING_CONTENT);
		menu.setMenu(R.layout.menu_left);

		menu_left_userInfo = (RelativeLayout) fcFrgAtv
				.findViewById(R.id.menu_layout_userinfo);
		menu_left_avatar = (ImageView) fcFrgAtv
				.findViewById(R.id.menu_left_avatar);
		menu_left_username = (TextView) fcFrgAtv
				.findViewById(R.id.menu_left_username);
		menu_left_listBtn = (ListView) fcFrgAtv
				.findViewById(R.id.menu_left_listbtn);

		btnMenuLeftAdapter = new ButtonOfMenuLeftAdapter(fcFrgAtv,
				setSlidingMenu());
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
	}

	public ArrayList<MenuLeft> setSlidingMenu() {
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

		return arrMenuLeft;
	}

	// Set background of Selected button
	private void setBackgroundButtonMenu(boolean isBtnUser, int position) {
		if (isBtnUser) {
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

	// Display Screen
	private void displayView(int position) {

		BaseFragment fragment = null;

		switch (position) {
		case StaticScreen.HOME_SCREEN:
			fragment = new HomeScreen();
			break;
		case StaticScreen.MESSAGE_SCREEN:
			fragment = new MessageScreen();
			break;
		case StaticScreen.CONTACT_SCREEN:
			fragment = new ContactScreen();
			break;
		case StaticScreen.CONNECT_SCREEN:
			fragment = new ConnectScreen();
			break;
		case StaticScreen.NOTIFICATION_SCREEN:
			fragment = new NotificationScreen();
			break;
		case StaticScreen.SETTING_SCREEN:
			fragment = new SettingScreen();
			break;

		default:
			break;
		}

		if (fragment != null) {
			// FragmentManager fragmentManager = fcFrgAtv
			// .getSupportFragmentManager();
			// // Chuyen fragment
			// fragmentManager.beginTransaction()
			// .replace(R.id.fragment_container, fragment).commit();

			FragmentController.replaceWithAddToBackStack(fcFrgAtv, fragment,
					fragment.toString());
			setBackgroundButtonMenu(false, position);
		} else {
			Log.e(FriendConnectFragmentActivity.class.toString(),
					"Error in creating fragment");
		}
	}

	@Override
	public void onClick(View v) {
		if (v == menu_left_userInfo) {
			menu.toggle();
			fcFrgAtv.getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, new UserScreen())
					.commit();

			setBackgroundButtonMenu(true, -1);
			fcFrgAtv.getTitleBar().setTitleBar("TRANG CÁ NHÂN", true, true,
					false, false);
		}
	}

	public void setUserNameAndAvatarOfCurrentUser(String username, String avatar) {
		menu_left_username.setText(username);
		if (!avatar.equals(""))
			ImageLoader.getInstance().displayImage(avatar, menu_left_avatar);
		else {
			menu_left_avatar.setImageResource(R.drawable.ic_launcher);
		}
	}

	public void setSlidingEnabled(boolean enable) {
		menu.setSlidingEnabled(enable);
	}

	public void toggle() {
		menu.toggle();
	}
}
