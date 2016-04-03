// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.webview;

import android.content.Context;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.ProductSpecificSellerTypes;
import com.flipkart.android.utils.SellerTypes;
import com.flipkart.android.utils.StringUtils;

public class WebviewLauncher
{

    public WebviewLauncher()
    {
    }

    public static void launchBuyNow(String s, String s1, String s2, ProductSpecificSellerTypes productspecificsellertypes, SellerTypes sellertypes, String s3, Context context)
    {
        if (context != null && (context instanceof HomeFragmentHolderActivity))
        {
            CrashLoggerUtils.pushAndUpdate((new StringBuilder("buying product: ")).append(s).toString());
            HomeFragmentHolderActivity homefragmentholderactivity = (HomeFragmentHolderActivity)context;
            if (!StringUtils.isNullOrEmpty(s1))
            {
                homefragmentholderactivity.doBuyNow(s, s1, s2, productspecificsellertypes, sellertypes, s3);
            }
        }
    }
}
