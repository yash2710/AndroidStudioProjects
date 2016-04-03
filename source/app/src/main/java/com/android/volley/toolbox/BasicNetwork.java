// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.SSLError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

// Referenced classes of package com.android.volley.toolbox:
//            ByteArrayPool, PoolingByteArrayOutputStream, HttpStack

public class BasicNetwork
    implements Network
{

    protected static final boolean DEBUG;
    private static int a = 3000;
    private static int b = 4096;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpstack)
    {
        this(httpstack, new ByteArrayPool(b));
    }

    public BasicNetwork(HttpStack httpstack, ByteArrayPool bytearraypool)
    {
        mHttpStack = httpstack;
        mPool = bytearraypool;
    }

    private static Map a(Header aheader[])
    {
        HashMap hashmap = new HashMap();
        for (int i = 0; i < aheader.length; i++)
        {
            hashmap.put(aheader[i].getName(), aheader[i].getValue());
        }

        return hashmap;
    }

    private static void a(String s, Request request, VolleyError volleyerror)
    {
        RetryPolicy retrypolicy = request.getRetryPolicy();
        int i = request.getTimeoutMs();
        Object aobj1[];
        try
        {
            retrypolicy.retry(volleyerror);
        }
        catch (VolleyError volleyerror1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(i);
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", aobj));
            throw volleyerror1;
        }
        aobj1 = new Object[2];
        aobj1[0] = s;
        aobj1[1] = Integer.valueOf(i);
        request.addMarker(String.format("%s-retry [timeout=%s]", aobj1));
    }

    private byte[] a(HttpEntity httpentity)
    {
        PoolingByteArrayOutputStream poolingbytearrayoutputstream;
        byte abyte0[];
        poolingbytearrayoutputstream = new PoolingByteArrayOutputStream(mPool, (int)httpentity.getContentLength());
        abyte0 = null;
        InputStream inputstream = httpentity.getContent();
        abyte0 = null;
        if (inputstream != null)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        throw new ServerError();
        Exception exception;
        exception;
        int i;
        byte abyte1[];
        IOException ioexception1;
        try
        {
            httpentity.consumeContent();
        }
        catch (IOException ioexception)
        {
            VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
        }
        mPool.returnBuf(abyte0);
        poolingbytearrayoutputstream.close();
        throw exception;
        abyte0 = mPool.getBuf(1024);
_L1:
        i = inputstream.read(abyte0);
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        poolingbytearrayoutputstream.write(abyte0, 0, i);
          goto _L1
        abyte1 = poolingbytearrayoutputstream.toByteArray();
        try
        {
            httpentity.consumeContent();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1)
        {
            VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
        }
        mPool.returnBuf(abyte0);
        poolingbytearrayoutputstream.close();
        return abyte1;
    }

    protected void logError(String s, String s1, long l)
    {
        long l1 = SystemClock.elapsedRealtime();
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = Long.valueOf(l1 - l);
        aobj[2] = s1;
        VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", aobj);
    }

    public NetworkResponse performRequest(Request request)
    {
        long l = SystemClock.elapsedRealtime();
_L7:
        HttpResponse httpresponse;
        byte abyte0[];
        Object obj;
        httpresponse = null;
        abyte0 = null;
        obj = new HashMap();
        HashMap hashmap;
        com.android.volley.Cache.Entry entry;
        hashmap = new HashMap();
        entry = request.getCacheEntry();
        abyte0 = null;
        httpresponse = null;
        if (entry == null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        int j;
        if (entry.etag != null)
        {
            hashmap.put("If-None-Match", entry.etag);
        }
        j = entry.serverDate != 0L;
        abyte0 = null;
        httpresponse = null;
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        hashmap.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.serverDate)));
        StatusLine statusline;
        int k;
        httpresponse = mHttpStack.performRequest(request, hashmap);
        statusline = httpresponse.getStatusLine();
        k = statusline.getStatusCode();
        obj = a(httpresponse.getAllHeaders());
        abyte0 = null;
        if (k != 304)
        {
            break MISSING_BLOCK_LABEL_189;
        }
        return new NetworkResponse(304, request.getCacheEntry().data, ((Map) (obj)), true);
        HttpEntity httpentity = httpresponse.getEntity();
        abyte0 = null;
        if (httpentity == null) goto _L2; else goto _L1
_L1:
        abyte0 = a(httpresponse.getEntity());
_L8:
        long l1 = SystemClock.elapsedRealtime() - l;
        if (!DEBUG && l1 <= (long)a) goto _L4; else goto _L3
_L3:
        Object aobj1[];
        aobj1 = new Object[5];
        aobj1[0] = request;
        aobj1[1] = Long.valueOf(l1);
        if (abyte0 == null)
        {
            break MISSING_BLOCK_LABEL_586;
        }
        Object obj1 = Integer.valueOf(abyte0.length);
_L9:
        aobj1[2] = obj1;
        aobj1[3] = Integer.valueOf(statusline.getStatusCode());
        aobj1[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
        VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", aobj1);
_L4:
        if (k == 200 || k == 204) goto _L6; else goto _L5
_L5:
        NetworkResponse networkresponse1;
        try
        {
            throw new IOException();
        }
        catch (SocketTimeoutException sockettimeoutexception)
        {
            a("socket", request, new TimeoutError());
        }
        catch (ConnectTimeoutException connecttimeoutexception)
        {
            a("connection", request, new TimeoutError());
        }
        catch (MalformedURLException malformedurlexception)
        {
            throw new RuntimeException((new StringBuilder("Bad URL ")).append(request.getUrl()).toString(), malformedurlexception);
        }
        catch (SSLException sslexception)
        {
            throw new SSLError();
        }
        catch (IOException ioexception)
        {
            if (httpresponse != null)
            {
                int i = httpresponse.getStatusLine().getStatusCode();
                Object aobj[] = new Object[2];
                aobj[0] = Integer.valueOf(i);
                aobj[1] = request.getUrl();
                VolleyLog.e("Unexpected response code %d for %s", aobj);
                if (abyte0 != null)
                {
                    NetworkResponse networkresponse = new NetworkResponse(i, abyte0, ((Map) (obj)), false);
                    if (i == 401 || i == 403)
                    {
                        a("auth", request, new AuthFailureError(networkresponse));
                    } else
                    {
                        throw new ServerError(networkresponse);
                    }
                } else
                {
                    throw new NetworkError(null);
                }
            } else
            {
                throw new NoConnectionError(ioexception);
            }
        }
          goto _L7
_L2:
        abyte0 = new byte[0];
          goto _L8
_L6:
        networkresponse1 = new NetworkResponse(k, abyte0, ((Map) (obj)), false);
        return networkresponse1;
        obj1 = "null";
          goto _L9
    }

    static 
    {
        DEBUG = VolleyLog.DEBUG;
    }
}
