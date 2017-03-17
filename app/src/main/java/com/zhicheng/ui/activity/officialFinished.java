package com.zhicheng.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.zhicheng.R;
import com.zhicheng.ui.fragment.FinishedFragment;
import com.zhicheng.ui.fragment.HandingFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Donson on 2017/2/22.
 */

public class officialFinished extends BaseActivity {
    private String[] titles = {"在办", "已办结"};
    private Fragment handingFragment, finishedFragment;
    private List<Fragment> fragmentList;
    private List<String> mTitles;
    private MyFragmentPagerAdapter adapter;
    private TabLayout mFragmentTabItem;
    private ViewPager mViewPager;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main_official_fragment);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
        mFragmentTabItem = (TabLayout)findViewById(R.id.fragment_tab_item);
        mViewPager = (ViewPager)findViewById(R.id.view_pager);
    }

    @Override
    protected void initData() {
        handingFragment = new HandingFragment();
        finishedFragment = new FinishedFragment();

        fragmentList = new ArrayList<>();
        fragmentList.add(handingFragment);
        fragmentList.add(finishedFragment);

        mTitles = Arrays.asList(titles);
        //设置滑动
        mFragmentTabItem.setTabMode(TabLayout.MODE_FIXED);
        for(String title : titles){
            mFragmentTabItem.addTab(mFragmentTabItem.newTab().setText(title));
        }
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList,mTitles);
        mViewPager.setAdapter(adapter);
        mFragmentTabItem.setupWithViewPager(mViewPager);

    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragmentList;
        private List<String> mTitles;


        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
            super(fm);
            this.fragmentList = fragmentList;
            this.mTitles = mTitles;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position % mTitles.size());
        }
    }

}
