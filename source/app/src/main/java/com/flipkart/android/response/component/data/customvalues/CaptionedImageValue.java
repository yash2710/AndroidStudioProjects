// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class CaptionedImageValue extends Renderable
{

    String caption;
    ImageValue image;

    public CaptionedImageValue()
    {
    }

    public CaptionedImageValue(ImageValue imagevalue, String s)
    {
        image = imagevalue;
        caption = s;
    }

    public String getCaption()
    {
        return caption;
    }

    public ImageValue getImage()
    {
        return image;
    }

    public void setCaption(String s)
    {
        caption = s;
    }

    public void setImage(ImageValue imagevalue)
    {
        image = imagevalue;
    }
}
