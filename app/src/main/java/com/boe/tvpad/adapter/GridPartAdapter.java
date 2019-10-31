package com.boe.tvpad.adapter;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.boe.tvpad.R;
import com.boe.tvpad.bean.PartBean;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.boe.tvpad.utils.ToastMgr;
import org.greenrobot.eventbus.EventBus;
import java.util.List;

public class GridPartAdapter extends BaseAdapter {
    private Context context;
    private List<PartBean.DataBean> list;
    private String type;
    private int i;

    public GridPartAdapter(Context context, List<PartBean.DataBean> list) {
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
        GridPartAdapter.ViewHolder holder;
        if (convertView == null) {
            holder = new GridPartAdapter.ViewHolder();
            type = list.get(position).getType();
            if (type.equals("播放器")) {
                convertView = View.inflate(context, R.layout.gv_part_device_item, null);
                holder.tv_part_device_name = convertView.findViewById(R.id.tv_part_device_name);
                holder.tv_part_device_currentplay = convertView.findViewById(R.id.tv_part_device_currentplay);
                holder.tv_part_device_state = convertView.findViewById(R.id.tv_part_device_state);
                holder.img_device_switch = convertView.findViewById(R.id.img_device_switch);
                holder.rl_part_device_adapter = convertView.findViewById(R.id.rl_part_device_adapter);
                holder.img_partredpoint = convertView.findViewById(R.id.img_partredpoint);
                holder.tv_part_device_unline = convertView.findViewById(R.id.tv_part_device_unline);
            } else {
                convertView = View.inflate(context, R.layout.gv_part_furniture_item, null);
                holder.tv_part_furniture_name = convertView.findViewById(R.id.tv_part_furniture_name);
                holder.tv_part_furniture_unline = convertView.findViewById(R.id.tv_part_furniture_unline);
                holder.rl_part_furniture_adapter = convertView.findViewById(R.id.rl_part_furniture_adapter);
                holder.img_furniture_switch = convertView.findViewById(R.id.img_furniture_switch);
            }
            convertView.setTag(holder);
        } else {
            holder = (GridPartAdapter.ViewHolder) convertView.getTag();
        }
        if (type.equals("播放器")) {
            if (list.get(position).getState().equals("stateOn")) {
                holder.tv_part_device_state.setVisibility(View.VISIBLE);
                holder.rl_part_device_adapter.setBackground(context.getResources().getDrawable(R.drawable.device_open));
                holder.img_device_switch.setImageResource(R.drawable.btu_open);
            } else if (list.get(position).getState().equals("stateOff")) {
                holder.tv_part_device_state.setVisibility(View.GONE);
                holder.rl_part_device_adapter.setBackground(context.getResources().getDrawable(R.drawable.device_close));
                holder.img_device_switch.setImageResource(R.drawable.btu_close);
            } else {
                holder.rl_part_device_adapter.setBackground(context.getResources().getDrawable(R.drawable.device_unline));
                holder.img_device_switch.setVisibility(View.GONE);
                holder.tv_part_device_unline.setVisibility(View.VISIBLE);
                holder.tv_part_device_state.setVisibility(View.GONE);
            }
            holder.tv_part_device_name.setText(list.get(position).getName());

            if (TextUtils.isEmpty(list.get(position).getStore())) {
                if (!TextUtils.isEmpty(list.get(position).getDownloadState())) {
                    if (list.get(position).getDownloadState().equals("下载中")) {
                        holder.tv_part_device_state.setText(list.get(position).getDownloadState());
                    } else {
                        holder.tv_part_device_state.setVisibility(View.GONE);
                        holder.tv_part_device_currentplay.setText(list.get(position).getPlay());
                    }
                }
            } else {
                holder.tv_part_device_state.setText(list.get(position).getStore());
            }
            //是否显示小红点
            if (list.get(position).getRed().equals("1")) {
                holder.img_partredpoint.setVisibility(View.VISIBLE);
            } else {
                holder.img_partredpoint.setVisibility(View.GONE);
            }
            holder.img_device_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastMgr.show("长按以开启或关闭设备");
                }
            });
            holder.img_device_switch.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (!list.get(position).getState().equals("stateOut")) {
                        String state = null;
                        if (list.get(position).getState().equals("stateOn")) {
                            state = "0";
                        } else if (list.get(position).getState().equals("stateOff")) {
                            state = "1";
                        }
                        ConstantEvent constantEvent = new ConstantEvent(4);
                        constantEvent.setDeviceposition(position + "");
                        constantEvent.setDevicestate(state);
                        constantEvent.setDeviceId(list.get(position).getId() + "");
                        EventBus.getDefault().postSticky(constantEvent);
                    }
                    return true;
                }
            });
        } else {
            if (list.get(position).getState().equals("stateOn")) {
                holder.img_furniture_switch.setImageResource(R.drawable.btu_open);
                holder.tv_part_furniture_unline.setVisibility(View.GONE);
                holder.rl_part_furniture_adapter.setBackground(context.getResources().getDrawable(R.drawable.furniture_open));
            } else if (list.get(position).getState().equals("stateOff")) {
                holder.img_furniture_switch.setImageResource(R.drawable.btu_close);
                holder.tv_part_furniture_unline.setVisibility(View.GONE);
                holder.rl_part_furniture_adapter.setBackground(context.getResources().getDrawable(R.drawable.furniture_close));
            } else {
                holder.img_furniture_switch.setVisibility(View.GONE);
                holder.tv_part_furniture_unline.setVisibility(View.VISIBLE);
                holder.rl_part_furniture_adapter.setBackground(context.getResources().getDrawable(R.drawable.furniture_unline));
            }
            holder.tv_part_furniture_name.setText(list.get(position).getName());
            holder.img_furniture_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastMgr.show("长按以开启或关闭开关");
                }
            });
            holder.img_furniture_switch.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (!list.get(position).getState().equals("stateOut")) {
                        String state = null;
                        if (list.get(position).getState().equals("stateOn")) {
                            state = "0";
                        } else if (list.get(position).getState().equals("stateOff")) {
                            state = "1";
                        }
                        ConstantEvent constantEvent = new ConstantEvent(5);
                        constantEvent.setDeviceposition(position + "");
                        constantEvent.setDevicestate(state);
                        constantEvent.setDeviceId(list.get(position).getId() + "");
                        EventBus.getDefault().postSticky(constantEvent);
                    }
                    return true;
                }
            });
        }


        return convertView;
    }

    static class ViewHolder {
        TextView tv_part_device_name;
        TextView tv_part_furniture_unline;
        TextView tv_part_furniture_name;
        TextView tv_part_device_currentplay;
        TextView tv_part_device_state;
        ImageView img_device_switch;
        RelativeLayout rl_part_device_adapter;
        RelativeLayout rl_part_furniture_adapter;
        ImageView img_partredpoint;
        TextView tv_part_device_unline;
        ImageView img_furniture_switch;
    }
}
