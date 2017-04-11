package com.zhicheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.Menu;

import com.zhicheng.R;
import com.zhicheng.ui.fragment.SearchClassifyFragment;
import com.zhicheng.ui.fragment.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchViewActivity extends BaseActivity {

    @Override
    protected void initEvents() {
        setContentView(R.layout.z_search_view_activity);
        if (getIntent().getStringExtra("fragment").equals("Search")) {
            SearchFragment searchFragment;
            String isClassify = getIntent().getStringExtra("isClassify");
            if (null != isClassify && isClassify.equals("true")) {
                searchFragment = SearchFragment.newInstance(false, "");
                searchFragment.setOpenFragment((fragment, s, type) -> {
                    SearchFragment newFragment = SearchFragment.newInstance(false, s);
                    newFragment.setOpenFragment((fragment1, s1, type1) -> {
                        openFragment(fragment, newFragment, type1);
                    });
                    openFragment(fragment, newFragment, type);
                });
            } else {
                searchFragment = SearchFragment.newInstance(getIntent().getStringExtra("action"));
            }
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.searchView, searchFragment);
            ft.commit();
        } else {
            SearchClassifyFragment searchClassifyFragment = SearchClassifyFragment.newInstance();
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.searchView, searchClassifyFragment);
            ft.commit();
        }
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {

    }

    private void openFragment(SearchFragment oldFragment, SearchFragment fragment, ArrayList<String> node) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.hide(oldFragment);
            ft.add(R.id.searchView, fragment);
            ft.addToBackStack(null);
            ft.commit();
        } else {
            Intent intent = new Intent();
            intent.setAction("com.search.classify.bao");
            intent.putStringArrayListExtra("node", node);
            this.sendBroadcast(intent);
            this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("搜索");
        return super.onCreateOptionsMenu(menu);
    }


}
