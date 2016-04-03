// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.NotificationVDataHandler;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.notification:
//            f

public class GcmUtils
{

    public GcmUtils()
    {
    }

    public static void sendRegistrationIdToBackend(String s)
    {
        f f1 = new f();
        String s1 = FlipkartPreferenceManager.instance().getNotificationRegId();
        if (!StringUtils.isNullOrEmpty(s1))
        {
            f1.sendNotificationRegId(s1, s);
        }
    }
}
