// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.datagovernance;

import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.volley.HeaderUtils;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.fk_android_batchnetworking.BatchNetworking;
import com.flipkart.fk_android_batchnetworking.JSONDataHandler;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchNetworkingHandler
{

    public BatchNetworkingHandler()
    {
    }

    public static void initNewBatchHandler(String s, String s1)
    {
        try
        {
            JSONDataHandler jsondatahandler = new JSONDataHandler(s, (new StringBuilder()).append(GsonRequest.BASE_API_URL).append("/2").append(s1).toString());
            FlipkartApplication.getJsonDataHandlers().add(jsondatahandler);
            BatchNetworking.getDefaultInstance().setGroupDataHandler(jsondatahandler);
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
    }

    public static void updateSessionHeaders()
    {
        ArrayList arraylist;
        arraylist = FlipkartApplication.getJsonDataHandlers();
        if (FlipkartApplication.getJsonDataHandlers() == null)
        {
            return;
        }
          goto _L1
_L3:
        int i;
        if (i >= arraylist.size())
        {
            break MISSING_BLOCK_LABEL_67;
        }
        HashMap hashmap = ((JSONDataHandler)arraylist.get(i)).getCustomHttpHeaders();
        String s = FlipkartPreferenceManager.instance().getSn();
        if (!StringUtils.isNullOrEmpty(s))
        {
            hashmap.put("sn", s);
        }
        HeaderUtils.addSessionSpecificHeaders(hashmap);
        i++;
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        return;
_L1:
        i = 0;
        if (true) goto _L3; else goto _L2
_L2:
    }
}
