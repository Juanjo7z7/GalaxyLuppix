package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.myrequest.MyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {
    //private MyRequest request;
    private Button retourBtn;
   RecyclerView recyclerView;
    Adapter adapter;
    String url = "http://192.168.1.79/java/json/users.json";
    List<Users> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        recyclerView = findViewById(R.id.recycler_view);
        retourBtn = findViewById(R.id.retour_btn);
        users = new ArrayList<>();
        getScores();

        retourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });



    }

    private void getScores() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("ERRORRRR", String.valueOf(response));
                for (int i = 0; i < response.length(); i++) {
                    //Log.d("ERRORRRR", "In the loop");
                    int j = i+1;
                    try {
                        Log.d("ERRORRRR", String.valueOf(response.getJSONObject(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        //Log.d("ERRORRRR", "success" + i);
                        JSONObject userObj = response.getJSONObject(i);
                        Users user = new Users();
                        user.setPosition(j);
                        user.setNameUser(userObj.getString("name").toString());
                        user.setScoreUser(userObj.getInt("score"));

                        Log.d("ERRORRRR", "success creating view");
                        users.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("ERRORRRR", "error creating view");
                    }
                }
                //Log.d("ERRORRRR", "success for the loop");
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), users);
                recyclerView.setAdapter(adapter);
                //Log.d("ERRORRRR", "success the 3 lines of onResponse");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("ERRORRRR", "onErrorResponse: " + error);
            }
        });
        //Log.d("ERRORRRR", "success onResponse");
        queue.add(json);
        //Log.d("ERRORRRR", "adding to queue");
    }
}