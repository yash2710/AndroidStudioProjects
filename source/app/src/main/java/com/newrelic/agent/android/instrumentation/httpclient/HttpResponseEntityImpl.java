// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.Measurements;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListenerSource;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpResponseEntityImpl
    implements StreamCompleteListener, HttpEntity
{

    private static final AgentLog e = AgentLogManager.getAgentLog();
    private final HttpEntity a;
    private final TransactionState b;
    private final long c;
    private CountingInputStream d;

    public HttpResponseEntityImpl(HttpEntity httpentity, TransactionState transactionstate, long l)
    {
        a = httpentity;
        b = transactionstate;
        c = l;
    }

    private void a(TransactionState transactionstate)
    {
        TransactionData transactiondata = transactionstate.end();
        TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        if ((long)transactionstate.getStatusCode() >= 400L)
        {
            StringBuilder stringbuilder = new StringBuilder();
            Header header;
            TreeMap treemap;
            try
            {
                InputStream inputstream = getContent();
                if (inputstream instanceof CountingInputStream)
                {
                    stringbuilder.append(((CountingInputStream)inputstream).getBufferAsString());
                }
            }
            catch (Exception exception)
            {
                e.error(exception.toString());
            }
            header = a.getContentType();
            treemap = new TreeMap();
            if (header != null && header.getValue() != null && !"".equals(header.getValue()))
            {
                treemap.put("content_type", header.getValue());
            }
            treemap.put("content_length", (new StringBuilder()).append(transactionstate.getBytesReceived()).toString());
            Measurements.addHttpError(transactiondata, stringbuilder.toString(), treemap);
        }
    }

    public final void consumeContent()
    {
        try
        {
            a.consumeContent();
            return;
        }
        catch (IOException ioexception)
        {
            TransactionStateUtil.setErrorCodeFromException(b, ioexception);
            if (!b.isComplete())
            {
                TransactionData transactiondata = b.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
            }
            throw ioexception;
        }
    }

    public final InputStream getContent()
    {
        if (d != null)
        {
            return d;
        }
        CountingInputStream countinginputstream;
        try
        {
            d = new CountingInputStream(a.getContent(), true);
            d.addStreamCompleteListener(this);
            countinginputstream = d;
        }
        catch (IOException ioexception)
        {
            TransactionStateUtil.setErrorCodeFromException(b, ioexception);
            if (!b.isComplete())
            {
                TransactionData transactiondata = b.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
            }
            throw ioexception;
        }
        return countinginputstream;
    }

    public final Header getContentEncoding()
    {
        return a.getContentEncoding();
    }

    public final long getContentLength()
    {
        return a.getContentLength();
    }

    public final Header getContentType()
    {
        return a.getContentType();
    }

    public final boolean isChunked()
    {
        return a.isChunked();
    }

    public final boolean isRepeatable()
    {
        return a.isRepeatable();
    }

    public final boolean isStreaming()
    {
        return a.isStreaming();
    }

    public final void streamComplete(StreamCompleteEvent streamcompleteevent)
    {
        ((StreamCompleteListenerSource)streamcompleteevent.getSource()).removeStreamCompleteListener(this);
        if (!b.isComplete())
        {
            if (c >= 0L)
            {
                b.setBytesReceived(c);
            } else
            {
                b.setBytesReceived(streamcompleteevent.getBytes());
            }
            a(b);
        }
    }

    public final void streamError(StreamCompleteEvent streamcompleteevent)
    {
        ((StreamCompleteListenerSource)streamcompleteevent.getSource()).removeStreamCompleteListener(this);
        TransactionStateUtil.setErrorCodeFromException(b, streamcompleteevent.getException());
        if (!b.isComplete())
        {
            b.setBytesReceived(streamcompleteevent.getBytes());
        }
    }

    public final void writeTo(OutputStream outputstream)
    {
        if (!b.isComplete())
        {
            CountingOutputStream countingoutputstream = new CountingOutputStream(outputstream);
            try
            {
                a.writeTo(countingoutputstream);
            }
            catch (IOException ioexception)
            {
                TransactionStateUtil.setErrorCodeFromException(b, ioexception);
                if (!b.isComplete())
                {
                    b.setBytesReceived(countingoutputstream.getCount());
                    TransactionData transactiondata = b.end();
                    TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
                }
                ioexception.printStackTrace();
                throw ioexception;
            }
            if (!b.isComplete())
            {
                if (c >= 0L)
                {
                    b.setBytesReceived(c);
                } else
                {
                    b.setBytesReceived(countingoutputstream.getCount());
                }
                a(b);
            }
            return;
        } else
        {
            a.writeTo(outputstream);
            return;
        }
    }

}
