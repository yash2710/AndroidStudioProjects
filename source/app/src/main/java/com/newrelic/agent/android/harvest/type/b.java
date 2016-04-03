// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest.type;


final class b
{

    static final int a[];

    static 
    {
        a = new int[Harvestable.Type.values().length];
        try
        {
            a[Harvestable.Type.OBJECT.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            a[Harvestable.Type.ARRAY.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            a[Harvestable.Type.VALUE.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2)
        {
            return;
        }
    }
}
