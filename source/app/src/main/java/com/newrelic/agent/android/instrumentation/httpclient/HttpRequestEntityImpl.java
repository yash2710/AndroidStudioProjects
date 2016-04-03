// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import com.newrelic.agent.android.instrumentation.io.CountingOutputStream;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteEvent;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListener;
import com.newrelic.agent.android.instrumentation.io.StreamCompleteListenerSource;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public final class HttpRequestEntityImpl
    implements StreamCompleteListener, HttpEntity
{

    private final HttpEntity a;
    private final TransactionState b;

    public HttpRequestEntityImpl(HttpEntity httpentity, TransactionState transactionstate)
    {
        a = httpentity;
        b = transactionstate;
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
        CountingInputStream countinginputstream;
        if (b.isSent())
        {
            break MISSING_BLOCK_LABEL_37;
        }
        countinginputstream = new CountingInputStream(a.getContent());
        countinginputstream.addStreamCompleteListener(this);
        return countinginputstream;
        InputStream inputstream;
        try
        {
            inputstream = a.getContent();
        }
        catch (IOException ioexception)
        {
            TransactionStateUtil.setErrorCodeFromException(b, ioexception);
            if (!b.isComplete())
            {
                TransactionData transactiondata1 = b.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata1.getUrl(), transactiondata1.getStatusCode(), transactiondata1.getErrorCode(), transactiondata1.getTimestamp(), transactiondata1.getTime(), transactiondata1.getBytesSent(), transactiondata1.getBytesReceived(), transactiondata1.getAppData()));
            }
            throw ioexception;
        }
        catch (IllegalStateException illegalstateexception)
        {
            TransactionStateUtil.setErrorCodeFromException(b, illegalstateexception);
            if (!b.isComplete())
            {
                TransactionData transactiondata = b.end();
                TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
            }
            throw illegalstateexception;
        }
        return inputstream;
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
        b.setBytesSent(streamcompleteevent.getBytes());
    }

    public final void streamError(StreamCompleteEvent streamcompleteevent)
    {
        ((StreamCompleteListenerSource)streamcompleteevent.getSource()).removeStreamCompleteListener(this);
        TransactionStateUtil.setErrorCodeFromException(b, streamcompleteevent.getException());
        if (!b.isComplete())
        {
            b.setBytesSent(streamcompleteevent.getBytes());
            TransactionData transactiondata = b.end();
            TaskQueue.queue(new HttpTransactionMeasurement(transactiondata.getUrl(), transactiondata.getStatusCode(), transactiondata.getErrorCode(), transactiondata.getTimestamp(), transactiondata.getTime(), transactiondata.getBytesSent(), transactiondata.getBytesReceived(), transactiondata.getAppData()));
        }
    }

    public final void writeTo(OutputStream outputstream)
    {
        try
        {
            if (!b.isSent())
            {
                CountingOutputStream countingoutputstream = new CountingOutputStream(outputstream);
                a.writeTo(countingoutputstream);
                b.setBytesSent(countingoutputstream.getCount());
                return;
            }
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
        a.writeTo(outputstream);
        return;
    }
}
