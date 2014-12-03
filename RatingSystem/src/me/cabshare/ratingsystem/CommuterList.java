package me.cabshare.ratingsystem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CommuterList extends Activity {


	
	public static final String ITEM_KEY = "item_key";
	public static final String DEBUG_TAG = "Android Tutorial App";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// sets the android xml view file to this activity
		setContentView(R.layout.commuter_list);
		// method convention I use to set all UI widgets
		setUpWidgets();

	}

	private void setUpWidgets() {
		
		ListView list = (ListView) findViewById(R.id.listView1);

		
		ArrayList<String> items = new ArrayList<String>();

		//I'm guessing these would be fetched using some R.id.commuter type thing like
		//when you get listView1
		items.add("Commuter1");
		items.add("Commuter2");
		items.add("Commuter3");
		items.add("Commuter4");

		
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
		});
	}

	private void itemSelected(int arg) {
		switch (arg) {
		case 0:
			
			Intent intent0 = new Intent(this, UserRatingPage.class);
			Bundle extras0 = new Bundle();
			extras0.putString("item_key", "pass information between activities");
			
			//attach the bundle to the intent
			intent0.putExtras(extras0);
			
			//start activity
			startActivity(intent0);
			break;
		case 1:
			Intent intent1 = new Intent(this, UserRatingPage.class);
			Bundle extras1 = new Bundle();
			extras1.putString("item_key", "pass information between activities");
			
			//attach the bundle to the intent
			intent1.putExtras(extras1);
			
			//start activity
			startActivity(intent1);
			break;
		case 2:
			Intent intent2 = new Intent(this, UserRatingPage.class);
			Bundle extras2 = new Bundle();
			extras2.putString("item_key", "pass information between activities");
			
			//attach the bundle to the intent
			intent2.putExtras(extras2);
			
			//start activity
			startActivity(intent2);
			break;
		case 3:
			Intent intent3 = new Intent(this, UserRatingPage.class);
			Bundle extras3 = new Bundle();
			extras3.putString("item_key", "pass information between activities");
			
			//attach the bundle to the intent
			intent3.putExtras(extras3);
			
			//start activity
			startActivity(intent3);
			break;
		}
		Toast.makeText(this, "Item Number: " + arg, Toast.LENGTH_SHORT).show();
	}
	
}
