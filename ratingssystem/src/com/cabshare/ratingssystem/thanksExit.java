package com.cabshare.ratingssystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class thanksExit extends Activity{
	
	Button exitbtn;
	
	public static final String ITEM_KEY = "item_key";
	public static final String DEBUG_TAG = "Cabpool exit ratings";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thanks_exit);
		setUpWidgets();
	}
	
	private void setUpWidgets() {
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		exitbtn = (Button) findViewById(R.id.exitbtn);	
		
		exitbtn.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

});
		
}}