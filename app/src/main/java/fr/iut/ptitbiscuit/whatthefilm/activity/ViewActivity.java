package fr.iut.ptitbiscuit.whatthefilm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import fr.iut.ptitbiscuit.whatthefilm.R;
import fr.iut.ptitbiscuit.whatthefilm.data.Movie;
import fr.iut.ptitbiscuit.whatthefilm.data.MovieAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import static fr.iut.ptitbiscuit.whatthefilm.WTFApplication.getAppRequestQueue;

/**
 * {@link Activity} affichant les résultats de la recherche entrée dans {@link MainActivity}
 */
public class ViewActivity extends AppCompatActivity {

	/**
	 * Tag utilisé dans les {@link Log} de cette {@link Activity}
	 */
	private static final String TAG = "ViewActivity";

	/**
	 * Référence vers les différentes {@link View} utilisées afin d'éviter d'appeler {@link #findViewById(int)}
	 */
	private static class ViewHolder {
		public ListView listView;
	}

	/**
	 * L'instance de {@link ViewHolder} utilisée par cette {@link ViewActivity}
	 */
	public final ViewHolder viewHolder = new ViewHolder();

	/**
	 * Initialise cette {@link ViewActivity} et récupère les données de l'{@link Intent}
	 * l'ayant appelée afin de construire la requête pour <a href="https://www.themoviedb.org">themoviedb.org</a>
	 * @param savedInstanceState données de backup de cette {@link MainActivity} (jamais utilisé)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view);
		this.viewHolder.listView = (ListView) findViewById(R.id.listView);

		String research =
				String.format("http://api.themoviedb.org/3/search/movie?api_key=b9cc31a4378d323f504ed73a57e513a5&query=%s&language=%s&year=%s",
				getIntent().getStringExtra("movieName"),
				getIntent().getStringExtra("language"),
				getIntent().getStringExtra("year"));

//        ((TextView) findViewById(R.id.textView4)).setText(research);

		//image --> https://image.tmdb.org/t/p/w45/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg
		//Picasso.with(this).load("https://image.tmdb.org/t/p/w45/" + );

		this.request(research);
	}

	private void request(String research) {
		final ArrayList<Movie> movies = new ArrayList<>();
		StringRequest stringRequest = new StringRequest(
				Request.Method.POST,
				research,
				new Response.Listener<String>() {
					public void onResponse(String response) {
						try {
							JSONObject repObj = (JSONObject) new JSONTokener(response).nextValue();
							JSONArray result = repObj.getJSONArray("results");

							JSONObject obj;
							JSONArray arr;
							String[] categories;
							for(int i = 0; i < result.length(); i++) {
								obj = result.getJSONObject(i);
								arr = obj.getJSONArray("genre_ids");
								categories = new String[arr.length()];
								for(int j = 0; j < arr.length(); j++)
									categories[j] = MainActivity.getGenres().get(arr.getInt(j));
								movies.add(
										new Movie()
											.setTitle(obj.getString("title"))
											.setDesc(obj.getString("overview"))
											.setDate(obj.getString("release_date"))
											.setRating(obj.getLong("vote_average"))
											.setImagePath(obj.getString("poster_path"))
											.setCategories(categories)
								);
								Log.i("Movie found", movies.get(i).toString());

							}
						}
						catch(JSONException je) {
							Log.e("JSON result", je.getMessage());
						}
					}
				},
				new Response.ErrorListener() {
					public void onErrorResponse(VolleyError error) {
						Log.e("VOLLEY", error.getMessage());
					}
				}
		);
		getAppRequestQueue().add(stringRequest);
		viewHolder.listView.setAdapter(new MovieAdapter(this, movies));
	}
}
