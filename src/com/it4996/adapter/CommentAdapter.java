package com.it4996.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;
import com.it4996.object.Comment;

public class CommentAdapter extends BaseAdapter {
	private FriendConnectFragmentActivity fcFrgAtv;
	private List<Comment> arrComment;
	private LayoutInflater inflater;

	public CommentAdapter(FriendConnectFragmentActivity fcFrgAtv,
			List<Comment> arrComment) {
		this.fcFrgAtv = fcFrgAtv;
		this.arrComment = arrComment;
		
		inflater = (LayoutInflater) fcFrgAtv
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return arrComment.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arrComment.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CommentHolder holder = null;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.my_item_comment_layout,
					null);
			holder = new CommentHolder();
			
			holder.avatar = (ImageView)convertView.findViewById(R.id.item_comment_avatar_iv);
			holder.username = (TextView)convertView.findViewById(R.id.item_comment_username_tv);
			holder.time_post = (TextView)convertView.findViewById(R.id.item_comment_time_post_tv);
			holder.remove = (ImageView)convertView.findViewById(R.id.item_comment_delete_iv);
			holder.content = (TextView)convertView.findViewById(R.id.item_comment_content_tv);
			
			convertView.setTag(holder);
		}
		else{
			holder = (CommentHolder)convertView.getTag();
		}
		
		return convertView;
	}

	private class CommentHolder{
		private ImageView avatar, remove;
		private TextView username, time_post, content;
	}
}
