// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;


public final class  extends Enum
{

    public static final a CONNECTED;
    public static final a DISABLED;
    public static final a DISCONNECTED;
    public static final a UNINITIALIZED;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/newrelic/agent/android/harvest/Harvester$State, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        UNINITIALIZED = new <init>("UNINITIALIZED", 0);
        DISCONNECTED = new <init>("DISCONNECTED", 1);
        CONNECTED = new <init>("CONNECTED", 2);
        DISABLED = new <init>("DISABLED", 3);
        e_3B_.clone aclone[] = new <init>[4];
        aclone[0] = UNINITIALIZED;
        aclone[1] = DISCONNECTED;
        aclone[2] = CONNECTED;
        aclone[3] = DISABLED;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
