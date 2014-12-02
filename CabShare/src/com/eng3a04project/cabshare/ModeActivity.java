package com.eng3a04project.cabshare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class ModeActivity extends ActionBarActivity {
	  private Button mRequestButton,mOfferButton,viewProfileButton,backToLoginButton;
	 
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
				Intent i = new Intent(ModeActivity.this, ScanBeforeOffer.class);
				startActivity(i);
			}
		});
        
        viewProfileButton=(Button) findViewById(R.id.view_profilebtn);
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ModeActivity.this, ViewProfile.class);
				startActivity(i);
			}
		});
        
        backToLoginButton=(Button) findViewById(R.id.backToLoginBtn);
        backToLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ModeActivity.this, CabshareActivity.class);
				startActivity(i);
			}
		});
	 }
	
	@Override
    public void onStart() {
    super.onStart();
    }
    @Override
    public void onPause() {
    super.onPause();
    }
    @Override
    public void onResume() {
    super.onResume();
    }
    @Override
    public void onStop() {
    super.onStop();
    }
    @Override
    public void onDestroy() {
    super.onDestroy();
    }
}

