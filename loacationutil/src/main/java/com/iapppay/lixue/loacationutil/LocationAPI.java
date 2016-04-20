package com.iapppay.lixue.loacationutil;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;

public class LocationAPI {
	private Context context;
	private GpsLocationManager gpsLocationManager;
	private NetworkLocationManager networkLocationManager;
	private Handler handler = new Handler();
	private int UPDATE_INTERVAL = 5000;
	private float minDistance = 1.0F;
	private static HalopayLocation lastLocation;
	private List<String> allProviders = new ArrayList();

	public LocationAPI(Context c) {
		this.context = c;
		LocationManager locationManager = (LocationManager) this.context.getSystemService("location");
		this.allProviders = locationManager.getAllProviders();
	}

	public void getChangeCurLocation(HalopayLocationListener listener) {
		if ((this.allProviders != null) && (this.allProviders.contains("gps"))) {
			this.gpsLocationManager = new GpsLocationManager(this.context,listener);
			this.gpsLocationManager.requestLocationUpdates(this.UPDATE_INTERVAL, this.minDistance);
		}

		if ((this.allProviders != null)&& (this.allProviders.contains("network")))
			try {
				this.networkLocationManager = new NetworkLocationManager(this.context, listener);
				this.networkLocationManager.requestLocationUpdates(this.UPDATE_INTERVAL, this.minDistance);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void setLastKnownLocation(HalopayLocation loc) {
		lastLocation = loc;
	}
	  public static HalopayLocation getLastKnownLocation()
	  {
	    return lastLocation;
	  }

	public GpsLocationManager getGpsLocationManager() {
		return gpsLocationManager;
	}

}
