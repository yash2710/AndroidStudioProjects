// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.activity.MeasuredActivity;
import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.Harvest;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.measurement.ThreadInfo;
import com.newrelic.agent.android.measurement.consumer.ActivityMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.CustomMetricConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpErrorHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.HttpTransactionHarvestingConsumer;
import com.newrelic.agent.android.measurement.consumer.MeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.MethodMeasurementConsumer;
import com.newrelic.agent.android.measurement.consumer.SummaryMetricMeasurementConsumer;
import com.newrelic.agent.android.measurement.http.HttpTransactionMeasurement;
import com.newrelic.agent.android.measurement.producer.ActivityMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.CustomMetricProducer;
import com.newrelic.agent.android.measurement.producer.HttpErrorMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MeasurementProducer;
import com.newrelic.agent.android.measurement.producer.MethodMeasurementProducer;
import com.newrelic.agent.android.measurement.producer.NetworkMeasurementProducer;
import com.newrelic.agent.android.metric.MetricUnit;
import com.newrelic.agent.android.tracing.Trace;
import java.util.Map;

// Referenced classes of package com.newrelic.agent.android:
//            MeasurementEngine, TaskQueue

public class Measurements
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private static final MeasurementEngine b = new MeasurementEngine();
    private static final HttpErrorMeasurementProducer c = new HttpErrorMeasurementProducer();
    private static final NetworkMeasurementProducer d = new NetworkMeasurementProducer();
    private static final ActivityMeasurementProducer e = new ActivityMeasurementProducer();
    private static final MethodMeasurementProducer f = new MethodMeasurementProducer();
    private static final CustomMetricProducer g = new CustomMetricProducer();
    private static final HttpErrorHarvestingConsumer h = new HttpErrorHarvestingConsumer();
    private static final HttpTransactionHarvestingConsumer i = new HttpTransactionHarvestingConsumer();
    private static final ActivityMeasurementConsumer j = new ActivityMeasurementConsumer();
    private static final MethodMeasurementConsumer k = new MethodMeasurementConsumer();
    private static final SummaryMetricMeasurementConsumer l = new SummaryMetricMeasurementConsumer();
    private static final CustomMetricConsumer m = new CustomMetricConsumer();
    private static boolean n = true;

    public Measurements()
    {
    }

    private static void a()
    {
        if (n)
        {
            broadcast();
        }
    }

    public static void addCustomMetric(String s, String s1, int i1, double d1, double d2)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            g.produceMeasurement(s, s1, i1, d1, d2);
            a();
            return;
        }
    }

    public static void addCustomMetric(String s, String s1, int i1, double d1, double d2, MetricUnit metricunit, 
            MetricUnit metricunit1)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            g.produceMeasurement(s, s1, i1, d1, d2, metricunit, metricunit1);
            a();
            return;
        }
    }

    public static void addHttpError(TransactionData transactiondata, String s, Map map)
    {
        addHttpError(transactiondata.getUrl(), transactiondata.getStatusCode(), s, map);
    }

    public static void addHttpError(String s, int i1)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            c.produceMeasurement(s, i1);
            a();
            return;
        }
    }

    public static void addHttpError(String s, int i1, String s1)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            c.produceMeasurement(s, i1, s1);
            a();
            return;
        }
    }

    public static void addHttpError(String s, int i1, String s1, Map map)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            c.produceMeasurement(s, i1, s1, map);
            a();
            return;
        }
    }

    public static void addHttpError(String s, int i1, String s1, Map map, ThreadInfo threadinfo)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            c.produceMeasurement(s, i1, s1, map, threadinfo);
            a();
            return;
        }
    }

    public static void addHttpTransaction(HttpTransactionMeasurement httptransactionmeasurement)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            d.produceMeasurement(httptransactionmeasurement);
            a();
            return;
        }
    }

    public static void addMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        b.addMeasurementConsumer(measurementconsumer);
    }

    public static void addMeasurementProducer(MeasurementProducer measurementproducer)
    {
        b.addMeasurementProducer(measurementproducer);
    }

    public static void addTracedMethod(Trace trace)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            f.produceMeasurement(trace);
            a();
            return;
        }
    }

    public static void broadcast()
    {
        b.broadcastMeasurements();
    }

    public static void endActivity(MeasuredActivity measuredactivity)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            b.endActivity(measuredactivity);
            e.produceMeasurement(measuredactivity);
            a();
            return;
        }
    }

    public static void endActivity(String s)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            MeasuredActivity measuredactivity = b.endActivity(s);
            e.produceMeasurement(measuredactivity);
            a();
            return;
        }
    }

    public static void endActivityWithoutMeasurement(MeasuredActivity measuredactivity)
    {
        if (Harvest.isDisabled())
        {
            return;
        } else
        {
            b.endActivity(measuredactivity);
            return;
        }
    }

    public static void initialize()
    {
        a.info("Measurement Engine initialized.");
        TaskQueue.start();
        addMeasurementProducer(c);
        addMeasurementProducer(d);
        addMeasurementProducer(e);
        addMeasurementProducer(f);
        addMeasurementProducer(g);
        addMeasurementConsumer(h);
        addMeasurementConsumer(i);
        addMeasurementConsumer(j);
        addMeasurementConsumer(k);
        addMeasurementConsumer(l);
        addMeasurementConsumer(m);
    }

    public static void process()
    {
        b.broadcastMeasurements();
    }

    public static void removeMeasurementConsumer(MeasurementConsumer measurementconsumer)
    {
        b.removeMeasurementConsumer(measurementconsumer);
    }

    public static void removeMeasurementProducer(MeasurementProducer measurementproducer)
    {
        b.removeMeasurementProducer(measurementproducer);
    }

    public static void renameActivity(String s, String s1)
    {
        b.renameActivity(s, s1);
    }

    public static void setBroadcastNewMeasurements(boolean flag)
    {
        n = flag;
    }

    public static void shutdown()
    {
        TaskQueue.stop();
        b.clear();
        a.info("Measurement Engine shutting down.");
        removeMeasurementProducer(c);
        removeMeasurementProducer(d);
        removeMeasurementProducer(e);
        removeMeasurementProducer(f);
        removeMeasurementProducer(g);
        removeMeasurementConsumer(h);
        removeMeasurementConsumer(i);
        removeMeasurementConsumer(j);
        removeMeasurementConsumer(k);
        removeMeasurementConsumer(l);
        removeMeasurementConsumer(m);
    }

    public static MeasuredActivity startActivity(String s)
    {
        if (Harvest.isDisabled())
        {
            return null;
        } else
        {
            return b.startActivity(s);
        }
    }

}
