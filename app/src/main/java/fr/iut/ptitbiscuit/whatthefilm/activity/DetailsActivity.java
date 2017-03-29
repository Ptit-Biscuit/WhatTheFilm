package fr.iut.ptitbiscuit.whatthefilm.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import fr.iut.ptitbiscuit.whatthefilm.R;


//TODO l'activité affichant les détails du film passé dans l'intent + sa javadoc
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }
}
