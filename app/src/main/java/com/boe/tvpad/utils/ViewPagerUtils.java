package com.boe.tvpad.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.boe.tvpad.R;

public class ViewPagerUtils {
    public static void setIndicator(Context context,int data,LinearLayout layout) {
        //添加小圆点,根据你数据data的大小来定义个数
        for (int i = 0; i < data; i++) {
            View view = new View(context);
            //重新设置view的大小
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 10);
            layoutParams.width = 25;
            layoutParams.height = 10;
            //设置间距
            layoutParams.rightMargin = 10;
            //把这个LayoutParams设置给view
            view.setLayoutParams(layoutParams);
            //设置第一个为选中状态
            if (i == 0) {
                view.setBackgroundResource(R.drawable.view1);
            } else
                view.setBackgroundResource(R.drawable.view2);
            //把view添加到LinearLayout
            layout.addView(view);
        }
    }
}
