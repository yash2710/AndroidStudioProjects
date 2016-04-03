// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;


final class D extends Enum
{

    public static final D a;
    public static final D b;
    public static final D c;
    public static final D d;
    public static final D e;
    private static final D f[];

    private D(String s, int i)
    {
        super(s, i);
    }

    public static D valueOf(String s)
    {
        return (D)Enum.valueOf(com/flipkart/android/customviews/D, s);
    }

    public static D[] values()
    {
        return (D[])f.clone();
    }

    static 
    {
        a = new D("ABOUT_TO_ANIMATE", 0);
        b = new D("ANIMATING", 1);
        c = new D("READY", 2);
        d = new D("TRACKING", 3);
        e = new D("FLYING", 4);
        D ad[] = new D[5];
        ad[0] = a;
        ad[1] = b;
        ad[2] = c;
        ad[3] = d;
        ad[4] = e;
        f = ad;
    }
}
