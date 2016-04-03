// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import android.content.Context;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.MarketPlaceSellerUgcInfo;
import com.flipkart.android.response.productInfo.ProductAvailabilityDetails;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.productInfo.ProductReturn;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

public class ProductPageMoreSellerModel
{

    private boolean a;
    private String b;
    private String c;
    private MoreSellerModel d[];
    private boolean e;
    private String f;
    private Map g;

    public ProductPageMoreSellerModel()
    {
    }

    public static ProductPageMoreSellerModel getModel(ProductInfo productinfo, Context context)
    {
        ProductPageMoreSellerModel productpagemoresellermodel;
        if (StringUtils.isNull(productinfo))
        {
            return null;
        }
        productpagemoresellermodel = new ProductPageMoreSellerModel();
        if (StringUtils.isNullOrEmpty(productinfo.getMainTitle())) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        productpagemoresellermodel.setMainTitle(productinfo.getMainTitle());
        productpagemoresellermodel.setTitleVisible(true);
        productpagemoresellermodel.setSubTitle(productinfo.getSubTitle());
        arraylist = productinfo.getMarketPlace();
        if (StringUtils.isNullOrEmpty(arraylist)) goto _L4; else goto _L3
_L3:
        MoreSellerModel amoresellermodel[];
        int i;
        productpagemoresellermodel.setMoreSellervisible(true);
        amoresellermodel = new MoreSellerModel[arraylist.size()];
        productpagemoresellermodel.setPreferredSellerId(productinfo.getPreferredListingId());
        i = 0;
_L13:
        if (i >= arraylist.size()) goto _L6; else goto _L5
_L5:
        MarketPlaceSeller marketplaceseller = (MarketPlaceSeller)arraylist.get(i);
        if (marketplaceseller == null) goto _L8; else goto _L7
_L7:
        MarketPlaceSellerUgcInfo marketplacesellerugcinfo;
        amoresellermodel[i] = new MoreSellerModel();
        amoresellermodel[i].setDisplayName(marketplaceseller.getSellerDisplayName());
        amoresellermodel[i].setSellingPrice(Integer.toString(marketplaceseller.getSellingPrice()));
        amoresellermodel[i].setListingId(marketplaceseller.getListId());
        marketplacesellerugcinfo = marketplaceseller.getUgcInfo();
        if (marketplacesellerugcinfo == null)
        {
            break MISSING_BLOCK_LABEL_203;
        }
        amoresellermodel[i].setAvgRatings((float)marketplacesellerugcinfo.getRating());
        amoresellermodel[i].setTotalRatings(marketplacesellerugcinfo.getTotalRatingCount());
        ProductAvailabilityDetails productavailabilitydetails = marketplaceseller.getAvailabilityDetails();
        if (productavailabilitydetails == null)
        {
            break MISSING_BLOCK_LABEL_241;
        }
        amoresellermodel[i].setStatus(productavailabilitydetails.getAvailabilityStatus());
        amoresellermodel[i].setShippingText(productavailabilitydetails.getAvailabilityStatusMessage());
        ProductReturn productreturn = marketplaceseller.getProductReturn();
        if (productreturn == null)
        {
            break MISSING_BLOCK_LABEL_279;
        }
        amoresellermodel[i].setReplacementPolicy(productreturn.getReturnPolicy());
        amoresellermodel[i].setSellerReturnPolicyShown(productreturn.isShowReturn());
        ArrayList arraylist1;
        amoresellermodel[i].setFbf(marketplaceseller.getFbf());
        amoresellermodel[i].setPinCodeServiceability(marketplaceseller.isServiceable());
        amoresellermodel[i].setShippingCharge(marketplaceseller.getShippingCharge());
        arraylist1 = marketplaceseller.getOffer();
        if (!StringUtils.isNullOrEmpty(arraylist1)) goto _L10; else goto _L9
_L9:
        amoresellermodel[i].setOfferExisting(false);
          goto _L8
_L10:
        try
        {
            amoresellermodel[i].setOfferExisting(true);
            amoresellermodel[i].setOffersText(arraylist1);
        }
        catch (Exception exception) { }
          goto _L8
_L6:
        productpagemoresellermodel.setMoreSeller(amoresellermodel);
_L11:
        productpagemoresellermodel.setSwatchesMap(null);
        return productpagemoresellermodel;
_L4:
        productpagemoresellermodel.setMoreSellervisible(false);
        continue; /* Loop/switch isn't completed */
_L2:
        productpagemoresellermodel.setTitleVisible(false);
        if (true) goto _L11; else goto _L8
_L8:
        i++;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public String getMainTitle()
    {
        return b;
    }

    public MoreSellerModel[] getMoreSeller()
    {
        return d;
    }

    public String getPreferredSellerId()
    {
        return f;
    }

    public String getSubTitle()
    {
        return c;
    }

    public Map getSwatchesMap()
    {
        return g;
    }

    public boolean isMoreSellerVisible()
    {
        return e;
    }

    public boolean isTitleVisible()
    {
        return a;
    }

    public void setMainTitle(String s)
    {
        b = s;
    }

    public void setMoreSeller(MoreSellerModel amoresellermodel[])
    {
        d = amoresellermodel;
    }

    public void setMoreSellervisible(boolean flag)
    {
        e = flag;
    }

    public void setPreferredSellerId(String s)
    {
        f = s;
    }

    public void setSubTitle(String s)
    {
        c = s;
    }

    public void setSwatchesMap(Map map)
    {
        g = map;
    }

    public void setTitleVisible(boolean flag)
    {
        a = flag;
    }

    private class MoreSellerModel
    {

        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private long f;
        private String g;
        private boolean h;
        private float i;
        private boolean j;
        private ArrayList k;
        private boolean l;
        private int m;
        private String n;

        public float getAvgRatings()
        {
            return i;
        }

        public String getDisplayName()
        {
            return a;
        }

        public String getFbf()
        {
            return n;
        }

        public String getListingId()
        {
            return c;
        }

        public ArrayList getOffersText()
        {
            return k;
        }

        public boolean getPinCodeServiceability()
        {
            return h;
        }

        public String getReplacementPolicy()
        {
            return g;
        }

        public String getSellingPrice()
        {
            return b;
        }

        public int getShippingCharge()
        {
            return m;
        }

        public String getShippingText()
        {
            return e;
        }

        public String getStatus()
        {
            return d;
        }

        public long getTotalRatings()
        {
            return f;
        }

        public boolean isOfferExisting()
        {
            return l;
        }

        public boolean isSellerReturnPolicyShown()
        {
            return j;
        }

        public void setAvgRatings(float f1)
        {
            i = f1;
        }

        public void setDisplayName(String s)
        {
            a = s;
        }

        public void setFbf(String s)
        {
            n = s;
        }

        public void setListingId(String s)
        {
            c = s;
        }

        public void setOfferExisting(boolean flag)
        {
            l = flag;
        }

        public void setOffersText(ArrayList arraylist)
        {
            k = arraylist;
        }

        public void setPinCodeServiceability(boolean flag)
        {
            h = flag;
        }

        public void setReplacementPolicy(String s)
        {
            g = s;
        }

        public void setSellerReturnPolicyShown(boolean flag)
        {
            j = flag;
        }

        public void setSellingPrice(String s)
        {
            b = s;
        }

        public void setShippingCharge(int i1)
        {
            m = i1;
        }

        public void setShippingText(String s)
        {
            e = s;
        }

        public void setStatus(String s)
        {
            d = s;
        }

        public void setTotalRatings(long l1)
        {
            f = l1;
        }

        public MoreSellerModel()
        {
            m = 0;
        }
    }

}
