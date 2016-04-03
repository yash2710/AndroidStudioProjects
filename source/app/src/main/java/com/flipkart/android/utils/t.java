// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.datahandler.ProductUgcVDataHandler;
import com.flipkart.android.response.ugc.UGCResponse;
import com.flipkart.android.response.ugc.UGCReview;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils:
//            ProductPageUgcBuilder, CustomDialog, ProductPageReviewContext, v

final class t extends ProductUgcVDataHandler
{

    t()
    {
    }

    public final void errorReceived(int i, int j, String s)
    {
        super.errorReceived(i, j, s);
        ProductPageUgcBuilder.a(false);
        ProductPageUgcBuilder.b(true);
        CustomDialog.showErrorMessage(i, j, s, (HomeFragmentHolderActivity)ProductPageUgcBuilder.a());
    }

    public final void resultReceivedUgcInfo(int i, String s, UGCResponse ugcresponse)
    {
        if (i == 200 && ugcresponse != null)
        {
            ProductPageUgcBuilder.a(false);
            UGCReview ugcreview = (UGCReview)ugcresponse.getReview().get(ProductPageUgcBuilder.b().getProductId());
            if (ugcreview != null)
            {
                ArrayList arraylist = ugcreview.getReviewLst();
                ProductPageUgcBuilder.b().getReviewList().addAll(arraylist);
                if ((long)ProductPageUgcBuilder.b().getReviewList().size() >= ProductPageUgcBuilder.b().getTotalReviewCount())
                {
                    ProductPageUgcBuilder.b(true);
                }
                if (ProductPageUgcBuilder.c() != null)
                {
                    ProductPageUgcBuilder.c().notifyDataSetChanged();
                }
                ProductPageUgcBuilder.c(false);
            }
            return;
        } else
        {
            CustomDialog.showErrorMessage("Oops!! Something went wrong. Please try after sometime.", (HomeFragmentHolderActivity)ProductPageUgcBuilder.a());
            return;
        }
    }
}
