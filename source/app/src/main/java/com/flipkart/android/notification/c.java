// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;

import com.flipkart.android.datahandler.NotificationVDataHandler;
import com.flipkart.android.response.notification.NotificationResponse;

final class c extends NotificationVDataHandler
{

    c()
    {
    }

    public final void resultReceived(NotificationResponse notificationresponse, boolean flag)
    {
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((NotificationResponse)obj, flag);
    }
}
