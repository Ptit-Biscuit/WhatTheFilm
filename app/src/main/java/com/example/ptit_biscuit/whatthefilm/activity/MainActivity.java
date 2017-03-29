package com.example.ptit_biscuit.whatthefilm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.ptit_biscuit.whatthefilm.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void research(View v) {
		startActivity(
			new Intent(this, ViewActivity.class)
				.putExtra("movieName",
						((EditText) findViewById(R.id.editText))
								.getText()
								.toString()
				)
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
