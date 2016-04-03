// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.Map;
import java.util.TreeMap;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            TransactionStateUtil, TransactionState, a, b

public class HttpURLConnectionExtension extends HttpURLConnection
{

    private static final AgentLog c = AgentLogManager.getAgentLog();
    private HttpURLConnection a;
    private TransactionState b;

    public HttpURLConnectionExtension(HttpURLConnection httpurlconnection)
    {
        super(httpurlconnection.getURL());
        a = httpurlconnection;
        TransactionStateUtil.setCrossProcessHeader(httpurlconnection);
    }

    static HttpURLConnection a(HttpURLConnectionExtension httpurlconnectionextension)
    {
        return httpurlconnectionextension.a;
    }

    private void a()
    {
        if (!b().isComplete())
        {
            TransactionStateUtil.inspectAndInstrumentResponse(b(), a);
        }
    }

    static void a(HttpURLConnectionExtension httpurlconnectionextension, TransactionState transactionstate)
    {
        httpurlconnectionextension.a(transactionstate);
    }

    static void a(HttpURLConnectionExtension httpurlconnectionextension, Exception exception)
    {
        httpurlconnectionextension.a(exception);
    }

    private void a(TransactionState transactionstate)
    {
        TransactionData transactiondata = transactionstate.end();
        TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        if ((long)transactionstate.getStatusCode() >= 400L)
        {
            StringBuilder stringbuilder = new StringBuilder();
            TreeMap treemap;
            String s;
            try
            {
                InputStream inputstream = getErrorStream();
                if (inputstream instanceof CountingInputStream)
                {
                    stringbuilder.append(((CountingInputStream)inputstream).getBufferAsString());
                }
            }
            catch (Exception exception)
            {
                c.error(exception.toString());
            }
            treemap = new TreeMap();
            s = a.getContentType();
            if (s != null && !"".equals(s))
            {
                treemap.put("content_type", s);
            }
            treemap.put("content_length", (new StringBuilder()).append(transactionstate.getBytesReceived()).toString());
            Measurements.addHttpError(transactiondata.getUrl(), transactiondata.getStatusCode(), stringbuilder.toString(), treemap);
        }
    }

    private void a(Exception exception)
    {
        TransactionState transactionstate = b();
        TransactionStateUtil.setErrorCodeFromException(transactionstate, exception);
        if (!transactionstate.isComplete())
        {
            TransactionStateUtil.inspectAndInstrumentResponse(transactionstate, a);
            TransactionData transactiondata = transactionstate.end();
            TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        }
    }

    private TransactionState b()
    {
        if (b == null)
        {
            b = new TransactionState();
            TransactionStateUtil.inspectAndInstrument(b, a);
        }
        return b;
    }

    public void addRequestProperty(String s, String s1)
    {
        a.addRequestProperty(s, s1);
    }

