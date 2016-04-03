// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss;


public class DataCharacter
{

    private final int a;
    private final int b;

    public DataCharacter(int i, int j)
    {
        a = i;
        b = j;
    }

    public final boolean equals(Object obj)
    {
        DataCharacter datacharacter;
        if (obj instanceof DataCharacter)
        {
            if (a == (datacharacter = (DataCharacter)obj).a && b == datacharacter.b)
            {
                return true;
            }
        }
        return false;
    }

    public final int getChecksumPortion()
    {
        return b;
    }

    public final int getValue()
    {
        return a;
    }

    public final int hashCode()
    {
        return a ^ b;
    }

    public final String toString()
    {
        return (new StringBuilder()).append(a).append("(").append(b).append(')').toString();
    }
}
