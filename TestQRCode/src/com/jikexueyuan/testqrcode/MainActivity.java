package com.jikexueyuan.testqrcode;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	private Button scanButton;
	private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanButton = (Button)findViewById(R.id.scan);
        text = (TextView) findViewById(R.id.text);
        scanButton.setOnClickListener(new View.OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "You can scan the QR code", Toast.LENGTH_SHORT).show();
				Intent startScan = new Intent(MainActivity.this,CaptureActivity.class);
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
	}
}
}
