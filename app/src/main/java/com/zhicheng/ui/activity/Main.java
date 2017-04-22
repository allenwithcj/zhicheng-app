package com.zhicheng.ui.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.gson.Gson;
import com.library.NoTouchBottomButton;
import com.pgyersdk.crash.PgyCrashManager;
import com.zhicheng.BaseApplication;
import com.zhicheng.R;
import com.zhicheng.api.presenter.impl.OfficialPresenterImpl;
import com.zhicheng.api.view.OfficialView;
import com.zhicheng.bean.http.OfficialResponse;
import com.zhicheng.bean.json.OfficialRequest;
import com.zhicheng.ui.fragment.BaseFragment;
import com.zhicheng.ui.fragment.CurrentMapFragment;
import com.zhicheng.ui.fragment.HomeFragment;
import com.zhicheng.ui.fragment.MainFragment;
import com.zhicheng.ui.fragment.MyNewsFragment;
import com.zhicheng.utils.common.PermissionUtils;
import com.zhicheng.utils.common.UIUtils;

import roboguice.inject.InjectView;

public class Main extends BaseActivity implements OfficialView, BottomNavigationBar.OnTabSelectedListener {

    @InjectView(R.id.bottomNavigationBar)
    private NoTouchBottomButton mBottomNavigation;
    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private HomeFragment mHomeFragment;
    private CurrentMapFragment mCurrentMapFragment;
    private MyNewsFragment mMyNewsFragment;
    private MainFragment mMainFragment;
    private PopupWindow mPopupWindow;
    private BadgeItem badgeItem;

    private OfficialPresenterImpl mOfficialPresenterImpl;
    private int start;


    private void initBottomNavigationBar() {
        mBottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigation.setInActiveColor(R.color.navigationColor);
        mBottomNavigation.setActiveColor(R.color.red);
        badgeItem.hide();
        mBottomNavigation
                .addItem(new BottomNavigationItem(R.drawable.nav_home, getResources().getString(R.string.homepage)))
                .addItem(new BottomNavigationItem(R.drawable.nav_map, getResources().getString(R.string.map)))
                .addItem(new BottomNavigationItem(R.drawable.nav_brokethenews, getResources().getString(R.string.baoliao)))
                .addItem(new BottomNavigationItem(R.drawable.nav_notification, getResources().getString(R.string.news))
                        .setBadgeItem(badgeItem))
                .addItem(new BottomNavigationItem(R.drawable.nav_mine, getResources().getString(R.string.mine)))
                .setFirstSelectedPosition(0)
                .initialise();
        setDefaultFragment();
        mBottomNavigation.setTabSelectedListener(this);
//        BaseApplication.startTimer();
        //登录
        BaseApplication.allLogin();
    }

    @Override
    protected void initEvents() {
        setContentView(R.layout.activity_main);
        PgyCrashManager.register(this);
        badgeItem = new BadgeItem();
        initBottomNavigationBar();
        if (mFragmentManager == null) {
            mFragmentManager = getSupportFragmentManager();
        }
        PermissionUtils.requestStoragePermission(this);
    }


    @Override
    protected void initData() {

    }


    private String createObj(int page) {
        Gson gson = new Gson();
        OfficialRequest officialRequest = new OfficialRequest();
        OfficialRequest.IqBean iqb = new OfficialRequest.IqBean();
        OfficialRequest.IqBean.QueryBean iqbQB = new OfficialRequest.IqBean.QueryBean();
        iqbQB.setRequestType("0");
        iqbQB.setPage(String.valueOf(page));
        iqbQB.setPerPageNums("10");
        iqbQB.setOrderBy("");
        iqbQB.setOrderType("");
        iqbQB.setIsReadJian("1");
        iqb.setQuery(iqbQB);
        iqb.setModel("0");
        iqb.setNamespace("ListRequest");
        officialRequest.setIq(iqb);
        return gson.toJson(officialRequest);
    }

