// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            DataToken

public class HarvestConfiguration
{

    private static HarvestConfiguration o;
    private boolean a;
    private String b;
    private int c;
    private int d[];
    private int e;
    private int f;
    private int g;
    private int h;
    private long i;
    private int j;
    private int k;
    private int l;
    private double m;
    private ActivityTraceConfiguration n;

    public HarvestConfiguration()
    {
        setDefaultValues();
    }

    public static HarvestConfiguration getDefaultHarvestConfiguration()
    {
        if (o != null)
        {
            return o;
        } else
        {
            HarvestConfiguration harvestconfiguration = new HarvestConfiguration();
            o = harvestconfiguration;
            return harvestconfiguration;
        }
    }

    public boolean equals(Object obj)
    {
        if (this != obj) goto _L2; else goto _L1
_L1:
        boolean flag = true;
_L4:
        return flag;
_L2:
        HarvestConfiguration harvestconfiguration;
        String s1;
        flag = false;
        if (obj == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Class class1 = getClass();
        Class class2 = obj.getClass();
        flag = false;
        if (class1 != class2)
        {
            continue; /* Loop/switch isn't completed */
        }
        harvestconfiguration = (HarvestConfiguration)obj;
        boolean flag1 = a;
        boolean flag2 = harvestconfiguration.a;
        flag = false;
        if (flag1 != flag2)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i1 = c;
        int j1 = harvestconfiguration.c;
        flag = false;
        if (i1 != j1)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k1 = e;
        int l1 = harvestconfiguration.e;
        flag = false;
        if (k1 != l1)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i2 = f;
        int j2 = harvestconfiguration.f;
        flag = false;
        if (i2 != j2)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k2 = g;
        int l2 = harvestconfiguration.g;
        flag = false;
        if (k2 != l2)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i3 = h;
        int j3 = harvestconfiguration.h;
        flag = false;
        if (i3 != j3)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k3 = j;
        int l3 = harvestconfiguration.j;
        flag = false;
        if (k3 != l3)
        {
            continue; /* Loop/switch isn't completed */
        }
        int i4 = k;
        int j4 = harvestconfiguration.k;
        flag = false;
        if (i4 != j4)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k4 = l;
        int l4 = harvestconfiguration.l;
        flag = false;
        if (k4 != l4)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (b != null)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = harvestconfiguration.b;
        flag = false;
        if (s1 != null) goto _L4; else goto _L3
_L3:
        String s;
        if (b == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s = harvestconfiguration.b;
        flag = false;
        if (s == null) goto _L4; else goto _L5
_L5:
        boolean flag3;
        if (b == null)
        {
            break; /* Loop/switch isn't completed */
        }
        flag3 = b.equals(harvestconfiguration.b);
        flag = false;
        if (!flag3) goto _L4; else goto _L6
_L6:
        int i5 = 100 * (int)m;
        int j5 = 100 * (int)harvestconfiguration.m;
        flag = false;
        if (i5 == j5)
        {
            return Arrays.equals(d, harvestconfiguration.d);
        }
        if (true) goto _L4; else goto _L7
_L7:
    }

    public int getActivity_trace_max_report_attempts()
    {
        return l;
    }

    public int getActivity_trace_max_size()
    {
        return k;
    }

    public double getActivity_trace_min_utilization()
    {
        return m;
    }

    public ActivityTraceConfiguration getAt_capture()
    {
        return n;
    }

    public String getCross_process_id()
    {
        return b;
    }

    public DataToken getDataToken()
    {
        if (d == null)
        {
            return null;
        } else
        {
            return new DataToken(d[0], d[1]);
        }
    }

    public int getData_report_period()
    {
        return c;
    }

    public int[] getData_token()
    {
        return d;
    }

    public int getError_limit()
    {
        return e;
    }

    public long getReportMaxTransactionAgeMilliseconds()
    {
        return TimeUnit.MILLISECONDS.convert(f, TimeUnit.SECONDS);
    }

    public int getReport_max_transaction_age()
    {
        return f;
    }

    public int getReport_max_transaction_count()
    {
        return g;
    }

    public int getResponse_body_limit()
    {
        return h;
    }

    public long getServer_timestamp()
    {
        return i;
    }

    public int getStack_trace_limit()
    {
        return j;
    }

    public int hashCode()
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        long l2;
        int k2;
        ActivityTraceConfiguration activitytraceconfiguration;
        int i3;
        if (a)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        j1 = i1 * 31;
        if (b != null)
        {
            k1 = b.hashCode();
        } else
        {
            k1 = 0;
        }
        l1 = 31 * (31 * (k1 + j1) + c);
        if (d != null)
        {
            i2 = Arrays.hashCode(d);
        } else
        {
            i2 = 0;
        }
        j2 = 31 * (31 * (31 * (31 * (31 * (31 * (31 * (i2 + l1) + e) + f) + g) + h) + j) + k) + l;
        l2 = Double.doubleToLongBits(m);
        k2 = 31 * (j2 * 31 + (int)(l2 ^ l2 >>> 32));
        activitytraceconfiguration = n;
        i3 = 0;
        if (activitytraceconfiguration != null)
        {
            i3 = n.hashCode();
        }
        return k2 + i3;
    }

    public boolean isCollect_network_errors()
    {
        return a;
    }

    public void reconfigure(HarvestConfiguration harvestconfiguration)
    {
        setCollect_network_errors(harvestconfiguration.isCollect_network_errors());
        if (harvestconfiguration.getCross_process_id() != null)
        {
            setCross_process_id(harvestconfiguration.getCross_process_id());
        }
        setData_report_period(harvestconfiguration.getData_report_period());
        if (harvestconfiguration.getDataToken().isValid())
        {
            setData_token(harvestconfiguration.getData_token());
        }
        setError_limit(harvestconfiguration.getError_limit());
        setReport_max_transaction_age(harvestconfiguration.getReport_max_transaction_age());
        setReport_max_transaction_count(harvestconfiguration.getReport_max_transaction_count());
        setResponse_body_limit(harvestconfiguration.getResponse_body_limit());
        setServer_timestamp(harvestconfiguration.getServer_timestamp());
        setStack_trace_limit(harvestconfiguration.getStack_trace_limit());
        setActivity_trace_min_utilization(harvestconfiguration.getActivity_trace_min_utilization());
        setActivity_trace_max_report_attempts(harvestconfiguration.getActivity_trace_max_report_attempts());
        if (harvestconfiguration.getAt_capture() != null)
        {
            setAt_capture(harvestconfiguration.getAt_capture());
        }
    }

    public void setActivity_trace_max_report_attempts(int i1)
    {
        l = i1;
    }

    public void setActivity_trace_max_size(int i1)
    {
        k = i1;
    }

    public void setActivity_trace_min_utilization(double d1)
    {
        m = d1;
    }

    public void setAt_capture(ActivityTraceConfiguration activitytraceconfiguration)
    {
        n = activitytraceconfiguration;
    }

    public void setCollect_network_errors(boolean flag)
    {
        a = flag;
    }

    public void setCross_process_id(String s)
    {
        b = s;
    }

    public void setData_report_period(int i1)
    {
        c = i1;
    }

    public void setData_token(int ai[])
    {
        d = ai;
    }

    public void setDefaultValues()
    {
        setData_token(new int[2]);
        setCollect_network_errors(true);
        setData_report_period(60);
        setError_limit(50);
        setResponse_body_limit(2048);
        setStack_trace_limit(100);
        setReport_max_transaction_age(600);
        setReport_max_transaction_count(1000);
        setActivity_trace_max_size(65534);
        setActivity_trace_max_report_attempts(1);
        setActivity_trace_min_utilization(0.30000001192092896D);
        setAt_capture(ActivityTraceConfiguration.defaultActivityTraceConfiguration());
    }

    public void setError_limit(int i1)
    {
        e = i1;
    }

    public void setReport_max_transaction_age(int i1)
    {
        f = i1;
    }

    public void setReport_max_transaction_count(int i1)
    {
        g = i1;
    }

    public void setResponse_body_limit(int i1)
    {
        h = i1;
    }

    public void setServer_timestamp(long l1)
    {
        i = l1;
    }

    public void setStack_trace_limit(int i1)
    {
        j = i1;
    }

    public String toString()
    {
        return (new StringBuilder("HarvestConfiguration{collect_network_errors=")).append(a).append(", cross_process_id='").append(b).append('\'').append(", data_report_period=").append(c).append(", data_token=").append(Arrays.toString(d)).append(", error_limit=").append(e).append(", report_max_transaction_age=").append(f).append(", report_max_transaction_count=").append(g).append(", response_body_limit=").append(h).append(", server_timestamp=").append(i).append(", stack_trace_limit=").append(j).append(", activity_trace_max_size=").append(k).append(", activity_trace_max_report_attempts=").append(l).append(", activity_trace_min_utilization=").append(m).append(", at_capture=").append(n).append('}').toString();
    }
}
