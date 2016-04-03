// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import java.util.List;

// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ButtonValue

public class FilterValue extends Renderable
{

    private ButtonValue button;
    List facetResponses;
    int maxItems;

    public FilterValue()
    {
    }

    public FilterValue(List list)
    {
        facetResponses = list;
    }

    public ButtonValue getButton()
    {
        return button;
    }

    public List getFacetResponses()
    {
        return facetResponses;
    }

    public int getMaxItems()
    {
        return maxItems;
    }

    public void setButton(ButtonValue buttonvalue)
    {
        button = buttonvalue;
    }

    public void setFacetResponses(List list)
    {
        facetResponses = list;
    }

    public void setMaxItems(int i)
    {
        maxItems = i;
    }
}
