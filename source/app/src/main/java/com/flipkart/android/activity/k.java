// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import com.flipkart.android.cart.Cart;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.cart.ICartListener;
import com.flipkart.android.datahandler.AddToCartVDataHandler;
import com.flipkart.android.response.cart.AddToCartResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.ToastExpander;
import com.flipkart.android.utils.ToastMessageUtils;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class k extends AddToCartVDataHandler
{

    private HomeFragmentHolderActivity a;

    k(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    private void a(String s, Boolean boolean1, String s1)
    {
        if (HomeFragmentHolderActivity.c(a) != null)
        {
            HomeFragmentHolderActivity.c(a).itemAddedToCart(s, boolean1, s1);
        }
    }

    public final void errorReceived(int i, int j, String s)
    {
        ToastExpander.cancel();
        ToastMessageUtils.showErrorToastMessage((new StringBuilder("Add to cart failed.")).append(CustomDialog.getErrorMessage(i)).toString(), a, false);
        a(null, Boolean.valueOf(false), null);
    }

    public final void resultReceived(AddToCartResponse addtocartresponse, boolean flag)
    {
        ToastExpander.cancel();
        if (!addtocartresponse.isAddedToCart()) goto _L2; else goto _L1
_L1:
        int i;
        TextView textview;
        Cart cart = new Cart();
        cart.setItems(addtocartresponse.getItems());
        CartHandler.save(cart);
        i = CartHandler.getCart().getCartItemCount();
        textview = (TextView)a.getSupportActionBar().getCustomView().findViewById(0x7f0a009e);
        int l = Integer.parseInt(textview.getText().toString());
        int j = l;
_L4:
        HomeFragmentHolderActivity.a(a, j, i, textview);
        a(addtocartresponse.getProductId(), Boolean.valueOf(true), null);
        return;
_L2:
        a(addtocartresponse.getProductId(), Boolean.valueOf(false), addtocartresponse.getErrorMessage());
        return;
        Exception exception;
        exception;
        j = 0;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((AddToCartResponse)obj, flag);
    }
}
