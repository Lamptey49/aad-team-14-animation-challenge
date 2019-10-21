package com.example.movieapp;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieItem extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ListItem> listItems;
    private static final String MOVIE_LIST = "https://api.themoviedb.org/3/discover/movie?api_key=cb96f17322096e1d8c4089a24de43315";
    private static final String TV_SHOWS = "https://api.themoviedb.org/3/discover/tv?api_key=cb96f17322096e1d8c4089a24de43315";
    private static final String POPULAR_MOVIES = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=cb96f17322096e1d8c4089a24de43315";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_item);
        //recyclerView.setHasFixedSize(true);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(layoutManager);

        LoadRecyclerViewData();
    }

    private void LoadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, MOVIE_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("results");

                            for(int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
//
                               String movietitle = jsonObject.getString("title");
                               String imageurl = jsonObject.getString("poster_path");
                               int vote = jsonObject.getInt("vote_count");
//
                                listItems.add(new ListItem(imageurl, movietitle,vote));
                            }
                            mAdapter = new MovieAdapter(MovieItem.this, listItems);
                            recyclerView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    }

