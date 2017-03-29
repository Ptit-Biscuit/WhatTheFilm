package fr.iut.ptitbiscuit.whatthefilm.activity;

import android.annotation.SuppressLint;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static fr.iut.ptitbiscuit.whatthefilm.WTFApplication.getAppRequestQueue;

/**
 * {@link Activity} principale permettant de saisir une recherche et de lancer l'activité affichant les résultats
 */
public class MainActivity extends AppCompatActivity {

	/**
	 * la {@link Map} associant les genres de films à leur id
	 */
	private static Map<Integer, String> genres;

	/**
	 * Référence vers les différentes {@link View} utilisées afin d'éviter d'appeler {@link #findViewById(int)}
	 */
	private static class ViewHolder {
		public EditText movieName;
		public Spinner language;
		public EditText year;
		public Button researchButton;
		public ProgressBar mainLoading;
	}

	/**
	 * L'instance de {@link ViewHolder} utilisée par cette {@link MainActivity}
	 */
	public final ViewHolder viewHolder = new ViewHolder();

	/**
	 * Initialise cette {@link MainActivity}, instancie les champs de son {@link ViewHolder},
	 * récupère les genres de films sur <a href="https://www.themoviedb.org">themoviedb.org</a>
	 * et les place dans {@link #genres}
	 * @param savedInstanceState données de backup de cette {@link MainActivity} (jamais utilisé)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.viewHolder.movieName = (EditText) findViewById(R.id.editText);
		this.viewHolder.language = (Spinner) findViewById(R.id.language);
		this.viewHolder.year = (EditText) findViewById(R.id.years);
		this.viewHolder.researchButton = (Button) findViewById(R.id.researchButton);
		this.viewHolder.mainLoading = (ProgressBar) findViewById(R.id.main_loading);

		if(genres == null) {
			// État initial de la vue : le bouton recherche ne peut être cliqué et la barre de chargement est animée
			this.viewHolder.researchButton.setClickable(false);
			this.viewHolder.mainLoading.setIndeterminate(true);

			@SuppressLint("UseSparseArrays")
			final Map<Integer, String> tmpMap = new HashMap<>();

			StringRequest request = new StringRequest(
					Request.Method.GET,
					String.format("https://api.themoviedb.org/3/genre/movie/list?api_key=b9cc31a4378d323f504ed73a57e513a5&language=%s", Locale.getDefault().getLanguage()),
					new Response.Listener<String>() {
						public void onResponse(String response) {
							try {
								JSONObject repObj = (JSONObject) new JSONTokener(response).nextValue();
								JSONArray result = (JSONArray) repObj.get("genres");
								JSONObject obj;
								for(int i = 0; i < result.length(); i++) {
									obj = result.getJSONObject(i);
									tmpMap.put(obj.getInt("id"), obj.getString("name"));
								}

								// Quand les genres ont été récupérés, le bouton recherche redevient cliquable et la barre de chargement disparait
								viewHolder.researchButton.setClickable(true);
								viewHolder.mainLoading.setIndeterminate(false);
								viewHolder.mainLoading.setVisibility(View.INVISIBLE);
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
			getAppRequestQueue().add(request);
			// La map genres est en lecture seule
			genres = Collections.unmodifiableMap(tmpMap);
		}
	}

	/**
	 * Méthode appelée lorsque le bouton de recherche est cliqué
	 * @param v l'instance de {@link View} qui a été cliquée (le bouton de recherche)
	 */
	public void research(View v) {
		// Si le champ de recherche n'est pas vide, l'activité suivante est appelée
		String movieName = this.viewHolder.movieName.getText().toString();
		if(this.verif(movieName)) {
			startActivity(
					new Intent(this, ViewActivity.class)
							.putExtra("movieName", movieName)
							.putExtra("language",
									((Spinner) findViewById(R.id.language))
											.getSelectedItem()
											.toString()
											.split(" - ")[1]
							)
							.putExtra("year",
									((EditText) findViewById(R.id.years))
											.getText()
											.toString()
							)
			);
		}
	}

	/**
	 * Méthode vérifiant que les données entrées sont correctes
	 * @param query la recherche entrée dans le champ de texte
	 * @return true si les données sont valides et false sinon
	 */
	private boolean verif(String query) {
		if(query.isEmpty()) {
			Toast.makeText(this, "Veuillez entrer un filmz", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	/**
	 * Retourne la {@link Map} associant les genres de films à leur id
	 * @return la {@link Map} associant les genres de films à leur id
	 */
	public static Map<Integer, String> getGenres() {
		return genres;
	}
}
