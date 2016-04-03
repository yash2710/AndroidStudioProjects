// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.data.customvalues;


// Referenced classes of package com.flipkart.android.response.component.data.customvalues:
//            Renderable, OMUImageValue, ImageValue

public class OMUValue extends Renderable
{

    private boolean banner;
    private boolean displayLimitedStock;
    private boolean displayTimer;
    private Double fsp;
    private String limitedStockText;
    private Double mrp;
    private String name;
    private String offerDescription;
    private String offerTitle;
    private OMUImageValue primaryImage;
    private String productStatus;
    private ImageValue secondaryImage;
    private String shareUrl;
    private long timeLeft;

    public OMUValue()
    {
    }

    public Double getFsp()
    {
        return fsp;
    }

    public String getLimitedStockText()
    {
        return limitedStockText;
    }

    public Double getMrp()
    {
        return mrp;
    }

    public String getName()
    {
        return name;
    }

    public String getOfferDescription()
    {
        return offerDescription;
    }

    public String getOfferTitle()
    {
        return offerTitle;
    }

    public OMUImageValue getPrimaryImage()
    {
        return primaryImage;
    }

    public String getProductStatus()
    {
        return productStatus;
    }

    public ImageValue getSecondaryImage()
    {
        return secondaryImage;
    }

    public String getShareUrl()
    {
        return shareUrl;
    }

    public long getTimeLeft()
    {
        return timeLeft;
    }

    public boolean isBanner()
    {
        return banner;
    }

    public boolean isDisplayLimitedStock()
    {
        return displayLimitedStock;
    }

    public boolean isDisplayTimer()
    {
        return displayTimer;
    }

    public void setBanner(boolean flag)
    {
        banner = flag;
    }

    public void setDisplayLimitedStock(boolean flag)
    {
        displayLimitedStock = flag;
    }

    public void setDisplayTimer(boolean flag)
    {
        displayTimer = flag;
    }

    public void setFsp(Double double1)
    {
        fsp = double1;
    }

    public void setLimitedStockText(String s)
    {
        limitedStockText = s;
    }

    public void setMrp(Double double1)
    {
        mrp = double1;
    }

    public void setName(String s)
    {
        name = s;
    }

    public void setOfferDescription(String s)
    {
        offerDescription = s;
    }

    public void setOfferTitle(String s)
    {
        offerTitle = s;
    }

    public void setPrimaryImage(OMUImageValue omuimagevalue)
    {
        primaryImage = omuimagevalue;
    }

    public void setProductStatus(String s)
    {
        productStatus = s;
    }

    public void setSecondaryImage(ImageValue imagevalue)
    {
        secondaryImage = imagevalue;
    }

    public void setShareUrl(String s)
    {
        shareUrl = s;
    }

    public void setTimeLeft(long l)
    {
        timeLeft = l;
    }
}
