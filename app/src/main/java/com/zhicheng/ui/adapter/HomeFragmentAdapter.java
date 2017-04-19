package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.library.LoopViewPager;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.http.NoticeResponse;
import com.zhicheng.bean.http.OfficialWorkDynamicList;
import com.zhicheng.bean.json.OfficialRequest;
import com.zhicheng.bean.json.PersonalDynamicRequest;
import com.zhicheng.common.URL;
import com.zhicheng.ui.activity.CallTheCounActivity;
import com.zhicheng.ui.activity.CallTheCounDetailActivity;
import com.zhicheng.ui.activity.ExperienceExchangeAddActivity;
import com.zhicheng.ui.activity.ExperienceExchangeLsitActivity;
import com.zhicheng.ui.activity.Official;
import com.zhicheng.ui.activity.OfficialBaseGrid;
import com.zhicheng.ui.activity.OfficialWorkDynamic;
import com.zhicheng.ui.activity.OfficialWorkDynamicDetail;
import com.zhicheng.ui.activity.SearchViewActivity;
import com.zhicheng.ui.activity.WorkNoteActivity;
import com.zhicheng.ui.activity.officialFinished;
import com.zhicheng.utils.common.UIUtils;

import java.util.List;


/**
 * Created by Donson on 2017/1/15.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter implements OfficialView{

    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TODAY = 1;
    private static final int TYPE_MODEL = 2;
    private static final int TYPE_NOTICE = 3;
    private static final int TYPE_WORK = 4;
    private CommonResponse mainResponses;
    private int mNofinish_count;


    private OfficialPresenterImpl mOfficialPresenterImpl;
    private MyNoticeAdapter mMyNoticeAdapter;//通知公告
    private MyWorkAdapter mMyWorkAdapter;//工作动态

    public HomeFragmentAdapter() {
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
    }

    public void setAdapterData(CommonResponse mainResponses) {
        this.mainResponses = mainResponses;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_BANNER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_banner, parent, false);
            return new BannerViewHolder(view);
        } else if (viewType == TYPE_TODAY) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_weektoday, parent, false);
            return new WeekTodayViewHolder(view);
        }else if (viewType == TYPE_MODEL) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_buttongroup, parent, false);
            return new ButtonGroupViewHolder(view);
        } else if (viewType == TYPE_NOTICE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_notice, parent, false);
            return new NoticeViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_work, parent, false);
            return new WorkViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof BannerViewHolder) {

            } else if (holder instanceof WeekTodayViewHolder) {
                if (mainResponses != null) {
                if (mainResponses.getIq().getQuery().getData() != null) {
                    String str = "今日新增案件<font color=#af3428>"
                            + mainResponses.getIq().getQuery().getData().getCaseReportTotal()
                            + "条</font>,网格基础数据<font color=#af3428>"
                            + mainResponses.getIq().getQuery().getData().getGridReportTotal()
                            + "条</font>,本周共办结<font color=#af3428>"
                            + mainResponses.getIq().getQuery().getData().getWeekDoneTotal() + "条</font>案件";
                    ((WeekTodayViewHolder) holder).todayAndWeekThings.setText(Html.fromHtml(str));
                }
                } else {
                    String str = "今日新增案件<font color=#af3428>"
                            + 0 + "条</font>,网格基础数据<font color=#af3428>"
                            + 0 + "条</font>,本周共办结<font color=#af3428>"
                            + 0 + "条</font>案件";
                    ((WeekTodayViewHolder) holder).todayAndWeekThings.setText(Html.fromHtml(str));
                }
            } else if (holder instanceof ButtonGroupViewHolder) {
                if (mNofinish_count != 0) {
                    ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setVisibility(View.VISIBLE);
                    ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setText("" + mNofinish_count);
                } else {
                    ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setVisibility(View.GONE);
                }
            } else if (holder instanceof NoticeViewHolder) {
                //查询通知公告
                OfficialRequest mOfficialRequest = new OfficialRequest();
                OfficialRequest.IqBean iq = new OfficialRequest.IqBean();
                OfficialRequest.IqBean.QueryBean query = new OfficialRequest.IqBean.QueryBean();
                query.setPage("1");
                query.setOrderBy("");
                query.setOrderType("");
                query.setPerPageNums("10");
                query.setRequestType("6");
                iq.setQuery(query);
                iq.setNamespace("ListRequest");
                mOfficialRequest.setIq(iq);
                Gson gson = new Gson();
                String g = gson.toJson(mOfficialRequest);
                mOfficialPresenterImpl.LoadNotice(g);

                ((NoticeViewHolder) holder).notice_more.setOnClickListener(view -> {
                    Intent intent = new Intent(holder.itemView.getContext(),CallTheCounActivity.class);
                    UIUtils.startActivity(intent);
                });

            } else if (holder instanceof WorkViewHolder) {
                //查询工作动态
                PersonalDynamicRequest mPersonalDynamicRequest = new PersonalDynamicRequest();
                PersonalDynamicRequest.IqBean iqb = new PersonalDynamicRequest.IqBean();
                PersonalDynamicRequest.IqBean.QueryBean qb = new PersonalDynamicRequest.IqBean.QueryBean();
                iqb.setNamespace("PersonalDynamicRequest");
                qb.setType("2");
                qb.setPage(1);
                qb.setRow(3);
                iqb.setQuery(qb);
                mPersonalDynamicRequest.setIq(iqb);
                Gson gson = new Gson();
                String g = gson.toJson(mPersonalDynamicRequest);
                mOfficialPresenterImpl.loadDynamic(g, 1);

                ((WorkViewHolder) holder).work_more.setOnClickListener(view -> {
                    Intent intent = new Intent(holder.itemView.getContext(),OfficialWorkDynamic.class);
                    UIUtils.startActivity(intent);
                });
            }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_TODAY;
        }else if (position == 2) {
            return TYPE_MODEL;
        } else if (position == 3) {
            return TYPE_NOTICE;
        } else {
            return TYPE_WORK;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }


    public void setCountDate(int Nofinish_count) {
        this.mNofinish_count = Nofinish_count;
        notifyDataSetChanged();

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void refreshData(Object result) {
        //通知公告返回
        if(result instanceof NoticeResponse){
            if(((NoticeResponse) result).getIq().getQuery().getErrorCode().equals("0")){
               mMyNoticeAdapter.setDataList(((NoticeResponse) result).getIq().getQuery().getTable());
            }
        //工作动态返回
        }else if (result instanceof OfficialWorkDynamicList) {
            if (((OfficialWorkDynamicList) result).getIq().getQuery().getErrorCode().equals("0")) {
                mMyWorkAdapter.setDateList(((OfficialWorkDynamicList) result).getIq().getQuery().getPrelogcon().getPrelogs());
            }
        }
    }

    @Override
    public void addData(Object result) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private LoopViewPager mLoopPager;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mLoopPager = (LoopViewPager) itemView.findViewById(R.id.loopViewPager);
        }
    }


    class ButtonGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView noFinish;
        private TextView fabNotice_nofinish;
        private TextView Finished;
        private TextView FenLei;
        private TextView GridDataBase;
        private TextView Experiment;
        private TextView workNote;


        public ButtonGroupViewHolder(View itemView) {
            super(itemView);
            noFinish = (TextView) itemView.findViewById(R.id.noFinish);
            fabNotice_nofinish = (TextView) itemView.findViewById(R.id.fabNotice_nofinish);
            Finished = (TextView) itemView.findViewById(R.id.Finished);
            FenLei = (TextView) itemView.findViewById(R.id.FenLei);
            GridDataBase = (TextView) itemView.findViewById(R.id.GridDataBase);
            Experiment = (TextView) itemView.findViewById(R.id.Experiment);
            workNote = (TextView) itemView.findViewById(R.id.workNote);

            noFinish.setOnClickListener(this);
            Finished.setOnClickListener(this);
            FenLei.setOnClickListener(this);
            GridDataBase.setOnClickListener(this);
            Experiment.setOnClickListener(this);
            workNote.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.noFinish:
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), Official.class));
                    break;
                case R.id.Finished:
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), officialFinished.class));
                    break;
                case R.id.FenLei:
                    Intent intent = new Intent(UIUtils.getContext(), SearchViewActivity.class);
                    intent.putExtra("fragment", "classify");
                    UIUtils.startActivity(intent);
                    break;
                case R.id.GridDataBase:
                    Intent mIntent = new Intent(UIUtils.getContext(), OfficialBaseGrid.class);
                    UIUtils.startActivity(mIntent);
                    break;
                case R.id.Experiment:
                    UIUtils.startActivity(new Intent(UIUtils.getContext(),ExperienceExchangeLsitActivity.class));
                    break;
                case R.id.workNote:
                    UIUtils.startActivity(new Intent(UIUtils.getContext(), WorkNoteActivity.class));
                    break;
            }
        }
    }

    class WeekTodayViewHolder extends RecyclerView.ViewHolder {

        private TextView todayAndWeekThings;

        public WeekTodayViewHolder(View itemView) {
            super(itemView);
            todayAndWeekThings = (TextView) itemView.findViewById(R.id.todayAndWeekThings);
        }
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView notice_mRecyclerView;
        private TextView notice_more;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            notice_mRecyclerView = (RecyclerView) itemView.findViewById(R.id.notice_mRecycleView);
            notice_more = (TextView) itemView.findViewById(R.id.notice_more);
            notice_mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mMyNoticeAdapter  = new MyNoticeAdapter();
            notice_mRecyclerView.setAdapter(mMyNoticeAdapter);
        }
    }

    class WorkViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView work_mRecyclerView;
        private TextView work_more;

        public WorkViewHolder(View itemView) {
            super(itemView);
            work_mRecyclerView = (RecyclerView) itemView.findViewById(R.id.work_mRecycleView);
            work_more = (TextView) itemView.findViewById(R.id.work_more);
            work_mRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            mMyWorkAdapter = new MyWorkAdapter();
            work_mRecyclerView.setAdapter(mMyWorkAdapter);
        }
    }

    //通知公告
    private class MyNoticeAdapter extends RecyclerView.Adapter<MyNoticeAdapter.NoticeItemViewHolder> {
        private List<List<NoticeResponse.IqBean.QueryBean.TableBean.TableRowsBean>> data;
        public void setDataList(NoticeResponse.IqBean.QueryBean.TableBean table) {
            this.data = table.getTableRows();
            this.notifyDataSetChanged();
        }

        @Override
        public NoticeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_official_notice_item,parent,false);
            return new NoticeItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(NoticeItemViewHolder holder, int position) {
            if(data != null){
                if(data.size() != 0){
                    holder.notice_title.setText(data.get(position).get(4).getValue());
                    holder.notice_time.setText(data.get(position).get(6).getValue());
                    holder.notice_layout.setOnClickListener(view -> {
                        Intent intent = new Intent(holder.itemView.getContext(), CallTheCounDetailActivity.class);
                        intent.putExtra("id", data.get(position).get(0).getValue());
                        UIUtils.startActivity(intent);
                    });
                }
            }

        }

        @Override
        public int getItemCount() {
            if(data != null){
                if(data.size() != 0) {
                    if(data.size() >5){
                        return 5;
                    }else{
                        return  data.size();
                    }
                }
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        class NoticeItemViewHolder extends RecyclerView.ViewHolder{
            private RelativeLayout notice_layout;
            private TextView notice_title;
            private TextView notice_time;

            public NoticeItemViewHolder(View itemView) {
                super(itemView);
                notice_layout = (RelativeLayout) itemView.findViewById(R.id.notice_layout);
                notice_title = (TextView) itemView.findViewById(R.id.notice_title);
                notice_time = (TextView) itemView.findViewById(R.id.notice_time);

            }
        }

    }



    //工作动态
    private class MyWorkAdapter extends RecyclerView.Adapter<MyWorkAdapter.WorkItemViewHolder>{
        private List<OfficialWorkDynamicList.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogs;

        public void setDateList(List<OfficialWorkDynamicList.IqBean.QueryBean.PrelogconBean.PrelogsBean> prelogs) {
            this.prelogs = prelogs;
            this.notifyDataSetChanged();
        }


        @Override
        public WorkItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_official_dynamic_item,parent,false);
            return new WorkItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(WorkItemViewHolder holder, int position) {
            if(prelogs != null){
                if(prelogs.size() != 0){
                    if(prelogs.get(position).getIMG().size() != 0){
                        Glide.with(holder.itemView.getContext())
                                .load(URL.HOST_URL_SERVER_ZHICHENG + prelogs.get(position).getIMG().get(0).getHref())
                                .placeholder(R.drawable.glide_loading)
                                .error(R.drawable.glide_failed)
                                .thumbnail((float) 0.4)
                                .into(holder.work_image);
                    }else{
                        Glide.with(holder.itemView.getContext())
                                .load(R.drawable.glide_failed)
                                .into((holder).work_image);
                    }
                    holder.work_content.setText(prelogs.get(position).getCOUNT());
                    holder.work_address.setText(prelogs.get(position).getLOCATION());
                    holder.work_department.setText(prelogs.get(position).getDEPT());//---------->新增
                    String mTime = prelogs.get(position).getDATETIME();
                    if(mTime.length() != 0 && mTime.length() >2){
                        holder.work_time.setText(mTime.substring(0,mTime.length()-2));
                    }
                    //查看工作动态详情
                    holder.work_layout.setOnClickListener(view -> {
                        Intent intent = new Intent(holder.itemView.getContext(), OfficialWorkDynamicDetail.class);
                        intent.putExtra("id",prelogs.get(position).getID());
                        UIUtils.startActivity(intent);
                    });
                }
            }

        }

        @Override
        public int getItemCount() {
            if(prelogs != null){
                    return prelogs.size();
                }
            return 0;
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        class WorkItemViewHolder extends RecyclerView.ViewHolder{
            private LinearLayout work_layout;
            private ImageView work_image;
            private TextView work_content;
            private TextView work_address;
            private TextView work_time;
            private TextView work_department;//---------->新增

            public WorkItemViewHolder(View itemView) {
                super(itemView);
                work_layout = (LinearLayout) itemView.findViewById(R.id.work_layout);
                work_image = (ImageView) itemView.findViewById(R.id.work_image);
                work_content = (TextView) itemView.findViewById(R.id.work_content);
                work_address = (TextView) itemView.findViewById(R.id.work_address);
                work_time = (TextView) itemView.findViewById(R.id.work_time);
                work_department= (TextView) itemView.findViewById(R.id.work_department);//---------->新增

            }
        }


    }


}
