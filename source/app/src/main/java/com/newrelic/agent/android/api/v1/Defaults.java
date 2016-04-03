// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android.api.v1;


public interface Defaults
{

    public static final float ACTIVITY_TRACE_MIN_UTILIZATION = 0.3F;
    public static final boolean COLLECT_NETWORK_ERRORS = true;
    public static final int ERROR_LIMIT = 10;
    public static final long HARVEST_INTERVAL_IN_SECONDS = 60L;
    public static final long MAX_TRANSACTION_AGE_IN_SECONDS = 600L;
    public static final long MAX_TRANSACTION_COUNT = 1000L;
    public static final long MIN_HARVEST_DELTA_IN_SECONDS = 50L;
    public static final long MIN_HTTP_ERROR_STATUS_CODE = 400L;
    public static final int RESPONSE_BODY_LIMIT = 1024;
    public static final int STACK_TRACE_LIMIT = 50;
}
