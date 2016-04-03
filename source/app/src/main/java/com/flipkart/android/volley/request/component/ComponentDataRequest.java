// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.component;

import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.component.params.ComponentDataParam;
import java.util.Map;

// Referenced classes of package com.flipkart.android.volley.request.component:
//            a, b

public class ComponentDataRequest extends GsonRequest
{

    private ComponentDataParam a;
    private String b;

    public ComponentDataRequest(ComponentDataParam componentdataparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, String s, AnalyticData analyticdata)
    {
        super(1, constructSecureGetUri("3", "data", componentdataparam.generateURI()), null, "application/json", (new a()).getType(), listener, errorlistener, analyticdata);
        a = null;
        b = null;
        a = componentdataparam;
        b = s;
    }

    static ComponentDataParam a(ComponentDataRequest componentdatarequest)
    {
        return componentdatarequest.a;
    }

    public byte[] getBody()
    {
        return a.getPostData();
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
        if (map == null)
        {
            return;
        } else
        {
            (new Thread(new b(this, map))).start();
            return;
        }
    }

    public void setRequestId(String s)
    {
        b = s;
    }
}
