package com.frsummit.crud_spreadsheet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by F R Summit on 16th July,2020
 * Simplexhub Limited
 * frsummit@simplexhub.com
 */
public class MainActivity extends Activity {
    Button buttonAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        buttonAddItem = (Button)findViewById(R.id.btn_add_item);
//        buttonAddItem.setOnClickListener(this);
    }

    public void btnClick(View view) {
        startActivity(new Intent(this, AddItem2.class));
    }

//    @Override
//    public void onClick(View v) {
//
//        if(v==buttonAddItem){
//
//            Intent intent = new Intent(getApplicationContext(),AddItem.class);
//            startActivity(intent);
//        }
//
//    }
}
