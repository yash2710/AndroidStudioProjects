// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.volley.request.session.SessionRequest;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler

public abstract class SessionVDataHandler extends BaseVDataHandler
{

    public SessionVDataHandler()
    {
    }

    public void getSessionInfo()
    {
        SessionRequest sessionrequest = new SessionRequest(listner, errorListner);
        request = sessionrequest;
        FlipkartApplication.getRequestQueue().add(sessionrequest);
    }
}
