package com.sunkai.stickyheaderrecyleview.view.viewhodler;

/**
 * Created by sunkai on 2016/12/1.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunkai.stickyheaderrecyleview.R;
import com.sunkai.stickyheaderrecyleview.view.adapter.NewWifiListAdapter;

/**
 * wifi 类型
 */
public class HeaderHolder extends RecyclerView.ViewHolder {

    TextView text;
    ImageView tips;

    public HeaderHolder(View itemView) {
        super(itemView);
        text = (TextView) itemView.findViewById(R.id.section_title);
        tips = (ImageView) itemView.findViewById(R.id.section_tips);
        itemView.setTag(NewWifiListAdapter.ListType.HEADER);
        text.setText("header:");
    }

    /**
     * @param wifiType 0 free,1 open ，2 other
     */
    public void initData(int wifiType) {
//        tips.setVisibility(View.GONE);
        text.setText("header:" + wifiType);
    }
}


