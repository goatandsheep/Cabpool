package com.eng3a04project.cabshare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.*;


public class CabshareActivity extends ActionBarActivity {

	private static String user_name, userpassword, first_name, last_name, emailaddress;
	private Button mRegisterButton;
	private Button mLoginButton;
	private Button mGPlusButton;
	private EditText loginInput, passwordInput;
	private static int success;
	public EditText getLoginInput() {
		return loginInput;
	}


	public void setLoginInput(EditText loginInput) {
		this.loginInput = loginInput;
	}


	public EditText getPasswordInput() {
		return passwordInput;
	}


	public void setPasswordInput(EditText passwordInput) {
		this.passwordInput = passwordInput;
	}

	//G+ Sign in button stuff
	/* Track whether the sign-in button has been clicked so that we know to resolve
	 * all issues preventing sign-in without waiting.
	 */
	private boolean mSignInClicked;

	/* Store the connection result from onConnectionFailed callbacks so that we can
	 * resolve them when the user clicks sign-in.
	 */
	private ConnectionResult mConnectionResult;

	/* A helper method to resolve the current ConnectionResult error. */
	private void resolveSignInError() {
	  if (mConnectionResult.hasResolution()) {
	    try {
	      mIntentInProgress = true;
	      startIntentSenderForResult(mConnectionResult.getResolution().getIntentSender(),
	          RC_SIGN_IN, null, 0, 0, 0);
	    } catch (SendIntentException e) {
	      // The intent was canceled before it was sent.  Return to the default
	      // state and attempt to connect to get an updated ConnectionResult.
	      mIntentInProgress = false;
	      mGoogleApiClient.connect();
	    }
	  }
	}

	public void getGravatar(String email)
	{
		String hash = MD5(email);
		hash = "http://gravatar.com/avatar/" + hash;
		
	}
	
	private String MD5(String md5) {
	   try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(md5.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        return sb.toString();
	    } catch (java.security.NoSuchAlgorithmException e) {
	    }
	    return null;
	}
	
	public void onConnectionFailed(ConnectionResult result) {
	  if (!mIntentInProgress) {
	    // Store the ConnectionResult so that we can use it later when the user clicks
	    // 'sign-in'.
	    mConnectionResult = result;

	    if (mSignInClicked) {
	      // The user has already clicked 'sign-in' so we attempt to resolve all
	      // errors until the user is signed in, or they cancel.
	      resolveSignInError();
	    }
	  }
	}
	
	private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabshare);
        setTitle("CabShare log in page");
        context = this;
        mRegisterButton = (Button)findViewById(R.id.Register_button);
        mLoginButton = (Button)findViewById(R.id.login_button);
        mGPlusButton = (Button)findViewById(R.id.sign_in_button);
        loginInput =(EditText)findViewById(R.id.user_namespace);
        passwordInput =(EditText)findViewById(R.id.password_namespace);
        
        mRegisterButton.setOnClickListener(new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
				// start RegistrationActivity
				Intent i = new Intent(CabshareActivity.this, RegistrationActivity.class);
				startActivity(i);
			}
		});
        mLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String userInput1 = loginInput.getText().toString().trim();
				String userInput2 = passwordInput.getText().toString().trim();
				
				Log.d("Input 1 = ",userInput1);
				Log.d("Input 2 = ",userInput2);
				
				LoginTask logintask = new LoginTask(userInput1,userInput2);
				logintask.execute();
				
				
				Log.d("Login task executed","Login task executed");
			}
		});
        mGPlusButton.setOnClickListener(new View.OnClickListener() {
        	
        		@Override
        		public void onClick(View view){
        			  if (view.getId() == R.id.sign_in_button
        					    && !mGoogleApiClient.isConnecting()) {
        					    mSignInClicked = true;
        					    resolveSignInError();
					  }
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
    
    public static  void executeLogin(int loginSuccess){
    	success = loginSuccess;
    	if(success == 1){
    		Intent loginIntent = new Intent(context, ModeActivity.class);
    		context.startActivity(loginIntent);
    		Toast.makeText(context, "success,loading...", Toast.LENGTH_SHORT).show();
    	}else{
    		Toast.makeText(context, "failed...", Toast.LENGTH_SHORT).show();
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cabshare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
	public static String getUser_name() {
		return user_name;
	}


	public static void setUser_name(String user_name) {
		CabshareActivity.user_name = user_name;
	}


	public static String getUserpassword() {
		return userpassword;
	}


	public static void setUserpassword(String userpassword) {
		CabshareActivity.userpassword = userpassword;
	}


	public static String getFirst_name() {
		return first_name;
	}


	public static void setFirst_name(String first_name) {
		CabshareActivity.first_name = first_name;
	}


	public static String getLast_name() {
		return last_name;
	}


	public static void setLast_name(String last_name) {
		CabshareActivity.last_name = last_name;
	}


	public static String getEmailaddress() {
		return emailaddress;
	}


	public static void setEmailaddress(String emailaddress) {
		CabshareActivity.emailaddress = emailaddress;
	}


	public static int getSuccess() {
		return success;
	}


	public static void setSuccess(int success) {
		CabshareActivity.success = success;
	}
	
	//receiving a connection
	@Override
	public void onConnected(Bundle connectionHint) {
	  mSignInClicked = false;
	  Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();
	}
    
}
