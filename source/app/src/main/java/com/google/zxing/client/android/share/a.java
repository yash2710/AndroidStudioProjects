// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.graphics.drawable.Drawable;

final class a
    implements Comparable
{

    private final String a;
    private final String b;
    private final Drawable c;

    a(String s, String s1, Drawable drawable)
    {
        a = s;
        b = s1;
        c = drawable;
    }

    final String a()
    {
        return a;
    }

    final Drawable b()
    {
        return c;
    }

    public final int compareTo(a a1)
    {
        return b.compareTo(a1.b);
    }

    public final volatile int compareTo(Object obj)
    {
        return compareTo((a)obj);
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof a))
        {
            return false;
        } else
        {
            a a1 = (a)obj;
            return b.equals(a1.b);
        }
    }

    public final int hashCode()
    {
        return b.hashCode();
    }

    public final String toString()
    {
        return b;
    }
}
