// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.init.FlipkartApplication;

// Referenced classes of package com.flipkart.android.wearable:
//            f, WearableDataSender

final class e
    implements Runnable
{

    final String a;
    final WearableDataSender b;

    e(WearableDataSender wearabledatasender, String s)
    {
        b = wearabledatasender;
        a = s;
        super();
    }

    public final void run()
    {
        FlipkartApplication.getImageLoader().get(a, new f(this));
    }
}
