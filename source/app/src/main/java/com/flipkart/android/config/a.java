// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.config;

import com.flipkart.android.datahandler.ConfigDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.response.appconfig.ConfigResponse;
import com.google.mygson.Gson;
import java.util.HashMap;

// Referenced classes of package com.flipkart.android.config:
//            FlipkartPreferenceManager, ConfigHelper

final class a extends ConfigDataHandler
{

    private ConfigHelper a;

    a(ConfigHelper confighelper)
    {
        a = confighelper;
        super();
    }

    public final void errorReceived(int i, int j, String s)
    {
        HashMap hashmap = new HashMap();
        hashmap.put("ResultCode", Integer.toString(i));
        hashmap.put("ResultMessage", s);
        hashmap.put("ErrorCode", Integer.toString(j));
        ApiLogger.log(LoggerTag.Init, "Non 200 response while reading config", hashmap);
    }

    public final void resultReceived(ConfigResponse configresponse, boolean flag)
    {
        if (configresponse != null)
        {
            com.flipkart.android.response.appconfig.ConfigResponseData configresponsedata = configresponse.getConfig();
            if (configresponsedata != null)
            {
                FlipkartPreferenceManager.instance().saveAppConfig(FlipkartApplication.getGsonInstance().toJson(configresponsedata));
                ConfigHelper.a(a, configresponsedata);
            }
        }
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((ConfigResponse)obj, flag);
    }
}
