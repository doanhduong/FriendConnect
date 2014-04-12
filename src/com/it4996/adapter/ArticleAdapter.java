package com.it4996.adapter;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.common.FragmentController;
import com.it4996.common.JsonParser;
import com.it4996.friendconnect.R;
import com.it4996.network.UserApi;
import com.it4996.object.Article;
import com.it4996.screen.ArticleFullInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ArticleAdapter extends BaseAdapter {
	private FriendConnectFragmentActivity fcFrgAtv;
	private List<Article> arrArticle;
	private LayoutInflater inflater;

	public ArticleAdapter(FriendConnectFragmentActivity fcFrgAtv,
			List<Article> arrArticle) {
		this.fcFrgAtv = fcFrgAtv;
		this.arrArticle = arrArticle;

		inflater = (LayoutInflater) fcFrgAtv
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return arrArticle.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		final ArticleHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.my_item_article_layout,
					null);
			holder = new ArticleHolder();

			holder.avatar = (ImageView) convertView
					.findViewById(R.id.item_article_avatar_iv);
			holder.username = (TextView) convertView
					.findViewById(R.id.item_article_username_tv);
			holder.time_post = (TextView) convertView
					.findViewById(R.id.item_article_post_date_tv);
			holder.content = (TextView) convertView
					.findViewById(R.id.item_article_content_tv);
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_article_image_iv);
			holder.like_tv = (TextView) convertView
					.findViewById(R.id.item_article_like_tv);
			holder.comment_tv = (TextView) convertView
					.findViewById(R.id.item_article_comment_tv);
			holder.like_iv = (ImageView) convertView
					.findViewById(R.id.item_article_like_iv);
			holder.comment_iv = (ImageView) convertView
					.findViewById(R.id.item_article_comment_iv);
			holder.remove = (ImageView) convertView
					.findViewById(R.id.item_article_remove_article_iv);

			convertView.setTag(holder);
		} else {
			holder = (ArticleHolder) convertView.getTag();
		}

		final Article article = arrArticle.get(position);
		UserApi userAPI = new UserApi(fcFrgAtv);
		JsonParser jParser = new JsonParser(fcFrgAtv);

		holder.username.setText(article.getId());

		holder.time_post.setText(article.getPost_date());

		// anh bai viet
		if (article.getUrl_image().equals("")) {
			holder.image.setVisibility(ImageView.GONE);
		} else {
			holder.image.setVisibility(ImageView.VISIBLE);
			ImageLoader.getInstance().displayImage(article.getUrl_image(),
					holder.image);
		}

		holder.content.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewDetailArticle(article.getId());
			}
		});
		holder.image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				viewDetailArticle(article.getId());
			}
		});

		return convertView;
	}

	private void viewDetailArticle(String articleId) {
		ArticleFullInfo fragment = new ArticleFullInfo();
		Bundle bundle = new Bundle();
		bundle.putString("articleId", articleId);
		fragment.setArguments(bundle);

		FragmentController.replaceWithAddToBackStack(fcFrgAtv, fragment,
				ArticleFullInfo.class.toString());
	}

	private class ArticleHolder {
		private ImageView avatar;
		private TextView username, time_post;
		private ImageView remove;
		private TextView content;
		private ImageView image;
		private ImageView comment_iv, like_iv;
		private TextView comment_tv, like_tv;
	}

}
