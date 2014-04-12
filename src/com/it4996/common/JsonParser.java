package com.it4996.common;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.object.Article;
import com.it4996.object.User;

public class JsonParser {
	private FriendConnectFragmentActivity fcFrgAtv;
	public int statusCode;
	public String errorDescriptions; // message error
	public String data;

	public JsonParser(FriendConnectFragmentActivity fcFrgAtv) {
		this.fcFrgAtv = fcFrgAtv;
	}

	public boolean parseResponse(String response) {
		try {
			JSONObject jObject = new JSONObject(response);
			if (jObject.opt("status_code") != null) {
				statusCode = jObject.getInt("status_code");

				if (statusCode == 200) {
					if (jObject.opt("data") != null) {
						data = jObject.getString("data");
						if (jObject.opt("message") != null) {
							errorDescriptions = jObject.getString("message");
						} else {
							errorDescriptions = "";
						}
						return true;
					} else {
						errorWhileParsingServerResponse();
						return false;
					}
				} else {
					if (jObject.opt("message") != null) {
						data = null;
						errorDescriptions = jObject.getString("message");
					} else {
						errorWhileParsingServerResponse();
					}
					return false;
				}
			} else {
				errorWhileParsingServerResponse();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorWhileParsingServerResponse();
			return false;
		}
	}

	private void errorWhileParsingServerResponse() {
		data = null;
		errorDescriptions = "Error while parsing server response";
	}

	/*
	 * ====================== Parse Response of USER
	 * ==============================
	 */
	private User getUser(JSONObject jObj) {
		User user = new User();
		try {
			if (jObj != null) {
				if (jObj.optString("_id") != null)
					user.setId(jObj.getString("_id"));
				if (jObj.optString("username") != null)
					user.setUsername(jObj.getString("username"));
				if (jObj.optString("password") != null)
					user.setPassword(jObj.getString("password"));
				if (jObj.optString("email") != null)
					user.setEmail(jObj.getString("email"));
				if (jObj.optString("auth_token") != null)
					user.setAuth_token(jObj.getString("auth_token"));
				if (jObj.optString("fullname") != null)
					user.setFullname(jObj.getString("fullname"));
				if (jObj.optString("avatar") != null)
					user.setAvatar(jObj.getString("avatar"));
				if (jObj.optString("cover") != null)
					user.setCover(jObj.getString("cover"));
				if (jObj.optString("address") != null)
					user.setAddress(jObj.getString("address"));
				if (jObj.optString("phone") != null)
					user.setPhone(jObj.getString("phone"));
				if (jObj.optString("birthday") != null)
					user.setBirthday(jObj.getString("birthday"));
				if (jObj.optString("gender") != null)
					user.setGender(jObj.optInt("gender"));

				if (jObj.optString("join_date") != null)
					user.setJoin_date(jObj.getString("join_date"));
				user.setOnline(jObj.optBoolean("online"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User getUser(String response) {
		User user = new User();
		try {
			JSONObject jObj = new JSONObject(response);
			Log.v(JsonParser.class.toString(), jObj.toString());
			user = getUser(jObj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> getListUser(String json) {
		List<User> listUser = new ArrayList<User>();
		try {
			JSONArray jArrayUser = new JSONArray(json);
			if (jArrayUser != null) {
				for (int i = 0; i < jArrayUser.length(); i++) {
					JSONObject jObj = jArrayUser.optJSONObject(i);
					if (jObj != null) {
						User user = new User();
						user = getUser(jObj);
						listUser.add(user);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listUser;
	}

	public boolean sessionData(String response) {
		try {
			JSONObject jObject = new JSONObject(response);
			String id;
			String username = null;
			String avatar = null;
			String auth_token;

			if (jObject.opt("auth_token") != null) {
				auth_token = jObject.getString("auth_token");
				if (auth_token.length() > 0) {
					if (jObject.opt("_id") != null) {
						id = jObject.getString("_id");

						if (jObject.opt("username") != null) {
							username = jObject.getString("username");
						}
						if (jObject.opt("avatar") != null) {
							avatar = jObject.getString("avatar");
						}

						DBHelper db = new DBHelper(fcFrgAtv);
						db.setPSDLUser(id, username, avatar, auth_token);
						db.close();

						// update info vao Menu
						fcFrgAtv.getSlidingMenu().setUserNameAndAvatarOfCurrentUser(username,
								avatar);
						return true;
					} else {
						cantGetUserIDFromServer();
						return false;
					}
				} else {
					cantGetAuthTokenKey();
					return false;
				}
			} else {
				cantGetAuthTokenKey();
				return false;
			}
		} catch (Exception e) {
			cantGetAuthTokenKey();
			return false;
		}
	}

	private void cantGetUserIDFromServer() {
		data = null;
		errorDescriptions = "Can not get userId from server";
	}

	private void cantGetAuthTokenKey() {
		data = null;
		errorDescriptions = "Can not get auth_token key";
	}

	/*
	 * ====================== Parse Response of Article ======================
	 */
	private Article getArticle(JSONObject jObj) {
		Article article = new Article();
		try {
			if (jObj != null) {
				if (jObj.optString("_id") != null) {
					article.setId(jObj.getString("_id"));
				}
				if (jObj.optString("poster") != null) {
					article.setPoster(jObj.getString("poster"));
				}
				if (jObj.optString("content") != null) {
					article.setContent(jObj.getString("content"));
				}
				if (jObj.optString("url_image") != null) {
					article.setUrl_image(jObj.getString("url_image"));
				}
				if (jObj.optString("post_date") != null) {
					article.setPost_date(jObj.getString("post_date"));
				}
				if (jObj.optString("update_date") != null) {
					article.setUpdate_date(jObj.getString("update_date"));
				}
				if (jObj.optString("post_location") != null) {
					article.setPost_location(jObj.getString("post_location"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return article;
	}

	public Article getArticle(String response) {
		Article article = new Article();
		try {
			JSONObject jObj = new JSONObject(response);
			article = getArticle(jObj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return article;
	}

	public List<Article> getListArticle(String response) {
		List<Article> listArticle = new ArrayList<Article>();

		try {
			JSONArray jArrayArticle = new JSONArray(response);
			if (jArrayArticle != null) {
				for (int i = 0; i < jArrayArticle.length(); i++) {
					JSONObject jObj = jArrayArticle.optJSONObject(i);
					if (jObj != null) {
						Article article = new Article();
						article = getArticle(jObj);
						listArticle.add(article);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return listArticle;
	}

}
