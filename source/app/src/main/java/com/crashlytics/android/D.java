// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.aS;
import com.crashlytics.android.internal.aa;
import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.v;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.crashlytics.android:
//            Crashlytics, B, Z, Q, 
//            z

final class D extends aa
{

    private final float a;
    private B b;

    D(B b1, float f)
    {
        b = b1;
        super();
        a = f;
    }

    public final void a()
    {
        float f;
        v.a().b().a("Crashlytics", (new StringBuilder("Starting report processing in ")).append(a).append(" second(s)...").toString());
        f = a;
        if (f <= 0.0F)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        Thread.sleep((long)(1000F * a));
        Exception exception;
        List list;
        Crashlytics crashlytics = Crashlytics.getInstance();
        Z z1 = crashlytics.m();
        list = b.a();
        if (!z1.a())
        {
            if (list.isEmpty() || ((Boolean)aS.a().a(new Q(crashlytics), Boolean.valueOf(true))).booleanValue())
            {
                break MISSING_BLOCK_LABEL_458;
            }
            v.a().b().a("Crashlytics", (new StringBuilder("User declined to send. Removing ")).append(list.size()).append(" Report(s).").toString());
            for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); ((z)iterator1.next()).a()) { }
        }
_L1:
        B.a(b);
        return;
        InterruptedException interruptedexception1;
        interruptedexception1;
        try
        {
            Thread.currentThread().interrupt();
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            v.a().b().a("Crashlytics", "An unexpected error occurred while attempting to upload crash reports.", exception);
        }
          goto _L1
_L4:
        List list1;
        if (list1.isEmpty() || Crashlytics.getInstance().m().a()) goto _L1; else goto _L2
_L2:
        v.a().b().a("Crashlytics", (new StringBuilder("Attempting to send ")).append(list1.size()).append(" report(s)").toString());
        z z2;
        for (Iterator iterator = list1.iterator(); iterator.hasNext(); b.a(z2))
        {
            z2 = (z)iterator.next();
        }

        list1 = b.a();
        if (list1.isEmpty()) goto _L4; else goto _L3
_L3:
        short aword0[] = B.b();
        int i;
        int j = i + 1;
        long l;
        l = aword0[Math.min(i, -1 + B.b().length)];
        v.a().b().a("Crashlytics", (new StringBuilder("Report submisson: scheduling delayed retry in ")).append(l).append(" seconds").toString());
        long l1 = l * 1000L;
        Thread.sleep(l1);
        i = j;
          goto _L4
        InterruptedException interruptedexception;
        interruptedexception;
        Thread.currentThread().interrupt();
          goto _L1
        list1 = list;
        i = 0;
          goto _L4
    }
}
