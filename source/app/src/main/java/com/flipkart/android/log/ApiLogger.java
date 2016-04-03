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
//            ApiLoggerBase, b, a, LoggerTag

public class ApiLogger extends ApiLoggerBase
{

    public static String TAG = "EXCP";
    public static String TAG_ERROR = "ERROR";
    private static ArrayList a = new ArrayList();
    public static boolean isCheckingMaxLimit = false;
    public static boolean isSendingError = false;
    public static boolean isSendingLogs = false;

    public ApiLogger()
    {
    }

    private static void a()
    {
label0:
        {
label1:
            {
                if (!isCheckingMaxLimit && !isSendingLogs)
                {
                    isSendingLogs = true;
                    ArrayList arraylist = a;
                    if ((long)arraylist.size() % AppConfigUtils.getInstance().getLoggingEntriesTimeoutCount() != 0L)
                    {
                        break label0;
                    }
                    int i = arraylist.size();
                    if (i <= 0)
                    {
                        break label1;
                    }
                    StringBuilder stringbuilder = StringUtils.join(arraylist, DELIMITER);
                    (new b(i)).sendLog(stringbuilder.toString(), TAG);
                }
                return;
            }
            isSendingLogs = false;
            return;
        }
        isSendingLogs = false;
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

    private static void b()
    {
        int i;
        if ((long)a.size() <= AppConfigUtils.getInstance().getLoggingEntriesMaxCount())
        {
            break MISSING_BLOCK_LABEL_62;
        }
        isCheckingMaxLimit = true;
        i = 0;
_L2:
        long l = i;
        if (l >= AppConfigUtils.getInstance().getLoggingEntriesMaxCount())
        {
            break; /* Loop/switch isn't completed */
        }
        a.remove(0);
        i++;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        a.clear();
_L1:
        isCheckingMaxLimit = false;
    }

    public static void log(LoggerTag loggertag, String s, Map map)
    {
        if (!AppConfigUtils.getInstance().isLoggingEnabled())
        {
            return;
        }
        String s1 = buildString(loggertag, s, null, map);
        if (!StringUtils.isNullOrEmpty(s1))
        {
            a.add(s1);
        }
        a();
        b();
    }

    public static void logError(LoggerTag loggertag, String s, Exception exception, Map map)
    {
        String s1;
        if (AppConfigUtils.getInstance().isLoggingEnabled())
        {
            if (!StringUtils.isNullOrEmpty(s1 = buildString(loggertag, s, exception, map)))
            {
                String s2 = (new StringBuilder()).append(s1).append(DELIMITER).toString();
                (new a()).sendLog(s2, TAG_ERROR);
                return;
            }
        }
    }

    public static void logException(LoggerTag loggertag, String s, Exception exception, Map map)
    {
        if (!AppConfigUtils.getInstance().isLoggingEnabled())
        {
            return;
        }
        String s1 = buildString(loggertag, s, exception, map);
        if (!StringUtils.isNullOrEmpty(s1))
        {
            a.add(s1);
        }
        a();
        b();
    }

}
