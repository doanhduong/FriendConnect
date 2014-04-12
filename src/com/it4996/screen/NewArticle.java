package com.it4996.screen;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;

public class NewArticle extends BaseFragment implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.new_article_status, null);
		}

		setTitleBar(StaticScreen.NEW_ARTICLE_SCREEN);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
}
