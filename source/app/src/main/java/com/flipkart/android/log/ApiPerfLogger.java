// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.log;

import com.flipkart.android.datahandler.LoggerVDataHandler;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.log:
//            ApiLoggerBase, LoggerTag, c

public class ApiPerfLogger extends ApiLoggerBase
{

    public static String TAG = "PERF";
    private static ArrayList a = new ArrayList();
    public static boolean isCheckingMaxLimit = false;
    public static boolean isSendingLogs = false;

    public ApiPerfLogger()
    {
    }

    static void a(int i)
    {
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        a.remove(0);
        j++;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        a.clear();
_L1:
    }

    public static void log(Map map)
    {
        if (AppConfigUtils.getInstance().isPerfLoggingEnabled()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s = buildString(LoggerTag.Perf, null, null, map);
        if (s.length() > 0)
        {
            a.add(s);
        }
        if (!isCheckingMaxLimit && !isSendingLogs)
        {
            isSendingLogs = true;
            if ((long)a.size() % AppConfigUtils.getInstance().getLoggingEntriesTimeoutCount() == 0L)
            {
                int j = a.size();
                int i;
                long l;
                if (j > 0)
                {
                    StringBuilder stringbuilder = StringUtils.join(a, DELIMITER);
                    (new c(j)).sendLog(stringbuilder.toString(), TAG);
                } else
                {
                    isSendingLogs = false;
                }
            } else
            {
                isSendingLogs = false;
            }
        }
        if ((long)a.size() <= AppConfigUtils.getInstance().getLoggingEntriesMaxCount()) goto _L1; else goto _L3
_L3:
        isCheckingMaxLimit = true;
        i = 0;
_L5:
        l = i;
        if (l >= AppConfigUtils.getInstance().getLoggingEntriesMaxCount())
        {
            break; /* Loop/switch isn't completed */
        }
        a.remove(0);
        i++;
        if (true) goto _L5; else goto _L4
        Exception exception;
        exception;
        a.clear();
_L4:
        isCheckingMaxLimit = false;
        return;
    }

}
