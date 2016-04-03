// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datahandler;

import com.android.volley.RequestQueue;
import com.flipkart.android.DB.UGC;
import com.flipkart.android.DB.UGCDao;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.ugc.UGCResponse;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.android.utils.HashUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.volley.request.UGC.UGCRequest;
import com.flipkart.android.volley.request.UGC.params.UGCParam;

// Referenced classes of package com.flipkart.android.datahandler:
//            BaseVDataHandler, g

public abstract class ProductUgcVDataHandler extends BaseVDataHandler
{

    public ProductUgcVDataHandler()
    {
    }

    public ProductUgcVDataHandler(String s, String s1, int i, int j)
    {
        fetchUgcInfo(s, s1, i, j);
    }

    public void fetchUgcInfo(String s, String s1, int i, int j)
    {
        UGCParam ugcparam = new UGCParam(s, s1, i, j);
        String s2 = ugcparam.generateURI();
        UGC ugc = (new UGCDao(FlipkartApplication.getAppContext())).getUgcById(HashUtils.md5(s2));
        UGCResponse ugcresponse = null;
        if (ugc != null)
        {
            long l = AppConfigUtils.getInstance().getUgcDbTimeout();
            int k = ScreenMathUtils.getCurrentLinuxTimeInSeconds() - ugc.getTime() != l;
            ugcresponse = null;
            if (k <= 0)
            {
                ugcresponse = (UGCResponse)GsonUtils.getResponse((new g(this)).getType(), ugc.getResponse(), true);
            }
        }
        if (ugcresponse != null)
        {
            resultReceived(ugcresponse, true);
            return;
        } else
        {
            UGCRequest ugcrequest = new UGCRequest(ugcparam, listner, errorListner);
            request = ugcrequest;
            FlipkartApplication.getRequestQueue().add(ugcrequest);
            return;
        }
    }

    public void resultReceived(UGCResponse ugcresponse, boolean flag)
    {
        if (ugcresponse != null)
        {
            resultReceivedUgcInfo(200, "", ugcresponse);
            return;
        } else
        {
            resultReceivedUgcInfo(4321, "Opps someting went wrong", null);
            return;
        }
    }

    public volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((UGCResponse)obj, flag);
    }

    public abstract void resultReceivedUgcInfo(int i, String s, UGCResponse ugcresponse);
}
