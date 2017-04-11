package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.presenter.OfficialBaseGridUpdatePresenter;
import com.zhicheng.bean.http.OfficialBaseGridDetailResponse;
import com.zhicheng.bean.json.SubmitOrdinaryFormRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.activity.BaseGridAddSelectType;
import com.zhicheng.utils.common.UIUtils;

/**
 * Created by hp on 2017/3/2.
 */

public class OfficialBaseGridDeatilAdapter extends RecyclerView.Adapter {
    private OfficialBaseGridDetailResponse mData;
    private String ZZ_RESIDENCE, GRIDNAME;
    private String RENTNAME, REMARK2, RENTPHONE;
    private String CARD_NUM, NAME, RELATION, HUZU, GENDER, MARITAL_STATUS, POLITICAL_STATUS, EDUCATION,
            HOBBY, REMARK1, DOMICILE, OUTADDRESS, PHONE, WORK, SORT;
    private OfficialBaseGridUpdatePresenter mOfficialBaseGridUpdatePresenter;
    private ItemViewHolder holder;
    private String[] mList;
    private String type;

    public OfficialBaseGridDeatilAdapter(OfficialBaseGridUpdatePresenter mOfficialBaseGridUpdatePresenter) {
        this.mOfficialBaseGridUpdatePresenter = mOfficialBaseGridUpdatePresenter;
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
                ((ItemViewHolder) holder).grid_base_add_lessor.setText(mData.getIq().getQuery().getPreMsg().getRENTNAME());
                ((ItemViewHolder) holder).grid_base_add_lessor_remark.setText(mData.getIq().getQuery().getPreMsg().getREMARK2());
                ((ItemViewHolder) holder).grid_base_add_lessor_telephone.setText(mData.getIq().getQuery().getPreMsg().getRENTPHONE());
                ((ItemViewHolder) holder).grid_base_add_name.setText(mData.getIq().getQuery().getPreMsg().getNAME());
                ((ItemViewHolder) holder).grid_base_add_relation.setText(mData.getIq().getQuery().getPreMsg().getRENTPHONE());
                ((ItemViewHolder) holder).grid_base_huzu_name.setText(mData.getIq().getQuery().getPreMsg().getHUZU());
                ((ItemViewHolder) holder).grid_base_add_sex.setText(getSexString(mData.getIq().getQuery().getPreMsg().getGENDER()));
                ((ItemViewHolder) holder).grid_base_add_cardid.setText(mData.getIq().getQuery().getPreMsg().getCARD_NUM());
                ((ItemViewHolder) holder).grid_base_add_policatial.setText(mData.getIq().getQuery().getPreMsg().getPOLITICAL_STATUS());
                ((ItemViewHolder) holder).grid_base_add_degree.setText(mData.getIq().getQuery().getPreMsg().getEDUCATION());
                ((ItemViewHolder) holder).grid_base_add_married.setText(getMarriedString(mData.getIq().getQuery().getPreMsg().getMARITAL_STATUS()));
                ((ItemViewHolder) holder).grid_base_add_brithplace.setText(mData.getIq().getQuery().getPreMsg().getDOMICILE());
                ((ItemViewHolder) holder).grid_base_add_telephone.setText(mData.getIq().getQuery().getPreMsg().getPHONE());
                ((ItemViewHolder) holder).grid_base_add_workspace.setText(mData.getIq().getQuery().getPreMsg().getWORK());
                ((ItemViewHolder) holder).grid_base_add_hobby.setText(mData.getIq().getQuery().getPreMsg().getHOBBY());
                ((ItemViewHolder) holder).grid_base_add_remark.setText(mData.getIq().getQuery().getPreMsg().getREMARK1());
                ((ItemViewHolder) holder).grid_base_add_outaddress.setText(mData.getIq().getQuery().getPreMsg().getOUTADDRESS());
                ((ItemViewHolder) holder).grid_base_add_rkfl.setText(mData.getIq().getQuery().getPreMsg().getSORT());
            }
        }
    }

    private String getSexString(String gender) {
        if (gender != null) {
            if (gender.equals("0")) {
                return "男";
            } else {
                return "女";
            }
        } else {
            return null;
        }
    }

    private String getMarriedString(String marital_status) {
        if (marital_status != null) {
            if (marital_status.equals("0")) {
                return "未婚";
            } else if (marital_status.equals("1")) {
                return "已婚";
            } else if (marital_status.equals("2")) {
                return "离婚";
            } else {
                return "丧偶";
            }
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void update(String id, AlertDialog dialog) {
        ZZ_RESIDENCE = holder.grid_base_add_residence.getText().toString();
        GRIDNAME = holder.grid_base_add_grid_no.getText().toString();

        NAME = holder.grid_base_add_name.getText().toString();
        RELATION = holder.grid_base_add_relation.getText().toString();
        GENDER = sexCode(holder.grid_base_add_sex.getText().toString());
        HUZU = holder.grid_base_huzu_name.getText().toString();
        CARD_NUM = holder.grid_base_add_cardid.getText().toString();
        POLITICAL_STATUS = holder.grid_base_add_policatial.getText().toString();
        EDUCATION = holder.grid_base_add_degree.getText().toString();
        MARITAL_STATUS = marriedCode(holder.grid_base_add_married.getText().toString());
        DOMICILE = holder.grid_base_add_brithplace.getText().toString();
        PHONE = holder.grid_base_add_telephone.getText().toString();
        WORK = holder.grid_base_add_workspace.getText().toString();
        HOBBY = holder.grid_base_add_hobby.getText().toString();
        REMARK1 = holder.grid_base_add_remark.getText().toString();
        OUTADDRESS = holder.grid_base_add_outaddress.getText().toString();
        SORT = holder.grid_base_add_rkfl.getText().toString();

        RENTNAME = holder.grid_base_add_lessor.getText().toString();
        REMARK2 = holder.grid_base_add_lessor_remark.getText().toString();
        RENTPHONE = holder.grid_base_add_lessor_telephone.getText().toString();

        SubmitOrdinaryFormRequest mSf = new SubmitOrdinaryFormRequest();
        SubmitOrdinaryFormRequest.IqBean lrIq = new SubmitOrdinaryFormRequest.IqBean();
        SubmitOrdinaryFormRequest.IqBean.QueryBean irIqQB = new SubmitOrdinaryFormRequest.IqBean.QueryBean();
        irIqQB.setType("1");
        SubmitOrdinaryFormRequest.IqBean.QueryBean.Formobj mFormobj = new SubmitOrdinaryFormRequest.IqBean.QueryBean.Formobj();
        mFormobj.setZZ_RESIDENCE(ZZ_RESIDENCE);
        mFormobj.setGRIDNAME(GRIDNAME);

        mFormobj.setRENTNAME(RENTNAME);
        mFormobj.setREMARK2(REMARK2);
        mFormobj.setRENTPHONE(RENTPHONE);

        mFormobj.setID(id);
        mFormobj.setNAME(NAME);
        mFormobj.setRELATION(RELATION);
        mFormobj.setHUZU(HUZU);
        mFormobj.setGENDER(GENDER);
        mFormobj.setCARD_NUM(CARD_NUM);
        mFormobj.setPOLITICAL_STATUS(POLITICAL_STATUS);
        mFormobj.setEDUCATION(EDUCATION);
        mFormobj.setMARITAL_STATUS(MARITAL_STATUS);
        mFormobj.setDOMICILE(DOMICILE);
        mFormobj.setPHONE(PHONE);
        mFormobj.setWORK(WORK);
        mFormobj.setHOBBY(HOBBY);
        mFormobj.setREMARK1(REMARK1);
        mFormobj.setOUTADDRESS(OUTADDRESS);

        irIqQB.setFormobj(mFormobj);
        lrIq.setNamespace("PersonMsgMaRequest");
        lrIq.setQuery(irIqQB);
        mSf.setIq(lrIq);
        Gson gson = new Gson();
        String json = gson.toJson(mSf);
        mOfficialBaseGridUpdatePresenter.updateDate(json);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public EditText grid_base_add_residence;
        public TextView grid_base_add_grid_no;

        public EditText grid_base_add_lessor;
        public TextView grid_base_add_lessor_remark;
        public EditText grid_base_add_lessor_telephone;

        public EditText grid_base_add_name;
        public EditText grid_base_add_relation;
        private EditText grid_base_huzu_name;
        public TextView grid_base_add_sex;
        public EditText grid_base_add_cardid;
        public TextView grid_base_add_policatial;
        public TextView grid_base_add_degree;
        public TextView grid_base_add_married;
        public EditText grid_base_add_brithplace;
        public EditText grid_base_add_telephone;
        public EditText grid_base_add_workspace;
        public EditText grid_base_add_hobby;
        public EditText grid_base_add_remark;
        public EditText grid_base_add_outaddress;
        public TextView grid_base_add_rkfl;

        public ItemViewHolder(View itemView) {
            super(itemView);
            grid_base_add_lessor = (EditText) itemView.findViewById(R.id.grid_base_add_lessor);
            grid_base_add_lessor_remark = (TextView) itemView.findViewById(R.id.grid_base_add_lessor_remark);
            grid_base_add_lessor_telephone = (EditText) itemView.findViewById(R.id.grid_base_add_lessor_telephone);

            grid_base_add_residence = (EditText) itemView.findViewById(R.id.grid_base_add_residence);
            grid_base_add_grid_no = (TextView) itemView.findViewById(R.id.grid_base_add_grid_no);

            grid_base_add_name = (EditText) itemView.findViewById(R.id.grid_base_add_name);
            grid_base_add_relation = (EditText) itemView.findViewById(R.id.grid_base_add_relation);
            grid_base_huzu_name = (EditText) itemView.findViewById(R.id.grid_base_huzu_name);
            grid_base_add_sex = (TextView) itemView.findViewById(R.id.input_sex);
            grid_base_add_cardid = (EditText) itemView.findViewById(R.id.grid_base_add_cardid);
            grid_base_add_policatial = (TextView) itemView.findViewById(R.id.input_political);
            grid_base_add_degree = (TextView) itemView.findViewById(R.id.input_degree);
            grid_base_add_married = (TextView) itemView.findViewById(R.id.input_married);
            grid_base_add_brithplace = (EditText) itemView.findViewById(R.id.grid_base_add_brithplace);
            grid_base_add_telephone = (EditText) itemView.findViewById(R.id.grid_base_add_telephone);
            grid_base_add_workspace = (EditText) itemView.findViewById(R.id.grid_base_add_workspace);
            grid_base_add_hobby = (EditText) itemView.findViewById(R.id.grid_base_add_hobby);
            grid_base_add_remark = (EditText) itemView.findViewById(R.id.grid_base_add_remark);
            grid_base_add_outaddress = (EditText) itemView.findViewById(R.id.grid_base_add_outaddress);
            grid_base_add_rkfl = (TextView) itemView.findViewById(R.id.input_rkfl);

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
                mList = UIUtils.getContext().getResources().getStringArray(R.array.classification);
                type = Constant.TYPE_CLASSIFICATION;
                myIntent(mList, type);
            });

            grid_base_add_lessor_remark.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.remark);
                type = Constant.TYPE_REMARK;
                myIntent(mList, type);
            });

        }
    }

    private void myIntent(String[] mList, String type) {
        Intent intent = new Intent(UIUtils.getContext(), BaseGridAddSelectType.class);
        intent.putExtra("mList", mList);
        intent.putExtra("type", type);
        UIUtils.startActivity(intent);
    }

    private String sexCode(String str) {
        if (str.equals("男")) {
            return "0";
        } else if (str.equals("女")) {
            return "1";
        }
        return null;
    }

    private String marriedCode(String str) {
        if (str.equals("未婚")) {
            return "0";
        } else if (str.equals("已婚")) {
            return "1";
        } else if (str.equals("离婚")) {
            return "2";
        } else if (str.equals("丧偶")) {
            return "3";
        }
        return null;
    }

}
