package com.zhicheng.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.ContactPresenterImpl;
import com.zhicheng.api.view.ContactView;
import com.zhicheng.bean.Contacts;
import com.zhicheng.bean.http.AddressBookResponse;
import com.zhicheng.bean.json.AddressBookRequest;
import com.zhicheng.ui.activity.ContactMultilevelActivity;
import com.zhicheng.ui.adapter.ContactAdapter;
import com.zhicheng.utils.ClearEditText;
import com.zhicheng.utils.HanziToPinyin;
import com.zhicheng.utils.OnRecyclerViewListener;
import com.zhicheng.utils.OnTouchingLetterChangedListener;
import com.zhicheng.utils.SideBar;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Donson on 2017/1/15.
 */

public class ContactFragment extends BaseFragment implements ContactView,OnRecyclerViewListener,SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView mRecyclerView;
    private SideBar mSideBar;
    private ClearEditText mClearEditText;
    private TextView mAnno;
    private TextView mNoResult;
    private ContactAdapter mAdapter;
    private ContactPresenterImpl mContactPresenterImpl;
    private LinearLayoutManager mLinearLayoutManager;
    private List<Contacts> contactsList;

    public static ContactFragment newInstance(){
        ContactFragment fragment = new ContactFragment();

        return fragment;
    }

    @Override
    protected void initRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.activity_main_contact,container,false);
    }

    @Override
    protected void initEvents() {
        mContactPresenterImpl = new ContactPresenterImpl(this);
        swipeRefresh = (SwipeRefreshLayout)mRootView.findViewById(R.id.swipeRefresh);
        mRecyclerView = (RecyclerView)mRootView.findViewById(R.id.mRecyclerview);
        mSideBar = (SideBar)mRootView.findViewById(R.id.sideBar);
        mClearEditText = (ClearEditText)mRootView.findViewById(R.id.mClearEditText);
        mAnno = (TextView)mRootView.findViewById(R.id.anno);
        mNoResult = (TextView)mRootView.findViewById(R.id.noResult);
        mSideBar.setmTextDialog(mAnno);

        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ContactAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));
        mAdapter.setOnRecyclerViewListener(this);
        mRecyclerView.setAdapter(mAdapter);
        swipeRefresh.setOnRefreshListener(this);
        mSideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mLinearLayoutManager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                searchData(s.toString());
            }
        });

    }

//    private void searchData(String s) {
//        if(s.isEmpty()){
//            refresh();
//            mNoResult.setVisibility(View.GONE);
//        }else{
//            for(Contacts contacts : contactsList){
//                String[] str = contacts.getPinyin().split(" ");
//                int strLength = str.length;
//                for(int i = 0; i <strLength; i ++ ){
//                    if(str[i].startsWith(s)){
//                        sortContactsList.add(contacts);
//                    }
//                }
//            }
//            if(sortContactsList.size() == 0){
//                mNoResult.setVisibility(View.VISIBLE);
//            }
//        }
//        mAdapter.notifyDataSetChanged();
//    }

    @Override
    protected void initData(boolean isSavedNull) {
        onRefresh();
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgress() {
        swipeRefresh.post(() -> swipeRefresh.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        swipeRefresh.post(() -> swipeRefresh.setRefreshing(false));
    }

    @Override
    public void refreshData(Object result) {
        if(result instanceof AddressBookResponse){
            if(((AddressBookResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                List<AddressBookResponse.IqBean.QueryBean.ItemsBean> itemList = ((AddressBookResponse) result).getIq().getQuery().getItems();
                contactsList = new ArrayList<>();
                contactsList.clear();
                for(AddressBookResponse.IqBean.QueryBean.ItemsBean item: itemList){
                    Contacts mContacts = new Contacts();
                    mContacts.setId(item.getId());
                    mContacts.setName(item.getName());
                    mContacts.setNums(String.valueOf(item.getNums()));
                    mContacts.setNodeNums(item.getNodeNums());
                    mContacts.setType(item.getType());
                    mContacts.setDepartmentName(item.getDepartmentName());
                    mContacts.setImageHref(item.getImageHref());
                    mContacts.setPosition(item.getPosition());
                    mContacts.setTel(item.getTel());
                    mContacts.setPhone(item.getPhone());
                    mContacts.setEmail(item.getEmail());
                    mContacts.setPinyin(hanziTopinyin(item.getName()));
                    contactsList.add(mContacts);
                }
                mAdapter.AddAllDate(contactsList);
            }
        }
    }

    @Override
    public void onItemClick(int position) {
        if(!contactsList.get(position).getType().equals("1")){
            Intent intent = new Intent(getActivity(), ContactMultilevelActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("contacts",contactsList.get(position));
            intent.putExtras(bundle);
            UIUtils.startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(int position) {
        return false;
    }

    public String hanziTopinyin(String hanzi){
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(hanzi);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString().toLowerCase();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        String strEntity = createObj();
        mContactPresenterImpl.loadContacts(strEntity);
    }

    private String createObj() {
        AddressBookRequest mAd = new AddressBookRequest();
        AddressBookRequest.IqBean iqb = new AddressBookRequest.IqBean();
        AddressBookRequest.IqBean.QueryBean qb = new AddressBookRequest.IqBean.QueryBean();
        iqb.setNamespace("AddressBookRequest");
        qb.setFilterType(0);
        qb.setParentItemType(0);
        qb.setParentItemID("");
        qb.setDataSourceType(1);
        qb.setPage("1");
        qb.setPerPageNums("10");
        qb.setOrderBy("");
        qb.setOrderType("");
        qb.setSearchKey("");
        qb.setIsCurrentDept("0");
        qb.setIsRequestAllData("1");
        iqb.setQuery(qb);
        mAd.setIq(iqb);
        Gson gson = new Gson();
        return gson.toJson(mAd);
    }
}
