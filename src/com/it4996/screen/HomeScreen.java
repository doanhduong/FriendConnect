package com.it4996.screen;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.it4996.adapter.ArticleAdapter;
import com.it4996.base.BaseFragment;
import com.it4996.common.DBHelper;
import com.it4996.common.JsonParser;
import com.it4996.friendconnect.R;
import com.it4996.helper.ListViewHelper;
import com.it4996.network.ArticleApi;
import com.it4996.object.Article;

public class HomeScreen extends BaseFragment{
	private ListView lvArticle;
	private ArticleAdapter adapter;
	private List<Article> arrArticle;
	private ArticleApi api;
	private DBHelper dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.home, null);
		}
		
		lvArticle = (ListView) containerView.findViewById(R.id.home_listview);	
			
		api = new ArticleApi(fcFrgAtv);
		dbHelper = new DBHelper(fcFrgAtv);

		jParser = new JsonParser(fcFrgAtv);

		arrArticle = new ArrayList<Article>();
		
		fcFrgAtv.getTitleBar().getTitle_bar_menu_left().setOnClickListener(this);
		
		new GetArticle().execute();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setTitleBar(StaticScreen.HOME_SCREEN);
		
		
		return super.onCreateView(inflater, container, savedInstanceState);
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

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
}
