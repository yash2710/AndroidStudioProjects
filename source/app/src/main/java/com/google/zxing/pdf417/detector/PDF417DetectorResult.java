// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.pdf417.detector;

import com.google.zxing.common.BitMatrix;
import java.util.List;

public final class PDF417DetectorResult
{

    private final BitMatrix a;
    private final List b;

    public PDF417DetectorResult(BitMatrix bitmatrix, List list)
    {
        a = bitmatrix;
        b = list;
    }

    public final BitMatrix getBits()
    {
        return a;
    }

    public final List getPoints()
    {
        return b;
    }
}
