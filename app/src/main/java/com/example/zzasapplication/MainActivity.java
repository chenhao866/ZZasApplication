package com.example.zzasapplication;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity implements OnClickListener {
    private Button mBT_creator;
    private Button mBT_insert;
    private Button mBT_update;
    private Button mBT_delete;
    private Button mBT_QueryID;
    private Button mBT_QueryAll;
    private Button mBT_QueryCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }


    protected void initButton() {
        mBT_creator = (Button) findViewById(R.id.BT_creator);
        mBT_insert = (Button) findViewById(R.id.BT_insert);
        mBT_update = (Button) findViewById(R.id.BT_update);
        mBT_delete = (Button) findViewById(R.id.BT_delete);
        mBT_QueryID = (Button) findViewById(R.id.BT_QueryID);
        mBT_QueryAll = (Button) findViewById(R.id.BT_QueryAll);
        mBT_QueryCount = (Button) findViewById(R.id.BT_QueryCount);
        mBT_creator.setOnClickListener(this);
        mBT_insert.setOnClickListener(this);
        mBT_update.setOnClickListener(this);
        mBT_delete.setOnClickListener(this);
        mBT_QueryID.setOnClickListener(this);
        mBT_QueryAll.setOnClickListener(this);
        mBT_QueryCount.setOnClickListener(this);
    }

    protected String GetIMEI() {
        try {
            String imsi = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            Log.i(TAG, "onCreate:****** " +imsi);
            if(null==imsi){
                imsi="";
            }
            return imsi;
        } catch (SecurityException e) {
            e.printStackTrace();
            return "";
        }
    }


    @Override
    public void onClick(View arg0) {
        SQLiteDAOImpl pd_insert = new SQLiteDAOImpl(this.getBaseContext());;
        switch (arg0.getId()) {
            case R.id.BT_creator:
                String imei =  GetIMEI();
                Toast.makeText(getApplicationContext(), imei, Toast.LENGTH_SHORT).show();
                break;
            case R.id.BT_insert:
                TUsers tuser = new TUsers();
                tuser.setUsername("用户");
                tuser.setPass("密码");
                pd_insert.save(tuser);
                Toast.makeText(getApplicationContext(), "写入成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BT_update:
                TUsers tuser01 = pd_insert.find(1);
                tuser01.setUsername("张三");
                pd_insert.update(tuser01);
                Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BT_delete:
                pd_insert.delete(2);
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.BT_QueryID:
                TUsers tUsers = pd_insert.find(1);
                Toast.makeText(getApplicationContext(), tUsers.getUsername(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.BT_QueryAll:
                List<TUsers> tusers = pd_insert.findAll();
                for (TUsers tuseritem : tusers) {
                    Toast.makeText(getApplicationContext(), tuseritem.getId()+"id数据", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.BT_QueryCount:
                Toast.makeText(getApplicationContext(), pd_insert.getCount()+"条数据", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }














}
