// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.fragments.model.ProductPageModel;
import com.flipkart.android.response.productInfo.MarketPlaceSeller;

// Referenced classes of package com.flipkart.android.utils:
//            SellerTypes, ProductSpecificSellerTypes

public class SellerTypeUtils
{

    public SellerTypeUtils()
    {
    }

    public static ProductSpecificSellerTypes getSellerType(ProductPageModel productpagemodel, MarketPlaceSeller marketplaceseller)
    {
        if (productpagemodel != null && marketplaceseller != null)
        {
            int i = productpagemodel.getCheapestPrice();
            if (marketplaceseller.isWSR())
            {
                if (productpagemodel.getSellerType() == SellerTypes.WSR_SINGLE)
                {
                    return ProductSpecificSellerTypes.WSR_SINGLE;
                }
                if (marketplaceseller.getSellingPrice() == i)
                {
                    return ProductSpecificSellerTypes.Pref_Cheap_WSR;
                } else
                {
                    return ProductSpecificSellerTypes.Pref_NCheap_WSR;
                }
            }
            if (productpagemodel.getSellerType() == SellerTypes.NWSR_SINGLE)
            {
                return ProductSpecificSellerTypes.NWSR_SINGLE;
            }
            if (marketplaceseller.getSellingPrice() == i)
            {
                return ProductSpecificSellerTypes.Pref_Cheap_NWSR;
            } else
            {
                return ProductSpecificSellerTypes.Pref_NCheap_NWSR;
            }
        } else
        {
            return ProductSpecificSellerTypes.NONE;
        }
    }
}
