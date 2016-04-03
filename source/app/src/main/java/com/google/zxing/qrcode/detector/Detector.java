// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPatternFinder, a, FinderPatternInfo, FinderPattern, 
//            AlignmentPattern

public class Detector
{

    private final BitMatrix a;
    private ResultPointCallback b;

    public Detector(BitMatrix bitmatrix)
    {
        a = bitmatrix;
    }

    private float a(int i, int j, int k, int l)
    {
        int i1 = 0;
        float f = b(i, j, k, l);
        int j1 = i - (k - i);
        int k1;
        float f1;
        int l1;
        float f2;
        if (j1 < 0)
        {
            f1 = (float)i / (float)(i - j1);
            k1 = 0;
        } else
        if (j1 >= a.getWidth())
        {
            f1 = (float)((-1 + a.getWidth()) - i) / (float)(j1 - i);
            k1 = -1 + a.getWidth();
        } else
        {
            k1 = j1;
            f1 = 1.0F;
        }
        l1 = (int)((float)j - f1 * (float)(l - j));
        if (l1 < 0)
        {
            f2 = (float)j / (float)(j - l1);
        } else
        if (l1 >= a.getHeight())
        {
            f2 = (float)((-1 + a.getHeight()) - j) / (float)(l1 - j);
            i1 = -1 + a.getHeight();
        } else
        {
            i1 = l1;
            f2 = 1.0F;
        }
        return (f + b(i, j, (int)((float)i + f2 * (float)(k1 - i)), i1)) - 1.0F;
    }

    private float a(ResultPoint resultpoint, ResultPoint resultpoint1)
    {
        float f = a((int)resultpoint.getX(), (int)resultpoint.getY(), (int)resultpoint1.getX(), (int)resultpoint1.getY());
        float f1 = a((int)resultpoint1.getX(), (int)resultpoint1.getY(), (int)resultpoint.getX(), (int)resultpoint.getY());
        if (Float.isNaN(f))
        {
            return f1 / 7F;
        }
        if (Float.isNaN(f1))
        {
            return f / 7F;
        } else
        {
            return (f + f1) / 14F;
        }
    }

