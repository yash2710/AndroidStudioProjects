// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;


// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            GroupSyncPolicy, Group

public class DefaultSyncPolicy
    implements GroupSyncPolicy
{

    public DefaultSyncPolicy()
    {
    }

    public boolean elegibleForSyncing(Group group)
    {
        boolean flag = true;
        if (group.getCurrentSyncState() != 0)
        {
            return false;
        }
        boolean flag1;
        if (System.currentTimeMillis() - group.getLastSyncTryTime() > (long)(1000 * syncIdleTime()) && group.size() > 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (!flag1)
        {
            if (group.size() < syncBatchSize())
            {
                flag = false;
            }
        } else
        {
            flag = flag1;
        }
        return flag;
    }

    public int syncBatchSize()
    {
        return 15;
    }

    public int syncIdleTime()
    {
        return 120;
    }
}
