package com.example.xoliuchat;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.xoliuchat.R.layout;
import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;

    public MsgAdapter(List<Msg> msgList) {
        this.mMsgList = msgList;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftTime;
        TextView rightTime;
        TextView leftMsg;
        TextView rightMsg;
        TextView leftName;

        ImageView leftImage;

        ImageView rightImage;
        public ViewHolder(@NonNull View v) {
            super(v);
            this.rightTime = v.findViewById(R.id.right_time);
            this.leftName = v.findViewById(R.id.left_username);
            this.leftLayout = v.findViewById(R.id.left_layout);
            this.rightLayout = v.findViewById(R.id.right_layout);
            this.leftTime = v.findViewById(R.id.left_time);
            this.leftMsg = v.findViewById(R.id.left_msg);
            this.rightMsg = v.findViewById(R.id.right_msg);
            this.leftImage = v.findViewById(R.id.left_image);
            this.rightImage = v.findViewById(R.id.right_image);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.msg_recycle, parent, false);
        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        String time = (new MainActivity()).getTime();
        Msg msg = (Msg)mMsgList.get(position);
        if (!msg.getUser_ip().equals(MainActivity.ip)) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftTime.setVisibility(View.VISIBLE);
            holder.rightTime.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getMsg_Context());
            holder.leftName.setText(msg.getUser_id());
            holder.leftTime.setText(msg.getTime());
            holder.leftImage.setVisibility(View.VISIBLE);
            holder.rightImage.setVisibility(View.GONE);
            int id = msg.getImageid();
            switch (id){
                case 1:
                    holder.leftImage.setImageResource(R.drawable.profile_1);
                    break;
                case 2:
                    holder.leftImage.setImageResource(R.drawable.profile_2);
                    break;
                case 3:
                    holder.leftImage.setImageResource(R.drawable.profile_3);
                    break;
                case 4:
                    holder.leftImage.setImageResource(R.drawable.profile_4);
                    break;
                case 5:
                    holder.leftImage.setImageResource(R.drawable.profile_5);
                    break;
                case 6:
                    holder.leftImage.setImageResource(R.drawable.profile_6);
                    break;
                case 7:
                    holder.leftImage.setImageResource(R.drawable.profile_7);
                    break;
                case 8:
                    holder.leftImage.setImageResource(R.drawable.profile_8);
                    break;
                case 9:
                    holder.leftImage.setImageResource(R.drawable.profile_9);
                    break;
                default:
                    break;
            }
        } else {
            holder.leftName.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.leftTime.setVisibility(View.GONE);
            holder.rightTime.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getMsg_Context());
            holder.rightTime.setText(msg.getTime());
            holder.leftImage.setVisibility(View.GONE);
            holder.rightImage.setVisibility(View.VISIBLE);
            int id = msg.getImageid();
            switch (id){
                case 1:
                    holder.rightImage.setImageResource(R.drawable.profile_1);
                    break;
                case 2:
                    holder.rightImage.setImageResource(R.drawable.profile_2);
                    break;
                case 3:
                    holder.rightImage.setImageResource(R.drawable.profile_3);
                    break;
                case 4:
                    holder.rightImage.setImageResource(R.drawable.profile_4);
                    break;
                case 5:
                    holder.rightImage.setImageResource(R.drawable.profile_5);
                    break;
                case 6:
                    holder.rightImage.setImageResource(R.drawable.profile_6);
                    break;
                case 7:
                    holder.rightImage.setImageResource(R.drawable.profile_7);
                    break;
                case 8:
                    holder.rightImage.setImageResource(R.drawable.profile_8);
                    break;
                case 9:
                    holder.rightImage.setImageResource(R.drawable.profile_9);
                    break;
                default:
                    break;
            }
        }

    }


    public int getItemCount() {
        return this.mMsgList.size();
    }

}