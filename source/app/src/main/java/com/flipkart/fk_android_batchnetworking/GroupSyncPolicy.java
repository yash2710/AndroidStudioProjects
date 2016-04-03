// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;


// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            Group

public interface GroupSyncPolicy
{

    public abstract boolean elegibleForSyncing(Group group);

    public abstract int syncBatchSize();

    public abstract int syncIdleTime();
}
