// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.api.v1;


// Referenced classes of package com.newrelic.agent.android.api.v1:
//            ConnectionEvent

public interface ConnectionListener
{

    public abstract void connected(ConnectionEvent connectionevent);

    public abstract void disconnected(ConnectionEvent connectionevent);
}
