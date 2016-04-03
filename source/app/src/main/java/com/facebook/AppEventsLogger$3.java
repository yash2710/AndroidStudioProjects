// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.facebook;

import com.facebook.internal.Utility;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

// Referenced classes of package com.facebook:
//            AppEventsLogger

final class cessTokenAppIdPair extends TimerTask
{

    public final void run()
    {
        HashSet hashset = new HashSet();
        Object obj = AppEventsLogger.access$200();
        obj;
        JVM INSTR monitorenter ;
        for (Iterator iterator = AppEventsLogger.access$300().keySet().iterator(); iterator.hasNext(); hashset.add(((cessTokenAppIdPair)iterator.next()).getApplicationId())) { }
        break MISSING_BLOCK_LABEL_67;
        Exception exception;
        exception;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
        for (Iterator iterator1 = hashset.iterator(); iterator1.hasNext(); Utility.queryAppSettings((String)iterator1.next(), true)) { }
        return;
    }

    cessTokenAppIdPair()
    {
    }
}
