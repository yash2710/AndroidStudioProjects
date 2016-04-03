// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import com.google.android.gms.internal.hm;

// Referenced classes of package com.google.android.gms.maps.model:
//            LatLngBounds, LatLng

public final class aaN
{

    private double aaK;
    private double aaL;
    private double aaM;
    private double aaN;

    private boolean d(double d1)
    {
        if (aaM > aaN) goto _L2; else goto _L1
_L1:
        if (aaM > d1 || d1 > aaN) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (aaM > d1 && d1 > aaN)
        {
            return false;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public final LatLngBounds build()
    {
        boolean flag;
        if (!Double.isNaN(aaM))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        hm.a(flag, "no included points");
        return new LatLngBounds(new LatLng(aaK, aaM), new LatLng(aaL, aaN));
    }

    public final aaN include(LatLng latlng)
    {
        double d1;
        aaK = Math.min(aaK, latlng.latitude);
        aaL = Math.max(aaL, latlng.latitude);
        d1 = latlng.longitude;
        if (!Double.isNaN(aaM)) goto _L2; else goto _L1
_L1:
        aaM = d1;
_L6:
        aaN = d1;
_L4:
        return this;
_L2:
        if (d(d1)) goto _L4; else goto _L3
_L3:
        if (LatLngBounds.d(aaM, d1) < LatLngBounds.e(aaN, d1))
        {
            aaM = d1;
            return this;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public ()
    {
        aaK = (1.0D / 0.0D);
        aaL = (-1.0D / 0.0D);
        aaM = (0.0D / 0.0D);
        aaN = (0.0D / 0.0D);
    }
}
