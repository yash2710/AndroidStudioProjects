// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.wifi;


final class a extends Enum
{

    public static final a a;
    public static final a b;
    public static final a c;
    private static final a d[];

    private a(String s, int i)
    {
        super(s, i);
    }

    static a a(String s)
    {
        if (s == null)
        {
            return c;
        }
        if ("WPA".equals(s))
        {
            return b;
        }
        if ("WEP".equals(s))
        {
            return a;
        }
        if ("nopass".equals(s))
        {
            return c;
        } else
        {
            throw new IllegalArgumentException(s);
        }
    }

    public static a valueOf(String s)
    {
        return (a)Enum.valueOf(com/google/zxing/client/android/wifi/a, s);
    }

    public static a[] values()
    {
        return (a[])d.clone();
    }

    static 
    {
        a = new a("WEP", 0);
        b = new a("WPA", 1);
        c = new a("NO_PASSWORD", 2);
        a aa[] = new a[3];
        aa[0] = a;
        aa[1] = b;
        aa[2] = c;
        d = aa;
    }
}
