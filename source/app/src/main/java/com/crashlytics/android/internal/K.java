// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;


// Referenced classes of package com.crashlytics.android.internal:
//            ab, j, G

final class K
    implements Runnable
{

    private final j a;
    private final G b;

    public K(j j1, G g)
    {
        a = j1;
        b = g;
    }

    public final void run()
    {
        try
        {
            ab.c("Performing time based analytics file roll over.");
            if (!a.a())
            {
                b.c();
            }
            return;
        }
        catch (Exception exception)
        {
            ab.d("Crashlytics failed to roll over session analytics file");
        }
    }
}
