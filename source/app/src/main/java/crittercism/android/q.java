// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package crittercism.android;

import com.crittercism.app.Crittercism;
import java.util.concurrent.Callable;

// Referenced classes of package crittercism.android:
//            d, k

final class q
    implements Callable
{

    q(k k)
    {
    }

    private static String a()
    {
        String s;
        try
        {
            Crittercism.a().p();
            s = d.a(Crittercism.a().n(), "breadcrumbsFileString");
        }
        catch (Exception exception)
        {
            (new StringBuilder("Exception in setBreadcrumbs.call: ")).append(exception.getClass().getName());
            return null;
        }
        return s;
    }

    public final Object call()
    {
        return a();
    }
}
