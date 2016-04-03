// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class NullResultWidgetState extends Enum
{

    public static final NullResultWidgetState None;
    public static final NullResultWidgetState ShowBarCodeError;
    public static final NullResultWidgetState ShowContinueShopping;
    public static final NullResultWidgetState ShowLargeError;
    public static final NullResultWidgetState ShowWrongQuery;
    private static final NullResultWidgetState a[];

    private NullResultWidgetState(String s, int i)
    {
        super(s, i);
    }

    public static NullResultWidgetState valueOf(String s)
    {
        return (NullResultWidgetState)Enum.valueOf(com/flipkart/android/utils/NullResultWidgetState, s);
    }

    public static NullResultWidgetState[] values()
    {
        return (NullResultWidgetState[])a.clone();
    }

    static 
    {
        ShowContinueShopping = new NullResultWidgetState("ShowContinueShopping", 0);
        ShowWrongQuery = new NullResultWidgetState("ShowWrongQuery", 1);
        ShowLargeError = new NullResultWidgetState("ShowLargeError", 2);
        ShowBarCodeError = new NullResultWidgetState("ShowBarCodeError", 3);
        None = new NullResultWidgetState("None", 4);
        NullResultWidgetState anullresultwidgetstate[] = new NullResultWidgetState[5];
        anullresultwidgetstate[0] = ShowContinueShopping;
        anullresultwidgetstate[1] = ShowWrongQuery;
        anullresultwidgetstate[2] = ShowLargeError;
        anullresultwidgetstate[3] = ShowBarCodeError;
        anullresultwidgetstate[4] = None;
        a = anullresultwidgetstate;
    }
}
