// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.crashlytics.android:
//            Z

final class m extends BroadcastReceiver
{

    private Z a;

    m(Z z)
    {
        a = z;
        super();
    }

    public final void onReceive(Context context, Intent intent)
    {
        Z.a(a, false);
    }
}
