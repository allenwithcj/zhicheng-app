package com.zhicheng.ui.activity;

import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhicheng.R;

import java.util.ArrayList;

public class BaseGridAddSelectTypeMultipleChoice extends BaseActivity {
    private String[] mList;
    private MyAdapter mAdapter;
    private ListView mListView;
    private String type;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_base_grid_add_select_multiple_type);
        mListView = (ListView) findViewById(R.id.mListView);
        mList = getIntent().getStringArrayExtra("mList");
        mAdapter = new MyAdapter(mList);
        mListView.setAdapter(mAdapter);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected int getMenuID() {
        return R.menu.official_grid_choice;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mToolbar.setTitle("请选择");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_choice){
            if(mAdapter.getSelectedItem().length() != 0){
                type = getIntent().getStringExtra("type");
                Intent intent = new Intent();
                intent.setAction("com.grid.type");
                intent.putExtra("value", mAdapter.getSelectedItem().substring(0,mAdapter.getSelectedItem().length()-1));
                intent.putExtra("type", type);
                sendBroadcast(intent);
                finish();
            }else{
                Toast.makeText(this,"请先选择",Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
    }

    class MyAdapter extends BaseAdapter {
        private String[] mList;
        private SparseBooleanArray mSelectedPositions = new SparseBooleanArray();
        private boolean mIsSelectable = false;

        public MyAdapter(String[] mList) {
            this.mList = mList;
        }

        //获得选中条目的结果
        public String getSelectedItem() {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mList.length; i++) {
                if (isItemChecked(i)) {
                    sb.append(mList[i]).append(",");
                }
            }
            return sb.toString();
        }

        //设置给定位置条目的选择状态
        private void setItemChecked(int position, boolean isChecked) {
            mSelectedPositions.put(position, isChecked);
        }

        //根据位置判断条目是否选中
        private boolean isItemChecked(int position) {
            return mSelectedPositions.get(position);
        }

        //根据位置判断条目是否可选
        private boolean isSelectable() {
            return mIsSelectable;
        }
        //设置给定位置条目的可选与否的状态
        private void setSelectable(boolean selectable) {
            mIsSelectable = selectable;
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.length;
        }

        @Override
        public Object getItem(int i) {
            return mList[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(BaseGridAddSelectTypeMultipleChoice.this).inflate(R.layout.activity_base_grid_select_multiple_item, null);
                holder = new ViewHolder();
                holder.select_checkbox = (CheckBox) convertView.findViewById(R.id.select_checkbox);
                holder.mText = (TextView) convertView.findViewById(R.id.mText);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.mText.setText(mList[i]);
            holder.select_checkbox.setOnClickListener(view -> {
                if (isItemChecked(i)) {
                    setItemChecked(i, false);
                } else {
                    setItemChecked(i, true);
                }
            });

            return convertView;
        }
    }

    public final class ViewHolder {
        public CheckBox select_checkbox;
        public TextView mText;
    }
}
