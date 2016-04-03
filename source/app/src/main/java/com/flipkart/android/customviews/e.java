// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.support.v4.app.FragmentManager;
import android.view.View;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;

// Referenced classes of package com.flipkart.android.customviews:
//            ActionBarView

final class e
    implements android.view.View.OnClickListener
{

    e()
    {
    }

    public final void onClick(View view)
    {
        if (ActionBarView.a().getSupportFragmentManager().getBackStackEntryCount() == 0)
        {
            ActionBarView.a().toggleDrawerLayout();
            return;
        } else
        {
            TrackingHelper.sendUpCarrotClicked();
            ActionBarView.a().onBackPressed();
            return;
        }
    }
}
