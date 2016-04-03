// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data;

import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.Renderable;

public class WidgetItem
{

    Action action;
    Renderable value;

    public WidgetItem()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public Renderable getValue()
    {
        return value;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setValue(Renderable renderable)
    {
        value = renderable;
    }
}
