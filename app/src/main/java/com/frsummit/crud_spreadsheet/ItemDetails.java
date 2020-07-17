package com.frsummit.crud_spreadsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetails extends Activity {

    TextView textViewitemName, textViewbrand, textViewprice,textViewId;
    Button buttonUpdateItem, buttonDeleteItem;
    String itemId, itemName, brand, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        itemName = intent.getStringExtra("itemName");
        brand = intent.getStringExtra("brand");
        price = intent.getStringExtra("price");

        textViewId = (TextView)findViewById(R.id.tv_id);
        textViewitemName = (TextView) findViewById(R.id.tv_item_name);
        textViewbrand = (TextView) findViewById(R.id.tv_brand);
        textViewprice = (TextView) findViewById(R.id.tv_price);

        textViewId.setText(itemId);
        textViewitemName.setText(itemName);
        textViewbrand.setText(brand);
        textViewprice.setText(price);
    }
}
