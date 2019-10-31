package com.boe.tvpad.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.boe.tvpad.R;
import com.boe.tvpad.adapter.LightAdapter;
import com.boe.tvpad.adapter.ProgramAdapter;
import com.boe.tvpad.adapter.SlidAdapter;
import com.boe.tvpad.base.BaseActivity;
import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.DeviceBean;
import com.boe.tvpad.bean.LightBean;
import com.boe.tvpad.bean.SlidHollListBean;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.boe.tvpad.dialog.DeleteDialog;
import com.boe.tvpad.presenter.LightPresenter;
import com.boe.tvpad.presenter.presenterImpl.DevicePresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.ExitLoginPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.LightPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.NoticeOpenOrClosePresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.SlidHollListPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.ToDownloadPresenterImpl;
import com.boe.tvpad.utils.SharedPreferencesUtils;
import com.boe.tvpad.utils.ToastMgr;
import com.boe.tvpad.utils.ViewPagerUtils;
import com.boe.tvpad.view.ListShowView;
import com.boe.tvpad.view.NoticeOpenOrCloseView;
import com.boe.tvpad.view.ToDownloadView;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ListShowActivity extends BaseActivity implements View.OnClickListener, ListShowView, NoticeOpenOrCloseView, ToDownloadView {
    private ImageView img_slid;
    private DrawerLayout drawlayout;
    private LinearLayout left_drawlayout;
    private ActionBarDrawerToggle drawerbar;
    private ViewPager vp_prgram;
    private ProgramAdapter programAdapter;
    private ListView listview;
    private SlidAdapter slidAdapter;
    private TextView tv_updatepwd;
    private TextView tv_exitlogin;
    private LinearLayout ll_bottom_choice;
    private LinearLayout ll_bottom_device;
    private LinearLayout ll_bottom_light;
    private ViewPager vp_light;
    private LightAdapter lightAdapter;
    private TextView tv_name;
    private TextView tv_username;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private SlidHollListPresenterImpl slidHollListPresenter;
    private String padToken;
    private List<SlidHollListBean.DataBean> sliddata;
    private ExitLoginPresenterImpl exitLoginPresenter;
    private String username;
    private String name;
    private DevicePresenterImpl devicePresenter;
    private int type = 1;
    private LightPresenterImpl lightPresenter;
    private TextView tv_devicename;
    private NoticeOpenOrClosePresenterImpl noticeOpenOrClosePresenter;
    private List<LightBean.DataBean> lightdata;
    private ImageView img_device_on;
    private ImageView img_device_off;
    private ImageView img_device_update;
    private List<DeviceBean.DataBean> devicedata;
    private int currentItem;
    private LinearLayout layout;
    private int size;
    private int flag = 0;
    private Timer timer;
    private TimerTask task;
    private int position;
    private int devicecurrentItem;
    private ToDownloadPresenterImpl toDownloadPresenter;
    private LinearLayout layoutlight;
    private int bottomType = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list_show;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }


    @Override
    protected void initView() {
        slidHollListPresenter.obtainallholls(padToken);
        if (timer==null&&task==null){
            //计时器
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    if (sliddata!=null&&sliddata.size() > 0) {
                        if (bottomType == 2) {
                            String slidId = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                            //调接口
                            lightPresenter.obtainLightData(slidId, padToken);
                        }

                        if (bottomType == 1) {
                            //调接口
                            String slidId = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                            devicePresenter.obtainDeviceData(slidId, padToken);
                        }
                    }

                }
            };
            timer.schedule(task, 1000, 2000);
        }

        //侧滑中用户名和名字的赋值
        tv_username.setText(username);
        tv_name.setText(name);

        //点击显示侧滑页面
        img_slid.setOnClickListener(this);
        //设置菜单内容之外其他区域的背景色
        drawlayout.setScrimColor(getResources().getColor(R.color.sileback));
        //设置开关监听
        drawerbar = new ActionBarDrawerToggle(this, drawlayout, R.string.open, R.string.close);
        drawlayout.setDrawerListener(drawerbar);

        //侧滑条目点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                layoutlight.removeAllViews();
                layout.removeAllViews();
                sharedPreferencesUtils.putData("slidname", sliddata.get(position).getName());
                sharedPreferencesUtils.putData("slidId", sliddata.get(position).getId() + "");
                for (int i = 0; i < sliddata.size(); i++) {
                    if (i == position) {
                        sliddata.get(i).setStatus(2);
                    } else {
                        sliddata.get(i).setStatus(1);
                    }
                }
                slidAdapter.notifyDataSetChanged();
                //调接口获取展区
                if (type == 1) {
                    layout.removeAllViews();
                    devicePresenter.obtainDeviceData(String.valueOf(sliddata.get(position).getId()), padToken);
                    drawlayout.closeDrawer(left_drawlayout);
                } else {
                    layout.removeAllViews();
                    lightPresenter.obtainLightData(String.valueOf(sliddata.get(position).getId()), padToken);
                    drawlayout.closeDrawer(left_drawlayout);
                }
                tv_devicename.setText(sliddata.get(position).getName());
            }
        });
        //侧滑里面的退出登录和修改密码
        tv_updatepwd.setOnClickListener(this::onClick);
        tv_exitlogin.setOnClickListener(this::onClick);
        ll_bottom_device.setOnClickListener(this::onClick);
        ll_bottom_light.setOnClickListener(this::onClick);
        //拦截侧滑点击冲突
        left_drawlayout.setOnClickListener(this::onClick);
        img_device_on.setOnClickListener(this);
        img_device_off.setOnClickListener(this);
        img_device_update.setOnClickListener(this);
        vp_prgram.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < size; i++) {
                    int childCount = layout.getChildCount();
                    if (childCount > 0) {
                        View view = layout.getChildAt(i);
                        if (i == position)
                            view.setBackgroundResource(R.drawable.view1);
                        else
                            view.setBackgroundResource(R.drawable.view2);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                devicecurrentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_light.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < size; i++) {
                    int childCount = layoutlight.getChildCount();
                    if (childCount > 0) {
                        View view = layoutlight.getChildAt(i);
                        if (i == position)
                            view.setBackgroundResource(R.drawable.view1);
                        else
                            view.setBackgroundResource(R.drawable.view2);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentItem = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        padToken = (String) sharedPreferencesUtils.getData("padToken", "");
        username = (String) sharedPreferencesUtils.getData("username", "");
        name = (String) sharedPreferencesUtils.getData("name", "");
        img_slid = findViewById(R.id.img_slid);
        drawlayout = findViewById(R.id.drawlayout);
        left_drawlayout = findViewById(R.id.left_drawlayout);
        vp_prgram = findViewById(R.id.vp_program);
        listview = findViewById(R.id.listview);
        tv_updatepwd = findViewById(R.id.tv_updatepwd);
        tv_exitlogin = findViewById(R.id.tv_exitlogin);
        ll_bottom_choice = findViewById(R.id.ll_bottom_choice);
        ll_bottom_device = findViewById(R.id.ll_bottom_device);
        ll_bottom_light = findViewById(R.id.ll_bottom_light);
        vp_light = findViewById(R.id.vp_light);
        tv_name = findViewById(R.id.tv_name);
        tv_username = findViewById(R.id.tv_username);
        tv_devicename = findViewById(R.id.tv_devicename);
        img_device_on = findViewById(R.id.img_device_on);
        img_device_off = findViewById(R.id.img_device_off);
        img_device_update = findViewById(R.id.img_device_update);
        layoutlight = findViewById(R.id.layoutlight);
        layout = findViewById(R.id.layout);
        initPresenter();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (drawlayout!=null){
            drawlayout.closeDrawer(left_drawlayout);
        }
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void initPresenter() {
        slidHollListPresenter = new SlidHollListPresenterImpl(this);
        exitLoginPresenter = new ExitLoginPresenterImpl(this);
        devicePresenter = new DevicePresenterImpl(this);
        lightPresenter = new LightPresenterImpl(this);
        noticeOpenOrClosePresenter = new NoticeOpenOrClosePresenterImpl(this);
        toDownloadPresenter = new ToDownloadPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_slid:
                //调接口侧滑里面列表
                slidHollListPresenter.obtainallholls(padToken);
                if (drawlayout.isDrawerOpen(left_drawlayout)) {
                    drawlayout.closeDrawer(left_drawlayout);
                } else {
                    drawlayout.openDrawer(left_drawlayout);
                }
                if (type==1){
                    vp_prgram.setVisibility(View.VISIBLE);
                    vp_light.setVisibility(View.GONE);
                    layoutlight.setVisibility(View.GONE);
                    img_device_update.setVisibility(View.VISIBLE);
                    ll_bottom_choice.setBackground(getResources().getDrawable(R.drawable.device));

                }else {
                    vp_prgram.setVisibility(View.GONE);
                    vp_light.setVisibility(View.VISIBLE);
                    layoutlight.setVisibility(View.VISIBLE);
                    img_device_update.setVisibility(View.GONE);
                    ll_bottom_choice.setBackground(getResources().getDrawable(R.drawable.light));
                }
                break;
            case R.id.tv_updatepwd:
                Intent intent = new Intent(this, ChangePwdActivity.class);
                intent.putExtra("isfirst", "no");
                startActivity(intent);
                break;
            case R.id.tv_exitlogin:
                exitLoginPresenter.obtainExitLoginData(padToken);
                break;
            case R.id.ll_bottom_device:
                bottomType = 1;
                layout.removeAllViews();
                layoutlight.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
                img_device_update.setVisibility(View.VISIBLE);
                type = 1;
                ll_bottom_choice.setBackground(getResources().getDrawable(R.drawable.device));
                vp_light.setVisibility(View.GONE);
                vp_prgram.setVisibility(View.VISIBLE);
                String slidname = (String) sharedPreferencesUtils.getData("slidname", "");
                if (sliddata.size() > 0) {
                    if (slidname.equals("")) {
                        tv_devicename.setText(sliddata.get(0).getName());
                    } else {
                        tv_devicename.setText(slidname);
                    }
                    String slidId2 = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                    //调接口
                    devicePresenter.obtainDeviceData(slidId2, padToken);
                }

                break;
            case R.id.ll_bottom_light:

                bottomType = 2;
                flag = 1;
                layoutlight.removeAllViews();
                layoutlight.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                img_device_update.setVisibility(View.GONE);
                type = 2;
                ll_bottom_choice.setBackground(getResources().getDrawable(R.drawable.light));
                vp_prgram.setVisibility(View.GONE);
                vp_light.setVisibility(View.VISIBLE);
                String slidname1 = (String) sharedPreferencesUtils.getData("slidname", "");
                if (sliddata.size() > 0) {
                    if (slidname1.equals("")) {
                        tv_devicename.setText(sliddata.get(0).getName());
                    } else {
                        tv_devicename.setText(slidname1);
                    }

                    String slidId3 = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                    //调接口
                    lightPresenter.obtainLightData(slidId3, padToken);
                }


                break;
            case R.id.img_device_on:
                String slidId = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                //调接口通知后台
                if (type == 1) {
                    if (devicedata.size() > 0) {
                        //弹框
                        new DeleteDialog(this).builder()
                                .setMsg("是否开启展厅内所有展品？")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        noticeOpenOrClosePresenter.obtainOpenOrCloseState(slidId, "", "", "1", "2", padToken);
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();
                    }
                } else {
                    if (lightdata.size() > 0) {
                        //弹框
                        new DeleteDialog(this).builder()
                                .setMsg("是否开启展厅内所有灯光？")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        noticeOpenOrClosePresenter.obtainOpenOrCloseState(slidId + "", "", "", "1", "1", padToken);
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();

                    }
                }

                break;
            case R.id.img_device_off:
                String slidId1 = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                //调接口通知后台
                if (type == 1) {
                    if (devicedata.size() > 0) {
                        //弹框
                        new DeleteDialog(this).builder()
                                .setMsg("是否关闭展厅内所有展品？")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        noticeOpenOrClosePresenter.obtainOpenOrCloseState(slidId1, "", "", "0", "2", padToken);
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();

                    }
                } else {
                    if (lightdata.size() > 0) {
                        //弹框
                        new DeleteDialog(this).builder()
                                .setMsg("是否关闭展厅内所有灯光？")
                                .setPositiveButton("确认", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        noticeOpenOrClosePresenter.obtainOpenOrCloseState(slidId1, "", "", "0", "1", padToken);
                                    }
                                })
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                }).show();

                    }
                }
                break;
            case R.id.img_device_update:
                if (devicedata.size() > 0) {
                    //弹框
                    new DeleteDialog(this).builder()
                            .setMsg("是否下载展厅内所有展品的节目内容？")
                            .setPositiveButton("确认", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String slidId1 = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                                    //调接口通知后台
                                    toDownloadPresenter.obtainallholls(slidId1, "", "", padToken);
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();

                }
                break;
        }
    }

    //侧滑里面展厅列表
    @Override
    public void onGetSlidHollList(SlidHollListBean slidHollListBean, String errorMsg) {
        if (slidHollListBean!=null&&slidHollListBean.getState().equals("s")) {
            sliddata = slidHollListBean.getData();
            if (slidHollListBean.getData().size() > 0) {
                tv_devicename.setText(slidHollListBean.getData().get(0).getName());
                layout.removeAllViews();
                layout.setVisibility(View.VISIBLE);
                String slidname = (String) sharedPreferencesUtils.getData("slidname", "");
                if (sliddata.size() > 0) {
                    if (slidname.equals("")) {
                        tv_devicename.setText(sliddata.get(0).getName());
                    } else {
                        tv_devicename.setText(slidname);
                    }
                    String slidId = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
                    //调接口
                    if (type==1){
                        devicePresenter.obtainDeviceData(slidId, padToken);
                    }else {
                        lightPresenter.obtainLightData(slidId,padToken);
                    }
                }
            }
            //侧滑里面listview适配器
            slidAdapter = new SlidAdapter(this, slidHollListBean.getData());
            listview.setAdapter(slidAdapter);
        } else if (slidHollListBean.getState().equals("-1")){
            if (timer!=null){
                timer.cancel();
            }
            if (task!=null){
                task.cancel();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    //退出登录
    @Override
    public void onGetExitState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        } else if (baseBean.getState().equals("-1")){
            if (timer!=null){
                timer.cancel();
            }
            if (task!=null){
                task.cancel();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    //获取设备管理的数据
    @Override
    public void onGetDeviceData(DeviceBean deviceBean, String errorMsg) {
        if (deviceBean.getState().equals("s")) {
            devicedata = deviceBean.getData();
            if (devicedata.size() > 0) {
                layout.removeAllViews();
                size = devicedata.size() / 6;
                if (devicedata.size() % 6 > 0) {
                    size++;
                }
                if (size > 1) {
                    ViewPagerUtils.setIndicator(this, size, layout);
                }

            }
            //设备管理的适配器
            programAdapter = new ProgramAdapter(this, deviceBean.getData());
            vp_prgram.setAdapter(programAdapter);
            vp_prgram.setCurrentItem(devicecurrentItem);
        } else if (deviceBean.getState().equals("-1")){
            if (timer!=null){
                timer.cancel();
            }
            if (task!=null){
                task.cancel();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    //获取灯光的数据
    @Override
    public void onGetLightData(LightBean lightBean, String errorMsg) {
        if (lightBean.getState().equals("s")) {
            lightdata = lightBean.getData();
            if (lightdata.size() > 0) {
                layoutlight.removeAllViews();
                size = lightdata.size() / 12;
                if (lightdata.size() % 12 > 0) {
                    size++;
                }
                if (flag == 1) {
                    if (size > 1) {
                        ViewPagerUtils.setIndicator(this, size, layoutlight);
                    }
                }
            }
            //灯光的适配器
            lightAdapter = new LightAdapter(this, lightdata);
            vp_light.setAdapter(lightAdapter);
            vp_light.setCurrentItem(currentItem);
        } else if (lightBean.getState().equals("-1")){
            if (timer!=null){
                timer.cancel();
            }
            if (task!=null){
                task.cancel();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    //Eventbus接受
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ConstantEvent event) {
        if (event.getType() == ConstantEvent.SelectedLight) {
            img_device_update.setVisibility(View.GONE);
            type = 2;
            bottomType=2;
            String slidId = (String) sharedPreferencesUtils.getData("slidId", sliddata.get(0).getId() + "");
            String slidName = (String) sharedPreferencesUtils.getData("slidName", sliddata.get(0).getName());
            tv_devicename.setText(slidName);
            layout.setVisibility(View.GONE);
            layoutlight.setVisibility(View.VISIBLE);
            layoutlight.removeAllViews();
            lightPresenter.obtainLightData(slidId, padToken);
            ll_bottom_choice.setBackground(getResources().getDrawable(R.drawable.light));
            vp_prgram.setVisibility(View.GONE);
            vp_light.setVisibility(View.VISIBLE);
        } else if (event.getType() == ConstantEvent.NoticeToOpenOrClose) {
            //id,state,type=1,padToken
            currentItem = vp_light.getCurrentItem();
            String lightposition = event.getLightposition();
            position = Integer.parseInt(lightposition);
            position = position + currentItem * 12;
            String s;
            String state = lightdata.get(position).getState();
            if (!state.equals("stateOut")) {
                if (state.equals("stateOn")) {
                    s = "0";
                } else {
                    s = "1";
                }
                noticeOpenOrClosePresenter.obtainOpenOrCloseState("", "", lightdata.get(position).getId() + "", s, "1", padToken);
            }
        } else if (event.getType() == ConstantEvent.NoticePosition) {
            int position = event.getPosition();
            int currentItem = vp_prgram.getCurrentItem();
            int realposition = position + currentItem * 6;
            String name = devicedata.get(realposition).getName();
            int id = devicedata.get(realposition).getId();
            Intent intent = new Intent(this, PartActivity.class);
            intent.putExtra("partitionId", id + "");
            intent.putExtra("partName", name);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
            timer=null;
        }
        if (task!=null){
            task.cancel();
            task=null;
        }

        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetNoticeOpenOrCloseState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")) {

        } else if (baseBean.getState().equals("-1")){
            if (timer!=null){
                timer.cancel();
            }
            if (task!=null){
                task.cancel();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    @Override
    public void onGetToDownloadState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")) {
            ToastMgr.show("开始下载节目");
        }else if (baseBean.getState().equals("-1")){
            if (timer!=null){
                timer.cancel();
            }
            if (task!=null){
                task.cancel();
            }
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }
}
