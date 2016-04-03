// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.notification;


public final class NotificationReceiptState extends Enum
{

    public static final NotificationReceiptState DISMISSED;
    public static final NotificationReceiptState READ;
    public static final NotificationReceiptState SHOWN;
    private static final NotificationReceiptState a[];

    private NotificationReceiptState(String s, int i)
    {
        super(s, i);
    }

    public static NotificationReceiptState valueOf(String s)
    {
        return (NotificationReceiptState)Enum.valueOf(com/flipkart/android/notification/NotificationReceiptState, s);
    }

    public static NotificationReceiptState[] values()
    {
        return (NotificationReceiptState[])a.clone();
    }

    static 
    {
        SHOWN = new NotificationReceiptState("SHOWN", 0);
        READ = new NotificationReceiptState("READ", 1);
        DISMISSED = new NotificationReceiptState("DISMISSED", 2);
        NotificationReceiptState anotificationreceiptstate[] = new NotificationReceiptState[3];
        anotificationreceiptstate[0] = SHOWN;
        anotificationreceiptstate[1] = READ;
        anotificationreceiptstate[2] = DISMISSED;
        a = anotificationreceiptstate;
    }
}
