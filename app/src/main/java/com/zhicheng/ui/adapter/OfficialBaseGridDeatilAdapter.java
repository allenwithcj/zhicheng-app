package com.zhicheng.ui.adapter;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.presenter.OfficialBaseGridUpdatePresenter;
import com.zhicheng.api.presenter.impl.HuZuPresenterImpl;
import com.zhicheng.api.view.HuZuView;
import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.bean.http.PersonMsgResponse;
import com.zhicheng.bean.json.PersonMsgMaRequest;
import com.zhicheng.bean.json.SubmitOrdinaryFormRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.activity.BaseGridAddSelectType;
import com.zhicheng.ui.activity.BaseGridAddSelectTypeMultipleChoice;
import com.zhicheng.ui.activity.HuZuSearchActivity;
import com.zhicheng.utils.CodeUtils;
import com.zhicheng.utils.common.UIUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hp on 2017/3/2.
 *
 */

public class OfficialBaseGridDeatilAdapter extends RecyclerView.Adapter implements HuZuView {
    private OfficialBaseGridDetailResponse mData;
    private String ZZ_RESIDENCE, GRIDNAME;
    private String REMARK2;
    private String CARD_NUM, NAME, RELATION, GENDER, MARITAL_STATUS, POLITICAL_STATUS, EDUCATION,
            HOBBY, REMARK1, DOMICILE, OUTADDRESS, PHONE, WORK, SORT,HUZUNAME,AGE;
    private String HUZUID;
    private OfficialBaseGridUpdatePresenter mOfficialBaseGridUpdatePresenter;
    private ItemViewHolder holder;
    private String[] mList;
    private String type;
    private HuZuPresenterImpl mHuZuPresenterImpl;
    private DatabaseHelper mDatabaseHelper;

    public OfficialBaseGridDeatilAdapter(OfficialBaseGridUpdatePresenter mOfficialBaseGridUpdatePresenter) {
        this.mOfficialBaseGridUpdatePresenter = mOfficialBaseGridUpdatePresenter;
        mHuZuPresenterImpl = new HuZuPresenterImpl(this);
        mDatabaseHelper = new DatabaseHelper();
    }

