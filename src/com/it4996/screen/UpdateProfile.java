package com.it4996.screen;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;

public class UpdateProfile extends BaseFragment {
	private ImageView avartar;
	private TextView username, email, birthday;
	private EditText fullname, address, phone;

	Calendar myCalendar = Calendar.getInstance();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss",
			Locale.getDefault());

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (containerView == null) {
			containerView = inflater.inflate(R.layout.update_information, null);
		}

		setTitleBar(StaticScreen.UPDATE_PROFILE);
		
		avartar = (ImageView) containerView
				.findViewById(R.id.update_information_avatar_iv);
		username = (TextView) containerView
				.findViewById(R.id.update_information_username_tv);
		email = (TextView) containerView
				.findViewById(R.id.update_information_email_tv);
		fullname = (EditText) containerView
				.findViewById(R.id.update_information_fullname_et);
		address = (EditText) containerView
				.findViewById(R.id.update_information_address_et);
		phone = (EditText) containerView
				.findViewById(R.id.update_information_phone_et);
		birthday = (TextView) containerView
				.findViewById(R.id.update_information_birthday_tv);

		birthday.setOnClickListener(this);
	}

	private void updateLabel() {
		String time_str = dateFormat.format(myCalendar.getTime());
		String[] s = time_str.split(" ");

		int year_sys = Integer.parseInt(s[0].split("/")[0]);
		int month_sys = Integer.parseInt(s[0].split("/")[1]);
		int day_sys = Integer.parseInt(s[0].split("/")[2]);

		birthday.setText(String.valueOf(day_sys).toString() + "/"
				+ String.valueOf(month_sys).toString() + "/"
				+ String.valueOf(year_sys).toString());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if(v==birthday){
			DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener() {
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					myCalendar.set(Calendar.YEAR, year);
					myCalendar.set(Calendar.MONTH, monthOfYear);
					myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					updateLabel();
				}
			};

			new DatePickerDialog(fcFrgAtv, datePickerDialog, myCalendar
					.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
					myCalendar.get(Calendar.DAY_OF_MONTH)).show();
		}
	}

}
