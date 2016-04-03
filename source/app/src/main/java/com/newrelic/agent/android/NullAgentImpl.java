// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import com.newrelic.agent.android.api.common.TransactionData;
import com.newrelic.agent.android.harvest.ApplicationInformation;
import com.newrelic.agent.android.harvest.DeviceInformation;
import com.newrelic.agent.android.util.Encoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package com.newrelic.agent.android:
//            AgentImpl, c

public class NullAgentImpl
    implements AgentImpl
{

    public static final NullAgentImpl instance = new NullAgentImpl();

    public NullAgentImpl()
    {
        new ReentrantLock();
    }

    public void addTransactionData(TransactionData transactiondata)
    {
    }

    public void disable()
    {
    }

    public List getAndClearTransactionData()
    {
        return new ArrayList();
    }

    public ApplicationInformation getApplicationInformation()
    {
        return new ApplicationInformation("null", "0.0", "null");
    }

    public String getCrossProcessId()
    {
        return null;
    }

    public DeviceInformation getDeviceInformation()
    {
        DeviceInformation deviceinformation = new DeviceInformation();
        deviceinformation.setOsName("Android");
        deviceinformation.setOsVersion("2.3");
        deviceinformation.setManufacturer("Fake");
        deviceinformation.setModel("NullAgent");
        deviceinformation.setAgentName("AndroidAgent");
        deviceinformation.setAgentVersion("2.123");
        deviceinformation.setDeviceId("389C9738-A761-44DE-8A66-1668CFD67DA1");
        return deviceinformation;
    }

    public Encoder getEncoder()
    {
        return new c(this);
    }

    public String getNetworkCarrier()
    {
        return "unknown";
    }

    public int getResponseBodyLimit()
    {
        return 0;
    }

    public int getStackTraceLimit()
    {
        return 0;
    }

    public boolean isDisabled()
    {
        return true;
    }

    public void mergeTransactionData(List list)
    {
    }

    public void setLocation(String s, String s1)
    {
    }

    public void start()
    {
    }

    public void stop()
    {
    }

}
