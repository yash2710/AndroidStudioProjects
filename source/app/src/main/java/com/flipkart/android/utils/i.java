// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.datahandler.InAppNotificationDataHandler;
import com.flipkart.android.response.inAppNotification.InAppNotificationResponse;

// Referenced classes of package com.flipkart.android.utils:
//            IInAppNotificationListener

final class i extends InAppNotificationDataHandler
{

    private IInAppNotificationListener a;

    i(IInAppNotificationListener iinappnotificationlistener)
    {
        a = iinappnotificationlistener;
        super();
    }

    public final void resultReceived(InAppNotificationResponse inappnotificationresponse, boolean flag)
    {
        if (inappnotificationresponse != null)
        {
            a.unReadCount(inappnotificationresponse.getUnreadCount());
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((InAppNotificationResponse)obj, flag);
    }
}
