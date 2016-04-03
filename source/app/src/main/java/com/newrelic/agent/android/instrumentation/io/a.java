// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.instrumentation.io;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.newrelic.agent.android.instrumentation.io:
//            StreamCompleteListener, StreamCompleteEvent

final class a
{

    private boolean a;
    private ArrayList b;

    a()
    {
        a = false;
        b = new ArrayList();
    }

    private boolean a()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = isComplete();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_16;
        }
        a = true;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    private List b()
    {
        ArrayList arraylist1;
        synchronized (b)
        {
            arraylist1 = new ArrayList(b);
            b.clear();
        }
        return arraylist1;
    }

    public final void addStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        synchronized (b)
        {
            b.add(streamcompletelistener);
        }
    }

    public final boolean isComplete()
    {
        this;
        JVM INSTR monitorenter ;
        boolean flag = a;
        this;
        JVM INSTR monitorexit ;
        return flag;
        Exception exception;
        exception;
        throw exception;
    }

    public final void notifyStreamComplete(StreamCompleteEvent streamcompleteevent)
    {
        if (!a())
        {
            for (Iterator iterator = b().iterator(); iterator.hasNext(); ((StreamCompleteListener)iterator.next()).streamComplete(streamcompleteevent)) { }
        }
    }

    public final void notifyStreamError(StreamCompleteEvent streamcompleteevent)
    {
        if (!a())
        {
            for (Iterator iterator = b().iterator(); iterator.hasNext(); ((StreamCompleteListener)iterator.next()).streamError(streamcompleteevent)) { }
        }
    }

    public final void removeStreamCompleteListener(StreamCompleteListener streamcompletelistener)
    {
        synchronized (b)
        {
            b.remove(streamcompletelistener);
        }
    }
}
