// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook.widget;


public final class minVersion extends Enum
    implements minVersion
{

    private static final .VALUES $VALUES[];
    public static final .VALUES SHARE_DIALOG;
    private int minVersion;

    public static minVersion valueOf(String s)
    {
        return (minVersion)Enum.valueOf(com/facebook/widget/FacebookDialog$ShareDialogFeature, s);
    }

    public static minVersion[] values()
    {
        return (minVersion[])$VALUES.clone();
    }

    public final int getMinVersion()
    {
        return minVersion;
    }

    static 
    {
        SHARE_DIALOG = new <init>("SHARE_DIALOG", 0, 0x1332b3a);
        minVersion aminversion[] = new <init>[1];
        aminversion[0] = SHARE_DIALOG;
        $VALUES = aminversion;
    }

    private (String s, int i, int j)
    {
        super(s, i);
        minVersion = j;
    }
}
