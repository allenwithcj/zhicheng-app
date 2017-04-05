package com.zhicheng.ui.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.common.database.DatabaseHelper;
import com.zhicheng.api.common.database.LocalConfig;
import com.zhicheng.api.presenter.impl.UpThingsPresenterImpl;
import com.zhicheng.api.view.UpThingsView;
import com.zhicheng.bean.http.CommonResponse;
import com.zhicheng.bean.json.UpFileRequest;
import com.zhicheng.bean.json.UpThingsRequest;
import com.zhicheng.module.imageloader.GlideImageLoader;
import com.zhicheng.ui.adapter.IneedAdapter;
import com.zhicheng.utils.common.PermissionUtils;
import com.zhicheng.utils.common.UIUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import cc.dagger.photopicker.PhotoPicker;
import cc.dagger.photopicker.picker.PhotoFilter;

public class IneedActivity extends BaseActivity implements UpThingsView{

	private RecyclerView mRecyclerView;
	private IneedAdapter mIneedAdapter;
	private PhotoFilter filter;
	private ArrayList<String> mImagePath;
	private UpThingsPresenterImpl mUpThingsPresenterImpl;
	private AlertDialog dialog;
	private boolean isCommon;
	//前置事项处理意见
	private final static int ACTIVITY_DEAL = 120;
	private List<String> activity_deal;
    //广播&&数据
    private SparseArray<String> mData;
	private ArrayList<String> mNode;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals("com.MapFragment.Location")){
				BaseApplication.log_say(TAG,intent.getAction());
				ArrayList<String> location = intent.getStringArrayListExtra("location");
				mData.put(0,location.get(2));//address
				mData.put(7,location.get(0));//latitude
				mData.put(8,location.get(1));//longitude
				mData.put(9,location.get(3));//desc
				IneedAdapter.InputChooseEdittTextViewHolder holder = (IneedAdapter.InputChooseEdittTextViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
				holder.input_choose.setText(mData.get(0));
				holder.mProgressBar.setVisibility(View.GONE);
			}else {
				IneedAdapter.InputChooseTextViewViewHolder holder = (IneedAdapter.InputChooseTextViewViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(1));
				mNode = intent.getStringArrayListExtra("node");
				holder.input_choose.setText(mNode.get(3));
			}
        }
    };

	@Override
	protected void initEvents() {
		setContentView(R.layout.activity_selectimg);
		isCommon = getIntent().getStringExtra("upType").equals("common");
		mData = new SparseArray<String>();
		mNode = new ArrayList<>();
		mImagePath = new ArrayList<>();
		activity_deal = new ArrayList<>();
		mRecyclerView = (RecyclerView) findViewById(R.id.mRecycleView);
		mIneedAdapter = new IneedAdapter(getApplicationContext());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		mRecyclerView.setAdapter(mIneedAdapter);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_clear));
        setResult(200);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.search.classify.bao");
		intentFilter.addAction("com.MapFragment.Location");
        registerReceiver(receiver,intentFilter);
		mUpThingsPresenterImpl = new UpThingsPresenterImpl(this);
		PhotoPicker.init(new GlideImageLoader(),null);
		filter = PhotoFilter.build();
		filter.showGif(false);
		mIneedAdapter.setSendLocation(maps -> {
			IneedAdapter.InputChooseEdittTextViewHolder holder = (IneedAdapter.InputChooseEdittTextViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
			if (maps.get("openMap") != null && maps.get("openMap").equals("true")){
				UIUtils.startActivity(new Intent(this,LcationMapTEST.class));
				holder.mProgressBar.setVisibility(View.GONE);
			}else {
				mData.put(0,maps.get("address"));
				mData.put(7,maps.get("latitude"));
				mData.put(8,maps.get("longitude"));
				mData.put(9,maps.get("desc"));
				holder.input_choose.setText(mData.get(0));
				holder.mProgressBar.setVisibility(View.GONE);
			}

        });
        mIneedAdapter.setSaveEdit((position, s) -> mData.put(2,s));
	}

	@Override
	protected void initData() {
		PermissionUtils.requestLocationPermission(this);
		mIneedAdapter.setButtonClick(() -> {
			PermissionUtils.requestCameraPermission(this);
			PermissionUtils.requestStoragePermission(this);
			PhotoPicker.load()
					.filter(filter) // 照片属性过滤
					.gridColumns(4) // 照片列表显示列数
					.showCamera(true)
					.multi()
					.maxPickSize(3) // 最大选择数
					.selectedPaths(mImagePath) // 已选择的照片地址
					.start(IneedActivity.this); // 从Fragment、Activity中启动
		});
	}

    @Override
    protected int getMenuID() {
        return R.menu.main_ineed;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (isCommon){
			mToolbar.setTitle("事项上报");
		}else {
			mToolbar.setTitle("前置上报");
		}
		return super.onCreateOptionsMenu(menu);
	}
	private boolean isChangeMenu = true;
	private Menu mMenu;
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		mMenu = menu;
		if (!isCommon && isChangeMenu){
			menu.findItem(R.id.action_send).setTitle("下一步");
		}else {
			menu.findItem(R.id.action_send).setTitle("上报");
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_send){
			if (!isCommon && activity_deal.size() == 0){
				isChangeMenu = false;
				onPrepareOptionsMenu(mMenu);
				startActivityForResult(new Intent(IneedActivity.this,DealActivity.class),ACTIVITY_DEAL);
				return true;
			}
			DatabaseHelper mDataBase = new DatabaseHelper();
			LocalConfig config = mDataBase.getLocalConfig();
			if (config != null && config.getUserName() != null && config.getPwd() != null){
				IneedAdapter.InputChooseEdittTextViewHolder holder = (IneedAdapter.InputChooseEdittTextViewHolder)
						mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(0));
				UpThingsRequest utp = new UpThingsRequest();
				UpThingsRequest.IqBean iqb = new UpThingsRequest.IqBean();
				UpThingsRequest.IqBean.QueryBean iqbQB = new UpThingsRequest.IqBean.QueryBean();
				String guid = UUID.randomUUID().toString();
				iqb.setNamespace("SubmitFormRequest");
				Class c = iqbQB.getClass();
				Method[] methods = c.getDeclaredMethods();
				for (Method method : methods){
					try {
						if (method.getName().startsWith("set")){
							if (method.getName().endsWith("ZC02")){
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
								String str = simpleDateFormat.format(System.currentTimeMillis());
								method.invoke(iqbQB,str);
							}else if(method.getName().endsWith("ZC03")){
								String mLocation = holder.input_choose.getText().toString();
								method.invoke(iqbQB,mLocation);
							}else if(method.getName().endsWith("ZC04")){
								method.invoke(iqbQB,mData.get(2));
							}else if (method.getName().endsWith("ZC32")){
								method.invoke(iqbQB,guid);
							}else if (method.getName().endsWith("ZC06")){
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
								String str = simpleDateFormat.format(System.currentTimeMillis());
								method.invoke(iqbQB,str);
							}else if (method.getName().endsWith("ZC08")){
								method.invoke(iqbQB,"智慧雉城App");
							}else if(method.getName().endsWith("ZC07")){
								method.invoke(iqbQB,config.getUserId());
							}else if(method.getName().endsWith("ZC17")){
								method.invoke(iqbQB,mNode.get(1));
							}else if(method.getName().endsWith("ZC19")){
								method.invoke(iqbQB,mNode.get(2));
							}else if(method.getName().endsWith("ZC27")){
								method.invoke(iqbQB,mNode.get(4));
							}else if(method.getName().endsWith("ZC18")){
								method.invoke(iqbQB,mNode.get(3));
							}else if(method.getName().endsWith("ZC21")){
								method.invoke(iqbQB,"是");
							}else if(method.getName().endsWith("ZC29")){
								method.invoke(iqbQB,mData.get(8));
							}else if(method.getName().endsWith("ZC30")){
								method.invoke(iqbQB,mData.get(7));
							}else if(method.getName().endsWith("ZC28")){
							method.invoke(iqbQB,mNode.get(5));
							}else if(method.getName().endsWith("ZC24")){
							method.invoke(iqbQB,mNode.get(6));
							}else if(method.getName().endsWith("ZC22")){
								method.invoke(iqbQB,mNode.get(7));
							}else if(method.getName().endsWith("No")){
								method.invoke(iqbQB,"1");
							}else if(method.getName().endsWith("key")){
								method.invoke(iqbQB,String.valueOf(System.currentTimeMillis()));
							}else if (method.getName().endsWith("ZC11")){//是否普通上报
								if (isCommon){
									method.invoke(iqbQB,"1");
								}else {
									method.invoke(iqbQB,"2");
								}
							}else if (method.getName().endsWith("ZC34")){//处置附件GUID
								if (isCommon){
									method.invoke(iqbQB,"");
								}else {
									method.invoke(iqbQB,activity_deal.get(0));
								}
							}else if (method.getName().endsWith("ZC45")){//处置意见描述
								if (isCommon){
									method.invoke(iqbQB,"");
								}else {
									method.invoke(iqbQB,activity_deal.get(1));
								}
							}else{
								method.invoke(iqbQB,"");
							}
						}
					}catch (Exception e){
						e.printStackTrace();
					}
				}
				iqb.setQuery(iqbQB);
				utp.setIq(iqb);
				Gson gson = new Gson();
				String strEntity = gson.toJson(utp);
				UpFileRequest uf = new UpFileRequest();
				UpFileRequest.IqBean ufIB = new UpFileRequest.IqBean();
				UpFileRequest.IqBean.QueryBean ufIBQB = new UpFileRequest.IqBean.QueryBean();
				ufIBQB.setAttachmentGUID(guid);
				ufIB.setQuery(ufIBQB);
				ufIB.setNamespace("AttachmentUpdateRequest");
				uf.setIq(ufIB);
				String jFile = gson.toJson(uf);
				if(mData.get(0) != null){
					if (null != mImagePath){
						if(mImagePath.size() != 0){
							if(mNode.get(3) != null){
								item.setEnabled(false);
								dialog = new AlertDialog.Builder(this,R.style.dialog)
										.setView(R.layout.z_loading_view)
										.setCancelable(false)
										.create();
								dialog.show();
								if (isCommon){
									mUpThingsPresenterImpl.UpThings(5,guid,strEntity,jFile,mImagePath);
								}else {
									mUpThingsPresenterImpl.UpThings(3,guid,strEntity,jFile,mImagePath);
								}
							}else{
								Toast.makeText(this,"请选择爆料类型",Toast.LENGTH_SHORT).show();
							}
						}else {
							item.setEnabled(true);
							Toast.makeText(this,"请选择上传图片",Toast.LENGTH_SHORT).show();
						}
					}
				}else{
					Toast.makeText(this,"请先定位地址",Toast.LENGTH_SHORT).show();
				}
			}else {
				BaseApplication.checkLogin();
				item.setEnabled(true);
				Snackbar.make(mToolbar,"重连成功，请重新上报",Snackbar.LENGTH_SHORT).show();
			}
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PhotoPicker.REQUEST_SELECTED){
			if (resultCode == RESULT_OK){
				List<String> mData = data.getStringArrayListExtra(PhotoPicker.EXTRA_RESULT);
				mImagePath = (ArrayList<String>) mData;
				mIneedAdapter.addPhoto(mData);
			}
		}else{
			if (data.getStringArrayListExtra("mData") != null){
				activity_deal = data.getStringArrayListExtra("mData");
			}else {
				isChangeMenu = true;
				onPrepareOptionsMenu(mMenu);
			}
		}
	}

	@Override
	public void showMessage(String msg) {
		if (dialog != null && dialog.isShowing()){
			dialog.dismiss();
		}
		Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
	}

	@Override
	public void UpThings(Object result) {
		if (result instanceof CommonResponse){
			BaseApplication.log_say(TAG,((CommonResponse) result).getIq().getQuery().getErrorMessage());
			if (((CommonResponse) result).getIq().getQuery().getErrorCode() == 0){
				showMessage("上报成功");
				this.finish();
			}else {
				mMenu.findItem(R.id.action_send).setEnabled(true);
				showMessage(((CommonResponse) result).getIq().getQuery().getErrorMessage());
			}
		}
	}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


}

