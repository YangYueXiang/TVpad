package com.boe.tvpad.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.boe.tvpad.R;
import com.boe.tvpad.activity.ListShowActivity;
import com.boe.tvpad.bean.SlidHollListBean;

import java.util.List;

public class SlidAdapter extends BaseAdapter {
    private Context context;
    private List<SlidHollListBean.DataBean> list;
    public SlidAdapter(ListShowActivity listShowActivity, List<SlidHollListBean.DataBean> arraylist) {
        this.context=listShowActivity;
        this.list=arraylist;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
       SlidAdapter.ViewHolder holder;
        SlidHollListBean.DataBean item = (SlidHollListBean.DataBean) getItem(i);
        if (convertView == null) {
            holder = new SlidAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.lv_slid_holllist, null);
            holder.tv_holllist = convertView.findViewById(R.id.tv_holllist);
            convertView.setTag(holder);
        } else {
            holder = (SlidAdapter.ViewHolder) convertView.getTag();
        }
        holder.tv_holllist.setText(list.get(i).getName());

        if (item.getStatus() == 2) {
            holder.tv_holllist.setBackground(context.getResources().getDrawable(R.drawable.slid_selectitem));
        }else {
            holder.tv_holllist.setBackgroundColor(context.getResources().getColor(R.color.sild));
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_holllist;
    }


}
