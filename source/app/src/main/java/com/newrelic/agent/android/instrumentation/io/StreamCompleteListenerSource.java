// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.io;


// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteListener

public interface StreamCompleteListenerSource
{

    public abstract void addStreamCompleteListener(StreamCompleteListener streamcompletelistener);

    public abstract void removeStreamCompleteListener(StreamCompleteListener streamcompletelistener);
}
