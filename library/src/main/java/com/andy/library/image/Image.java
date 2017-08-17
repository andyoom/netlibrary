package com.andy.library.image;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 类描述：
 * 创建人：yekh
 * 创建时间：2017/8/17 11:54
 */
public class Image {
    public static void display(String url, ImageView imageView){
        ImageLoader.getInstance().displayImage(url,imageView);
//        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
}
