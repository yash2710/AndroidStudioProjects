// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.TextView;
import com.flipkart.android.cart.ICartListener;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.fragments:
//            ProductPageSpecificationFragment

final class aL
    implements ICartListener
{

    private View a;
    private ProductPageSpecificationFragment b;

    aL(ProductPageSpecificationFragment productpagespecificationfragment, View view)
    {
        b = productpagespecificationfragment;
        a = view;
        super();
    }

    public final void itemAddedToCart(String s, Boolean boolean1, String s1)
    {
        if (s != null && boolean1.booleanValue()) goto _L2; else goto _L1
_L1:
        TextView textview = (TextView)a;
        textview.setText("+Cart");
        textview.setEnabled(true);
        if (StringUtils.isNullOrEmpty(s1)) goto _L4; else goto _L3
_L3:
        ToastMessageUtils.showErrorToastMessage(s1, b.activity, false);
_L6:
        return;
_L4:
        ToastMessageUtils.showErrorToastMessage("Sorry..Add to cart failed.", b.activity, false);
        return;
_L2:
        if (b.a != null && b.a.getProductIds().get(b.b) != null && ((String)b.a.getProductIds().get(b.b)).equals(s))
        {
            TextView textview1 = (TextView)a;
            textview1.setText("GoTo Cart");
            textview1.setEnabled(true);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }
}
