// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            DefaultSyncPolicy, h, BatchNetworking, GroupSyncPolicy

public abstract class GroupDataHandler
{

    public final int PRIORITY_BATCH_DEFAULT;
    public final int PRIORITY_BATCH_HIGHEST;
    public final int PRIORITY_BATCH_LOWEST;
    private int a;
    private String b;
    private String c;
    private GroupSyncPolicy d;
    private int e;
    private int f;
    private HashMap g;

    public GroupDataHandler(String s, String s1)
    {
        PRIORITY_BATCH_LOWEST = 0x7fffffff;
        PRIORITY_BATCH_DEFAULT = 0x3fffffff;
        PRIORITY_BATCH_HIGHEST = 0x80000000;
        b = s;
        c = s1;
        d = new DefaultSyncPolicy();
        a = 0x3fffffff;
        e = 50;
        f = 5;
    }

    public GroupDataHandler(String s, String s1, GroupSyncPolicy groupsyncpolicy, int i)
    {
        PRIORITY_BATCH_LOWEST = 0x7fffffff;
        PRIORITY_BATCH_DEFAULT = 0x3fffffff;
        PRIORITY_BATCH_HIGHEST = 0x80000000;
        b = s;
        c = s1;
        d = groupsyncpolicy;
        if (d == null)
        {
            d = new DefaultSyncPolicy();
        }
        a = i;
        e = 50;
        f = 5;
    }

    static HashMap a(GroupDataHandler groupdatahandler)
    {
        return groupdatahandler.g;
    }

    public abstract Object deSerializeIndividualData(byte abyte0[]);

    public String getContentType()
    {
        return null;
    }

    public HashMap getCustomHttpHeaders()
    {
        if (g == null)
        {
            g = new HashMap();
        }
        return g;
    }

    public int getElementCountToDeleteOnBatchFull()
    {
        return f;
    }

    public String getGroupId()
    {
        return b;
    }

    public int getMaxBatchSize()
    {
        return e;
    }

    protected abstract byte[] getPackedDataForNetworkPush(ArrayList arraylist);

    public int getPriority()
    {
        return a;
    }

    public GroupSyncPolicy getSyncPolicy()
    {
        return d;
    }

    public String getUrl()
    {
        return c;
    }

    public String getUserAgent()
    {
        if (g != null)
        {
            return (String)g.get("User-Agent");
        } else
        {
            return null;
        }
    }

    public abstract byte[] serializeIndividualData(Object obj);

    public void setCustomHttpHeaders(HashMap hashmap)
    {
        g = hashmap;
    }

    public void setElementCountToDeleteOnBatchFull(int i)
    {
        f = i;
    }

    public void setGroupId(String s)
    {
        b = s;
    }

    public void setMaxBatchSize(int i)
    {
        e = i;
    }

    public void setPriority(int i)
    {
        a = i;
    }

    public void setSyncPolicy(GroupSyncPolicy groupsyncpolicy)
    {
        d = groupsyncpolicy;
    }

    public void setUrl(String s)
    {
        c = s;
    }

    public void setUserAgent(String s)
    {
        if (g == null && s == null)
        {
            return;
        }
        if (g == null)
        {
            g = new HashMap();
        }
        if (s == null)
        {
            g.remove("User-Agent");
            return;
        } else
        {
            g.put("User-Agent", s);
            return;
        }
    }

    protected void syncBatch(ArrayList arraylist, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener)
    {
        h h1 = new h(this, 1, getUrl(), listener, errorlistener, arraylist);
        h1.setRetryPolicy(new DefaultRetryPolicy(2500, 0, 1.0F));
        BatchNetworking.getDefaultInstance().getRequestQueue().add(h1);
    }
}
