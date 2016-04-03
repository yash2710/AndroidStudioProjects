// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;

// Referenced classes of package com.flipkart.android.DB:
//            FlipkartProductInfo, FlipkartProductInfoDao

final class d
    implements Callable
{

    private ArrayList a;
    private boolean b;
    private FlipkartProductInfoDao c;

    d(FlipkartProductInfoDao flipkartproductinfodao, ArrayList arraylist, boolean flag)
    {
        c = flipkartproductinfodao;
        a = arraylist;
        b = flag;
        super();
    }

    public final volatile Object call()
    {
        return call();
    }

    public final Void call()
    {
        Iterator iterator = a.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            FlipkartProductInfo flipkartproductinfo = (FlipkartProductInfo)iterator.next();
            if (flipkartproductinfo != null)
            {
                c.create(flipkartproductinfo, b);
            }
        } while (true);
        return null;
    }
}
