// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.InfiniteListResponse;

import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.OMUValue;

public class OMUResponse
{

    Action action;
    OMUValue value;

    public OMUResponse()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public OMUValue getValue()
    {
        return value;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setValue(OMUValue omuvalue)
    {
        value = omuvalue;
    }
}
