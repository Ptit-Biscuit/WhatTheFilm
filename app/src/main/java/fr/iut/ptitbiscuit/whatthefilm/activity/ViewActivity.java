package fr.iut.ptitbiscuit.whatthefilm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

import fr.iut.ptitbiscuit.whatthefilm.R;
import fr.iut.ptitbiscuit.whatthefilm.data.Movie;
import fr.iut.ptitbiscuit.whatthefilm.data.MovieAdapter;

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
	 * Message d'information si aucun film n'est récupéré
	 */
	private static final String NODATA = "Aucun film correspondant à votre recherche n'a été trouvé";

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
	 * Liste des films obtenus à l'aide de la requête
	 */
	private ArrayList<Movie> movies;

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

		this.request(research);
		this.listenerManager();
	}

	/**
	 * Envoie la requête <i>research</i> construite et parse/traite les données retournées
	 * @param research la requête http dont on souhaite récupérer les données (en JSON)
	 */
	private void request(String research) {
		this.movies = new ArrayList<>();
		findViewById(R.id.listView).setVisibility(View.INVISIBLE);
		findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

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
											.setRating(obj.getLong("vote_average") / 2)
											.setImagePath(obj.getString("poster_path"))
											.setCategories(categories)
								);
								Log.i("Movie found", movies.get(i).toString());
							}

							if (movies.size() != 0) {
								viewHolder.listView.setAdapter(new MovieAdapter(ViewActivity.this, movies));
								findViewById(R.id.progressBar).setVisibility(View.GONE);
								findViewById(R.id.listView).setVisibility(View.VISIBLE);
							}
							else {
								Toast.makeText(ViewActivity.this, NODATA, Toast.LENGTH_SHORT).show();
								finish();
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
	}

	/**
	 * Gestionnaire du listener pour le traitement associé au click
	 * sur l'un des éléments de la liste des films
	 */
	private void listenerManager() {
		((AdapterView) findViewById(R.id.listView)).setOnItemClickListener(
				new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						startActivity(
								new Intent(ViewActivity.this, DetailsActivity.class)
										.putExtra("movie", movies.get(position))
						);
					}
				}
		);
	}
}
