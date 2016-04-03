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
//            HttpTransaction

public class HttpTransactions extends HarvestableArray
{

    private final Collection a = new ArrayList();

    public HttpTransactions()
    {
    }

    public void add(HttpTransaction httptransaction)
    {
        this;
        JVM INSTR monitorenter ;
        a.add(httptransaction);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for (Iterator iterator = a.iterator(); iterator.hasNext(); jsonarray.add(((HttpTransaction)iterator.next()).asJson())) { }
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

    public Collection getHttpTransactions()
    {
        return a;
    }

    public void remove(HttpTransaction httptransaction)
    {
        this;
        JVM INSTR monitorenter ;
        a.remove(httptransaction);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public String toString()
    {
        return (new StringBuilder("HttpTransactions{httpTransactions=")).append(a).append('}').toString();
    }
}
