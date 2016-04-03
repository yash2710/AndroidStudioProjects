// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.AgentConfiguration;
import com.newrelic.agent.android.TaskQueue;
import com.newrelic.agent.android.activity.config.ActivityTraceConfiguration;
import com.newrelic.agent.android.activity.config.ActivityTraceConfigurationDeserializer;
import com.newrelic.agent.android.logging.AgentLog;
import com.newrelic.agent.android.logging.AgentLogManager;
import com.newrelic.agent.android.stats.StatsEngine;
import com.newrelic.agent.android.tracing.ActivityTrace;
import com.newrelic.com.google.gson.Gson;
import com.newrelic.com.google.gson.GsonBuilder;
import com.newrelic.com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestConfiguration, HarvestResponse, AgentHealth, HarvestLifecycleAware, 
//            HarvestData, Harvest, HttpTransactions, HttpErrors, 
//            ActivityTraces, HarvestConnection, b, DataToken, 
//            HttpError, HttpTransaction, ConnectInformation

public class Harvester
{

    private final AgentLog a = AgentLogManager.getAgentLog();
    private State b;
    private HarvestConnection c;
    private AgentConfiguration d;
    private HarvestConfiguration e;
    private HarvestData f;
    private final Collection g = new ArrayList();
    protected boolean stateChanged;

    public Harvester()
    {
        b = State.UNINITIALIZED;
        e = HarvestConfiguration.getDefaultHarvestConfiguration();
    }

    private HarvestConfiguration a(HarvestResponse harvestresponse)
    {
        GsonBuilder gsonbuilder = new GsonBuilder();
        gsonbuilder.registerTypeAdapter(com/newrelic/agent/android/activity/config/ActivityTraceConfiguration, new ActivityTraceConfigurationDeserializer());
        Gson gson = gsonbuilder.create();
        HarvestConfiguration harvestconfiguration;
        try
        {
            harvestconfiguration = (HarvestConfiguration)gson.fromJson(harvestresponse.getResponseBody(), com/newrelic/agent/android/harvest/HarvestConfiguration);
        }
        catch (JsonSyntaxException jsonsyntaxexception)
        {
            a.error((new StringBuilder("Unable to parse collector configuration: ")).append(jsonsyntaxexception.getMessage()).toString());
            AgentHealth.noticeException(jsonsyntaxexception);
            return null;
        }
        return harvestconfiguration;
    }

    private void a()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestBefore()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestBefore", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void a(HarvestConfiguration harvestconfiguration)
    {
        e.reconfigure(harvestconfiguration);
        f.setDataToken(e.getDataToken());
        Harvest.setHarvestConfiguration(e);
    }

    private static transient boolean a(State state, State astate[])
    {
        int k = astate.length;
        int l = 0;
        do
        {
label0:
            {
                boolean flag = false;
                if (l < k)
                {
                    if (state != astate[l])
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            l++;
        } while (true);
    }

    private void b()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvest()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvest", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void c()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestFinalize()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestFinalize", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void d()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestDisabled()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestDisabled", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void e()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestDisconnected()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestDisconnected", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void f()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestError()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestError", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void g()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestSendFailed()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestSendFailed", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void h()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestComplete()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestComplete", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private void i()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestConnected()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestConnected", exception);
            AgentHealth.noticeException(exception);
        }
    }

    private Collection j()
    {
        return new ArrayList(g);
    }

    public void addHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
