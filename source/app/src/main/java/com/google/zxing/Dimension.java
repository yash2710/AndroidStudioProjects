// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;


public final class Dimension
{

    private final int a;
    private final int b;

    public Dimension(int i, int j)
    {
        if (i < 0 || j < 0)
        {
            throw new IllegalArgumentException();
        } else
        {
            a = i;
            b = j;
            return;
        }
    }

    public final boolean equals(Object obj)
    {
        boolean flag = obj instanceof Dimension;
        boolean flag1 = false;
        if (flag)
        {
            Dimension dimension = (Dimension)obj;
            int i = a;
            int j = dimension.a;
            flag1 = false;
            if (i == j)
            {
                int k = b;
                int l = dimension.b;
                flag1 = false;
                if (k == l)
                {
                    flag1 = true;
                }
            }
        }
        return flag1;
    }

    public final int getHeight()
    {
        return b;
    }

    public final int getWidth()
    {
        return a;
    }

    public final int hashCode()
    {
        return 32713 * a + b;
    }

    public final String toString()
    {
        return (new StringBuilder()).append(a).append("x").append(b).toString();
    }
}
