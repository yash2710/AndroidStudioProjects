// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.os.Handler;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            BatchNetworking, Data, Group, GroupDataHandler, 
//            a, GroupSyncPolicy, GroupPriorityQueue

final class g
    implements Runnable
{

    private Data a;
    private Group b;
    private Group c;

    g(Group group, Data data, Group group1)
    {
        c = group;
        a = data;
        b = group1;
        super();
    }

    public final void run()
    {
        try
        {
            BatchNetworking.getDefaultInstance().a().persistData(a.getEventId(), Group.c(c).getGroupId(), Group.c(c).serializeIndividualData(a.getData()), a.getExpiry());
        }
        catch (Exception exception)
        {
            FkLogger.info("Group", "Error in persisting data");
            FkLogger.printStackTrace(exception);
        }
        if (Group.c(c).getSyncPolicy().elegibleForSyncing(b))
        {
            BatchNetworking.getDefaultInstance().getGroupPriorityQueue().getNotificationHandler().sendEmptyMessage(0);
        }
    }
}
