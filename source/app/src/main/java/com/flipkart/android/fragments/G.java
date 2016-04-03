// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.EnhancedListView;

// Referenced classes of package com.flipkart.android.fragments:
//            InAppNotificationFragment, K, H

final class G
    implements com.flipkart.android.customviews.EnhancedListView.OnDismissCallback
{

    final InAppNotificationFragment a;

    G(InAppNotificationFragment inappnotificationfragment)
    {
        a = inappnotificationfragment;
        super();
    }

    public final com.flipkart.android.customviews.EnhancedListView.Undoable onDismiss(EnhancedListView enhancedlistview, int i)
    {
        String s = (String)InAppNotificationFragment.g(a).getItem(i);
        InAppNotificationFragment.g(a).remove(i);
        if (InAppNotificationFragment.g(a).getCount() <= 1 && FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            TrackingHelper.sendInAppNotificationPage("Notification:Nothing_yet");
            InAppNotificationFragment.b(a).setVisibility(0);
            InAppNotificationFragment.c(a).setVisibility(8);
        }
        return new H(this, i, s);
    }
}
