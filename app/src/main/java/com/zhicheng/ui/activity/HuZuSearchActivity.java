package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.HuZuPresenterImpl;
import com.zhicheng.api.view.HuZuView;
import com.zhicheng.bean.http.PersonMsgMaResponse;
import com.zhicheng.bean.json.PersonMsgMaRequest;
import com.zhicheng.bean.json.PersonQueryRequest;
import com.zhicheng.utils.ClearEditText;

import java.util.List;

public class HuZuSearchActivity extends BaseActivity implements HuZuView,SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView title_name;
    private ClearEditText mClearEditText;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TextView search_count;
    private MyHuzuAdapter mAdapter;
    private AlertDialog dialog;
    private HuZuPresenterImpl mHuZuPresenterImpl;
    private int start = 1;
    private int page = 1;
    private boolean isSearch = false;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_hu_zu_search);
        mHuZuPresenterImpl = new HuZuPresenterImpl(this);
        title_name = (TextView) findViewById(R.id.title_name);
        mRecyclerView = (RecyclerView)findViewById(R.id.mRecycleView);
        mClearEditText = (ClearEditText)findViewById(R.id.mClearEditText);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        search_count = (TextView)findViewById(R.id.search_count);
        mAdapter = new MyHuzuAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewScrollDetector());
        mSwipeRefreshLayout.setOnRefreshListener(this);
        title_name.setText(getResources().getString(R.string.hz_search));

        mClearEditText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        mClearEditText.setImeActionLabel(getResources().getString(R.string.search), EditorInfo.IME_ACTION_SEND);
        mClearEditText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        mClearEditText.setSingleLine(false);
        mClearEditText.setMaxLines(5);
        mClearEditText.setHorizontallyScrolling(false);
        mClearEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mClearEditText.getWindowToken(), 0);
                dialog = new AlertDialog.Builder(this, R.style.dialog)
                        .setView(R.layout.z_loading_view)
                        .setCancelable(false)
                        .create();
                dialog.show();

                if (mClearEditText.getText().toString().isEmpty()) {
                    showMessage(getResources().getString(R.string.hint_hz_search));
                } else {
                    isSearch = true;
                    page = 1;
                    queryHuzu(page);
                    page ++;

                }
                return true;
            }
            return false;
        });

        mClearEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()){
                    isSearch = false;
                    onRefresh();
                }
            }
        });
    }

    private void queryHuzu(int page) {
        PersonQueryRequest mPersonQueryRequest = new PersonQueryRequest();
        PersonQueryRequest.IqBean iqb = new PersonQueryRequest.IqBean();
        PersonQueryRequest.IqBean.QueryBean qb = new PersonQueryRequest.IqBean.QueryBean();
        iqb.setNamespace("PersonMsgMaRequest");
        qb.setType("7");
        qb.setPagenum(page);
        qb.setPmastkey(mClearEditText.getText().toString());
        iqb.setQuery(qb);
        mPersonQueryRequest.setIq(iqb);
        Gson gson = new Gson();
        mHuZuPresenterImpl.fuzzyQueryHuZuName(gson.toJson(mPersonQueryRequest),page);
    }

    @Override
    protected void initData() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        start = 1;
        page = 1;
        if(isSearch){
            queryHuzu(page);
            page ++;
        }else{
            search_count.setVisibility(View.GONE);
            String strEntity = createObj(start);
            mHuZuPresenterImpl.queryHuZu(strEntity, start);
            start++;
        }
    }

    private String createObj(int start) {
        Gson gson = new Gson();
        PersonMsgMaRequest mPersonMsgMaRequest = new PersonMsgMaRequest();
        PersonMsgMaRequest.IqBean iqb = new PersonMsgMaRequest.IqBean();
        iqb.setNamespace("PersonMsgMaRequest");
        PersonMsgMaRequest.IqBean.QueryBean qyb = new PersonMsgMaRequest.IqBean.QueryBean();
        qyb.setType("6");
        qyb.setRow("10");
        qyb.setPage(String.valueOf(start));
        iqb.setQuery(qyb);
        mPersonMsgMaRequest.setIq(iqb);
        return gson.toJson(mPersonMsgMaRequest);
    }

    @Override
    protected int getMenuID() {
        return super.getMenuID();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void showMessage(String msg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.post(() -> mSwipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void refreshHuZuResponse(Object result) {
        if(result instanceof PersonMsgMaResponse){
            if(((PersonMsgMaResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                if(isSearch){
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    int num = ((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getAllnum();
                    if(num != 0){
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        String str = "共搜索房主/户主信息<font color=#af3428>"+ num + "条</font>";
                        search_count.setVisibility(View.VISIBLE);
                        search_count.setText(Html.fromHtml(str));
                    }else{
                        showMessage(getResources().getString(R.string.no_data));
                    }
                }
                mAdapter.setDataList(((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }else{
                showMessage(((PersonMsgMaResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    @Override
    public void loadHuZuResponse(Object result) {
        if(result instanceof PersonMsgMaResponse){
            if(((PersonMsgMaResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                mAdapter.addDataList(((PersonMsgMaResponse) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }else{
                showMessage(((PersonMsgMaResponse) result).getIq().getQuery().getErrorMessage());
            }
        }
    }

    class MyHuzuAdapter extends RecyclerView.Adapter{
        private String[] str = {"户主姓名:","地址:"};
        private List<PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogs;

        public void setDataList(List<PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogs) {
            this.prelogs = prelogs;
            notifyDataSetChanged();
        }

        public void addDataList(List<PersonMsgMaResponse.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogs) {
            int page = this.prelogs.size();
            this.prelogs.addAll(prelogs);
            this.notifyItemRangeInserted(page, prelogs.size());
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_huzu_item,parent,false);
            return new MyHuzuViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof MyHuzuViewHolder){
                if(isSearch){
                    ((MyHuzuViewHolder) holder).hz_name.setText(str[0]+prelogs.get(position).getHZNAME());
                }else{
                    ((MyHuzuViewHolder) holder).hz_name.setText(str[0]+prelogs.get(position).getNAME());
                }
                ((MyHuzuViewHolder) holder).hz_address.setText(str[1]+prelogs.get(position).getZZ_RESIDENCE());
                ((MyHuzuViewHolder) holder).hz_layout.setOnClickListener(view -> {
                    Intent intent = new Intent();
                    intent.setAction("com.grid.huzu");
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("value",prelogs.get(position));
                    intent.putExtras(bundle);
                    intent.putExtra("type",isSearch);
                    sendBroadcast(intent);
                    finish();
                });
            }
        }

        @Override
        public int getItemCount() {
            return prelogs == null ? 0:prelogs.size();
        }



    }

    class MyHuzuViewHolder extends RecyclerView.ViewHolder{
        private TextView hz_name;
        private TextView hz_address;
        private LinearLayout hz_layout;

        public MyHuzuViewHolder(View itemView) {
            super(itemView);
            hz_name = (TextView) itemView.findViewById(R.id.hz_name);
            hz_address = (TextView) itemView.findViewById(R.id.hz_address);
            hz_layout = (LinearLayout)itemView.findViewById(R.id.hz_layout);
        }
    }

    public class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
        private int lastVisibleItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                onLoadMore();
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
        }
    }

    private void onLoadMore() {
        if(isSearch){
           queryHuzu(page);
            page ++;
        }else{
            String strEntity = createObj(start);
            mHuZuPresenterImpl.queryHuZu(strEntity, start);
            start++;
        }

    }

}
