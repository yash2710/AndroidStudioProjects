// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.customwidgetitem;

import com.flipkart.android.response.customwidgetitemvalue.Action;
import com.flipkart.android.response.customwidgetitemvalue.InstallCareWidgetItemValue;

public class InstallCareWidgetItem
{

    Action action;
    InstallCareWidgetItemValue value;

    public InstallCareWidgetItem()
    {
    }

    public Action getAction()
    {
        return action;
    }

    public InstallCareWidgetItemValue getValue()
    {
        return value;
    }

    public void setAction(Action action1)
    {
        action = action1;
    }

    public void setValue(InstallCareWidgetItemValue installcarewidgetitemvalue)
    {
        value = installcarewidgetitemvalue;
    }
}
