// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TextView;
import com.flipkart.android.cart.Cart;
import com.flipkart.android.cart.CartHandler;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.CartVDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.cart.CartResponse;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.activity:
//            HomeFragmentHolderActivity

final class l extends CartVDataHandler
{

    private HomeFragmentHolderActivity a;

    l(HomeFragmentHolderActivity homefragmentholderactivity)
    {
        a = homefragmentholderactivity;
        super();
    }

    public final void resultReceived(CartResponse cartresponse, boolean flag)
    {
        Cart cart;
        int i;
        TextView textview;
        cart = new Cart();
        cart.setItems(cartresponse.getItems());
        CartHandler.save(cart);
        i = CartHandler.getCart().getCartItemCount();
        textview = (TextView)a.getSupportActionBar().getCustomView().findViewById(0x7f0a009e);
        int k = Integer.parseInt(textview.getText().toString());
        int j = k;
_L2:
        HomeFragmentHolderActivity.a(a, j, i, textview);
        if (HomeFragmentHolderActivity.d(a) != null)
        {
            HomeFragmentHolderActivity.d(a).dismissDlg();
        }
        if (!StringUtils.isNullOrEmpty(HomeFragmentHolderActivity.e(a)))
        {
            String s = HomeFragmentHolderActivity.e(a);
            HomeFragmentHolderActivity.a(a, null);
            ArrayList arraylist = cart.getProductsIds();
            ArrayList arraylist1 = cart.getListingIds();
            if (arraylist.indexOf(s) == -1)
            {
                arraylist.clear();
                arraylist.add(s);
            }
            a.openProductPage(arraylist, arraylist1, arraylist.indexOf(s), "", new AnalyticData(null, null, PageTypeUtils.HomePage));
        }
        Intent intent = new Intent();
        intent.setAction(HomeFragmentHolderActivity.CART_UPDATED);
        FlipkartApplication.getAppContext().sendBroadcast(intent);
        return;
        Exception exception;
        exception;
        j = 0;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public final volatile void resultReceived(Object obj, boolean flag)
    {
        resultReceived((CartResponse)obj, flag);
    }
}
