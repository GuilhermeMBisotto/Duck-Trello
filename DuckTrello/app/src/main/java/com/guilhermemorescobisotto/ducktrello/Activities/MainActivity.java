package com.guilhermemorescobisotto.ducktrello.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.guilhermemorescobisotto.ducktrello.R;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

//	FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//	fab.setOnClickListener(new View.OnClickListener() {
//	  @Override
//	  public void onClick(View view) {
//		Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//				.setAction("Action", null).show();
//	  }
//	});
  }
}
