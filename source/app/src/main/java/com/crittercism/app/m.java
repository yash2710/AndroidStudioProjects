// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crittercism.app;

import android.util.Log;

// Referenced classes of package com.crittercism.app:
//            Crittercism

final class m
    implements Runnable
{

    private String a;

    public m(String s)
    {
        a = "";
        a = s;
    }

    public final void run()
    {
        try
        {
            Crittercism.a(Crittercism.a(), a);
            return;
        }
        catch (Exception exception)
        {
            Log.w("Crittercism", "Failed to leave breadcrumb.  Please contact us at support@crittercism.com.");
        }
    }
}