    public void connect()
    {
        b();
        try
        {
            a.connect();
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public void disconnect()
    {
        if (b != null && !b.isComplete())
        {
            a(b);
        }
        a.disconnect();
    }

    public boolean getAllowUserInteraction()
    {
        return a.getAllowUserInteraction();
    }

    public int getConnectTimeout()
    {
        return a.getConnectTimeout();
    }

    public Object getContent()
    {
        b();
        Object obj;
        int i;
        try
        {
            obj = a.getContent();
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        i = a.getContentLength();
        if (i >= 0)
        {
            TransactionState transactionstate = b();
            if (!transactionstate.isComplete())
            {
                transactionstate.setBytesReceived(i);
                a(transactionstate);
            }
        }
        return obj;
    }

    public Object getContent(Class aclass[])
    {
        b();
        Object obj;
        try
        {
            obj = a.getContent(aclass);
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        a();
        return obj;
    }

    public String getContentEncoding()
    {
        b();
        String s = a.getContentEncoding();
        a();
        return s;
    }

    public int getContentLength()
    {
        b();
        int i = a.getContentLength();
        a();
        return i;
    }

    public String getContentType()
    {
        b();
        String s = a.getContentType();
        a();
        return s;
    }

    public long getDate()
    {
        b();
        long l = a.getDate();
        a();
        return l;
    }

    public boolean getDefaultUseCaches()
    {
        return a.getDefaultUseCaches();
    }

    public boolean getDoInput()
    {
        return a.getDoInput();
    }

    public boolean getDoOutput()
    {
        return a.getDoOutput();
    }

    public InputStream getErrorStream()
    {
        b();
        CountingInputStream countinginputstream;
        try
        {
            countinginputstream = new CountingInputStream(a.getErrorStream(), true);
        }
        catch (Exception exception)
        {
            c.error(exception.toString());
            return a.getErrorStream();
        }
        return countinginputstream;
    }

    public long getExpiration()
    {
        b();
        long l = a.getExpiration();
        a();
        return l;
    }

    public String getHeaderField(int i)
    {
        b();
        String s = a.getHeaderField(i);
        a();
        return s;
    }

    public String getHeaderField(String s)
    {
        b();
        String s1 = a.getHeaderField(s);
        a();
        return s1;
    }

    public long getHeaderFieldDate(String s, long l)
    {
        b();
        long l1 = a.getHeaderFieldDate(s, l);
        a();
        return l1;
    }

    public int getHeaderFieldInt(String s, int i)
    {
        b();
        int j = a.getHeaderFieldInt(s, i);
        a();
        return j;
    }

    public String getHeaderFieldKey(int i)
    {
        b();
        String s = a.getHeaderFieldKey(i);
        a();
        return s;
    }

    public Map getHeaderFields()
    {
        b();
        Map map = a.getHeaderFields();
        a();
        return map;
    }

    public long getIfModifiedSince()
    {
        b();
        long l = a.getIfModifiedSince();
        a();
        return l;
    }

    public InputStream getInputStream()
    {
        TransactionState transactionstate = b();
        CountingInputStream countinginputstream;
        try
        {
            countinginputstream = new CountingInputStream(a.getInputStream());
            TransactionStateUtil.inspectAndInstrumentResponse(transactionstate, a);
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        countinginputstream.addStreamCompleteListener(new a(this, transactionstate));
        return countinginputstream;
    }

    public boolean getInstanceFollowRedirects()
    {
        return a.getInstanceFollowRedirects();
    }

    public long getLastModified()
    {
        b();
        long l = a.getLastModified();
        a();
        return l;
    }

    public OutputStream getOutputStream()
    {
        TransactionState transactionstate = b();
        CountingOutputStream countingoutputstream;
        try
        {
            countingoutputstream = new CountingOutputStream(a.getOutputStream());
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        countingoutputstream.addStreamCompleteListener(new b(this, transactionstate));
        return countingoutputstream;
    }

    public Permission getPermission()
    {
        return a.getPermission();
    }

    public int getReadTimeout()
    {
        return a.getReadTimeout();
    }

    public String getRequestMethod()
    {
        return a.getRequestMethod();
    }

    public Map getRequestProperties()
    {
        return a.getRequestProperties();
    }

    public String getRequestProperty(String s)
    {
        return a.getRequestProperty(s);
    }

    public int getResponseCode()
    {
        b();
        int i;
        try
        {
            i = a.getResponseCode();
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        a();
        return i;
    }

    public String getResponseMessage()
    {
        b();
        String s;
        try
        {
            s = a.getResponseMessage();
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        a();
        return s;
    }

    public URL getURL()
    {
        return a.getURL();
    }

    public boolean getUseCaches()
    {
        return a.getUseCaches();
    }

    public void setAllowUserInteraction(boolean flag)
    {
        a.setAllowUserInteraction(flag);
    }

    public void setChunkedStreamingMode(int i)
    {
        a.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i)
    {
        a.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean flag)
    {
        a.setDefaultUseCaches(flag);
    }

    public void setDoInput(boolean flag)
    {
        a.setDoInput(flag);
    }

    public void setDoOutput(boolean flag)
    {
        a.setDoOutput(flag);
    }

    public void setFixedLengthStreamingMode(int i)
    {
        a.setFixedLengthStreamingMode(i);
    }

    public void setIfModifiedSince(long l)
    {
        a.setIfModifiedSince(l);
    }

    public void setInstanceFollowRedirects(boolean flag)
    {
        a.setInstanceFollowRedirects(flag);
    }

    public void setReadTimeout(int i)
    {
        a.setReadTimeout(i);
    }

    public void setRequestMethod(String s)
    {
        b();
        try
        {
            a.setRequestMethod(s);
            return;
        }
        catch (ProtocolException protocolexception)
        {
            a(protocolexception);
            throw protocolexception;
        }
    }

    public void setRequestProperty(String s, String s1)
    {
        a.setRequestProperty(s, s1);
    }

    public void setUseCaches(boolean flag)
    {
        a.setUseCaches(flag);
    }

    public String toString()
    {
        return a.toString();
    }

    public boolean usingProxy()
    {
        return a.usingProxy();
    }

}
