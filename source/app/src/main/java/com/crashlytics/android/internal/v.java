// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.crashlytics.android.internal:
//            p, B, m, aL, 
//            r, u, q, aH, 
//            aI

public final class v extends p
{

    private AtomicReference a;
    private boolean b;
    private File c;
    private Application d;
    private WeakReference e;
    private String f;
    private int g;
    private ConcurrentHashMap h;

    v()
    {
        a = new AtomicReference();
        g = 4;
        new B(m.a);
        h = new ConcurrentHashMap();
    }

    public static v a()
    {
        return aL.a();
    }

    private v a(Activity activity)
    {
        e = new WeakReference(activity);
        return this;
    }

    static v a(v v1, Activity activity)
    {
        return v1.a(activity);
    }

    public static transient void a(Context context, u au[])
    {
        com/crashlytics/android/internal/v;
        JVM INSTR monitorenter ;
        boolean flag = aL.a().isInitialized();
        if (!flag) goto _L2; else goto _L1
_L1:
        com/crashlytics/android/internal/v;
        JVM INSTR monitorexit ;
        return;
_L2:
        v v2;
        int j;
        v v1 = aL.a();
        v1.d = r.b(context);
        v2 = v1.a(r.a(context));
        j = au.length;
        Exception exception;
        u u1;
        for (int k = 0; k >= j; k++)
        {
            break MISSING_BLOCK_LABEL_94;
        }

        u1 = au[k];
        if (!v2.h.containsKey(au))
        {
            v2.h.putIfAbsent(u1.getClass(), u1);
        }
        break MISSING_BLOCK_LABEL_109;
        v2.a(context);
        if (true) goto _L1; else goto _L3
_L3:
        exception;
        throw exception;
    }

    public final u a(Class class1)
    {
        return (u)h.get(class1);
    }

    public final void a(q q1)
    {
        a.set(q1);
    }

    public final void a(String s)
    {
        f = s;
    }

    public final void a(boolean flag)
    {
        b = flag;
        int j;
        if (flag)
        {
            j = 3;
        } else
        {
            j = 4;
        }
        g = j;
    }

    public final q b()
    {
        Object obj = (q)a.get();
        if (obj == null)
        {
            obj = new r();
            if (!a.compareAndSet(null, obj))
            {
                obj = (q)a.get();
            }
        }
        return ((q) (obj));
    }

    protected final void c()
    {
        Context context = getContext();
        c = new File(context.getFilesDir(), "com.crashlytics.sdk.android");
        if (!c.exists())
        {
            c.mkdirs();
        }
        if (android.os.Build.VERSION.SDK_INT >= 14)
        {
            aH ah = new aH(this, (byte)0);
            Application application = d;
            if (application != null)
            {
                application.registerActivityLifecycleCallbacks(new aI(ah));
            }
        }
        if (b && Log.isLoggable("CrashlyticsInternal", 3))
        {
            StringBuilder stringbuilder = new StringBuilder();
            p p1;
            long l;
            for (Iterator iterator1 = h.values().iterator(); iterator1.hasNext(); stringbuilder.append("sdkPerfStart.").append(p1.getClass().getName()).append('=').append(System.nanoTime() - l).append('\n'))
            {
                p1 = (p)iterator1.next();
                l = System.nanoTime();
                p1.a(context);
            }

            Log.d("CrashlyticsInternal", stringbuilder.toString());
        } else
        {
            Iterator iterator = h.values().iterator();
            while (iterator.hasNext()) 
            {
                ((p)iterator.next()).a(context);
            }
        }
    }

    public final Application d()
    {
        return d;
    }

    public final Activity e()
    {
        if (e != null)
        {
            return (Activity)e.get();
        } else
        {
            return null;
        }
    }

    public final boolean f()
    {
        return b;
    }

    public final int g()
    {
        return g;
    }

    public final String getVersion()
    {
        return "1.1.13.29";
    }

    public final File h()
    {
        return c;
    }

    public final String i()
    {
        return f;
    }
}
