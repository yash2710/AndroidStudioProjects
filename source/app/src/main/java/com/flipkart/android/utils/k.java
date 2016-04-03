// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.response.productInfo.ProductInfo;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.flipkart.android.utils:
//            PmuWidgetBuilder

final class k
    implements Runnable
{

    private ArrayList a;

    k(ArrayList arraylist)
    {
        a = arraylist;
        super();
    }

    public final void run()
    {
        for (Iterator iterator = a.iterator(); iterator.hasNext(); PmuWidgetBuilder.a((ProductInfo)iterator.next())) { }
    }
}
