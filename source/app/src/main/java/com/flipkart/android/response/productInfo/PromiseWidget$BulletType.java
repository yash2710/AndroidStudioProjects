// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;


public final class bulletType extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES CROSS;
    public static final .VALUES LINE;
    public static final .VALUES NONE;
    public static final .VALUES TICK;
    private final String bulletType;

    public static bulletType valueOf(String s)
    {
        return (bulletType)Enum.valueOf(com/flipkart/android/response/productInfo/PromiseWidget$BulletType, s);
    }

    public static bulletType[] values()
    {
        return (bulletType[])$VALUES.clone();
    }

    public final String toString()
    {
        return bulletType;
    }

    static 
    {
        TICK = new <init>("TICK", 0, "TICK");
        CROSS = new <init>("CROSS", 1, "CROSS");
        LINE = new <init>("LINE", 2, "LINE");
        NONE = new <init>("NONE", 3, "NONE");
        bulletType abullettype[] = new <init>[4];
        abullettype[0] = TICK;
        abullettype[1] = CROSS;
        abullettype[2] = LINE;
        abullettype[3] = NONE;
        $VALUES = abullettype;
    }

    private (String s, int i, String s1)
    {
        super(s, i);
        bulletType = s1;
    }
}
