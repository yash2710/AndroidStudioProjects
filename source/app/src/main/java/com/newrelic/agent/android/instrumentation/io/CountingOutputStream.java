// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.io;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteListenerSource, a, StreamCompleteEvent, StreamCompleteListener

public final class CountingOutputStream extends OutputStream
    implements StreamCompleteListenerSource
{

    private final OutputStream a;
    private long b;
    private final a c = new a();

    public CountingOutputStream(OutputStream outputstream)
    {
        b = 0L;
        a = outputstream;
    }

    private void a(Exception exception)
    {
        if (!c.isComplete())
        {
            c.notifyStreamError(new StreamCompleteEvent(this, b, exception));
        }
    }

    public final void addStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        c.addStreamCompleteListener(streamcompletelistener);
    }

    public final void close()
    {
        try
        {
            a.close();
            if (!c.isComplete())
            {
                c.notifyStreamComplete(new StreamCompleteEvent(this, b));
            }
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public final void flush()
    {
        try
        {
            a.flush();
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public final long getCount()
    {
        return b;
    }

    public final void removeStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        c.removeStreamCompleteListener(streamcompletelistener);
    }

    public final void write(int i)
    {
        try
        {
            a.write(i);
            b = 1L + b;
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public final void write(byte abyte0[])
    {
        try
        {
            a.write(abyte0);
            b = b + (long)abyte0.length;
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public final void write(byte abyte0[], int i, int j)
    {
        try
        {
            a.write(abyte0, i, j);
            b = b + (long)j;
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }
}
