// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.volley;

import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HeaderUtils
{

    public HeaderUtils()
    {
    }

    public static void addAnalyticDataInHeader(Map map, AnalyticData analyticdata)
    {
        FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil();
        Map map1 = analyticdata.getAnalyticDataMap();
        map.putAll(map1);
        String s;
        for (Iterator iterator = map1.keySet().iterator(); iterator.hasNext(); FkLogger.debug((new StringBuilder("DGChanges AD: ")).append(s).append(" ").append((String)map1.get(s)).toString()))
        {
            s = (String)iterator.next();
        }

        removeDataFromAnalyticDataMap(analyticdata);
    }

    public static void addAuthKeyHeader(Map map)
    {
        String s = FlipkartPreferenceManager.instance().getRegisterKey();
        if (!StringUtils.isNullOrEmpty(s))
        {
            map.put("authKey", s);
        }
    }

    public static void addSecureTokenHeaderForHttps(Map map, String s)
    {
        String s1 = FlipkartPreferenceManager.instance().getRegisterKey();
        if (!StringUtils.isNullOrEmpty(s1))
        {
            map.put("secureToken", s1);
            return;
        } else
        {
            map.put("checksum", s);
            return;
        }
    }

    public static void addSessionKeysHeader(Map map)
    {
        String s = FlipkartPreferenceManager.instance().getSn();
        if (!StringUtils.isNullOrEmpty(s))
        {
            map.put("sn", s);
        } else
        {
            String s1 = FlipkartPreferenceManager.instance().getVid();
            if (!StringUtils.isNullOrEmpty(s1))
            {
                map.put("vid", s1);
            }
            String s2 = FlipkartPreferenceManager.instance().getNsid();
            if (!StringUtils.isNullOrEmpty(s2))
            {
                map.put("nsid", s2);
                return;
            }
        }
    }

    public static void addSessionSpecificHeaders(Map map)
    {
        map.put("User-Agent", FlipkartPreferenceManager.instance().getUserAgent());
        map.put("Browser-Name", FlipkartPreferenceManager.instance().getBrowserFamily());
        map.put("Device-Id", FlipkartDeviceInfoProvider.getDeviceId());
    }

    public static void removeDataFromAnalyticDataMap(AnalyticData analyticdata)
    {
        analyticdata.removeIsUserClick();
        analyticdata.removeIsPageFirstLanding();
    }
}
