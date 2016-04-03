// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.UGC;

import com.flipkart.android.response.ugc.UGCResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.UGC.params.UGCParam;

// Referenced classes of package com.flipkart.android.volley.request.UGC:
//            a, b

public class UGCRequest extends GsonRequest
{

    private final UGCParam a;

    public UGCRequest(UGCParam ugcparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructGetUri("2", "ugc/getProductUGCSummary", ugcparam.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = ugcparam;
    }

    static UGCParam a(UGCRequest ugcrequest)
    {
        return ugcrequest.a;
    }

    public void performJsonUpdate(byte abyte0[], UGCResponse ugcresponse, boolean flag)
    {
        if (ugcresponse == null)
        {
            return;
        } else
        {
            (new b(this, flag, abyte0)).start();
            return;
        }
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (UGCResponse)obj, flag);
    }
}
