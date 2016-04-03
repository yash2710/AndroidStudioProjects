// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.metric;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.newrelic.agent.android.metric:
//            Metric

public class MetricStore
{

    private final Map a = new ConcurrentHashMap();

    public MetricStore()
    {
    }

    public void add(Metric metric)
    {
        String s = metric.getStringScope();
        String s1 = metric.getName();
        if (!a.containsKey(s))
        {
            a.put(s, new HashMap());
        }
        if (((Map)a.get(s)).containsKey(s1))
        {
            ((Metric)((Map)a.get(s)).get(s1)).aggregate(metric);
            return;
        } else
        {
            ((Map)a.get(s)).put(s1, metric);
            return;
        }
    }

    public void clear()
    {
        a.clear();
    }

    public Metric get(String s)
    {
        return get(s, "");
    }

    public Metric get(String s, String s1)
    {
        Map map;
        Metric metric;
        try
        {
            map = a;
        }
        catch (NullPointerException nullpointerexception)
        {
            return null;
        }
        if (s1 == null)
        {
            s1 = "";
        }
        metric = (Metric)((Map)map.get(s1)).get(s);
        return metric;
    }

    public List getAll()
    {
        ArrayList arraylist = new ArrayList();
        for (Iterator iterator = a.entrySet().iterator(); iterator.hasNext();)
        {
            Iterator iterator1 = ((Map)((java.util.Map.Entry)iterator.next()).getValue()).entrySet().iterator();
            while (iterator1.hasNext()) 
            {
                arraylist.add(((java.util.Map.Entry)iterator1.next()).getValue());
            }
        }

        return arraylist;
    }

    public List getAllByScope(String s)
    {
        ArrayList arraylist = new ArrayList();
        try
        {
            for (Iterator iterator = ((Map)a.get(s)).entrySet().iterator(); iterator.hasNext(); arraylist.add(((java.util.Map.Entry)iterator.next()).getValue())) { }
        }
        catch (NullPointerException nullpointerexception) { }
        return arraylist;
    }

    public List getAllUnscoped()
    {
        return getAllByScope("");
    }

    public boolean isEmpty()
    {
        return a.isEmpty();
    }

    public void remove(Metric metric)
    {
        String s = metric.getStringScope();
        String s1;
        for (s1 = metric.getName(); !a.containsKey(s) || !((Map)a.get(s)).containsKey(s1);)
        {
            return;
        }

        ((Map)a.get(s)).remove(s1);
    }

    public void removeAll(List list)
    {
        Map map = a;
        map;
        JVM INSTR monitorenter ;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); remove((Metric)iterator.next())) { }
        break MISSING_BLOCK_LABEL_47;
        Exception exception;
        exception;
        throw exception;
        map;
        JVM INSTR monitorexit ;
    }

    public List removeAllWithScope(String s)
    {
        List list = getAllByScope(s);
        if (!list.isEmpty())
        {
            removeAll(list);
        }
        return list;
    }
}
