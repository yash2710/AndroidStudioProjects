// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.producer;

import com.newrelic.agent.android.Agent;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.http.HttpErrorMeasurement;
import com.newrelic.agent.android.util.Util;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android.measurement.producer:
//            BaseMeasurementProducer

public class HttpErrorMeasurementProducer extends BaseMeasurementProducer
{

    public HttpErrorMeasurementProducer()
    {
        super(MeasurementType.HttpError);
    }

    public void produceMeasurement(String s, int i)
    {
        produceMeasurement(s, i, "");
    }

    public void produceMeasurement(String s, int i, String s1)
    {
        produceMeasurement(s, i, s1, null);
    }

    public void produceMeasurement(String s, int i, String s1, Map map)
    {
        produceMeasurement(s, i, s1, map, new ThreadInfo());
    }

    public void produceMeasurement(String s, int i, String s1, Map map, ThreadInfo threadinfo)
    {
        String s2 = Util.sanitizeUrl(s);
        if (s2 == null)
        {
            return;
        }
        HttpErrorMeasurement httperrormeasurement = new HttpErrorMeasurement(s2, i);
        httperrormeasurement.setThreadInfo(threadinfo);
        StringBuilder stringbuilder = new StringBuilder();
        StackTraceElement astacktraceelement[] = Thread.currentThread().getStackTrace();
        int j = 0;
        int k = 0;
        do
        {
label0:
            {
                if (k >= astacktraceelement.length)
                {
                    break label0;
                }
                StackTraceElement stacktraceelement = astacktraceelement[k];
                String s3 = stacktraceelement.getClassName();
                String s4 = stacktraceelement.getMethodName();
                boolean flag;
                if (s3.startsWith("com.newrelic"))
                {
                    flag = true;
                } else
                if (s3.startsWith("dalvik.system.VMStack") && s4.startsWith("getThreadStackTrace"))
                {
                    flag = true;
                } else
                if (s3.startsWith("java.lang.Thread") && s4.startsWith("getStackTrace"))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    stringbuilder.append(stacktraceelement.toString());
                    if (k <= -1 + astacktraceelement.length)
                    {
                        stringbuilder.append("\n");
                    }
                    if (++j >= Agent.getStackTraceLimit())
                    {
                        break label0;
                    }
                }
                k++;
                continue;
            }
            httperrormeasurement.setStackTrace(stringbuilder.toString());
            httperrormeasurement.setResponseBody(s1);
            httperrormeasurement.setParams(map);
            produceMeasurement(((com.newrelic.agent.android.measurement.Measurement) (httperrormeasurement)));
            return;
        } while (true);
    }
}
