package com.it4996.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.BackStackEntry;

import com.it4996.base.BaseFragment;
import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;

public class FragmentController {
	/**
	 * replace new fragment to screen, but don't add to {@link BackStackEntry}
	 * 
	 * @param frgManager
	 * @param fragment
	 */
	public static void replaceDontAddToBackStack(
			FriendConnectFragmentActivity fcFrgAtv, Fragment fragment) {
		fcFrgAtv.getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, fragment).commit();
	}

	/**
	 * remove fragment from containerView
	 * 
	 * @param shlFragment
	 */
	public static void removeFragment(FriendConnectFragmentActivity fcFrgAtv,
			Fragment fragment) {
		fcFrgAtv.getSupportFragmentManager().beginTransaction()
				.remove(fragment).commit();
	}

	public static void replaceWithAddToBackStack(
			FriendConnectFragmentActivity fcFrgAtv, BaseFragment fragment,
			String nameClass) {
		fcFrgAtv.getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, fragment, nameClass)
				.addToBackStack(nameClass).commit();

	}

}
