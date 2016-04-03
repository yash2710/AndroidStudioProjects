// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.os.Handler;
import android.os.HandlerThread;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            c, GroupDataHandler, GroupSyncPolicy, e, 
//            f, d, Data, BatchNetworking, 
//            a, g, GroupPriorityQueue

public class Group
{

    public static final int NOT_SYNCED = 0;
    public static final int QUEUED_FOR_SYNCING = 1;
    public static final int SYNC_FAILED = 3;
    public static final int SYNC_SUCCESSFUL = 2;
    Handler a;
    ArrayList b;
    private long c;
    private GroupDataHandler d;
    private ArrayList e;
    private int f;

    public Group(GroupDataHandler groupdatahandler)
    {
        a = null;
        b = null;
        setLastSyncTryTime(System.currentTimeMillis());
        d = groupdatahandler;
        e = new ArrayList();
        f = 0;
        HandlerThread handlerthread = new HandlerThread("GroupHandler");
        handlerthread.setPriority(3);
        handlerthread.start();
        a = new c(this, handlerthread.getLooper());
    }

    static int a(Group group, int i)
    {
        group.f = 0;
        return 0;
    }

    static ArrayList a(Group group)
    {
        return group.e;
    }

    static void b(Group group)
    {
        int i = group.d.getSyncPolicy().syncBatchSize();
        if (i > group.e.size())
        {
            i = group.e.size();
        }
        int j;
        if (group.b == null)
        {
            group.b = new ArrayList();
        } else
        {
            group.b.clear();
        }
        for (j = 0; j < i; j++)
        {
            group.b.add(group.e.get(j));
        }

        group.c = System.currentTimeMillis();
        e e1 = new e(group);
        f f1 = new f(group);
        try
        {
            group.d.syncBatch(group.b, e1, f1);
            return;
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
        }
    }

    static GroupDataHandler c(Group group)
    {
        return group.d;
    }

    public GroupDataHandler getBatchDataHandler()
    {
        return d;
    }

    public int getCurrentSyncState()
    {
        return f;
    }

    public long getLastSyncTryTime()
    {
        return c;
    }

    public boolean handleSyncPoke()
    {
        boolean flag = true;
        this;
        JVM INSTR monitorenter ;
        if (!d.getSyncPolicy().elegibleForSyncing(this)) goto _L2; else goto _L1
_L1:
        f = 1;
        a.post(new d(this));
_L4:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L2:
        flag = false;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void push(Data data)
    {
        if (data != null)
        {
            if (size() > d.getMaxBatchSize())
            {
                int i = d.getElementCountToDeleteOnBatchFull();
                int j;
                int k;
                if (i > size())
                {
                    j = size() - d.getMaxBatchSize();
                } else
                {
                    j = i;
                }
                k = 0;
                while (k < j) 
                {
                    Data data1 = (Data)e.remove(0);
                    if (data1.getCacheState() == Data.DataCacheState.CSTATE_CACHED)
                    {
                        try
                        {
                            BatchNetworking.getDefaultInstance().a().removeData(data1.getEventId());
                        }
                        catch (Exception exception)
                        {
                            FkLogger.printStackTrace(exception);
                        }
                    }
                    k++;
                }
            }
            e.add(data);
            if (data.getCacheState() == Data.DataCacheState.CSTATE_NOT_CACHED)
            {
                data.setCacheState(Data.DataCacheState.CSTATE_CACHED);
                a.post(new g(this, data, this));
                return;
            }
            if (d.getSyncPolicy().elegibleForSyncing(this))
            {
                BatchNetworking.getDefaultInstance().getGroupPriorityQueue().getNotificationHandler().sendEmptyMessage(0);
                return;
            }
        }
    }

    public void setBatchDataHandler(GroupDataHandler groupdatahandler)
    {
        d = groupdatahandler;
    }

    public void setLastSyncTryTime(long l)
    {
        c = l;
    }

    public int size()
    {
        return e.size();
    }
}
