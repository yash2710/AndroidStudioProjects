// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class SantaOfferValue extends Renderable
{

    ImageValue image;
    private String source;
    String text;

    public SantaOfferValue()
    {
    }

    public SantaOfferValue(String s, ImageValue imagevalue, String s1)
    {
        text = s;
        image = imagevalue;
        source = s1;
    }

    public SantaOfferValue(String s, String s1)
    {
        text = s;
        source = s1;
        image = null;
    }

    public ImageValue getImage()
    {
        return image;
    }

    public String getSource()
    {
        return source;
    }

    public String getText()
    {
        return text;
    }

    public void setImage(ImageValue imagevalue)
    {
        image = imagevalue;
    }

    public void setSource(String s)
    {
        source = s;
    }

    public void setText(String s)
    {
        text = s;
    }
}
