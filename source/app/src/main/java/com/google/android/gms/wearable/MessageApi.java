// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface MessageApi
{

    public static final int UNKNOWN_REQUEST_ID = -1;

    public abstract PendingResult addListener(GoogleApiClient googleapiclient, MessageListener messagelistener);

    public abstract PendingResult removeListener(GoogleApiClient googleapiclient, MessageListener messagelistener);

    public abstract PendingResult sendMessage(GoogleApiClient googleapiclient, String s, String s1, byte abyte0[]);
}
