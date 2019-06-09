package com.kantaro.originalapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.Sort;

public class MemoAdapter extends ArrayAdapter<Memo> {

    public LayoutInflater layoutInflater;

        MemoAdapter(Context context, int textViewResourceId, List<Memo> objects){
            super(context,textViewResourceId,objects);
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            final Memo memo = getItem(position);

            if (convertView == null){
                convertView = layoutInflater.inflate(R.layout.layout_item_memo,null);
            }

            final TextView content1Text = (TextView) convertView.findViewById(R.id.content1_text);

            if (memo != null){
                content1Text.setText(memo.content1);

                content1Text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Realm realm = Realm.getDefaultInstance();

                        final Memo realmMemo = realm.where(Memo.class).equalTo("updateDate",memo.updateDate).findFirst();

                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                if (realmMemo.judge){
                                    content1Text.setText(memo.content1);
                                    realmMemo.judge = false;
                                }else {
                                    content1Text.setText(memo.content2);
                                    realmMemo.judge = true;
                                }
                            }
                        });
                    }
                });

                content1Text.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        final Context context = getContext();
                        Intent intent = new Intent(context,DetailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("updateDate",memo.updateDate);
                        context.startActivity(intent);

                        return false;
                    }
                });


                


            }

            //content1Text.setText(memo.content1);
            //content2Text.setText(memo.content2);

            return  convertView;
        }
}
