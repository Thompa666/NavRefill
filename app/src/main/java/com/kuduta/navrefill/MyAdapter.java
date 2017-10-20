package com.kuduta.navrefill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by osx on 10/19/2017 AD.
 */

public class MyAdapter extends BaseAdapter {

    private  List<Data> mDatas;
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, List<Data> mDatas) {
        mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    static class ViewHolder{

        TextView tvNumphone;
        TextView tvMoney;
        TextView tvExpire;

    }//

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){

            convertView = mLayoutInflater.inflate(R.layout.row_layout,parent,false);
            holder = new ViewHolder();

            holder.tvNumphone = (TextView)convertView.findViewById(R.id.numphone);
            holder.tvExpire = (TextView)convertView.findViewById(R.id.expire);
            holder.tvMoney = (TextView)convertView.findViewById(R.id.money);
            convertView.setTag(holder);
        }else {

            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvNumphone.setText(mDatas.get(position).getmNumphon());
        holder.tvMoney.setText(mDatas.get(position).getmMoney());
        holder.tvExpire.setText(mDatas.get(position).getmExpire());

        return convertView;
    }
}
