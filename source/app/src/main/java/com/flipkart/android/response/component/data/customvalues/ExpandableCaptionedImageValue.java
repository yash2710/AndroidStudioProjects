// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;

import java.util.List;

// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class ExpandableCaptionedImageValue extends Renderable
{

    ImageValue imageValue;
    List items;
    String subTitle;
    String title;

    public ExpandableCaptionedImageValue()
    {
        items = null;
    }

    public ExpandableCaptionedImageValue(ImageValue imagevalue, String s)
    {
        items = null;
        imageValue = imagevalue;
        title = s;
    }

    public ImageValue getImageValue()
    {
        return imageValue;
    }

    public List getItems()
    {
        return items;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public String getTitle()
    {
        return title;
    }

    public void setImageValue(ImageValue imagevalue)
    {
        imageValue = imagevalue;
    }

    public void setItems(List list)
    {
        items = list;
    }

    public void setSubTitle(String s)
    {
        subTitle = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
