package com.example.ptit_biscuit.whatthefilm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Iterator;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        String movieName = getIntent().getStringExtra("movieName");
        String lang = getIntent().getStringExtra("language");
        String year = getIntent().getStringExtra("year");

        String research = "http://api.themoviedb.org/3/search/movie?" +
                "api_key=b9cc31a4378d323f504ed73a57e513a5" +
                "&include_adult=false";

        research += this.verif(movieName, lang, year);

        ((TextView) findViewById(R.id.textView4)).setText(research);

        //image --> https://image.tmdb.org/t/p/w45/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
        //Picasso.with(this).load("https://image.tmdb.org/t/p/w45/" + );

        this.request(research);
    }

    private String verif(String movieName, String lang, String year) {
        String toAdd = "";

        if (movieName.isEmpty()) {
            Toast.makeText(this, "Le nom du film ne doit pas être null", Toast.LENGTH_SHORT).show();
            this.finishActivity(10);
        }
        else
            toAdd += "&query=" + movieName;

        if (!"all".equals(lang))
            toAdd += "&language=" + lang;

        if (!year.isEmpty())
            toAdd += "&year=" + year;

        return toAdd;
    }

    private void request(String reseach) {
        final ArrayList<Movie> movies = new ArrayList<>();
        RequestQueue request = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                reseach,
                new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try {
                            JSONObject repObj =
                                    (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray result = (JSONArray) repObj.get("results");

                            ((TextView) findViewById(R.id.textView5)).setText(result.toString());

                            movies.add(
                                    new Movie(
                                            result.getString(8),
                                            result.getString(2),
                                            result.getString(3)
                                    )
                            );
                        } catch (JSONException je) {
                            Log.e("Ptit-Biscuit", je.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.getMessage());
                    }
                }
        );

        request.add(stringRequest);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new MovieAdapter(this, movies));
    }
}
