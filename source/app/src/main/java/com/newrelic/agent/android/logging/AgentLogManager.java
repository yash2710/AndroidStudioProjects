// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.logging;


// Referenced classes of package com.newrelic.agent.android.logging:
//            DefaultAgentLog, AgentLog

public class AgentLogManager
{

    private static DefaultAgentLog a = new DefaultAgentLog();

    public AgentLogManager()
    {
    }

    public static AgentLog getAgentLog()
    {
        return a;
    }

    public static void setAgentLog(AgentLog agentlog)
    {
        a.setImpl(agentlog);
    }

}
