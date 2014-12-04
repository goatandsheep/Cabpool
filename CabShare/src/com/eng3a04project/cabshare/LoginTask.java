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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.internal.l;

public class LoginTask extends AsyncTask<Object, Object, Object> {
	private static String user_name, userpassword, first_name, last_name, emailaddress;
	private static int loginSuccess;
	
	public LoginTask(String user_name, String userpassword){
		this.user_name = user_name;
		this.userpassword = userpassword;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		
		
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		try{
		CabshareActivity.executeLogin(loginSuccess);
		CabshareActivity.setUser_name(user_name);
		CabshareActivity.setUserpassword(userpassword);
		CabshareActivity.setFirst_name(first_name);
		CabshareActivity.setLast_name(last_name);
		CabshareActivity.setEmailaddress(emailaddress);
		}catch(Exception e){
			e.printStackTrace();
		}
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Object... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		login(user_name, userpassword);
		
		return null;
	}
	
	
	public void login(String user_name, String userpassword){
		// create new HTTPClient and Post Header
					CurrentIp currentip = new CurrentIp();
					currentip.getIp();
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://"+currentip.getIp()+"/android_connect/read_user.php");
					try {
						Log.d("response", "Part1");
						List<NameValuePair> pairs = new ArrayList<NameValuePair>();
						pairs.add(new BasicNameValuePair("user_name", user_name));
						pairs.add(new BasicNameValuePair("userpassword", userpassword));
						httppost.setEntity(new UrlEncodedFormEntity(pairs));
						Log.d("response", "Part2");
						// what you get back after executing the http request
						HttpResponse response = httpclient.execute(httppost);				
						Log.d("response", "Part3");
						BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
						StringBuilder builder = new StringBuilder();
						Log.d("response", "Part4");
						for(String line = null; (line = reader.readLine()) != null;) {
							builder.append(line);
						}
						Log.d("builder.toString()", builder.toString());
						
						try {
							JSONObject mainObject = new JSONObject(builder.toString()); //JSON
							String success = mainObject.getString("success"); //success
							JSONArray userInfo = mainObject.getJSONArray("user_info"); //user_info
							JSONObject jObject = userInfo.getJSONObject(0); //0
							Log.d("name", jObject.getString("first_name"));
							loginSuccess = Integer.parseInt(success);
							user_name = jObject.getString("user_name");
							userpassword = jObject.getString("userpassword");		
							first_name = jObject.getString("first_name");
							last_name = jObject.getString("last_name");
							emailaddress = jObject.getString("emailaddress");
							
							//play background music
							MediaPlayer mp = MediaPlayer.create(context, R.cabshare.wav);
							mp.start();
							
							
							
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					} catch (ClientProtocolException e){
						
					} catch (IOException e){
						
					}
	}
}
