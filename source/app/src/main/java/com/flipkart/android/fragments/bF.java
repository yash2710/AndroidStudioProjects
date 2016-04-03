// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;


final class bF extends Enum
{

    public static final bF a;
    public static final bF b;
    public static final bF c;
    public static final bF d;
    private static final bF e[];

    private bF(String s, int i)
    {
        super(s, i);
    }

    public static bF valueOf(String s)
    {
        return (bF)Enum.valueOf(com/flipkart/android/fragments/bF, s);
    }

    public static bF[] values()
    {
        return (bF[])e.clone();
    }

    static 
    {
        a = new bF("PageLoadStart", 0);
        b = new bF("PageLoadComplete", 1);
        c = new bF("Finish", 2);
        d = new bF("Error", 3);
        bF abf[] = new bF[4];
        abf[0] = a;
        abf[1] = b;
        abf[2] = c;
        abf[3] = d;
        e = abf;
    }
}