    public void setData(OfficialBaseGridDetailResponse mData) {
        this.mData = mData;
        this.notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_grid_content_add_detail, parent, false);
        return new ItemViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        this.holder = (ItemViewHolder) holder;
        if (mData != null) {
            if (holder instanceof ItemViewHolder) {

                ((ItemViewHolder) holder).grid_base_add_residence.setText(mData.getIq().getQuery().getPreMsg().getZZ_RESIDENCE());
                ((ItemViewHolder) holder).grid_base_add_grid_no.setText(mData.getIq().getQuery().getPreMsg().getGRIDNAME());

                ((ItemViewHolder) holder).grid_base_add_name.setText(mData.getIq().getQuery().getPreMsg().getNAME());
                ((ItemViewHolder) holder).grid_base_add_relation.setText(CodeUtils.getRelationString(mData.getIq().getQuery().getPreMsg().getRELATION()));
                //本人或户主置灰
                if(((ItemViewHolder) holder).grid_base_add_relation.getText().toString().equals("本人或户主")){
                    ((ItemViewHolder) holder).grid_base_huzu_name.setText(mData.getIq().getQuery().getPreMsg().getHUZU());
                    ((ItemViewHolder) holder).grid_base_huzu_name.setBackgroundColor(UIUtils.getContext().getResources().getColor(R.color.gray_text));
                    ((ItemViewHolder) holder).grid_base_huzu_name.setClickable(false);
                }else{
                    ((ItemViewHolder) holder).grid_base_huzu_name.setBackgroundColor(UIUtils.getContext().getResources().getColor(R.color.white));
                    ((ItemViewHolder) holder).grid_base_huzu_name.setClickable(true);
                }
                ((ItemViewHolder) holder).grid_base_add_sex.setText(CodeUtils.getSexString(mData.getIq().getQuery().getPreMsg().getGENDER()));
                ((ItemViewHolder) holder).grid_base_add_cardid.setText(mData.getIq().getQuery().getPreMsg().getCARD_NUM());
                ((ItemViewHolder) holder).grid_base_add_policatial.setText(mData.getIq().getQuery().getPreMsg().getPOLITICAL_STATUS());
                ((ItemViewHolder) holder).grid_base_add_degree.setText(CodeUtils.getDegreeString(mData.getIq().getQuery().getPreMsg().getEDUCATION()));
                ((ItemViewHolder) holder).grid_base_add_married.setText(CodeUtils.getMarriedString(mData.getIq().getQuery().getPreMsg().getMARITAL_STATUS()));
                ((ItemViewHolder) holder).grid_base_add_brithplace.setText(mData.getIq().getQuery().getPreMsg().getDOMICILE());
                ((ItemViewHolder) holder).grid_base_add_telephone.setText(mData.getIq().getQuery().getPreMsg().getPHONE());
                ((ItemViewHolder) holder).grid_base_add_workspace.setText(mData.getIq().getQuery().getPreMsg().getWORK());
                ((ItemViewHolder) holder).grid_base_add_hobby.setText(mData.getIq().getQuery().getPreMsg().getHOBBY());
                ((ItemViewHolder) holder).grid_base_add_remark1.setText(mData.getIq().getQuery().getPreMsg().getREMARK1());
                ((ItemViewHolder) holder).grid_base_add_remark2.setText(mData.getIq().getQuery().getPreMsg().getREMARK2());
                ((ItemViewHolder) holder).grid_base_add_outaddress.setText(mData.getIq().getQuery().getPreMsg().getOUTADDRESS());
                ((ItemViewHolder) holder).grid_base_add_rkfl.setText(mData.getIq().getQuery().getPreMsg().getSORT());
                if (mData.getIq().getQuery().getPreMsg().getPERSONAGE() != null){
                    ((ItemViewHolder) holder).grid_base_add_age.setText(mData.getIq().getQuery().getPreMsg().getPERSONAGE().substring(0,10));
                }else {
                    ((ItemViewHolder) holder).grid_base_add_age.setText("");
                }

                HUZUID = mData.getIq().getQuery().getPreMsg().getHUZU();
                getHuzuNAme(HUZUID);

            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void update(String id, AlertDialog dialog, String hzID, String latitude, String longitude, String address) {
        ZZ_RESIDENCE = holder.grid_base_add_residence.getText().toString();
        GRIDNAME = holder.grid_base_add_grid_no.getText().toString();

        NAME = holder.grid_base_add_name.getText().toString();
        RELATION = CodeUtils.relationCode(holder.grid_base_add_relation.getText().toString());
        GENDER = CodeUtils.sexCode(holder.grid_base_add_sex.getText().toString());
        CARD_NUM = holder.grid_base_add_cardid.getText().toString();
        POLITICAL_STATUS = holder.grid_base_add_policatial.getText().toString();
        EDUCATION = CodeUtils.degreeCode(holder.grid_base_add_degree.getText().toString());
        MARITAL_STATUS = CodeUtils.marriedCode(holder.grid_base_add_married.getText().toString());
        DOMICILE = holder.grid_base_add_brithplace.getText().toString();
        PHONE = holder.grid_base_add_telephone.getText().toString();
        WORK = holder.grid_base_add_workspace.getText().toString();
        HOBBY = holder.grid_base_add_hobby.getText().toString();
        REMARK1 = holder.grid_base_add_remark1.getText().toString();
        OUTADDRESS = holder.grid_base_add_outaddress.getText().toString();
        SORT = holder.grid_base_add_rkfl.getText().toString();
        AGE = holder.grid_base_add_age.getText().toString();
        HUZUNAME = holder.grid_base_huzu_name.getText().toString();

        REMARK2 = holder.grid_base_add_remark2.getText().toString();

        if(NAME.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_name),dialog);
        }else if(ZZ_RESIDENCE.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_residence),dialog);
        }else if(DOMICILE.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_brithplace),dialog);
        }else if(PHONE.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_telephone),dialog);
        }else if(AGE.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_age),dialog);
        }else if(GENDER.equals("")|| GENDER == null){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_sex),dialog);
        }else if(MARITAL_STATUS.equals("") || MARITAL_STATUS == null){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_married),dialog);
        }else if(EDUCATION.equals("") || EDUCATION == null){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_degree),dialog);
        }else if(POLITICAL_STATUS.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_policatial),dialog);
        }else if(SORT.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_rkfl),dialog);
        }else if(RELATION.equals("")|| RELATION == null){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_relation),dialog);
        }else if(REMARK1.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_remark),dialog);
        }else if(REMARK2.equals("")){
            showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_add_lessor_remark),dialog);
        }else if(HUZUID.equals("")){
            if(!RELATION.equals("0")){
                showMessage(UIUtils.getContext().getResources().getString(R.string.grid_base_huzu_name),dialog);
            }else{
                requestGrid(id,hzID,address,latitude,longitude);
            }
        }else{
            requestGrid(id,hzID,address,latitude,longitude);
        }
    }

    private void requestGrid(String id, String hzID, String address, String latitude, String longitude) {
        SubmitOrdinaryFormRequest mSf = new SubmitOrdinaryFormRequest();
        SubmitOrdinaryFormRequest.IqBean lrIq = new SubmitOrdinaryFormRequest.IqBean();
        SubmitOrdinaryFormRequest.IqBean.QueryBean irIqQB = new SubmitOrdinaryFormRequest.IqBean.QueryBean();
        irIqQB.setType("1");
        SubmitOrdinaryFormRequest.IqBean.QueryBean.Formobj mFormobj = new SubmitOrdinaryFormRequest.IqBean.QueryBean.Formobj();
        mFormobj.setZZ_RESIDENCE(ZZ_RESIDENCE);
        mFormobj.setGRIDNAME(GRIDNAME);

        mFormobj.setRENTNAME("");
        mFormobj.setREMARK2(REMARK2);
        mFormobj.setRENTPHONE("");
        mFormobj.setID(id);
        mFormobj.setNAME(NAME);
        mFormobj.setRELATION(RELATION);
        if (hzID == null) {
            if (RELATION.equals("0")) {
                mFormobj.setHUZU("");
            } else {
                mFormobj.setHUZU(HUZUID);
            }
        } else {
            mFormobj.setHUZU(hzID);
        }
        mFormobj.setGENDER(GENDER);
        mFormobj.setCARD_NUM(CARD_NUM);
        mFormobj.setPOLITICAL_STATUS(POLITICAL_STATUS);
        mFormobj.setEDUCATION(EDUCATION);
        mFormobj.setMARITAL_STATUS(MARITAL_STATUS);
        mFormobj.setDOMICILE(DOMICILE);
        mFormobj.setPHONE(PHONE);
        mFormobj.setWORK(WORK);
        mFormobj.setHOBBY(HOBBY);
        mFormobj.setPERSONAGE(AGE);
        mFormobj.setREMARK1(REMARK1);
        mFormobj.setOUTADDRESS(OUTADDRESS);
        mFormobj.setSORT(SORT);
        if (address != null){
            mFormobj.setBADDRESS(address);
        }else {
            mFormobj.setBADDRESS("");
        }
        mFormobj.setBLONGITUDE(longitude);
        mFormobj.setBLATITUDE(latitude);
        mFormobj.setFLAG("1");
        mFormobj.setHZNAME(HUZUNAME);
        mFormobj.setUSERNAME(mDatabaseHelper.getLocalConfig().getUserName());

        irIqQB.setFormobj(mFormobj);
        lrIq.setNamespace("PersonMsgMaRequest");
        lrIq.setQuery(irIqQB);
        mSf.setIq(lrIq);
        Gson gson = new Gson();
        String json = gson.toJson(mSf);
        mOfficialBaseGridUpdatePresenter.updateDate(json);
    }

    private void showMessage(String msg, Dialog dialog) {
        dialog.dismiss();
        Toast.makeText(UIUtils.getContext(), msg+"不能为空", Toast.LENGTH_SHORT).show();
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
    public void refreshHuZuResponse(Object result) {
        if(result instanceof PersonMsgResponse){
            holder.grid_base_huzu_name.setVisibility(View.VISIBLE);
            if(((PersonMsgResponse) result).getIq().getQuery().getErrorCode().equals("0")){
                holder.grid_base_huzu_name.setText(((PersonMsgResponse) result).getIq().getQuery().getPreMsg().getNAME());
            }
        }
    }

    @Override
    public void loadHuZuResponse(Object result) {

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public EditText grid_base_add_residence;
        public TextView grid_base_add_grid_no;

        public TextView grid_base_add_remark2;

        public EditText grid_base_add_name;
        public TextView grid_base_add_relation;
        public TextView grid_base_huzu_name;
        public TextView grid_base_add_sex;
        public EditText grid_base_add_cardid;
        public TextView grid_base_add_policatial;
        public TextView grid_base_add_degree;
        public TextView grid_base_add_married;
        public EditText grid_base_add_brithplace;
        public EditText grid_base_add_telephone;
        public EditText grid_base_add_workspace;
        public TextView grid_base_add_hobby;
        public TextView grid_base_add_remark1;
        public EditText grid_base_add_outaddress;
        public TextView grid_base_add_rkfl;
        public TextView grid_base_add_age;

        public ItemViewHolder(View itemView) {
            super(itemView);

            grid_base_add_residence = (EditText) itemView.findViewById(R.id.grid_base_add_residence);
            grid_base_add_grid_no = (TextView) itemView.findViewById(R.id.grid_base_add_grid_no);

            grid_base_add_name = (EditText) itemView.findViewById(R.id.grid_base_add_name);
            grid_base_add_relation = (TextView) itemView.findViewById(R.id.input_relation);
            grid_base_huzu_name = (TextView) itemView.findViewById(R.id.grid_base_huzu_name);
            grid_base_add_sex = (TextView) itemView.findViewById(R.id.input_sex);
            grid_base_add_cardid = (EditText) itemView.findViewById(R.id.grid_base_add_cardid);
            grid_base_add_policatial = (TextView) itemView.findViewById(R.id.input_political);
            grid_base_add_degree = (TextView) itemView.findViewById(R.id.input_degree);
            grid_base_add_married = (TextView) itemView.findViewById(R.id.input_married);
            grid_base_add_brithplace = (EditText) itemView.findViewById(R.id.grid_base_add_brithplace);
            grid_base_add_telephone = (EditText) itemView.findViewById(R.id.grid_base_add_telephone);
            grid_base_add_workspace = (EditText) itemView.findViewById(R.id.grid_base_add_workspace);
            grid_base_add_hobby = (TextView) itemView.findViewById(R.id.grid_base_add_hobby);
            grid_base_add_remark1 = (TextView) itemView.findViewById(R.id.grid_base_add_remark1);
            grid_base_add_remark2 = (TextView) itemView.findViewById(R.id.grid_base_add_remark2);
            grid_base_add_outaddress = (EditText) itemView.findViewById(R.id.grid_base_add_outaddress);
            grid_base_add_rkfl = (TextView) itemView.findViewById(R.id.input_rkfl);
            grid_base_add_age = (TextView) itemView.findViewById(R.id.grid_base_add_age);

            grid_base_add_relation.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.relation);
                type = Constant.TYPE_RELATION;
                myIntent(mList, type);
            });

            grid_base_add_sex.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.sex);
                type = Constant.TYPE_SEX;
                myIntent(mList, type);
            });

            grid_base_add_policatial.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.political_status);
                type = Constant.TYPE_POLICATIAL;
                myIntent(mList, type);
            });

            grid_base_add_degree.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.degree_of_educatio);
                type = Constant.TYPE_DEGREE;
                myIntent(mList, type);
            });

            grid_base_add_married.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.married);
                type = Constant.TYPE_MARRIED;
                myIntent(mList, type);
            });

            grid_base_add_rkfl.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.sort);
                type = Constant.TYPE_CLASSIFICATION;
                myIntent(mList, type);
            });

            grid_base_add_remark1.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.remark1);
                type = Constant.TYPE_REMARK1;
                myMultipleIntent(mList, type);
            });

            grid_base_add_remark2.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.remark2);
                type = Constant.TYPE_REMARK2;
                myMultipleIntent(mList, type);
            });

            grid_base_add_hobby.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.grid_hobby);
                type = Constant.TYPE_HOBBY;
                myMultipleIntent(mList, type);
            });

            grid_base_huzu_name.setOnClickListener(view -> {
                UIUtils.startActivity(new Intent(UIUtils.getContext(), HuZuSearchActivity.class));
            });

            grid_base_add_age.setOnClickListener(v -> {
                TimePickerView pvTime = new TimePickerView.Builder(itemView.getContext(), new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {//选中事件回调
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
                        grid_base_add_age.setText(sdf.format(date));
                    }
                }).setType(TimePickerView.Type.YEAR_MONTH_DAY)
                        .setSubmitText("确认")
                        .setCancelText("取消")
                        .setRange(1899,2100)
                        .isDialog(true)
                        .build();
                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
                pvTime.show();
            });

        }
    }

    private void myIntent(String[] mList, String type) {
        Intent intent = new Intent(UIUtils.getContext(), BaseGridAddSelectType.class);
        intent.putExtra("mList", mList);
        intent.putExtra("type", type);
        UIUtils.startActivity(intent);
    }

    private void myMultipleIntent(String[] mList, String type) {
        Intent intent = new Intent(UIUtils.getContext(), BaseGridAddSelectTypeMultipleChoice.class);
        intent.putExtra("mList", mList);
        intent.putExtra("type", type);
        UIUtils.startActivity(intent);
    }

    public void getHuzuNAme(String hzId){
        String strEntity = createObj(hzId);
        mHuZuPresenterImpl.queryHuZuName(strEntity);
    }

    private String createObj(String hzId) {
        Gson gson = new Gson();
        PersonMsgMaRequest mPersonMsgMaRequest = new PersonMsgMaRequest();
        PersonMsgMaRequest.IqBean iqb = new PersonMsgMaRequest.IqBean();
        iqb.setNamespace("PersonMsgMaRequest");
        PersonMsgMaRequest.IqBean.QueryBean qyb = new PersonMsgMaRequest.IqBean.QueryBean();
        qyb.setType("5");
        qyb.setID(hzId);
        iqb.setQuery(qyb);
        mPersonMsgMaRequest.setIq(iqb);
        return gson.toJson(mPersonMsgMaRequest);
    }


}
