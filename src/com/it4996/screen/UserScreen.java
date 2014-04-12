package com.it4996.screen;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

import com.it4996.adapter.ArticleAdapter;
import com.it4996.base.BaseFragment;
import com.it4996.common.DBHelper;
import com.it4996.common.FragmentController;
import com.it4996.common.JsonParser;
import com.it4996.friendconnect.R;
import com.it4996.helper.ListViewHelper;
import com.it4996.network.ArticleApi;
import com.it4996.object.Article;

public class UserScreen extends BaseFragment{
	private ListView lvArticle;
	private ArticleAdapter adapter;
	private List<Article> arrArticle;

	private ArticleApi api;
	private DBHelper dbHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Toast.makeText(fcFrgAtv, "OnCreateView User", Toast.LENGTH_SHORT)
				.show();
		setTitleBar(StaticScreen.USER_SCREEN);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.user, null);
		}

		api = new ArticleApi(fcFrgAtv);
		dbHelper = new DBHelper(fcFrgAtv);
		jParser = new JsonParser(fcFrgAtv);

		lvArticle = (ListView) containerView
				.findViewById(R.id.user_article_listview);

		arrArticle = new ArrayList<Article>();

		new GetArticle().execute();

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		fcFrgAtv.getTitleBar().getTitle_bar_menu_right()
				.setVisibility(View.GONE);
	}

	private class GetArticle extends AsyncTask<Void, Void, List<Article>> {

		@Override
		protected List<Article> doInBackground(Void... params) {
			String res = api.getArticleByUserId(dbHelper.getUserId());
			arrArticle = jParser.getListArticle(res);
			return arrArticle;
		}

		@Override
		protected void onPostExecute(List<Article> result) {
			adapter = new ArticleAdapter(fcFrgAtv, arrArticle);
			lvArticle.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
			lvArticle.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			ListViewHelper.getListViewSize(lvArticle, fcFrgAtv);
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

	}

	private void setMenuRight(View v) {
		PopupMenu popup = new PopupMenu(fcFrgAtv, v);
		popup.getMenuInflater().inflate(R.menu.menu_article, popup.getMenu());
		popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.menu_new_article:
					FragmentController.replaceWithAddToBackStack(fcFrgAtv,
							new NewArticle(), NewArticle.class.toString());
					break;

				case R.id.menu_new_status:
					FragmentController.replaceWithAddToBackStack(fcFrgAtv,
							new NewStatus(), NewStatus.class.toString());
					break;

				case R.id.menu_remove_status:

					break;
				case R.id.menu_edit_profile:
					FragmentController.replaceWithAddToBackStack(fcFrgAtv,
							new UpdateProfile(), UpdateProfile.class.toString());
					break;

				case R.id.menu_update_avatar:
					UpdateAvatarCover avatarFragment = new UpdateAvatarCover();
					Bundle bundleAvatar = new Bundle();
					bundleAvatar.putString("typeScreen", "avatarScreen");
					avatarFragment.setArguments(bundleAvatar);

					FragmentController.replaceWithAddToBackStack(fcFrgAtv,
							avatarFragment, UpdateAvatarCover.class.toString());
					break;

				case R.id.menu_update_cover:
					UpdateAvatarCover coverFragment = new UpdateAvatarCover();
					Bundle bundleCover = new Bundle();
					bundleCover.putString("typeScreen", "coverScreen");
					coverFragment.setArguments(bundleCover);
					FragmentController.replaceWithAddToBackStack(fcFrgAtv,
							coverFragment, UpdateAvatarCover.class.toString());
					break;

				default:
					break;
				}
				Toast.makeText(fcFrgAtv, "You Clicked : " + item.getTitle(),
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		popup.show();

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == menuRight) {
			setMenuRight(v);
		}
	}
}
