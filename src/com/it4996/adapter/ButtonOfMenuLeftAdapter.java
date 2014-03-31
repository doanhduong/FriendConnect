package com.it4996.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;
import com.it4996.object.MenuLeft;

public class ButtonOfMenuLeftAdapter extends BaseAdapter {
	private ArrayList<MenuLeft> menuleft;
	private FriendConnectFragmentActivity activity;


	public ButtonOfMenuLeftAdapter(FriendConnectFragmentActivity activity,
			ArrayList<MenuLeft> menuleft) {

		this.menuleft = menuleft;
		this.activity = activity;
	}

	@Override
	public int getCount() {
		return menuleft.size();
	}

	@Override
	public Object getItem(int position) {
		return menuleft.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = (LayoutInflater) activity
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.menu_left_item, null);

			holder.menu_left_item_layout = (RelativeLayout) convertView
					.findViewById(R.id.menu_left_item_layout);
			holder.menu_left_item_icon = (ImageView) convertView
					.findViewById(R.id.menu_left_item_icon);
			holder.menu_left_item_name = (TextView) convertView
					.findViewById(R.id.menu_left_item_name);
			holder.menu_left_item_notification = (TextView) convertView
					.findViewById(R.id.menu_left_item_notification);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.menu_left_item_icon.setImageResource(menuleft.get(position)
				.getIcon());
		holder.menu_left_item_name.setText(menuleft.get(position).getName());

		if (menuleft.get(position).isNotificationVisible()) {
			holder.menu_left_item_notification.setText(menuleft.get(position)
					.getNotification());
			holder.menu_left_item_notification.setVisibility(View.VISIBLE);
		} else {
			holder.menu_left_item_notification.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

	class ViewHolder {
		public RelativeLayout menu_left_item_layout;
		public ImageView menu_left_item_icon;
		public TextView menu_left_item_name, menu_left_item_notification;
	}

}
