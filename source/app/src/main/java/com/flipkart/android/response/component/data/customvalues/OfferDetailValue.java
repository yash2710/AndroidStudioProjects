// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class OfferDetailValue extends Renderable
{

    private Double fsp;
    private Double mrp;
    private String offerDescription;
    private String offerTitle;

    public OfferDetailValue()
    {
    }

    public Double getFsp()
    {
        return fsp;
    }

    public Double getMrp()
    {
        return mrp;
    }

    public String getOfferDescription()
    {
        return offerDescription;
    }

    public String getOfferTitle()
    {
        return offerTitle;
    }

    public void setFsp(Double double1)
    {
        fsp = double1;
    }

    public void setMrp(Double double1)
    {
        mrp = double1;
    }

    public void setOfferDescription(String s)
    {
        offerDescription = s;
    }

    public void setOfferTitle(String s)
    {
        offerTitle = s;
    }
}
