// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android:
//            SplashActivity

public class a extends BroadcastReceiver
{

    private SplashActivity a;

    public void onReceive(Context context, Intent intent)
    {
        boolean flag1 = intent.getBooleanExtra("updateLogo", false);
        boolean flag = flag1;
_L2:
        if (!flag && FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            SplashActivity.h(a);
        }
        return;
        Exception exception;
        exception;
        flag = false;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public (SplashActivity splashactivity)
    {
        a = splashactivity;
        super();
    }
}
