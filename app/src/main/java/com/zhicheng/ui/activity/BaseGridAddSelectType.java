package com.zhicheng.ui.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhicheng.R;

public class BaseGridAddSelectType extends BaseActivity {
    private String[] mList;
    private MyAdapter mAdapter;
    private ListView mListView;
    private String type;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_base_grid_add_select_type);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
        mListView = (ListView) findViewById(R.id.mListView);
        mList = getIntent().getStringArrayExtra("mList");
        mAdapter = new MyAdapter(mList);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                type = getIntent().getStringExtra("type");
                Intent intent = new Intent();
                intent.setAction("com.grid.type");
                intent.putExtra("value",mList[i]);
                intent.putExtra("type",type);
                sendBroadcast(intent);
                finish();

            }
        });
    }

    @Override
    protected int getMenuID() {
        mToolbar.setTitle("请选择");
        return super.getMenuID();
    }



    @Override
    protected void initData() {
    }

    class MyAdapter extends BaseAdapter{
        private String[] mList;
        public MyAdapter(String[] mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            if(mList != null){
                return mList.length;
            }
            return 0;
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
            if(convertView == null){
                convertView = LayoutInflater.from(BaseGridAddSelectType.this).inflate(R.layout.activity_base_grid_select_item,null);
                holder = new ViewHolder();
                holder.mText = (TextView)convertView.findViewById(R.id.mText);
                convertView.setTag(holder);
            }else{
                holder  = (ViewHolder)convertView.getTag();
            }
            holder.mText.setText(mList[i]);

            return convertView;
        }
    }

     public final class ViewHolder{
        public TextView mText;
    }
}
