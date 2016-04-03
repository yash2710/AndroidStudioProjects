// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.flipkart.android.config.FlipkartPreferenceManager;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

public class a extends BroadcastReceiver
{

    private HomeFragmentHolderActivity a;

    public void onReceive(Context context, Intent intent)
    {
        try
        {
            intent.getBooleanExtra("updateLogo", false);
        }
        catch (Exception exception) { }
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            a.performLoggedInActions(true);
            return;
        } else
        {
            HomeFragmentHolderActivity.f(a);
            return;
        }
    }

    public Q(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }
}
