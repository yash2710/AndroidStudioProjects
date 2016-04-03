// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern, c, b, FinderPatternInfo

public class FinderPatternFinder
{

    protected static final int MAX_MODULES = 57;
    protected static final int MIN_SKIP = 3;
    private final BitMatrix a;
    private final List b;
    private boolean c;
    private final int d[];
    private final ResultPointCallback e;

    public FinderPatternFinder(BitMatrix bitmatrix)
    {
        this(bitmatrix, null);
    }

    public FinderPatternFinder(BitMatrix bitmatrix, ResultPointCallback resultpointcallback)
    {
        a = bitmatrix;
        b = new ArrayList();
        d = new int[5];
        e = resultpointcallback;
    }

    private static float a(int ai[], int i)
    {
        return (float)(i - ai[4] - ai[3]) - (float)ai[2] / 2.0F;
    }

    private int[] a()
    {
        d[0] = 0;
        d[1] = 0;
        d[2] = 0;
        d[3] = 0;
        d[4] = 0;
        return d;
    }

    private boolean b()
    {
        float f = 0.0F;
        int i = b.size();
        Iterator iterator = b.iterator();
        float f1 = 0.0F;
        int j = 0;
        while (iterator.hasNext()) 
        {
            FinderPattern finderpattern = (FinderPattern)iterator.next();
            float f2;
            Iterator iterator1;
            float f3;
            int k;
            if (finderpattern.a() >= 2)
            {
                int l = j + 1;
                f3 = f1 + finderpattern.getEstimatedModuleSize();
                k = l;
            } else
            {
                f3 = f1;
                k = j;
            }
            j = k;
            f1 = f3;
        }
        if (j >= 3)
        {
            f2 = f1 / (float)i;
            for (iterator1 = b.iterator(); iterator1.hasNext();)
            {
                f += Math.abs(((FinderPattern)iterator1.next()).getEstimatedModuleSize() - f2);
            }

            if (f <= 0.05F * f1)
            {
                return true;
            }
        }
        return false;
    }

    protected static boolean foundPatternCross(int ai[])
    {
        int i;
        int j;
        i = 0;
        j = 0;
_L8:
        if (i >= 5) goto _L2; else goto _L1
_L1:
        int i1 = ai[i];
        if (i1 != 0) goto _L4; else goto _L3
_L3:
        return false;
_L4:
        j += i1;
        i++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (j < 7) goto _L3; else goto _L5
_L5:
        int k;
        int l;
        k = (j << 8) / 7;
        l = k / 2;
        if (Math.abs(k - (ai[0] << 8)) >= l || Math.abs(k - (ai[1] << 8)) >= l || Math.abs(k * 3 - (ai[2] << 8)) >= l * 3 || Math.abs(k - (ai[3] << 8)) >= l || Math.abs(k - (ai[4] << 8)) >= l) goto _L3; else goto _L6
_L6:
        return true;
        if (true) goto _L8; else goto _L7
_L7:
    }

