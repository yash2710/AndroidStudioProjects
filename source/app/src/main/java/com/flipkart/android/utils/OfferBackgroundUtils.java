// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.layout.LayoutDetails;

// Referenced classes of package com.flipkart.android.utils:
//            ImageUtils, StringUtils, j

public class OfferBackgroundUtils
{

    public OfferBackgroundUtils()
    {
    }

    public static void setOffersBackground(View view, LayoutDetails layoutdetails)
    {
        if (layoutdetails == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        if (layoutdetails.getBackgroundColor() == null)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        view.setBackgroundDrawable(new ColorDrawable(Color.parseColor(layoutdetails.getBackgroundColor())));
_L1:
        if (layoutdetails != null)
        {
            try
            {
                if (layoutdetails.getBackgroundImage() != null)
                {
                    String s = ImageUtils.getImageUrl(layoutdetails.getBackgroundImage().getImage());
                    if (!StringUtils.isNullOrEmpty(s))
                    {
                        FlipkartApplication.getImageLoader().get(s, new j(view));
                        return;
                    }
                }
            }
            catch (Exception exception) { }
        }
        break MISSING_BLOCK_LABEL_92;
        view.setBackgroundDrawable(view.getResources().getDrawable(0x7f02011a));
          goto _L1
    }
}
