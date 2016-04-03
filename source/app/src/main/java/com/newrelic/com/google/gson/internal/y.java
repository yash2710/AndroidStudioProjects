// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.com.google.gson.internal;


final class y
    implements CharSequence
{

    char a[];

    y()
    {
    }

    public final char charAt(int i)
    {
        return a[i];
    }

    public final int length()
    {
        return a.length;
    }

    public final CharSequence subSequence(int i, int j)
    {
        return new String(a, i, j - i);
    }
}
