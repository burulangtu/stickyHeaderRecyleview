package com.sunkai.stickyheaderrecyleview.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sunkai.stickyheaderrecyleview.R;
import com.sunkai.stickyheaderrecyleview.view.viewhodler.HeaderHolder;
import com.sunkai.stickyheaderrecyleview.view.viewhodler.ContentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunkai on 2016/12/1.
 */

public class NewWifiListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> listItems = new ArrayList<>();

    private WifiItemOnClickListener wifiItemOnClickListener;

    public NewWifiListAdapter() {
        ListItem item = new ListItem();
        item.type = 1;

        listItems.add(item);

        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }


        item = new ListItem();
        item.type = 1;
        listItems.add(item);

        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }

        item = new ListItem();
        item.type = 1;
        listItems.add(item);

        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }

        item = new ListItem();
        item.type = 1;
        listItems.add(item);
        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }
        item = new ListItem();
        item.type = 1;
        listItems.add(item);
        for (int i = 0; i < 5; i++) {
            item = new ListItem();
            item.type = 2;
            listItems.add(item);
        }


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        View view;
        switch (viewType) {
            case 1:// wifiType
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
                viewHolder = new HeaderHolder(view);
                break;
            case 2:// wifi
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
                viewHolder = new ContentHolder(view);
                break;
        }
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        if (holder instanceof HeaderHolder) {
            ((HeaderHolder) holder).initData(position);
        } else if (holder instanceof ContentHolder) {


        }
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        ListItem item = listItems.get(position);
        return item.type;
    }

    public View updateHeaderView(@NonNull ViewGroup parentView, int position) {
        View view;
        if (parentView.getChildCount() > 0) {
            view = parentView.getChildAt(0);
        } else {
            view = LayoutInflater.from(parentView.getContext()).inflate(R.layout.item_section, parentView, false);
            parentView.addView(view);

        }
        HeaderHolder wifiTypeHolder;
        if (view.getTag() != null) {
            wifiTypeHolder = (HeaderHolder) view.getTag();
        } else {
            wifiTypeHolder = new HeaderHolder(view);
            view.setTag(wifiTypeHolder);
        }
        view.setTranslationY(0);
        wifiTypeHolder.initData(position);
        return view;
    }

    private static class ListItem {
        int type;

    }

    public interface WifiItemOnClickListener {
        void onClick();
    }

    private static class AdViewParent extends FrameLayout {

        public AdViewParent(Context context) {
            super(context);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Log.e("SK", "onInterceptTouchEvent");
            onTouchEvent(ev);
            super.onInterceptTouchEvent(ev);
            return false;

        }

    }

    public enum ListType {
        HEADER, CONTEXT
    }
}
