// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.app.Activity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.fragments.MultiWidgetFragment;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class e extends ActionBarDrawerToggle
{

    private HomeFragmentHolderActivity a;

    e(HomeFragmentHolderActivity homefragmentholderactivity, Activity activity, DrawerLayout drawerlayout, int i, int j, int k)
    {
        a = homefragmentholderactivity;
        super(activity, drawerlayout, 0x7f0200ff, 0x7f0d0094, 0x7f0d002b);
    }

    public final void onDrawerClosed(View view)
    {
        super.onDrawerClosed(view);
        a.setActionBarBgAlpha(HomeFragmentHolderActivity.a(a));
        HomeFragmentHolderActivity.a(a, -1);
    }

    public final void onDrawerOpened(View view)
    {
        android.support.v4.app.Fragment fragment = a.getSupportFragmentManager().findFragmentByTag("flyout");
        if (fragment != null)
        {
            ((MultiWidgetFragment)fragment).setCurrentPosition();
        }
        super.onDrawerOpened(view);
        TrackingHelper.sendFlyoutClicked();
        a.setActionBarBgAlpha(255);
        if (fragment != null)
        {
            ((MultiWidgetFragment)fragment).setScrollPosition();
        }
    }

    public final void onDrawerSlide(View view, float f)
    {
        super.onDrawerSlide(view, f);
        if (HomeFragmentHolderActivity.a(a) == -1)
        {
            HomeFragmentHolderActivity.a(a, a.getActionBarBgAlpha());
        }
        if (HomeFragmentHolderActivity.b(a) instanceof MultiWidgetFragment)
        {
            double d = 255F * f;
            if ((double)HomeFragmentHolderActivity.a(a) < d)
            {
                a.setActionBarBgAlpha((int)d);
            }
        }
    }
}
