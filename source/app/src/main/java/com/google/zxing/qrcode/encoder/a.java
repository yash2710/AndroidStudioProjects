// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.encoder;


// Referenced classes of package com.google.zxing.qrcode.encoder:
//            ByteMatrix

class a
{

    private final byte a[];
    private final byte b[];

    a(byte abyte0[], byte abyte1[])
    {
        a = abyte0;
        b = abyte1;
    }

    static int a(ByteMatrix bytematrix)
    {
        return a(bytematrix, true) + a(bytematrix, false);
    }

    private static int a(ByteMatrix bytematrix, boolean flag)
    {
        int i;
        int j;
        byte abyte0[][];
        int k;
        int l;
        if (flag)
        {
            i = bytematrix.getHeight();
        } else
        {
            i = bytematrix.getWidth();
        }
        if (flag)
        {
            j = bytematrix.getWidth();
        } else
        {
            j = bytematrix.getHeight();
        }
        abyte0 = bytematrix.getArray();
        k = 0;
        l = 0;
        for (; k < i; k++)
        {
            byte byte0 = -1;
            int i1 = 0;
            int j1 = 0;
            while (i1 < j) 
            {
                byte byte1;
                int k1;
                int l1;
                byte byte3;
                if (flag)
                {
                    byte1 = abyte0[k][i1];
                } else
                {
                    byte1 = abyte0[i1][k];
                }
                if (byte1 == byte0)
                {
                    int i2 = j1 + 1;
                    byte byte4 = byte0;
                    k1 = i2;
                    l1 = l;
                    byte3 = byte4;
                } else
                {
                    if (j1 >= 5)
                    {
                        l += 3 + (j1 - 5);
                    }
                    k1 = 1;
                    byte byte2 = byte1;
                    l1 = l;
                    byte3 = byte2;
                }
                i1++;
                j1 = k1;
                byte0 = byte3;
                l = l1;
            }
            if (j1 >= 5)
            {
                l += 3 + (j1 - 5);
            }
        }

        return l;
    }

    public final byte[] getDataBytes()
    {
        return a;
    }

    public final byte[] getErrorCorrectionBytes()
    {
        return b;
    }
}
