// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss.expanded;

import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;

final class a
{

    private final DataCharacter a;
    private final DataCharacter b;
    private final FinderPattern c;

    a(DataCharacter datacharacter, DataCharacter datacharacter1, FinderPattern finderpattern, boolean flag)
    {
        a = datacharacter;
        b = datacharacter1;
        c = finderpattern;
    }

    private static int a(Object obj)
    {
        if (obj == null)
        {
            return 0;
        } else
        {
            return obj.hashCode();
        }
    }

    private static boolean a(Object obj, Object obj1)
    {
        if (obj == null)
        {
            return obj1 == null;
        } else
        {
            return obj.equals(obj1);
        }
    }

    final DataCharacter a()
    {
        return a;
    }

    final DataCharacter b()
    {
        return b;
    }

    final FinderPattern c()
    {
        return c;
    }

    public final boolean equals(Object obj)
    {
        a a1;
        if (obj instanceof a)
        {
            if (a(a, (a1 = (a)obj).a) && a(b, a1.b) && a(c, a1.c))
            {
                return true;
            }
        }
        return false;
    }

    public final int hashCode()
    {
        return a(a) ^ a(b) ^ a(c);
    }

    public final boolean mustBeLast()
    {
        return b == null;
    }

    public final String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder("[ ")).append(a).append(" , ").append(b).append(" : ");
        Object obj;
        if (c == null)
        {
            obj = "null";
        } else
        {
            obj = Integer.valueOf(c.getValue());
        }
        return stringbuilder.append(obj).append(" ]").toString();
    }
}
