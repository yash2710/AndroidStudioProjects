// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, ImageValue

public class SponsoredValue extends Renderable
{

    double heightFactor;
    ImageValue image;

    public SponsoredValue()
    {
    }

    public double getHeightFactor()
    {
        return heightFactor;
    }

    public ImageValue getImage()
    {
        return image;
    }

    public void setHeightFactor(double d)
    {
        heightFactor = d;
    }

    public void setImage(ImageValue imagevalue)
    {
        image = imagevalue;
    }
}
