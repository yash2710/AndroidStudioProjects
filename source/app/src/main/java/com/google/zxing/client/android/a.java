// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import com.google.zxing.client.android.camera.CameraManager;
import com.google.zxing.client.android.camera.FrontLightMode;

final class a
    implements SensorEventListener
{

    private final Context a;
    private CameraManager b;
    private Sensor c;

    a(Context context)
    {
        a = context;
    }

    final void a()
    {
        if (c != null)
        {
            ((SensorManager)a.getSystemService("sensor")).unregisterListener(this);
            b = null;
            c = null;
        }
    }

    final void a(CameraManager cameramanager)
    {
        b = cameramanager;
        if (FrontLightMode.readPref(PreferenceManager.getDefaultSharedPreferences(a)) == FrontLightMode.AUTO)
        {
            SensorManager sensormanager = (SensorManager)a.getSystemService("sensor");
            c = sensormanager.getDefaultSensor(5);
            if (c != null)
            {
                sensormanager.registerListener(this, c, 3);
            }
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i)
    {
    }

    public final void onSensorChanged(SensorEvent sensorevent)
    {
        float f = sensorevent.values[0];
        if (b != null)
        {
            if (f <= 45F)
            {
                b.setTorch(true);
            } else
            if (f >= 450F)
            {
                b.setTorch(false);
                return;
            }
        }
    }
}
