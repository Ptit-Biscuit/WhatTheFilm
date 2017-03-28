package com.example.ptit_biscuit.whatthefilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void research(View v) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("movieName",
                ((EditText) findViewById(R.id.editText))
                        .getText()
                        .toString()
        );
        intent.putExtra("language",
                ((Spinner) findViewById(R.id.language))
                        .getSelectedItem()
                        .toString()
                        .split(" - ")[1]
        );
        intent.putExtra("year",
                ((EditText) findViewById(R.id.years))
                        .getText()
                        .toString()
        );

        startActivity(intent);
    }
}
