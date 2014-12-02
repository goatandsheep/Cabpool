package com.eng3a04project.cabshare;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanBeforeOffer extends Activity {
	private static String user_name;
	private Button scanButton;
	private TextView text;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanbeforeoffer);
        scanButton = (Button)findViewById(R.id.scanQRButton);
        text = (TextView) findViewById(R.id.textDiaplayQR);
        scanButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ScanBeforeOffer.this, "Scan QR code before u can offer a taxi! ", Toast.LENGTH_SHORT).show();
				Intent startScan = new Intent(ScanBeforeOffer.this,CaptureActivity.class);
			//	startActivity(startScan);
				startActivityForResult(startScan, 0);
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
  @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == RESULT_OK){
		user_name=CabshareActivity.getUser_name();
		String CabID = data.getExtras().getString("result");
		text.setText("CabID is: "+CabID+"\n click on the screen to continue...");
		Log.d("cabid is ",CabID);
		Log.d("user_name is",user_name);
		ScanTask scantask = new ScanTask(CabID,user_name);
		scantask.execute();
		text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ScanBeforeOffer.this,OfferActivity.class);
				startActivity(i);
			}
		});

	}
}
}
