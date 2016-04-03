// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.cast;

import com.google.android.gms.internal.gi;
import java.util.Locale;

// Referenced classes of package com.google.android.gms.cast:
//            LaunchOptions

public final class As
{

    private LaunchOptions As;

    public final LaunchOptions build()
    {
        return As;
    }

    public final As setLocale(Locale locale)
    {
        As.setLanguage(gi.b(locale));
        return this;
    }

    public final age setRelaunchIfRunning(boolean flag)
    {
        As.setRelaunchIfRunning(flag);
        return this;
    }

    public ()
    {
        As = new LaunchOptions();
    }
}
