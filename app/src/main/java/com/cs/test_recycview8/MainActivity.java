package com.cs.test_recycview8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvMain1;
    private Button mBtnMain1;

    private Button mBtnMain2;

    private Button mBtnMain3;

    private  Map<Integer,List<ChargeListResult >> mChargeList;
    private  Map<Integer,List<ChargeListResult >> mSendList;
    private TextView mTvMain2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTvMain1 = (TextView) findViewById(R.id.tv_main1);
        mBtnMain1 = (Button) findViewById(R.id.btn_main1);
        mTvMain2 = (TextView) findViewById(R.id.tv_main2);
        mBtnMain2 = (Button) findViewById(R.id.btn_main2);

        mBtnMain1.setOnClickListener(this);
        mBtnMain2.setOnClickListener(this);
        mBtnMain3 = (Button) findViewById(R.id.btn_main3);
        mBtnMain3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        switch (v.getId()) {
            case R.id.btn_main1:
                if (mChargeList!=null) {
                    Log.d("XXX", "mNames.size():" + mChargeList.size());
                    Gson gson = new Gson();
                    String json = gson.toJson(mChargeList);
                    intent.putExtra("chargename",json);
                }

               /* if (mSendList!=null) {
                    Log.d("XXX", "mNames.size():" + mSendList.size());
                    Gson gson = new Gson();
                    String json = gson.toJson(mSendList);
                    intent.putExtra("sendname",json);
                }
*/

                intent.putExtra("type",1);

                startActivityForResult(intent, 1);

                break;
            case R.id.btn_main2:
                intent.putExtra("type",2);

                if (mChargeList!=null) {
                    Log.d("XXX", "mNames.size():" + mChargeList.size());
                    Gson gson = new Gson();
                    String json = gson.toJson(mChargeList);
                    intent.putExtra("chargename",json);
                }

                /*if (mSendList!=null) {
                    Log.d("XXX", "mNames.size():" + mSendList.size());
                    Gson gson = new Gson();
                    String json = gson.toJson(mSendList);
                    intent.putExtra("sendname",json);
                }*/

                startActivityForResult(intent, 2);
                break;
            case R.id.btn_main3:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == 1) {
                String json = data.getStringExtra("json");
                Gson gson = new Gson();
                mChargeList = gson.fromJson(json, new TypeToken<Map<Integer,List<ChargeListResult >>>() {
                }.getType());
                String name = "";
                StringBuffer append = null;
                List<ChargeListResult> mChargeName = mChargeList.get(1);
                for (int i = 0; i < mChargeName.size(); i++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    append = stringBuffer.append(mChargeName.get(i).getName() + ",");
                    Log.d("MainActivity", mChargeName.get(i).getName());
                    name += mChargeName.get(i).getName();

                }

                mTvMain1.setText(name);
            }
        } else if (requestCode == 2) {
            if (resultCode == 2) {
                /*String json = data.getStringExtra("json");
                Gson gson = new Gson();
                mSendList = gson.fromJson(json, new TypeToken<Map<Integer,List<ChargeListResult >>>() {
                }.getType());
                String name = "";
                StringBuffer append = null;
                List<ChargeListResult> mSendName = mSendList.get(2);
                for (int i = 0; i < mSendName.size(); i++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    append = stringBuffer.append(mSendName.get(i).getName() + ",");
                    Log.d("MainActivity", mSendName.get(i).getName());
                    name += mSendName.get(i).getName();

                }
*/

                String json = data.getStringExtra("json");
                Gson gson = new Gson();
                mChargeList = gson.fromJson(json, new TypeToken<Map<Integer,List<ChargeListResult >>>() {
                }.getType());
                String name = "";
                StringBuffer append = null;
                List<ChargeListResult> mChargeName = mChargeList.get(2);
                for (int i = 0; i < mChargeName.size(); i++) {
                    StringBuffer stringBuffer = new StringBuffer();
                    append = stringBuffer.append(mChargeName.get(i).getName() + ",");
                    Log.d("MainActivity", mChargeName.get(i).getName());
                    name += mChargeName.get(i).getName();

                }
                mTvMain2.setText(name);
            }

        }
    }
}
