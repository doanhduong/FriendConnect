package com.it4996.screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;
import com.it4996.network.UserApi;

public class ForgetPassword extends BaseFragment {
	private EditText etEmail;
	private Button btnSend;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (containerView == null) {
			containerView = inflater.inflate(R.layout.forget_pasword, null);
		}

		etEmail = (EditText) containerView
				.findViewById(R.id.forget_password_email_et);
		btnSend = (Button) containerView.findViewById(R.id.forget_password_btn);

		btnSend.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setTitleBar(StaticScreen.FORGET_PASSWORD_SCREEN);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (v == btnSend) {
			UserApi api = new UserApi(fcFrgAtv);
		}
	}

}
