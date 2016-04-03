// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;


public final class  extends Enum
{

    public static final a LAUNCH;
    public static final a ORDER_STATUS;
    public static final a PRICE_DROP;
    public static final a SANTA_OFFERS;
    public static final a TARGETED_OFFERS;
    public static final a WELCOME;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/flipkart/android/fragments/InAppNotificationFragment$NotificationTypes, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        PRICE_DROP = new <init>("PRICE_DROP", 0);
        ORDER_STATUS = new <init>("ORDER_STATUS", 1);
        TARGETED_OFFERS = new <init>("TARGETED_OFFERS", 2);
        WELCOME = new <init>("WELCOME", 3);
        SANTA_OFFERS = new <init>("SANTA_OFFERS", 4);
        LAUNCH = new <init>("LAUNCH", 5);
        s_3B_.clone aclone[] = new <init>[6];
        aclone[0] = PRICE_DROP;
        aclone[1] = ORDER_STATUS;
        aclone[2] = TARGETED_OFFERS;
        aclone[3] = WELCOME;
        aclone[4] = SANTA_OFFERS;
        aclone[5] = LAUNCH;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
