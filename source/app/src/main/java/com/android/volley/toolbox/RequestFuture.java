// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFuture
    implements com.android.volley.Response.ErrorListener, com.android.volley.Response.Listener, Future
{

    private Request a;
    private boolean b;
    private Object c;
    private VolleyError d;

    private RequestFuture()
    {
        b = false;
    }

    private Object a(Long long1)
    {
        this;
        JVM INSTR monitorenter ;
        if (d != null)
        {
            throw new ExecutionException(d);
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (!b) goto _L2; else goto _L1
_L1:
        Object obj = c;
_L7:
        this;
        JVM INSTR monitorexit ;
        return obj;
_L2:
        if (long1 != null) goto _L4; else goto _L3
_L3:
        wait(0L);
_L6:
        if (d != null)
        {
            throw new ExecutionException(d);
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (long1.longValue() > 0L)
        {
            wait(long1.longValue());
        }
        if (true) goto _L6; else goto _L5
_L5:
        if (!b)
        {
            throw new TimeoutException();
        }
        obj = c;
          goto _L7
    }

    public static RequestFuture newFuture()
    {
        return new RequestFuture();
    }

    public boolean cancel(boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Request request = a;
        boolean flag1 = false;
        if (request != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return flag1;
_L2:
        boolean flag2 = isDone();
        flag1 = false;
        if (flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        a.cancel();
        flag1 = true;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Object get()
    {
        Object obj;
        try
        {
            obj = a(null);
        }
        catch (TimeoutException timeoutexception)
        {
            throw new AssertionError(timeoutexception);
        }
        return obj;
    }

    public Object get(long l, TimeUnit timeunit)
    {
        return a(Long.valueOf(TimeUnit.MILLISECONDS.convert(l, timeunit)));
    }

    public boolean isCancelled()
    {
        if (a == null)
        {
            return false;
        } else
        {
            return a.isCanceled();
        }
    }

    public boolean isDone()
    {
        this;
        JVM INSTR monitorenter ;
        if (b || d != null) goto _L2; else goto _L1
_L1:
        boolean flag1 = isCancelled();
        if (!flag1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    public void onErrorResponse(VolleyError volleyerror)
    {
        this;
        JVM INSTR monitorenter ;
        d = volleyerror;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void onResponse(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        b = true;
        c = obj;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setRequest(Request request)
    {
        a = request;
    }
}
