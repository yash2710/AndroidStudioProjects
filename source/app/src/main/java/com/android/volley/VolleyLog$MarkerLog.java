// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.android.volley:
//            f, VolleyLog

public class b
{

    public static final boolean ENABLED = true;
    private final List a = new ArrayList();
    private boolean b;

    private long a()
    {
        if (a.size() == 0)
        {
            return 0L;
        } else
        {
            long l = ((f)a.get(0)).c;
            return ((f)a.get(-1 + a.size())).c - l;
        }
    }

    public void add(String s, long l)
    {
        this;
        JVM INSTR monitorenter ;
        if (b)
        {
            throw new IllegalStateException("Marker added to finished log");
        }
        break MISSING_BLOCK_LABEL_26;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        a.add(new f(s, l, SystemClock.elapsedRealtime()));
        this;
        JVM INSTR monitorexit ;
    }

    public long calcDuration()
    {
        if (a() <= 0L)
        {
            return -1L;
        }
        long l = ((f)a.get(0)).c;
        Iterator iterator = a.iterator();
        long l2;
        for (long l1 = l; iterator.hasNext(); l1 = l2)
        {
            f f1 = (f)iterator.next();
            l2 = f1.c;
            if (f1.a.equalsIgnoreCase("network-http-complete"))
            {
                return l2 - l1;
            }
        }

        return -1L;
    }

    public int calcRetries()
    {
        Iterator iterator = a.iterator();
        int i = 0;
        while (iterator.hasNext()) 
        {
            int j;
            if (((f)iterator.next()).a.contains("retry"))
            {
                j = i + 1;
            } else
            {
                j = i;
            }
            i = j;
        }
        return i;
    }

    protected void finalize()
    {
        if (!b)
        {
            finish("Request on the loose");
            VolleyLog.e("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    public void finish(String s)
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        b = true;
        l = a();
        if (l > 0L) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        long l1;
        Iterator iterator;
        l1 = ((f)a.get(0)).c;
        Object aobj[] = new Object[2];
        aobj[0] = Long.valueOf(l);
        aobj[1] = s;
        VolleyLog.d("(%-4d ms) %s", aobj);
        iterator = a.iterator();
        long l2 = l1;
_L4:
        long l3;
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        f f1 = (f)iterator.next();
        l3 = f1.c;
        Object aobj1[] = new Object[3];
        aobj1[0] = Long.valueOf(l3 - l2);
        aobj1[1] = Long.valueOf(f1.b);
        aobj1[2] = f1.a;
        VolleyLog.d("(+%-4d) [%2d] %s", aobj1);
        l2 = l3;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public ()
    {
        b = false;
    }
}
