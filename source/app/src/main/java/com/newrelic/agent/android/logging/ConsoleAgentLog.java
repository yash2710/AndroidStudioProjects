// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.logging;

import java.io.PrintStream;

// Referenced classes of package com.newrelic.agent.android.logging:
//            AgentLog

public class ConsoleAgentLog
    implements AgentLog
{

    private int a;

    public ConsoleAgentLog()
    {
        a = 3;
    }

    private static void a(String s, String s1)
    {
        System.out.println((new StringBuilder("[")).append(s).append("] ").append(s1).toString());
    }

    public void debug(String s)
    {
        if (a == 5)
        {
            a("DEBUG", s);
        }
    }

    public void error(String s)
    {
        if (a > 0)
        {
            a("ERROR", s);
        }
    }

    public void error(String s, Throwable throwable)
    {
        if (a > 0)
        {
            a("ERROR", (new StringBuilder()).append(s).append(" ").append(throwable.getMessage()).toString());
        }
    }

    public int getLevel()
    {
        return a;
    }

    public void info(String s)
    {
        if (a >= 3)
        {
            a("INFO", s);
        }
    }

    public void setLevel(int i)
    {
        a = i;
    }

    public void verbose(String s)
    {
        if (a >= 4)
        {
            a("VERBOSE", s);
        }
    }

    public void warning(String s)
    {
        if (a >= 2)
        {
            a("WARN", s);
        }
    }
}
