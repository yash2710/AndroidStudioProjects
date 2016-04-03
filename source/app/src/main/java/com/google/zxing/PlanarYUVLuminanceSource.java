// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            LuminanceSource

public final class PlanarYUVLuminanceSource extends LuminanceSource
{

    private final byte a[];
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public PlanarYUVLuminanceSource(byte abyte0[], int i, int j, int k, int l, int i1, int j1, 
            boolean flag)
    {
        super(i1, j1);
        if (k + i1 > i || l + j1 > j)
        {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        a = abyte0;
        b = i;
        c = j;
        d = k;
        e = l;
        if (flag)
        {
            a(i1, j1);
        }
    }

    private void a(int i, int j)
    {
        byte abyte0[] = a;
        int k = e * b + d;
        int l1;
        for (int l = 0; l < j; l = l1)
        {
            int i1 = k + i / 2;
            int j1 = -1 + (k + i);
            for (int k1 = k; k1 < i1;)
            {
                byte byte0 = abyte0[k1];
                abyte0[k1] = abyte0[j1];
                abyte0[j1] = byte0;
                k1++;
                j1--;
            }

            l1 = l + 1;
            k += b;
        }

    }

    public final LuminanceSource crop(int i, int j, int k, int l)
    {
        return new PlanarYUVLuminanceSource(a, b, c, i + d, j + e, k, l, false);
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

    public final int getThumbnailHeight()
    {
        return getHeight() / 2;
    }

    public final int getThumbnailWidth()
    {
        return getWidth() / 2;
    }

    public final boolean isCropSupported()
    {
        return true;
    }

    public final int[] renderThumbnail()
    {
        int i = getWidth() / 2;
        int j = getHeight() / 2;
        int ai[] = new int[i * j];
        byte abyte0[] = a;
        int k = e * b + d;
        int l = 0;
        int i1 = k;
        for (; l < j; l++)
        {
            int j1 = l * i;
            for (int k1 = 0; k1 < i; k1++)
            {
                int l1 = 0xff & abyte0[i1 + (k1 << 1)];
                ai[j1 + k1] = 0xff000000 | l1 * 0x10101;
            }

            i1 += b << 1;
        }

        return ai;
    }
}
