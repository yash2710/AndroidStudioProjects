// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestAdapter, Harvest, HarvestData, ActivityTraces, 
//            MachineMeasurements

public class HarvestDataValidator extends HarvestAdapter
{

    public HarvestDataValidator()
    {
    }

    public void ensureActivityNameMetricsExist()
    {
        HarvestData harvestdata;
        ActivityTraces activitytraces;
        harvestdata = Harvest.getInstance().getHarvestData();
        activitytraces = harvestdata.getActivityTraces();
        if (activitytraces != null && activitytraces.count() != 0) goto _L2; else goto _L1
_L1:
        MachineMeasurements machinemeasurements;
        return;
_L2:
        if ((machinemeasurements = harvestdata.getMetrics()) == null || machinemeasurements.isEmpty()) goto _L1; else goto _L3
_L3:
        Iterator iterator = activitytraces.getActivityTraces().iterator();
_L5:
        if (!iterator.hasNext()) goto _L1; else goto _L4
_L4:
        String s1;
        boolean flag;
        String s = ((ActivityTrace)iterator.next()).rootTrace.displayName;
        int i = s.indexOf("#");
        if (i > 0)
        {
            s = s.substring(0, i);
        }
        s1 = (new StringBuilder("Mobile/Activity/Name/")).append(s).toString();
        List list = machinemeasurements.getMetrics().getAllUnscoped();
        if (list == null || list.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_210;
        }
        Iterator iterator1 = list.iterator();
        do
        {
            if (!iterator1.hasNext())
            {
                break MISSING_BLOCK_LABEL_210;
            }
        } while (!((Metric)iterator1.next()).getName().startsWith(s1));
        flag = true;
_L6:
        if (!flag)
        {
            machinemeasurements.addMetric(new Metric(s1));
        }
          goto _L5
          goto _L1
        flag = false;
          goto _L6
    }

    public void onHarvestFinalize()
    {
        if (!Harvest.isInitialized())
        {
            return;
        } else
        {
            ensureActivityNameMetricsExist();
            return;
        }
    }
}
