package com.example.pocketpetlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<BoardItem> boardItems;

    public BoardAdapter(Context context, ArrayList<BoardItem> data){
        this.context = context;
        boardItems = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return boardItems.size();
    }

    @Override
    public BoardItem getItem(int position) {
        return boardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converview, ViewGroup viewGroup) {
        //BoardItem boardItem;
        View view = layoutInflater.inflate(R.layout.activity_list_item, null);

        TextView title = view.findViewById(R.id.txt_title);
        TextView writer = view.findViewById(R.id.txt_writer);
        TextView reg_date = view.findViewById(R.id.txt_regDate);
        TextView heart = view.findViewById(R.id.txt_heart);
        TextView comment = view.findViewById(R.id.txt_comm);

        title.setText(boardItems.get(position).getTitle());
        writer.setText(boardItems.get(position).getWriter());
        reg_date.setText(boardItems.get(position).getReg_date());
        heart.setText(String.valueOf(boardItems.get(position).getHeart()));
        comment.setText(String.valueOf(boardItems.get(position).getComment()));

        return view;
    }
}
