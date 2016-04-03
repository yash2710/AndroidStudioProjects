// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.util;


class ContainerHelpers
{

    static final int EMPTY_INTS[] = new int[0];
    static final long EMPTY_LONGS[] = new long[0];
    static final Object EMPTY_OBJECTS[] = new Object[0];

    ContainerHelpers()
    {
    }

    static int binarySearch(int ai[], int i, int j)
    {
        int i1;
label0:
        {
            int k = i - 1;
            int l;
            for (l = 0; l <= k;)
            {
                i1 = l + k >>> 1;
                int j1 = ai[i1];
                if (j1 < j)
                {
                    l = i1 + 1;
                } else
                {
                    if (j1 <= j)
                    {
                        break label0;
                    }
                    k = i1 - 1;
                }
            }

            i1 = ~l;
        }
        return i1;
    }

    static int binarySearch(long al[], int i, long l)
    {
        int i1;
label0:
        {
            int j = i - 1;
            int k;
            for (k = 0; k <= j;)
            {
                i1 = k + j >>> 1;
                long l1 = al[i1];
                if (l1 < l)
                {
                    k = i1 + 1;
                } else
                {
                    if (l1 <= l)
                    {
                        break label0;
                    }
                    j = i1 - 1;
                }
            }

            i1 = ~k;
        }
        return i1;
    }

    public static boolean equal(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    public static int idealByteArraySize(int i)
    {
        int j = 4;
        do
        {
label0:
            {
                if (j < 32)
                {
                    if (i > -12 + (1 << j))
                    {
                        break label0;
                    }
                    i = -12 + (1 << j);
                }
                return i;
            }
            j++;
        } while (true);
    }

    public static int idealIntArraySize(int i)
    {
        return idealByteArraySize(i << 2) / 4;
    }

    public static int idealLongArraySize(int i)
    {
        return idealByteArraySize(i << 3) / 8;
    }

}
