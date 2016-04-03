// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.session;

import com.flipkart.android.response.session.SessionResponse;
import com.flipkart.android.volley.request.GsonRequest;

// Referenced classes of package com.flipkart.android.volley.request.session:
//            a

public class SessionRequest extends GsonRequest
{

    public SessionRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(0, constructSecureGetUri("2", "session", ""), null, (new a()).getType(), listener, errorlistener);
    }

    public void performJsonUpdate(byte abyte0[], SessionResponse sessionresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (SessionResponse)obj, flag);
    }
}
