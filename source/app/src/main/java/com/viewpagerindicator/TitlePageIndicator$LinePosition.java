// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;


public final class value extends Enum
{

    public static final a Bottom;
    public static final a Top;
    private static final a a[];
    public final int value;

    public static value fromValue(int i)
    {
        value avalue[] = values();
        int j = avalue.length;
        for (int k = 0; k < j; k++)
        {
            value value1 = avalue[k];
            if (value1.value == i)
            {
                return value1;
            }
        }

        return null;
    }

    public static value valueOf(String s)
    {
        return (value)Enum.valueOf(com/viewpagerindicator/TitlePageIndicator$LinePosition, s);
    }

    public static value[] values()
    {
        return (value[])a.clone();
    }

    static 
    {
        Bottom = new <init>("Bottom", 0, 0);
        Top = new <init>("Top", 1, 1);
        n_3B_.clone aclone[] = new <init>[2];
        aclone[0] = Bottom;
        aclone[1] = Top;
        a = aclone;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        value = j;
    }
}
