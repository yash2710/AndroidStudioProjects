// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public abstract class JsonRequest extends Request
{

    private static final String a = String.format("application/json; charset=%s", new Object[] {
        "utf-8"
    });
    private final com.android.volley.Response.Listener b;
    private final String c;

    public JsonRequest(int i, String s, String s1, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        super(i, s, errorlistener);
        b = listener;
        c = s1;
    }

    public JsonRequest(String s, String s1, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        this(-1, s, s1, listener, errorlistener);
    }

    protected void deliverResponse(Object obj)
    {
        b.onResponse(obj);
    }

    public byte[] getBody()
    {
        if (c == null)
        {
            return null;
        }
        byte abyte0[];
        try
        {
            abyte0 = c.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            Object aobj[] = new Object[2];
            aobj[0] = c;
            aobj[1] = "utf-8";
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", aobj);
            return null;
        }
        return abyte0;
    }

    public String getBodyContentType()
    {
        return a;
    }

    public byte[] getPostBody()
    {
        return getBody();
    }

    public String getPostBodyContentType()
    {
        return getBodyContentType();
    }

    protected abstract Response parseNetworkResponse(NetworkResponse networkresponse);

}
