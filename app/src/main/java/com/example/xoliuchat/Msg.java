package com.example.xoliuchat;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Msg {
    private String user_ip;
    private String user_id;
    private String msg_Context;
    private String time;

    private int Imageid;

    public int getImageid() {
        return Imageid;
    }

    public void setImageid(int imageid) {
        Imageid = imageid;
    }

    public Msg(String Information, String time, int ImageID) {
        String[] informations = Information.split("#xoliu#");
        this.user_ip = informations[0];
        this.user_id = informations[1];
        this.msg_Context = informations[2];
        this.time = time;
        this.Imageid = ImageID;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMsg_Context() {
        return this.msg_Context;
    }

    public void setMsg_Context(String msg_Context) {
        this.msg_Context = msg_Context;
    }

    public String getUser_ip() {
        return this.user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Msg msg = (Msg) o;

        if (Imageid != msg.Imageid) return false;
        if (!Objects.equals(user_ip, msg.user_ip)) return false;
        if (!Objects.equals(user_id, msg.user_id)) return false;
        if (!Objects.equals(msg_Context, msg.msg_Context))
            return false;
        return Objects.equals(time, msg.time);
    }

    @Override
    public int hashCode() {
        int result = user_ip != null ? user_ip.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (msg_Context != null ? msg_Context.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + Imageid;
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return this.user_ip + "#xoliu#" + this.user_id + "#xoliu#" + this.time + "#xoliu#" + this.Imageid + "#xoliu#" + this.msg_Context;
    }
}
