// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;


// Referenced classes of package com.crashlytics.android.internal:
//            x, G, ab, H

final class z
    implements Runnable
{

    private H a;
    private boolean b;
    private x c;

    z(x x1, H h, boolean flag)
    {
        c = x1;
        a = h;
        b = flag;
        super();
    }

    public final void run()
    {
        try
        {
            c.a.a(a);
            if (b)
            {
                c.a.d();
            }
            return;
        }
        catch (Exception exception)
        {
            ab.d("Crashlytics failed to record session event.");
        }
    }
}
