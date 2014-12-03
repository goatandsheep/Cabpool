package com.cabshare.ratingsystem;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
	
	public static final String ITEM_KEY = "item_key";
	public static final String DEBUG_TAG = "Cabpool Rating System";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUpWidgets();
	}

	private void setUpWidgets() {
		ListView list = (ListView) findViewById(R.id.lvItemList);
		ArrayList<String> items = new ArrayList<String>();

		items.add("Continue to a list of all fellow Commuters for this trip.");
		ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, items);
		list.setAdapter(itemAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// arg2 returns the element number of the list
				// call a method to handle the itemClick
				itemSelected(arg2);
			}
		});}
		
		private void itemSelected(int arg) {
			switch (arg) {
			case 0:
				
				Intent intent = new Intent(MainActivity.this, CommList.class);
				Bundle extras = new Bundle();
				extras.putString("item_key", "pass information between activities");
				intent.putExtras(extras);
				Log.d(DEBUG_TAG, "switch to list of commuters");
				
				startActivity(intent);
				
				break;
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}