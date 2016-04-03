// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;


public class AgentConfiguration
{

    private String a;
    private String b;
    private boolean c;

    public AgentConfiguration()
    {
        a = "mobile-collector.newrelic.com";
    }

    public String getApplicationToken()
    {
        return b;
    }

    public String getCollectorHost()
    {
        return a;
    }

    public void setApplicationToken(String s)
    {
        b = s;
    }

    public void setCollectorHost(String s)
    {
        a = s;
    }

    public void setUseSsl(boolean flag)
    {
        c = flag;
    }

    public boolean useSsl()
    {
        return c;
    }
}
