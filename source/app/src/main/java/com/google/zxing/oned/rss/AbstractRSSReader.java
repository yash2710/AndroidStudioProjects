// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.oned.rss;

import com.google.zxing.NotFoundException;
import com.google.zxing.oned.OneDReader;

public abstract class AbstractRSSReader extends OneDReader
{

    private final int a[] = new int[4];
    private final int b[] = new int[8];
    private final float c[] = new float[4];
    private final float d[] = new float[4];
    private final int e[] = new int[4];
    private final int f[] = new int[4];

    protected AbstractRSSReader()
    {
        int[] _tmp = b;
        int[] _tmp1 = b;
    }

    protected static int count(int ai[])
    {
        int i = 0;
        int j = ai.length;
        int k = 0;
        for (; i < j; i++)
        {
            k += ai[i];
        }

        return k;
    }

    protected static void decrement(int ai[], float af[])
    {
        int i = 0;
        float f1 = af[0];
        for (int j = 1; j < ai.length; j++)
        {
            if (af[j] < f1)
            {
                f1 = af[j];
                i = j;
            }
        }

        ai[i] = -1 + ai[i];
    }

    protected static void increment(int ai[], float af[])
    {
        int i = 0;
        float f1 = af[0];
        for (int j = 1; j < ai.length; j++)
        {
            if (af[j] > f1)
            {
                f1 = af[j];
                i = j;
            }
        }

        ai[i] = 1 + ai[i];
    }

    protected static boolean isFinderPattern(int ai[])
    {
        int i = ai[0] + ai[1];
        int j = i + ai[2] + ai[3];
        float f1 = (float)i / (float)j;
        if (f1 >= 0.7916667F && f1 <= 0.8928571F)
        {
            int k = 0x7fffffff;
            int l = 0x80000000;
            int i1 = ai.length;
            int j1 = 0;
            while (j1 < i1) 
            {
                int k1 = ai[j1];
                if (k1 > l)
                {
                    l = k1;
                }
                if (k1 >= k)
                {
                    k1 = k;
                }
                j1++;
                k = k1;
            }
            return l < k * 10;
        } else
        {
            return false;
        }
    }

    protected static int parseFinderValue(int ai[], int ai1[][])
    {
        for (int i = 0; i < ai1.length; i++)
        {
            if (patternMatchVariance(ai, ai1[i], 115) < 51)
            {
                return i;
            }
        }

        throw NotFoundException.getNotFoundInstance();
    }

    protected final int[] getDataCharacterCounters()
    {
        return b;
    }

    protected final int[] getDecodeFinderCounters()
    {
        return a;
    }

    protected final int[] getEvenCounts()
    {
        return f;
    }

    protected final float[] getEvenRoundingErrors()
    {
        return d;
    }

    protected final int[] getOddCounts()
    {
        return e;
    }

    protected final float[] getOddRoundingErrors()
    {
        return c;
    }
}
