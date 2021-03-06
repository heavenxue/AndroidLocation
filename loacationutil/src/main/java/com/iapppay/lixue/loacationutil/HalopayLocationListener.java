package com.iapppay.lixue.loacationutil;

import android.os.Bundle;

public abstract interface HalopayLocationListener {
    public abstract void onLocationChanged(HalopayLocation paramYongcheLocation);

    public abstract void onProviderDisabled(String paramString);

    public abstract void onProviderEnabled(String paramString);

    public abstract void onStatusChanged(String paramString, int paramInt, Bundle paramBundle);

    public abstract void onLocationFail(String paramString);
}
