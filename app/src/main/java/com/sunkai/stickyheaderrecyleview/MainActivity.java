package com.sunkai.stickyheaderrecyleview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sunkai.stickyheaderrecyleview.view.adapter.NewWifiListAdapter;

public class MainActivity extends AppCompatActivity {
    private NewWifiListAdapter newWifiListAdapter = new NewWifiListAdapter();
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = new RecyclerView(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(newWifiListAdapter);
        final ViewGroup viewGroup = new FrameLayout(getApplicationContext());
        headerView = newWifiListAdapter.updateHeaderView(viewGroup, 0);
        FrameLayout frameLayout = new FrameLayout(getApplicationContext());
        frameLayout.addView(recyclerView);
        frameLayout.addView(viewGroup);

        swipeRefreshLayout.addView(frameLayout);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                View stickyInfoView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2, 5);
                int position = recyclerView.getChildAdapterPosition(stickyInfoView);
                if (stickyInfoView == null) {
                    return;
                }
                NewWifiListAdapter.ListType stickyViewStatus = (NewWifiListAdapter.ListType) stickyInfoView.getTag();
                if (stickyViewStatus == NewWifiListAdapter.ListType.HEADER) {
                    headerView = newWifiListAdapter.updateHeaderView(viewGroup, position);
                }

                View transInfoView = recyclerView.findChildViewUnder(headerView.getMeasuredWidth() / 2, headerView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
                    NewWifiListAdapter.ListType transViewStatus = (NewWifiListAdapter.ListType) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - headerView.getMeasuredHeight();
                    if (transViewStatus == NewWifiListAdapter.ListType.HEADER) {
                        if (transInfoView.getTop() > 0) {
                            headerView.setTranslationY(dealtY);
                        } else {
                            headerView.setTranslationY(0);
                        }
                    }
                }
            }
        });
    }
}
