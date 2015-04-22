package com.burroakapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jim on 4/14/2015.
 */
public class RSSItemView extends RelativeLayout {
    private TextView titleTextView;
    private TextView dateTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;

    public static RSSItemView inflate(ViewGroup parent) {
        RSSItemView rssItemView = (RSSItemView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rssitem_view, parent, false);
        return rssItemView;
    }

    public RSSItemView(Context c) {
        this(c, null);
    }

    public RSSItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RSSItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.rssitem_view_children, this, true);
        setupChildren();
    }

    private void setupChildren() {
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        startTimeTextView = (TextView) findViewById(R.id.startTimeTextView);
        endTimeTextView = (TextView) findViewById(R.id.endTimeTextView);

    }

    public void setItem(RSSItem rssItem) {
        titleTextView.setText(rssItem.getTitle());
        dateTextView.setText(RSSItem.DATE_FORMAT.format(rssItem.getDate()));
        startTimeTextView.setText(rssItem.getStartTime());
        endTimeTextView.setText(rssItem.getEndTime());
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public TextView getStartTimeTextView() {
        return startTimeTextView;
    }

    public TextView getEndTimeTextView() {
        return endTimeTextView;
    }

}
