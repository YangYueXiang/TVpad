package com.boe.tvpad.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boe.tvpad.R;
import com.boe.tvpad.activity.CurrentPlayActivity;
import com.boe.tvpad.bean.ProgramListBean;
import com.boe.tvpad.bean.eventBusBean.ConstantEvent;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class SlidProgramListAdapter extends BaseAdapter {
    private Context context;
    private List<ProgramListBean.DataBean> list;
    public SlidProgramListAdapter(CurrentPlayActivity currentPlayActivity, List<ProgramListBean.DataBean> arraylist) {
        this.context=currentPlayActivity;
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
        SlidProgramListAdapter.ViewHolder holder;
     //   ProgramListBean.DataBean item = (ProgramListBean.DataBean) getItem(i);
        if (convertView == null) {
            holder = new SlidProgramListAdapter.ViewHolder();
            convertView = View.inflate(context, R.layout.lv_slid_programlist, null);
            holder.tv_slidprogramname = convertView.findViewById(R.id.tv_slidprogramname);
            holder.tv_slidprogramsize = convertView.findViewById(R.id.tv_slidprogramsize);
            holder.tv_slidprogramtimelength = convertView.findViewById(R.id.tv_slidprogramtimelength);
            holder.tv_slidprogramstate = convertView.findViewById(R.id.tv_slidprogramstate);
            holder.img_defaultplay=convertView.findViewById(R.id.img_defaultplay);
            holder.img_play=convertView.findViewById(R.id.img_play);
            holder.img_littlecover=convertView.findViewById(R.id.img_littlecover);
            convertView.setTag(holder);
        } else {
            holder = (SlidProgramListAdapter.ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(list.get(i).getThumbnailName()).into(holder.img_littlecover);
        holder.tv_slidprogramname.setText(list.get(i).getName());
        holder.tv_slidprogramsize.setText(list.get(i).getFlow()+"KB");
        if (list.get(i).getType().equals("图片集")){
            holder.tv_slidprogramtimelength.setVisibility(View.VISIBLE);
            holder.tv_slidprogramtimelength.setText(list.get(i).getPhotoNum()+"张");
            holder.tv_slidprogramsize.setText(list.get(i).getPhotoFlow()+"KB");
        }else if (list.get(i).getType().equals("视频")){
            holder.tv_slidprogramtimelength.setVisibility(View.VISIBLE);
            holder.tv_slidprogramtimelength.setText(list.get(i).getDuration());
        }else {
            holder.tv_slidprogramtimelength.setVisibility(View.GONE);
        }

       if (list.get(i).getDownloadType().equals("0")){
            holder.tv_slidprogramstate.setText("未下载");
        }else {
           holder.tv_slidprogramstate.setText("");
       }

       if (list.get(i).getDefaultPlay().equals("0")){
           holder.img_defaultplay.setImageDrawable(context.getResources().getDrawable(R.drawable.default_no));
       }else {
           holder.img_defaultplay.setImageDrawable(context.getResources().getDrawable(R.drawable.default_yes));
       }
       //点击条目播放
        holder.img_littlecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!list.get(i).getDownloadType().equals("0")){
                    //类型，大图链接传给currentPlayActivity
                    ConstantEvent constantEvent = new ConstantEvent(2);
                    if (list.get(i).getType().equals("图片")){
                        constantEvent.setImgurl(list.get(i).getTempFilename());
                    }else {
                        constantEvent.setImgurl(list.get(i).getCoverName());
                    }
                    constantEvent.setPrigramId(list.get(i).getId()+"");
                    constantEvent.setProgramType(list.get(i).getType());
                    constantEvent.setName(list.get(i).getName());
                    EventBus.getDefault().postSticky(constantEvent);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView tv_slidprogramname;
        TextView tv_slidprogramsize;
        TextView tv_slidprogramtimelength;
        TextView tv_slidprogramstate;
        ImageView img_defaultplay;
        ImageView img_play;
        ImageView img_littlecover;
    }
}
