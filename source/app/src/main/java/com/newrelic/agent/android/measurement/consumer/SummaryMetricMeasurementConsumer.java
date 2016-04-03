// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.measurement.consumer;

import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.instrumentation.MetricCategory;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.BaseMeasurement;
import com.newrelic.agent.android.measurement.CustomMetricMeasurement;
import com.newrelic.agent.android.measurement.Measurement;
import com.newrelic.agent.android.measurement.MeasurementType;
import com.newrelic.agent.android.measurement.MethodMeasurement;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.metric.MetricStore;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceLifecycleAware;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

// Referenced classes of package com.newrelic.agent.android.measurement.consumer:
//            MetricMeasurementConsumer, a

public class SummaryMetricMeasurementConsumer extends MetricMeasurementConsumer
    implements TraceLifecycleAware
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private final List b = new CopyOnWriteArrayList();

    public SummaryMetricMeasurementConsumer()
    {
        super(MeasurementType.Any);
        recordUnscopedMetrics = false;
        TraceMachine.addTraceListener(this);
    }

    public void consumeMeasurement(Measurement measurement)
    {
        if (measurement != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        a.a[measurement.getType().ordinal()];
        JVM INSTR tableswitch 1 3: default 44
    //                   1 45
    //                   2 125
    //                   3 156;
           goto _L3 _L4 _L5 _L6
_L3:
        return;
_L4:
        MethodMeasurement methodmeasurement;
        methodmeasurement = (MethodMeasurement)measurement;
        if (methodmeasurement.getCategory() != null && methodmeasurement.getCategory() != MetricCategory.NONE)
        {
            break; /* Loop/switch isn't completed */
        }
        methodmeasurement.setCategory(MetricCategory.categoryForMethod(methodmeasurement.getName()));
        if (methodmeasurement.getCategory() == MetricCategory.NONE) goto _L1; else goto _L7
_L7:
        BaseMeasurement basemeasurement2 = new BaseMeasurement(methodmeasurement);
        basemeasurement2.setName(methodmeasurement.getCategory().getCategoryName());
        super.consumeMeasurement(basemeasurement2);
        return;
_L5:
        BaseMeasurement basemeasurement1 = new BaseMeasurement((HttpTransactionMeasurement)measurement);
        basemeasurement1.setName(MetricCategory.NETWORK.getCategoryName());
        super.consumeMeasurement(basemeasurement1);
        return;
_L6:
        CustomMetricMeasurement custommetricmeasurement = (CustomMetricMeasurement)measurement;
        if (custommetricmeasurement.getCategory() != null && custommetricmeasurement.getCategory() != MetricCategory.NONE)
        {
            BaseMeasurement basemeasurement = new BaseMeasurement(custommetricmeasurement);
            basemeasurement.setName(custommetricmeasurement.getCategory().getCategoryName());
            super.consumeMeasurement(basemeasurement);
            return;
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L8
_L8:
        if (true) goto _L1; else goto _L9
_L9:
    }

    protected String formatMetricName(String s)
    {
        return (new StringBuilder("Mobile/Summary/")).append(s.replace("#", "/")).toString();
    }

    public void onEnterMethod()
    {
    }

    public void onExitMethod()
    {
    }

    public void onHarvest()
    {
        while (metrics.getAll().size() == 0 || b.size() == 0) 
        {
            return;
        }
        for (Iterator iterator = b.iterator(); iterator.hasNext();)
        {
            Trace trace = ((ActivityTrace)iterator.next()).rootTrace;
            List list = metrics.removeAllWithScope(trace.metricName);
            List list1 = metrics.removeAllWithScope(trace.metricBackgroundName);
            HashMap hashmap = new HashMap();
            Metric metric3;
            for (Iterator iterator1 = list.iterator(); iterator1.hasNext(); hashmap.put(metric3.getName(), metric3))
            {
                metric3 = (Metric)iterator1.next();
            }

            for (Iterator iterator2 = list1.iterator(); iterator2.hasNext();)
            {
                Metric metric2 = (Metric)iterator2.next();
                if (hashmap.containsKey(metric2.getName()))
                {
                    ((Metric)hashmap.get(metric2.getName())).aggregate(metric2);
                } else
                {
                    hashmap.put(metric2.getName(), metric2);
                }
            }

            Iterator iterator3 = hashmap.values().iterator();
            double d;
            for (d = 0.0D; iterator3.hasNext(); d += ((Metric)iterator3.next()).getExclusive()) { }
            double d1 = (double)(trace.exitTimestamp - trace.entryTimestamp) / 1000D;
            Iterator iterator4 = hashmap.values().iterator();
            while (iterator4.hasNext()) 
            {
                Metric metric = (Metric)iterator4.next();
                double d2;
                double d3;
                Metric metric1;
                if (metric.getExclusive() != 0.0D && d != 0.0D)
                {
                    d2 = metric.getExclusive() / d;
                } else
                {
                    d2 = 0.0D;
                }
                d3 = d2 * d1;
                metric.setTotal(Double.valueOf(d3));
                metric.setExclusive(Double.valueOf(d3));
                metric.setMinFieldValue(Double.valueOf(0.0D));
                metric.setMaxFieldValue(Double.valueOf(0.0D));
                metric.setSumOfSquares(Double.valueOf(0.0D));
                metric.setScope((new StringBuilder("Mobile/Activity/Summary/Name/")).append(trace.displayName).toString());
                Harvest.addMetric(metric);
                metric1 = new Metric(metric);
                metric1.setScope(null);
                Harvest.addMetric(metric1);
            }
        }

        if (metrics.getAll().size() != 0)
        {
            a.debug("Not all metrics were summarized!");
        }
        b.clear();
    }

    public void onHarvestComplete()
    {
    }

    public void onHarvestError()
    {
    }

    public void onTraceComplete(ActivityTrace activitytrace)
    {
        if (!b.contains(activitytrace))
        {
            b.add(activitytrace);
        }
    }

    public void onTraceStart(ActivityTrace activitytrace)
    {
    }

}
