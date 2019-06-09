package com.kantaro.originalapp;

import io.realm.Realm;
import io.realm.RealmObject;

public class Memo extends RealmObject {

    public String content1;
    public String updateDate;
    public String content2;
    public Boolean judge;


    public Memo(){
    }

    public Memo(String content1,String content2,String updateDate,Boolean judge) {
        this.content1 = content1;
        this.content2 = content2;
        this.updateDate = updateDate;
        this.judge = judge;
    }
}