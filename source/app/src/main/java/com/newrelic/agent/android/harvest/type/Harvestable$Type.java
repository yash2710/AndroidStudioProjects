// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;


public final class  extends Enum
{

    public static final a ARRAY;
    public static final a OBJECT;
    public static final a VALUE;
    private static final a a[];

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/newrelic/agent/android/harvest/type/Harvestable$Type, s);
    }

    public static [] values()
    {
        return ([])a.clone();
    }

    static 
    {
        OBJECT = new <init>("OBJECT", 0);
        ARRAY = new <init>("ARRAY", 1);
        VALUE = new <init>("VALUE", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = OBJECT;
        aclone[1] = ARRAY;
        aclone[2] = VALUE;
        a = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
