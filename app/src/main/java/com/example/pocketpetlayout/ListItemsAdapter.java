package com.example.pocketpetlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemsAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mBoardItmes;

    public ListItemsAdapter(Context mContext, ArrayList<String> mBoardItmes) {
        this.mContext = mContext;
        this.mBoardItmes = mBoardItmes;
    }

    @Override
    public int getCount() {
        return mBoardItmes.size();
    }

    @Override
    public Object getItem(int position) {
        return mBoardItmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //화면에 보여주는 역할을 함
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_list_item, parent, false);
        }else{

            TextView txt_Title = (TextView) convertView.findViewById(R.id.txt_title);
            TextView txt_Heart = (TextView) convertView.findViewById(R.id.txt_heart);
            TextView txt_Comm =  (TextView) convertView.findViewById(R.id.txt_comm);

            txt_Title.setText("" + position);
            txt_Heart.setText(mBoardItmes.get(position));
            txt_Comm.setText(mBoardItmes.get(position));
        }


        return convertView;
    }
}