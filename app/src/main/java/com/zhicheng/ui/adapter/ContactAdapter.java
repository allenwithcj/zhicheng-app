package com.zhicheng.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.zhicheng.R;
import com.zhicheng.bean.Contacts;
import com.zhicheng.utils.OnRecyclerViewListener;

import java.util.List;

/**
 * Created by hp on 2017/3/14.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>
        implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>, SectionIndexer {

    private OnRecyclerViewListener onRecyclerViewListener;
    private List<Contacts> contactsList;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int position) {
        holder.position = holder.getAdapterPosition();
        holder.item_name.setText(contactsList.get(position).getName());
    }

    @Override
    public long getHeaderId(int position) {
        if (contactsList.get(position).getLetter().length() != 0) {
            return contactsList.get(position).getLetter().charAt(0);
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_contact_head_item, parent, false);
        return new RecyclerView.ViewHolder(view) {

        };
    }


    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView item_header = (TextView) holder.itemView.findViewById(R.id.item_header);
        item_header.setText(contactsList.get(position).getLetter());
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int position) {
        for (int i = 0; i < getItemCount(); i++) {
            char firstChar = contactsList.get(i).getLetter().charAt(0);
            if (firstChar == position) {
                return i;
            }
        }
        return -1;

    }

    @Override
    public int getSectionForPosition(int i) {
        return contactsList.get(i).getLetter().charAt(0);
    }

    @Override
    public int getItemCount() {
        if (contactsList != null) {
            return contactsList.size();
        }
        return 0;
    }

    public void AddAllDate(List<Contacts> itemsList) {
        this.contactsList = itemsList;
        notifyDataSetChanged();
    }

    public void updateDate(List<Contacts> mSortContactsList) {
        this.contactsList = mSortContactsList;
        notifyDataSetChanged();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView item_name;
        private int position;

        public ContactViewHolder(View itemView) {
            super(itemView);
            item_name = (TextView) itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onRecyclerViewListener != null) {
                onRecyclerViewListener.onItemClick(position);
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (onRecyclerViewListener != null) {
                onRecyclerViewListener.onItemLongClick(position);
            }
            return false;
        }
    }
}