    final FinderPatternInfo a(Map map)
    {
        boolean flag;
        int i;
        int j;
        int k;
        int l;
        boolean flag1;
        int ai[];
        int i1;
        int j1;
        int j2;
        int k2;
        if (map != null && map.containsKey(DecodeHintType.TRY_HARDER))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = a.getHeight();
        j = a.getWidth();
        k = (i * 3) / 228;
        int k1;
        FinderPattern afinderpattern[];
        Iterator iterator;
        float f;
        float f1;
        Iterator iterator1;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int l1;
        int i2;
        float f7;
        int l2;
        int i3;
        int j3;
        boolean flag2;
        Iterator iterator2;
        FinderPattern finderpattern;
        FinderPattern finderpattern1;
        if (k < 3 || flag)
        {
            l = 3;
        } else
        {
            l = k;
        }
        flag1 = false;
        ai = new int[5];
        i1 = l - 1;
        j1 = l;
        if (i1 >= i || flag1) goto _L2; else goto _L1
_L1:
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        j2 = 0;
        k2 = 0;
_L4:
        if (k2 >= j)
        {
            break MISSING_BLOCK_LABEL_530;
        }
        if (!a.get(k2, i1))
        {
            break; /* Loop/switch isn't completed */
        }
        if ((j2 & 1) == 1)
        {
            j2++;
        }
        ai[j2] = 1 + ai[j2];
_L7:
        k2++;
        if (true) goto _L4; else goto _L3
_L3:
        if ((j2 & 1) != 0)
        {
            break MISSING_BLOCK_LABEL_515;
        }
        if (j2 != 4)
        {
            break MISSING_BLOCK_LABEL_497;
        }
        if (!foundPatternCross(ai))
        {
            break MISSING_BLOCK_LABEL_457;
        }
        if (!handlePossibleCenter(ai, i1, k2))
        {
            break MISSING_BLOCK_LABEL_417;
        }
        if (!c) goto _L6; else goto _L5
_L5:
        flag2 = b();
_L8:
        ai[0] = 0;
        ai[1] = 0;
        ai[2] = 0;
        ai[3] = 0;
        ai[4] = 0;
        flag1 = flag2;
        j1 = 2;
        j2 = 0;
          goto _L7
_L6:
        if (b.size() <= 1)
        {
            break MISSING_BLOCK_LABEL_411;
        }
        iterator2 = b.iterator();
        finderpattern = null;
        do
        {
            do
            {
                if (!iterator2.hasNext())
                {
                    break MISSING_BLOCK_LABEL_411;
                }
                finderpattern1 = (FinderPattern)iterator2.next();
            } while (finderpattern1.a() < 2);
            if (finderpattern != null)
            {
                break;
            }
            finderpattern = finderpattern1;
        } while (true);
        c = true;
        l2 = (int)(Math.abs(finderpattern.getX() - finderpattern1.getX()) - Math.abs(finderpattern.getY() - finderpattern1.getY())) / 2;
_L9:
        if (l2 > ai[2])
        {
            j3 = i1 + (-2 + (l2 - ai[2]));
            i3 = j - 1;
        } else
        {
            i3 = k2;
            j3 = i1;
        }
        k2 = i3;
        i1 = j3;
        flag2 = flag1;
          goto _L8
        l2 = 0;
          goto _L9
        ai[0] = ai[2];
        ai[1] = ai[3];
        ai[2] = ai[4];
        ai[3] = 1;
        ai[4] = 0;
        j2 = 3;
          goto _L7
        ai[0] = ai[2];
        ai[1] = ai[3];
        ai[2] = ai[4];
        ai[3] = 1;
        ai[4] = 0;
        j2 = 3;
          goto _L7
        j2++;
        ai[j2] = 1 + ai[j2];
          goto _L7
        ai[j2] = 1 + ai[j2];
          goto _L7
        if (!foundPatternCross(ai) || !handlePossibleCenter(ai, i1, j))
        {
            continue; /* Loop/switch isn't completed */
        }
        j1 = ai[0];
        if (c)
        {
            flag1 = b();
        }
        i1 += j1;
        break MISSING_BLOCK_LABEL_75;
_L2:
        k1 = b.size();
        if (k1 < 3)
        {
            throw NotFoundException.getNotFoundInstance();
        }
        if (k1 > 3)
        {
            iterator1 = b.iterator();
            f2 = 0.0F;
            for (f3 = 0.0F; iterator1.hasNext(); f3 += f7 * f7)
            {
                f7 = ((FinderPattern)iterator1.next()).getEstimatedModuleSize();
                f2 += f7;
            }

            f4 = f2 / (float)k1;
            f5 = (float)Math.sqrt(f3 / (float)k1 - f4 * f4);
            Collections.sort(b, new c(f4, (byte)0));
            f6 = Math.max(0.2F * f4, f5);
            l1 = 0;
            while (l1 < b.size() && b.size() > 3) 
            {
                if (Math.abs(((FinderPattern)b.get(l1)).getEstimatedModuleSize() - f4) > f6)
                {
                    b.remove(l1);
                    i2 = l1 - 1;
                } else
                {
                    i2 = l1;
                }
                l1 = i2 + 1;
            }
        }
        if (b.size() > 3)
        {
            iterator = b.iterator();
            for (f = 0.0F; iterator.hasNext(); f += ((FinderPattern)iterator.next()).getEstimatedModuleSize()) { }
            f1 = f / (float)b.size();
            Collections.sort(b, new b(f1, (byte)0));
            b.subList(3, b.size()).clear();
        }
        afinderpattern = new FinderPattern[3];
        afinderpattern[0] = (FinderPattern)b.get(0);
        afinderpattern[1] = (FinderPattern)b.get(1);
        afinderpattern[2] = (FinderPattern)b.get(2);
        ResultPoint.orderBestPatterns(afinderpattern);
        return new FinderPatternInfo(afinderpattern);
    }

    protected final BitMatrix getImage()
    {
        return a;
    }

    protected final List getPossibleCenters()
    {
        return b;
    }

