package com.example.pocketpetlayout;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FeedAdapter3 extends BaseAdapter {
    private static final String TAG = "FeedAdapter3";

    Context context;
    LayoutInflater inflater;
    ArrayList<NewFeedItem> newFeedItems;


    public FeedAdapter3(Context context, ArrayList<NewFeedItem> data) {
        this.context = context;
        newFeedItems = data;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return (null != newFeedItems) ? newFeedItems.size() : 0;
    }

    @Override
    public NewFeedItem getItem(int position) {
        return newFeedItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertview, ViewGroup viewGroup){
        //NewFeedItem newFeedItems;
        ImageView imageView = null;
        if (null != convertview)
            imageView = (ImageView)convertview;
        else {
            View view = inflater.inflate(R.layout.activity_feed_item, null);

            imageView = view.findViewById(R.id.Feed_image);
            imageView.setImageBitmap(newFeedItems.get(position).getImgName());

        }return imageView;
    }
}
