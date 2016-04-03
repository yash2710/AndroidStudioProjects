// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.HarvestableArray;
import com.newrelic.com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package com.newrelic.agent.android.harvest:
//            HttpError

public class HttpErrors extends HarvestableArray
{

    private final Collection a = new ArrayList();

    public HttpErrors()
    {
    }

    public void addHttpError(HttpError httperror)
    {
        httperror;
        JVM INSTR monitorenter ;
        Iterator iterator = a.iterator();
        HttpError httperror1;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_55;
            }
            httperror1 = (HttpError)iterator.next();
        } while (!httperror.getHash().equals(httperror1.getHash()));
        httperror1.incrementCount();
        httperror;
        JVM INSTR monitorexit ;
        return;
        a.add(httperror);
        httperror;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        httperror;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for (Iterator iterator = a.iterator(); iterator.hasNext(); jsonarray.add(((HttpError)iterator.next()).asJson())) { }
        return jsonarray;
    }

    public void clear()
    {
        a.clear();
    }

    public int count()
    {
        return a.size();
    }

    public Collection getHttpErrors()
    {
        return a;
    }

    public void removeHttpError(HttpError httperror)
    {
        this;
        JVM INSTR monitorenter ;
        a.remove(httperror);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }
}
