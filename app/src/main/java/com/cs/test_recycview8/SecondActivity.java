package com.cs.test_recycview8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRlvSecond;
    ArrayList<MultiItemEntity> mData = new ArrayList<>();
    private ExpandableItemAdapter adapter;
    private Map<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> isEnable = new HashMap<Integer, Boolean>();
    private Button mBtnSecond1;
    private List<ChargeListResult> chargeListResults=null;
    private int type;
    private List<ChargeListResult> sendListResults=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initData();
    }

    private void initData() {
        int lv0Count = 9;
        int lv1Count = 3;
        int personCount = 5;

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
        Random random = new Random();

        mData.clear();

        List<Level0Item> mLv0 = new ArrayList<>();
        List<Level1Item> mLv1 = new ArrayList<>();
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Level0Item lv0 = new Level0Item("我是一级机构", "");
            for (int j = 0; j < 1; j++) {
                Level1Item lv1 = new Level1Item("我是二级机构", "");
                for (int k = 0; k < 5; k++) {
                    Person person = new Person(nameList[k], random.nextInt(10));
                    lv1.addSubItem(person);

                    persons.add(person);

                }
                mLv1.add(lv1);
                lv0.addSubItem(lv1);

            }
            mLv0.add(lv0);
            mData.add(lv0);

        }
       /* Log.d("SecondActivity", "mLv0.size():" + mLv0.size());
        Log.d("SecondActivity", "mLv0.size():" + mLv1.size());
        Log.d("SecondActivity", "mLv0.size():" + persons.size());
*/

        isSelected.clear();
        int size = mLv0.size() + mLv1.size() + persons.size();
        for (int i = 0; i < size; i++) {
            isSelected.put(i, false);
            isEnable.put(i, true);
        }


        if (type==1){
            // TODO: 2017/11/10/010 这是1的数据
            if (chargeListResults!=null){
                for (int i = 0; i < chargeListResults.size(); i++) {
                    Log.d("NNN", "chargeListResults.get(i).getPos():" + chargeListResults.get(i).getName());
                    int pos = chargeListResults.get(i).getPos();
                    isSelected.put(pos,true);
                }

            }

            // TODO: 2017/11/10/010 这是2的数据
            if (sendListResults!=null){
                for (int i = 0; i < sendListResults.size(); i++) {
                    Log.d("WWW", "chargeListResults.get(i).getPos():" + sendListResults.get(i).getName());
                    int pos = sendListResults.get(i).getPos();
                    isEnable.put(pos,false);


                }

            }


        }



        if (type==2){

            // TODO: 2017/11/10/010 这是1的数据
            if (chargeListResults!=null){
                for (int i = 0; i < chargeListResults.size(); i++) {
                    Log.d("UUU", "chargeListResults.get(i).getPos():" + chargeListResults.get(i).getName());
                    int pos = chargeListResults.get(i).getPos();
                    isEnable.put(pos,false);
                }

            }

            // TODO: 2017/11/10/010 这是2的数据
            if (sendListResults!=null){
                for (int i = 0; i < sendListResults.size(); i++) {
                    Log.d("OOO", "chargeListResults.get(i).getPos():" + sendListResults.get(i).getName());
                    int pos = sendListResults.get(i).getPos();
                    isSelected.put(pos,true);

                }

            }





        }






        Log.d("BBB", "isSelected.size():" + isSelected.size());
        adapter.notifyDataSetChanged();


    }

    private void initView() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type",0);
        String chargename = intent.getStringExtra("chargename");
        String sendname = intent.getStringExtra("sendname");
        Gson gson = new Gson();
        Map<Integer,List<ChargeListResult>> mCharMap=gson.fromJson(chargename, new TypeToken<Map<Integer,List<ChargeListResult>>>() {
        }.getType());
        Map<Integer,List<ChargeListResult>> mSendMap=gson.fromJson(sendname, new TypeToken<Map<Integer,List<ChargeListResult>>>() {
        }.getType());

        if (mCharMap!=null){
           chargeListResults = mCharMap.get(1);

        }
        if (mSendMap!=null){
            sendListResults = mSendMap.get(2);
        }


        mRlvSecond = (RecyclerView) findViewById(R.id.rlv_second);
        mRlvSecond.setLayoutManager(new LinearLayoutManager(this));


        adapter = new ExpandableItemAdapter(mData, isSelected,isEnable);
        mRlvSecond.setAdapter(adapter);
        mBtnSecond1 = (Button) findViewById(R.id.btn_second1);
        mBtnSecond1.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_second1:

                Map<Integer,List<ChargeListResult >> map=new HashMap<>();
                List<ChargeListResult> mChargeName=new ArrayList<>();
                for (Map.Entry<Integer,Boolean> entry:isSelected.entrySet()) {

                    if (entry.getValue()) {
                       // Log.d("MMM", "entry.getKey():" + entry.getKey());
                        Person person = (Person) mData.get(entry.getKey());
                        Log.d("MMM", "person:" + person.getName());
                        ChargeListResult chargeListResult = new ChargeListResult();
                        chargeListResult.setName(person.getName());
                        chargeListResult.setPos(entry.getKey());
                        mChargeName.add(chargeListResult);

                    }
                }
                map.put(type, mChargeName);

                Gson gson = new Gson();
                String json = gson.toJson(map);
                Intent intent = new Intent();
                intent.putExtra("json",json);
                setResult(type,intent);
                finish();

                break;
        }
    }
}
