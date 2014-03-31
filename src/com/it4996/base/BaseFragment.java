package com.it4996.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {

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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = (LayoutInflater) getActivity().getSystemService(
				Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// super.onCreateView(inflater, container, savedInstanceState);
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

}
