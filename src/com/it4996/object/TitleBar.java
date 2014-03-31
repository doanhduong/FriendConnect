package com.it4996.object;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it4996.friendconnect.R;

public class TitleBar extends RelativeLayout {
	
	private ImageView title_bar_menu_left, title_bar_menu_right;
	private TextView title_bar_tv;

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.title_bar, this);

		title_bar_menu_left = (ImageView) findViewById(R.id.title_bar_menu_left);
		title_bar_menu_right = (ImageView) findViewById(R.id.title_bar_menu_right);
		title_bar_tv = (TextView) findViewById(R.id.title_bar_tv);
	}

	public ImageView getTitle_bar_menu_left() {
		return title_bar_menu_left;
	}

	public void setTitle_bar_menu_left(ImageView title_bar_menu_left) {
		this.title_bar_menu_left = title_bar_menu_left;
	}

	public ImageView getTitle_bar_menu_right() {
		return title_bar_menu_right;
	}

	public void setTitle_bar_menu_right(ImageView title_bar_menu_right) {
		this.title_bar_menu_right = title_bar_menu_right;
	}

	public TextView getTitle_bar_tv() {
		return title_bar_tv;
	}

	public void setTitle_bar_tv(TextView title_bar_tv) {
		this.title_bar_tv = title_bar_tv;
	}

	public void hintTitleBar(){
		this.setVisibility(GONE);
	}
	
	public void setTitleBarHaveMenu(){
		
	}
}
