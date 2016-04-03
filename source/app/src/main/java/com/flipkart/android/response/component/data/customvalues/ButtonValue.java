// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class ButtonValue extends Renderable
{

    private ImageValue image;
    private String title;

    public ButtonValue()
    {
    }

    public ImageValue getImage()
    {
        return image;
    }

    public String getTitle()
    {
        return title;
    }

    public void setImage(ImageValue imagevalue)
    {
        image = imagevalue;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