label0:
        {
            if (harvestlifecycleaware == null)
            {
                a.error("Can't add null harvest listener");
                (new Exception()).printStackTrace();
                return;
            }
            synchronized (g)
            {
                if (!g.contains(harvestlifecycleaware))
                {
                    break label0;
                }
            }
            return;
        }
        g.add(harvestlifecycleaware);
        collection;
        JVM INSTR monitorexit ;
    }

    protected void connected()
    {
        a.info("Harvester: connected");
        a.info((new StringBuilder("Harvester: Sending ")).append(f.getHttpTransactions().count()).append(" HTTP transactions.").toString());
        a.info((new StringBuilder("Harvester: Sending ")).append(f.getHttpErrors().count()).append(" HTTP errors.").toString());
        a.info((new StringBuilder("Harvester: Sending ")).append(f.getActivityTraces().count()).append(" activity traces.").toString());
        HarvestResponse harvestresponse = c.sendData(f);
        if (harvestresponse == null || harvestresponse.isUnknown())
        {
            g();
            return;
        }
        f.reset();
        StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", harvestresponse.getResponseTime());
        a.debug((new StringBuilder("Harvest data response: ")).append(harvestresponse.getResponseCode()).toString());
        a.debug((new StringBuilder("Harvest data response status code: ")).append(harvestresponse.getStatusCode()).toString());
        a.debug((new StringBuilder("Harvest data response body: ")).append(harvestresponse.getResponseBody()).toString());
        if (harvestresponse.isError())
        {
            f();
            switch (b.a[harvestresponse.getResponseCode().ordinal()])
            {
            default:
                a.error("An unknown error occurred when connecting to the Collector.");
                return;

            case 1: // '\001'
            case 2: // '\002'
                f.getDataToken().clear();
                transition(State.DISCONNECTED);
                return;

            case 3: // '\003'
                if (harvestresponse.isDisableCommand())
                {
                    a.error("Collector has commanded Agent to disable.");
                    transition(State.DISABLED);
                    return;
                } else
                {
                    a.error("Unexpected Collector response: FORBIDDEN");
                    transition(State.DISCONNECTED);
                    return;
                }

            case 4: // '\004'
            case 5: // '\005'
                a.error("Invalid ConnectionInformation was sent to the Collector.");
                return;
            }
        }
        HarvestConfiguration harvestconfiguration = a(harvestresponse);
        if (harvestconfiguration == null)
        {
            a.error("Unable to configure Harvester using Collector configuration.");
            return;
        } else
        {
            a(harvestconfiguration);
            h();
            return;
        }
    }

    protected void disabled()
    {
        Harvest.stop();
        d();
    }

    protected void disconnected()
    {
        HarvestResponse harvestresponse;
        if (f.getDataToken().isValid())
        {
            a.verbose((new StringBuilder("Skipping connect call, saved state is available: ")).append(f.getDataToken()).toString());
            StatsEngine.get().sample("Session/Start", 1.0F);
            i();
            transition(State.CONNECTED);
            execute();
            return;
        }
        a.info((new StringBuilder("Connecting, saved state is not available: ")).append(f.getDataToken()).toString());
        harvestresponse = c.sendConnect();
        if (harvestresponse == null)
        {
            a.error("Unable to connect to the Collector.");
            return;
        }
        if (harvestresponse.isOK())
        {
            HarvestConfiguration harvestconfiguration = a(harvestresponse);
            if (harvestconfiguration == null)
            {
                a.error("Unable to configure Harvester using Collector configuration.");
                return;
            } else
            {
                a(harvestconfiguration);
                StatsEngine.get().sampleTimeMs("Supportability/AgentHealth/Collector/Harvest", harvestresponse.getResponseTime());
                i();
                transition(State.CONNECTED);
                return;
            }
        }
        a.debug((new StringBuilder("Harvest connect response: ")).append(harvestresponse.getResponseCode()).toString());
        b.a[harvestresponse.getResponseCode().ordinal()];
        JVM INSTR tableswitch 1 5: default 264
    //                   1 281
    //                   2 281
    //                   3 296
    //                   4 342
    //                   5 342;
           goto _L1 _L2 _L2 _L3 _L4 _L4
_L1:
        a.error("An unknown error occurred when connecting to the Collector.");
_L6:
        f();
        return;
_L2:
        f.getDataToken().clear();
        e();
        return;
_L3:
        if (harvestresponse.isDisableCommand())
        {
            a.error("Collector has commanded Agent to disable.");
            d();
            transition(State.DISABLED);
            return;
        }
        a.error("Unexpected Collector response: FORBIDDEN");
        continue; /* Loop/switch isn't completed */
_L4:
        a.error("Invalid ConnectionInformation was sent to the Collector.");
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected void execute()
    {
        a.debug((new StringBuilder("Harvester state: ")).append(b).toString());
        stateChanged = false;
        expireHarvestData();
        b.b[b.ordinal()];
        JVM INSTR tableswitch 1 4: default 80
    //                   1 107
    //                   2 112
    //                   3 121
    //                   4 141;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        Exception exception;
        throw new IllegalStateException();
_L2:
        try
        {
            uninitialized();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            a.error("Exception encountered while attempting to harvest", exception);
            AgentHealth.noticeException(exception);
            return;
        }
_L3:
        a();
        disconnected();
        return;
_L4:
        a();
        b();
        c();
        TaskQueue.synchronousDequeue();
        connected();
        return;
_L5:
        disabled();
        return;
    }

    public void expireActivityTraces()
    {
        ActivityTraces activitytraces = f.getActivityTraces();
        activitytraces;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        long l = e.getActivity_trace_max_report_attempts();
        Iterator iterator = activitytraces.getActivityTraces().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            ActivityTrace activitytrace = (ActivityTrace)iterator.next();
            if (activitytrace.getReportAttemptCount() >= l)
            {
                a.debug((new StringBuilder("ActivityTrace has had ")).append(activitytrace.getReportAttemptCount()).append(" report attempts, purging: ").append(activitytrace).toString());
                arraylist.add(activitytrace);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_130;
        Exception exception;
        exception;
        throw exception;
        for (Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); activitytraces.remove((ActivityTrace)iterator1.next())) { }
        activitytraces;
        JVM INSTR monitorexit ;
    }

    public void expireHarvestData()
    {
        expireHttpErrors();
        expireHttpTransactions();
        expireActivityTraces();
    }

    public void expireHttpErrors()
    {
        HttpErrors httperrors = f.getHttpErrors();
        httperrors;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        long l = System.currentTimeMillis();
        long l1 = e.getReportMaxTransactionAgeMilliseconds();
        Iterator iterator = httperrors.getHttpErrors().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            HttpError httperror = (HttpError)iterator.next();
            if (httperror.getTimestamp().longValue() < l - l1)
            {
                a.debug((new StringBuilder("HttpError too old, purging: ")).append(httperror).toString());
                arraylist.add(httperror);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_126;
        Exception exception;
        exception;
        throw exception;
        for (Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); httperrors.removeHttpError((HttpError)iterator1.next())) { }
        httperrors;
        JVM INSTR monitorexit ;
    }

    public void expireHttpTransactions()
    {
        HttpTransactions httptransactions = f.getHttpTransactions();
        httptransactions;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList();
        long l = System.currentTimeMillis();
        long l1 = e.getReportMaxTransactionAgeMilliseconds();
        Iterator iterator = httptransactions.getHttpTransactions().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            HttpTransaction httptransaction = (HttpTransaction)iterator.next();
            if (httptransaction.getTimestamp().longValue() < l - l1)
            {
                a.debug((new StringBuilder("HttpTransaction too old, purging: ")).append(httptransaction).toString());
                arraylist.add(httptransaction);
            }
        } while (true);
        break MISSING_BLOCK_LABEL_126;
        Exception exception;
        exception;
        throw exception;
        for (Iterator iterator1 = arraylist.iterator(); iterator1.hasNext(); httptransactions.remove((HttpTransaction)iterator1.next())) { }
        httptransactions;
        JVM INSTR monitorexit ;
    }

    public State getCurrentState()
    {
        return b;
    }

    public HarvestConnection getHarvestConnection()
    {
        return c;
    }

    public HarvestData getHarvestData()
    {
        return f;
    }

    public boolean isDisabled()
    {
        return State.DISABLED == b;
    }

    public void removeHarvestListener(HarvestLifecycleAware harvestlifecycleaware)
    {
label0:
        {
            synchronized (g)
            {
                if (g.contains(harvestlifecycleaware))
                {
                    break label0;
                }
            }
            return;
        }
        g.remove(harvestlifecycleaware);
        collection;
        JVM INSTR monitorexit ;
    }

    public void setAgentConfiguration(AgentConfiguration agentconfiguration)
    {
        d = agentconfiguration;
    }

    public void setConfiguration(HarvestConfiguration harvestconfiguration)
    {
        e = harvestconfiguration;
    }

    public void setHarvestConnection(HarvestConnection harvestconnection)
    {
        c = harvestconnection;
    }

    public void setHarvestData(HarvestData harvestdata)
    {
        f = harvestdata;
    }

    public void start()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestStart()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestStart", exception);
            AgentHealth.noticeException(exception);
        }
    }

    public void stop()
    {
        try
        {
            for (Iterator iterator = j().iterator(); iterator.hasNext(); ((HarvestLifecycleAware)iterator.next()).onHarvestStop()) { }
        }
        catch (Exception exception)
        {
            a.error("Error in fireOnHarvestStop", exception);
            AgentHealth.noticeException(exception);
        }
    }

    protected void transition(State state)
    {
        if (!stateChanged) goto _L2; else goto _L1
_L1:
        a.debug((new StringBuilder("Ignoring multiple transition: ")).append(state).toString());
_L4:
        return;
_L2:
        if (b == state) goto _L4; else goto _L3
_L3:
        switch (b.b[b.ordinal()])
        {
        default:
            throw new IllegalStateException();

        case 1: // '\001'
            State astate2[] = new State[4];
            astate2[0] = State.DISCONNECTED;
            astate2[1] = state;
            astate2[2] = State.CONNECTED;
            astate2[3] = State.DISABLED;
            if (!a(state, astate2))
            {
                throw new IllegalStateException();
            }
            break;

        case 2: // '\002'
            State astate1[] = new State[3];
            astate1[0] = State.UNINITIALIZED;
            astate1[1] = State.CONNECTED;
            astate1[2] = State.DISABLED;
            if (!a(state, astate1))
            {
                throw new IllegalStateException();
            }
            break;

        case 3: // '\003'
            State astate[] = new State[2];
            astate[0] = State.DISCONNECTED;
            astate[1] = State.DISABLED;
            if (!a(state, astate))
            {
                throw new IllegalStateException();
            }
            break;
        }
        a.debug((new StringBuilder("Harvester changing state: ")).append(b).append(" -> ").append(state).toString());
        if (b != State.CONNECTED) goto _L6; else goto _L5
_L5:
        if (state != State.DISCONNECTED) goto _L8; else goto _L7
_L7:
        e();
_L6:
        b = state;
        stateChanged = true;
        return;
_L8:
        if (state == State.DISABLED)
        {
            d();
        }
        if (true) goto _L6; else goto _L9
_L9:
    }

    protected void uninitialized()
    {
        if (d == null)
        {
            a.error("Agent configuration unavailable.");
            return;
        } else
        {
            c.setConnectInformation(new ConnectInformation());
            c.setApplicationToken(d.getApplicationToken());
            c.setCollectorHost(d.getCollectorHost());
            c.useSsl(d.useSsl());
            transition(State.DISCONNECTED);
            execute();
            return;
        }
    }

    private class State extends Enum
    {

        public static final State CONNECTED;
        public static final State DISABLED;
        public static final State DISCONNECTED;
        public static final State UNINITIALIZED;
        private static final State a[];

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/newrelic/agent/android/harvest/Harvester$State, s);
        }

        public static State[] values()
        {
            return (State[])a.clone();
        }

        static 
        {
            UNINITIALIZED = new State("UNINITIALIZED", 0);
            DISCONNECTED = new State("DISCONNECTED", 1);
            CONNECTED = new State("CONNECTED", 2);
            DISABLED = new State("DISABLED", 3);
            State astate[] = new State[4];
            astate[0] = UNINITIALIZED;
            astate[1] = DISCONNECTED;
            astate[2] = CONNECTED;
            astate[3] = DISABLED;
            a = astate;
        }

        private State(String s, int k)
        {
            super(s, k);
        }
    }

}
