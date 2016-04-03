// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;


public final class TraceType extends Enum
{

    public static final TraceType NETWORK;
    public static final TraceType TRACE;
    private static final TraceType a[];

    private TraceType(String s, int i)
    {
        super(s, i);
    }

    public static TraceType valueOf(String s)
    {
        return (TraceType)Enum.valueOf(com/newrelic/agent/android/tracing/TraceType, s);
    }

    public static TraceType[] values()
    {
        return (TraceType[])a.clone();
    }

    static 
    {
        TRACE = new TraceType("TRACE", 0);
        NETWORK = new TraceType("NETWORK", 1);
        TraceType atracetype[] = new TraceType[2];
        atracetype[0] = TRACE;
        atracetype[1] = NETWORK;
        a = atracetype;
    }
}
