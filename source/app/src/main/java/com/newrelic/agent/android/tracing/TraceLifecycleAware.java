// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.tracing;


// Referenced classes of package com.newrelic.agent.android.tracing:
//            ActivityTrace

public interface TraceLifecycleAware
{

    public abstract void onEnterMethod();

    public abstract void onExitMethod();

    public abstract void onTraceComplete(ActivityTrace activitytrace);

    public abstract void onTraceStart(ActivityTrace activitytrace);
}
