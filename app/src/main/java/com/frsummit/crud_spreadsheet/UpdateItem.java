package com.frsummit.crud_spreadsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class UpdateItem extends Activity {
    ProgressDialog loading;
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
//                loading = ProgressDialog.show(getApplicationContext(), "Loading", "please wait", false, true);
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw0lXmfX5kz3ZGM8P-G5hy4-sK-ewRbIHKUpaLj0IfWiROEhhtA/exec"
                        + "?action=updateItem"
                        + "&date=" + sheetDate
                        + "&id=" + sheetId
                        + "&itemId=" + id.getText()
                        + "&itemName=" + name.getText()
                        + "&brand=" + brand.getText()
                        + "&price=" + price.getText(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
//                                loading.dismiss();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("error : " + error);
                            }
                        }
                );

                int socketTimeOut = 50000;
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                stringRequest.setRetryPolicy(policy);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(stringRequest);
            }
        });
    }
}
