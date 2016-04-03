// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipkart.android.datahandler.SellerVDataHandler;
import com.flipkart.android.response.seller.SellerResponse;
import com.flipkart.android.utils.CustomDialog;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.fragments:
//            SellerFragment

final class bq extends SellerVDataHandler
{

    private SellerFragment a;

    bq(SellerFragment sellerfragment, String s)
    {
        a = sellerfragment;
        super(s);
    }

    public final void errorReceived(int i, int j, String s)
    {
        a.a.setVisibility(8);
        a.showError(SellerFragment.a(a), i, a, false);
    }

    public final void resultReceivedSellerInfo(int i, String s, SellerResponse sellerresponse)
    {
        if (SellerFragment.a(a) != null)
        {
            a.a.setVisibility(8);
            a.b.setVisibility(0);
            View view = SellerFragment.a(a).findViewById(0x7f0a00d6);
            if (view != null)
            {
                ((RelativeLayout)SellerFragment.a(a)).removeView(view);
            }
        }
        if (i == 200)
        {
            SellerFragment.a(a, sellerresponse);
            SellerFragment.b(a);
        } else
        if (SellerFragment.a(a) != null)
        {
            LinearLayout linearlayout = (LinearLayout)SellerFragment.a(a).findViewById(0x7f0a0137);
            linearlayout.setVisibility(0);
            TextView textview = (TextView)linearlayout.findViewById(0x7f0a0138);
            String s1 = CustomDialog.getErrorMessage(i);
            Button button;
            if (!StringUtils.isNullOrEmpty(s1))
            {
                textview.setText(s1);
            } else
            {
                textview.setText("Oops!! Something went wrong.Please try again");
            }
            button = (Button)linearlayout.findViewById(0x7f0a0139);
            button.setOnClickListener(a);
            button.setTag("load_home_fragment");
            return;
        }
    }
}
