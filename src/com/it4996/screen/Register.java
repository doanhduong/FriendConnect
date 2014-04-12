package com.it4996.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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

public class Register extends BaseFragment {
	private EditText etUsername, etPassword, etRePassword, etEmail;
	private Button btnRegister;
	private TextView tvLinkToLogin;
	private String username, password, rePassword, email;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.register, null);
		}

		setTitleBar(StaticScreen.REGISTER_SCREEN);

		etUsername = (EditText) containerView
				.findViewById(R.id.register_username_et);
		etPassword = (EditText) containerView
				.findViewById(R.id.register_password_et);
		etRePassword = (EditText) containerView
				.findViewById(R.id.register_confirm_password_et);
		etEmail = (EditText) containerView.findViewById(R.id.register_email_et);

		btnRegister = (Button) containerView
				.findViewById(R.id.register_btn_register);
		tvLinkToLogin = (TextView) containerView
				.findViewById(R.id.register_link_to_login_tv);

		tvLinkToLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentController.replaceDontAddToBackStack(fcFrgAtv,
						new Login());
			}
		});

		btnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				username = etUsername.getText().toString();
				password = etPassword.getText().toString();
				rePassword = etRePassword.getText().toString();
				email = etEmail.getText().toString();
				if (checkUsername(username)
						&& checkPassword(password, rePassword)
						&& checkEmail(email)) {

					new RegisterUser().execute();
				}
			}
		});
	}

	private boolean checkUsername(String username) {
		if (username.equals("")) {
			Toast.makeText(fcFrgAtv, "Tên tài khoản không hợp lệ",
					Toast.LENGTH_LONG).show();
			return false;
		} else
			return true;
	}

	private boolean checkPassword(String pass1, String pass2) {
		if (pass1.equals(pass2)) {
			if (pass1.length() >= 6) {
				return true;
			} else {
				Toast.makeText(fcFrgAtv, "Mật khẩu ít nhất phải có 6 ký tự!",
						Toast.LENGTH_LONG).show();
				return false;
			}
		} else {
			Toast.makeText(fcFrgAtv,
					"Mật khẩu và mật khẩu nhập lại phải giống nhau!",
					Toast.LENGTH_LONG).show();
			return false;
		}
	}

	private boolean checkEmail(String email) {
		if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
			return true;
		else {
			Toast.makeText(fcFrgAtv, "Email không hợp lệ", Toast.LENGTH_LONG)
					.show();
			return false;
		}
	}

	private class RegisterUser extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {

			UserApi service = new UserApi(fcFrgAtv);
			return service.register(username, password, email);

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
			super.onPostExecute(result);
		}

	}
}
