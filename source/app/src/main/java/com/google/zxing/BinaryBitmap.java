// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing:
//            Binarizer, LuminanceSource

public final class BinaryBitmap
{

    private final Binarizer a;
    private BitMatrix b;

    public BinaryBitmap(Binarizer binarizer)
    {
        if (binarizer == null)
        {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        } else
        {
            a = binarizer;
            return;
        }
    }

    public final BinaryBitmap crop(int i, int j, int k, int l)
    {
        LuminanceSource luminancesource = a.getLuminanceSource().crop(i, j, k, l);
        return new BinaryBitmap(a.createBinarizer(luminancesource));
    }

    public final BitMatrix getBlackMatrix()
    {
        if (b == null)
        {
            b = a.getBlackMatrix();
        }
        return b;
    }

    public final BitArray getBlackRow(int i, BitArray bitarray)
    {
        return a.getBlackRow(i, bitarray);
    }

    public final int getHeight()
    {
        return a.getHeight();
    }

    public final int getWidth()
    {
        return a.getWidth();
    }

    public final boolean isCropSupported()
    {
        return a.getLuminanceSource().isCropSupported();
    }

    public final boolean isRotateSupported()
    {
        return a.getLuminanceSource().isRotateSupported();
    }

    public final BinaryBitmap rotateCounterClockwise()
    {
        LuminanceSource luminancesource = a.getLuminanceSource().rotateCounterClockwise();
        return new BinaryBitmap(a.createBinarizer(luminancesource));
    }

    public final BinaryBitmap rotateCounterClockwise45()
    {
        LuminanceSource luminancesource = a.getLuminanceSource().rotateCounterClockwise45();
        return new BinaryBitmap(a.createBinarizer(luminancesource));
    }
}
