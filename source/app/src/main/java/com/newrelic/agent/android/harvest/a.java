// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;


// Referenced classes of package com.newrelic.agent.android.harvest:
//            HarvestTimer

final class a
    implements Runnable
{

    private HarvestTimer a;

    a(HarvestTimer harvesttimer, HarvestTimer harvesttimer1)
    {
        a = harvesttimer1;
        super();
    }

    public final void run()
    {
        a.tick();
    }
}
