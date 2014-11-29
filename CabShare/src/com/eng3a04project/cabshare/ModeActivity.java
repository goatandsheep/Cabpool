package com.eng3a04project.cabshare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class ModeActivity extends ActionBarActivity {
	  private Button mRequestButton;
	  private Button mOfferButton;
	@Override
	    protected void onCreate(Bundle savedInstanceState){
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_mode);
	    setTitle("Mode selection page");
	    mRequestButton = (Button)findViewById(R.id.requestmode_button);
        mRequestButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// start RegistrationActivity
				Intent i = new Intent(ModeActivity.this, RequestActivity.class);
				startActivity(i);
			}
		});
        
	    mOfferButton = (Button)findViewById(R.id.offermode_button);
        mOfferButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// start RegistrationActivity
				Intent i = new Intent(ModeActivity.this, OfferActivity.class);
				startActivity(i);
			}
		});
	 }
}
