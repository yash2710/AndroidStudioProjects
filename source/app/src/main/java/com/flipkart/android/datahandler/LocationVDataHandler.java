// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.location.params.LocationParams;
import com.flipkart.android.volley.request.locations.LocationRequest;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class LocationVDataHandler extends BaseVDataHandler
{

    public LocationVDataHandler()
    {
    }

    public void sendLocation(String s, String s1)
    {
        LocationRequest locationrequest = new LocationRequest(new LocationParams(s, s1), listner, errorListner);
        FlipkartApplication.getRequestQueue().add(locationrequest);
    }
}
