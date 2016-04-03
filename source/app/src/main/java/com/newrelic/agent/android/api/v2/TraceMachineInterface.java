// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.api.v2;


public interface TraceMachineInterface
{

    public abstract long getCurrentThreadId();

    public abstract String getCurrentThreadName();

    public abstract boolean isUIThread();
}
