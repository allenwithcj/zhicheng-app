package com.library;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.codeboy.android.aligntextview.CBAlignTextView;


/**
 * Created by Donson on 2017/1/16.
 */

public class LoopContentPager extends FrameLayout {

    private static final int DataNum = 3;
    private static final int UPDATE_IMAGE = 1;
    private static final int PAUSE = 0;
    private static final int CHANGE_DURATION = 3000;

    private LinearLayout mLinearLayout;

    private ViewPager mViewPager;
    private mPagerAdapter adapter;
    private List<View> images;
    private imageHandler mHandler = new imageHandler();
    private DataViewList mDataViewList;
    private ViewHolder mViewHolder;


    public LoopContentPager(Context context) {
        this(context,null);
    }

    public LoopContentPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mViewPager = new ViewPager(getContext());
        ViewGroup.LayoutParams params = new ViewPager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        mViewPager.setLayoutParams(params);
        controlViewPagerSpeed();
        addView(mViewPager);
        mLinearLayout = new LinearLayout(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        mLinearLayout.setLayoutParams(lp);
        mLinearLayout.setPadding(10,10,10,10);
        mLinearLayout.setGravity(Gravity.RIGHT);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(mLinearLayout);
        images = new ArrayList<View>();
        setContentImages(images);
        adapter = new mPagerAdapter();

        initEvents();
    }

    private void setContentImages(List<View> images){
        for (int i=0;i< DataNum;i++){
            View view = LayoutInflater.from(getContext()).inflate(R.layout.viewpager_content,null);
            ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
            view.setLayoutParams(params);
            images.add(view);
        }
    }

    private void initEvents(){
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(Integer.MAX_VALUE/2);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

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
            if (mDataViewList != null){
                if (mViewHolder == null){
                    mViewHolder = new ViewHolder();
                }
                initView(position);
                if(mDataViewList.getImgs().size() == 3){
                    Glide.with(getContext())
                            .load(mDataViewList.getImgs().get(position))
                            .placeholder(R.drawable.welcome)
                            .error(R.drawable.welcome)
                            .centerCrop()
                            .into(mViewHolder.img);
                    mViewHolder.mContent.setText(mDataViewList.getmContents().get(position));
                    mViewHolder.mLocation.setText(mDataViewList.getmLocation().get(position));
                    mViewHolder.mTime.setText(mDataViewList.getmTime().get(position));
                }else if(mDataViewList.getImgs().size() == 2){
                    if(position == 0){
                        Glide.with(getContext())
                                .load(mDataViewList.getImgs().get(0))
                                .placeholder(R.drawable.welcome)
                                .error(R.drawable.welcome)
                                .centerCrop()
                                .into(mViewHolder.img);
                        mViewHolder.mContent.setText(mDataViewList.getmContents().get(0));
                        mViewHolder.mLocation.setText(mDataViewList.getmLocation().get(0));
                        mViewHolder.mTime.setText(mDataViewList.getmTime().get(0));
                    }else if(position == 1){
                        Glide.with(getContext())
                                .load(mDataViewList.getImgs().get(1))
                                .placeholder(R.drawable.welcome)
                                .error(R.drawable.welcome)
                                .centerCrop()
                                .into(mViewHolder.img);
                        mViewHolder.mContent.setText(mDataViewList.getmContents().get(1));
                        mViewHolder.mLocation.setText(mDataViewList.getmLocation().get(1));
                        mViewHolder.mTime.setText(mDataViewList.getmTime().get(1));
                    }
                }else if(mDataViewList.getImgs().size() == 1){
                    Glide.with(getContext())
                            .load(mDataViewList.getImgs().get(0))
                            .placeholder(R.drawable.welcome)
                            .error(R.drawable.welcome)
                            .centerCrop()
                            .into(mViewHolder.img);
                    mViewHolder.mContent.setText(mDataViewList.getmContents().get(0));
                    mViewHolder.mLocation.setText(mDataViewList.getmLocation().get(0));
                    mViewHolder.mTime.setText(mDataViewList.getmTime().get(0));
                }

            }
            container.addView(images.get(position));
            return images.get(position);
        }

        private void initView(final int position){
            mViewHolder.img = (ImageView) images.get(position).findViewById(R.id.img);
            mViewHolder.mContent = (CBAlignTextView) images.get(position).findViewById(R.id.view_content);
            mViewHolder.mLocation = (TextView) images.get(position).findViewById(R.id.location);
            mViewHolder.mTime = (TextView) images.get(position).findViewById(R.id.time);
            mViewHolder.img.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mButtonClick.onButtonClickcallBack(position);
                }
            });

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }
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
    public void setDataViewList(List<String> imgs,List<String> content,List<String> location,List<String> time){
        mDataViewList = new DataViewList(imgs,content,location,time);
        adapter.notifyDataSetChanged();
    }

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
                case MotionEvent.ACTION_CANCEL:
                    mHandler.sendEmptyMessageDelayed(UPDATE_IMAGE,CHANGE_DURATION);
                    break;
            }
        }
    }

    class ViewHolder {
        ImageView img;
        CBAlignTextView mContent;
        TextView mLocation;
        TextView mTime;

        public ViewHolder(){

        }
    }

    class DataViewList{

        private List<String> imgs;
        private List<String> mContents;
        private List<String> mLocation;
        private List<String> mTime;

        public DataViewList(){
        }

        public DataViewList(List<String> imgs,List<String> content,List<String> location,List<String> time){
            this.imgs = imgs;
            this.mContents = content;
            this.mLocation = location;
            this.mTime = time;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        public List<String> getmContents() {
            return mContents;
        }

        public void setmContents(List<String> mContents) {
            this.mContents = mContents;
        }

        public List<String> getmLocation() {
            return mLocation;
        }

        public void setmLocation(List<String> mLocation) {
            this.mLocation = mLocation;
        }

        public List<String> getmTime() {
            return mTime;
        }

        public void setmTime(List<String> mTime) {
            this.mTime = mTime;
        }
    }

    public interface ButtonClick {
        public void onButtonClickcallBack(int position);
    }

    private ButtonClick mButtonClick;

    public void setButtonClick(ButtonClick mButtonClick){
        if (this.mButtonClick == null){
            this.mButtonClick = mButtonClick;
        }
    }

}
