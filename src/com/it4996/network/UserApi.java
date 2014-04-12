package com.it4996.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.common.DBHelper;

public class UserApi extends BaseApi {

	public UserApi(FriendConnectFragmentActivity fcFrgAtv) {
		super(fcFrgAtv);
	}

	public String register(String username, String password, String email) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("email", email));

		return requestHTTP(METHOD_POST, REGISTER, GROUP_USERS,
				params);
	}

	public String login(String username, String password) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		String res = requestHTTP(METHOD_PUT, LOGIN, GROUP_USERS, params);
		return res;
	}

	public String getAllUser() {
		return parseResponse(requestHTTP(METHOD_GET, GET_ALL_USER, GROUP_USERS,
				null));
	}

	public String getUserById(String userId) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", userId));

		String response = requestHTTP(METHOD_GET, GET_USER_BY_ID, GROUP_USERS,
				params);
		return parseResponse(response);
	}

	public String getUserByUsername(String username) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));

		return requestHTTP(METHOD_GET, GET_USER_BY_NAME, GROUP_USERS, params);
	}

	public void updateInformation() {
	}

	public String forgetPassword(String email) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));

		return requestHTTP(METHOD_PUT, FORGET_PASSWORD, GROUP_USERS, params);
	}

	public String logout() {
		DBHelper db = new DBHelper(fcFrgAtv);
		db.deleteAllData();
		db.close();
		SharedPreferences sharePref = fcFrgAtv
				.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharePref.edit();
		editor.clear();
		editor.commit();
		return parseResponse(requestHTTP(METHOD_PUT, LOGOUT, GROUP_USERS, null));
	}

}
