// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.login.LogoutRequest;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class LogoutVDataHandler extends BaseVDataHandler
{

    public LogoutVDataHandler()
    {
    }

    public void doLogout()
    {
        LogoutRequest logoutrequest = new LogoutRequest(listner, errorListner);
        request = logoutrequest;
        FlipkartApplication.getRequestQueue().cancelAll();
        FlipkartApplication.getRequestQueue().add(logoutrequest);
    }
}
