package com.boe.tvpad.adapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.boe.tvpad.R;
import com.boe.tvpad.activity.CurrentPlayActivity;
import com.boe.tvpad.bean.PartBean;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.boe.tvpad.customView.MyGridView;
import com.boe.tvpad.utils.ToastMgr;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class PartAdapter extends PagerAdapter {
    private Context context;
    private List<PartBean.DataBean> list;
    private int mChildCount = 0;
    private GridPartAdapter gridPartAdapter;

    public PartAdapter(Context context, List<PartBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object)   {
        Log.i("terr","terr");
        if ( mChildCount > 0) {
            mChildCount --;
            return POSITION_NONE;
        }
        return super.getItemPosition(0);
    }

    @Override
    public int getCount() {
        final int count = (int) Math.ceil(list.size() / 6.0f);
        return count;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View inflate = View.inflate(context, R.layout.vp_part_item, null);
        int start = 0;
        int end = 0;
        MyGridView gvLignt = inflate.findViewById(R.id.gv_part);

        start = (position * 6);
        end = ((position * 6) + 6);

        List<PartBean.DataBean> pageList = new ArrayList<>();
        for (int i = start; i < end && i < list.size(); i++) {
            pageList.add(list.get(i));
        }
        gridPartAdapter = new GridPartAdapter(context, pageList);
        gvLignt.setAdapter(gridPartAdapter);
        gvLignt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        ConstantEvent constantEvent = new ConstantEvent(7);
                        constantEvent.setPartposition(i);
                        EventBus.getDefault().postSticky(constantEvent);
            }
        });
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
