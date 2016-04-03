// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.util.Encoder;
import java.util.List;

// Referenced classes of package com.newrelic.agent.android:
//            NullAgentImpl, AgentImpl

public class Agent
{

    public static final String VERSION = "3.429.0";
    private static final AgentImpl a;
    private static Object b = new Object();
    private static AgentImpl c;

    public Agent()
    {
    }

    public static void addTransactionData(TransactionData transactiondata)
    {
        getImpl().addTransactionData(transactiondata);
    }

    public static void disable()
    {
        getImpl().disable();
    }

    public static String getActiveNetworkCarrier()
    {
        return getImpl().getNetworkCarrier();
    }

    public static List getAndClearTransactionData()
    {
        return getImpl().getAndClearTransactionData();
    }

    public static ApplicationInformation getApplicationInformation()
    {
        return getImpl().getApplicationInformation();
    }

    public static String getCrossProcessId()
    {
        return getImpl().getCrossProcessId();
    }

    public static DeviceInformation getDeviceInformation()
    {
        return getImpl().getDeviceInformation();
    }

    public static Encoder getEncoder()
    {
        return getImpl().getEncoder();
    }

    public static AgentImpl getImpl()
    {
        AgentImpl agentimpl;
        synchronized (b)
        {
            agentimpl = c;
        }
        return agentimpl;
    }

    public static int getResponseBodyLimit()
    {
        return getImpl().getResponseBodyLimit();
    }

    public static int getStackTraceLimit()
    {
        return getImpl().getStackTraceLimit();
    }

    public static String getVersion()
    {
        return "3.429.0";
    }

    public static boolean isDisabled()
    {
        return getImpl().isDisabled();
    }

    public static void mergeTransactionData(List list)
    {
        getImpl().mergeTransactionData(list);
    }

    public static void setImpl(AgentImpl agentimpl)
    {
        Object obj = b;
        obj;
        JVM INSTR monitorenter ;
        if (agentimpl != null)
        {
            break MISSING_BLOCK_LABEL_19;
        }
        c = a;
_L2:
        obj;
        JVM INSTR monitorexit ;
        return;
        c = agentimpl;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public static void setLocation(String s, String s1)
    {
        getImpl().setLocation(s, s1);
    }

    public static void start()
    {
        getImpl().start();
    }

    public static void stop()
    {
        getImpl().stop();
    }

    static 
    {
        a = new NullAgentImpl();
        c = a;
    }
}
