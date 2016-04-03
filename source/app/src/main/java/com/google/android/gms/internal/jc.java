// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            jh, hm

public class jc
    implements GeofencingApi
{

    public jc()
    {
    }

    public PendingResult addGeofences(GoogleApiClient googleapiclient, List list, PendingIntent pendingintent)
    {
        ArrayList arraylist1;
        if (list != null)
        {
            ArrayList arraylist = new ArrayList(list.size());
            Geofence geofence;
            for (Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add((jh)geofence))
            {
                geofence = (Geofence)iterator.next();
                hm.b(geofence instanceof jh, "Geofence must be created using Geofence.Builder.");
            }

            arraylist1 = arraylist;
        } else
        {
            arraylist1 = null;
        }
        return googleapiclient.b(new _cls1(arraylist1, pendingintent));
    }

    public PendingResult removeGeofences(GoogleApiClient googleapiclient, PendingIntent pendingintent)
    {
        return googleapiclient.b(new _cls2(pendingintent));
    }

    public PendingResult removeGeofences(GoogleApiClient googleapiclient, List list)
    {
        return googleapiclient.b(new _cls3(list));
    }

    private class _cls1 extends a
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


        final List VC;
        final PendingIntent VD;
        final jc VE;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            class _cls1
                implements com.google.android.gms.location.LocationClient.OnAddGeofencesResultListener
            {

                final _cls1 VF;

                public void onAddGeofencesResult(int i, String as[])
                {
                    VF.b(LocationStatusCodes.cK(i));
                }

                _cls1()
                {
                    VF = _cls1.this;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            jg1.addGeofences(VC, VD, _lcls1);
        }

        _cls1(List list, PendingIntent pendingintent)
        {
            VE = jc.this;
            VC = list;
            VD = pendingintent;
            super(null);
        }
    }


    private class _cls2 extends a
    {

        final PendingIntent VD;
        final jc VE;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            class _cls1
                implements com.google.android.gms.location.LocationClient.OnRemoveGeofencesResultListener
            {

                final _cls2 VG;

                public void onRemoveGeofencesByPendingIntentResult(int i, PendingIntent pendingintent)
                {
                    VG.b(LocationStatusCodes.cK(i));
                }

                public void onRemoveGeofencesByRequestIdsResult(int i, String as[])
                {
                    Log.wtf("GeofencingImpl", "Request ID callback shouldn't have been called");
                }

                _cls1()
                {
                    VG = _cls2.this;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            jg1.removeGeofences(VD, _lcls1);
        }

        _cls2(PendingIntent pendingintent)
        {
            VE = jc.this;
            VD = pendingintent;
            super(null);
        }
    }


    private class _cls3 extends a
    {

        final jc VE;
        final List VH;

        protected volatile void a(com.google.android.gms.common.api.Api.a a1)
        {
            a((jg)a1);
        }

        protected void a(jg jg1)
        {
            class _cls1
                implements com.google.android.gms.location.LocationClient.OnRemoveGeofencesResultListener
            {

                final _cls3 VI;

                public void onRemoveGeofencesByPendingIntentResult(int i, PendingIntent pendingintent)
                {
                    Log.wtf("GeofencingImpl", "PendingIntent callback shouldn't have been called");
                }

                public void onRemoveGeofencesByRequestIdsResult(int i, String as[])
                {
                    VI.b(LocationStatusCodes.cK(i));
                }

                _cls1()
                {
                    VI = _cls3.this;
                    super();
                }
            }

            _cls1 _lcls1 = new _cls1();
            jg1.removeGeofences(VH, _lcls1);
        }

        _cls3(List list)
        {
            VE = jc.this;
            VH = list;
            super(null);
        }
    }

}
