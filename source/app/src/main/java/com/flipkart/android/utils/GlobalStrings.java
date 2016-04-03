// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;


public final class GlobalStrings extends Enum
{

    private static final GlobalStrings b[];
    public static final GlobalStrings paymentNetBankingStepTitle;
    private final String a;

    private GlobalStrings(String s, int i, String s1)
    {
        super(s, 0);
        a = s1;
    }

    public static GlobalStrings valueOf(String s)
    {
        return (GlobalStrings)Enum.valueOf(com/flipkart/android/utils/GlobalStrings, s);
    }

    public static GlobalStrings[] values()
    {
        return (GlobalStrings[])b.clone();
    }

    public final String getStringVal()
    {
        return a;
    }

    static 
    {
        paymentNetBankingStepTitle = new GlobalStrings("paymentNetBankingStepTitle", 0, "Payment");
        GlobalStrings aglobalstrings[] = new GlobalStrings[1];
        aglobalstrings[0] = paymentNetBankingStepTitle;
        b = aglobalstrings;
    }
}
