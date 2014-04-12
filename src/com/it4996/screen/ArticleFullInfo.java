package com.it4996.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;

public class ArticleFullInfo extends BaseFragment {
	private ImageView ivAvatar, ivImage;
	private TextView tvUsername, tvTimePost, tvContent, tvNumberLike,
			tvNumberComment;
	private EditText etContentComment;
	private Button btnComment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.article_full_info, null);
		}

		setTitleBar(StaticScreen.ARTICLE_FULL_INFO_SCREEN);
		
		
		Bundle bundle = this.getArguments();
		String articleId = bundle.getString("articleId", "Not data");

		getControls();
	}

	private void getControls() {
		ivAvatar = (ImageView) containerView
				.findViewById(R.id.article_full_info_avatar_iv);
		ivImage = (ImageView) containerView
				.findViewById(R.id.article_full_info_image_iv);
		tvUsername = (TextView) containerView
				.findViewById(R.id.article_full_info_username_tv);
		tvTimePost = (TextView) containerView
				.findViewById(R.id.article_full_info_time_post_tv);
		tvContent = (TextView) containerView
				.findViewById(R.id.article_full_info_content_tv);
		tvNumberLike = (TextView) containerView
				.findViewById(R.id.article_full_info_count_like_tv);
		tvNumberComment = (TextView) containerView
				.findViewById(R.id.article_full_info_count_comment_tv);
		etContentComment = (EditText) containerView
				.findViewById(R.id.article_full_info_content_comment_et);
		btnComment = (Button) containerView
				.findViewById(R.id.article_full_info_send_btn);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
