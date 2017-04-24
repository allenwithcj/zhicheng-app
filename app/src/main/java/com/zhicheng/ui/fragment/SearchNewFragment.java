package com.zhicheng.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.SearchPresenterImpl;
import com.zhicheng.api.view.SearchView;
import com.zhicheng.bean.http.SearchBaoClassifyResponse;
import com.zhicheng.bean.http.SearchResponse;
import com.zhicheng.bean.json.CaseItemRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by Donson on 2017/1/17.
 */

public class SearchNewFragment extends BaseFragment implements SearchView {

    private SearchPresenterImpl mSearchPresenterImpl;
    private EditText mAutoComplete;
    private ArrayAdapter<String> mAdapter;
    private ListView mListView;
    private OpenFragment mOpenFragment;

    //接口
    public interface OpenFragment {
        void onOpenFragment(SearchNewFragment fragment, String s, ArrayList<String> node);
    }

    public void setOpenFragment(OpenFragment fragment) {
        if (this.mOpenFragment != null) {
            this.mOpenFragment = null;
        }
        this.mOpenFragment = fragment;
    }

    public static SearchNewFragment newInstance(String request) {
        SearchNewFragment fragment = new SearchNewFragment();
        Bundle b = new Bundle();
        b.putString("request", request);
        fragment.setArguments(b);
        return fragment;
    }

    public static SearchNewFragment newInstance() {
        SearchNewFragment fragment = new SearchNewFragment();
        return fragment;
    }

    public static SearchNewFragment newInstance(boolean isShowSearch) {
        SearchNewFragment fragment = new SearchNewFragment();
        Bundle b = new Bundle();
        b.putBoolean("isShowSearch", isShowSearch);
        fragment.setArguments(b);
        return fragment;
    }

    public static SearchNewFragment newInstance(boolean isShowSearch,String f1) {
        SearchNewFragment fragment = new SearchNewFragment();
        Bundle b = new Bundle();
        b.putBoolean("isShowSearch", isShowSearch);
        b.putString("parentPoint",f1);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.z_search_view, container, false);
    }

    @Override
    protected void initEvents() {
        mAutoComplete = (EditText) mRootView.findViewById(R.id.autoText);
        mListView = (ListView) mRootView.findViewById(R.id.searchList);
        mSearchPresenterImpl = new SearchPresenterImpl(this);
        mAdapter = new ArrayAdapter<String>(getContext(), R.layout.text_view);
        mListView.setAdapter(mAdapter);
    }

    @Override
    protected void initData(boolean isSavedNull) {
        if (!getArguments().getBoolean("isShowSearch", true)) {
            if (!getArguments().getString("parentPoint").equals("")){
                mAutoComplete.setVisibility(View.GONE);
                Gson gson = new Gson();
                CaseItemRequest request = new CaseItemRequest();
                CaseItemRequest.IqBean iq = new CaseItemRequest.IqBean();
                CaseItemRequest.IqBean.QueryBean query = new CaseItemRequest.IqBean.QueryBean();
                query.setParentNodeId(getArguments().getString("parentPoint"));
                iq.setNamespace("CaseItemRequest");
                iq.setQuery(query);
                request.setIq(iq);
                mSearchPresenterImpl.SearchBaoClassify(gson.toJson(request));
            }else {
                mAutoComplete.setVisibility(View.GONE);
                Gson gson = new Gson();
                CaseItemRequest request = new CaseItemRequest();
                CaseItemRequest.IqBean iq = new CaseItemRequest.IqBean();
                CaseItemRequest.IqBean.QueryBean query = new CaseItemRequest.IqBean.QueryBean();
                query.setParentNodeId("");
                iq.setNamespace("CaseItemRequest");
                iq.setQuery(query);
                request.setIq(iq);
                mSearchPresenterImpl.SearchBaoClassify(gson.toJson(request));
            }
        }else {

        }
        if (getArguments() != null && getArguments().getBoolean("isShowSearch", true)) {
            Observable.create(subscriber -> {
                mSearchPresenterImpl.getSearchResult(getArguments().getString("request"));
                mAutoComplete.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        subscriber.onNext(s);
                    }
                });
            }).subscribeOn(AndroidSchedulers.mainThread())
                    .debounce(100, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                    .switchMap(new Func1<Object, Observable<String>>() {
                        @Override
                        public Observable<String> call(Object o) {
                            return Observable.just(o.toString());
                        }
                    })
                    .subscribe(new Subscriber<String>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(String s) {
                            mSearchPresenterImpl.getSearchResult(s);
                        }
                    });
            mListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent = new Intent();
                if (!getArguments().getString("request").isEmpty()) {
                    if (getArguments().getString("request", "").equals("communicate")) {
                        intent.setAction("com.search.communicate.result");
                    } else if (getArguments().getString("request", "").equals("grid")) {
                        intent.setAction("com.search.grid.result");
                    }
                }
                intent.putExtra("item", parent.getItemAtPosition(position).toString());
                getActivity().sendBroadcast(intent);
                getActivity().finish();
            });
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(Object result) {
        if (result instanceof SearchResponse) {
            List<String> list = new ArrayList<String>();
            mAdapter.clear();
            for (int i = 0; i < ((SearchResponse) result).getDataList().size(); i++) {
                if (((SearchResponse) result).getDataList().get(i).contains(mAutoComplete.getText().toString())) {
                    list.add(((SearchResponse) result).getDataList().get(i));
                }
            }
            mAdapter.addAll(list);
            mAdapter.notifyDataSetChanged();
        } else if (result instanceof SearchBaoClassifyResponse) {
            List<String> list = new ArrayList<>();
            List<ArrayList<String>> nodePoint = new ArrayList<>();
            for (SearchBaoClassifyResponse.IqBean.QueryBean.ItemsBean s : ((SearchBaoClassifyResponse) result).getIq().getQuery().getItems()) {
                list.add(s.getSecondName());
                ArrayList<String> node = new ArrayList<>();
                node.add(s.getId());
                node.add(s.getId());
                node.add(s.getFirstPart());
                node.add(s.getMergeCategory());
                node.add(s.getTimeLimit());
                node.add(s.getPLimitUnit());
                node.add(s.getCode());
                node.add(s.getLimitDescribe());
                nodePoint.add(node);
            }
            mAdapter.clear();
            mAdapter.addAll(list);
            mListView.setOnItemClickListener((parent, view, position, id) -> {
                Intent intent=new Intent();
                intent.setAction("com.searchNewFragment.item.result");
                intent.putExtra("item",parent.getItemAtPosition(position).toString());
                intent.putExtra("parentPoint",((SearchBaoClassifyResponse) result).getIq().getQuery().getItems().get(position).getId());
                getActivity().sendBroadcast(intent);
                getActivity().finish();
            });
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mAutoComplete.getWindowToken(), 0);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
