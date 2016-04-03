// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.aP;
import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;

// Referenced classes of package com.crashlytics.android:
//            Crashlytics

final class P
    implements aU
{

    private Crashlytics a;

    P(Crashlytics crashlytics)
    {
        a = crashlytics;
        super();
    }

    public final Object a(aX ax)
    {
        if (ax.d.a)
        {
            Crashlytics _tmp = a;
            boolean flag = Crashlytics.k();
            boolean flag1 = false;
            if (!flag)
            {
                flag1 = true;
            }
            return Boolean.valueOf(flag1);
        } else
        {
            return Boolean.valueOf(false);
        }
    }
}
