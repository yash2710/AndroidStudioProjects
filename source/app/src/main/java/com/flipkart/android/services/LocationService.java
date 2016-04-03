// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.datahandler.LocationVDataHandler;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.StringUtils;
import com.google.mygson.JsonObject;

// Referenced classes of package com.flipkart.android.services:
//            a

public class LocationService extends IntentService
    implements LocationListener
{

    private String a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private LocationManager f;
    private String g;
    private Object h;

    public LocationService()
    {
        super("LocationService");
        b = false;
        c = false;
        d = false;
        e = false;
        g = "network";
        h = new Object();
    }

    public void onCreate()
    {
        super.onCreate();
        String s = AppConfigUtils.getInstance().getLocationSource();
        if (StringUtils.isNullOrEmpty(s))
        {
            s = "";
        }
        a = s;
        f = (LocationManager)getSystemService("location");
        try
        {
            d = f.isProviderEnabled("gps");
        }
        catch (SecurityException securityexception) { }
        catch (Exception exception) { }
        e = f.isProviderEnabled("network");
    }

    public void onDestroy()
    {
        synchronized (h)
        {
            c = true;
            h.notify();
        }
        f.removeUpdates(this);
        super.onDestroy();
    }

    protected void onHandleIntent(Intent intent)
    {
        if ((d || e) && !a.equalsIgnoreCase("none"))
        {
            if (e)
            {
                f.requestLocationUpdates("network", 0L, 0.0F, this, Looper.getMainLooper());
            }
            if (a.equalsIgnoreCase("gps") && d)
            {
                try
                {
                    f.requestLocationUpdates("gps", 0L, 0.0F, this, Looper.getMainLooper());
                    g = "gps";
                }
                catch (SecurityException securityexception) { }
            }
            while (!b && !c) 
            {
                synchronized (h)
                {
                    try
                    {
                        h.wait(0x493e0L);
                        c = true;
                    }
                    catch (InterruptedException interruptedexception) { }
                }
            }
        }
    }

    public void onLocationChanged(Location location)
    {
        a a1 = new a(this);
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("deviceId", FlipkartDeviceInfoProvider.getDeviceId());
        jsonobject.addProperty("lat", Double.valueOf(location.getLatitude()));
        jsonobject.addProperty("long", Double.valueOf(location.getLongitude()));
        jsonobject.addProperty("utc_timestamp", Long.valueOf(location.getTime()));
        jsonobject.addProperty("location_source", g);
        jsonobject.addProperty("accuracy", Long.valueOf((long)location.getAccuracy()));
        a1.sendLocation(jsonobject.toString(), "LOCATION");
        JsonObject jsonobject1 = new JsonObject();
        jsonobject1.addProperty("lat", Double.valueOf(location.getLatitude()));
        jsonobject1.addProperty("long", Double.valueOf(location.getLongitude()));
        TrackingHelper.sendGPSLocation(jsonobject1.toString());
        synchronized (h)
        {
            b = true;
            h.notify();
        }
    }

    public void onProviderDisabled(String s)
    {
    }

    public void onProviderEnabled(String s)
    {
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }
}
