// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification.data;


public final class NotificationType extends Enum
{

    public static final NotificationType APP_ACTION;
    public static final NotificationType IN_APP_BLIP;
    public static final NotificationType IN_APP_POPUP;
    public static final NotificationType PULLDOWN_NOTIFICATION;
    private static final NotificationType a[];

    private NotificationType(String s, int i)
    {
        super(s, i);
    }

    public static NotificationType getType(int i)
    {
        NotificationType notificationtype = PULLDOWN_NOTIFICATION;
        switch (i)
        {
        default:
            return notificationtype;

        case 1: // '\001'
            return PULLDOWN_NOTIFICATION;

        case 2: // '\002'
            return IN_APP_POPUP;

        case 3: // '\003'
            return IN_APP_BLIP;

        case 4: // '\004'
            return APP_ACTION;
        }
    }

    public static NotificationType valueOf(String s)
    {
        return (NotificationType)Enum.valueOf(com/flipkart/android/notification/data/NotificationType, s);
    }

    public static NotificationType[] values()
    {
        return (NotificationType[])a.clone();
    }

    static 
    {
        PULLDOWN_NOTIFICATION = new NotificationType("PULLDOWN_NOTIFICATION", 0);
        IN_APP_POPUP = new NotificationType("IN_APP_POPUP", 1);
        IN_APP_BLIP = new NotificationType("IN_APP_BLIP", 2);
        APP_ACTION = new NotificationType("APP_ACTION", 3);
        NotificationType anotificationtype[] = new NotificationType[4];
        anotificationtype[0] = PULLDOWN_NOTIFICATION;
        anotificationtype[1] = IN_APP_POPUP;
        anotificationtype[2] = IN_APP_BLIP;
        anotificationtype[3] = APP_ACTION;
        a = anotificationtype;
    }
}
