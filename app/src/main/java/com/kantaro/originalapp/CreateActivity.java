package com.kantaro.originalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

class CreateActivity extends AppCompatActivity {

    public Realm realm;

    public EditText content1EditText;
    public EditText content2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        realm = Realm.getDefaultInstance();

        content1EditText = (EditText) findViewById(R.id.content1EditText);
        content2EditText = (EditText) findViewById(R.id.content2EditText);
    }

    public void save(final String content1,final  String updateDate,final String content2){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.content1 = content1;
                memo.updateDate = updateDate;
                memo.content2 = content2;
                memo.judge = false;
            }
        });
    }

    public void create(View view) {
        String content1 = content1EditText.getText().toString();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.JAPANESE);
        String updateDate = sdf.format(date);

        String content2 = content2EditText.getText().toString();

        //check(content1,updateDate,content2);

        save(content1,updateDate,content2);

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    private void check(String content1,String updateDate,String content2){

        Memo memo = new Memo();

        memo.content1 = content1;
        memo.updateDate = updateDate;
        memo.content2 = content2;

        Log.d("Memo",memo.content1);
        Log.d("Memo",memo.updateDate);
        Log.d("Memo",memo.content2);
    }

}
