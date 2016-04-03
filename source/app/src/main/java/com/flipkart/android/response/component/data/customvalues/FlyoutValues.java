// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, Image

public class FlyoutValues extends Renderable
{

    private Image imageMap;
    private String title;

    public FlyoutValues()
    {
        imageMap = null;
    }

    public Image getImageMap()
    {
        return imageMap;
    }

    public String getTitle()
    {
        return title;
    }

    public void setImageMap(Image image)
    {
        imageMap = image;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
