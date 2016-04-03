// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            LuminanceSource

public final class RGBLuminanceSource extends LuminanceSource
{

    private final byte a[];
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public RGBLuminanceSource(int i, int j, int ai[])
    {
        super(i, j);
        b = i;
        c = j;
        d = 0;
        e = 0;
        a = new byte[i * j];
        for (int k = 0; k < j; k++)
        {
            int l = k * i;
            int i1 = 0;
            while (i1 < i) 
            {
                int j1 = ai[l + i1];
                int k1 = 0xff & j1 >> 16;
                int l1 = 0xff & j1 >> 8;
                int i2 = j1 & 0xff;
                if (k1 == l1 && l1 == i2)
                {
                    a[l + i1] = (byte)k1;
                } else
                {
                    a[l + i1] = (byte)(i2 + (l1 + (k1 + l1)) >> 2);
                }
                i1++;
            }
        }

    }

    private RGBLuminanceSource(byte abyte0[], int i, int j, int k, int l, int i1, int j1)
    {
        super(i1, j1);
        if (k + i1 > i || l + j1 > j)
        {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        } else
        {
            a = abyte0;
            b = i;
            c = j;
            d = k;
            e = l;
            return;
        }
    }

    public final LuminanceSource crop(int i, int j, int k, int l)
    {
        return new RGBLuminanceSource(a, b, c, i + d, j + e, k, l);
    }

    public final byte[] getMatrix()
    {
        int i = 0;
        int j = getWidth();
        int k = getHeight();
        byte abyte0[];
        if (j == b && k == c)
        {
            abyte0 = a;
        } else
        {
            int l = j * k;
            abyte0 = new byte[l];
            int i1 = e * b + d;
            if (j == b)
            {
                System.arraycopy(a, i1, abyte0, 0, l);
                return abyte0;
            }
            byte abyte1[] = a;
            while (i < k) 
            {
                System.arraycopy(abyte1, i1, abyte0, i * j, j);
                i1 += b;
                i++;
            }
        }
        return abyte0;
    }

    public final byte[] getRow(int i, byte abyte0[])
    {
        if (i < 0 || i >= getHeight())
        {
            throw new IllegalArgumentException((new StringBuilder("Requested row is outside the image: ")).append(i).toString());
        }
        int j = getWidth();
        if (abyte0 == null || abyte0.length < j)
        {
            abyte0 = new byte[j];
        }
        int k = (i + e) * b + d;
        System.arraycopy(a, k, abyte0, 0, j);
        return abyte0;
    }

    public final boolean isCropSupported()
    {
        return true;
    }
}
