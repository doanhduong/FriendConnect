package com.it4996.network;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import android.util.Log;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.common.DBHelper;
import com.it4996.common.JsonParser;

public class BaseApi {
	// private final String SERVER_ADDRESS = "http://192.168.0.103:3000/api/";
	private final String SERVER_ADDRESS = "http://192.168.0.101:3000/api/";
//	private final String SERVER_ADDRESS = "http://192.168.173.1:3000/api/";
	private final int SERVER_TIMEOUT = 90000;
	protected final String TAG = BaseApi.class.toString();

	private final String AUTH_TOKEN = "auth_token";
	private final String USER_ID = "userId";

	protected FriendConnectFragmentActivity fcFrgAtv;
	protected JsonParser jParser;

	public BaseApi(FriendConnectFragmentActivity fcFrgAtv) {
		this.fcFrgAtv = fcFrgAtv;
		this.jParser = new JsonParser(fcFrgAtv);
	}

	protected String requestHTTP(int methodHttp, String nameAPI, String group,
			List<NameValuePair> paramList) {
		String result = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(),
					SERVER_TIMEOUT);
			HttpConnectionParams.setSoTimeout(httpClient.getParams(),
					SERVER_TIMEOUT);

			if (group == null) {
				group = "";
			}

			String url = SERVER_ADDRESS + group + nameAPI;
			Log.i(TAG, url);

			HttpResponse response = null;
			switch (methodHttp) {
			case METHOD_GET:
				if (paramList != null) {
					url += getStrParam(paramList, nameAPI);
					Log.i(TAG, url);
				}
				HttpGet httpget = new HttpGet(url);

				setHeadersForHttpRequest(null, httpget, null, null);
				response = httpClient.execute(httpget);
				break;
			case METHOD_POST:
				HttpPost httppost = new HttpPost(url);

				if (paramList != null) {
					httppost.setEntity(new UrlEncodedFormEntity(paramList,
							HTTP.UTF_8));
				}

				setHeadersForHttpRequest(httppost, null, null, null);
				response = httpClient.execute(httppost);
				break;
			case METHOD_DEL:
				if (paramList != null) {
					url += getStrParam(paramList, null);
					Log.i(TAG, url);
				}
				HttpDelete httpdelete = new HttpDelete(url);
				setHeadersForHttpRequest(null, null, httpdelete, null);
				response = httpClient.execute(httpdelete);
				break;
			case METHOD_PUT:
				HttpPut httpput = new HttpPut(url);
				if (paramList != null) {
					httpput.setEntity(new UrlEncodedFormEntity(paramList,
							HTTP.UTF_8));
				}

				setHeadersForHttpRequest(null, null, null, httpput);
				response = httpClient.execute(httpput);
				break;
			}

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			response.getEntity().writeTo(byteArrayOutputStream);
			result = byteArrayOutputStream.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.v(TAG, result);

