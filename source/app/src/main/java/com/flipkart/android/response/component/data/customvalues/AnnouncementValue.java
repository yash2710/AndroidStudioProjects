// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class AnnouncementValue extends Renderable
{

    private ImageValue imageValue;
    private String subTitle;
    private String title;

    public AnnouncementValue()
    {
    }

    public ImageValue getImageValue()
    {
        return imageValue;
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

    public void setSubTitle(String s)
    {
        subTitle = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
