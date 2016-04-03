// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import com.flipkart.android.cart.ICartListener;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageSummaryFragment

final class aN
    implements ICartListener
{

    private View a;
    private ProductPageSummaryFragment b;

    aN(ProductPageSummaryFragment productpagesummaryfragment, View view)
    {
        b = productpagesummaryfragment;
        a = view;
        super();
    }

    public final void itemAddedToCart(String s, Boolean boolean1, String s1)
    {
        if (s != null && boolean1.booleanValue()) goto _L2; else goto _L1
_L1:
        CustomRobotoRegularTextView customrobotoregulartextview = (CustomRobotoRegularTextView)a;
        customrobotoregulartextview.setText("+Cart");
        customrobotoregulartextview.setEnabled(true);
        if (StringUtils.isNullOrEmpty(s1)) goto _L4; else goto _L3
_L3:
        ToastMessageUtils.showErrorToastMessage(s1, b.activity, false);
_L6:
        return;
_L4:
        ToastMessageUtils.showErrorToastMessage("Sorry..Add to cart failed.", b.activity, false);
        return;
_L2:
        if (b.b != null && b.b.getProductIds().get(b.a) != null && ((String)b.b.getProductIds().get(b.a)).equals(s))
        {
            CustomRobotoRegularTextView customrobotoregulartextview1 = (CustomRobotoRegularTextView)a;
            customrobotoregulartextview1.setText("GoTo Cart");
            customrobotoregulartextview1.setEnabled(true);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }
}
