// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import android.app.Activity;
import android.os.Looper;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

// Referenced classes of package com.crashlytics.android.internal:
//            ak, ah, i, j, 
//            z, H, ab, F, 
//            I, C, y, E, 
//            G, av, aK

class x
    implements ak
{

    G a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final ScheduledExecutorService j;

    public x(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            j j1, av av)
    {
        this(s, s1, s2, s3, s4, s5, s6, j1, ah.b("Crashlytics SAM"), av);
    }

    x(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            j j1, ScheduledExecutorService scheduledexecutorservice, av av)
    {
        b = s;
        c = s1;
        d = s2;
        e = s3;
        f = s4;
        g = s5;
        h = s6;
        i = UUID.randomUUID().toString();
        j = scheduledexecutorservice;
        a = new i(scheduledexecutorservice, j1, av);
        j1.a(this);
    }

    static String a(x x1)
    {
        return x1.b;
    }

    private void a(H h1, boolean flag)
    {
        a(((Runnable) (new z(this, h1, flag))));
    }

    private void a(I k, Activity activity)
    {
        a(H.a(b, i, c, d, e, f, g, h, k, Collections.singletonMap("activity", activity.getClass().getName())), false);
    }

    private void a(Runnable runnable)
    {
        try
        {
            j.submit(runnable);
            return;
        }
        catch (Exception exception)
        {
            ab.d("Crashlytics failed to submit analytics task");
        }
    }

    static String b(x x1)
    {
        return x1.i;
    }

    static String c(x x1)
    {
        return x1.c;
    }

    static String d(x x1)
    {
        return x1.d;
    }

    static String e(x x1)
    {
        return x1.e;
    }

    static String f(x x1)
    {
        return x1.f;
    }

    static String g(x x1)
    {
        return x1.g;
    }

    static String h(x x1)
    {
        return x1.h;
    }

    void a()
    {
        a(((Runnable) (new F(this))));
    }

    public final void a(Activity activity)
    {
        a(I.a, activity);
    }

    final void a(aK ak1, String s)
    {
        a(((Runnable) (new C(this, ak1, s))));
    }

    public final void a(String s)
    {
        if (Looper.myLooper() == Looper.getMainLooper())
        {
            throw new IllegalStateException("onCrash called from main thread!!!");
        }
        y y1 = new y(this, s);
        try
        {
            j.submit(y1).get();
            return;
        }
        catch (Exception exception)
        {
            ab.d("Crashlytics failed to run analytics task");
        }
    }

    public final void b()
    {
        a(H.a(b, i, c, d, e, f, g, h, I.j, new HashMap()), true);
    }

    public final void b(Activity activity)
    {
        a(I.g, activity);
    }

    public final void b(String s)
    {
        String s1 = b;
        String s2 = i;
        String s3 = c;
        String s4 = d;
        String s5 = e;
        String s6 = f;
        String s7 = g;
        String s8 = h;
        java.util.Map map = Collections.singletonMap("sessionId", s);
        a(H.a(s1, s2, s3, s4, s5, s6, s7, s8, I.h, map), false);
    }

    public final void c()
    {
        a(new E(this));
    }

    public final void c(Activity activity)
    {
        a(I.e, activity);
    }

    public final void d(Activity activity)
    {
        a(I.c, activity);
    }

    public final void e(Activity activity)
    {
        a(I.d, activity);
    }

    public final void f(Activity activity)
    {
        a(I.b, activity);
    }

    public final void g(Activity activity)
    {
        a(I.f, activity);
    }
}
