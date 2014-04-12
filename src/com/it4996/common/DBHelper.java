package com.it4996.common;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.object.User;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DBname = "androidpsdl.sqlite";
	private final String tableName = "psdlUser";

	public DBHelper(FriendConnectFragmentActivity fcFrgAtv) {
		super(fcFrgAtv, DBname, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE psdlUser (id VARCHAR(100) PRIMARY KEY, username VARCHAR(100), avatar VARCHAR(200), "
				+ "auth_token VARCHAR(100))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + tableName);
		onCreate(db);
	}

	/**
	 * set psdlUser info to DB
	 * 
	 * @param id
	 * @param username
	 * @param avatar
	 * @param auth_token
	 */
	public void setPSDLUser(String id, String username, String avatar,
			String auth_token) {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = this.getWritableDatabase();

		if (id == null) {
			id = "";
		}
		cv.put("id", id);

		if (username == null) {
			username = "";
		}
		cv.put("username", username);

		if (avatar == null) {
			avatar = "";
		}
		cv.put("avatar", avatar);

		if (auth_token == null) {
			auth_token = "";
		}
		cv.put("auth_token", auth_token);

		db.replace(tableName, null, cv);
		db.close();
	}

	public void update(String avatar) {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = this.getWritableDatabase();
		cv.put("avatar", avatar);
		db.update(tableName, cv, null, null);
		db.close();
	}

	/**
	 * Lay du lieu cua User tu trong DB
	 * 
	 * @return
	 */
	public User getPSDLUser() {
		User psdlUser = new User();
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cs = db.rawQuery("SELECT * FROM " + tableName, null);
			cs.moveToFirst();
			if (cs.getCount() > 0) {
				psdlUser.setId(cs.getString(cs.getColumnIndex("id")));
				psdlUser.setUsername(cs.getString(cs.getColumnIndex("username")));
				psdlUser.setAvatar(cs.getString(cs.getColumnIndex("avatar")));
				psdlUser.setAuth_token(cs.getString(cs
						.getColumnIndex("auth_token")));
			}
			cs.close();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psdlUser;
	}

	/**
	 * get id of user from db
	 * 
	 * @return
	 */
	public String getUserId() {
		String id = "";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cs = db.rawQuery("SELECT id FROM " + tableName, null);
		cs.moveToFirst();
		if (cs.getCount() > 0) {
			id = cs.getString(cs.getColumnIndex("id"));
		}
		cs.close();
		db.close();
		return id;
	}

	/**
	 * get avatar of user from db
	 * 
	 * @return
	 */
	public String getUserAvatar() {
		String avatar = "";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cs = db.rawQuery("SELECT avatar FROM " + tableName, null);
		cs.moveToFirst();
		if (cs.getCount() > 0) {
			avatar = cs.getString(cs.getColumnIndex("avatar"));
		}
		cs.close();
		db.close();
		return avatar;
	}

	/**
	 * get authen token of user from db
	 * 
	 * @return
	 */
	public String getAuthToken() {
		String authToken = "";
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cs = db.rawQuery("SELECT auth_token FROM " + tableName, null);
		cs.moveToFirst();
		if (cs.getCount() > 0) {
			authToken = cs.getString(cs.getColumnIndex("auth_token"));
		}
		cs.close();
		db.close();
		return authToken;
	}

	/**
	 * delete all row in table
	 */
	public void deleteAllData() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tableName, null, null);
		db.close();
	}
}