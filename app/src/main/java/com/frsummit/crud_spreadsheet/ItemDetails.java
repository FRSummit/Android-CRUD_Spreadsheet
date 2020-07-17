package com.frsummit.crud_spreadsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ItemDetails extends Activity {
    ProgressDialog loading;

    TextView textViewitemName, textViewbrand, textViewprice, textViewId, sheetDetails;
    String sheetDate, sheetId, itemId, itemName, brand, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Intent intent = getIntent();
        sheetDate = intent.getStringExtra("date");
        sheetId = intent.getStringExtra("sheetId");
        itemId = intent.getStringExtra("itemId");
        itemName = intent.getStringExtra("itemName");
        brand = intent.getStringExtra("brand");
        price = intent.getStringExtra("price");

        textViewId = (TextView) findViewById(R.id.itemId);
        textViewitemName = (TextView) findViewById(R.id.itemName);
        textViewbrand = (TextView) findViewById(R.id.itemBrand);
        textViewprice = (TextView) findViewById(R.id.itemPrice);
        sheetDetails = (TextView) findViewById(R.id.sheetDetails);

        textViewId.setText(itemId);
        textViewitemName.setText(itemName);
        textViewbrand.setText(brand);
        textViewprice.setText(price);
        sheetDetails.setText(sheetDate + "    " + sheetId);
    }

    public void UpdateBtnClick(View view) {
        Toast.makeText(this, itemId, Toast.LENGTH_LONG).show();
        System.out.println(itemId);
    }

    public void deleteBtnClick(View view) {
        Toast.makeText(this, itemId, Toast.LENGTH_LONG).show();
        System.out.println(itemId);

        loading = ProgressDialog.show(this, "Loading", "please wait", false, true);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw0lXmfX5kz3ZGM8P-G5hy4-sK-ewRbIHKUpaLj0IfWiROEhhtA/exec" + "?action=deleteItem",
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw0lXmfX5kz3ZGM8P-G5hy4-sK-ewRbIHKUpaLj0IfWiROEhhtA/exec"
                + "?action=deleteItem"
                + "&date=" + sheetDate
                + "&id=" + sheetId
                + "&itemId=" + itemId
                + "&itemName=" + itemName
                + "&brand=" + brand
                + "&price=" + price,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        System.out.println("response : " + response);
                        loading.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("error : " + error);
                    }
                }
        );
//        {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> parmas = new HashMap<>();
//                //here we pass params
////                parmas.put("action", "deleteItem");
//                parmas.put("date", sheetDate);
//                parmas.put("sheetId", sheetId);
//                parmas.put("itemId", itemId);
//                parmas.put("itemName", itemName);
//                parmas.put("brand", brand);
//                parmas.put("price", price);
//
//                return parmas;
//            }
//        };

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
