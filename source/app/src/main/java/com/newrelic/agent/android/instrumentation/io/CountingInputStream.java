// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.io;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteListenerSource, a, StreamCompleteEvent, StreamCompleteListener

public final class CountingInputStream extends InputStream
    implements StreamCompleteListenerSource
{

    private static final AgentLog f = AgentLogManager.getAgentLog();
    private final InputStream a;
    private long b;
    private final a c;
    private final ByteBuffer d;
    private boolean e;

    public CountingInputStream(InputStream inputstream)
    {
        b = 0L;
        c = new a();
        e = false;
        a = inputstream;
        if (e)
        {
            d = ByteBuffer.allocate(Agent.getResponseBodyLimit());
            fillBuffer();
            return;
        } else
        {
            d = null;
            return;
        }
    }

    public CountingInputStream(InputStream inputstream, boolean flag)
    {
        b = 0L;
        c = new a();
        e = false;
        a = inputstream;
        e = flag;
        if (flag)
        {
            d = ByteBuffer.allocate(Agent.getResponseBodyLimit());
            fillBuffer();
            return;
        } else
        {
            d = null;
            return;
        }
    }

    private int a(byte abyte0[], int i, int j)
    {
        if (a())
        {
            return -1;
        } else
        {
            int k = d.remaining();
            d.get(abyte0, i, j);
            return k - d.remaining();
        }
    }

    private void a(Exception exception)
    {
        if (!c.isComplete())
        {
            c.notifyStreamError(new StreamCompleteEvent(this, b, exception));
        }
    }

    private boolean a()
    {
        return !d.hasRemaining();
    }

    private boolean a(long l)
    {
        return (long)d.remaining() >= l;
    }

    private void b()
    {
        if (!c.isComplete())
        {
            c.notifyStreamComplete(new StreamCompleteEvent(this, b));
        }
    }

    public final void addStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        c.addStreamCompleteListener(streamcompletelistener);
    }

    public final int available()
    {
        boolean flag = e;
        int i = 0;
        if (flag)
        {
            i = d.remaining();
        }
        int j;
        try
        {
            j = a.available();
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        return i + j;
    }

    public final void close()
    {
        try
        {
            a.close();
            b();
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public final void fillBuffer()
    {
        if (d != null && d.hasArray()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j = a.read(d.array(), 0, d.capacity());
        int i = j;
_L4:
        if (i <= 0)
        {
            d.limit(0);
            return;
        }
        break; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        f.error(ioexception.toString());
        i = 0;
        if (true) goto _L4; else goto _L3
_L3:
        if (i >= d.capacity()) goto _L1; else goto _L5
_L5:
        d.limit(i);
        return;
    }

    public final String getBufferAsString()
    {
        if (d != null)
        {
            byte abyte0[] = new byte[d.limit()];
            for (int i = 0; i < d.limit(); i++)
            {
                abyte0[i] = d.get(i);
            }

            return new String(abyte0);
        } else
        {
            return "";
        }
    }

    public final void mark(int i)
    {
        if (!markSupported())
        {
            return;
        } else
        {
            a.mark(i);
            return;
        }
    }

    public final boolean markSupported()
    {
        return a.markSupported();
    }

    public final int read()
    {
        int i;
        if (e && a(1L))
        {
            byte byte0;
            if (a())
            {
                byte0 = -1;
            } else
            {
                byte0 = d.get();
            }
            if (byte0 >= 0)
            {
                b = 1L + b;
            }
            return byte0;
        }
        try
        {
            i = a.read();
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        b = 1L + b;
        return i;
        b();
        return i;
    }

    public final int read(byte abyte0[])
    {
        int j;
        int i = abyte0.length;
        boolean flag = e;
        j = 0;
        if (flag)
        {
            if (a(i))
            {
                int i1 = a(abyte0, 0, abyte0.length);
                if (i1 >= 0)
                {
                    b = b + (long)i1;
                    return i1;
                } else
                {
                    throw new RuntimeException("readBufferBytes failed");
                }
            }
            int l = d.remaining();
            j = 0;
            if (l > 0)
            {
                j = a(abyte0, 0, l);
                if (j < 0)
                {
                    throw new RuntimeException("partial read from buffer failed");
                }
                i -= j;
                b = b + (long)j;
            }
        }
        int k;
        try
        {
            k = a.read(abyte0, j, i);
        }
        catch (IOException ioexception)
        {
            f.error(ioexception.toString());
            System.out.println((new StringBuilder("NOTIFY STREAM ERROR: ")).append(ioexception).toString());
            ioexception.printStackTrace();
            a(ioexception);
            throw ioexception;
        }
        if (k < 0)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        b = b + (long)k;
        return k + j;
        if (j > 0)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        b();
        return k;
        return j;
    }

    public final int read(byte abyte0[], int i, int j)
    {
        int k;
        boolean flag = e;
        k = 0;
        if (flag)
        {
            if (a(j))
            {
                int j1 = a(abyte0, i, j);
                if (j1 >= 0)
                {
                    b = b + (long)j1;
                    return j1;
                } else
                {
                    throw new RuntimeException("readBufferBytes failed");
                }
            }
            int i1 = d.remaining();
            k = 0;
            if (i1 > 0)
            {
                k = a(abyte0, i, i1);
                if (k < 0)
                {
                    throw new RuntimeException("partial read from buffer failed");
                }
                j -= k;
                b = b + (long)k;
            }
        }
        int l;
        try
        {
            l = a.read(abyte0, i + k, j);
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
        if (l < 0)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        b = b + (long)l;
        return l + k;
        if (k > 0)
        {
            break MISSING_BLOCK_LABEL_182;
        }
        b();
        return l;
        return k;
    }

    public final void removeStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        c.removeStreamCompleteListener(streamcompletelistener);
    }

    public final void reset()
    {
        if (!markSupported())
        {
            return;
        }
        try
        {
            a.reset();
            return;
        }
        catch (IOException ioexception)
        {
            a(ioexception);
            throw ioexception;
        }
    }

    public final void setBufferingEnabled(boolean flag)
    {
        e = flag;
    }

    public final long skip(long l)
    {
label0:
        {
            if (e)
            {
                if (a(l))
                {
                    d.position((int)l);
                    b = l + b;
                    return l;
                }
                l -= d.remaining();
                if (l <= 0L)
                {
                    break label0;
                }
                d.position(d.remaining());
            }
            long l1;
            try
            {
                l1 = a.skip(l);
                b = l1 + b;
            }
            catch (IOException ioexception)
            {
                a(ioexception);
                throw ioexception;
            }
            return l1;
        }
        throw new RuntimeException("partial read from buffer (skip) failed");
    }

}
