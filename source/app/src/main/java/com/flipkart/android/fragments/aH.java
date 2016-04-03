// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import com.flipkart.android.datahandler.ProductUgcVDataHandler;
import com.flipkart.android.response.ugc.UGCResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageReviewFragment

final class aH extends ProductUgcVDataHandler
{

    private ProductPageReviewFragment a;

    aH(ProductPageReviewFragment productpagereviewfragment, String s, String s1, int i, int j)
    {
        a = productpagereviewfragment;
        super(s, s1, 0, j);
    }

    public final void errorReceived(int i, int j, String s)
    {
        ProductPageReviewFragment.a(a);
        if (ProductPageReviewFragment.b(a) != null)
        {
            ProductPageReviewFragment.b(a).dismissDlg();
        }
        CustomDialog.showErrorMessage(i, j, s, a.a);
    }

    public final void resultReceivedUgcInfo(int i, String s, UGCResponse ugcresponse)
    {
        ProductPageReviewFragment.a(a);
        if (ProductPageReviewFragment.b(a) != null)
        {
            ProductPageReviewFragment.b(a).dismissDlg();
        }
        if (i == 200 && ugcresponse != null)
        {
            ProductPageReviewFragment.a(a, ugcresponse);
            StringBuilder stringbuilder = new StringBuilder("productUgc is null? ");
            boolean flag;
            if (ProductPageReviewFragment.c(a) == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            FkLogger.debug("ProductUgcFragment", stringbuilder.append(flag).toString());
            ProductPageReviewFragment.d(a);
            return;
        } else
        {
            CustomDialog.showErrorMessage("Oops!! Something went wrong. Please try after sometime.", a.a);
            return;
        }
    }
}
