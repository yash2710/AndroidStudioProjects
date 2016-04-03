// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.stats;


final class a extends Enum
{

    public static final a a;
    public static final a b;
    private static final a c[];

    private a(String s, int i)
    {
        super(s, i);
    }

    public static a valueOf(String s)
    {
        return (a)Enum.valueOf(com/newrelic/agent/android/stats/a, s);
    }

    public static a[] values()
    {
        return (a[])c.clone();
    }

    static 
    {
        a = new a("STOPPED", 0);
        b = new a("STARTED", 1);
        a aa[] = new a[2];
        aa[0] = a;
        aa[1] = b;
        c = aa;
    }
}
