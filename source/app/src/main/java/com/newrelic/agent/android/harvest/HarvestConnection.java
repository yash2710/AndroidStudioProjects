// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.stats.TicToc;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestResponse, ConnectInformation

public class HarvestConnection
{

    public static final int NSURLErrorBadServerResponse = -1011;
    public static final int NSURLErrorBadURL = -1000;
    public static final int NSURLErrorCannotConnectToHost = -1004;
    public static final int NSURLErrorDNSLookupFailed = -1006;
    public static final int NSURLErrorSecureConnectionFailed = -1200;
    public static final int NSURLErrorTimedOut = -1001;
    public static final int NSURLErrorUnknown = -1;
    private static final Boolean b = Boolean.valueOf(false);
    private final AgentLog a = AgentLogManager.getAgentLog();
    private String c;
    private String d;
    private long e;
    private final HttpClient f;
    private ConnectInformation g;
    private boolean h;

    public HarvestConnection()
    {
        int i = (int)TimeUnit.MILLISECONDS.convert(20L, TimeUnit.SECONDS);
        BasicHttpParams basichttpparams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basichttpparams, i);
        HttpConnectionParams.setSoTimeout(basichttpparams, i);
        HttpConnectionParams.setTcpNoDelay(basichttpparams, true);
        HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
        f = new DefaultHttpClient(basichttpparams);
    }

    private byte[] a(String s)
    {
        Deflater deflater = new Deflater();
        deflater.setInput(s.getBytes());
        deflater.finish();
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[8192];
        int i;
        for (; !deflater.finished(); bytearrayoutputstream.write(abyte0, 0, i))
        {
            i = deflater.deflate(abyte0);
            if (i <= 0)
            {
                a.error("HTTP request contains an incomplete payload");
            }
        }

        deflater.end();
        return bytearrayoutputstream.toByteArray();
    }

    private String b(String s)
    {
        String s1;
        if (h)
        {
            s1 = "https://";
        } else
        {
            s1 = "http://";
        }
        return (new StringBuilder()).append(s1).append(c).append(s).toString();
    }

    public static String readResponse(HttpResponse httpresponse)
    {
        char ac[];
        StringBuilder stringbuilder;
        InputStream inputstream;
        ac = new char[8192];
        stringbuilder = new StringBuilder();
        inputstream = httpresponse.getEntity().getContent();
        BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
_L1:
        int i = bufferedreader.read(ac);
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_77;
        }
        stringbuilder.append(ac, 0, i);
          goto _L1
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        inputstream.close();
        return stringbuilder.toString();
    }

    public HttpPost createConnectPost(String s)
    {
        return createPost(b("/mobile/v2/connect"), s);
    }

    public HttpPost createDataPost(String s)
    {
        return createPost(b("/mobile/v2/data"), s);
    }

    public HttpPost createPost(String s, String s1)
    {
        String s2;
        HttpPost httppost;
        if (s1.length() <= 512 || b.booleanValue())
        {
            s2 = "identity";
        } else
        {
            s2 = "deflate";
        }
        httppost = new HttpPost(s);
        httppost.addHeader("Content-Type", "application/json");
        httppost.addHeader("Content-Encoding", s2);
        httppost.addHeader("User-Agent", System.getProperty("http.agent"));
        if (d == null)
        {
            a.error("Cannot create POST without an Application Token.");
            return null;
        }
        httppost.addHeader("X-App-License-Key", d);
        if (e != 0L)
        {
            httppost.addHeader("X-NewRelic-Connect-Time", Long.valueOf(e).toString());
        }
        if (!"deflate".equals(s2)) goto _L2; else goto _L1
_L1:
        httppost.setEntity(new ByteArrayEntity(a(s1)));
_L4:
        return httppost;
_L2:
        try
        {
            httppost.setEntity(new StringEntity(s1, "utf-8"));
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            a.error("UTF-8 is unsupported");
            throw new IllegalArgumentException(unsupportedencodingexception);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public ConnectInformation getConnectInformation()
    {
        return g;
    }

    public HarvestResponse send(HttpPost httppost)
    {
        char c1;
        HarvestResponse harvestresponse;
        c1 = '\uFC17';
        harvestresponse = new HarvestResponse();
        HttpResponse httpresponse;
        TicToc tictoc = new TicToc();
        tictoc.tic();
        httpresponse = f.execute(httppost);
        harvestresponse.setResponseTime(tictoc.toc());
        harvestresponse.setStatusCode(httpresponse.getStatusLine().getStatusCode());
        Exception exception;
        StatsEngine statsengine;
        StringBuilder stringbuilder;
        try
        {
            harvestresponse.setResponseBody(readResponse(httpresponse));
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            a.error((new StringBuilder("Failed to retrieve collector response: ")).append(ioexception.getMessage()).toString());
        }
_L3:
        return harvestresponse;
        exception;
        a.error((new StringBuilder("Failed to send POST to collector: ")).append(exception.getMessage()).toString());
        statsengine = StatsEngine.get();
        stringbuilder = new StringBuilder("Supportability/AgentHealth/Collector/ResponseErrorCodes/");
        if (!(exception instanceof ClientProtocolException)) goto _L2; else goto _L1
_L1:
        c1 = '\uFC0D';
_L4:
        statsengine.inc(stringbuilder.append(c1).toString());
        return null;
_L2:
        if (exception instanceof UnknownHostException)
        {
            c1 = '\uFC12';
        } else
        if (!(exception instanceof SocketTimeoutException) && !(exception instanceof ConnectTimeoutException))
        {
            if (exception instanceof ConnectException)
            {
                c1 = '\uFC14';
            } else
            if (exception instanceof MalformedURLException)
            {
                c1 = '\uFC18';
            } else
            if (exception instanceof SSLException)
            {
                c1 = '\uFB50';
            } else
            {
                c1 = '\uFFFF';
            }
        }
        if (true) goto _L4; else goto _L3
    }

    public HarvestResponse sendConnect()
    {
        if (g == null)
        {
            throw new IllegalArgumentException();
        }
        HttpPost httppost = createConnectPost(g.toJsonString());
        if (httppost == null)
        {
            a.error("Failed to create connect POST");
            return null;
        } else
        {
            TicToc tictoc = new TicToc();
            tictoc.tic();
            HarvestResponse harvestresponse = send(httppost);
            StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Connect", tictoc.toc());
            return harvestresponse;
        }
    }

    public HarvestResponse sendData(Harvestable harvestable)
    {
        if (harvestable == null)
        {
            throw new IllegalArgumentException();
        }
        HttpPost httppost = createDataPost(harvestable.toJsonString());
        if (httppost == null)
        {
            a.error("Failed to create data POST");
            return null;
        } else
        {
            return send(httppost);
        }
    }

    public void setApplicationToken(String s)
    {
        d = s;
    }

    public void setCollectorHost(String s)
    {
        c = s;
    }

    public void setConnectInformation(ConnectInformation connectinformation)
    {
        g = connectinformation;
    }

    public void setServerTimestamp(long l)
    {
        a.debug((new StringBuilder("Setting server timestamp: ")).append(l).toString());
        e = l;
    }

    public void useSsl(boolean flag)
    {
        h = flag;
    }

}
