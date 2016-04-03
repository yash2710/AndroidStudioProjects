// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;


final class b
{

    static final int a[];
    static final int b[];

    static 
    {
        b = new int[Harvester.State.values().length];
        try
        {
            b[Harvester.State.UNINITIALIZED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror) { }
        try
        {
            b[Harvester.State.DISCONNECTED.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror1) { }
        try
        {
            b[Harvester.State.CONNECTED.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror2) { }
        try
        {
            b[Harvester.State.DISABLED.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror3) { }
        a = new int[HarvestResponse.Code.values().length];
        try
        {
            a[HarvestResponse.Code.UNAUTHORIZED.ordinal()] = 1;
        }
        catch (NoSuchFieldError nosuchfielderror4) { }
        try
        {
            a[HarvestResponse.Code.INVALID_AGENT_ID.ordinal()] = 2;
        }
        catch (NoSuchFieldError nosuchfielderror5) { }
        try
        {
            a[HarvestResponse.Code.FORBIDDEN.ordinal()] = 3;
        }
        catch (NoSuchFieldError nosuchfielderror6) { }
        try
        {
            a[HarvestResponse.Code.UNSUPPORTED_MEDIA_TYPE.ordinal()] = 4;
        }
        catch (NoSuchFieldError nosuchfielderror7) { }
        try
        {
            a[HarvestResponse.Code.ENTITY_TOO_LARGE.ordinal()] = 5;
        }
        catch (NoSuchFieldError nosuchfielderror8)
        {
            return;
        }
    }
}
