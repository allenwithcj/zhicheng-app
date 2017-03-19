package com.library;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Donson on 2017/1/16.
 */
//无限轮播功能
public class LoopViewPager extends FrameLayout {

    private static final int DataNum = 3;//数据个数
    private static final int UPDATE_IMAGE = 1;//整型常量，消息处理时用
    private static final int PAUSE = 0;
    private static final int CHANGE_DURATION = 3000;//时间间隔 3秒
    private boolean isBanner = true;//是否滚动

    private LinearLayout mLinearLayout;

    private ViewPager mViewPager;
    private mPagerAdapter adapter;
    private List<View> images;//3张图
    private List<View> dots;//点
    private imageHandler mHandler = new imageHandler();//异步消息处理的方法

    public LoopViewPager(Context context) {
        this(context,null);
    }

    public LoopViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化数据
    private void init(){
        mViewPager = new ViewPager(getContext());
        ViewGroup.LayoutParams params = new ViewPager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        mViewPager.setLayoutParams(params);
        controlViewPagerSpeed();//控制速度方法
        addView(mViewPager);
        //对Linearlayout的一些设置
        mLinearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        mLinearLayout.setLayoutParams(lp);
        mLinearLayout.setPadding(10,10,10,10);
        mLinearLayout.setGravity(Gravity.RIGHT);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(mLinearLayout);
        images = new ArrayList<View>();
        dots = new ArrayList<View>();
        if (isBanner){
            setImages(images);
            setDots(dots);
        }else {
            setContentImages(images);
        }
        adapter = new mPagerAdapter();

        initEvents();
    }

    private void controlViewPagerSpeed(){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixScroller mScroller = new FixScroller(mViewPager.getContext(),new AccelerateInterpolator());
            mScroller.setDuration(500);
            field.set(mViewPager,mScroller);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //轮播中小圆圈的设置
    private void setDots(List<View> data){
        for (int i=0;i<DataNum;i++){
            View view = new ImageView(getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
            params.setMargins(6,0,6,6);
            view.setBackgroundDrawable(getResources().getDrawable(R.drawable.viewpager_dots_normal));
            data.add(view);
            mLinearLayout.addView(view,params);
        }
    }

    //设置图片数据
    public void setImages(List<View> images){
        int[] imgs = {
//                R.drawable.banner_defult,
//                R.drawable.banner2,
//                R.drawable.banner3
                R.drawable.banner_1,
                R.drawable.banner_2,
                R.drawable.banner_3
        };
        for (int i=0;i<DataNum;i++){
            View view = new ImageView(getContext());
            ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
            view.setLayoutParams(params);
            view.setBackgroundResource(imgs[i]);
            images.add(view);
        }
    }

    //设置内容
    public void setContentImages(List<View> images){
        for (int i=0;i<DataNum;i++){
            View view = View.inflate(getContext(),R.layout.viewpager_content,null);
            ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
            view.setLayoutParams(params);
            images.add(view);
        }
    }

    public void setIsBanner(boolean i){
        this.isBanner = i;
    }

    //轮播功能的实现
    private void initEvents(){
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position %= images.size();
                if (position < 0){
                    position += images.size();
                }
                for (View i: dots){
                    i.setBackgroundResource(R.drawable.viewpager_dots_normal);
                }
                dots.get(position).setBackgroundResource(R.drawable.viewpager_dots_select);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mHandler.sendEmptyMessage(PAUSE);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        mHandler.sendEmptyMessageDelayed(UPDATE_IMAGE,CHANGE_DURATION);
                        break;
                }
            }
        });
        mViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mHandler.sendEmptyMessage(PAUSE);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(UPDATE_IMAGE,CHANGE_DURATION);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        mHandler.sendEmptyMessageDelayed(UPDATE_IMAGE,CHANGE_DURATION);
                        break;
                }
                return false;
            }
        });
        mHandler.sendEmptyMessage(UPDATE_IMAGE);
    }

    //页面适配器 将图片位置匹配到viewpager上
    private class mPagerAdapter extends PagerAdapter {

        public mPagerAdapter(){}

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= images.size();
        if (position < 0){
            position = position+images.size();
        }
        ViewParent vp = images.get(position).getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(images.get(position));
        }
        container.addView(images.get(position));
        return images.get(position);
    }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
    }

    //解决子线程中更新UI的问题
    private class imageHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.hasMessages(PAUSE)){
                this.removeMessages(PAUSE);
            }else if (this.hasMessages(UPDATE_IMAGE)){
                this.removeMessages(UPDATE_IMAGE);
            }

            switch (msg.what){
                case PAUSE:
                    this.removeMessages(UPDATE_IMAGE);
                    break;
                case UPDATE_IMAGE:
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1,true);
                    mHandler.sendEmptyMessageDelayed(UPDATE_IMAGE,CHANGE_DURATION);
                    break;
            }
        }
    }
}
