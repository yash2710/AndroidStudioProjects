// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.crashlytics.android.internal:
//            G, K, ab, j, 
//            D, r, w, H, 
//            g, aK, av

final class i
    implements G
{

    private final ScheduledExecutorService a;
    private final j b;
    private final av c;
    private ScheduledFuture d;
    private int e;
    private w f;

    public i(ScheduledExecutorService scheduledexecutorservice, j j1, av av)
    {
        e = -1;
        a = scheduledexecutorservice;
        b = j1;
        c = av;
    }

    private void a(int k, int l)
    {
        try
        {
            K k1 = new K(b, this);
            ab.c((new StringBuilder("Scheduling time based file roll over every ")).append(l).append(" seconds").toString());
            d = a.scheduleAtFixedRate(k1, k, l, TimeUnit.SECONDS);
            return;
        }
        catch (RejectedExecutionException rejectedexecutionexception)
        {
            ab.d("Crashlytics failed to schedule time based analytics file roll over");
        }
    }

    public final void a()
    {
        if (f != null) goto _L2; else goto _L1
_L1:
        ab.c("skipping analytics files send because we don't yet know the target endpoint");
_L5:
        return;
_L2:
        List list;
        int k;
        ab.c("Sending all analytics files");
        list = b.b();
        k = 0;
_L3:
        boolean flag;
        if (list.size() <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        flag = f.a(r.a(D.a().getContext(), false), list);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        int i1 = list.size();
        int l = i1 + k;
        b.a(list);
        k = l;
        Locale locale;
        Object aobj[];
        locale = Locale.US;
        aobj = new Object[2];
        aobj[0] = Integer.valueOf(list.size());
        String s;
        if (flag)
        {
            s = "succeeded";
        } else
        {
            s = "did not succeed";
        }
        aobj[1] = s;
        ab.c(String.format(locale, "attempt to send batch of %d analytics files %s", aobj));
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
        list = b.b();
          goto _L3
        Exception exception;
        exception;
        Exception exception1;
        l = k;
        exception1 = exception;
_L6:
        ab.d((new StringBuilder("Crashlytics failed to send batch of analytics files to server: ")).append(exception1.getMessage()).toString());
        k = l;
        if (k != 0) goto _L5; else goto _L4
_L4:
        b.d();
        return;
        exception1;
          goto _L6
    }

    public final void a(H h)
    {
        boolean flag = true;
        ab.c(h.toString());
        boolean flag1;
        try
        {
            b.a(h);
        }
        catch (IOException ioexception)
        {
            ab.d("Crashlytics failed to write session event.");
        }
        if (e != -1)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (d != null)
        {
            flag = false;
        }
        if (flag1 && flag)
        {
            a(e, e);
        }
    }

    public final void a(aK ak, String s)
    {
        f = new g(s, ak.a, c);
        b.a(ak);
        e = ak.b;
        a(0, e);
    }

    public final void b()
    {
        b.c();
    }

    public final void c()
    {
        if (d != null)
        {
            ab.c("Cancelling time-based rollover because no events are currently being generated.");
            d.cancel(false);
            d = null;
        }
    }

    public final void d()
    {
        try
        {
            b.a();
            return;
        }
        catch (IOException ioexception)
        {
            ab.d("Crashlytics failed to roll analytics file over.");
        }
    }
}