    private float b(int i, int j, int k, int l)
    {
        boolean flag;
        int k1;
        int l1;
        int i2;
        int j2;
        byte byte0;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int i4;
        int j4;
        boolean flag1;
        if (Math.abs(l - j) > Math.abs(k - i))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        int l3;
        int k4;
        int l4;
        int i5;
        int j5;
        if (!flag)
        {
            int i1 = l;
            l = k;
            k = i1;
            int j1 = j;
            j = i;
            i = j1;
        }
        k1 = Math.abs(l - j);
        l1 = Math.abs(k - i);
        i2 = -k1 >> 1;
        if (j < l)
        {
            j2 = 1;
        } else
        {
            j2 = -1;
        }
        if (i < k)
        {
            byte0 = 1;
        } else
        {
            byte0 = -1;
        }
        k2 = 0;
        l2 = l + j2;
        i3 = j;
        j3 = i2;
        k3 = i;
        if (i3 == l2)
        {
            break MISSING_BLOCK_LABEL_292;
        }
        if (flag)
        {
            i4 = k3;
        } else
        {
            i4 = i3;
        }
        if (flag)
        {
            j4 = i3;
        } else
        {
            j4 = k3;
        }
        if (k2 == 1)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 == a.get(i4, j4))
        {
            if (k2 == 2)
            {
                return MathUtils.distance(i3, k3, j, i);
            }
            k4 = k2 + 1;
        } else
        {
            k4 = k2;
        }
        l4 = j3 + l1;
        if (l4 <= 0) goto _L2; else goto _L1
_L1:
        if (k3 == k) goto _L4; else goto _L3
_L3:
        i5 = k3 + byte0;
        j5 = l4 - k1;
_L5:
        i3 += j2;
        k2 = k4;
        j3 = j5;
        k3 = i5;
        break MISSING_BLOCK_LABEL_85;
_L4:
        l3 = k4;
_L6:
        if (l3 == 2)
        {
            return MathUtils.distance(l + j2, k, j, i);
        } else
        {
            return (0.0F / 0.0F);
        }
_L2:
        i5 = k3;
        j5 = l4;
          goto _L5
        l3 = k2;
          goto _L6
    }

    protected final float calculateModuleSize(ResultPoint resultpoint, ResultPoint resultpoint1, ResultPoint resultpoint2)
    {
        return (a(resultpoint, resultpoint1) + a(resultpoint, resultpoint2)) / 2.0F;
    }

    public DetectorResult detect()
    {
        return detect(null);
    }

    public final DetectorResult detect(Map map)
    {
        ResultPointCallback resultpointcallback;
        if (map == null)
        {
            resultpointcallback = null;
        } else
        {
            resultpointcallback = (ResultPointCallback)map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        b = resultpointcallback;
        return processFinderPatternInfo((new FinderPatternFinder(a, b)).a(map));
    }

    protected final AlignmentPattern findAlignmentInRegion(float f, int i, int j, float f1)
    {
        int k = (int)(f1 * f);
        int l = Math.max(0, i - k);
        int i1 = Math.min(-1 + a.getWidth(), i + k);
        if ((float)(i1 - l) < f * 3F)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        int j1 = Math.max(0, j - k);
        int k1 = Math.min(-1 + a.getHeight(), k + j);
        if ((float)(k1 - j1) < f * 3F)
        {
            throw NotFoundException.getNotFoundInstance();
        } else
        {
            return (new a(a, l, j1, i1 - l, k1 - j1, f, b)).a();
        }
    }

    protected final BitMatrix getImage()
    {
        return a;
    }

    protected final ResultPointCallback getResultPointCallback()
    {
        return b;
    }

    protected final DetectorResult processFinderPatternInfo(FinderPatternInfo finderpatterninfo)
    {
        FinderPattern finderpattern;
        FinderPattern finderpattern1;
        FinderPattern finderpattern2;
        float f;
        int i;
        finderpattern = finderpatterninfo.getTopLeft();
        finderpattern1 = finderpatterninfo.getTopRight();
        finderpattern2 = finderpatterninfo.getBottomLeft();
        f = calculateModuleSize(finderpattern, finderpattern1, finderpattern2);
        if (f < 1.0F)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        i = 7 + (MathUtils.round(ResultPoint.distance(finderpattern, finderpattern1) / f) + MathUtils.round(ResultPoint.distance(finderpattern, finderpattern2) / f) >> 1);
        i & 3;
        JVM INSTR tableswitch 0 3: default 104
    //                   0 394
    //                   1 104
    //                   2 403
    //                   3 412;
           goto _L1 _L2 _L1 _L3 _L4
_L1:
        int j = i;
_L5:
        int l;
        int i1;
        int j1;
        Version version = Version.getProvisionalVersionForDimension(j);
        int k = -7 + version.getDimensionForVersion();
        if (version.getAlignmentPatternCenters().length <= 0)
        {
            break MISSING_BLOCK_LABEL_499;
        }
        float f6 = (finderpattern1.getX() - finderpattern.getX()) + finderpattern2.getX();
        float f7 = (finderpattern1.getY() - finderpattern.getY()) + finderpattern2.getY();
        float f8 = 1.0F - 3F / (float)k;
        l = (int)(finderpattern.getX() + f8 * (f6 - finderpattern.getX()));
        i1 = (int)(finderpattern.getY() + f8 * (f7 - finderpattern.getY()));
        j1 = 4;
_L6:
        float f9;
        if (j1 > 16)
        {
            break MISSING_BLOCK_LABEL_499;
        }
        f9 = j1;
        AlignmentPattern alignmentpattern1 = findAlignmentInRegion(f, l, i1, f9);
        AlignmentPattern alignmentpattern = alignmentpattern1;
_L7:
        float f1 = (float)j - 3.5F;
        float f2;
        float f3;
        float f4;
        float f5;
        PerspectiveTransform perspectivetransform;
        BitMatrix bitmatrix;
        BitMatrix bitmatrix1;
        ResultPoint aresultpoint[];
        NotFoundException notfoundexception;
        if (alignmentpattern != null)
        {
            f2 = alignmentpattern.getX();
            f3 = alignmentpattern.getY();
            f4 = f1 - 3F;
            f5 = f4;
        } else
        {
            f2 = (finderpattern1.getX() - finderpattern.getX()) + finderpattern2.getX();
            f3 = (finderpattern1.getY() - finderpattern.getY()) + finderpattern2.getY();
            f4 = f1;
            f5 = f1;
        }
        perspectivetransform = PerspectiveTransform.quadrilateralToQuadrilateral(3.5F, 3.5F, f1, 3.5F, f5, f4, 3.5F, f1, finderpattern.getX(), finderpattern.getY(), finderpattern1.getX(), finderpattern1.getY(), f2, f3, finderpattern2.getX(), finderpattern2.getY());
        bitmatrix = a;
        bitmatrix1 = GridSampler.getInstance().sampleGrid(bitmatrix, j, j, perspectivetransform);
        if (alignmentpattern == null)
        {
            aresultpoint = (new ResultPoint[] {
                finderpattern2, finderpattern, finderpattern1
            });
        } else
        {
            aresultpoint = (new ResultPoint[] {
                finderpattern2, finderpattern, finderpattern1, alignmentpattern
            });
        }
        return new DetectorResult(bitmatrix1, aresultpoint);
_L2:
        j = i + 1;
          goto _L5
_L3:
        j = i - 1;
          goto _L5
_L4:
        throw NotFoundException.getNotFoundInstance();
        notfoundexception;
        j1 <<= 1;
          goto _L6
        alignmentpattern = null;
          goto _L7
    }
}
