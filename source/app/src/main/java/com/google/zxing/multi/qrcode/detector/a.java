// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.multi.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.detector.FinderPattern;
import com.google.zxing.qrcode.detector.FinderPatternFinder;
import com.google.zxing.qrcode.detector.FinderPatternInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.multi.qrcode.detector:
//            b

final class a extends FinderPatternFinder
{

    private static final FinderPatternInfo a[] = new FinderPatternInfo[0];

    a(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
    {
        super(bitmatrix, resultpointcallback);
    }

    public final FinderPatternInfo[] findMulti(Map map)
    {
        boolean flag;
        BitMatrix bitmatrix;
        int i;
        int j;
        int k;
        int l;
        int ai[];
        if (map != null && map.containsKey(DecodeHintType.TRY_HARDER))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        bitmatrix = getImage();
        i = bitmatrix.getHeight();
        j = bitmatrix.getWidth();
        k = (int)(3F * ((float)i / 228F));
        List list;
        int j1;
        FinderPattern afinderpattern[][];
        ArrayList arraylist1;
        int l1;
        if (k < 3 || flag)
        {
            l = 3;
        } else
        {
            l = k;
        }
        ai = new int[5];
        for (int i1 = l - 1; i1 < i; i1 += l)
        {
            ai[0] = 0;
            ai[1] = 0;
            ai[2] = 0;
            ai[3] = 0;
            ai[4] = 0;
            int l2 = 0;
            int i3 = 0;
            while (i3 < j) 
            {
                if (bitmatrix.get(i3, i1))
                {
                    if ((l2 & 1) == 1)
                    {
                        l2++;
                    }
                    ai[l2] = 1 + ai[l2];
                } else
                if ((l2 & 1) == 0)
                {
                    if (l2 == 4)
                    {
                        if (foundPatternCross(ai) && handlePossibleCenter(ai, i1, i3))
                        {
                            ai[0] = 0;
                            ai[1] = 0;
                            ai[2] = 0;
                            ai[3] = 0;
                            ai[4] = 0;
                            l2 = 0;
                        } else
                        {
                            ai[0] = ai[2];
                            ai[1] = ai[3];
                            ai[2] = ai[4];
                            ai[3] = 1;
                            ai[4] = 0;
                            l2 = 3;
                        }
                    } else
                    {
                        l2++;
                        ai[l2] = 1 + ai[l2];
                    }
                } else
                {
                    ai[l2] = 1 + ai[l2];
                }
                i3++;
            }
            if (foundPatternCross(ai))
            {
                handlePossibleCenter(ai, i1, j);
            }
        }

        list = getPossibleCenters();
        j1 = list.size();
        if (j1 < 3)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (j1 == 3)
        {
            FinderPattern afinderpattern3[][] = new FinderPattern[1][];
            FinderPattern afinderpattern4[] = new FinderPattern[3];
            afinderpattern4[0] = (FinderPattern)list.get(0);
            afinderpattern4[1] = (FinderPattern)list.get(1);
            afinderpattern4[2] = (FinderPattern)list.get(2);
            afinderpattern3[0] = afinderpattern4;
            afinderpattern = afinderpattern3;
        } else
        {
            Collections.sort(list, new b((byte)0));
            ArrayList arraylist = new ArrayList();
            for (int k1 = 0; k1 < j1 - 2; k1++)
            {
                FinderPattern finderpattern = (FinderPattern)list.get(k1);
                if (finderpattern == null)
                {
                    continue;
                }
                for (int j2 = k1 + 1; j2 < j1 - 1; j2++)
                {
                    FinderPattern finderpattern1 = (FinderPattern)list.get(j2);
                    if (finderpattern1 == null)
                    {
                        continue;
                    }
                    float f = (finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) / Math.min(finderpattern.getEstimatedModuleSize(), finderpattern1.getEstimatedModuleSize());
                    if (Math.abs(finderpattern.getEstimatedModuleSize() - finderpattern1.getEstimatedModuleSize()) > 0.5F && f >= 0.05F)
                    {
                        break;
                    }
                    for (int k2 = j2 + 1; k2 < j1; k2++)
                    {
                        FinderPattern finderpattern2 = (FinderPattern)list.get(k2);
                        if (finderpattern2 == null)
                        {
                            continue;
                        }
                        float f1 = (finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) / Math.min(finderpattern1.getEstimatedModuleSize(), finderpattern2.getEstimatedModuleSize());
                        if (Math.abs(finderpattern1.getEstimatedModuleSize() - finderpattern2.getEstimatedModuleSize()) > 0.5F && f1 >= 0.05F)
                        {
                            break;
                        }
                        FinderPattern afinderpattern2[] = {
                            finderpattern, finderpattern1, finderpattern2
                        };
                        ResultPoint.orderBestPatterns(afinderpattern2);
                        FinderPatternInfo finderpatterninfo = new FinderPatternInfo(afinderpattern2);
                        float f2 = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getBottomLeft());
                        float f3 = ResultPoint.distance(finderpatterninfo.getTopRight(), finderpatterninfo.getBottomLeft());
                        float f4 = ResultPoint.distance(finderpatterninfo.getTopLeft(), finderpatterninfo.getTopRight());
                        float f5 = (f2 + f4) / (2.0F * finderpattern.getEstimatedModuleSize());
                        if (f5 > 180F || f5 < 9F || Math.abs((f2 - f4) / Math.min(f2, f4)) >= 0.1F)
                        {
                            continue;
                        }
                        float f6 = (float)Math.sqrt(f2 * f2 + f4 * f4);
                        if (Math.abs((f3 - f6) / Math.min(f3, f6)) < 0.1F)
                        {
                            arraylist.add(afinderpattern2);
                        }
                    }

                }

            }

            if (!arraylist.isEmpty())
            {
                afinderpattern = (FinderPattern[][])arraylist.toArray(new FinderPattern[arraylist.size()][]);
            } else
            {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        arraylist1 = new ArrayList();
        l1 = afinderpattern.length;
        for (int i2 = 0; i2 < l1; i2++)
        {
            FinderPattern afinderpattern1[] = afinderpattern[i2];
            ResultPoint.orderBestPatterns(afinderpattern1);
            arraylist1.add(new FinderPatternInfo(afinderpattern1));
        }

        if (arraylist1.isEmpty())
        {
            return a;
        } else
        {
            return (FinderPatternInfo[])arraylist1.toArray(new FinderPatternInfo[arraylist1.size()]);
        }
    }

}
