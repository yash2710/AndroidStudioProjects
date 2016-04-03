// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

final class H
{

    private final byte a[];
    private volatile int b;

    private H(byte abyte0[])
    {
        b = 0;
        a = abyte0;
    }

    public static H a(String s)
    {
        H h;
        try
        {
            h = new H(s.getBytes("UTF-8"));
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("UTF-8 not supported.", unsupportedencodingexception);
        }
        return h;
    }

    public static H a(byte abyte0[], int i, int j)
    {
        byte abyte1[] = new byte[j];
        System.arraycopy(abyte0, 0, abyte1, 0, j);
        return new H(abyte1);
    }

    public final int a()
    {
        return a.length;
    }

    public final void a(byte abyte0[], int i, int j, int k)
    {
        System.arraycopy(a, i, abyte0, j, k);
    }

    public final InputStream b()
    {
        return new ByteArrayInputStream(a);
    }

    public final boolean equals(Object obj)
    {
        if (obj != this)
        {
            if (!(obj instanceof H))
            {
                return false;
            }
            H h = (H)obj;
            int i = a.length;
            if (i != h.a.length)
            {
                return false;
            }
            byte abyte0[] = a;
            byte abyte1[] = h.a;
            int j = 0;
            while (j < i) 
            {
                if (abyte0[j] != abyte1[j])
                {
                    return false;
                }
                j++;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        int i = b;
        if (i == 0)
        {
            byte abyte0[] = a;
            int j = a.length;
            int k = 0;
            int l;
            for (i = j; k < j; i = l)
            {
                l = i * 31 + abyte0[k];
                k++;
            }

            if (i == 0)
            {
                i = 1;
            }
            b = i;
        }
        return i;
    }

    static 
    {
        new H(new byte[0]);
    }
}
