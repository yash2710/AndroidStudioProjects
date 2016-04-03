// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.httpclient.ResponseHandlerImpl;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionState, TransactionStateUtil, HttpsURLConnectionExtension, HttpURLConnectionExtension

public final class HttpInstrumentation
{

    private HttpInstrumentation()
    {
    }

    private static void a(TransactionState transactionstate, Exception exception)
    {
        if (!transactionstate.isComplete())
        {
            TransactionStateUtil.setErrorCodeFromException(transactionstate, exception);
            TransactionData transactiondata = transactionstate.end();
            TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        }
    }

    public static Object execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest, ResponseHandler responsehandler)
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(httphost, TransactionStateUtil.inspectAndInstrument(transactionstate, httphost, httprequest), ResponseHandlerImpl.wrap(responsehandler, transactionstate));
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            a(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static Object execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest, ResponseHandler responsehandler, HttpContext httpcontext)
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(httphost, TransactionStateUtil.inspectAndInstrument(transactionstate, httphost, httprequest), ResponseHandlerImpl.wrap(responsehandler, transactionstate), httpcontext);
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            a(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static Object execute(HttpClient httpclient, HttpUriRequest httpurirequest, ResponseHandler responsehandler)
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(TransactionStateUtil.inspectAndInstrument(transactionstate, httpurirequest), ResponseHandlerImpl.wrap(responsehandler, transactionstate));
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            a(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static Object execute(HttpClient httpclient, HttpUriRequest httpurirequest, ResponseHandler responsehandler, HttpContext httpcontext)
    {
        TransactionState transactionstate = new TransactionState();
        Object obj;
        try
        {
            obj = httpclient.execute(TransactionStateUtil.inspectAndInstrument(transactionstate, httpurirequest), ResponseHandlerImpl.wrap(responsehandler, transactionstate), httpcontext);
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            a(transactionstate, clientprotocolexception);
            throw clientprotocolexception;
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return obj;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest)
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = TransactionStateUtil.inspectAndInstrument(transactionstate, httpclient.execute(httphost, TransactionStateUtil.inspectAndInstrument(transactionstate, httphost, httprequest)));
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpHost httphost, HttpRequest httprequest, HttpContext httpcontext)
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = TransactionStateUtil.inspectAndInstrument(transactionstate, httpclient.execute(httphost, TransactionStateUtil.inspectAndInstrument(transactionstate, httphost, httprequest), httpcontext));
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpUriRequest httpurirequest)
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = TransactionStateUtil.inspectAndInstrument(transactionstate, httpclient.execute(TransactionStateUtil.inspectAndInstrument(transactionstate, httpurirequest)));
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static HttpResponse execute(HttpClient httpclient, HttpUriRequest httpurirequest, HttpContext httpcontext)
    {
        TransactionState transactionstate = new TransactionState();
        HttpResponse httpresponse;
        try
        {
            httpresponse = TransactionStateUtil.inspectAndInstrument(transactionstate, httpclient.execute(TransactionStateUtil.inspectAndInstrument(transactionstate, httpurirequest), httpcontext));
        }
        catch (IOException ioexception)
        {
            a(transactionstate, ioexception);
            throw ioexception;
        }
        return httpresponse;
    }

    public static URLConnection openConnection(URLConnection urlconnection)
    {
        if (urlconnection instanceof HttpsURLConnection)
        {
            urlconnection = new HttpsURLConnectionExtension((HttpsURLConnection)urlconnection);
        } else
        if (urlconnection instanceof HttpURLConnection)
        {
            return new HttpURLConnectionExtension((HttpURLConnection)urlconnection);
        }
        return urlconnection;
    }

    public static URLConnection openConnectionWithProxy(URLConnection urlconnection)
    {
        if (urlconnection instanceof HttpsURLConnection)
        {
            urlconnection = new HttpsURLConnectionExtension((HttpsURLConnection)urlconnection);
        } else
        if (urlconnection instanceof HttpURLConnection)
        {
            return new HttpURLConnectionExtension((HttpURLConnection)urlconnection);
        }
        return urlconnection;
    }
}
