// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.flipkart.android.DB:
//            FlipkartProductInfo, FlipkartProductInfoDao

final class e
    implements Callable
{

    private List a;
    private FlipkartProductInfoDao b;

    e(FlipkartProductInfoDao flipkartproductinfodao, List list)
    {
        b = flipkartproductinfodao;
        a = list;
        super();
    }

    public final volatile Object call()
    {
        return call();
    }

    public final Void call()
    {
        FlipkartProductInfo flipkartproductinfo;
        for (Iterator iterator = a.iterator(); iterator.hasNext(); b.delete(flipkartproductinfo))
        {
            flipkartproductinfo = (FlipkartProductInfo)iterator.next();
        }

        return null;
    }
}
