// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.volley.filters;

import com.android.volley.Request;
import com.flipkart.android.volley.request.component.ComponentLayoutRequest;

public class ComponentLayoutFilter
    implements com.android.volley.RequestQueue.RequestFilter
{

    private String a;

    public ComponentLayoutFilter(String s)
    {
        a = s;
    }

    public boolean apply(Request request)
    {
        return (request instanceof ComponentLayoutRequest) && ((ComponentLayoutRequest)request).getRequestId().equals(a);
    }
}
