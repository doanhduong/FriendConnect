package com.it4996.object;

import android.app.Activity;
import android.graphics.Typeface;

public class Font {
	Activity activity;
	Typeface font;

	public Font(Activity activity) {
		this.activity = activity;
		setDefaultTypeface();
	}

	public void setDefaultTypeface() {
		font = Typeface.createFromAsset(activity.getAssets(),
				"fonts/HelveticaNeueLTStd-LtExO.otf");
	}

	public Typeface getFont() {
		return font;
	}

	public void setFont(Typeface font) {
		this.font = font;
	}

}
