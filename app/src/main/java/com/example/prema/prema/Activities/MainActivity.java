package com.example.prema.prema.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.prema.prema.Data.ContactAdapter;
import com.example.prema.prema.Model.Contacts;
import com.example.prema.prema.R;
import com.example.prema.prema.Util.Constants;
import com.example.prema.prema.Util.preference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ContactAdapter contactAdapter;
    private List<Contacts> contactsList;
    private RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.contactrecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactsList = new ArrayList<>();
        preference pref = new preference(MainActivity.this);
        String search = pref.getSearch();
        getContact(search);

        contactAdapter = new ContactAdapter(this,contactsList);
        recyclerView.setAdapter(contactAdapter);
    }

    public List<Contacts> getContact(String searchTerm) {
        contactsList.clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                Constants.URL, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray contactArray = response.getJSONArray("search");
                    for (int i = 0; i < contactArray.length(); i++) {
                        JSONObject contactobj = contactArray.getJSONObject(i);
                        Contacts contact = new Contacts((contactobj.getString("name")),(contactobj.getString("image")));
                        contact.setName(contactobj.getString("name"));
                        Log.d("Api response",(contactobj.get("name")).toString());
                        Log.d("Api response",(contactobj.get("image")).toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonObjectRequest);
        return contactsList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
