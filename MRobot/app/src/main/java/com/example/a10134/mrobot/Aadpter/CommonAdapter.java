package com.example.a10134.mrobot.Aadpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> datas;
    int layoutId;

    public CommonAdapter(Context context, List<T> datas,int layoutId) {
        this.context = context;
        this.datas = datas;
        this.layoutId = layoutId;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder = ViewHolder.get(context,convertView,parent, layoutId,position);

        convert(holder,getItem(position));

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T t);
}
