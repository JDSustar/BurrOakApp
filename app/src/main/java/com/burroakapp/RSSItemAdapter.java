package com.burroakapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Jim on 4/14/2015.
 */
public class RSSItemAdapter extends ArrayAdapter<RSSItem> {

    public RSSItemAdapter(Context c, List<RSSItem> rssItems){
        super(c, 0, rssItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RSSItemView rssItemView = (RSSItemView)convertView;
        if (null == rssItemView)
            rssItemView = RSSItemView.inflate(parent);
        rssItemView.setItem(getItem(position));
        return rssItemView;
    }

}
