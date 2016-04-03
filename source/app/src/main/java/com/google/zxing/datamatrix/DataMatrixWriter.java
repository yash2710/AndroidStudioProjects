// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.DefaultPlacement;
import com.google.zxing.datamatrix.encoder.ErrorCorrection;
import com.google.zxing.datamatrix.encoder.HighLevelEncoder;
import com.google.zxing.datamatrix.encoder.SymbolInfo;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.util.Map;

public final class DataMatrixWriter
    implements Writer
{

    public DataMatrixWriter()
    {
    }

    private static BitMatrix a(DefaultPlacement defaultplacement, SymbolInfo symbolinfo)
    {
        int i = symbolinfo.getSymbolDataWidth();
        int j = symbolinfo.getSymbolDataHeight();
        ByteMatrix bytematrix = new ByteMatrix(symbolinfo.getSymbolWidth(), symbolinfo.getSymbolHeight());
        int k = 0;
        int l = 0;
        while (k < j) 
        {
            int i2;
            int j2;
            int k2;
            if (k % symbolinfo.matrixHeight == 0)
            {
                int i4 = 0;
                int j4 = 0;
                while (i4 < symbolinfo.getSymbolWidth()) 
                {
                    boolean flag1;
                    if (i4 % 2 == 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    bytematrix.set(j4, l, flag1);
                    j4++;
                    i4++;
                }
                i2 = l + 1;
            } else
            {
                i2 = l;
            }
            j2 = 0;
            k2 = 0;
            while (j2 < i) 
            {
                if (j2 % symbolinfo.matrixWidth == 0)
                {
                    bytematrix.set(k2, i2, true);
                    k2++;
                }
                bytematrix.set(k2, i2, defaultplacement.getBit(j2, k));
                int l3 = k2 + 1;
                int i1;
                int j1;
                BitMatrix bitmatrix;
                int k1;
                int l1;
                int l2;
                int i3;
                int j3;
                int k3;
                if (j2 % symbolinfo.matrixWidth == -1 + symbolinfo.matrixWidth)
                {
                    boolean flag;
                    if (k % 2 == 0)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    bytematrix.set(l3, i2, flag);
                    k2 = l3 + 1;
                } else
                {
                    k2 = l3;
                }
                j2++;
            }
            l2 = i2 + 1;
            if (k % symbolinfo.matrixHeight == -1 + symbolinfo.matrixHeight)
            {
                j3 = 0;
                k3 = 0;
                for (; j3 < symbolinfo.getSymbolWidth(); j3++)
                {
                    bytematrix.set(k3, l2, true);
                    k3++;
                }

                i3 = l2 + 1;
            } else
            {
                i3 = l2;
            }
            k++;
            l = i3;
        }
        i1 = bytematrix.getWidth();
        j1 = bytematrix.getHeight();
        bitmatrix = new BitMatrix(i1, j1);
        bitmatrix.clear();
        for (k1 = 0; k1 < i1; k1++)
        {
            for (l1 = 0; l1 < j1; l1++)
            {
                if (bytematrix.get(k1, l1) == 1)
                {
                    bitmatrix.set(k1, l1);
                }
            }

        }

        return bitmatrix;
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public final BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Map map)
    {
        if (s.length() == 0)
        {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeformat != BarcodeFormat.DATA_MATRIX)
        {
            throw new IllegalArgumentException((new StringBuilder("Can only encode DATA_MATRIX, but got ")).append(barcodeformat).toString());
        }
        if (i < 0 || j < 0)
        {
            throw new IllegalArgumentException((new StringBuilder("Requested dimensions are too small: ")).append(i).append('x').append(j).toString());
        }
        SymbolShapeHint symbolshapehint = SymbolShapeHint.FORCE_NONE;
        Dimension dimension;
        Dimension dimension1;
        if (map != null)
        {
            SymbolShapeHint symbolshapehint1 = (SymbolShapeHint)map.get(EncodeHintType.DATA_MATRIX_SHAPE);
            if (symbolshapehint1 != null)
            {
                symbolshapehint = symbolshapehint1;
            }
            Dimension dimension2 = (Dimension)map.get(EncodeHintType.MIN_SIZE);
            String s1;
            SymbolInfo symbolinfo;
            DefaultPlacement defaultplacement;
            Dimension dimension3;
            if (dimension2 != null)
            {
                dimension = dimension2;
            } else
            {
                dimension = null;
            }
            dimension3 = (Dimension)map.get(EncodeHintType.MAX_SIZE);
            dimension1 = null;
            if (dimension3 != null)
            {
                dimension1 = dimension3;
            }
        } else
        {
            dimension = null;
            dimension1 = null;
        }
        s1 = HighLevelEncoder.encodeHighLevel(s, symbolshapehint, dimension, dimension1);
        symbolinfo = SymbolInfo.lookup(s1.length(), symbolshapehint, dimension, dimension1, true);
        defaultplacement = new DefaultPlacement(ErrorCorrection.encodeECC200(s1, symbolinfo), symbolinfo.getSymbolDataWidth(), symbolinfo.getSymbolDataHeight());
        defaultplacement.place();
        return a(defaultplacement, symbolinfo);
    }
}
