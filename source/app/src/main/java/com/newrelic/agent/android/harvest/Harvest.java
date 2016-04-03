// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.harvest.type.Harvestable;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.metric.Metric;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.agent.android.tracing.Trace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestableCache, HarvestConfiguration, HarvestData, Harvester, 
//            ActivityTraces, AgentHealth, HttpErrors, HttpTransactions, 
//            MachineMeasurements, HarvestTimer, HarvestLifecycleAware, HarvestConnection, 
//            HarvestDataValidator, AgentHealthException, HttpError, HttpTransaction

public class Harvest
{

    private static final AgentLog a = AgentLogManager.getAgentLog();
    private static final Collection f = new ArrayList();
    private static final HarvestableCache g = new HarvestableCache();
    protected static Harvest instance = new Harvest();
    private Harvester b;
    private HarvestConnection c;
    private HarvestTimer d;
    private HarvestDataValidator e;
    private HarvestConfiguration h;
    protected HarvestData harvestData;

    public Harvest()
    {
        h = HarvestConfiguration.getDefaultHarvestConfiguration();
    }

    private static boolean a(HarvestLifecycleAware harvestlifecycleaware)
    {
        if (harvestlifecycleaware == null)
        {
            return false;
        } else
        {
            return f.contains(harvestlifecycleaware);
        }
    }

    public static void addActivityTrace(ActivityTrace activitytrace)
    {
        if (isDisabled())
        {
            return;
        }
        if (!isInitialized())
        {
            g.add(activitytrace);
            return;
        }
        if (activitytrace.rootTrace == null)
        {
            a.error("Activity trace is lacking a root trace!");
            return;
        }
        if (activitytrace.rootTrace.childExclusiveTime == 0L)
        {
            a.error((new StringBuilder("Total trace exclusive time is zero. Ignoring trace ")).append(activitytrace.rootTrace.displayName).toString());
            return;
        }
        double d1 = (double)activitytrace.rootTrace.childExclusiveTime / 1000D;
        if (d1 < instance.getConfiguration().getActivity_trace_min_utilization())
        {
            StatsEngine.get().inc("Supportability/AgentHealth/IgnoredTraces");
            a.debug((new StringBuilder("Total trace exclusive time is too low (")).append(d1).append("). Ignoring trace ").append(activitytrace.rootTrace.displayName).toString());
            return;
        }
        ActivityTraces activitytraces = instance.getHarvestData().getActivityTraces();
        ActivityTraceConfiguration activitytraceconfiguration = instance.getActivityTraceConfiguration();
        instance.getHarvester().expireActivityTraces();
        if (activitytraces.count() >= activitytraceconfiguration.getMaxTotalTraceCount())
        {
            a.debug((new StringBuilder("Activity trace limit of ")).append(activitytraceconfiguration.getMaxTotalTraceCount()).append(" exceeded. Ignoring trace: ").append(activitytrace.toJsonString()).toString());
            return;
        } else
        {
            a.debug((new StringBuilder("Adding activity trace: ")).append(activitytrace.toJsonString()).toString());
            activitytraces.add(activitytrace);
            return;
        }
    }

    public static void addAgentHealthException(AgentHealthException agenthealthexception)
    {
        if (isDisabled() || !isInitialized())
        {
            return;
        } else
        {
            instance.getHarvestData().getAgentHealth().addException(agenthealthexception);
            return;
        }
    }

