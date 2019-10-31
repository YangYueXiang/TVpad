package com.boe.tvpad.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.boe.tvpad.R;
import com.boe.tvpad.bean.LightBean;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.boe.tvpad.customView.MyGridView;
import com.boe.tvpad.utils.LongClickUtils;
import com.boe.tvpad.utils.ToastMgr;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.greenrobot.eventbus.EventBus;

public class LightAdapter extends PagerAdapter {
    private Context context;
    private List<LightBean.DataBean> list;
    private int mChildCount;
    private GridLightAdapter gridLightAdapter;

    public LightAdapter(Context context, List<LightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        final int count = (int) Math.ceil(list.size() / 12.0f);
        return count;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View inflate = View.inflate(context, R.layout.vp_light_item, null);
        int start = 0;
        int end = 0;
        MyGridView gvLignt = inflate.findViewById(R.id.gv_light);

        start = (position * 12);
        end = ((position * 12) + 12);

        List<LightBean.DataBean> pageList = new ArrayList<>();
        for (int i = start; i < end && i < list.size(); i++) {
            pageList.add(list.get(i));
        }
        gridLightAdapter = new GridLightAdapter(context, pageList);
        gvLignt.setAdapter(gridLightAdapter);
        gvLignt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastMgr.show("长按以开启或关闭灯光");
            }
        });
        gvLignt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //通知调接口请求后台打开或关闭灯光
                ConstantEvent constantEvent = new ConstantEvent(3);
                constantEvent.setLightposition(i+"");
                EventBus.getDefault().postSticky(constantEvent);
                return true;
            }
        });
       /* LongClickUtils.setLongClick(new Handler(), gvLignt.getFocusedChild(), 300, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //通知调接口请求后台打开或关闭灯光
                ConstantEvent constantEvent = new ConstantEvent(3);
                constantEvent.setLightposition(gvLignt.getCheckedItemPosition()+"");
                EventBus.getDefault().postSticky(constantEvent);
                return true;
            }
        });*/

        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
