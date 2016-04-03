// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;

import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.StringUtils;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.flipkart.android.log:
//            LoggerTag

public abstract class ApiLoggerBase
{

    public static String DELIMITER = "---&&---";
    public static int LENGTH_OF_TAG = 4;

    public ApiLoggerBase()
    {
    }

    protected static String buildString(LoggerTag loggertag, String s, Exception exception, Map map)
    {
        if (map == null)
        {
            map = new HashMap();
        }
        if (!StringUtils.isNullOrEmpty(s))
        {
            map.put("CustomMessage", s);
        }
        if (exception != null)
        {
            StringWriter stringwriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringwriter));
            map.put("Exception", stringwriter.toString());
            map.put("ExceptionMessage", exception.getMessage());
        }
        if (loggertag != null)
        {
            map.put("TAG", loggertag.name());
        }
        map.put("AppVersionNumber", Integer.toString(FlipkartDeviceInfoProvider.getAppVersionNumber()));
        map.put("AppVersionCode", FlipkartDeviceInfoProvider.getAppVersionCode());
        map.put("DeviceId", FlipkartDeviceInfoProvider.getReadableDeviceId());
        map.put("OS", FlipkartDeviceInfoProvider.getOsVersion());
        map.put("ConnectionType", Integer.toString(NetworkMonitor.isNetworkFast()));
        JSONObject jsonobject = new JSONObject();
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            try
            {
                jsonobject.put(s1, map.get(s1));
            }
            catch (JSONException jsonexception) { }
        }

        if (!(jsonobject instanceof JSONObject))
        {
            return jsonobject.toString();
        } else
        {
            return JSONObjectInstrumentation.toString((JSONObject)jsonobject);
        }
    }

}
