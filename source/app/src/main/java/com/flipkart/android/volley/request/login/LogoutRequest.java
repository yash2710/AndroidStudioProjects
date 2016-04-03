// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.login;

import com.flipkart.android.response.login.LogoutResponse;
import com.flipkart.android.volley.request.GsonRequest;
import java.util.Date;

// Referenced classes of package com.flipkart.android.volley.request.login:
//            b

public class LogoutRequest extends GsonRequest
{

    public LogoutRequest(com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecureGetUri("2", "user/logout", ""), null, (new b()).getType(), listener, errorlistener);
    }

    public byte[] getBody()
    {
        return (new StringBuilder("logout=")).append((new Date()).getTime()).toString().getBytes();
    }

    public void performJsonUpdate(byte abyte0[], LogoutResponse logoutresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (LogoutResponse)obj, flag);
    }
}
