// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.qrcode.detector;

import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package com.google.zxing.qrcode.detector:
//            FinderPattern

final class b
    implements Serializable, Comparator
{

    private final float a;

    private b(float f)
    {
        a = f;
    }

    b(float f, byte byte0)
    {
        this(f);
    }

    public final int compare(FinderPattern finderpattern, FinderPattern finderpattern1)
    {
        if (finderpattern1.a() == finderpattern.a())
        {
            float f = Math.abs(finderpattern1.getEstimatedModuleSize() - a);
            float f1 = Math.abs(finderpattern.getEstimatedModuleSize() - a);
            if (f < f1)
            {
                return 1;
            }
            return f != f1 ? -1 : 0;
        } else
        {
            return finderpattern1.a() - finderpattern.a();
        }
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((FinderPattern)obj, (FinderPattern)obj1);
    }
}
