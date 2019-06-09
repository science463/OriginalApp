package com.kantaro.originalapp;

import android.app.LauncherActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Comparator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public Realm realm;

    public ListView listView;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();

        listView = (ListView) findViewById(R.id.listView);

        boolean change;

        change = true;




    }



    public void setMemoList(){

        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> items = realm.copyFromRealm(results);

        MemoAdapter adapter = new MemoAdapter(this,R.layout.layout_item_memo,items);

        listView.setAdapter(adapter);
    }


    @Override
    protected void onResume(){
        super.onResume();

        setMemoList();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }


    public void create (View view){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }


}
