// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.app.Activity;
import com.crashlytics.android.internal.aU;
import com.crashlytics.android.internal.aX;
import com.crashlytics.android.internal.v;

// Referenced classes of package com.crashlytics.android:
//            Crashlytics

final class Q
    implements aU
{

    private Crashlytics a;

    Q(Crashlytics crashlytics)
    {
        a = crashlytics;
        super();
    }

    public final Object a(aX ax)
    {
        boolean flag = true;
        Activity activity = v.a().e();
        if (activity != null && !activity.isFinishing() && a.j())
        {
            flag = Crashlytics.a(a, activity, ax.c);
        }
        return Boolean.valueOf(flag);
    }
}
