// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.api.v1;

import com.newrelic.agent.android.api.common.ConnectionState;
import java.util.EventObject;

public final class ConnectionEvent extends EventObject
{

    private final ConnectionState a;

    public ConnectionEvent(Object obj)
    {
        this(obj, null);
    }

    public ConnectionEvent(Object obj, ConnectionState connectionstate)
    {
        super(obj);
        a = connectionstate;
    }

    public final ConnectionState getConnectionState()
    {
        return a;
    }
}
