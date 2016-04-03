// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import com.flipkart.android.response.customwidgetitemvalue.Action;

public class ExtraMessage
{

    private Action action;
    private String message;
    private String title;

    public ExtraMessage()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public String getMessage()
    {
        return message;
    }

    public String getTitle()
    {
        return title;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setMessage(String s)
    {
        message = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
