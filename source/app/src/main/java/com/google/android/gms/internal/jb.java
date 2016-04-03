// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

// Referenced classes of package com.google.android.gms.internal:
//            jg

public class jb
    implements FusedLocationProviderApi
{

    public jb()
    {
    }

    public Location getLastLocation(GoogleApiClient googleapiclient)
    {
        jg jg1 = LocationServices.e(googleapiclient);
        Location location;
        try
        {
            location = jg1.getLastLocation();
        }
        catch (Exception exception)
        {
            return null;
        }
        return location;
    }

    public PendingResult removeLocationUpdates(GoogleApiClient googleapiclient, PendingIntent pendingintent)
    {
        return googleapiclient.b(new _cls5(pendingintent));
    }

    public PendingResult removeLocationUpdates(GoogleApiClient googleapiclient, LocationListener locationlistener)
    {
        return googleapiclient.b(new _cls4(locationlistener));
    }

    public PendingResult requestLocationUpdates(GoogleApiClient googleapiclient, LocationRequest locationrequest, PendingIntent pendingintent)
    {
        return googleapiclient.b(new _cls3(locationrequest, pendingintent));
    }

    public PendingResult requestLocationUpdates(GoogleApiClient googleapiclient, LocationRequest locationrequest, LocationListener locationlistener)
    {
        return googleapiclient.b(new _cls1(locationrequest, locationlistener));
    }

    public PendingResult requestLocationUpdates(GoogleApiClient googleapiclient, LocationRequest locationrequest, LocationListener locationlistener, Looper looper)
    {
        return googleapiclient.b(new _cls2(locationrequest, locationlistener, looper));
    }

    public PendingResult setMockLocation(GoogleApiClient googleapiclient, Location location)
    {
        return googleapiclient.b(new _cls7(location));
    }

    public PendingResult setMockMode(GoogleApiClient googleapiclient, boolean flag)
    {
        return googleapiclient.b(new _cls6(flag));
    }

    private class _cls5 extends a
    {
        private class a extends com.google.android.gms.location.LocationServices.a
        {

            public Result c(Status status)
            {
                return d(status);
            }

            public Status d(Status status)
            {
                return status;
            }

            private a()
            {
            }

            a(_cls1 _pcls1)
            {
                this();
            }
        }


        final PendingIntent Vu;
        final jb Vy;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.removeLocationUpdates(Vu);
            b(Status.En);
        }

        _cls5(PendingIntent pendingintent)
        {
            Vy = jb.this;
            Vu = pendingintent;
            super(null);
        }
    }


    private class _cls4 extends a
    {

        final LocationListener Vx;
        final jb Vy;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.removeLocationUpdates(Vx);
            b(Status.En);
        }

        _cls4(LocationListener locationlistener)
        {
            Vy = jb.this;
            Vx = locationlistener;
            super(null);
        }
    }


    private class _cls3 extends a
    {

        final PendingIntent Vu;
        final LocationRequest Vw;
        final jb Vy;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.requestLocationUpdates(Vw, Vu);
            b(Status.En);
        }

        _cls3(LocationRequest locationrequest, PendingIntent pendingintent)
        {
            Vy = jb.this;
            Vw = locationrequest;
            Vu = pendingintent;
            super(null);
        }
    }


    private class _cls1 extends a
    {

        final LocationRequest Vw;
        final LocationListener Vx;
        final jb Vy;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.requestLocationUpdates(Vw, Vx);
            b(Status.En);
        }

        _cls1(LocationRequest locationrequest, LocationListener locationlistener)
        {
            Vy = jb.this;
            Vw = locationrequest;
            Vx = locationlistener;
            super(null);
        }
    }


    private class _cls2 extends a
    {

        final LocationRequest Vw;
        final LocationListener Vx;
        final jb Vy;
        final Looper Vz;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.requestLocationUpdates(Vw, Vx, Vz);
            b(Status.En);
        }

        _cls2(LocationRequest locationrequest, LocationListener locationlistener, Looper looper)
        {
            Vy = jb.this;
            Vw = locationrequest;
            Vx = locationlistener;
            Vz = looper;
            super(null);
        }
    }


    private class _cls7 extends a
    {

        final Location VB;
        final jb Vy;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.setMockLocation(VB);
            b(Status.En);
        }

        _cls7(Location location)
        {
            Vy = jb.this;
            VB = location;
            super(null);
        }
    }


    private class _cls6 extends a
    {

        final boolean VA;
        final jb Vy;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            jg1.setMockMode(VA);
            b(Status.En);
        }

        _cls6(boolean flag)
        {
            Vy = jb.this;
            VA = flag;
            super(null);
        }
    }

}
