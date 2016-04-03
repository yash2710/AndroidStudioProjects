// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.login;

import com.flipkart.android.response.login.LoginResponse;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.volley.request.login.params.LoginParam;

// Referenced classes of package com.flipkart.android.volley.request.login:
//            a

public class LoginRequest extends GsonRequest
{

    private LoginParam a;

    public LoginRequest(LoginParam loginparam, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(1, constructSecureGetUri("2", "user", loginparam.generateURI()), null, (new a()).getType(), listener, errorlistener);
        a = null;
        a = loginparam;
    }

    public byte[] getBody()
    {
        return a.generateToByteArray();
    }

    public void performJsonUpdate(byte abyte0[], LoginResponse loginresponse, boolean flag)
    {
    }

    public volatile void performJsonUpdate(byte abyte0[], Object obj, boolean flag)
    {
        performJsonUpdate(abyte0, (LoginResponse)obj, flag);
    }
}
