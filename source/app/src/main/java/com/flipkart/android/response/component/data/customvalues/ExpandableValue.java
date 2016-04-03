// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import java.util.List;

// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class ExpandableValue extends Renderable
{

    private boolean autoExpand;
    private int childViewMoreCount;
    ImageValue image;
    boolean isExpandable;
    boolean isHeading;
    boolean isNew;
    List items;
    private double probability;
    boolean showInViewMore;
    String text;

    public ExpandableValue()
    {
        items = null;
    }

    public int getChildViewMoreCount()
    {
        return childViewMoreCount;
    }

    public ImageValue getImage()
    {
        return image;
    }

    public List getItems()
    {
        return items;
    }

    public double getProbability()
    {
        return probability;
    }

    public String getText()
    {
        return text;
    }

    public boolean isAutoExpand()
    {
        return autoExpand;
    }

    public boolean isExpandable()
    {
        return isExpandable;
    }

    public boolean isHeading()
    {
        return isHeading;
    }

    public boolean isNew()
    {
        return isNew;
    }

    public boolean isShowInViewMore()
    {
        return showInViewMore;
    }

    public void setAutoExpand(boolean flag)
    {
        autoExpand = flag;
    }

    public void setChildViewMoreCount(int i)
    {
        childViewMoreCount = i;
    }

    public void setExpandable(boolean flag)
    {
        isExpandable = flag;
    }

    public void setHeading(boolean flag)
    {
        isHeading = flag;
    }

    public void setImage(ImageValue imagevalue)
    {
        image = imagevalue;
    }

    public void setItems(List list)
    {
        items = list;
    }

    public void setNew(boolean flag)
    {
        isNew = flag;
    }

    public void setProbability(double d)
    {
        probability = d;
    }

    public void setShowInViewMore(boolean flag)
    {
        showInViewMore = flag;
    }

    public void setText(String s)
    {
        text = s;
    }
}
