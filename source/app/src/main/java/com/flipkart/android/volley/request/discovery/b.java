// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.request.discovery;

import com.flipkart.android.DB.FlipkartProductInfoDao;
import com.flipkart.android.init.FlipkartApplication;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.volley.request.discovery:
//            ProductInfoRequest

final class b extends Thread
{

    private ArrayList a;

    b(ProductInfoRequest productinforequest, ArrayList arraylist)
    {
        a = arraylist;
        super();
    }

    public final void run()
    {
        try
        {
            (new FlipkartProductInfoDao(FlipkartApplication.getAppContext())).createInBulk(a, true);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }
}
