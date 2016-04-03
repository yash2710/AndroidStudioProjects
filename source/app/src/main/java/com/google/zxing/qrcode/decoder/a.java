// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing.qrcode.decoder:
//            m, Version, c

class a
{

    private final BitMatrix a;
    private Version b;
    private m c;

    a(BitMatrix bitmatrix)
    {
        int i = bitmatrix.getHeight();
        if (i < 21 || (i & 3) != 1)
        {
            throw FormatException.getFormatInstance();
        } else
        {
            a = bitmatrix;
            return;
        }
    }

    private int a(int i, int j, int k)
    {
        if (a.get(i, j))
        {
            return 1 | k << 1;
        } else
        {
            return k << 1;
        }
    }

    final m a()
    {
        int i = 0;
        if (c != null)
        {
            return c;
        }
        int j = 0;
        int k = 0;
        for (; j < 6; j++)
        {
            k = a(j, 8, k);
        }

        int l = a(8, 7, a(8, 8, a(7, 8, k)));
        for (int i1 = 5; i1 >= 0; i1--)
        {
            l = a(8, i1, l);
        }

        int j1 = a.getHeight();
        int k1 = j1 - 7;
        for (int l1 = j1 - 1; l1 >= k1; l1--)
        {
            i = a(8, l1, i);
        }

        for (int i2 = j1 - 8; i2 < j1; i2++)
        {
            i = a(i2, 8, i);
        }

        c = m.b(l, i);
        if (c != null)
        {
            return c;
        } else
        {
            throw FormatException.getFormatInstance();
        }
    }

    final Version b()
    {
        if (b != null)
        {
            return b;
        }
        int i = a.getHeight();
        int j = i - 17 >> 2;
        if (j <= 6)
        {
            return Version.getVersionForNumber(j);
        }
        int k = i - 11;
        int l = 5;
        int i1 = 0;
        for (; l >= 0; l--)
        {
            for (int j2 = i - 9; j2 >= k; j2--)
            {
                i1 = a(j2, l, i1);
            }

        }

        Version version = Version.a(i1);
        if (version != null && version.getDimensionForVersion() == i)
        {
            b = version;
            return version;
        }
        int j1 = 5;
        int k1 = 0;
        for (; j1 >= 0; j1--)
        {
            for (int l1 = i - 9; l1 >= k;)
            {
                int i2 = a(j1, l1, k1);
                l1--;
                k1 = i2;
            }

        }

        Version version1 = Version.a(k1);
        if (version1 != null && version1.getDimensionForVersion() == i)
        {
            b = version1;
            return version1;
        } else
        {
            throw FormatException.getFormatInstance();
        }
    }

    final byte[] c()
    {
        m m1 = a();
        Version version = b();
        c c1 = com.google.zxing.qrcode.decoder.c.a(m1.b());
        int i = a.getHeight();
        BitMatrix bitmatrix = a;
        for (int j = 0; j < i; j++)
        {
            for (int k2 = 0; k2 < i; k2++)
            {
                if (c1.a(j, k2))
                {
                    bitmatrix.flip(k2, j);
                }
            }

        }

        BitMatrix bitmatrix1 = version.a();
        byte abyte0[] = new byte[version.getTotalCodewords()];
        int k = i - 1;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        boolean flag1;
        for (boolean flag = true; k > 0; flag = flag1)
        {
            if (k == 6)
            {
                k--;
            }
            int l1;
            for (int k1 = 0; k1 < i; k1++)
            {
                int i2;
                if (flag)
                {
                    l1 = i - 1 - k1;
                } else
                {
                    l1 = k1;
                }
                for (i2 = 0; i2 < 2; i2++)
                {
                    if (bitmatrix1.get(k - i2, l1))
                    {
                        continue;
                    }
                    l++;
                    i1 <<= 1;
                    if (a.get(k - i2, l1))
                    {
                        i1 |= 1;
                    }
                    if (l == 8)
                    {
                        int j2 = j1 + 1;
                        abyte0[j1] = (byte)i1;
                        i1 = 0;
                        j1 = j2;
                        l = 0;
                    }
                }

            }

            flag1 = flag ^ true;
            k -= 2;
        }

        if (j1 != version.getTotalCodewords())
        {
            throw FormatException.getFormatInstance();
        } else
        {
            return abyte0;
        }
    }
}
