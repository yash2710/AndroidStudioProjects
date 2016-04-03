// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.harvest;


public interface HarvestLifecycleAware
{

    public abstract void onHarvest();

    public abstract void onHarvestBefore();

    public abstract void onHarvestComplete();

    public abstract void onHarvestConnected();

    public abstract void onHarvestDisabled();

    public abstract void onHarvestDisconnected();

    public abstract void onHarvestError();

    public abstract void onHarvestFinalize();

    public abstract void onHarvestSendFailed();

    public abstract void onHarvestStart();

    public abstract void onHarvestStop();
}
