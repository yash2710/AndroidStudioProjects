// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.fragments.model.InAppNotificationModel;
import com.flipkart.android.utils.InAppNotificationUtils;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            G, InAppNotificationFragment, K

final class H extends com.flipkart.android.customviews.EnhancedListView.Undoable
{

    private int a;
    private String b;
    private G c;

    H(G g, int i, String s)
    {
        c = g;
        a = i;
        b = s;
        super();
    }

    public final void discard()
    {
        InAppNotificationModel inappnotificationmodel = (InAppNotificationModel)InAppNotificationFragment.d(c.a).get(b);
        if (inappnotificationmodel != null)
        {
            TrackingHelper.sendInAppNotificationVariablesIm((new StringBuilder("nf_dismiss_")).append(inappnotificationmodel.getNotificationType().toLowerCase()).toString());
            if (inappnotificationmodel.getNotificationType().equals("UPGRADE_APP"))
            {
                FlipkartPreferenceManager.instance().saveIsShowAppUpgradeNotification(Boolean.valueOf(false));
            } else
            {
                InAppNotificationUtils.deleteInAppNotification(inappnotificationmodel.getNotificationId(), inappnotificationmodel.getNotificationType());
            }
            InAppNotificationFragment.d(c.a).remove(b);
        }
    }

    public final void undo()
    {
        TrackingHelper.sendInAppNotificationPage("Notification");
        InAppNotificationFragment.b(c.a).setVisibility(8);
        InAppNotificationFragment.c(c.a).setVisibility(0);
        InAppNotificationFragment.g(c.a).insert(a, b);
    }
}
