// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.webview;

import com.flipkart.android.utils.StringUtils;
import java.net.URLDecoder;

public class CookieParser
{

    public CookieParser()
    {
    }

    public static Cookie parse(String s)
    {
        int i;
        Cookie cookie;
        i = 1;
        cookie = new Cookie();
        cookie.nsid = "";
        cookie.vid = "";
        cookie.sn = "";
        if (!StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        return cookie;
_L2:
        String as[] = s.split(";\\s*");
_L4:
        String as1[];
        if (i >= as.length)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (as[i].indexOf('=') <= 0)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        as1 = as[i].split("=");
        if ("SN".equalsIgnoreCase(as1[0]))
        {
            cookie.sn = URLDecoder.decode(as1[1], "UTF-8");
            break MISSING_BLOCK_LABEL_156;
        }
        if ("VID".equalsIgnoreCase(as1[0]))
        {
            cookie.vid = URLDecoder.decode(as1[1], "UTF-8");
            break MISSING_BLOCK_LABEL_156;
        }
        try
        {
            if ("NSID".equalsIgnoreCase(as1[0]))
            {
                cookie.nsid = URLDecoder.decode(as1[1], "UTF-8");
            }
        }
        catch (Exception exception)
        {
            return cookie;
        }
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
    }

    private class Cookie
    {

        public String nsid;
        public String sn;
        public String vid;

        public Cookie()
        {
        }
    }

}
