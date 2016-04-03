// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            BatchNetworking, Group, GroupDataHandler, i

public class GroupPriorityQueue
{

    public static final int NOTIFICATION_POKE_ME = 0;
    public static final int NOTIFICATION_SYNC_FAILED = -1;
    public static final int NOTIFICATION_SYNC_SUCCESSFUL = 1;
    private ArrayList a;
    private HashMap b;
    private Handler c;

    public GroupPriorityQueue()
    {
        c = null;
        a = new ArrayList();
        b = new HashMap();
    }

    static void a(GroupPriorityQueue grouppriorityqueue)
    {
        NetworkInfo networkinfo = ((ConnectivityManager)BatchNetworking.getDefaultInstance().getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        boolean flag;
        if (networkinfo != null && networkinfo.isConnectedOrConnecting())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            for (Iterator iterator = grouppriorityqueue.a.iterator(); iterator.hasNext() && !((Group)iterator.next()).handleSyncPoke();) { }
        }
    }

    public boolean addGroup(Group group, String s)
    {
        int j;
        int k;
        int l;
        int i1;
        if (getGroupForGroupId(s) != null)
        {
            return false;
        }
        b.put(s, group);
        if (a.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_173;
        }
        j = group.getBatchDataHandler().getPriority();
        k = -1 + a.size();
        l = 0;
        i1 = k;
_L4:
        int j1;
        for (; l < k; k = j1)
        {
            j1 = (l + k) / 2;
            if (j > ((Group)a.get(j1)).getBatchDataHandler().getPriority())
            {
                break MISSING_BLOCK_LABEL_107;
            }
            i1 = j1;
        }

          goto _L1
        if (l != j1) goto _L3; else goto _L2
_L2:
        if (j > ((Group)a.get(k)).getBatchDataHandler().getPriority())
        {
            i1 = k + 1;
        } else
        {
            i1 = k;
        }
_L1:
        a.add(i1, group);
_L5:
        return true;
_L3:
        i1 = j1;
        l = j1;
          goto _L4
        a.add(group);
          goto _L5
    }

    public Group getGroupForGroupId(String s)
    {
        return (Group)b.get(s);
    }

    public Handler getNotificationHandler()
    {
        if (c == null)
        {
            HandlerThread handlerthread = new HandlerThread("Notification handler");
            handlerthread.start();
            c = new i(this, handlerthread.getLooper());
        }
        return c;
    }

    public boolean isEmpty()
    {
        return a.size() == 0;
    }

    public int size()
    {
        return a.size();
    }
}
