// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.locations;

import com.flipkart.android.response.location.LocationResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.location.params.LocationParams;

// Referenced classes of package com.flipkart.android.volley.request.locations:
//            a

public class LocationRequest extends GsonRequest
{

    private LocationParams a;

    public LocationRequest(LocationParams locationparams, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructGetUri("2", "data/collector/location", locationparams.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = null;
        a = locationparams;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], LocationResponse locationresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (LocationResponse)obj, flag);
    }
}
