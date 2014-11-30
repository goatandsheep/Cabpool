package com.eng3a04project.cabshare;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



import android.location.LocationListener;

import android.view.Menu;
import android.widget.TextView;
 

import com.tyczj.mapnavigator.Navigator;
 
public class RequestActivity extends FragmentActivity implements LocationListener,GoogleMap.OnMarkerDragListener {
 
    GoogleMap googleMap;
    Navigator navigator;
    static final LatLng mySetPoint = new LatLng(43, -80);
	//make the marker using the coordinates of mySetPoint
   private LatLng myCurrentMarkerCord;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestcab);
        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
       
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        }else { // Google Play Services are available
 
            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
           
            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();
 
            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);
            
            // Enabling the MarkerDragListener
            googleMap.setOnMarkerDragListener(this);
            
 
            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
 
            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
 
            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);
 
        
          	Marker setMarker = googleMap.addMarker(new MarkerOptions()
            .position(mySetPoint)
            .draggable(true));

        	setMarker.setTitle("This is a test point");
        	
        	this.onMarkerDragEnd(setMarker);
        	


            if(location!=null){
            	//call function onLocationChanged();
                this.onLocationChanged(location);
                
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }
    }
    

   


    @Override
    public void onLocationChanged(Location location) {
 
        TextView tvrequestLocation = (TextView) findViewById(R.id.tvrequest_location);
 
        // Getting latitude of the current location
        double latitude = location.getLatitude();
 
        // Getting longitude of the current location
        double longitude = location.getLongitude();
 
        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
 
        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
 
        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
 
        // Setting latitude and longitude in the TextView tv_location
        tvrequestLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );
 	   
        
        navigator = new Navigator(googleMap,latLng,myCurrentMarkerCord);
        navigator.findDirections(true);
    }
 
    public void rePaintMarker(){
      	Marker setMarker = googleMap.addMarker(new MarkerOptions()
        .position(myCurrentMarkerCord)
        .draggable(true));

    	setMarker.setTitle("This is a test point");
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cabshare, menu);
        return true;
    }



	@Override
	public void onMarkerDrag(Marker marker) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onMarkerDragEnd(Marker marker) {

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

		LatLng startPoint = new LatLng(location.getLatitude(),location.getLongitude());
		LatLng endPoint = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
        navigator = new Navigator(googleMap,startPoint,endPoint);
        navigator.findDirections(true);
        myCurrentMarkerCord = endPoint;
        googleMap.clear();
        this.rePaintMarker();
	}



	@Override
	public void onMarkerDragStart(Marker marker) {
		// TODO Auto-generated method stub
		
		
	}
}