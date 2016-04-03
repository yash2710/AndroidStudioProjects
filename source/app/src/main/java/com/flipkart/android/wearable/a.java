// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.flipkart.android.datahandler.ProductInfoVDataHandler;
import com.flipkart.android.response.productInfo.ProductInfoResponse;
import com.flipkart.logging.FkLogger;
import java.util.List;

// Referenced classes of package com.flipkart.android.wearable:
//            WearableActionHandler, WearableDataSender

final class a extends ProductInfoVDataHandler
{

    private int a;
    private List b;
    private List c;
    private int d;
    private WearableActionHandler e;

    a(WearableActionHandler wearableactionhandler, int i, int j, List list, List list1, int k)
    {
        e = wearableactionhandler;
        a = j;
        b = list;
        c = list1;
        d = k;
        super(1);
    }

    public final void errorReceived(int i, int j, String s)
    {
        super.errorReceived(i, j, s);
        if (d == 0)
        {
            FkLogger.verbose(WearableActionHandler.a(), "Request Failed, Sending whatever data we have");
            (new WearableDataSender(WearableActionHandler.a(e))).updateWishList(c);
            return;
        } else
        {
            FkLogger.verbose(WearableActionHandler.a(), "Request Failed, retrying");
            e.refreshWishList();
            return;
        }
    }

    public final void resultReceived(ProductInfoResponse productinforesponse, boolean flag)
    {
        FkLogger.verbose(WearableActionHandler.a(), (new StringBuilder("Response received:")).append(productinforesponse).toString());
        if (a < b.size())
        {
            WearableActionHandler.a(e, c, b, a);
            return;
        } else
        {
            e.refreshWishList();
            return;
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((ProductInfoResponse)obj, flag);
    }
}
