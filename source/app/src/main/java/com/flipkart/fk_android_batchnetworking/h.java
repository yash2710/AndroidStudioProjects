// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.fk_android_batchnetworking;

import com.android.volley.toolbox.StringRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.fk_android_batchnetworking:
//            GroupDataHandler

final class h extends StringRequest
{

    private ArrayList a;
    private GroupDataHandler b;

    h(GroupDataHandler groupdatahandler, int i, String s, com.android.volley.Response.Listener listener, com.android.volley.Response.ErrorListener errorlistener, ArrayList arraylist)
    {
        b = groupdatahandler;
        a = arraylist;
        super(1, s, listener, errorlistener);
    }

    public final byte[] getBody()
    {
        return b.getPackedDataForNetworkPush(a);
    }

    public final String getBodyContentType()
    {
        if (b.getContentType() != null)
        {
            return b.getContentType();
        } else
        {
            return super.getBodyContentType();
        }
    }

    public final Map getHeaders()
    {
        if (GroupDataHandler.a(b) != null && GroupDataHandler.a(b).size() > 0)
        {
            return GroupDataHandler.a(b);
        } else
        {
            return super.getHeaders();
        }
    }
}
