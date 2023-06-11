package com.example.test.myrequest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.test.Adapter;
import com.example.test.OtherActivity;
import com.example.test.R;
import com.example.test.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRequest {
    private Context context;
    RecyclerView recyclerView;
    List<Users> users;
    Adapter adapter;
    private RequestQueue queue;

    public MyRequest(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(String pseudo, int value) {
        String url = "http://192.168.1.79/java/php/index.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("APP", response);
                Toast.makeText(context.getApplicationContext(), response, Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("APP", "Error" + error);
                Toast.makeText(context.getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> map = new HashMap<>();
                map.put("name", pseudo);
                map.put("score", String.valueOf(value));

                //Log.i("Values", String.valueOf(map));
                return map;
            }
        };

        queue.add(request);
    }

    public void getScores() {
        String url = "http://192.168.1.79/java/json/users.json";
        recyclerView = recyclerView.findViewById(R.id.recycler_view);
        users = new ArrayList<>();

        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("RESULT :", "Everything good" + response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject userObject = response.getJSONObject(i);
                        Users user = new Users();
                        user.setNameUser(userObject.getString("pseudo").toString());
                        user.setScoreUser(Integer.parseInt(userObject.getString("value").toString()));

                        users.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("RESULT :", "Something wrong" + error);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(context.getApplicationContext()));
        adapter = new Adapter(context.getApplicationContext(), users);
        recyclerView.setAdapter(adapter);

        queue.add(json);

    }
}
