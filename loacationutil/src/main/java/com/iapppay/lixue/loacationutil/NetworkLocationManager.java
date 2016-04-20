package com.iapppay.lixue.loacationutil;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

public class NetworkLocationManager extends BaseLocationManager
{
  private HalopayLocationListener listener;
  private LocationManager mLocationManager;

  public NetworkLocationManager(Context context, HalopayLocationListener listener)
  {
    this.listener = listener;
    this.mLocationManager = ((LocationManager)context.getSystemService("location"));
  }

  public void requestLocationUpdates(long minTime, float minDistance)
  {
    if (this.mLocationManager != null) {
      this.mLocationManager.removeUpdates(this);
      this.mLocationManager.requestLocationUpdates("network", minTime, minDistance, this);
    }
  }

  public void stopListner(HalopayLocationListener l) {
    if ((this.listener != null) && (l == this.listener))
      this.listener = null;
  }

  public void destroy()
  {
    if (this.mLocationManager != null)
      this.mLocationManager.removeUpdates(this);
  }

  public void onLocationChanged(Location location)
  {
    if ((this.listener != null) && (location != null)) {
      HalopayLocation ycLocation = new HalopayLocation(location);
      ycLocation.setDataSource(10003);
      ycLocation.setProvider("network");
      ycLocation.setRadius(location.getAccuracy());
      ycLocation.setCoordinateSystem("world");
      LocationAPI.setLastKnownLocation(ycLocation);
      this.listener.onLocationChanged(ycLocation);
    }
  }

  public void onProviderDisabled(String provider)
  {
    if (this.listener != null)
      this.listener.onProviderDisabled(provider);
  }

  public void onProviderEnabled(String provider)
  {
    if (this.listener != null)
      this.listener.onProviderEnabled(provider);
  }

  public void onStatusChanged(String provider, int status, Bundle extras)
  {
    if (this.listener != null)
      this.listener.onStatusChanged(provider, status, extras);
  }
}
