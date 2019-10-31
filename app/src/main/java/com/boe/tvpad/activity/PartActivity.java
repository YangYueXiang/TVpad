package com.boe.tvpad.activity;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.boe.tvpad.R;
import com.boe.tvpad.adapter.PartAdapter;
import com.boe.tvpad.base.BaseActivity;
import com.boe.tvpad.base.BaseBean;
import com.boe.tvpad.bean.PartBean;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.boe.tvpad.dialog.DeleteDialog;
import com.boe.tvpad.presenter.presenterImpl.NoticeOpenOrClosePresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.PartPresenterImpl;
import com.boe.tvpad.presenter.presenterImpl.ToDownloadPresenterImpl;
import com.boe.tvpad.utils.SharedPreferencesUtils;
import com.boe.tvpad.utils.ToastMgr;
import com.boe.tvpad.utils.ViewPagerUtils;
import com.boe.tvpad.view.NoticeOpenOrCloseView;
import com.boe.tvpad.view.PartView;
import com.boe.tvpad.view.ToDownloadView;
import com.umeng.analytics.MobclickAgent;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import androidx.viewpager.widget.ViewPager;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PartActivity extends BaseActivity implements View.OnClickListener, PartView , NoticeOpenOrCloseView, ToDownloadView {
    //是否下载展区内所有展品的节目内容？
    private ImageView img_back;
    private LinearLayout ll_bottom_choice;
    private LinearLayout ll_bottom_device;
    private LinearLayout ll_bottom_light;
    private ViewPager vp_part;
    private PartAdapter partAdapter;
    private PartPresenterImpl partPresenter;
    private String partitionId;
    private SharedPreferencesUtils sharedPreferencesUtils;
    private String padToken;
    private LinearLayout layout_part;
    private int size;
    private int currentItem;
    private NoticeOpenOrClosePresenterImpl noticeOpenOrClosePresenter;
    private List<PartBean.DataBean> partdata;
    private ImageView img_part_on;
    private ImageView img_part_off;
    private ImageView img_part_update;
    private ToDownloadPresenterImpl toDownloadPresenter;
    private Timer timer;
    private TimerTask task;
    private TextView tv_partname;
    private String partName;
    private Vibrator vibrator;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_part;
    }

    @Override
    protected int getTitleBarId() {
        return 0;
    }

    @Override
    protected void initView() {
        tv_partname.setText(partName);
        partPresenter.obtainPartData(partitionId, padToken);
        //计时器
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                    //调接口
                //调接口获取分区的数据
                partPresenter.obtainPartData(partitionId, padToken);
                }
        };
        timer.schedule(task, 1000, 2000);
        //调接口获取分区的数据
        partPresenter.obtainPartData(partitionId, padToken);
        img_back.setOnClickListener(this);
        ll_bottom_device.setOnClickListener(this);
        ll_bottom_light.setOnClickListener(this);
        img_part_on.setOnClickListener(this);
        img_part_off.setOnClickListener(this);
        img_part_update.setOnClickListener(this);
        tv_partname.setOnClickListener(this);
        vp_part.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < size; i++) {
                    int childCount = layout_part.getChildCount();
                    if (childCount > 0) {
                        View view = layout_part.getChildAt(i);
                        if (i == position)
                            view.setBackgroundResource(R.drawable.view1);
                        else
                            view.setBackgroundResource(R.drawable.view2);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                currentItem=position;
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
        img_back = findViewById(R.id.img_back);
        ll_bottom_choice = findViewById(R.id.ll_part_bottom_choice);
        ll_bottom_device = findViewById(R.id.ll_part_bottom_device);
        ll_bottom_light = findViewById(R.id.ll_part_bottom_light);
        vp_part = findViewById(R.id.vp_part);
        layout_part = findViewById(R.id.layout_part);
        img_part_on = findViewById(R.id.img_part_on);
        img_part_off = findViewById(R.id.img_part_off);
        img_part_update = findViewById(R.id.img_part_update);
        tv_partname = findViewById(R.id.tv_partname);
        initPresenter();
        Intent intent = getIntent();
        partitionId = intent.getStringExtra("partitionId");
        partName = intent.getStringExtra("partName");
        padToken = (String) sharedPreferencesUtils.getData("padToken", "");
    }

    private void initPresenter() {
        partPresenter = new PartPresenterImpl(this);
        noticeOpenOrClosePresenter = new NoticeOpenOrClosePresenterImpl(this);
        toDownloadPresenter = new ToDownloadPresenterImpl(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_part_bottom_device:
                ll_bottom_choice.setBackground(getResources().getDrawable(R.drawable.device));
                break;
            case R.id.ll_part_bottom_light:
                EventBus.getDefault().postSticky(new ConstantEvent(1));
                finish();
                break;
            case R.id.img_part_on:
                //弹框
                new DeleteDialog(this).builder()
                        .setMsg("是否开启展区内所有展品？")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                noticeOpenOrClosePresenter.obtainOpenOrCloseState("", partitionId, "", "1", "2", padToken);
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();

                break;
            case R.id.img_part_off:
                //弹框
                new DeleteDialog(this).builder()
                        .setMsg("是否关闭展区内所有展品？")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                noticeOpenOrClosePresenter.obtainOpenOrCloseState("", partitionId, "", "0", "2", padToken);
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();

                break;
            case R.id.img_part_update:
                //弹框
                new DeleteDialog(this).builder()
                        .setMsg("是否下载展区内所有展品的节目内容？")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toDownloadPresenter.obtainallholls("",partitionId,"",padToken);
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                break;
            case R.id.tv_partname:
                finish();
                break;
        }
    }

    @Override
    public void onGetPartData(PartBean partBean, String errorMsg) {
        if (partBean.getState().equals("s")) {
            partdata = partBean.getData();
                //展区的适配器
                partAdapter = new PartAdapter(this, partBean.getData());
                vp_part.setAdapter(partAdapter);
                vp_part.setCurrentItem(currentItem);

            if (partBean.getData().size() > 0) {
                layout_part.removeAllViews();
                size = partBean.getData().size() / 6;
                if (partBean.getData().size() % 6 > 0) {
                    size++;
                }
                if (size>1){
                    ViewPagerUtils.setIndicator(this, size, layout_part);
                }
            } else {
            }
        }else if (partBean.getState().equals("-1")){
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMess(ConstantEvent event){
          if (event.getType() == ConstantEvent.OpenOrCloseDevice) {
            //通知后台调接口
            String deviceId = event.getDeviceId();
            String devicestate = event.getDevicestate();
            noticeOpenOrClosePresenter.obtainOpenOrCloseState("", "", deviceId, devicestate, "2", padToken);
        }else if (event.getType()==ConstantEvent.OpenOrCloseSwitch){
              //通知后台调接口
              String deviceId = event.getDeviceId();
              String devicestate = event.getDevicestate();
              noticeOpenOrClosePresenter.obtainOpenOrCloseState("", "", deviceId, devicestate, "2", padToken);
          }else if (event.getType()== ConstantEvent.NoticePartPosition){
              int partposition = event.getPartposition();
              partposition = partposition + currentItem * 6;
              if (partdata.get(partposition).getState().equals("stateOn")&&partdata.get(partposition).getType().equals("播放器")) {
                  if (!partdata.get(partposition).getDownloadState().equals("下载中")) {
                      Intent intent = new Intent(this, CurrentPlayActivity.class);
                      intent.putExtra("name", partdata.get(partposition).getPlay());
                      intent.putExtra("type", partdata.get(partposition).getPlayType());
                      intent.putExtra("filepath", partdata.get(partposition).getFilepath());
                      intent.putExtra("exhibitsId", partdata.get(partposition).getId() + "");
                      intent.putExtra("programId", partdata.get(partposition).getProgramId());
                      startActivity(intent);
                  }else {
                      ToastMgr.show("该设备正在下载，请勿操作");
                  }
              }
          }
    }

    /**
     * 震动
     */
    private void startVibrator() {
        /**
         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
         *
         */
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = { 1000, 1000, 0, 0 }; // 停止 开启 停止 开启
        vibrator.vibrate(pattern, 0);
    }

    @Override
    public void onGetNoticeOpenOrCloseState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")){
           startVibrator();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
       if (task!=null){
           task.cancel();
       }
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetToDownloadState(BaseBean baseBean, String errorMsg) {
        if (baseBean.getState().equals("s")){
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
