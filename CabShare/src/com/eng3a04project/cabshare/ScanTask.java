package com.eng3a04project.cabshare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ScanTask extends AsyncTask<Object, Object, Object> {

	public static int ScanSuccess;
	private  String CabID;
	private  String user_name;
	public ScanTask(String cabid,String user_name){
		this.CabID=cabid;
		this.user_name=user_name;
	}
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		addCabID(CabID,user_name);
		return null;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	public void addCabID(String cabid,String user_name){
		// create new HTTPClient and Post Header
		CurrentIp currentip = new CurrentIp();
		currentip.getIp();
		HttpClient httpclient = new DefaultHttpClient();
		Log.d("httpclient","created");
		HttpPost httppost = new HttpPost("http://"+currentip.getIp()+"/android_connect/create_cabid.php");
		Log.d("go to create_cabid", "done");
		try {
			Log.d("go to addcabID try loop", "done");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("cabid", cabid));
			pairs.add(new BasicNameValuePair("user_name", user_name));
			httppost.setEntity(new UrlEncodedFormEntity(pairs));
			Log.d("encoding", "done");
			// what you get back after executing the http request
			HttpResponse response = httpclient.execute(httppost);
			Log.d("httppost", "went through");
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			StringBuilder builder = new StringBuilder();
			for(String line = null; (line = reader.readLine()) != null;) {
				builder.append(line);
				Log.d("inside for loop", "going");
			}
			Log.d("builder return", builder.toString());
			
			try {
				JSONObject mainObject = new JSONObject(builder.toString()); //JSON
				String success = mainObject.getString("success"); //success
				
				ScanSuccess = Integer.parseInt(success);
				
				
				
				
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClientProtocolException e){
			
		} catch (IOException e){
			
		}
		
	}
}
