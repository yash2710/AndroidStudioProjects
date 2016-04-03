// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;

import com.newrelic.agent.android.harvest.type.Harvestable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HarvestableCache
{

    private int a;
    private final Collection b = new ArrayList();

    public HarvestableCache()
    {
        a = 1024;
    }

    public void add(Harvestable harvestable)
    {
        if (harvestable == null || b.size() >= a)
        {
            return;
        } else
        {
            b.add(harvestable);
            return;
        }
    }

    public Collection flush()
    {
        if (b.size() == 0)
        {
            return Collections.emptyList();
        }
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList(b);
        b.clear();
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        throw exception;
    }

    public int getSize()
    {
        return b.size();
    }

    public void setLimit(int i)
    {
        a = i;
    }
}
