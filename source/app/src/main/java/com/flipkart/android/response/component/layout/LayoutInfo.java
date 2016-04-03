// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.layout;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.component.layout:
//            LayoutDetails, LayoutData

public class LayoutInfo
{

    ArrayList children;
    LayoutData data;
    LayoutDetails layoutDetails;
    String widgetType;

    public LayoutInfo()
    {
        layoutDetails = new LayoutDetails();
    }

    public LayoutInfo(String s, LayoutData layoutdata)
    {
        widgetType = s;
        data = layoutdata;
        layoutDetails = new LayoutDetails();
    }

    public ArrayList getChildren()
    {
        return children;
    }

    public LayoutData getData()
    {
        return data;
    }

    public LayoutDetails getLayoutDetails()
    {
        return layoutDetails;
    }

    public String getWidgetType()
    {
        return widgetType;
    }

    public void setChildren(ArrayList arraylist)
    {
        children = arraylist;
    }

    public void setData(LayoutData layoutdata)
    {
        data = layoutdata;
    }

    public void setLayoutDetails(LayoutDetails layoutdetails)
    {
        layoutDetails = layoutdetails;
    }

    public void setWidgetType(String s)
    {
        widgetType = s;
    }
}
