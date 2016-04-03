// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.DB;

import java.util.List;
import java.util.concurrent.Callable;

// Referenced classes of package com.flipkart.android.DB:
//            UGCDao, UGC

final class i
    implements Callable
{

    private UGCDao a;

    i(UGCDao ugcdao)
    {
        a = ugcdao;
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
            for (int j = 0; j < list.size(); j++)
            {
                a.delete((UGC)list.get(j));
            }

        }
        return null;
    }
}
