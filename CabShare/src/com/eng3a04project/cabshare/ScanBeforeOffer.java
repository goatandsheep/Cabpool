package com.eng3a04project.cabshare;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScanBeforeOffer extends Activity {

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
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	if(resultCode == RESULT_OK){
		String result = data.getExtras().getString("result");
		text.setText(result);
		text.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(ScanBeforeOffer.this, OfferActivity.class);
				startActivity(i);
			}
		});
	}
}
}
