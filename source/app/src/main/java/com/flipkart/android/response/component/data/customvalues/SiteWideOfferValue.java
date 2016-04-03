// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable

public class SiteWideOfferValue extends Renderable
{

    private String description;
    private String offerId;
    private String offerType;
    private String termsAndConditionsUrl;
    private String title;

    public SiteWideOfferValue()
    {
    }

    public String getDescription()
    {
        return description;
    }

    public String getOfferId()
    {
        return offerId;
    }

    public String getOfferType()
    {
        return offerType;
    }

    public String getTermsAndConditionsUrl()
    {
        return termsAndConditionsUrl;
    }

    public String getTitle()
    {
        return title;
    }

    public void setDescription(String s)
    {
        description = s;
    }

    public void setOfferId(String s)
    {
        offerId = s;
    }

    public void setOfferType(String s)
    {
        offerType = s;
    }

    public void setTermsAndConditionsUrl(String s)
    {
        termsAndConditionsUrl = s;
    }

    public void setTitle(String s)
    {
        title = s;
    }
}
