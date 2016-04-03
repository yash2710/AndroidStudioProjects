// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments.model;

import android.content.Context;
import android.content.res.Resources;
import android.view.Display;
import android.view.WindowManager;
import com.flipkart.android.response.discovery.OmnitureData;
import com.flipkart.android.response.productInfo.CheapestEmi;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;
import com.flipkart.android.response.productInfo.ProductAvailabilityDetails;
import com.flipkart.android.response.productInfo.ProductImage;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.ugc.UGCRating;
import com.flipkart.android.response.ugc.UGCRatingObj;
import com.flipkart.android.response.ugc.UGCReviewObj;
import com.flipkart.android.utils.MiscUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments.model:
//            ProductPageModel

public class ProductListItemModel
{

    private String A;
    private String B;
    private String C;
    private boolean D;
    private String E;
    private String a;
    private String b;
    private String c;
    private String d;
    private boolean e;
    private String f[];
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private boolean l;
    private String m;
    private boolean n;
    private String o;
    private String p;
    private String q;
    private boolean r;
    private float s;
    private long t;
    private boolean u;
    private String v;
    private int w;
    private Map x;
    private boolean y;
    private boolean z;

    public ProductListItemModel()
    {
        q = null;
    }

    private static void a(ProductInfo productinfo, ProductListItemModel productlistitemmodel, Context context)
    {
        WindowManager windowmanager = (WindowManager)context.getSystemService("window");
        int i1 = windowmanager.getDefaultDisplay().getWidth();
        int j1 = ScreenMathUtils.dpToPx(110, context);
        ProductImage productimage = productinfo.fetchBestImage(j1, j1);
        if (productimage != null)
        {
            productlistitemmodel.setPrimaryImageUrlList(productimage.getUrl());
        }
        int k1 = (i1 - ScreenMathUtils.dpToPx(40, context)) / 2;
        ProductImage productimage1 = productinfo.fetchBestImage(k1, k1);
        if (productimage1 != null)
        {
            productlistitemmodel.setPrimaryImageUrlGrid(productimage1.getUrl());
        }
        int l1 = windowmanager.getDefaultDisplay().getHeight() - (3 * ScreenMathUtils.dpToPx(60, context) + ScreenMathUtils.dpToPx(40, context));
        ProductImage productimage2 = productinfo.fetchBestImage((i1 * 90) / 100, l1);
        if (productimage2 != null)
        {
            productlistitemmodel.setPrimaryImageFullScreen(productimage2.getUrl());
        }
    }

