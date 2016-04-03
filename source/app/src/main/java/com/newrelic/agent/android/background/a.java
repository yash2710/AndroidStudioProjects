// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.background;

import java.util.concurrent.ThreadFactory;

// Referenced classes of package com.newrelic.agent.android.background:
//            ApplicationStateMonitor

final class a
    implements ThreadFactory
{

    a(ApplicationStateMonitor applicationstatemonitor)
    {
    }

    public final Thread newThread(Runnable runnable)
    {
        return new Thread(runnable, "[New Relic] App State Monitor");
    }
}
