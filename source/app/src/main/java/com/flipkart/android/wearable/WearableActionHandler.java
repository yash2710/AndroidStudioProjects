// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.wearable;

import com.flipkart.android.DB.FlipkartProductInfo;
import com.flipkart.android.DB.FlipkartProductInfoDao;
import com.flipkart.android.DB.WishListDao;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.ProductInfoVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.productInfo.ProductInfo;
import com.flipkart.android.response.ugc.UGCRating;
import com.flipkart.android.response.ugc.UGCRatingObj;
import com.flipkart.android.utils.GsonUtils;
import com.flipkart.android.wearable.shared.WearableWishListItem;
import com.flipkart.logging.FkLogger;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.flipkart.android.wearable:
//            a, WearableDataSender

public class WearableActionHandler
{

    private static final String a = com/flipkart/android/wearable/WearableActionHandler.getName();
    private GoogleApiClient b;

    public WearableActionHandler(GoogleApiClient googleapiclient)
    {
        b = googleapiclient;
    }

    static GoogleApiClient a(WearableActionHandler wearableactionhandler)
    {
        return wearableactionhandler.b;
    }

    static String a()
    {
        return a;
    }

    static void a(WearableActionHandler wearableactionhandler, List list, List list1, int i)
    {
        wearableactionhandler.a(list, list1, i);
    }

    private void a(List list, List list1, int i)
    {
        if (i < list1.size())
        {
            int j;
            List list2;
            if (i + 9 <= list1.size())
            {
                j = i + 9;
            } else
            {
                j = list1.size();
            }
            list2 = list1.subList(i, j);
            FkLogger.verbose(a, (new StringBuilder("Requesting from ")).append(i).append(" to ").append(j).toString());
            (new a(this, 1, j, list1, list, i)).fetchProductInfoForProducts(list2, null, null, FlipkartPreferenceManager.instance().getUserPinCode(), 1, null);
        }
    }

    public void refreshWishList()
    {
        ArrayList arraylist1;
label0:
        {
            WishListDao wishlistdao = new WishListDao(FlipkartApplication.getAppContext());
            FlipkartProductInfoDao flipkartproductinfodao = new FlipkartProductInfoDao(FlipkartApplication.getAppContext());
            ArrayList arraylist = wishlistdao.getAllWishListPids();
            if (arraylist != null)
            {
                arraylist1 = new ArrayList();
                ArrayList arraylist2 = new ArrayList();
                for (Iterator iterator = arraylist.iterator(); iterator.hasNext();)
                {
                    String s = (String)iterator.next();
                    FlipkartProductInfo flipkartproductinfo = flipkartproductinfodao.getFlipkartProductInfoById(s);
                    if (flipkartproductinfo == null)
                    {
                        arraylist2.add(s);
                    } else
                    {
                        ProductInfo productinfo = (ProductInfo)GsonUtils.getResponse(com/flipkart/android/response/productInfo/ProductInfo, flipkartproductinfo.getResponse(), true);
                        WearableWishListItem wearablewishlistitem = new WearableWishListItem();
                        wearablewishlistitem.setPrice(productinfo.getSellingPrice());
                        wearablewishlistitem.setMrp(productinfo.getMrp());
                        wearablewishlistitem.setShowMrp(productinfo.isShowMrp());
                        wearablewishlistitem.setProductTitle(productinfo.getMainTitle());
                        wearablewishlistitem.setOverallRating(productinfo.getUgc().getRatingObj().getOverallRating());
                        wearablewishlistitem.setTotalRatingCount(productinfo.getUgc().getRatingObj().getTotalRatingCount());
                        wearablewishlistitem.setId(productinfo.getProductId());
                        ArrayList arraylist3 = productinfo.fetchBestImageUrls(300, 300);
                        if (arraylist3 != null && arraylist3.size() > 0)
                        {
                            wearablewishlistitem.setImageUrl((String)arraylist3.get(0));
                        }
                        arraylist1.add(wearablewishlistitem);
                    }
                }

                if (arraylist2.size() <= 0)
                {
                    break label0;
                }
                a(arraylist1, arraylist2, 0);
            }
            return;
        }
        (new WearableDataSender(b)).updateWishList(arraylist1);
    }

}
