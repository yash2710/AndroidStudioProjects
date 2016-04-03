// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            GroupPriorityQueue, a, Group, Data, 
//            GroupDataHandler

public class BatchNetworking
{

    public static final String DEBUG_LOGGER_GROUPID = "debug_logger";
    private static final BatchNetworking a = new BatchNetworking();
    private GroupPriorityQueue b;
    private Context c;
    private RequestQueue d;
    private a e;

    private BatchNetworking()
    {
        b = new GroupPriorityQueue();
    }

    public static BatchNetworking getDefaultInstance()
    {
        return a;
    }

    final a a()
    {
        if (e == null)
        {
            if (getApplicationContext() == null)
            {
                throw new Exception("initialize method not called");
            }
            e = new a(getApplicationContext());
            e.loadCachedDataInBatchNetworkingInstance(this);
        }
        return e;
    }

    public Context getApplicationContext()
    {
        return c;
    }

    public GroupPriorityQueue getGroupPriorityQueue()
    {
        return b;
    }

    public RequestQueue getRequestQueue()
    {
        if (d == null)
        {
            if (getApplicationContext() == null)
            {
                throw new Exception("initialize method not called");
            }
            d = Volley.newRequestQueue(getApplicationContext());
        }
        return d;
    }

    public void initialize(Context context)
    {
        c = context;
    }

    public void pushDataForGroupId(Data data, String s)
    {
        Group group = b.getGroupForGroupId(s);
        if (group == null)
        {
            throw new IllegalArgumentException((new StringBuilder("No data handler found for groupId ")).append(s).toString());
        } else
        {
            group.push(data);
            return;
        }
    }

    public void pushDataForGroupId(Object obj, String s)
    {
        pushDataForGroupId(new Data(obj), s);
    }

    public void setGroupDataHandler(GroupDataHandler groupdatahandler)
    {
        Group group = new Group(groupdatahandler);
        b.addGroup(group, groupdatahandler.getGroupId());
    }

}
