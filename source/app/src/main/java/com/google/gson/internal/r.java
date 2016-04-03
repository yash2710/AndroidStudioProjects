// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.gson.internal;


// Referenced classes of package com.google.gson.internal:
//            y

class r
{

    private y a;
    private int b;
    private int c;
    private int d;

    r()
    {
    }

    final y a()
    {
        y y1 = a;
        if (y1.a != null)
        {
            throw new IllegalStateException();
        } else
        {
            return y1;
        }
    }

    final void a(int i)
    {
        b = (-1 + (Integer.highestOneBit(i) << 1)) - i;
        d = 0;
        c = 0;
        a = null;
    }

    final void a(y y1)
    {
        y1.c = null;
        y1.a = null;
        y1.b = null;
        y1.i = 1;
        if (b > 0 && (1 & d) == 0)
        {
            d = 1 + d;
            b = -1 + b;
            c = 1 + c;
        }
        y1.a = a;
        a = y1;
        d = 1 + d;
        if (b > 0 && (1 & d) == 0)
        {
            d = 1 + d;
            b = -1 + b;
            c = 1 + c;
        }
        int i = 4;
        while ((d & i - 1) == i - 1) 
        {
            if (c == 0)
            {
                y y4 = a;
                y y5 = y4.a;
                y y6 = y5.a;
                y5.a = y6.a;
                a = y5;
                y5.b = y6;
                y5.c = y4;
                y5.i = 1 + y4.i;
                y6.a = y5;
                y4.a = y5;
            } else
            if (c == 1)
            {
                y y2 = a;
                y y3 = y2.a;
                a = y3;
                y3.c = y2;
                y3.i = 1 + y2.i;
                y2.a = y3;
                c = 0;
            } else
            if (c == 2)
            {
                c = 0;
            }
            i <<= 1;
        }
    }
}
