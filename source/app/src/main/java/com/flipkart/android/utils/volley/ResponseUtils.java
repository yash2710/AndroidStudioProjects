// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.volley;

import com.android.volley.NetworkResponse;
import com.flipkart.android.utils.StringUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class ResponseUtils
{

    public ResponseUtils()
    {
    }

    public static Reader getJsonReader(NetworkResponse networkresponse)
    {
        boolean flag;
        ByteArrayInputStream bytearrayinputstream;
        InputStreamReader inputstreamreader;
        try
        {
            flag = isResponseGZipped(networkresponse);
            bytearrayinputstream = new ByteArrayInputStream(networkresponse.data);
        }
        catch (Exception exception)
        {
            return null;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        return new InputStreamReader(bytearrayinputstream);
        inputstreamreader = new InputStreamReader(new GZIPInputStream(bytearrayinputstream));
        return inputstreamreader;
    }

    public static boolean isResponseGZipped(NetworkResponse networkresponse)
    {
        String s = (String)networkresponse.headers.get("Content-Encoding");
        return !StringUtils.isNullOrEmpty(s) && s.contains("gzip");
    }
}
