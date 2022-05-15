package com.example.pocketpetlayout;

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

import java.util.ArrayList;

public class FeedAdapter2 extends BaseAdapter {
    private static final String TAG = "FeedAdapter2";
    private Context context;
    private int[] imageIDs;
    LayoutInflater inflater;

    public FeedAdapter2(Context c, int[] imageIDs) {
        this.context = c;
        this.imageIDs = imageIDs;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /*public void FeedAdapter2(Context c, ArrayList<Bitmap>){
        this.context = c;
        this.picArr = picArr;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }*/

    @Override
    public int getCount() {
        return (null != imageIDs) ? imageIDs.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return (null != imageIDs) ? imageIDs[position] : 0;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = null;

        if (null != convertView)
            imageView = (ImageView)convertView;
        else {
            //---------------------------------------------------------------
            // GridView 뷰를 구성할 ImageView 뷰의 비트맵을 정의합니다.
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageIDs[position]);
            bmp = Bitmap.createScaledBitmap(bmp, 500, 500, false);

            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);

            imageView.setImageBitmap(bmp);
        }
    return imageView;
    }


}