    public static ProductListItemModel getProductListModel(ProductInfo productinfo, Context context)
    {
        ProductListItemModel productlistitemmodel;
        int i1;
        String s2;
        if (productinfo == null)
        {
            return null;
        }
        productlistitemmodel = new ProductListItemModel();
        productlistitemmodel.setProductId(productinfo.getProductId());
        productlistitemmodel.setMainTitle(productinfo.getMainTitle());
        productlistitemmodel.setSubTitle(productinfo.getSubTitle());
        productlistitemmodel.q = productinfo.getOmnitureData().getVertical();
        productlistitemmodel.setProductErrorImageUrl(productinfo.getProductErrorImage());
        productlistitemmodel.setProductPageUrl(productinfo.getProductPageUrl());
        productlistitemmodel.setSizeChartUrl(productinfo.getSizeChartUrl());
        productlistitemmodel.setRequestId(productinfo.getRequestId());
        productlistitemmodel.setListingId(productinfo.getPreferredListingId());
        ArrayList arraylist;
        if (productinfo.getCheapestEmi() != null)
        {
            String s3 = productinfo.getCheapestEmi().getMonthlyInstallment();
            MarketPlaceSeller marketplaceseller;
            String s1;
            double d1;
            double d2;
            int j1;
            if (!StringUtils.isNullOrEmpty(s3))
            {
                productlistitemmodel.setEmiText((new StringBuilder("EMI starts from ")).append(s3).toString());
            } else
            {
                productlistitemmodel.setEmiText("");
            }
        }
        productlistitemmodel.setCategory(productinfo.getOmnitureData().getCategory());
        arraylist = productinfo.getProductOffers();
        if (arraylist == null || arraylist.size() == 0)
        {
            productlistitemmodel.setOfferExisting(false);
        } else
        {
            productlistitemmodel.setOfferExisting(true);
            productlistitemmodel.setOffersText((String[])arraylist.toArray(new String[arraylist.size()]));
        }
        productinfo.getDynamicImageUrl().get(productinfo.getPrimaryImageId());
        s2 = productinfo.fetchBestImageUrl("ProductList page");
        if (s2 != null) goto _L2; else goto _L1
_L1:
        try
        {
            a(productinfo, productlistitemmodel, context);
        }
        catch (Exception exception)
        {
            a(productinfo, productlistitemmodel, context);
        }
        if (productinfo.getSellingPrice() == 0) goto _L4; else goto _L3
_L3:
        productlistitemmodel.setFspShown(true);
_L5:
        productlistitemmodel.setFsp((new StringBuilder()).append(productinfo.getSellingPrice()).toString());
        productlistitemmodel.setMrpVisible(productinfo.isShowMrp());
        productlistitemmodel.setMrp((new StringBuilder()).append(productinfo.getMrp()).toString());
        productlistitemmodel.setCheckoutEnable(productinfo.isEnableCheckout());
        d1 = productinfo.getSellingPrice();
        d2 = productinfo.getMrp();
        j1 = MiscUtils.roundoffDecimal((float)(100D * ((d2 - d1) / d2)));
        i1 = j1;
_L6:
        productlistitemmodel.setDiscount((new StringBuilder()).append(i1).toString());
        UGCRating ugcrating = productinfo.getUgc();
        if (ugcrating != null)
        {
            Exception exception1;
            Exception exception2;
            long l1;
            long l2;
            if (ugcrating.getRatingObj() != null)
            {
                l1 = ugcrating.getRatingObj().getTotalRatingCount();
            } else
            {
                l1 = 0L;
            }
            if (ugcrating.getReviewObj() != null)
            {
                l2 = ugcrating.getReviewObj().getTotalNoReviews();
            } else
            {
                l2 = 0L;
            }
            if (l1 == 0L && l2 == 0L)
            {
                productlistitemmodel.setRatingVisible(false);
            } else
            {
                productlistitemmodel.setRatingVisible(true);
                productlistitemmodel.setTotalRatings(l1);
                productlistitemmodel.setRating((float)ugcrating.getRatingObj().getOverallRating());
            }
        } else
        {
            productlistitemmodel.setRatingVisible(false);
        }
        marketplaceseller = productinfo.getPreferredSeller();
        if (marketplaceseller != null)
        {
            productlistitemmodel.setInventoryStatus(marketplaceseller.getAvailiabilityDetails().getAvailabilityStatus());
            productlistitemmodel.setInventoryStatusToBeShown(marketplaceseller.getAvailiabilityDetails().isShowStatus());
            s1 = marketplaceseller.getAvailiabilityDetails().getAvailabilityStatusIntent();
            if (s1 != null && s1.equals("positive"))
            {
                productlistitemmodel.setInventoryStatusColor(context.getResources().getColor(0x7f090035));
            } else
            {
                productlistitemmodel.setInventoryStatusColor(context.getResources().getColor(0x7f090048));
            }
        }
        productlistitemmodel.setEbook(productinfo.isEbook());
        productlistitemmodel.setSwatchesMap(ProductPageModel.processSwatches(productinfo));
        productlistitemmodel.setImageLandscape(false);
        return productlistitemmodel;
_L2:
        productlistitemmodel.setPrimaryImageUrlList(s2);
        productlistitemmodel.setPrimaryImageUrlGrid(productinfo.fetchBestImageUrl("ProductGrid"));
        productlistitemmodel.setPrimaryImageFullScreen(productinfo.fetchBestImageUrl("ProductList full view"));
        break MISSING_BLOCK_LABEL_199;
_L4:
        try
        {
            productlistitemmodel.setFspShown(false);
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception1)
        {
            productlistitemmodel.setFspShown(false);
        }
          goto _L5
        exception2;
        i1 = 0;
          goto _L6
    }

