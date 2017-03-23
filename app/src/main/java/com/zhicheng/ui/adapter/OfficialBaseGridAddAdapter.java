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
import com.zhicheng.utils.common.UIUtils;

import java.util.UUID;

/**
 * Created by hp on 2017/3/1.
 */

public class OfficialBaseGridAddAdapter extends RecyclerView.Adapter {
    private DatabaseHelper mDataBase;
    private String ZZ_RESIDENCE, GRIDNAME;
    private String RENTNAME, REMARK2, RENTPHONE;
    private String CARD_NUM, NAME, RELATION,HUZUNAME, GENDER, MARITAL_STATUS, POLITICAL_STATUS, EDUCATION,
            HOBBY, REMARK1, DOMICILE, OUTADDRESS, PHONE, WORK;

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

    public void submit(AlertDialog dialog) {
        ZZ_RESIDENCE = holder.grid_base_add_residence.getText().toString();
        GRIDNAME = holder.grid_base_add_grid_no.getText().toString();

        NAME = holder.grid_base_add_name.getText().toString();
        RELATION = holder.grid_base_add_relation.getText().toString();
        HUZUNAME = holder.grid_base_huzu_name.getText().toString();
        GENDER = sexCode(holder.grid_base_add_sex.getText().toString());
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

        RENTNAME = holder.grid_base_add_lessor.getText().toString();
        REMARK2 = holder.grid_base_add_lessor_remark.getText().toString();
        RENTPHONE = holder.grid_base_add_lessor_telephone.getText().toString();

        if(GENDER.equals("") || MARITAL_STATUS.equals("")){
            dialog.dismiss();
            Toast.makeText(UIUtils.getContext(),"性别和婚姻选项不能为空",Toast.LENGTH_SHORT).show();
        }else{
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

        public EditText grid_base_add_lessor;
        public EditText grid_base_add_lessor_remark;
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

        public ItemViewHolder(View itemView) {
            super(itemView);
            grid_base_add_lessor = (EditText) itemView.findViewById(R.id.grid_base_add_lessor);
            grid_base_add_lessor_remark = (EditText) itemView.findViewById(R.id.grid_base_add_lessor_remark);
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

            grid_base_add_sex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList = UIUtils.getContext().getResources().getStringArray(R.array.sex);
                    type = Constant.TYPE_SEX;
                    myIntent(mList, type);
                }
            });

            grid_base_add_policatial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList = UIUtils.getContext().getResources().getStringArray(R.array.political_status);
                    type = Constant.TYPE_POLICATIAL;
                    myIntent(mList, type);
                }
            });

            grid_base_add_degree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList = UIUtils.getContext().getResources().getStringArray(R.array.degree_of_educatio);
                    type = Constant.TYPE_DEGREE;
                    myIntent(mList, type);
                }
            });

            grid_base_add_married.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList = UIUtils.getContext().getResources().getStringArray(R.array.married);
                    type = Constant.TYPE_MARRIED;
                    myIntent(mList, type);
                }
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
        } else if(str.equals("女")){
            return "1";
        }
        return "";
    }

    private String marriedCode(String str) {
        if (str.equals("未婚")) {
            return "0";
        } else if (str.equals("已婚")) {
            return "1";
        } else if (str.equals("离婚")) {
            return "2";
        } else if(str.equals("丧偶")){
            return "3";
        }else{
            return "";
        }
    }
}
