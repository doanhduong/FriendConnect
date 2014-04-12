package com.it4996.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.it4996.common.JsonParser;
import com.it4996.object.TitleBar;
import com.it4996.screen.StaticScreen;

public class BaseFragment extends Fragment implements OnClickListener {

	/**
	 * Su dung cho truong hop onBackPress.
	 * <p>
	 * Khi goi ham onBackPresh, theo vong doi cua fragment thi no se goi lai
	 * onCreateView. Vi the de khong phai khoi tao lai view nay, ta su dung
	 * containerView
	 * <p>
	 * if (containerView == null) { create new } else { return containerView }
	 */
	protected View containerView;
	protected LayoutInflater inflater;
	protected JsonParser jParser;
	protected FriendConnectFragmentActivity fcFrgAtv;
	protected ImageView menuLeft, menuRight;
	protected int curScreen = StaticScreen.HOME_SCREEN;
	protected TitleBar titleBar;

	public int getCurScreen() {
		return curScreen;
	}

	public void setCurScreen(int curScreen) {
		this.curScreen = curScreen;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = (LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
		fcFrgAtv = (FriendConnectFragmentActivity) getActivity();
		jParser = new JsonParser(fcFrgAtv);
		menuLeft = fcFrgAtv.getTitleBar().getTitle_bar_menu_left();
		menuRight = fcFrgAtv.getTitleBar().getTitle_bar_menu_right();
		titleBar = fcFrgAtv.getTitleBar();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (curScreen == StaticScreen.HOME_SCREEN
				|| curScreen == StaticScreen.MESSAGE_SCREEN
				|| curScreen == StaticScreen.CONTACT_SCREEN
				|| curScreen == StaticScreen.CONNECT_SCREEN
				|| curScreen == StaticScreen.NOTIFICATION_SCREEN
				|| curScreen == StaticScreen.SETTING_SCREEN
				|| curScreen == StaticScreen.USER_SCREEN) {
			fcFrgAtv.getSlidingMenu().setSlidingEnabled(true);
		}else{
			fcFrgAtv.getSlidingMenu().setSlidingEnabled(false);
		}

		menuLeft.setOnClickListener(this);
		menuRight.setOnClickListener(this);
		return containerView;
	}

	/**
	 * Phai co onDestroyView de ko bi loi khi replace
	 * <p>
	 * <b> Error: </b> The specified child already has a parent. You must call
	 * removeView() on the child's parent first
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (containerView != null) {
			try {
				ViewGroup parentViewGroup = (ViewGroup) containerView
						.getParent();
				if (null != parentViewGroup) {
					parentViewGroup.removeAllViews();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v == menuLeft) {
			if (curScreen == StaticScreen.HOME_SCREEN
					|| curScreen == StaticScreen.MESSAGE_SCREEN
					|| curScreen == StaticScreen.CONTACT_SCREEN
					|| curScreen == StaticScreen.CONNECT_SCREEN
					|| curScreen == StaticScreen.NOTIFICATION_SCREEN
					|| curScreen == StaticScreen.SETTING_SCREEN
					|| curScreen == StaticScreen.USER_SCREEN) {
				fcFrgAtv.getSlidingMenu().toggle();
			} else {
				fcFrgAtv.getSupportFragmentManager().popBackStack();
			}
		}

		if (v == menuRight) {
			if (curScreen == StaticScreen.HOME_SCREEN
					|| curScreen == StaticScreen.MESSAGE_SCREEN
					|| curScreen == StaticScreen.CONTACT_SCREEN
					|| curScreen == StaticScreen.CONNECT_SCREEN
					|| curScreen == StaticScreen.NOTIFICATION_SCREEN
					|| curScreen == StaticScreen.SETTING_SCREEN) {
				// fcFrgAtv.getSlidingMenu().toggle();
			} else {
				// fcFrgAtv.getSupportFragmentManager().popBackStack();
				Toast.makeText(fcFrgAtv, "menuright", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
	
public void setTitleBar(int nameScreen) {
		setCurScreen(nameScreen);
		switch (nameScreen) {
		case StaticScreen.HOME_SCREEN:
			titleBar.setTitleBar("TRANG CHỦ", true, false, false, false);			
			break;
		case StaticScreen.USER_SCREEN:
			titleBar.setTitleBar("TRANG CÁ NHÂN", true, true, false, false);
			break;
		case StaticScreen.MESSAGE_SCREEN:
			titleBar.setTitleBar("TIN NHẮN", true, false, false, false);
			break;
		case StaticScreen.CONTACT_SCREEN:
			titleBar.setTitleBar("DANH BẠ", true, false, false, false);
			break;
		case StaticScreen.CONNECT_SCREEN:
			titleBar.setTitleBar("KẾT NỐI", true, false, false, false);
			break;
		case StaticScreen.NOTIFICATION_SCREEN:
			titleBar.setTitleBar("THÔNG BÁO", true, false, false, false);
			break;
		case StaticScreen.SETTING_SCREEN:
			titleBar.setTitleBar("CÀI ĐẶT", true, false, false, false);
			break;
		case StaticScreen.ARTICLE_FULL_INFO_SCREEN:
			titleBar.setTitleBar("CHI TIẾT", false, false, true, false);
			break;
		case StaticScreen.LOGIN_SCREEN:
			titleBar.setTitleBar("ĐĂNG NHẬP", false, false, false, false);
			break;
		case StaticScreen.REGISTER_SCREEN:
			titleBar.setTitleBar("ĐĂNG KÝ TÀI KHOẢN", false, false, false,
					false);
			break;
		case StaticScreen.FORGET_PASSWORD_SCREEN:
			titleBar.setTitleBar("QUÊN MẬT KHẨU", false, false, false, false);
			break;
		case StaticScreen.NEW_ARTICLE_SCREEN:
			titleBar.setTitleBar("ĐĂNG BÀI VIẾT", false, false, true, true);
			break;
		case StaticScreen.NEW_STATUS_SCREEN:
			titleBar.setTitleBar("CHIA SẺ CẢM NGHĨ", false, false, true, true);
			break;
		case StaticScreen.START_SCREEN:
			titleBar.hintTitleBar();
			break;
		case StaticScreen.UPDATE_AVATAR_COVER_SCREEN:
			titleBar.setTitleBar("CẬP NHẬT ẢNH", false, false, true, true);
			break;
		case StaticScreen.UPDATE_PROFILE:
			titleBar.setTitleBar("CHỈNH SỬA TÀI KHOẢN", false, false, true,
					true);
			break;

		default:
			break;
		}
	}
}
