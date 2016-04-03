// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;


public final class A extends Enum
{

    public static final a COLLAPSED_POPUP;
    public static final a MULTILEVEL_POPUP;
    public static final a SINGLE_POPUP;
    private static final a a[];

    public static A valueOf(String s)
    {
        return (A)Enum.valueOf(com/flipkart/android/customviews/EnhancedListView$UndoStyle, s);
    }

    public static A[] values()
    {
        return (A[])a.clone();
    }

    static 
    {
        SINGLE_POPUP = new <init>("SINGLE_POPUP", 0);
        MULTILEVEL_POPUP = new <init>("MULTILEVEL_POPUP", 1);
        COLLAPSED_POPUP = new <init>("COLLAPSED_POPUP", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = SINGLE_POPUP;
        aclone[1] = MULTILEVEL_POPUP;
        aclone[2] = COLLAPSED_POPUP;
        a = aclone;
    }

    private A(String s, int i)
    {
        super(s, i);
    }
}
