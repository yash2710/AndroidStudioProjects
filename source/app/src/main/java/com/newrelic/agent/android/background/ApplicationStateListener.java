// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.background;


// Referenced classes of package com.newrelic.agent.android.background:
//            ApplicationStateEvent

public interface ApplicationStateListener
{

    public abstract void applicationBackgrounded(ApplicationStateEvent applicationstateevent);

    public abstract void applicationForegrounded(ApplicationStateEvent applicationstateevent);
}
