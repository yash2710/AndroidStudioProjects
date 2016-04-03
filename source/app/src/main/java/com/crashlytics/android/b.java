// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import com.crashlytics.android.internal.q;
import com.crashlytics.android.internal.v;
import java.io.File;
import java.util.concurrent.Callable;

// Referenced classes of package com.crashlytics.android:
//            Z

final class b
    implements Callable
{

    private Z a;

    b(Z z)
    {
        a = z;
        super();
    }

    private Boolean a()
    {
        Boolean boolean1;
        try
        {
            boolean flag = Z.f(a).delete();
            v.a().b().a("Crashlytics", (new StringBuilder("Initialization marker file removed: ")).append(flag).toString());
            boolean1 = Boolean.valueOf(flag);
        }
        catch (Exception exception)
        {
            v.a().b().a("Crashlytics", "Problem encountered deleting Crashlytics initialization marker.", exception);
            return Boolean.valueOf(false);
        }
        return boolean1;
    }

    public final Object call()
    {
        return a();
    }
}
