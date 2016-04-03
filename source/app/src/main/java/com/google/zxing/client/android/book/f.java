// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;


final class f
{

    private static String a = null;
    private final String b;
    private final String c;
    private final String d;
    private final boolean e;

    f(String s, String s1, String s2, boolean flag)
    {
        b = s;
        c = s1;
        d = s2;
        e = flag;
    }

    public static String getQuery()
    {
        return a;
    }

    public static void setQuery(String s)
    {
        a = s;
    }

    public final String getPageId()
    {
        return b;
    }

    public final String getPageNumber()
    {
        return c;
    }

    public final String getSnippet()
    {
        return d;
    }

    public final boolean getValidSnippet()
    {
        return e;
    }

}
