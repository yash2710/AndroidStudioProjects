// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.InAppNotificationDataHandler;

// Referenced classes of package com.flipkart.android.utils:
//            h, i, f, g, 
//            IInAppNotificationListener

public class InAppNotificationUtils
{

    public InAppNotificationUtils()
    {
    }

    public static void deleteInAppNotification(String s, String s1)
    {
        (new h()).deleteInAppNotification(s, s1);
        FlipkartPreferenceManager.instance().saveInAppUnreadCount(0);
    }

    public static void getUnReadCount(IInAppNotificationListener iinappnotificationlistener)
    {
        (new i(iinappnotificationlistener)).getUnReadCount();
    }

    public static void markAllRead(Activity activity)
    {
        (new f(activity)).markAllRead();
        FlipkartPreferenceManager.instance().saveInAppUnreadCount(0);
    }

    public static void markAsRead(String s, String s1)
    {
        (new g()).markAsRead(s, s1);
    }
}
