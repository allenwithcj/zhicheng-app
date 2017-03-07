package com.zhicheng.ui.fragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.SearchPresenterImpl;
import com.zhicheng.api.view.SearchView;
import com.zhicheng.bean.http.ClassifyResponse;
import com.zhicheng.bean.http.SearchResponse;
import com.zhicheng.utils.CircleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchClassifyFragment extends BaseFragment implements SearchView{

    private SearchPresenterImpl mSearchPresenterImpl;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private EditText mInput;
    private RecyclerView mainContent;
    //数据集
    private List<ClassifyResponse> SearchResult;
    private SearchClassifyAdapter mSearchClassifyAdapter;
    //侧滑栏


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public static SearchClassifyFragment newInstance(String request){
        SearchClassifyFragment fragment = new SearchClassifyFragment();
        Bundle b = new Bundle();
        b.putString("request",request);
        fragment.setArguments(b);
        return fragment;
    }

    public static SearchClassifyFragment newInstance(){
        SearchClassifyFragment fragment = new SearchClassifyFragment();
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.z_search_classify,container,false);
    }
    @Override
    protected void initEvents() {
        mDrawerLayout = (DrawerLayout) mRootView.findViewById(R.id.drawer_layout);
        mInput = (EditText) mRootView.findViewById(R.id.mInput);
        mainContent = (RecyclerView) mRootView.findViewById(R.id.mRecycleView);
        mSearchPresenterImpl = new SearchPresenterImpl(this);
        SearchResult = new ArrayList<ClassifyResponse>();
        //主内容RecyclerView
        mSearchClassifyAdapter = new SearchClassifyAdapter();
        mainContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mainContent.setAdapter(mSearchClassifyAdapter);
        //侧滑栏内容RecyclerView

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,null,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();
        Map<String,Object> mapQuery = new HashMap<>();
        mapQuery.put("manageStatus","");
        mapQuery.put("caseTime","");
        mapQuery.put("pageNum","1");
        map1.put("namespace","CaseQueryRequest");
        map1.put("query",mapQuery);
        map.put("iq",map1);
        String json = gson.toJson(map);
        mSearchPresenterImpl.SearchBaoClassify(json);
        if (getArguments() != null){
//            mSearchPresenterImpl.getSearchResult(getArguments().getString("request"));
        }

    }

    @Override
    public void showMessage(String msg) {
        Snackbar.make(mRootView,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(Object result) {
        if (result instanceof SearchResponse){
            SearchResult = ((SearchResponse) result).getClassifyResponses();
            mSearchClassifyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mInput.getWindowToken(),0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add("筛选").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("筛选")){
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return true;
    }

    private class SearchClassifyAdapter extends RecyclerView.Adapter{

        public SearchClassifyAdapter(){

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.z_search_classify,parent,false);
            return new SearchClassifyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SearchClassifyViewHolder){
                if (SearchResult.get(position).getCode() == 200){
                    Glide.with(getContext())
                            .load(SearchResult.get(position).getmTitleImage())
                            .into(((SearchClassifyViewHolder) holder).mTitleImg);
                    ((SearchClassifyViewHolder) holder).mDesc.setText(SearchResult.get(position).getmDesc());
                    ((SearchClassifyViewHolder) holder).mOrigin.setText(SearchResult.get(position).getmOrigin());
                    ((SearchClassifyViewHolder) holder).upTime.setText(SearchResult.get(position).getUpTime());
                }
            }
        }

        @Override
        public int getItemCount() {
            return SearchResult.size();
        }

        private class SearchClassifyViewHolder extends RecyclerView.ViewHolder {

            private ImageView mTitleImg;
            private TextView mDesc;
            private TextView mOrigin;
            private TextView upTime;

            public SearchClassifyViewHolder(View itemView) {
                super(itemView);
                mTitleImg = (ImageView) itemView.findViewById(R.id.title_img);
                mDesc = (TextView) itemView.findViewById(R.id.Desc);
                mOrigin = (TextView) itemView.findViewById(R.id.Origin);
                upTime = (TextView) itemView.findViewById(R.id.upTime);
            }
        }
    }
}
