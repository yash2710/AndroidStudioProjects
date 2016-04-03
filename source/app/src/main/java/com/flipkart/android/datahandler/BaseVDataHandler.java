// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.wrapper.ResponseWrapper;
import java.util.concurrent.ExecutionException;

// Referenced classes of package com.flipkart.android.datahandler:
//            a, c, d

public abstract class BaseVDataHandler
{

    com.android.volley.Response.ErrorListener errorListner;
    com.android.volley.Response.Listener listner;
    Request request;
    com.android.volley.RequestQueue.RequestFilter requestFilter;

    public BaseVDataHandler()
    {
        errorListner = new a(this);
        listner = new c(this);
    }

    private Object doBlockingCall()
    {
        if (request == null)
        {
            return null;
        }
        RequestFuture requestfuture = RequestFuture.newFuture();
        FlipkartApplication.getRequestQueue().add(request);
        Object obj;
        try
        {
            obj = requestfuture.get();
        }
        catch (InterruptedException interruptedexception)
        {
            return null;
        }
        catch (ExecutionException executionexception)
        {
            return null;
        }
        return obj;
    }

    private void doBlockingCallsInBackGround()
    {
        (new Thread(new d(this))).start();
    }

    private Boolean isRegistrationRequired(ResponseWrapper responsewrapper)
    {
        if (responsewrapper != null && responsewrapper.getStatus() == 401 && responsewrapper.getErrorCode() == 5000)
        {
            FlipkartPreferenceManager.instance().saveRegisterKey("");
            return Boolean.valueOf(true);
        } else
        {
            return Boolean.valueOf(false);
        }
    }

    private Boolean isSessionInvalidated(ResponseWrapper responsewrapper)
    {
        if (responsewrapper != null && (responsewrapper.getStatus() == 401 || responsewrapper.getStatus() == 500) && responsewrapper.getErrorCode() == 3000)
        {
            return Boolean.valueOf(true);
        } else
        {
            return Boolean.valueOf(false);
        }
    }

    public void cancelRequests()
    {
        RequestQueue requestqueue = FlipkartApplication.getRequestQueue();
        if (requestqueue != null && requestFilter != null)
        {
            requestqueue.cancelAll(requestFilter);
        }
    }

    public void errorReceived(int i, int j, String s)
    {
    }

    public abstract void resultReceived(Object obj, boolean flag);




}
