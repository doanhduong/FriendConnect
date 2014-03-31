package com.it4996.object;

import android.content.Context;
import android.view.animation.AnimationUtils;

import com.it4996.friendconnect.R;

public class Animation {
	private Context context;

	public Animation(Context context) {
		this.context = context;
	}

	public android.view.animation.Animation fadeOutAnimation() {
		return AnimationUtils.loadAnimation(context, R.animator.fade_out);
	}

	public android.view.animation.Animation fadeInAnimation() {
		return AnimationUtils.loadAnimation(context, R.animator.fadein);
	}

	public android.view.animation.Animation fadeNMove() {
		return AnimationUtils.loadAnimation(context, R.animator.fade_n_move);
	}

	public android.view.animation.Animation rotate() {
		return AnimationUtils.loadAnimation(context, R.animator.rotate);

	}

}
