// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;


public final class EntryChannel extends Enum
{

    public static final EntryChannel AppWidget;
    public static final EntryChannel DeepLinking;
    public static final EntryChannel Direct;
    public static final EntryChannel Notification;
    private static final EntryChannel a[];

    private EntryChannel(String s, int i)
    {
        super(s, i);
    }

    public static EntryChannel valueOf(String s)
    {
        return (EntryChannel)Enum.valueOf(com/flipkart/android/analytics/EntryChannel, s);
    }

    public static EntryChannel[] values()
    {
        return (EntryChannel[])a.clone();
    }

    static 
    {
        Direct = new EntryChannel("Direct", 0);
        AppWidget = new EntryChannel("AppWidget", 1);
        Notification = new EntryChannel("Notification", 2);
        DeepLinking = new EntryChannel("DeepLinking", 3);
        EntryChannel aentrychannel[] = new EntryChannel[4];
        aentrychannel[0] = Direct;
        aentrychannel[1] = AppWidget;
        aentrychannel[2] = Notification;
        aentrychannel[3] = DeepLinking;
        a = aentrychannel;
    }
}
