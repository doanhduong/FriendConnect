package com.it4996.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.it4996.base.FriendConnectFragmentActivity;

public class ArticleApi extends BaseApi {

	public ArticleApi(FriendConnectFragmentActivity fcFrgAtv) {
		super(fcFrgAtv);
	}

	public String addArticle(String poster, String content, String url_image,
			String post_location) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("poster", poster));
		params.add(new BasicNameValuePair("content", content));
		params.add(new BasicNameValuePair("url_image", url_image));
		params.add(new BasicNameValuePair("post_location", post_location));
		String res = requestHTTP(METHOD_POST, ADD_ARTICLE, GROUP_ARTICLES,
				params);
		return parseResponse(res);
	}

	public String getArticleFollowing(String userId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String res = requestHTTP(METHOD_GET, GET_ARTICLE_FOLLOWING,
				GROUP_ARTICLES, params);
		return parseResponse(res);
	}

	public String getArticleById(String articleId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("articleId", articleId));
		String res = requestHTTP(METHOD_GET, GET_ARTICLE_BY_ID, GROUP_ARTICLES,
				params);
		return parseResponse(res);
	}

	public String getArticleByUserId(String userId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String res = requestHTTP(METHOD_GET, GET_ARTICLE_BY_USERID,
				GROUP_ARTICLES, params);
		return parseResponse(res);
	}

	public String getNewArticleHaveImage(String userId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String res = requestHTTP(METHOD_GET, GET_NEW_ARTICLE_HAVE_IMAGE,
				GROUP_ARTICLES, params);
		return parseResponse(res);
	}

	public String countArticle(String userId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String res = requestHTTP(METHOD_GET, COUNT_ARTICLE_OF_USER,
				GROUP_ARTICLES, params);
		return parseResponse(res);
	}

	public String countTotalImage(String userId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));
		String res = requestHTTP(METHOD_GET, COUNT_TOTAL_ARTICLE_HAVE_IMAGE,
				GROUP_ARTICLES, params);
		return parseResponse(res);
	}

	public String removeArticle(String articleId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("articleId", articleId));
		String res = requestHTTP(METHOD_DEL, DELETE_ARTICLE, GROUP_ARTICLES,
				params);
		return parseResponse(res);
	}

	public String updateArticle(String articleId, String content) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("articleId", articleId));
		params.add(new BasicNameValuePair("content", content));

		String res = requestHTTP(METHOD_PUT, UPDATE_ARTICLE, GROUP_ARTICLES,
				params);
		return parseResponse(res);
	}

}
