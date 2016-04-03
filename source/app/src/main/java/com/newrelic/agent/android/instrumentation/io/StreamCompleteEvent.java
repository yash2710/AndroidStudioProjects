// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.io;

import java.util.EventObject;

public final class StreamCompleteEvent extends EventObject
{

    private final long a;
    private final Exception b;

    public StreamCompleteEvent(Object obj, long l)
    {
        this(obj, l, null);
    }

    public StreamCompleteEvent(Object obj, long l, Exception exception)
    {
        super(obj);
        a = l;
        b = exception;
    }

    public final long getBytes()
    {
        return a;
    }

    public final Exception getException()
    {
        return b;
    }

    public final boolean isError()
    {
        return b != null;
    }
}
