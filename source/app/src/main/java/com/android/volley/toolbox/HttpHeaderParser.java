// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class HttpHeaderParser
{

    public HttpHeaderParser()
    {
    }

    public static com.android.volley.Cache.Entry parseCacheHeaders(NetworkResponse networkresponse)
    {
        long l;
        Map map;
        long l1;
        long l2;
        long l3;
        String s1;
        boolean flag;
        l = System.currentTimeMillis();
        map = networkresponse.headers;
        l1 = 0L;
        l2 = 0L;
        l3 = 0L;
        String s = (String)map.get("Date");
        if (s != null)
        {
            l1 = parseDateAsEpoch(s);
        }
        s1 = (String)map.get("Cache-Control");
        flag = false;
        if (s1 == null) goto _L2; else goto _L1
_L1:
        String as[];
        int i;
        flag = true;
        as = s1.split(",");
        i = 0;
_L6:
        if (i >= as.length) goto _L2; else goto _L3
_L3:
        String s4;
        s4 = as[i].trim();
        if (s4.equals("no-cache") || s4.equals("no-store"))
        {
            return null;
        }
        if (!s4.startsWith("max-age=")) goto _L5; else goto _L4
_L4:
        long l6 = Long.parseLong(s4.substring(8));
        l3 = l6;
_L7:
        i++;
          goto _L6
_L5:
        if (s4.equals("must-revalidate") || s4.equals("proxy-revalidate"))
        {
            l3 = 0L;
        }
          goto _L7
_L2:
        boolean flag1 = flag;
        long l4 = l3;
        String s2 = (String)map.get("Expires");
        if (s2 != null)
        {
            l2 = parseDateAsEpoch(s2);
        }
        String s3 = (String)map.get("ETag");
        long l5;
        com.android.volley.Cache.Entry entry;
        Exception exception;
        if (flag1)
        {
            l5 = l + l4 * 1000L;
        } else
        if (l1 > 0L && l2 >= l1)
        {
            l5 = l + (l2 - l1);
        } else
        {
            l5 = 0L;
        }
        entry = new com.android.volley.Cache.Entry();
        entry.data = networkresponse.data;
        entry.etag = s3;
        entry.softTtl = l5;
        entry.ttl = entry.softTtl;
        entry.serverDate = l1;
        entry.responseHeaders = map;
        return entry;
        exception;
          goto _L7
    }

    public static String parseCharset(Map map)
    {
        String s = (String)map.get("Content-Type");
        if (s != null)
        {
            String as[] = s.split(";");
            for (int i = 1; i < as.length; i++)
            {
                String as1[] = as[i].trim().split("=");
                if (as1.length == 2 && as1[0].equals("charset"))
                {
                    return as1[1];
                }
            }

        }
        return "ISO-8859-1";
    }

    public static long parseDateAsEpoch(String s)
    {
        long l;
        try
        {
            l = DateUtils.parseDate(s).getTime();
        }
        catch (DateParseException dateparseexception)
        {
            return 0L;
        }
        return l;
    }
}
