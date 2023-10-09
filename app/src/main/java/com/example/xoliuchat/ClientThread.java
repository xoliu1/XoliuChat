package com.example.xoliuchat;


import android.os.Handler;
import android.os.Message;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable {
    private static final String TAG = "tagOfXoliu1";
    private Handler handler;
    private BufferedReader br = null;

    public ClientThread(Socket socket, Handler handler) throws IOException {
        Log.d("tagOfXoliu1", "ClientThread: 构造Client线程中......");
        this.handler = handler;
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Log.d("tagOfXoliu1", "ClientThread: 构造Client线程完成");
    }

    public void run() {
        Log.d("tagOfXoliu1", "run: Client类run方法执行开始");

        try {
            String content = null;
            Log.d("tagOfXoliu1", "run: 开始读取Socket输入流的内容");

            while((content = br.readLine()) != null) {
                Log.d("tagOfXoliu1", "run: 读取服务器数据循环中......");
                Message msg = new Message();
                msg.what = 0x234;
                msg.obj = content;
                Log.d("tagOfXoliu1", "run: msg待发送" + msg.obj.toString());
                handler.sendMessage(msg);
                Log.d("tagOfXoliu1", "run: msg发送成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("tagOfXoliu1", "run: 客户端run方法出现异常");
        }

    }
}