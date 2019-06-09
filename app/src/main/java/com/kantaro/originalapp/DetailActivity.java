package com.kantaro.originalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import io.realm.RealmResults;

public class DetailActivity extends AppCompatActivity {

    public Realm realm;

    public EditText content1Text;
    public EditText content2Text;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        realm = Realm.getDefaultInstance();

        content1Text = (EditText) findViewById(R.id.content1EditText);
        content2Text = (EditText) findViewById(R.id.content2EditText);

        showData();
    }



    public void showData(){

        final Memo memo = realm.where(Memo.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();

        content1Text.setText(memo.content1);
        content2Text.setText(memo.content2);
    }

    public void update(View view){

        final Memo memo = realm.where(Memo.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                memo.content1 = content1Text.getText().toString();
                memo.content2 = content2Text.getText().toString();
            }
        });

        finish();
    }

    public void delete(View view){

        final Memo memo = realm.where(Memo.class).equalTo("updateDate",getIntent().getStringExtra("updateDate")).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                memo.deleteFromRealm();
            }
        });

        finish();
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();

        realm.close();
    }


}