    public String getCategory()
    {
        return A;
    }

    public String getDiscount()
    {
        return p;
    }

    public String getEmiText()
    {
        return C;
    }

    public String getFsp()
    {
        return m;
    }

    public String getInventoryStatus()
    {
        return v;
    }

    public int getInventoryStatusColor()
    {
        return w;
    }

    public String getListingId()
    {
        return b;
    }

    public String getMainTitle()
    {
        return c;
    }

    public String getMrp()
    {
        return o;
    }

    public String[] getOffersText()
    {
        return f;
    }

    public String getPrimaryImageFullScreen()
    {
        return j;
    }

    public String getPrimaryImageUrlGrid()
    {
        return i;
    }

    public String getPrimaryImageUrlList()
    {
        return g;
    }

    public String getProductErrorImageUrl()
    {
        return h;
    }

    public String getProductId()
    {
        return a;
    }

    public String getProductPageUrl()
    {
        return k;
    }

    public String getProductVertical()
    {
        return q;
    }

    public float getRating()
    {
        return s;
    }

    public String getRequestId()
    {
        return E;
    }

    public String getSizeChartUrl()
    {
        return B;
    }

    public String getSubTitle()
    {
        return d;
    }

    public Map getSwatchesMap()
    {
        return x;
    }

    public long getTotalRatings()
    {
        return t;
    }

    public boolean isCheckoutEnable()
    {
        return z;
    }

    public boolean isEbook()
    {
        return y;
    }

    public boolean isFspShown()
    {
        return l;
    }

    public boolean isImageLandscape()
    {
        return D;
    }

    public boolean isInventoryStatusToBeShown()
    {
        return u;
    }

    public boolean isMrpVisible()
    {
        return n;
    }

    public boolean isOfferExisting()
    {
        return e;
    }

    public boolean isRatingVisible()
    {
        return r;
    }

    public void setCategory(String s1)
    {
        A = s1;
    }

    public void setCheckoutEnable(boolean flag)
    {
        z = flag;
    }

    public void setDiscount(String s1)
    {
        p = s1;
    }

    public void setEbook(boolean flag)
    {
        y = flag;
    }

    public void setEmiText(String s1)
    {
        C = s1;
    }

    public void setFsp(String s1)
    {
        m = s1;
    }

    public void setFspShown(boolean flag)
    {
        l = flag;
    }

    public void setImageLandscape(boolean flag)
    {
        D = flag;
    }

    public void setInventoryStatus(String s1)
    {
        v = s1;
    }

    public void setInventoryStatusColor(int i1)
    {
        w = i1;
    }

    public void setInventoryStatusToBeShown(boolean flag)
    {
        u = flag;
    }

    public void setListingId(String s1)
    {
        b = s1;
    }

    public void setMainTitle(String s1)
    {
        c = s1;
    }

    public void setMrp(String s1)
    {
        o = s1;
    }

    public void setMrpVisible(boolean flag)
    {
        n = flag;
    }

    public void setOfferExisting(boolean flag)
    {
        e = flag;
    }

    public void setOffersText(String as[])
    {
        f = as;
    }

    public void setPrimaryImageFullScreen(String s1)
    {
        j = s1;
    }

    public void setPrimaryImageUrlGrid(String s1)
    {
        i = s1;
    }

    public void setPrimaryImageUrlList(String s1)
    {
        g = s1;
    }

    public void setProductErrorImageUrl(String s1)
    {
        h = s1;
    }

    public void setProductId(String s1)
    {
        a = s1;
    }

    public void setProductPageUrl(String s1)
    {
        k = s1;
    }

    public void setRating(float f1)
    {
        s = f1;
    }

    public void setRatingVisible(boolean flag)
    {
        r = flag;
    }

    public void setRequestId(String s1)
    {
        E = s1;
    }

    public void setSizeChartUrl(String s1)
    {
        B = s1;
    }

    public void setSubTitle(String s1)
    {
        d = s1;
    }

    public void setSwatchesMap(Map map)
    {
        x = map;
    }

    public void setTotalRatings(long l1)
    {
        t = l1;
    }
}
