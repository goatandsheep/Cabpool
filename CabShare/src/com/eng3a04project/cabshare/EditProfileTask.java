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

public class EditProfileTask extends AsyncTask<Object, Object, Object>{
	private static int EditSuccess;
	private String user_name;
	private String userpassword;
	private String first_name;
	private String last_name;
	private String emailaddress;
	public EditProfileTask(String username, String password, String firstname, String lastname,
			String emailaddress){
		this.user_name = username;
		this.userpassword = password;
		this.first_name = firstname;
		this.last_name = lastname;
		this.emailaddress = emailaddress;
}
	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		edit(user_name,userpassword,first_name,last_name,emailaddress);
		return null;
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
			EditProfile.setEdit(EditSuccess);
			}catch(Exception e){
				e.printStackTrace();
			}
		super.onPostExecute(result);
	}
	
	public void edit(String username, String password, String firstname, String lastname,
			String emailaddress){
		CurrentIp currentip = new CurrentIp();
		currentip.getIp();
		// create new HTTPClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://"+currentip.getIp()+"/android_connect/update_user.php");
		
		try {
			Log.d("response", "Part1");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("user_name", username));
			pairs.add(new BasicNameValuePair("userpassword", password));
			pairs.add(new BasicNameValuePair("first_name", firstname));
			pairs.add(new BasicNameValuePair("last_name", lastname));
			pairs.add(new BasicNameValuePair("emailaddress", emailaddress));
			httppost.setEntity(new UrlEncodedFormEntity(pairs));
			Log.d("response", "Part2");
			// what you get back after executing the http request
			HttpResponse response = httpclient.execute(httppost);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			StringBuilder builder = new StringBuilder();
			for(String line = null; (line = reader.readLine()) != null;) {
				builder.append(line);
			}
			Log.d("the response from server", builder.toString());
			
			try {
				JSONObject mainObject = new JSONObject(builder.toString()); //JSON
				String success = mainObject.getString("success"); //success
				EditSuccess = Integer.parseInt(success);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClientProtocolException e){
			
		} catch (IOException e){
			
		}
}


}
