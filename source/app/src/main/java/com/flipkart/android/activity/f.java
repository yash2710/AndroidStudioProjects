// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.utils.IInAppNotificationListener;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class f
    implements IInAppNotificationListener
{

    private HomeFragmentHolderActivity a;

    f(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    public final void unReadCount(int i)
    {
        TextView textview;
        if (HomeFragmentHolderActivity.i(a) && !FlipkartPreferenceManager.instance().isAppUpgradeNotificationShown().booleanValue())
        {
            i++;
        }
        textview = (TextView)a.getSupportActionBar().getCustomView().findViewById(0x7f0a0123);
        int k = Integer.parseInt(textview.getText().toString());
        int j = k;
_L2:
        FlipkartPreferenceManager.instance().saveInAppUnreadCount(i);
        HomeFragmentHolderActivity.a(a, j, i, textview);
        return;
        Exception exception;
        exception;
        j = 0;
        if (true) goto _L2; else goto _L1
_L1:
    }
}
