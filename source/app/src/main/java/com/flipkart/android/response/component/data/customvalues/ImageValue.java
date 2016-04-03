// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, Image

public class ImageValue extends Renderable
{

    private Image imageMap;

    public ImageValue()
    {
        imageMap = null;
    }

    public Image getImage()
    {
        return imageMap;
    }

    public void setImage(Image image)
    {
        imageMap = image;
    }
}
