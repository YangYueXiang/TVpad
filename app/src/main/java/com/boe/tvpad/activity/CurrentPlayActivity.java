package com.boe.tvpad.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boe.tvpad.R;
import com.boe.tvpad.adapter.SlidProgramListAdapter;
import com.boe.tvpad.base.BaseActivity;
import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.ProgramListBean;
import com.boe.tvpad.bean.SpSaveDefault;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.boe.tvpad.customView.MyListView;
import com.boe.tvpad.dialog.DeleteDialog;
import com.boe.tvpad.presenter.presenterImpl.ControllerPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.DeleteProgramPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.PlayProgramPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.ProgramListPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.ToDownloadPresenterImpl;
import com.boe.tvpad.utils.SharedPreferencesUtils;
import com.boe.tvpad.utils.ToastMgr;
import com.boe.tvpad.view.CurrentPlayView;
import com.boe.tvpad.view.DeleteProgramView;
import com.boe.tvpad.view.ToDownloadView;
import com.bumptech.glide.Glide;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class CurrentPlayActivity extends BaseActivity implements View.OnClickListener, CurrentPlayView, DeleteProgramView, ToDownloadView {

    private ImageView img_currentplay;
    private RelativeLayout rl_header;
    private Boolean showTitle = false;
    private LinearLayout ll_left_drawlayout;
    private ImageView img_slidright;
    private DrawerLayout currentplay_drawlayout;
    private ActionBarDrawerToggle drawerbar;
    private MyListView programlistview;
    private ArrayList<ProgramListBean> programListBeans;
    private SlidProgramListAdapter slidProgramListAdapter;
    private String name;
    private String type;
    private String filepath;
    private TextView tv_title;
    private RelativeLayout rl_picturescontroller;
    private RelativeLayout rl_videocontrollewr;
    private ImageView fanhui;
    private ProgramListPresenterImpl programListPresenter;
    private String exhibitsId;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String padToken;
    private DeleteProgramPresenterImpl deleteProgramPresenter;
    private List<ProgramListBean.DataBean> data;
    private ImageView img_videoplay;
    private ImageView img_videochongbo;
    private ImageView img_auto;
    private ImageView img_pictureschongbo;
    private ImageView img_shangyige;
    private ImageView img_xiayige;
    private PlayProgramPresenterImpl playProgramPresenter;
    private String programType;
    private String titlename;
    private String imgurl;
    private ControllerPresenterImpl controllerPresenter;
    private String programId;
    private int auto = 1;
    private int play = 1;
    private ImageView img_download;
    private ToDownloadPresenterImpl toDownloadPresenter;
    private int flag = 0;
    private int change;
    private ImageView img_noplay;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_current_play;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        //视频播放按钮的状态和图片集自动播放的状态
        String videocontroller = (String) sharedPreferencesUtils.getData("videocontroller", "play");
        String picturesauto = (String) sharedPreferencesUtils.getData("picturesauto", "yes");
        if (videocontroller.equals("play")) {
            play = 1;
            img_videoplay.setImageResource(R.drawable.pause);
        } else {
            play = 0;
            img_videoplay.setImageResource(R.drawable.play);
        }
        if (picturesauto.equals("yes")) {
            auto = 1;
            img_auto.setImageResource(R.drawable.icon_auto_on);
        } else {
            auto = 0;
            img_auto.setImageResource(R.drawable.icon_auto_off);
        }

        //显示图片
        if (filepath != null) {
            img_noplay.setVisibility(View.GONE);
            Glide.with(this)
                    .load(Uri.parse(filepath))
                    .into(img_currentplay);
            tv_title.setText(name);
            if (type.equals("视频")) {
                rl_videocontrollewr.setVisibility(View.VISIBLE);
                rl_picturescontroller.setVisibility(View.GONE);
            } else if (type.equals("图片集")) {
                rl_picturescontroller.setVisibility(View.VISIBLE);
                rl_videocontrollewr.setVisibility(View.GONE);
            } else {
                rl_picturescontroller.setVisibility(View.GONE);
                rl_videocontrollewr.setVisibility(View.GONE);
            }
        }

        //调右面侧滑节目列表
        programListPresenter.obtainProgramList(exhibitsId, padToken);
        //点击显示侧滑页面
        img_slidright.setOnClickListener(this);
        fanhui.setOnClickListener(this);
        //设置菜单内容之外其他区域的背景色
        currentplay_drawlayout.setScrimColor(getResources().getColor(R.color.sileback));
        //设置开关监听
        drawerbar = new ActionBarDrawerToggle(this, currentplay_drawlayout, R.string.open, R.string.close);
        currentplay_drawlayout.setDrawerListener(drawerbar);
        currentplay_drawlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        //侧滑中listview滑动删除
        programlistview.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                //弹框
                new DeleteDialog(CurrentPlayActivity.this).builder()
                        .setMsg("是否删除该节目？")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //调用接口通知后台删除
                                deleteProgramPresenter.obtainDeleteState(CurrentPlayActivity.this.data.get(index).getId() + "", exhibitsId, padToken);
                                CurrentPlayActivity.this.data.remove(CurrentPlayActivity.this.data.get(index));
                                slidProgramListAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();

            }
        });
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        sharedPreferencesUtils = new SharedPreferencesUtils(this);
        padToken = (String) sharedPreferencesUtils.getData("padToken", "");
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        type = intent.getStringExtra("type");
        filepath = intent.getStringExtra("filepath");
        exhibitsId = intent.getStringExtra("exhibitsId");
        Log.i("exhibitsId",exhibitsId+"");
        programId = intent.getStringExtra("programId");

        img_currentplay = findViewById(R.id.img_currentplay);
        rl_header = findViewById(R.id.rl_header);
        ll_left_drawlayout = findViewById(R.id.ll_leftdrawlayout);
        img_slidright = findViewById(R.id.img_slidright);
        currentplay_drawlayout = findViewById(R.id.currentplay_drawlayout);
        programlistview = findViewById(R.id.slidright_program_listview);
        tv_title = findViewById(R.id.tv_title);
        rl_picturescontroller = findViewById(R.id.rl_picturescontroller);
        rl_videocontrollewr = findViewById(R.id.rl_videocontroller);
        fanhui = findViewById(R.id.fanhui);
        img_videoplay = findViewById(R.id.img_videoplay);
        img_videochongbo = findViewById(R.id.img_videochongbo);
        img_auto = findViewById(R.id.img_auto);
        img_pictureschongbo = findViewById(R.id.img_pictureschongbo);
        img_shangyige = findViewById(R.id.img_shangyige);
        img_xiayige = findViewById(R.id.img_xiayige);
        img_download = findViewById(R.id.img_download);
        img_noplay = findViewById(R.id.img_noplay);
        img_download.setOnClickListener(this);
        img_videoplay.setOnClickListener(this);
        img_videochongbo.setOnClickListener(this);
        img_auto.setOnClickListener(this);
        img_pictureschongbo.setOnClickListener(this);
        img_shangyige.setOnClickListener(this);
        img_xiayige.setOnClickListener(this);
        initPresenter();
    }

    private void initPresenter() {
        programListPresenter = new ProgramListPresenterImpl(this);
        deleteProgramPresenter = new DeleteProgramPresenterImpl(this);
        playProgramPresenter = new PlayProgramPresenterImpl(this);
        controllerPresenter = new ControllerPresenterImpl(this);
        toDownloadPresenter = new ToDownloadPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_slidright:
                if (currentplay_drawlayout.isDrawerOpen(ll_left_drawlayout)) {
                    currentplay_drawlayout.closeDrawer(ll_left_drawlayout);
                } else {
                    currentplay_drawlayout.openDrawer(ll_left_drawlayout);
                }
                //调右面侧滑节目列表
                programListPresenter.obtainProgramList(exhibitsId, padToken);
                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.img_auto:
                change = 3;
                if (auto == 1) {
                    auto = 0;
                    sharedPreferencesUtils.putData("picturesauto", "no");
                } else {
                    auto = 1;
                    sharedPreferencesUtils.putData("picturesauto", "yes");
                }
                controllerPresenter.obtainControllerState(exhibitsId, programId, "", auto + "", padToken);
                break;
            case R.id.img_shangyige:
                auto=0;
                change = 2;
                controllerPresenter.obtainControllerState(exhibitsId, programId, "0", "", padToken);
                break;
            case R.id.img_pictureschongbo:
                auto = 1;
                change = 3;
                img_auto.setImageResource(R.drawable.icon_auto_on);
                controllerPresenter.obtainControllerState(exhibitsId, programId, "2", "", padToken);
                break;
            case R.id.img_xiayige:
                auto=0;
                change = 2;
                controllerPresenter.obtainControllerState(exhibitsId, programId, "1", "", padToken);
                break;
            case R.id.img_videoplay:
                if (play == 1) {
                    play = 0;
                    sharedPreferencesUtils.putData("videocontroller", "pause");
                } else {
                    play = 1;
                    sharedPreferencesUtils.putData("videocontroller", "play");
                }
                controllerPresenter.obtainControllerState(exhibitsId, programId, play + "", "", padToken);
                break;
            case R.id.img_videochongbo:
                play = 2;
                controllerPresenter.obtainControllerState(exhibitsId, programId, "2", "", padToken);
                break;
            case R.id.img_download:
                new DeleteDialog(this).builder()
                        .setMsg("下载过程中展品将停止播放，是否确认？")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toDownloadPresenter.obtainallholls("", "", exhibitsId, padToken);
                                finish();
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();

                break;
        }
    }

    @Override
    public void onGetProgramList(ProgramListBean programListBean, String errorMsg) {
        if (programListBean.getState().equals("s")) {
          //  ToastMgr.show("成功了哦");
            data = programListBean.getData();
            //侧滑节目列表的适配器
            slidProgramListAdapter = new SlidProgramListAdapter(this, programListBean.getData());
            programlistview.setAdapter(slidProgramListAdapter);
        } else if (programListBean.getState().equals("-1")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }else if (programListBean.getState().equals("e")){
         //   ToastMgr.show("失败了哦");
        }
    }

    @Override
    public void toPlayProgramState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")) {
            if (flag == 1) {
                img_noplay.setVisibility(View.GONE);
                //显示图片
                Glide.with(this)
                        .load(Uri.parse(imgurl))
                        .into(img_currentplay);

                tv_title.setText(titlename);
                if (programType.equals("视频")) {
                    play = 1;
                    img_videoplay.setImageResource(R.drawable.pause);
                    rl_videocontrollewr.setVisibility(View.VISIBLE);
                    rl_picturescontroller.setVisibility(View.GONE);
                } else if (programType.equals("图片集")) {
                    rl_picturescontroller.setVisibility(View.VISIBLE);
                    rl_videocontrollewr.setVisibility(View.GONE);
                } else {
                    rl_picturescontroller.setVisibility(View.GONE);
                    rl_videocontrollewr.setVisibility(View.GONE);
                }
                //关闭侧滑
                currentplay_drawlayout.closeDrawer(ll_left_drawlayout);
            }

        } else if (baseBean.getState().equals("-1")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    @Override
    public void controllerState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")) {
            if (play == 1) {
                img_videoplay.setImageResource(R.drawable.pause);
            } else if (play == 0) {
                img_videoplay.setImageResource(R.drawable.play);
            } else if (play == 2) {
                play = 1;
                img_videoplay.setImageResource(R.drawable.pause);
            }

            if (auto == 0) {
                img_auto.setImageResource(R.drawable.icon_auto_off);
            } else if (auto == 1) {
                img_auto.setImageResource(R.drawable.icon_auto_on);
            }

            if (change == 2) {
                auto = 0;
                img_auto.setImageResource(R.drawable.icon_auto_off);
            }

        } else if (baseBean.getState().equals("-1")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ConstantEvent event) {
        if (event.getType() == ConstantEvent.ChangeCurrentPlayImg) {
            flag = 1;
            programType = event.getProgramType();
            titlename = event.getName();
            imgurl = event.getImgurl();
            programId = event.getPrigramId();
            //网络请求告知要播放哪个节目
            playProgramPresenter.toPlayProgramState(exhibitsId, event.getPrigramId(), padToken);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetDeleteState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")) {
            //刷新适配器
          //  ToastMgr.show("删除成功");
        } else if (baseBean.getState().equals("-1")){
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
        } else if (baseBean.getState().equals("-1")){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
            sharedPreferencesUtils.clean(getContext());
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
