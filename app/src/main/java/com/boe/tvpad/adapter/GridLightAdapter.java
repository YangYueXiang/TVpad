package com.boe.tvpad.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.boe.tvpad.R;
import com.boe.tvpad.bean.LightBean;
import java.util.List;

public class GridLightAdapter extends BaseAdapter {
    private Context context;
    private List<LightBean.DataBean> list;
    public GridLightAdapter(Context context, List<LightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridLightAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new GridLightAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.gv_light_item, null);
            holder.tv_lightitemname = convertView.findViewById(R.id.tv_lightitemname);
            holder.rl_lightback= convertView.findViewById(R.id.rl_lightback);
            convertView.setTag(holder);
        } else {
            holder = (GridLightAdapter.ViewHolder) convertView.getTag();
        }
        holder.tv_lightitemname.setText(list.get(position).getName());
        if (list.get(position).getState().equals("stateOff")){
            holder.rl_lightback.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.lightoff));
        }else if (list.get(position).getState().equals("stateOn")){
            holder.rl_lightback.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.lighton));
        }else {
            holder.rl_lightback.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.unlinelight));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tv_lightitemname;
        RelativeLayout rl_lightback;
    }
}
