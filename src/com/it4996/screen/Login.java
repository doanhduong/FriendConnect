package com.it4996.screen;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.it4996.base.BaseFragment;
import com.it4996.common.DBHelper;
import com.it4996.common.FragmentController;
import com.it4996.common.JsonParser;
import com.it4996.friendconnect.R;
import com.it4996.network.UserApi;

public class Login extends BaseFragment {

	private Button btnLogin;
	private EditText etUsername, etPassword;
	private TextView tvForgetPassword, tvLinkToRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.login, null);
		}

		etUsername = (EditText) containerView
				.findViewById(R.id.login_username_et);
		etPassword = (EditText) containerView.findViewById(R.id.login_pass_et);
		tvForgetPassword = (TextView) containerView
				.findViewById(R.id.login_forget_pass_tv);
		tvLinkToRegister = (TextView) containerView
				.findViewById(R.id.login_link_to_register_tv);
		btnLogin = (Button) containerView.findViewById(R.id.login_btn_login);

		btnLogin.setOnClickListener(this);
		tvLinkToRegister.setOnClickListener(this);
		tvForgetPassword.setOnClickListener(this);

		SharedPreferences sharePref = fcFrgAtv
				.getPreferences(Context.MODE_PRIVATE);
		if (sharePref.contains("userId"))

			FragmentController.replaceDontAddToBackStack(fcFrgAtv,
					new HomeScreen());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setTitleBar(StaticScreen.LOGIN_SCREEN);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);

		if (v == btnLogin) {
			String username = etUsername.getText().toString();
			String password = etPassword.getText().toString();
			String[] data = { username, password };
			if (!username.equals("") && !password.equals(""))
				new Logins().execute(data);
			else {
				Toast.makeText(fcFrgAtv,
						"Tên đăng nhập và mật khẩu không được để trống",
						Toast.LENGTH_SHORT).show();
			}
		}
		if (v == tvLinkToRegister) {
			FragmentController.replaceWithAddToBackStack(fcFrgAtv,
					new Register(), Register.class.toString());
		}
		if (v == tvForgetPassword) {
			FragmentController.replaceWithAddToBackStack(fcFrgAtv,
					new ForgetPassword(), ForgetPassword.class.toString());
		}
	}

	private class Logins extends AsyncTask<String, Void, String> {
		ProgressDialog pDialog;

		@Override
		protected String doInBackground(String... params) {
			String result = null;

			UserApi service = new UserApi(fcFrgAtv);
			result = service.login(params[0], params[1]);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			JsonParser js = new JsonParser(fcFrgAtv);

			if (js.parseResponse(result)) {
				if (js.sessionData(js.data)) {
					FragmentController.replaceDontAddToBackStack(fcFrgAtv,
							new HomeScreen());
					
					DBHelper db = new DBHelper(fcFrgAtv);

					SharedPreferences sharePref = fcFrgAtv
							.getPreferences(Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharePref.edit();
					editor.putString("userId", db.getUserId());
					editor.putString("auth_token", db.getAuthToken());
					editor.commit();
				}
			} else {
				Toast.makeText(fcFrgAtv, js.errorDescriptions,
						Toast.LENGTH_SHORT).show();
			}

			pDialog.dismiss();
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			pDialog = new ProgressDialog(fcFrgAtv);
			pDialog.setCancelable(true);
			pDialog.show();
			super.onPreExecute();
		}

	}
}
