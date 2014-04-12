package com.it4996.screen;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.it4996.base.BaseFragment;
import com.it4996.friendconnect.R;

public class UpdateAvatarCover extends BaseFragment implements OnClickListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(containerView == null){
			containerView = inflater.inflate(R.layout.update_avatar_cover, null);
		}
		
		setTitleBar(StaticScreen.UPDATE_AVATAR_COVER_SCREEN);
		
		Bundle bundle = this.getArguments();
		String typeScreen = bundle.getString("typeScreen", "");
		if(typeScreen.equals("avatarScreen")){
			
		}
		
		
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
	
}
