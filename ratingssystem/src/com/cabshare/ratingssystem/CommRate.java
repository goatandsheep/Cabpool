package com.cabshare.ratingssystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class CommRate extends Activity {
	
	public static final String ITEM_KEY = "item_key";
	public static final String DEBUG_TAG = "Cabpool Rating page";
	
	/*private*/ RatingBar starBar;
	/*private*/ TextView textRating;
	/*private*/ Button btnSubmit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commuterrating);
		setUpWidgets();
	}
	
	private void setUpWidgets() {
		addListenerOnRatingBar();
		addListenerOnButton();
		
	}

	private void addListenerOnButton() {
		// perform a function to calculate and store the rating 
		// take the user to the final page 'thank you for your input'
		
		starBar = (RatingBar) findViewById(R.id.ratingStar);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
		// click then display the current rating value.
		btnSubmit.setOnClickListener(new OnClickListener() {
	 
			@Override
			public void onClick(View v) {
	 
				Intent intent = new Intent(CommRate.this, thanksExit.class);
				Bundle extras = new Bundle();
				extras.putString("item_key", "go to commuter 1 rating page");
				intent.putExtras(extras);
				Log.d(DEBUG_TAG, "switch to commuter1");
				
				startActivity(intent);
				
				//STORE VALUE INTO DB INSTEAD OF TOASTING
				Toast.makeText(CommRate.this,
					String.valueOf(starBar.getRating()),
						Toast.LENGTH_SHORT).show();
			}
	});
	}

	//okayy so no longer crashing; STORE INTO DATABASE ON SUBMIT
	private void addListenerOnRatingBar() {
		
		starBar = (RatingBar) findViewById(R.id.ratingStar);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	 
		starBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				//textRating.setText(String.valueOf(rating));
				
				Toast.makeText(getApplicationContext(), "You rated this commuter" + String.valueOf(rating), Toast.LENGTH_LONG).show();
			}
		});
	}
	

}
