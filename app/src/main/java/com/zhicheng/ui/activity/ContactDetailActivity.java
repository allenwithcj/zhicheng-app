package com.zhicheng.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhicheng.R;
import com.zhicheng.bean.Contacts;

public class ContactDetailActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private Contacts contacts;
    private MyContactAdapter mAdapter;
    public static final int CALL_PHONE = 0;

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_contact_detail);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyContactAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mToolbar.setNavigationIcon(R.drawable.ic_action_clear);
    }

    @Override
    protected void initData() {
        contacts = (Contacts) getIntent().getSerializableExtra("contacts");
        mAdapter.addDate(contacts);

    }


    class MyContactAdapter extends RecyclerView.Adapter {
        private static final int TYPE_TOP = 0;
        private static final int TYPE_TEL = 1;
        private static final int TYPE_PHONE = 2;
        private static final int TYPE_EMAIL = 3;
        private Contacts contacts;
        private String[] tag = {"手机:", "电话:", "邮箱:"};

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            if (viewType == TYPE_TOP) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_top, parent, false);
                return new ContactTopViewHolder(view);
            } else if (viewType == TYPE_TEL) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_tel, parent, false);
                return new ContactTelViewHolder(view);
            } else if (viewType == TYPE_PHONE) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_phone, parent, false);
                return new ContactPhoneViewHolder(view);
            } else {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_contact_email, parent, false);
                return new ContactEmailViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ContactTopViewHolder) {
                ((ContactTopViewHolder) holder).name.setText(contacts.getName());
                ((ContactTopViewHolder) holder).departmentName.setText(contacts.getDepartmentName());
                ((ContactTopViewHolder) holder).position.setText(contacts.getPosition());
            } else if (holder instanceof ContactTelViewHolder) {
                if (contacts.getTel() != null) {
                    ((ContactTelViewHolder) holder).tel.setText(tag[0] + contacts.getTel());
                    ((ContactTelViewHolder) holder).tel_layout.setOnClickListener(view -> {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacts.getTel()));
                        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Snackbar.make(activity.getWindow().getDecorView(),
                                    "please give me the permission", Snackbar.LENGTH_SHORT).show();
                        } else {
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE);
                            startActivity(intent);
                        }

                    });

                    ((ContactTelViewHolder) holder).message.setOnClickListener(view -> {
                        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + contacts.getTel()));
                        startActivity(intent);
                    });
                } else {
                    ((ContactTelViewHolder) holder).tel.setText(tag[0] + "无");
                }
            } else if (holder instanceof ContactPhoneViewHolder) {
                if (contacts.getPhone() != null) {
                    ((ContactPhoneViewHolder) holder).phone.setText(tag[1] + contacts.getPhone());
                    ((ContactPhoneViewHolder) holder).phone_layout.setOnClickListener(view -> {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contacts.getPhone()));
                        startActivity(intent);
                    });
                } else {
                    ((ContactPhoneViewHolder) holder).phone.setText(tag[1] + "无");
                }
            } else if (holder instanceof ContactEmailViewHolder) {
                if (contacts.getEmail() != null) {
                    ((ContactEmailViewHolder) holder).email.setText(tag[2] + contacts.getEmail());
                } else {
                    ((ContactEmailViewHolder) holder).email.setText(tag[2] + "无");
                }
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TOP;
            } else if (position == 1) {
                return TYPE_TEL;
            } else if (position == 2) {
                return TYPE_PHONE;
            } else {
                return TYPE_EMAIL;
            }
        }

        public void addDate(Contacts contacts) {
            this.contacts = contacts;
            notifyDataSetChanged();
        }

        class ContactTopViewHolder extends RecyclerView.ViewHolder {
            private TextView name;
            private TextView departmentName;
            private TextView position;

            public ContactTopViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.name);
                departmentName = (TextView) itemView.findViewById(R.id.departmentName);
                position = (TextView) itemView.findViewById(R.id.position);
            }
        }

        class ContactTelViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout tel_layout;
            private TextView tel;
            private ImageView message;

            public ContactTelViewHolder(View itemView) {
                super(itemView);
                tel_layout = (LinearLayout) itemView.findViewById(R.id.tel_layout);
                tel = (TextView) itemView.findViewById(R.id.tel);
                message = (ImageView) itemView.findViewById(R.id.message);
            }
        }

        class ContactPhoneViewHolder extends RecyclerView.ViewHolder {
            private LinearLayout phone_layout;
            private TextView phone;


            public ContactPhoneViewHolder(View itemView) {
                super(itemView);
                phone_layout = (LinearLayout) itemView.findViewById(R.id.phone_layout);
                phone = (TextView) itemView.findViewById(R.id.phone);
            }
        }

        class ContactEmailViewHolder extends RecyclerView.ViewHolder {
            private TextView email;
            private LinearLayout email_layout;

            public ContactEmailViewHolder(View itemView) {
                super(itemView);
                email = (TextView) itemView.findViewById(R.id.email);
                email_layout = (LinearLayout) itemView.findViewById(R.id.email_layout);
            }
        }

    }
}
