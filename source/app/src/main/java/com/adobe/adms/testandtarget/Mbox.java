// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.testandtarget;

import android.util.Log;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.adobe.adms.testandtarget:
//            MboxFactory, MboxContentConsumer, MissingDefaultContentException, MissingOnLoadConsumerException, 
//            a

public class Mbox
{

    private String a;
    private long b;
    private String c;
    private ConcurrentHashMap d;
    private String e;
    private Timer f;
    private a g;
    private Vector h;
    private boolean i;
    private MboxFactory j;

    protected Mbox(MboxFactory mboxfactory, String s)
    {
        b = 15000L;
        d = new ConcurrentHashMap();
        h = new Vector();
        i = false;
        j = mboxfactory;
        c = s;
    }

    private String a()
    {
        String s = String.valueOf(System.currentTimeMillis());
        StringBuilder stringbuilder = new StringBuilder(j.getBaseRequestURL());
        stringbuilder.append((new StringBuilder("mbox=")).append(j.encode(c)).append("&mboxDefault=").append(j.encode("/images/log.gif")).append("&mboxContentType=").append(j.encode("text/plain; charset=utf-8")).append("&mboxTime=").append(s).toString());
        java.util.Map.Entry entry;
        for (Iterator iterator = d.entrySet().iterator(); iterator.hasNext(); stringbuilder.append((new StringBuilder("&")).append(j.encode((String)entry.getKey())).append("=").append(j.encode((String)entry.getValue())).toString()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        return stringbuilder.toString();
    }

    public void addMboxParameter(String s, String s1)
    {
        d.putIfAbsent(s, s1);
    }

    public void addOnLoadConsumer(MboxContentConsumer mboxcontentconsumer)
    {
        h.add(mboxcontentconsumer);
    }

    protected void callOnLoadConsumers(String s)
    {
        this;
        JVM INSTR monitorenter ;
        if (f != null)
        {
            f.cancel();
        }
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_25;
        }
        s = a;
        if (i)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        i = true;
        int k = 0;
_L1:
        int l = h.size();
        if (k >= l)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        ((MboxContentConsumer)h.elementAt(k)).consume(s);
_L2:
        k++;
          goto _L1
        Exception exception1;
        exception1;
        exception1.printStackTrace();
          goto _L2
        Exception exception;
        exception;
        throw exception;
        this;
        JVM INSTR monitorexit ;
    }

    public MboxFactory getFactory()
    {
        return j;
    }

    public void load()
    {
        this;
        JVM INSTR monitorenter ;
        if (a == null)
        {
            throw new MissingDefaultContentException();
        }
        break MISSING_BLOCK_LABEL_22;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (h.isEmpty())
        {
            throw new MissingOnLoadConsumerException();
        }
        if (j.isEnabled())
        {
            break MISSING_BLOCK_LABEL_69;
        }
        Log.w("Mbox", "WARNING: MboxFactory is disabled. Consuming default content.");
        callOnLoadConsumers(a);
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
        f = new Timer();
        g = new a(this, (byte)0);
        f.schedule(g, b);
        e = j.getMboxResponse(this, a());
        callOnLoadConsumers(e);
          goto _L1
    }

    public void setDefaultContent(String s)
    {
        this;
        JVM INSTR monitorenter ;
        a = s;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setMaxResponseTime(long l)
    {
        b = l;
    }
}
