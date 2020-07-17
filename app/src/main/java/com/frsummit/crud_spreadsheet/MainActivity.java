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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        startActivity(new Intent(this, AddItem.class));
    }
}

/**
 *
 * TUTORIAL
 * https://www.crazycodersclub.com/android/how-to-use-google-sheet-as-database-for-android-app-1-insert-operation/
 * https://www.youtube.com/watch?v=v2Az8yIU1lE
 *
 */
