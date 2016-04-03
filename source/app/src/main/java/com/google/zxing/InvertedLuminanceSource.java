// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;


// Referenced classes of package com.google.zxing:
//            LuminanceSource

public final class InvertedLuminanceSource extends LuminanceSource
{

    private final LuminanceSource a;

    public InvertedLuminanceSource(LuminanceSource luminancesource)
    {
        super(luminancesource.getWidth(), luminancesource.getHeight());
        a = luminancesource;
    }

    public final LuminanceSource crop(int i, int j, int k, int l)
    {
        return new InvertedLuminanceSource(a.crop(i, j, k, l));
    }

    public final byte[] getMatrix()
    {
        byte abyte0[] = a.getMatrix();
        int i = getWidth() * getHeight();
        byte abyte1[] = new byte[i];
        for (int j = 0; j < i; j++)
        {
            abyte1[j] = (byte)(255 - (0xff & abyte0[j]));
        }

        return abyte1;
    }

    public final byte[] getRow(int i, byte abyte0[])
    {
        byte abyte1[] = a.getRow(i, abyte0);
        int j = getWidth();
        for (int k = 0; k < j; k++)
        {
            abyte1[k] = (byte)(255 - (0xff & abyte1[k]));
        }

        return abyte1;
    }

    public final LuminanceSource invert()
    {
        return a;
    }

    public final boolean isCropSupported()
    {
        return a.isCropSupported();
    }

    public final boolean isRotateSupported()
    {
        return a.isRotateSupported();
    }

    public final LuminanceSource rotateCounterClockwise()
    {
        return new InvertedLuminanceSource(a.rotateCounterClockwise());
    }

    public final LuminanceSource rotateCounterClockwise45()
    {
        return new InvertedLuminanceSource(a.rotateCounterClockwise45());
    }
}
