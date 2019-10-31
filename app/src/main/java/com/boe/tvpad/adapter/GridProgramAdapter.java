package com.boe.tvpad.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.boe.tvpad.R;
import com.boe.tvpad.bean.DeviceBean;
import java.util.List;

public class GridProgramAdapter extends BaseAdapter {
    private Context context;
    private List<DeviceBean.DataBean> list;

    public GridProgramAdapter(Context context, List<DeviceBean.DataBean> list) {
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
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.gv_program_item, null);
            holder.programName = convertView.findViewById(R.id.tv_program_name);
            holder.programOn = convertView.findViewById(R.id.tv_program_on);
            holder.programAll = convertView.findViewById(R.id.tv_program_all);
            holder.redPoint=convertView.findViewById(R.id.img_redpoint);
            holder.rl_deviceback=convertView.findViewById(R.id.rl_deviceback);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.programName.setText(list.get(position).getName());
        holder.programOn.setText(list.get(position).getCountOn()+"/");
        holder.programAll.setText(list.get(position).getCount()+"");
        if (String.valueOf(list.get(position).getCountOn()).equals("0")){
            holder.rl_deviceback.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.programbg_off));
        }else {
            holder.rl_deviceback.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.programbg_on));
        }
        if (list.get(position).getRed().equals("1")){
            holder.redPoint.setVisibility(View.VISIBLE);
        }else {
            holder.redPoint.setVisibility(View.GONE);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView programName;
        TextView programOn;
        TextView programAll;
        ImageView redPoint;
        RelativeLayout rl_deviceback;
    }

}
