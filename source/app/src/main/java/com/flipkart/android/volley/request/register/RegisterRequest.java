// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.register;

import com.flipkart.android.response.register.RegisterResponse;
import com.flipkart.android.volley.request.GsonRequest;

// Referenced classes of package com.flipkart.android.volley.request.register:
//            a

public class RegisterRequest extends GsonRequest
{

    private String a;

    public RegisterRequest(String s, String s1, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecureGetUri("3", "register/app", ""), null, "application/json", (new a()).getType(), listener, errorlistener, s);
        a = s1;
    }

    public byte[] getBody()
    {
        return a.getBytes();
    }

    public void performJsonUpdate(byte abyte0[], RegisterResponse registerresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (RegisterResponse)obj, flag);
    }
}