    protected final boolean handlePossibleCenter(int ai[], int i, int j)
    {
        int k;
        float f1;
        k = ai[0] + ai[1] + ai[2] + ai[3] + ai[4];
        float f = a(ai, j);
        int l = (int)f;
        int i1 = ai[2];
        BitMatrix bitmatrix = a;
        int j1 = bitmatrix.getHeight();
        int ai1[] = a();
        int k1;
        for (k1 = i; k1 >= 0 && bitmatrix.get(l, k1); k1--)
        {
            ai1[2] = 1 + ai1[2];
        }

        if (k1 < 0)
        {
            f1 = (0.0F / 0.0F);
        } else
        {
            for (; k1 >= 0 && !bitmatrix.get(l, k1) && ai1[1] <= i1; k1--)
            {
                ai1[1] = 1 + ai1[1];
            }

            if (k1 < 0 || ai1[1] > i1)
            {
                f1 = (0.0F / 0.0F);
            } else
            {
                for (; k1 >= 0 && bitmatrix.get(l, k1) && ai1[0] <= i1; k1--)
                {
                    ai1[0] = 1 + ai1[0];
                }

                if (ai1[0] > i1)
                {
                    f1 = (0.0F / 0.0F);
                } else
                {
                    int k3;
                    for (k3 = i + 1; k3 < j1 && bitmatrix.get(l, k3); k3++)
                    {
                        ai1[2] = 1 + ai1[2];
                    }

                    if (k3 == j1)
                    {
                        f1 = (0.0F / 0.0F);
                    } else
                    {
                        for (; k3 < j1 && !bitmatrix.get(l, k3) && ai1[3] < i1; k3++)
                        {
                            ai1[3] = 1 + ai1[3];
                        }

                        if (k3 == j1 || ai1[3] >= i1)
                        {
                            f1 = (0.0F / 0.0F);
                        } else
                        {
                            for (; k3 < j1 && bitmatrix.get(l, k3) && ai1[4] < i1; k3++)
                            {
                                ai1[4] = 1 + ai1[4];
                            }

                            if (ai1[4] >= i1)
                            {
                                f1 = (0.0F / 0.0F);
                            } else
                            if (5 * Math.abs((ai1[0] + ai1[1] + ai1[2] + ai1[3] + ai1[4]) - k) >= k * 2)
                            {
                                f1 = (0.0F / 0.0F);
                            } else
                            if (foundPatternCross(ai1))
                            {
                                f1 = a(ai1, k3);
                            } else
                            {
                                f1 = (0.0F / 0.0F);
                            }
                        }
                    }
                }
            }
        }
        if (Float.isNaN(f1)) goto _L2; else goto _L1
_L1:
        int i3;
        boolean flag;
        int l1 = (int)f;
        int i2 = (int)f1;
        int j2 = ai[2];
        BitMatrix bitmatrix1 = a;
        int k2 = bitmatrix1.getWidth();
        int ai2[] = a();
        int l2;
        for (l2 = l1; l2 >= 0 && bitmatrix1.get(l2, i2); l2--)
        {
            ai2[2] = 1 + ai2[2];
        }

        float f2;
        float f3;
        FinderPattern finderpattern;
        FinderPattern finderpattern1;
        if (l2 < 0)
        {
            f2 = (0.0F / 0.0F);
        } else
        {
            for (; l2 >= 0 && !bitmatrix1.get(l2, i2) && ai2[1] <= j2; l2--)
            {
                ai2[1] = 1 + ai2[1];
            }

            if (l2 < 0 || ai2[1] > j2)
            {
                f2 = (0.0F / 0.0F);
            } else
            {
                for (; l2 >= 0 && bitmatrix1.get(l2, i2) && ai2[0] <= j2; l2--)
                {
                    ai2[0] = 1 + ai2[0];
                }

                if (ai2[0] > j2)
                {
                    f2 = (0.0F / 0.0F);
                } else
                {
                    int j3;
                    for (j3 = l1 + 1; j3 < k2 && bitmatrix1.get(j3, i2); j3++)
                    {
                        ai2[2] = 1 + ai2[2];
                    }

                    if (j3 == k2)
                    {
                        f2 = (0.0F / 0.0F);
                    } else
                    {
                        for (; j3 < k2 && !bitmatrix1.get(j3, i2) && ai2[3] < j2; j3++)
                        {
                            ai2[3] = 1 + ai2[3];
                        }

                        if (j3 == k2 || ai2[3] >= j2)
                        {
                            f2 = (0.0F / 0.0F);
                        } else
                        {
                            for (; j3 < k2 && bitmatrix1.get(j3, i2) && ai2[4] < j2; j3++)
                            {
                                ai2[4] = 1 + ai2[4];
                            }

                            if (ai2[4] >= j2)
                            {
                                f2 = (0.0F / 0.0F);
                            } else
                            if (5 * Math.abs((ai2[0] + ai2[1] + ai2[2] + ai2[3] + ai2[4]) - k) >= k)
                            {
                                f2 = (0.0F / 0.0F);
                            } else
                            if (foundPatternCross(ai2))
                            {
                                f2 = a(ai2, j3);
                            } else
                            {
                                f2 = (0.0F / 0.0F);
                            }
                        }
                    }
                }
            }
        }
        if (Float.isNaN(f2)) goto _L2; else goto _L3
_L3:
        f3 = (float)k / 7F;
        i3 = 0;
_L6:
        if (i3 >= b.size())
        {
            break MISSING_BLOCK_LABEL_1088;
        }
        finderpattern1 = (FinderPattern)b.get(i3);
        if (!finderpattern1.a(f3, f1, f2)) goto _L5; else goto _L4
_L4:
        b.set(i3, finderpattern1.b(f1, f2, f3));
        flag = true;
_L7:
        if (!flag)
        {
            finderpattern = new FinderPattern(f2, f1, f3);
            b.add(finderpattern);
            if (e != null)
            {
                e.foundPossibleResultPoint(finderpattern);
            }
        }
        return true;
_L5:
        i3++;
          goto _L6
_L2:
        return false;
        flag = false;
          goto _L7
    }
}
