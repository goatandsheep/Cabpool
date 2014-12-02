package com.eng3a04project.cabshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {
	public static int RegisterSuccess;
	private static Context context;
	private EditText usernameInput,passwordInput,firstnameInput,lastnameInput,emailInput;
	private Button confirmButton,cancelButton;
	private static String user_name, userpassword,first_name,last_name,emailaddress;
	
	
	public static String getUser_name() {
		return user_name;
	}

	public static void setUser_name(String user_name) {
		RegistrationActivity.user_name = user_name;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setTitle("Registration Page");
	setContentView(R.layout.activity_registration);
	context = this;
	
	
	usernameInput = (EditText)findViewById(R.id.newusernameInput);
	passwordInput = (EditText)findViewById(R.id.createdpassword_namespace);
	firstnameInput = (EditText)findViewById(R.id.firstnameInput);
	lastnameInput = (EditText)findViewById(R.id.lastnameInput);
	emailInput = (EditText)findViewById(R.id.useremailInput);
	confirmButton = (Button)findViewById(R.id.confirmRegist_button);
	cancelButton=(Button) findViewById(R.id.cancelRegist_button);
	confirmButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			user_name = usernameInput.getText().toString();
			userpassword = passwordInput.getText().toString();
			first_name = firstnameInput.getText().toString();
			last_name = lastnameInput.getText().toString();
			emailaddress = emailInput.getText().toString();
			
			RegistrationTask RegistTask = new RegistrationTask(user_name,userpassword,first_name,last_name,emailaddress);
			RegistTask.execute();	
		}
	});
	
	cancelButton.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent (RegistrationActivity.this,CabshareActivity.class);
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

	public static void setRegister(int RegistSuccess){
		RegisterSuccess = RegistSuccess;
		if(RegisterSuccess ==1){
			Toast.makeText(context,"Successfully registered! Jumping to login page",Toast.LENGTH_LONG).show();
			RegistrationTask regTask = new RegistrationTask(user_name, userpassword,first_name,last_name,emailaddress);
			regTask.execute();
			jumptoMain();
		}else if (RegisterSuccess ==0){
			Toast.makeText(context, "Unable to register, failed....",  Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(context, "Username taken, try another one", Toast.LENGTH_SHORT).show();
		}
	}
	public static void jumptoMain(){
		Intent i = new Intent(context,CabshareActivity.class);
		context.startActivity(i);
	}
	
}
