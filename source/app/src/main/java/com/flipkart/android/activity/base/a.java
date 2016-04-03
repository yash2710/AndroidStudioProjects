// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.flipkart.android.activity.base:
//            FlipkartBaseSherlockFragmentActivity

final class a extends BroadcastReceiver
{

    private FlipkartBaseSherlockFragmentActivity a;

    private a(FlipkartBaseSherlockFragmentActivity flipkartbasesherlockfragmentactivity)
    {
        a = flipkartbasesherlockfragmentactivity;
        super();
    }

    a(FlipkartBaseSherlockFragmentActivity flipkartbasesherlockfragmentactivity, byte byte0)
    {
        this(flipkartbasesherlockfragmentactivity);
    }

    public final void onReceive(Context context, Intent intent)
    {
        a.finish();
    }
}
