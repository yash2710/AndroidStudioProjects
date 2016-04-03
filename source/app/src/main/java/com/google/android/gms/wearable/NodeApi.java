// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

public interface NodeApi
{

    public abstract PendingResult addListener(GoogleApiClient googleapiclient, NodeListener nodelistener);

    public abstract PendingResult getConnectedNodes(GoogleApiClient googleapiclient);

    public abstract PendingResult getLocalNode(GoogleApiClient googleapiclient);

    public abstract PendingResult removeListener(GoogleApiClient googleapiclient, NodeListener nodelistener);
}
