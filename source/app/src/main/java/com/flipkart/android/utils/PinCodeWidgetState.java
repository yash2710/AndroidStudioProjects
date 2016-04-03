// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class PinCodeWidgetState extends Enum
{

    public static final PinCodeWidgetState AvailableAtPin;
    public static final PinCodeWidgetState EnterPin;
    public static final PinCodeWidgetState None;
    public static final PinCodeWidgetState NotFound;
    public static final PinCodeWidgetState NotFoundShowAll;
    private static final PinCodeWidgetState a[];

    private PinCodeWidgetState(String s, int i)
    {
        super(s, i);
    }

    public static PinCodeWidgetState valueOf(String s)
    {
        return (PinCodeWidgetState)Enum.valueOf(com/flipkart/android/utils/PinCodeWidgetState, s);
    }

    public static PinCodeWidgetState[] values()
    {
        return (PinCodeWidgetState[])a.clone();
    }

    static 
    {
        EnterPin = new PinCodeWidgetState("EnterPin", 0);
        AvailableAtPin = new PinCodeWidgetState("AvailableAtPin", 1);
        NotFound = new PinCodeWidgetState("NotFound", 2);
        NotFoundShowAll = new PinCodeWidgetState("NotFoundShowAll", 3);
        None = new PinCodeWidgetState("None", 4);
        PinCodeWidgetState apincodewidgetstate[] = new PinCodeWidgetState[5];
        apincodewidgetstate[0] = EnterPin;
        apincodewidgetstate[1] = AvailableAtPin;
        apincodewidgetstate[2] = NotFound;
        apincodewidgetstate[3] = NotFoundShowAll;
        apincodewidgetstate[4] = None;
        a = apincodewidgetstate;
    }
}
