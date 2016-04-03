// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.instrumentation.io.CountingInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class ContentBufferingResponseEntityImpl
    implements HttpEntity
{

    private HttpEntity a;
    private CountingInputStream b;

    public ContentBufferingResponseEntityImpl(HttpEntity httpentity)
    {
        if (httpentity == null)
        {
            throw new IllegalArgumentException("Missing wrapped entity");
        } else
        {
            a = httpentity;
            return;
        }
    }

    public void consumeContent()
    {
        a.consumeContent();
    }

    public InputStream getContent()
    {
        if (b != null)
        {
            return b;
        } else
        {
            b = new CountingInputStream(a.getContent(), true);
            return b;
        }
    }

    public Header getContentEncoding()
    {
        return a.getContentEncoding();
    }

    public long getContentLength()
    {
        return a.getContentLength();
    }

    public Header getContentType()
    {
        return a.getContentType();
    }

    public boolean isChunked()
    {
        return a.isChunked();
    }

    public boolean isRepeatable()
    {
        return a.isRepeatable();
    }

    public boolean isStreaming()
    {
        return a.isStreaming();
    }

    public void writeTo(OutputStream outputstream)
    {
        a.writeTo(outputstream);
    }
}