    private void setDefaultFragment() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        hideAllFragments(ft);
        mHomeFragment = HomeFragment.newInstance();
        ft.add(R.id.main_content, mHomeFragment);
        ft.commit();
    }

    private void hideAllFragments(FragmentTransaction ft) {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }
        if (mCurrentMapFragment != null) {
            ft.hide(mCurrentMapFragment);
        }
        if (mMyNewsFragment != null) {
            ft.hide(mMyNewsFragment);
        }
        if (mMainFragment != null) {
            ft.hide(mMainFragment);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mBottomNavigation.selectTab(2, true);
            return true;
        }
        if (id == R.id.action_location) {
            UIUtils.startActivity(new Intent(this, LcationMapTEST.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        hideAllFragments(ft);
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    ft.add(R.id.main_content, mHomeFragment);
                } else {
                    ft.show(mHomeFragment);
                }
                break;
            case 1:
                if (mCurrentMapFragment == null) {
                    mCurrentMapFragment = CurrentMapFragment.newInstance();
                    ft.add(R.id.main_content, mCurrentMapFragment);
                } else {
                    ft.show(mCurrentMapFragment);
                }
                break;
            case 2:
//                ViewGroup parentView = (ViewGroup) findViewById(android.R.id.content).getRootView();
//                View pop_view = LayoutInflater.from(this).inflate(R.layout.item_pop_ineed_choose,parentView,false);
//                pop_view.findViewById(R.id.imgBtn1).setOnClickListener(v -> {
//                    Intent intent = new Intent(Main.this,IneedActivity.class);
//                    intent.putExtra("upType","no");
//                    startActivityForResult(intent,0);
//                });
//                pop_view.findViewById(R.id.imgBtn2).setOnClickListener(v -> {
//                    Intent intent = new Intent(Main.this,IneedActivity.class);
//                    intent.putExtra("upType","common");
//                    startActivityForResult(intent,0);
//                });
//                if (mPopupWindow != null && mPopupWindow.isShowing()){
//                    mPopupWindow.dismiss();
//                    mPopupWindow = null;
//                }
//                int width = getWindowManager().getDefaultDisplay().getWidth();
//                mPopupWindow = new PopupWindow(pop_view,width-width/3, WindowManager.LayoutParams.WRAP_CONTENT);
//                mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//                mPopupWindow.setOutsideTouchable(true);
//                mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//                mPopupWindow.setOnDismissListener(() -> {
//                    mBottomNavigation.selectTab(0);
//                    if (mHomeFragment == null){
//                        mHomeFragment = HomeFragment.newInstance();
//                        ft.add(R.id.main_content,mHomeFragment);
//                    }else {
//                        ft.show(mHomeFragment);
//                    }
//                    AnimationUtils.darkBackgroundColor(getWindow(),1f);
//                });
//                mPopupWindow.showAtLocation(parentView, Gravity.CENTER,0,0);
//                AnimationUtils.darkBackgroundColor(getWindow(),0.4f);
                Intent intent = new Intent(Main.this, IneedActivity.class);
                intent.putExtra("upType", "common");
                startActivityForResult(intent, 0);
                break;
            case 3:
                if (mMyNewsFragment == null) {
                    mMyNewsFragment = MyNewsFragment.newInstance();
                    ft.add(R.id.main_content, mMyNewsFragment);
                } else {
                    ft.show(mMyNewsFragment);
                }
                break;
            case 4:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                    ft.add(R.id.main_content, mMainFragment);
                } else {
                    ft.show(mMainFragment);
                }
                break;
        }
        ft.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            mBottomNavigation.selectTab(0);
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            hideAllFragments(ft);
            if (mHomeFragment == null) {
                mHomeFragment = HomeFragment.newInstance();
                ft.add(R.id.main_content, mHomeFragment);
            } else {
                ft.show(mHomeFragment);
            }
            ft.commit();
        }
    }

    @Override
    protected void onResume() {
        fresh();
        super.onResume();
        firstClickBack = 0;
    }

    private void fresh() {
        mOfficialPresenterImpl = new OfficialPresenterImpl(this);
        start = 1;
        String strEntity = createObj(start);
        mOfficialPresenterImpl.loadNoFinish(strEntity, start);
        start++;
    }

    private long firstClickBack = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (firstClickBack != 0 && System.currentTimeMillis() - firstClickBack < 1500) {
                BaseApplication.quiteApplication();
                return true;
            } else {
                Toast.makeText(this, "再点一次退出本应用", Toast.LENGTH_SHORT).show();
                firstClickBack = System.currentTimeMillis();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PgyCrashManager.unregister();
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
    public void refreshData(Object result) {
        if (result instanceof OfficialResponse) {
            if (((OfficialResponse) result).getIq().getQuery().getErrorCode().equals("0")) {
                int mTotalNum = ((OfficialResponse) result).getIq().getQuery().getTotalNums();
                if (mTotalNum != 0) {
                    badgeItem.setHideOnSelect(false)
                            .setText(String.valueOf(mTotalNum))
                            .setBackgroundColorResource(R.color.red)
                            .setBorderWidth(0)
                            .show();
                } else {
                    badgeItem.hide();
                }
            }
        }
    }

    @Override
    public void addData(Object result) {

    }
}
