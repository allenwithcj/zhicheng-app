package com.zhicheng.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.holder.itemsprovider.Line;
import com.zhicheng.utils.common.UIUtils;

public class SettingActivity extends BaseActivity {
    private TextView title_name;
    private RecyclerView mRecyclerView;
    private MySetAdapter mAdapter;


    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_setting);
        title_name = (TextView) findViewById(R.id.title_name);
        mRecyclerView = (RecyclerView)findViewById(R.id.mRecycleView);
        title_name.setText(getResources().getString(R.string.setting));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MySetAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("");
        return super.onCreateOptionsMenu(menu);
    }

    private class MySetAdapter extends RecyclerView.Adapter{
        private String[] strs = {"退出登录"};

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_set_item,parent,false);
            return new SetViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof  SetViewHolder){
                if(position == 0){
                    ((SetViewHolder) holder).text_view.setText(strs[0]);
                    ((SetViewHolder) holder).set_layout.setOnClickListener(view -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
                        builder.setTitle(getResources().getString(R.string.exit));
                        builder.setMessage(getResources().getString(R.string.exit_content));
                        builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseHelper mDatabase = new DatabaseHelper();
                                mDatabase.deleteLocalConfig();
                                SharedPreferences.Editor sp = getSharedPreferences("cookies",0).edit();
                                sp.clear();
                                sp.apply();
                                UIUtils.startActivity(new Intent(UIUtils.getContext(), LoginActivity.class));
                                finish();
                            }
                        }).setNegativeButton("取消",null);
                        builder.setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    });
                }
            }
        }

        @Override
        public int getItemCount() {

            return 1;
        }
    }

    class SetViewHolder extends RecyclerView.ViewHolder{
        private TextView text_view;
        private LinearLayout set_layout;

        public SetViewHolder(View itemView) {
            super(itemView);
            text_view = (TextView) itemView.findViewById(R.id.text_view) ;
            set_layout = (LinearLayout) itemView.findViewById(R.id.set_layout);
        }
    }
}
