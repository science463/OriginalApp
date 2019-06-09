package com.kantaro.originalapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.realm.Realm;

public class SettingsActivity extends AppCompatActivity {

    public Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        realm = Realm.getDefaultInstance();

    }

    //public void sort_abc (View view){RealmResults<User> result = realm.where(User.class).findAll();result = result.sort("age");}
}
