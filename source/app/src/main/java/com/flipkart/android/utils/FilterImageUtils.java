// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.response.component.data.customvalues.Image;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils:
//            AppConfigUtils, ImageUtils, StringUtils

public class FilterImageUtils
{

    public FilterImageUtils()
    {
    }

    public static boolean showFilterImage(String s, LinearLayout linearlayout, ImageLoader imageloader)
    {
        Map map = AppConfigUtils.getInstance().getFilterImagesMap();
        if (map == null)
        {
            return false;
        }
        String s1 = ImageUtils.getImageUrl((Image)map.get(s));
        if (StringUtils.isNullOrEmpty(s1))
        {
            return false;
        } else
        {
            NetworkImageView networkimageview = (NetworkImageView)linearlayout.findViewById(0x7f0a00e3);
            networkimageview.setVisibility(0);
            networkimageview.setImageUrl(s1, imageloader);
            return true;
        }
    }
}
