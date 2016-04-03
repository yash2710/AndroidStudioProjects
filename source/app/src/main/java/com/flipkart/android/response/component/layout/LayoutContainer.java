// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.layout;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.component.layout:
//            LayoutDetails

public class LayoutContainer
{

    ArrayList children;
    LayoutDetails layoutDetails;
    long layoutId;

    public LayoutContainer()
    {
    }

    public ArrayList getChildren()
    {
        return children;
    }

    public LayoutDetails getLayoutDetails()
    {
        return layoutDetails;
    }

    public long getLayoutId()
    {
        return layoutId;
    }

    public void setChildren(ArrayList arraylist)
    {
        children = arraylist;
    }

    public void setLayoutDetails(LayoutDetails layoutdetails)
    {
        layoutDetails = layoutdetails;
    }

    public void setLayoutId(long l)
    {
        layoutId = l;
    }
}
