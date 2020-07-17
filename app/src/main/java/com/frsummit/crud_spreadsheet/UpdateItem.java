package com.frsummit.crud_spreadsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateItem extends Activity {
    EditText id, name, brand, price;
    Button updateItem;
    String sheetDate, sheetId, itemId, itemName, itemBrand, itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item_);

        Intent intent = getIntent();
        sheetDate = intent.getStringExtra("sheetDate");
        sheetId = intent.getStringExtra("sheetId");
        itemId = intent.getStringExtra("itemId");
        itemName = intent.getStringExtra("itemName");
        itemBrand = intent.getStringExtra("brand");
        itemPrice = intent.getStringExtra("price");

        id = findViewById(R.id.up_id);
        name = findViewById(R.id.up_name);
        brand = findViewById(R.id.up_brand);
        price = findViewById(R.id.up_price);

        id.setText(itemId);
        name.setText(itemName);
        brand.setText(itemBrand);
        price.setText(itemPrice);

        updateItem = findViewById(R.id.updateItem);

        updateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(id.getText());
            }
        });
    }
}
