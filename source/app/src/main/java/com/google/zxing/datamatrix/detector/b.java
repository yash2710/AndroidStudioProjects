// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.datamatrix.detector;

import java.io.Serializable;
import java.util.Comparator;

// Referenced classes of package com.google.zxing.datamatrix.detector:
//            a

final class b
    implements Serializable, Comparator
{

    private b()
    {
    }

    b(byte byte0)
    {
        this();
    }

    public final int compare(a a1, a a2)
    {
        return a1.getTransitions() - a2.getTransitions();
    }

    public final volatile int compare(Object obj, Object obj1)
    {
        return compare((a)obj, (a)obj1);
    }
}
