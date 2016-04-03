// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.config.FlipkartPreferenceManager;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils

public class RequestHeaderUtils
{

    public RequestHeaderUtils()
    {
    }

    public static void appendRelavantHeaders(Map map, boolean flag)
    {
        String s = FlipkartPreferenceManager.instance().getSn();
        if (StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        map.put("sn", s);
_L4:
        if (flag)
        {
            String s4 = FlipkartPreferenceManager.instance().getSecureToken();
            if (!StringUtils.isNullOrEmpty(s4))
            {
                map.put("secureToken", s4);
            }
        }
        String s3 = FlipkartPreferenceManager.instance().getRegisterKey();
        if (!StringUtils.isNullOrEmpty(s3))
        {
            map.put("authKey", s3);
        }
        return;
_L2:
        String s1 = FlipkartPreferenceManager.instance().getVid();
        if (!StringUtils.isNullOrEmpty(s1))
        {
            map.put("vid", s1);
        }
        String s2 = FlipkartPreferenceManager.instance().getNsid();
        if (!StringUtils.isNullOrEmpty(s2))
        {
            map.put("nsid", s2);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