		return result;
	}

	/*------------ Convert Params to String ------------*/
	private String getStrParam(List<NameValuePair> params, String nameAPI) {
		return "?" + URLEncodedUtils.format(params, "utf8");
	}

	/*------ Set Header For HttpRequest -------*/
	private void setHeadersForHttpRequest(HttpPost httppost, HttpGet httpget,
			HttpDelete httpdelete, HttpPut httpput) {
		DBHelper db = new DBHelper(fcFrgAtv);
		String auth_token = db.getAuthToken();
		String userId = db.getUserId();
		if (httppost != null) {
			if (userId != null && userId.length() > 0) {
				httppost.setHeader(USER_ID, userId);
			}
			if (auth_token != null && auth_token.length() > 0) {
				httppost.setHeader(AUTH_TOKEN, auth_token);
			}
		} else if (httpget != null) {
			if (userId != null && userId.length() > 0) {
				httpget.setHeader(USER_ID, userId);
			}
			if (auth_token != null && auth_token.length() > 0) {
				httpget.setHeader(AUTH_TOKEN, auth_token);
			}
		} else if (httpput != null) {

			if (userId != null && userId.length() > 0) {
				httpput.setHeader(USER_ID, userId);
			}
			if (auth_token != null && auth_token.length() > 0) {
				httpput.setHeader(AUTH_TOKEN, auth_token);
			}
		} else if (httpdelete != null) {
			if (userId != null && userId.length() > 0) {
				httpdelete.setHeader(USER_ID, userId);
			}
			if (auth_token != null && auth_token.length() > 0) {
				httpdelete.setHeader(AUTH_TOKEN, auth_token);
			}
		}
	}

	protected String parseResponse(String res) {
		if (jParser.parseResponse(res)) {
			return jParser.data;
		} else {
			return jParser.errorDescriptions;
		}
	}

	/* ====================================================================== */

	/*----------- Methods of API --------------*/
	protected final int METHOD_GET = 1;
	protected final int METHOD_POST = 2;
	protected final int METHOD_DEL = 3;
	protected final int METHOD_PUT = 4;

	/*---------- Name Of Group API -----------*/
	protected final String GROUP_ARTICLES = "articles/";
	protected final String GROUP_USERS = "users/";
	protected final String GROUP_COMMENTS = "comments/";
	protected final String GROUP_MESSAGES = "messages/";
	protected final String GROUP_STATUS = "status/";
	protected final String GROUP_LIKES = "likes/";
	protected final String GROUP_FRIENDS = "friends";
	protected final String GROUP_FOLLOWS = "follows/";
	protected final String GROUP_EMOTICONS = "emoticons/";

	/*-------------- NameAPI in Group USERS -----------*/
	protected static final String LOGIN = "login.json";
	protected static final String LOGOUT = "logout.json";
	protected static final String REGISTER = "register.json";
	protected static final String GET_ALL_USER = "get_all_user.json";
	protected static final String GET_USER_BY_ID = "get_user_by_id.json";
	protected static final String GET_USER_BY_NAME = "get_user_by_name.json";
	protected static final String UPDATE_INFORMATION = "update_info.json";
	protected static final String UPDATE_PASSWORD = "update_password.json";
	protected static final String FORGET_PASSWORD = "forget_pass.json";

	/*-------------- NameAPI in Group ARTICLES -----------*/
	protected static final String ADD_ARTICLE = "add_article.json";
	protected static final String GET_ARTICLE_FOLLOWING = "get_article_following.json";
	protected static final String GET_ARTICLE_BY_ID = "get_article_by_id.json";
	protected static final String GET_ARTICLE_BY_USERID = "get_article_by_userid.json";
	protected static final String GET_NEW_ARTICLE_HAVE_IMAGE = "get_new_article_have_image.json";
	protected static final String COUNT_ARTICLE_OF_USER = "count_article.json";
	protected static final String COUNT_TOTAL_ARTICLE_HAVE_IMAGE = "count_total_image.json";
	protected static final String DELETE_ARTICLE = "delete_article.json";
	protected static final String UPDATE_ARTICLE = "update_article.json";

	/*-------------- NameAPI in Group COMMENTS -----------*/
	protected static final String ADD_COMMENT = "add_comment.json";
	protected static final String GET_COMMENT_BY_ARTICLEID = "get_comment_by_articleid.json";
	protected static final String COUNT_COMMENT_OF_ARTICLE = "count_comment.json";
	protected static final String DELETE_COMMENT = "delete_comment.json";
	protected static final String UPDATE_COMMENT = "update_comment.json";

	/*-------------- NameAPI in Group FOLLOWS -----------*/
	protected static final String ADD_FOLLOW = "add_follow.json";
	protected static final String GET_LIST_FOLLOWING = "get_list_following.json";
	protected static final String COUNT_FOLLOWING = "count_following.json";
	protected static final String DELETE_FOLLOW = "delete_follow.json";

	/*-------------- NameAPI in Group LIKES -----------*/
	protected static final String ADD_LIKE = "add_like.json";
	protected static final String GET_LIKE_OF_ARTICLE = "get_like.json";
	protected static final String GET_ALL_LIKE_OF_USER = "get_all_like_of_user.json";
	protected static final String CHECK_LIKE = "check_like.json";
	protected static final String COUNT_LIKE = "count_like.json";
	protected static final String UNLIKE = "unlike.json";

	/*-------------- NameAPI in Group STATUS -----------*/
	protected static final String ADD_STATUS = "add_status.json";
	protected static final String GET_STATUS = "get_status.json";
	protected static final String UPDATE_STATUS = "update_status.json";
	protected static final String DELETE_STATUS = "delete_status.json";

	/*-------------- NameAPI in Group FRIENDS -----------*/
	protected static final String ADD_FRIEND = "add_friend.json";
	protected static final String GET_FRIEND_OF_USER = "get_friend.json";
	protected static final String CHECK_FRIEND = "check_friend.json";
	protected static final String COUNT_FRIEND = "count_friend.json";
	protected static final String DELETE_FRIEND = "delete_friend.json";
	protected static final String GET_LIST_REQUEST = "get_list_request.json";
	protected static final String GET_LIST_REQUESTED = "get_list_requested.json";
	protected static final String AGREE_ADD_FRIEND = "agree_add_friend.json";
	protected static final String DISAGREE_ADD_FRIEND = "disagree_add_friend.json";
}
