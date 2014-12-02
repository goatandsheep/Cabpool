package com.eng3a04project.cabshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends Activity {

	private TextView usernameView,userpasswordView,firstnameView,lastnameView,emailView;
	private Button editProfileButton,backButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.viewprofile);
        setTitle("CabShare log in page");
        //Connect all widgets in .xml
        usernameView=(TextView) findViewById(R.id.usernameview);
        userpasswordView=(TextView) findViewById(R.id.userpasswordview);
        firstnameView=(TextView) findViewById(R.id.firstnameview);
        lastnameView=(TextView) findViewById(R.id.lastnameview);
        emailView=(TextView) findViewById(R.id.useremailview);
        editProfileButton=(Button) findViewById(R.id.editprofilebtn);
        backButton=(Button) findViewById(R.id.backtomodebtn);
        
        usernameView.setText(CabshareActivity.getUser_name());
        userpasswordView.setText(CabshareActivity.getUserpassword());
        firstnameView.setText(CabshareActivity.getFirst_name());
        lastnameView.setText(CabshareActivity.getLast_name());
        emailView.setText(CabshareActivity.getEmailaddress());
        
        backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewProfile.this,ModeActivity.class);
				startActivity(i);
			}
		});
        
        editProfileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ViewProfile.this,EditProfile.class);
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
