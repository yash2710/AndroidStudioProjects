// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.Request;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package com.android.volley.toolbox:
//            HttpStack

public class HttpClientStack
    implements HttpStack
{

    protected final HttpClient mClient;

    public HttpClientStack(HttpClient httpclient)
    {
        mClient = httpclient;
    }

    private static void a(HttpEntityEnclosingRequestBase httpentityenclosingrequestbase, Request request)
    {
        byte abyte0[] = request.getBody();
        if (abyte0 != null)
        {
            httpentityenclosingrequestbase.setEntity(new ByteArrayEntity(abyte0));
        }
    }

    private static void a(HttpUriRequest httpurirequest, Map map)
    {
        String s;
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); httpurirequest.setHeader(s, (String)map.get(s)))
        {
            s = (String)iterator.next();
        }

    }

    protected void onPrepareRequest(HttpUriRequest httpurirequest)
    {
    }

    public HttpResponse performRequest(Request request, Map map)
    {
        request.getMethod();
        JVM INSTR tableswitch -1 3: default 40
    //                   -1 50
    //                   0 194
    //                   1 226
    //                   2 263
    //                   3 210;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L5:
        break MISSING_BLOCK_LABEL_263;
_L1:
        throw new IllegalStateException("Unknown request method.");
_L2:
        Object obj;
        HttpClient httpclient;
        byte abyte0[] = request.getPostBody();
        org.apache.http.params.HttpParams httpparams;
        int i;
        if (abyte0 != null)
        {
            HttpPost httppost1 = new HttpPost(request.getUrl());
            httppost1.addHeader("Content-Type", request.getPostBodyContentType());
            httppost1.setEntity(new ByteArrayEntity(abyte0));
            obj = httppost1;
        } else
        {
            obj = new HttpGet(request.getUrl());
        }
_L7:
        a(((HttpUriRequest) (obj)), map);
        a(((HttpUriRequest) (obj)), request.getHeaders());
        onPrepareRequest(((HttpUriRequest) (obj)));
        httpparams = ((HttpUriRequest) (obj)).getParams();
        i = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(httpparams, 5000);
        HttpConnectionParams.setSoTimeout(httpparams, i);
        httpclient = mClient;
        HttpPut httpput;
        HttpPost httppost;
        if (!(httpclient instanceof HttpClient))
        {
            return httpclient.execute(((HttpUriRequest) (obj)));
        } else
        {
            return HttpInstrumentation.execute((HttpClient)httpclient, ((HttpUriRequest) (obj)));
        }
_L3:
        obj = new HttpGet(request.getUrl());
          goto _L7
_L6:
        obj = new HttpDelete(request.getUrl());
          goto _L7
_L4:
        httppost = new HttpPost(request.getUrl());
        httppost.addHeader("Content-Type", request.getBodyContentType());
        a(httppost, request);
        obj = httppost;
          goto _L7
        httpput = new HttpPut(request.getUrl());
        httpput.addHeader("Content-Type", request.getBodyContentType());
        a(httpput, request);
        obj = httpput;
          goto _L7
    }
}