    public static void addHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if (harvestlifecycleaware == null)
        {
            a.error("Harvest: Argument to addHarvestListener cannot be null.");
        } else
        if (!isInitialized())
        {
            if (!a(harvestlifecycleaware) && harvestlifecycleaware != null)
            {
                synchronized (f)
                {
                    f.add(harvestlifecycleaware);
                }
                return;
            }
        } else
        {
            instance.getHarvester().addHarvestListener(harvestlifecycleaware);
            return;
        }
    }

    public static void addHttpError(HttpError httperror)
    {
        if (!instance.shouldCollectNetworkErrors() || isDisabled())
        {
            return;
        }
        HttpErrors httperrors = instance.getHarvestData().getHttpErrors();
        instance.getHarvester().expireHttpErrors();
        int i = instance.getConfiguration().getError_limit();
        if (httperrors.count() >= i)
        {
            StatsEngine.get().inc("Supportability/AgentHealth/ErrorsDropped");
            a.debug((new StringBuilder("Maximum number of HTTP errors (")).append(i).append(") reached. HTTP Error dropped.").toString());
            return;
        } else
        {
            httperrors.addHttpError(httperror);
            return;
        }
    }

    public static void addHttpTransaction(HttpTransaction httptransaction)
    {
        if (isDisabled())
        {
            return;
        }
        HttpTransactions httptransactions = instance.getHarvestData().getHttpTransactions();
        instance.getHarvester().expireHttpTransactions();
        int i = instance.getConfiguration().getReport_max_transaction_count();
        if (httptransactions.count() >= i)
        {
            StatsEngine.get().inc("Supportability/AgentHealth/TransactionsDropped");
            a.debug((new StringBuilder("Maximum number of transactions (")).append(i).append(") reached. HTTP Transaction dropped.").toString());
            return;
        } else
        {
            httptransactions.add(httptransaction);
            return;
        }
    }

    public static void addMetric(Metric metric)
    {
        if (isDisabled() || !isInitialized())
        {
            return;
        } else
        {
            instance.getHarvestData().getMetrics().addMetric(metric);
            return;
        }
    }

    public static int getActivityTraceCacheSize()
    {
        return g.getSize();
    }

    public static HarvestConfiguration getHarvestConfiguration()
    {
        if (!isInitialized())
        {
            return HarvestConfiguration.getDefaultHarvestConfiguration();
        } else
        {
            return instance.getConfiguration();
        }
    }

    public static Harvest getInstance()
    {
        return instance;
    }

    public static void harvestNow()
    {
        if (!isInitialized())
        {
            return;
        } else
        {
            StatsEngine.get().sampleTimeMs("Session/Duration", instance.d.timeSinceStart());
            instance.d.tickNow();
            return;
        }
    }

    public static void initialize(AgentConfiguration agentconfiguration)
    {
        instance.initializeHarvester(agentconfiguration);
        for (Iterator iterator = f.iterator(); iterator.hasNext(); addHarvestListener((HarvestLifecycleAware)iterator.next())) { }
        f.clear();
        addHarvestListener(StatsEngine.get());
    }

    public static boolean isDisabled()
    {
        if (!isInitialized())
        {
            return false;
        } else
        {
            return instance.getHarvester().isDisabled();
        }
    }

    public static boolean isInitialized()
    {
        return instance.getHarvester() != null;
    }

    public static void removeHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
        if (harvestlifecycleaware == null)
        {
            a.error("Harvest: Argument to removeHarvestListener cannot be null.");
        } else
        if (!isInitialized())
        {
            if (a(harvestlifecycleaware) && harvestlifecycleaware != null)
            {
                synchronized (f)
                {
                    f.remove(harvestlifecycleaware);
                }
                return;
            }
        } else
        {
            instance.getHarvester().removeHarvestListener(harvestlifecycleaware);
            return;
        }
    }

    public static void setHarvestConfiguration(HarvestConfiguration harvestconfiguration)
    {
        if (!isInitialized())
        {
            a.error("Cannot configure Harvester before initialization.");
            (new Exception()).printStackTrace();
            return;
        } else
        {
            a.debug((new StringBuilder("Harvest Configuration: ")).append(harvestconfiguration).toString());
            instance.setConfiguration(harvestconfiguration);
            return;
        }
    }

    public static void setInstance(Harvest harvest)
    {
        instance = harvest;
    }

    public static void setPeriod(long l)
    {
        instance.d.setPeriod(l);
    }

    public static boolean shouldCollectActivityTraces()
    {
        if (!isDisabled())
        {
            if (!isInitialized())
            {
                return true;
            }
            ActivityTraceConfiguration activitytraceconfiguration = instance.getActivityTraceConfiguration();
            if (activitytraceconfiguration == null)
            {
                return true;
            }
            if (activitytraceconfiguration.getMaxTotalTraceCount() > 0)
            {
                return true;
            }
        }
        return false;
    }

    public static void shutdown()
    {
        if (!isInitialized())
        {
            return;
        } else
        {
            stop();
            instance.shutdownHarvester();
            return;
        }
    }

    public static void start()
    {
        instance.d.start();
    }

    public static void stop()
    {
        instance.d.stop();
    }

    public void createHarvester()
    {
        c = new HarvestConnection();
        harvestData = new HarvestData();
        b = new Harvester();
        b.setHarvestConnection(c);
        b.setHarvestData(harvestData);
        d = new HarvestTimer(b);
        e = new HarvestDataValidator();
        addHarvestListener(e);
    }

    protected ActivityTraceConfiguration getActivityTraceConfiguration()
    {
        return h.getAt_capture();
    }

    public HarvestConfiguration getConfiguration()
    {
        return h;
    }

    public HarvestConnection getHarvestConnection()
    {
        return c;
    }

    public HarvestData getHarvestData()
    {
        return harvestData;
    }

    protected Harvester getHarvester()
    {
        return b;
    }

    public void initializeHarvester(AgentConfiguration agentconfiguration)
    {
        createHarvester();
        b.setAgentConfiguration(agentconfiguration);
        b.setConfiguration(instance.getConfiguration());
        try
        {
            for (Iterator iterator = g.flush().iterator(); iterator.hasNext(); addActivityTrace((ActivityTrace)(Harvestable)iterator.next())) { }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void setConfiguration(HarvestConfiguration harvestconfiguration)
    {
        h.reconfigure(harvestconfiguration);
        d.setPeriod(TimeUnit.MILLISECONDS.convert(h.getData_report_period(), TimeUnit.SECONDS));
        c.setServerTimestamp(h.getServer_timestamp());
        harvestData.setDataToken(h.getDataToken());
        b.setConfiguration(h);
    }

    public void setHarvestConnection(HarvestConnection harvestconnection)
    {
        c = harvestconnection;
    }

    public boolean shouldCollectNetworkErrors()
    {
        return h.isCollect_network_errors();
    }

    public void shutdownHarvester()
    {
        d = null;
        b = null;
        c = null;
        harvestData = null;
    }

}
