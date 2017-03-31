package fr.iut.ptitbiscuit.whatthefilm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

import fr.iut.ptitbiscuit.whatthefilm.R;
import fr.iut.ptitbiscuit.whatthefilm.data.Movie;


//TODO l'activité affichant les détails du film passé dans l'intent + sa javadoc
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

	    Movie movie = getIntent().getParcelableExtra("movie");

	    ((TextView) findViewById(R.id.titre)).setText(movie.getTitle());
	    ((TextView) findViewById(R.id.date)).setText(movie.getDate());
	    Picasso
			    .with(this)
			    .load("https://image.tmdb.org/t/p/w500/" + movie.getImagePath())
			    .into(((ImageView) findViewById(R.id.poster)));
	    ((TextView) findViewById(R.id.desc)).setText(movie.getDesc());
	    ((TextView) findViewById(R.id.categories)).setText(Arrays.toString(movie.getCategories()));
	    ((RatingBar) findViewById(R.id.score)).setRating(movie.getRating());
    }
}
