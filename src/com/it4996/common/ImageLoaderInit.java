package com.it4996.common;

import android.graphics.Bitmap;

import com.it4996.base.FriendConnectFragmentActivity;
import com.it4996.friendconnect.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class ImageLoaderInit {
	private FriendConnectFragmentActivity fcFrgAtv;

	public ImageLoaderInit(FriendConnectFragmentActivity fcFrgAtv) {
		this.fcFrgAtv = fcFrgAtv;
	}

	public void init() {

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

				.showImageOnFail(R.drawable.ic_action_picture)
				// .showStubImage(R.drawable.loading)
				.resetViewBeforeLoading(true).cacheInMemory(true)
				.cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.displayer(new FadeInBitmapDisplayer(100)).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				fcFrgAtv.getApplicationContext())
				// .discCache(new UnlimitedDiscCache(cacheDir))
				.denyCacheImageMultipleSizesInMemory()
				.defaultDisplayImageOptions(defaultOptions)
				.threadPoolSize(3)
				.threadPriority(Thread.MAX_PRIORITY)
				.memoryCache(new LruMemoryCache(30 * 1024 * 1024))
				// .memoryCacheSizePercentage(13)
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs().build();

		ImageLoader.getInstance().init(config);
	}

}
