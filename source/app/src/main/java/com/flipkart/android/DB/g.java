// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.flipkart.android.DB:
//            ProductDiscoveryDao, ProductDiscovery

final class g
    implements Callable
{

    private ProductDiscoveryDao a;

    g(ProductDiscoveryDao productdiscoverydao)
    {
        a = productdiscoverydao;
        super();
    }

    public final volatile Object call()
    {
        return call();
    }

    public final Void call()
    {
        List list = a.getAll();
        if (list != null)
        {
            for (int i = 0; i < list.size(); i++)
            {
                a.delete((ProductDiscovery)list.get(i));
            }

        }
        return null;
    }
}
