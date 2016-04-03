// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.decoder;

import java.util.Formatter;

// Referenced classes of package com.google.zxing.pdf417.decoder:
//            c, d

class i
{

    private final c a;
    private final d b[];

    i(c c1)
    {
        a = new c(c1);
        b = new d[1 + (c1.d() - c1.c())];
    }

    final c a()
    {
        return a;
    }

    final d a(int j)
    {
        d d1 = c(j);
        if (d1 == null) goto _L2; else goto _L1
_L1:
        return d1;
_L2:
        int k = 1;
_L7:
        if (k >= 5) goto _L4; else goto _L3
_L3:
        int l = b(j) - k;
        if (l < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        d1 = b[l];
        if (d1 != null) goto _L1; else goto _L5
_L5:
        int i1 = k + b(j);
        if (i1 >= b.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        d1 = b[i1];
        if (d1 != null) goto _L1; else goto _L6
_L6:
        k++;
          goto _L7
_L4:
        return null;
    }

    final void a(int j, d d1)
    {
        b[b(j)] = d1;
    }

    final int b(int j)
    {
        return j - a.c();
    }

    final d[] b()
    {
        return b;
    }

    final d c(int j)
    {
        return b[b(j)];
    }

    public String toString()
    {
        Formatter formatter = new Formatter();
        d ad[] = b;
        int j = ad.length;
        int k = 0;
        int l = 0;
        while (k < j) 
        {
            d d1 = ad[k];
            int i1;
            if (d1 == null)
            {
                Object aobj1[] = new Object[1];
                i1 = l + 1;
                aobj1[0] = Integer.valueOf(l);
                formatter.format("%3d:    |   \n", aobj1);
            } else
            {
                Object aobj[] = new Object[3];
                i1 = l + 1;
                aobj[0] = Integer.valueOf(l);
                aobj[1] = Integer.valueOf(d1.h());
                aobj[2] = Integer.valueOf(d1.g());
                formatter.format("%3d: %3d|%3d\n", aobj);
            }
            k++;
            l = i1;
        }
        String s = formatter.toString();
        formatter.close();
        return s;
    }
}
