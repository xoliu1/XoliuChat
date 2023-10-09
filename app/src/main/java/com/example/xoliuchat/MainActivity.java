package com.example.xoliuchat;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xoliuchat.R.layout;

import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tagOfXoliu1";
    private EditText input;
    private EditText input_id;
    private EditText input_ip;
    private Button sendBtn;
    private OutputStream os;
    private Handler handler;
    String id = "";
    private List<Msg> mMsgList;
    LinearLayout Layout_recyclerView;
    RecyclerView recyclerView;
    MsgAdapter msgAdapter;
    static String ip = "";

    public static String serverIP = "192.168.1.44";

    public static int IMAGE_ID = 1;

    DrawerLayout mdrawerlayout;

    ImageView touxiang;

    Toolbar toolbar;

    SharedPreferences preferences;



    SharedPreferences.Editor editor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        if (VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = (new StrictMode.ThreadPolicy.Builder()).permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        preferences = getSharedPreferences("tmpdata", MODE_PRIVATE);
        touxiang = (ImageView) findViewById(R.id.menu_touxiang);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //Thread.sleep(3000);
//                inittouxiang();
//            }
//        }).start();
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, photo_profile.class);
                startActivity(intent);
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdrawerlayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        editor = preferences.edit();
        input = (EditText) findViewById(R.id.main_et_input);
        sendBtn = (Button) findViewById(R.id.main_btn_send);
        input_id = (EditText) findViewById(R.id.main_et_id);
        input_ip = (EditText) findViewById(R.id.main_et_ip);
        recyclerView = (RecyclerView) findViewById(R.id.Main_recyclerView);
        mMsgList = new ArrayList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        msgAdapter = new MsgAdapter(mMsgList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(msgAdapter);
        Log.d("tagOfXoliu1", "onCreate: 按键初始化成功");
        IMAGE_ID = preferences.getInt("touxiangID", 1);
        input_id.setText(preferences.getString("userID", ""));
        input_ip.setText(preferences.getString("userIP",""));
        Log.d(TAG, IMAGE_ID + "" + input_id.getText().toString());
        Set<String> msgSET = (Set<String>) preferences.getStringSet("msgSET", new HashSet<>());

        for (String s : msgSET) {
            Log.d(TAG, s);
            String[] strings = s.split("#xoliu#");
            mMsgList.add(new Msg(strings[0] + "#xoliu#" + strings[1] + "#xoliu#" + strings[4], strings[2], Integer.parseInt(strings[3])));
        }
        Collections.sort(mMsgList, new Comparator<Msg>() {
            @Override
            public int compare(Msg m1, Msg m2) {
                return m1.getTime().compareTo(m2.getTime());
            }
        });
        msgAdapter = new MsgAdapter(mMsgList);
        recyclerView.setAdapter(msgAdapter);
        msgAdapter.notifyItemInserted(mMsgList.size() - 1);
        recyclerView.scrollToPosition(mMsgList.size() - 1);

        try {
            ip = getIP();
            Log.d("tagOfXoliu1", ip);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "获取ip地址失败！", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 如果消息来自子线程
                if (msg.what == 0x234) {
                    Log.d("tagOfXoliu1", "onCreate: Handler成功创建");
                    flushMsgs(msg.obj.toString());
                }
            }
        };

        new Thread() {
            public void run() {
                Socket socket;
                if (!input_ip.getText().toString().equals("")){
                    serverIP = input_ip.getText().toString();
                }
                try {
                    socket = new Socket(serverIP, 11233);
                    // 自定义修改
                    new Thread(new ClientThread(socket, handler)).start();
                    os = socket.getOutputStream();
                    Log.d(TAG, "run: 得到os输入流");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.getText().toString().equals("")) {
                    try {
                        // 发送
                        id = input_id.getText().toString();
                        if (id.equals("")) {
                            id = ip;
                        }
                        String num = String.valueOf(IMAGE_ID);
                        os.write((ip + "#xoliu#" + id + "#xoliu#" + input.getText().toString() + "#xoliu#" + num + "\r\n").getBytes());
                        // 清空input文本框数据
                        input.setText("");
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "连接服务器错误！", Toast.LENGTH_LONG);
                        e.printStackTrace();
                    }
                }
            }
        });
        //开始加载初始数据

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            mdrawerlayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    public void flushMsgs(String information) {
        Msg msg = new Msg(information, getTime(),IMAGE_ID);
        mMsgList.add(msg);
        msgAdapter = new MsgAdapter(mMsgList);
        recyclerView.setAdapter(msgAdapter);
        msgAdapter.notifyItemInserted(mMsgList.size() - 1);
        recyclerView.scrollToPosition(mMsgList.size() - 1);
    }

    public String getIP() {
        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        int ipAddressInt = wm.getConnectionInfo().getIpAddress();
        String ipAddress = String.format(Locale.getDefault(), "%d.%d.%d.%d", ipAddressInt & 255, ipAddressInt >> 8 & 255, ipAddressInt >> 16 & 255, ipAddressInt >> 24 & 255);
        return ipAddress;
    }

    public String getTime() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(currentDate);
        return currentTime;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                inittouxiang();
            }
        });
    }

    public void inittouxiang(){
        switch (IMAGE_ID){
            case 1:
                touxiang.setImageResource(R.drawable.profile_1);
                break;
            case 2:
                touxiang.setImageResource(R.drawable.profile_2);
                break;
            case 3:
                touxiang.setImageResource(R.drawable.profile_3);
                break;
            case 4:
                touxiang.setImageResource(R.drawable.profile_4);
                break;
            case 5:
                touxiang.setImageResource(R.drawable.profile_5);
                break;
            case 6:
                touxiang.setImageResource(R.drawable.profile_6);
                break;
            case 7:
                touxiang.setImageResource(R.drawable.profile_7);
                break;
            case 8:
                touxiang.setImageResource(R.drawable.profile_8);
                break;
            case 9:
                touxiang.setImageResource(R.drawable.profile_9);
                break;
            default:
                break;
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        Set<String> set = new LinkedHashSet<>();
        for (Msg msg : mMsgList) {
            set.add(msg.toString());
            Log.d(TAG, msg.toString());
        }
        //存储数据
        editor.putStringSet("msgSET", set).putInt("touxiangID", IMAGE_ID).putString("userID", input_id.getText().toString()).putString("userIP", input_ip.getText().toString()).commit();
        Log.d(TAG, Integer.toString(IMAGE_ID) + input_id.getText().toString());

    }
}

