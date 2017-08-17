package com.andy.library;

import android.app.Application;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.impl.BaseDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.xutils.x;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/16 16:09
 */
public class AppLibrary extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        Logger.addLogAdapter(new AndroidLogAdapter());

        initImageloader();
    }

    private void initImageloader() {

        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .bitmapConfig(Bitmap.Config.RGB_565)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .showImageOnLoading(R.drawable.ic_launcher)
                .displayer(new RoundedBitmapDisplayer(45))
//                .displayer(new FadeInBitmapDisplayer(100))
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .diskCacheFileCount(100)
                .diskCacheSize(30*1024*1024)
                .diskCache(new BaseDiskCache(getExternalCacheDir()) {
                })
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
