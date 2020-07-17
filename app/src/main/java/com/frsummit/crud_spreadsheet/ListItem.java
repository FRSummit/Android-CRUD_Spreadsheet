package com.frsummit.crud_spreadsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListItem extends Activity {
    ListView listView;
    ListAdapter adapter;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        listView = (ListView) findViewById(R.id.lv_items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListItem.this, "No Name ", Toast.LENGTH_SHORT).show();
            }
        });
        getItems();
    }

    private void getItems() {
        loading = ProgressDialog.show(this, "Loading", "please wait", false, true);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://script.google.com/macros/s/AKfycbw0lXmfX5kz3ZGM8P-G5hy4-sK-ewRbIHKUpaLj0IfWiROEhhtA/exec" + "?action=getItems",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseItems(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }


    private void parseItems(String jsonResposnce) {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("items");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
//                String itemId = jo.getString("itemId"); // To display item details
                String itemName = jo.getString("itemName");
                String brand = jo.getString("brand");
                String price = jo.getString("price");

                HashMap<String, String> item = new HashMap<>();
//                item.put("itemId", itemId); // To display item details
                item.put("itemName", itemName);
                item.put("brand", brand);
                item.put("price", price);

                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(this, list, R.layout.list_item_row, new String[]{"itemName", "brand", "price", "itemId"}, new int[]{R.id.tv_item_name, R.id.tv_brand, R.id.tv_price});
        listView.setAdapter(adapter);
        loading.dismiss();
    }

//    // To display item details
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, ItemDetails.class);
//        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
//        String itemId = map.get("itemId").toString();
//        String itemName = map.get("itemName").toString();
//        String brand = map.get("brand").toString();
//        String price = map.get("price").toString();
//
//
//        // String sno = map.get("sno").toString();
//
//        // Log.e("SNO test",sno);
//        intent.putExtra("itemId",itemId);
//        intent.putExtra("itemName",itemName);
//        intent.putExtra("brand",brand);
//        intent.putExtra("price",price);
//
//        startActivity(intent);
//    }
}
