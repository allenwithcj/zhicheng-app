package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.library.LoopContentPager;
import com.library.LoopViewPager;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.common.URL;
import com.zhicheng.ui.activity.CallTheCounActivity;
import com.zhicheng.ui.activity.Official;
import com.zhicheng.ui.activity.OfficialBaseGrid;
import com.zhicheng.ui.activity.OfficialWorkDynamic;
import com.zhicheng.ui.activity.SearchViewActivity;
import com.zhicheng.ui.activity.WorkNoteActivity;
import com.zhicheng.ui.activity.officialFinished;
import com.zhicheng.utils.common.UIUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Donson on 2017/1/15.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private static final int TYPE_BANNER = 0;
    private static final int TYPE_BAOLIAO = 1;
    private static final int TYPE_TODAY = 2;
    private static final int TYPE_MODEL = 3;
    private DatabaseHelper mData;
    private CommonResponse mainResponses;

    public HomeFragmentAdapter(){
        mData = new DatabaseHelper();
    }

    public void setAdapterData(CommonResponse mainResponses){
        this.mainResponses = mainResponses;
        this.notifyDataSetChanged();
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_BANNER){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_banner,parent,false);
            return new BannerViewHolder(view);
        }else if (viewType == TYPE_TODAY){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_weektoday,parent,false);
            return new WeekTodayViewHolder(view);
        }else if (viewType == TYPE_BAOLIAO){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_toplist,parent,false);
            return new TopListViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_home_buttongroup,parent,false);
            return new ButtonGroupViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (mainResponses != null){
            if (holder instanceof BannerViewHolder){

            }else if(holder instanceof WeekTodayViewHolder){
                if (mainResponses.getIq().getQuery().getData() != null){
                    String str = "今日新增案件<font color=#af3428>"+mainResponses.getIq().getQuery().getData().getCaseReportTotal()+"条</font>,网格基础数据<font color=#af3428>"+mainResponses.getIq().getQuery().getData().getGridReportTotal()+"条</font>,本周共办结<font color=#af3428>"+mainResponses.getIq().getQuery().getData().getWeekDoneTotal()+"条</font>案件";
                    ((WeekTodayViewHolder) holder).todayAndWeekThings.setText(Html.fromHtml(str));
                }else {
                    String str = "今日新增案件<font color=#af3428>"+0+"条</font>,网格基础数据<font color=#af3428>"+0+"条</font>,本周共办结<font color=#af3428>"+0+"条</font>案件";
                    ((WeekTodayViewHolder) holder).todayAndWeekThings.setText(Html.fromHtml(str));
                }
            }else if (holder instanceof TopListViewHolder){
                if (mainResponses.getIq().getQuery().getData() != null){
                    List<String> img = new ArrayList<>();
                    List<String> content = new ArrayList<>();
                    List<String> location = new ArrayList<>();
                    List<String> time = new ArrayList<>();
                    for (CommonResponse.IqBean.QueryBean.DataBean.CasesBean c : mainResponses.getIq().getQuery().getData().getCases()){
                        img.add(URL.HOST_URL_SERVER_ZHICHENG + c.getAttachments().get(0).getHref());
                        content.add(c.getItemcon());
                        location.add(c.getItemaddress());
                        if(c.getItemtime() !=null){
                            time.add(c.getItemtime().substring(0,c.getItemtime().length()-2));
                        }
                    }
                    ((TopListViewHolder) holder).mLoopViewPager.setDataViewList(img,content,location,time);
                }
            }else if (holder instanceof ButtonGroupViewHolder){
                if (mainResponses.getIq().getQuery().getData() != null){
                    if (mainResponses.getIq().getQuery().getData().getDaiBanTotal() != 0){
                        ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setVisibility(View.VISIBLE);
                        ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setText(""+mainResponses.getIq().getQuery().getData().getDaiBanTotal());
                    }else {
                        ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setVisibility(View.GONE);
                    }
                    if(mainResponses.getIq().getQuery().getData().getNoticeTotal() != 0){
                        ((ButtonGroupViewHolder) holder).fabNotice_notice.setVisibility(View.VISIBLE);
                        ((ButtonGroupViewHolder) holder).fabNotice_notice.setText(""+mainResponses.getIq().getQuery().getData().getNoticeTotal());
                    }else{
                        ((ButtonGroupViewHolder) holder).fabNotice_notice.setVisibility(View.GONE);
                    }
                }else {
                    ((ButtonGroupViewHolder) holder).fabNotice_nofinish.setVisibility(View.GONE);
                    ((ButtonGroupViewHolder) holder).fabNotice_notice.setVisibility(View.GONE);
                }
                ((ButtonGroupViewHolder) holder).noFinish.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).Finished.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).FenLei.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).GridDataBase.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).work.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).Experiment.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).CallTheCoun.setOnClickListener(this);
                ((ButtonGroupViewHolder) holder).workNote.setOnClickListener(this);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_BANNER;
        }else if(position == 1){
            return TYPE_TODAY;
        }else if(position == 2){
            return TYPE_BAOLIAO;
        }else{
            return TYPE_MODEL;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.noFinish:
                UIUtils.startActivity(new Intent(UIUtils.getContext(),Official.class));
                break;
            case R.id.Finished:
                UIUtils.startActivity(new Intent(UIUtils.getContext(), officialFinished.class));
                break;
            case R.id.FenLei:
                Intent intent = new Intent(UIUtils.getContext(),SearchViewActivity.class);
                intent.putExtra("fragment","classify");
                UIUtils.startActivity(intent);
                break;
            case R.id.GridDataBase:
                if(mData.getLocalConfig() != null){
                    if(mData.getLocalConfig().getUserPost().contains("网格员")){
                        UIUtils.startActivity(new Intent(UIUtils.getContext(), OfficialBaseGrid.class));
                    }else{
                        Toast.makeText(UIUtils.getContext(),"非网格员无此功能操作权限",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.work:
                UIUtils.startActivity(new Intent(UIUtils.getContext(),OfficialWorkDynamic.class));
                break;
            case R.id.Experiment:
                break;
            case R.id.CallTheCoun:
                UIUtils.startActivity(new Intent(UIUtils.getContext(), CallTheCounActivity.class));
                break;
            case R.id.workNote:
                UIUtils.startActivity(new Intent(UIUtils.getContext(), WorkNoteActivity.class));
                break;
        }
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        private LoopViewPager mLoopPager;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mLoopPager = (LoopViewPager) itemView.findViewById(R.id.loopViewPager);
        }
    }

    class TopListViewHolder extends RecyclerView.ViewHolder {

        private LoopContentPager mLoopViewPager;

        public TopListViewHolder(View itemView) {
            super(itemView);
            mLoopViewPager = (LoopContentPager) itemView.findViewById(R.id.mLoopContentPager);
        }
    }

    class ButtonGroupViewHolder extends RecyclerView.ViewHolder {

        private TextView noFinish;
        private TextView fabNotice_nofinish;
        private TextView Finished;
        private TextView FenLei;
        private TextView GridDataBase;
        private TextView work;
        private TextView Experiment;
        private TextView CallTheCoun;
        private TextView fabNotice_finished;
        private TextView workNote;
        private TextView fabNotice_work;
        private TextView fabNotice_notice;


        public ButtonGroupViewHolder(View itemView) {
            super(itemView);
            noFinish = (TextView) itemView.findViewById(R.id.noFinish);
            fabNotice_nofinish = (TextView) itemView.findViewById(R.id.fabNotice_nofinish);
            Finished = (TextView) itemView.findViewById(R.id.Finished);
            FenLei = (TextView) itemView.findViewById(R.id.FenLei);
            GridDataBase = (TextView) itemView.findViewById(R.id.GridDataBase);
            work = (TextView) itemView.findViewById(R.id.work);
            Experiment = (TextView) itemView.findViewById(R.id.Experiment);
            CallTheCoun = (TextView) itemView.findViewById(R.id.CallTheCoun);
            fabNotice_finished = (TextView) itemView.findViewById(R.id.fabNotice_finished);
            workNote = (TextView) itemView.findViewById(R.id.workNote);
            fabNotice_notice = (TextView)itemView.findViewById(R.id.fabNotice_notice);
            fabNotice_notice = (TextView)itemView.findViewById(R.id.fabNotice_notice);

        }
    }

    class WeekTodayViewHolder extends RecyclerView.ViewHolder {

        private TextView todayAndWeekThings;

        public WeekTodayViewHolder(View itemView) {
            super(itemView);
            todayAndWeekThings = (TextView) itemView.findViewById(R.id.todayAndWeekThings);
        }
    }
}
