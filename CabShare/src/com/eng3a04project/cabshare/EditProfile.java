package com.eng3a04project.cabshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfile extends Activity{
	public static int EditedSuccess;
	private TextView usernamefixed;
	private EditText passwordInput,firstnameInput,lastnameInput,emailInput;
	private Button confirmButton,cancelButton;
	private static Context context;
	private static String user_name, userpassword,first_name,last_name,emailaddress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("Registration Page");
		setContentView(R.layout.editprofile);
		context = this;
		
		
		usernamefixed = (TextView)findViewById(R.id.editusername);
		passwordInput = (EditText)findViewById(R.id.edituserpassword);
		firstnameInput = (EditText)findViewById(R.id.editfirstname);
		lastnameInput = (EditText)findViewById(R.id.editlastname);
		emailInput = (EditText)findViewById(R.id.editemailaddress);
		confirmButton = (Button)findViewById(R.id.confirmeditBtn);
		cancelButton = (Button) findViewById(R.id.canceleditBtn);
		usernamefixed.setText(CabshareActivity.getUser_name());
		
		confirmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//get the input in the EditTextView widgets
				
				user_name = CabshareActivity.getUser_name();
				userpassword = passwordInput.getText().toString();
				first_name = firstnameInput.getText().toString();
				last_name = lastnameInput.getText().toString();
				emailaddress = emailInput.getText().toString();
				
				//do the registration
				EditProfileTask editTask = new EditProfileTask(user_name,userpassword,first_name,last_name,emailaddress);
				editTask.execute();
			}
		});
		
		cancelButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Intent i = new Intent(EditProfile.this,ViewProfile.class);
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
	public static void setEdit(int EditSuccess){
		EditedSuccess = EditSuccess;
		if(EditedSuccess ==1){
			Log.d("This command","excuted once");
			Toast.makeText(context,"Successfully updated! Jumping to login page",Toast.LENGTH_SHORT).show();
//			EditProfileTask ediTask = new EditProfileTask(user_name, userpassword,first_name,last_name,emailaddress);
//			ediTask.execute();
			jumptoMain();
		}else if (EditedSuccess ==0){
			Toast.makeText(context, "Unable to edit, failed....",  Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "You know,shit happens...", Toast.LENGTH_SHORT).show();
		}
	
	}

	public static void jumptoMain(){
		Intent i = new Intent(context,CabshareActivity.class);
		context.startActivity(i);
	}
}
