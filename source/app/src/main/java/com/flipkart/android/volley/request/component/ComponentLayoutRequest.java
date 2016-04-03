// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.component;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.component.params.ComponentParam;
import java.util.Map;

// Referenced classes of package com.flipkart.android.volley.request.component:
//            c, d

public class ComponentLayoutRequest extends GsonRequest
{

    private final ComponentParam a;
    private String b;

    public ComponentLayoutRequest(ComponentParam componentparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, String s, AnalyticData analyticdata)
    {
        super(0, constructSecureGetUri("3", "layout/page", componentparam.generateURI()), null, (new c()).getType(), listener, errorlistener, analyticdata);
        b = null;
        a = componentparam;
        b = s;
    }

    public com.android.volley.Request.Priority getPriority()
    {
        if (a.isLowPriority())
        {
            return com.android.volley.Request.Priority.LOW;
        } else
        {
            return super.getPriority();
        }
    }

    public String getRequestId()
    {
        return b;
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (Map)obj, flag);
    }

    public void performJsonUpdate(byte abyte0[], Map map, boolean flag)
    {
        (new Thread(new d(this, map))).start();
    }

    public void setRequestId(String s)
    {
        b = s;
    }
}
