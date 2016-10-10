package com.example.a10134.mrobot.Aadpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a10134.mrobot.Aadpter.CommonAdapter;
import com.example.a10134.mrobot.Aadpter.ViewHolder;
import com.example.a10134.mrobot.Bean.ChatMessage;
import com.example.a10134.mrobot.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 10134 on 2016/8/7.
 */
public class Myadpter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ChatMessage> mDatas;
    public Myadpter(Context context, List<ChatMessage> mDatas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = mDatas.get(position);
        ViewHolder viewHolder = null;
        if(convertView == null){
            //通过ItemType设置不同布局
            if(getItemViewType(position) == 0) {
                convertView = mInflater.inflate(R.layout.item_from_msg,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_from_msg_date);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_from_msg_content);
                convertView.setTag(viewHolder);
            }else if(getItemViewType(position) == 1){
                convertView = mInflater.inflate(R.layout.item_to_msg,parent,false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_to_msg_date);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_to_msg_content);
                convertView.setTag(viewHolder);
                System.out.println("dsfwefdwe");
            }
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //设置数据
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        viewHolder.mMsg.setText(chatMessage.getMsg());
        viewHolder.mDate.setText(sf.format(chatMessage.getDate()));
        viewHolder.mMsg.setText(chatMessage.getMsg());

        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = mDatas.get(position);
        if(chatMessage.getType() == ChatMessage.Type.INCOMING){
            return 0;
        }
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    private final class ViewHolder{
        TextView mDate;
        TextView mMsg;
    }


}
