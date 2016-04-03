// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.newrelic.agent.android.util.Util;
import java.net.MalformedURLException;
import java.net.URL;

// Referenced classes of package com.newrelic.agent.android.instrumentation:
//            f

public final class TransactionState
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private String b;
    private int c;
    private int d;
    private long e;
    private long f;
    private long g;
    private long h;
    private String i;
    private String j;
    private f k;
    private String l;
    private TransactionData m;

    public TransactionState()
    {
        g = System.currentTimeMillis();
        j = "unknown";
        k = f.a;
        TraceMachine.enterNetworkSegment("External/unknownhost");
    }

    public final TransactionData end()
    {
        if (!isComplete())
        {
            k = f.c;
            h = System.currentTimeMillis();
            TraceMachine.exitMethod();
        }
        if (!isComplete())
        {
            a.warning("toTransactionData() called on incomplete TransactionState");
        }
        if (b == null)
        {
            a.error("Attempted to convert a TransactionState instance with no URL into a TransactionData");
            return null;
        }
        if (m == null)
        {
            m = new TransactionData(b, j, (float)(h - g) / 1000F, c, d, e, f, i);
        }
        return m;
    }

    public final long getBytesReceived()
    {
        return f;
    }

    public final String getContentType()
    {
        return l;
    }

    public final int getErrorCode()
    {
        return d;
    }

    public final int getStatusCode()
    {
        return c;
    }

    public final String getUrl()
    {
        return b;
    }

    public final boolean isComplete()
    {
        return k.ordinal() >= f.c.ordinal();
    }

    public final boolean isSent()
    {
        return k.ordinal() >= f.b.ordinal();
    }

    public final void setAppData(String s)
    {
        if (!isComplete())
        {
            i = s;
            TraceMachine.setCurrentTraceParam("encoded_app_data", s);
            return;
        } else
        {
            a.warning((new StringBuilder("setAppData(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
            return;
        }
    }

    public final void setBytesReceived(long l1)
    {
        if (!isComplete())
        {
            f = l1;
            TraceMachine.setCurrentTraceParam("bytes_received", Long.valueOf(l1));
            return;
        } else
        {
            a.warning((new StringBuilder("setBytesReceived(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
            return;
        }
    }

    public final void setBytesSent(long l1)
    {
        if (!isComplete())
        {
            e = l1;
            TraceMachine.setCurrentTraceParam("bytes_sent", Long.valueOf(l1));
            k = f.b;
            return;
        } else
        {
            a.warning((new StringBuilder("setBytesSent(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
            return;
        }
    }

    public final void setCarrier(String s)
    {
        if (!isSent())
        {
            j = s;
            TraceMachine.setCurrentTraceParam("carrier", s);
            return;
        } else
        {
            a.warning((new StringBuilder("setCarrier(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
            return;
        }
    }

    public final void setContentType(String s)
    {
        l = s;
    }

    public final void setErrorCode(int i1)
    {
        if (!isComplete())
        {
            d = i1;
            TraceMachine.setCurrentTraceParam("error_code", Integer.valueOf(i1));
            return;
        }
        if (m != null)
        {
            m.setErrorCode(i1);
        }
        a.warning((new StringBuilder("setErrorCode(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
    }

    public final void setStatusCode(int i1)
    {
        if (!isComplete())
        {
            c = i1;
            TraceMachine.setCurrentTraceParam("status_code", Integer.valueOf(i1));
            return;
        } else
        {
            a.warning((new StringBuilder("setStatusCode(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
            return;
        }
    }

    public final void setUrl(String s)
    {
        String s1 = Util.sanitizeUrl(s);
        if (s1 == null)
        {
            return;
        }
        if (!isSent())
        {
            b = s1;
            try
            {
                TraceMachine.setCurrentDisplayName((new StringBuilder("External/")).append((new URL(s1)).getHost()).toString());
            }
            catch (MalformedURLException malformedurlexception)
            {
                a.error((new StringBuilder("unable to parse host name from ")).append(s1).toString());
            }
            TraceMachine.setCurrentTraceParam("uri", s1);
            return;
        } else
        {
            a.warning((new StringBuilder("setUrl(...) called on TransactionState in ")).append(k.toString()).append(" state").toString());
            return;
        }
    }

    public final String toString()
    {
        return (new StringBuilder("TransactionState{url='")).append(b).append('\'').append(", statusCode=").append(c).append(", errorCode=").append(d).append(", bytesSent=").append(e).append(", bytesReceived=").append(f).append(", startTime=").append(g).append(", endTime=").append(h).append(", appData='").append(i).append('\'').append(", carrier='").append(j).append('\'').append(", state=").append(k).append(", contentType='").append(l).append('\'').append(", transactionData=").append(m).append('}').toString();
    }

}
