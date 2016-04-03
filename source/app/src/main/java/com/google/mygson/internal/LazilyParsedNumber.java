// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.mygson.internal;

import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number
{

    private final String a;

    public LazilyParsedNumber(String s)
    {
        a = s;
    }

    public final double doubleValue()
    {
        return Double.parseDouble(a);
    }

    public final float floatValue()
    {
        return Float.parseFloat(a);
    }

    public final int intValue()
    {
        int i;
        try
        {
            i = Integer.parseInt(a);
        }
        catch (NumberFormatException numberformatexception)
        {
            long l;
            try
            {
                l = Long.parseLong(a);
            }
            catch (NumberFormatException numberformatexception1)
            {
                return (new BigDecimal(a)).intValue();
            }
            return (int)l;
        }
        return i;
    }

    public final long longValue()
    {
        long l;
        try
        {
            l = Long.parseLong(a);
        }
        catch (NumberFormatException numberformatexception)
        {
            return (new BigDecimal(a)).longValue();
        }
        return l;
    }

    public final String toString()
    {
        return a;
    }
}
