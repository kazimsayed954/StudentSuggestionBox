package com.kazim.studentsuggestionbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllSuggestionActivity extends AppCompatActivity {
    private static final String HI = "http://192.168.56.1/android_login_api/getSuggestionmaxLike.php";
    private List<MyListData>myListData;
    private RecyclerView rv;
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private MyAdapter adapter;
    public static MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_suggestion);
        rv=(RecyclerView)findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        myListData=new ArrayList<>();
        myDatabase= Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"mydb").allowMainThreadQueries().build();
        getData();
    }

    private void getData() {
        request=new JsonArrayRequest(HI, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject=null;
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject ob=response.getJSONObject(i);
                        MyListData md=new MyListData(ob.getInt("uid"),ob.getString("image_path"),ob.getString("like"),ob.getString("description"),ob.getString("status"));

                        myListData.add(md);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                setupData( myListData);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void setupData(List<MyListData> myListData) {


        adapter=new MyAdapter( myListData,this);
        rv.setAdapter(adapter);


    }
}