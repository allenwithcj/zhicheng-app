package com.zhicheng.module.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.io.File;

import cc.dagger.photopicker.PhotoPickerImageLoader;

/**
 * Created by Donson on 2017/2/14.
 */

public class GlideImageLoader extends PhotoPickerImageLoader {

    @Override
    public ImageView onCreateGridItemView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return imageView;
    }

    @Override
    public void loadGridItemView(ImageView view, String imagePath, final int tag, int width, int height) {

        Glide.with(view.getContext())
                .load("file://" + imagePath)
                .placeholder(getDefaultPlaceHolder())
                .error(getDefaultPlaceHolder())
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                .skipMemoryCache(false)
                .centerCrop()
                .crossFade()
                .into(new ImageViewTarget<GlideDrawable>(view) {
                    @Override
                    protected void setResource(GlideDrawable resource) {
                        view.setImageDrawable(resource);
                    }

                    @Override
                    public void setRequest(Request request) {
                        view.setTag(tag, request);
                    }

                    @Override
                    public Request getRequest() {
                        return (Request) view.getTag(tag);
                    }
                });
    }

    @Override
    public void resumeRequests(Context mCxt, int tag) {
        Glide.with(mCxt).resumeRequests();
    }

    @Override
    public void pauseRequests(Context mCxt, int tag) {
        Glide.with(mCxt).pauseRequests();
    }

    @Override
    public ImageView onCreatePreviewItemView(Context context) {
        ImageView photoView = new ImageView(context);
        return photoView;
    }

    @Override
    public void loadPreviewItemView(ImageView view, String imagePath, int width, int height) {
        if (imagePath.contains("http")) {
            Glide.with(view.getContext())
                    .load(imagePath)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE) //缓存原始数据
                    .skipMemoryCache(true)
//                    .override(width, height)
                    .crossFade()
                    .into(view);
        } else {
            Glide.with(view.getContext())
                    .load(Uri.fromFile(new File(imagePath)))
                    .diskCacheStrategy(DiskCacheStrategy.NONE) //不缓存到SD卡
                    .skipMemoryCache(true)
                    .override(width, height)
                    .crossFade()
                    .into(view);
        }
    }
}
