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
//            Event

public class Events extends HarvestableArray
{

    private final Collection a = new ArrayList();

    public Events()
    {
    }

    public void addEvent(Event event)
    {
        a.add(event);
    }

    public JsonArray asJsonArray()
    {
        JsonArray jsonarray = new JsonArray();
        for (Iterator iterator = a.iterator(); iterator.hasNext(); jsonarray.add(((Event)iterator.next()).asJson())) { }
        return jsonarray;
    }
}
