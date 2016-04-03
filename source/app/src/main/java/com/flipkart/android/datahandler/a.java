// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.SSLError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.wrapper.ResponseWrapper;
import com.flipkart.android.utils.volley.ResponseUtils;
import com.google.mygson.Gson;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, b

final class a
    implements com.android.volley.Response.ErrorListener
{

    private BaseVDataHandler a;

    a(BaseVDataHandler basevdatahandler)
    {
        a = basevdatahandler;
        super();
    }

    public final void onErrorResponse(VolleyError volleyerror)
    {
        if (volleyerror.networkResponse != null && (volleyerror instanceof TimeoutError))
        {
            a.errorReceived(504, -1, "Request Timedout!!");
        }
        if (volleyerror instanceof SSLError)
        {
            a.errorReceived(999, -1, "Certificate validation Error");
            return;
        }
        if (volleyerror instanceof NoConnectionError)
        {
            a.errorReceived(-1, -1, "Oops, something when wrong!");
            return;
        }
        if (volleyerror.networkResponse == null) goto _L2; else goto _L1
_L1:
        java.io.Reader reader;
        if (volleyerror.networkResponse.statusCode == 204)
        {
            a.errorReceived(204, -1, "");
            return;
        }
        reader = ResponseUtils.getJsonReader(volleyerror.networkResponse);
        ResponseWrapper responsewrapper = (ResponseWrapper)FlipkartApplication.getGsonInstance().fromJson(reader, (new b(this)).getType());
        if (!BaseVDataHandler.access$000(a, responsewrapper).booleanValue()) goto _L4; else goto _L3
_L3:
        FlipkartPreferenceManager.instance().clearUserSessionVariables();
        a.errorReceived(500, 3000, "Session Error");
_L6:
        if (responsewrapper != null)
        {
            a.errorReceived(volleyerror.networkResponse.statusCode, responsewrapper.getErrorCode(), responsewrapper.getErrorMessage());
            return;
        } else
        {
            a.errorReceived(999, -1, "Oops, something when wrong!");
            return;
        }
_L4:
        try
        {
            if (BaseVDataHandler.access$100(a, responsewrapper).booleanValue() || volleyerror.networkResponse.statusCode == 401)
            {
                BaseVDataHandler.access$200(a);
            }
        }
        catch (Exception exception1) { }
        continue; /* Loop/switch isn't completed */
_L2:
        a.errorReceived(999, -1, "Oops, something when wrong!");
        return;
        Exception exception;
        exception;
        responsewrapper = null;
        if (true) goto _L6; else goto _L5
_L5:
    }
}
