// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.productInfo;

import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.response.productInfo:
//            ProductReturn, MarketPlaceSellerUgcInfo, ProductAvailabilityDetails, SellerEmiOptions

public class MarketPlaceSeller
{

    private ProductAvailabilityDetails availabilityDetails;
    private boolean codAvailable;
    private SellerEmiOptions emiOptions;
    private String fbf;
    private boolean isServiceable;
    private boolean isWSR;
    private String listId;
    private ArrayList offer;
    private ProductReturn productReturn;
    private ArrayList promiseWidgets;
    private String sellerDisplayName;
    private String sellerId;
    private String sellerPageURL;
    private int sellingPrice;
    private int shippingCharge;
    private String shippingText;
    private MarketPlaceSellerUgcInfo ugcInfo;

    public MarketPlaceSeller()
    {
        productReturn = new ProductReturn();
        ugcInfo = new MarketPlaceSellerUgcInfo();
        availabilityDetails = new ProductAvailabilityDetails();
        offer = new ArrayList();
    }

    public ProductAvailabilityDetails getAvailabilityDetails()
    {
        return availabilityDetails;
    }

    public ProductAvailabilityDetails getAvailiabilityDetails()
    {
        return availabilityDetails;
    }

    public SellerEmiOptions getEmiOptions()
    {
        return emiOptions;
    }

    public String getFbf()
    {
        return fbf;
    }

    public String getListId()
    {
        return listId;
    }

    public ArrayList getOffer()
    {
        if (offer == null)
        {
            offer = new ArrayList();
        }
        return offer;
    }

    public ProductReturn getProductReturn()
    {
        return productReturn;
    }

    public ArrayList getPromiseWidgets()
    {
        return promiseWidgets;
    }

    public String getSellerDisplayName()
    {
        return sellerDisplayName;
    }

    public String getSellerId()
    {
        return sellerId;
    }

    public String getSellerPageURL()
    {
        return sellerPageURL;
    }

    public int getSellingPrice()
    {
        return sellingPrice;
    }

    public int getShippingCharge()
    {
        return shippingCharge;
    }

    public String getShippingText()
    {
        return shippingText;
    }

    public MarketPlaceSellerUgcInfo getUgcInfo()
    {
        return ugcInfo;
    }

    public boolean isCodAvailable()
    {
        return codAvailable;
    }

    public boolean isServiceable()
    {
        return isServiceable;
    }

    public boolean isWSR()
    {
        return isWSR;
    }

    public void setAvailabilityDetails(ProductAvailabilityDetails productavailabilitydetails)
    {
        availabilityDetails = productavailabilitydetails;
    }

    public void setAvailiabilityDetails(ProductAvailabilityDetails productavailabilitydetails)
    {
        availabilityDetails = productavailabilitydetails;
    }

    public void setCodAvailable(boolean flag)
    {
        codAvailable = flag;
    }

    public void setEmiOptions(SellerEmiOptions selleremioptions)
    {
        emiOptions = selleremioptions;
    }

    public void setFbf(String s)
    {
        fbf = s;
    }

    public void setListId(String s)
    {
        listId = s;
    }

    public void setOffer(ArrayList arraylist)
    {
        offer = arraylist;
    }

    public void setProductReturn(ProductReturn productreturn)
    {
        productReturn = productreturn;
    }

    public void setPromiseWidgets(ArrayList arraylist)
    {
        promiseWidgets = arraylist;
    }

    public void setSellerDisplayName(String s)
    {
        sellerDisplayName = s;
    }

    public void setSellerId(String s)
    {
        sellerId = s;
    }

    public void setSellerPageURL(String s)
    {
        sellerPageURL = s;
    }

    public void setSellingPrice(int i)
    {
        sellingPrice = i;
    }

    public void setServiceable(boolean flag)
    {
        isServiceable = flag;
    }

    public void setShippingCharge(int i)
    {
        shippingCharge = i;
    }

    public void setShippingText(String s)
    {
        shippingText = s;
    }

    public void setUgcInfo(MarketPlaceSellerUgcInfo marketplacesellerugcinfo)
    {
        ugcInfo = marketplacesellerugcinfo;
    }

    public void setWSR(boolean flag)
    {
        isWSR = flag;
    }
}
