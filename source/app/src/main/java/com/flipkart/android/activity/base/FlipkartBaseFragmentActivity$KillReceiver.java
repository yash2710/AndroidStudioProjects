// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// Referenced classes of package com.flipkart.android.activity.base:
//            FlipkartBaseFragmentActivity

public final class a extends BroadcastReceiver
{

    private FlipkartBaseFragmentActivity a;

    public final void onReceive(Context context, Intent intent)
    {
        a.finish();
    }

    public (FlipkartBaseFragmentActivity flipkartbasefragmentactivity)
    {
        a = flipkartbasefragmentactivity;
        super();
    }
}
