// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.logging;


// Referenced classes of package com.newrelic.agent.android.logging:
//            AgentLog, NullAgentLog

public class DefaultAgentLog
    implements AgentLog
{

    private AgentLog a;

    public DefaultAgentLog()
    {
        a = new NullAgentLog();
    }

    public void debug(String s)
    {
        this;
        JVM INSTR monitorenter ;
        a.debug(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void error(String s)
    {
        this;
        JVM INSTR monitorenter ;
        a.error(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void error(String s, Throwable throwable)
    {
        this;
        JVM INSTR monitorenter ;
        a.error(s, throwable);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getLevel()
    {
        this;
        JVM INSTR monitorenter ;
        int i = a.getLevel();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void info(String s)
    {
        this;
        JVM INSTR monitorenter ;
        a.info(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setImpl(AgentLog agentlog)
    {
        this;
        JVM INSTR monitorenter ;
        a = agentlog;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setLevel(int i)
    {
        this;
        JVM INSTR monitorenter ;
        a.setLevel(i);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void verbose(String s)
    {
        this;
        JVM INSTR monitorenter ;
        a.verbose(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void warning(String s)
    {
        this;
        JVM INSTR monitorenter ;
        a.warning(s);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }
}
