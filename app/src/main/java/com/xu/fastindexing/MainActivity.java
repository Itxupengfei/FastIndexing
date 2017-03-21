package com.xu.fastindexing;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.xu.fastindexing.Constans.NameConstans;
import com.xu.fastindexing.adapter.ListViewAdapter;
import com.xu.fastindexing.bean.Person;
import com.xu.fastindexing.view.FastIndexingView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity implements FastIndexingView.OnUpdateListener {

    private FastIndexingView mFastindext;
    private ListView mLv;
    private List<Person> mPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.lv);
        mPersons = new ArrayList<>();

        addData(mPersons);

        ListViewAdapter listViewAdapter = new ListViewAdapter(mPersons);
        mLv.setAdapter(listViewAdapter);
        //mLv.setSelection();
        mFastindext = (FastIndexingView) findViewById(R.id.fastindext);
        mFastindext.setOnUpdateListener(this);
    }

    private void addData(List<Person> persons) {
        for (int i = 0; i < NameConstans.NAME.length; i++) {
            persons.add(new Person(NameConstans.NAME[i]));
        }
        Collections.sort(persons);
    }


    @Override
    public void OnUpdate(String str) {
        for (int i = 0; i < mPersons.size(); i++) {
            Person person = mPersons.get(i);
            if(person.getLetter().equals(str)){
                //跳转到指定位置
                mLv.setSelection(i);
                break;
            }
        }
    }
}
