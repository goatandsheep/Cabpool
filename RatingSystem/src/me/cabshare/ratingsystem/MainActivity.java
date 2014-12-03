package me.cabshare.ratingsystem;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String ITEM_KEY = "item_key";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//      //set up the button widget
//    	Button button = (Button) findViewById(R.id.button1);
//    	
//    	button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				itemSelected(v);
//			}
//		});
//    	
//        setUpWidgets();
//        
   }

    public void goRatings(View h){
    	Intent intent = new Intent(this, CommuterList.class);
		Bundle extras = new Bundle();
		extras.putString("item_key", "pass information between activities");
		
		//attach the bundle to the intent
		intent.putExtras(extras);
		//start activity
		startActivity(intent);
    }

//    private void setUpWidgets() {
//		 
//	}

   

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
