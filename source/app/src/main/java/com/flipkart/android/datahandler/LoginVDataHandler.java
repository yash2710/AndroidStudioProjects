// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.login.LoginRequest;
import com.flipkart.android.volley.request.login.params.LoginParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class LoginVDataHandler extends BaseVDataHandler
{

    public LoginVDataHandler()
    {
    }

    public void doLogin(String s, String s1)
    {
        LoginRequest loginrequest = new LoginRequest(new LoginParam(s, s1), listner, errorListner);
        request = loginrequest;
        FlipkartApplication.getRequestQueue().cancelAll();
        FlipkartApplication.getRequestQueue().add(loginrequest);
    }

    public void doLoginFacebook(String s)
    {
        LoginRequest loginrequest = new LoginRequest(new LoginParam(com.flipkart.android.volley.request.login.params.LoginParam.LoginType.FACEBOOK, s), listner, errorListner);
        request = loginrequest;
        FlipkartApplication.getRequestQueue().cancelAll();
        FlipkartApplication.getRequestQueue().add(loginrequest);
    }

    public void doLoginGoogle(String s)
    {
        LoginRequest loginrequest = new LoginRequest(new LoginParam(com.flipkart.android.volley.request.login.params.LoginParam.LoginType.GOOGLE, s), listner, errorListner);
        request = loginrequest;
        FlipkartApplication.getRequestQueue().cancelAll();
        FlipkartApplication.getRequestQueue().add(loginrequest);
    }
}
