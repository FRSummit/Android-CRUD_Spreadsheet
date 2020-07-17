package com.frsummit.crud_spreadsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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
    Button deleteBtn;
    ArrayList<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        listView = (ListView) findViewById(R.id.lv_items);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListItem.this, itemList.get(position).getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ItemDetails.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
                String date = map.get("date").toString();
                String sheetId = map.get("sheetId").toString();
                String itemId = map.get("itemId").toString();
                String itemName = map.get("itemName").toString();
                String brand = map.get("brand").toString();
                String price = map.get("price").toString();


                // String sno = map.get("sno").toString();

                // Log.e("SNO test",sno);
                intent.putExtra("date", date);
                intent.putExtra("sheetId", sheetId);
                intent.putExtra("itemId", itemId);
                intent.putExtra("itemName", itemName);
                intent.putExtra("brand", brand);
                intent.putExtra("price", price);

                startActivity(intent);
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
                String date = jo.getString("date");
                String sheetId = jo.getString("id");
                String itemId = jo.getString("itemId");
                String itemName = jo.getString("itemName");
                String brand = jo.getString("brand");
                String price = jo.getString("price");

                HashMap<String, String> item = new HashMap<>();
                item.put("date", date);
                item.put("sheetId", sheetId);
                item.put("itemId", itemId);
                item.put("itemName", itemName);
                item.put("brand", brand);
                item.put("price", price);

                list.add(item);

                /*******************************************/
                Item my_item = new Item(itemId, itemName, brand, price);
                itemList.add(my_item);
                /*******************************************/
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new SimpleAdapter(this, list, R.layout.list_item_row, new String[]{"itemName", "brand", "price", "itemId"}, new int[]{R.id.tv_item_name, R.id.tv_brand, R.id.tv_price});
        listView.setAdapter(adapter);
        loading.dismiss();
    }
}
