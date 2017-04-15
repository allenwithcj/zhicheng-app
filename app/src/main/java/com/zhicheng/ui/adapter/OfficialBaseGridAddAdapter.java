package com.zhicheng.ui.adapter;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.presenter.OfficialBaseGridAddPresenter;
import com.zhicheng.bean.json.SubmitOrdinaryFormRequest;
import com.zhicheng.common.Constant;
import com.zhicheng.ui.activity.BaseGridAddSelectType;
import com.zhicheng.ui.activity.BaseGridAddSelectTypeMultipleChoice;
import com.zhicheng.ui.activity.OfficialBaseGrid;
import com.zhicheng.utils.CodeUtils;
import com.zhicheng.utils.common.UIUtils;

import java.util.UUID;

/**
 * Created by hp on 2017/3/1.
 */

public class OfficialBaseGridAddAdapter extends RecyclerView.Adapter {
    private DatabaseHelper mDataBase;
    private String ZZ_RESIDENCE, GRIDNAME;
    private String REMARK2;
    private String CARD_NUM, NAME, RELATION, HUZUNAME, GENDER, MARITAL_STATUS, POLITICAL_STATUS, EDUCATION,
            HOBBY, REMARK1, DOMICILE, OUTADDRESS, PHONE, WORK, SORT;

    private OfficialBaseGridAddPresenter mOfficialBaseGridAddPresenter;
    private ItemViewHolder holder;
    private String[] mList;
    private String type;

    public OfficialBaseGridAddAdapter(OfficialBaseGridAddPresenter mOfficialBaseGridAddPresenter) {
        mDataBase = new DatabaseHelper();
        this.mOfficialBaseGridAddPresenter = mOfficialBaseGridAddPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_grid_content_add_detail, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.holder = (ItemViewHolder) holder;
        if (holder instanceof ItemViewHolder) {
            if (mDataBase.getLocalConfig() != null) {
                ((ItemViewHolder) holder).grid_base_add_grid_no.setText(mDataBase.getLocalConfig().getDepartment());
            }
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void submit(AlertDialog dialog, String latitude, String longitude, String address) {

        ZZ_RESIDENCE = holder.grid_base_add_residence.getText().toString();
        GRIDNAME = holder.grid_base_add_grid_no.getText().toString();

        NAME = holder.grid_base_add_name.getText().toString();
        RELATION = CodeUtils.relationCode(holder.grid_base_add_relation.getText().toString());
        HUZUNAME = holder.grid_base_huzu_name.getText().toString();
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

        REMARK2 = holder.grid_base_add_lessor_remark2.getText().toString();

        if (GENDER.equals("") || MARITAL_STATUS.equals("")) {
            dialog.dismiss();
            Toast.makeText(UIUtils.getContext(), "性别和婚姻选项不能为空", Toast.LENGTH_SHORT).show();
        } else {
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

            mFormobj.setID(UUID.randomUUID().toString());
            mFormobj.setNAME(NAME);
            mFormobj.setRELATION(RELATION);
            mFormobj.setHUZU(HUZUNAME);
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
            mFormobj.setSORT(SORT);
            mFormobj.setBADDRESS(address);
            mFormobj.setBLONGITUDE(longitude);
            mFormobj.setBLATITUDE(latitude);

            irIqQB.setFormobj(mFormobj);
            lrIq.setNamespace("PersonMsgMaRequest");
            lrIq.setQuery(irIqQB);
            mSf.setIq(lrIq);
            Gson gson = new Gson();
            String json = gson.toJson(mSf);
            mOfficialBaseGridAddPresenter.addDate(json);
        }
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public EditText grid_base_add_residence;
        public TextView grid_base_add_grid_no;

        public TextView grid_base_add_lessor_remark2;

        public EditText grid_base_add_name;
        public TextView grid_base_add_relation;
        public EditText grid_base_huzu_name;
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

        public ItemViewHolder(View itemView) {
            super(itemView);
            grid_base_add_lessor_remark2 = (TextView) itemView.findViewById(R.id.grid_base_add_lessor_remark2);

            grid_base_add_residence = (EditText) itemView.findViewById(R.id.grid_base_add_residence);
            grid_base_add_grid_no = (TextView) itemView.findViewById(R.id.grid_base_add_grid_no);

            grid_base_add_name = (EditText) itemView.findViewById(R.id.grid_base_add_name);
            grid_base_add_relation = (TextView) itemView.findViewById(R.id.input_relation);
            grid_base_huzu_name = (EditText) itemView.findViewById(R.id.grid_base_huzu_name);
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
            grid_base_add_outaddress = (EditText) itemView.findViewById(R.id.grid_base_add_outaddress);
            grid_base_add_rkfl = (TextView) itemView.findViewById(R.id.input_rkfl);


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

            grid_base_add_lessor_remark2.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.remark2);
                type = Constant.TYPE_REMARK2;
                myMultipleIntent(mList, type);
            });

            grid_base_add_hobby.setOnClickListener(view -> {
                mList = UIUtils.getContext().getResources().getStringArray(R.array.grid_hobby);
                type = Constant.TYPE_HOBBY;
                myMultipleIntent(mList, type);
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

}
