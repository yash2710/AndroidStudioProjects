// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.response.appconfig.FBFData;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.utils:
//            AppConfigUtils, ImageUtils, StringUtils

public class FbfUtils
{

    public FbfUtils()
    {
    }

    public static void showFbf(LinearLayout linearlayout, ImageLoader imageloader, android.view.View.OnClickListener onclicklistener)
    {
        FBFData fbfdata;
        com.flipkart.android.response.customwidgetitemvalue.Action action;
        String s;
        String s1;
        TextView textview;
        try
        {
            fbfdata = AppConfigUtils.getInstance().getFbfData();
        }
        catch (Exception exception)
        {
            FkLogger.printStackTrace(exception);
            return;
        }
        if (fbfdata == null)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        action = fbfdata.getAction();
        s = ImageUtils.getImageUrl(fbfdata.getEnd_image());
        if (StringUtils.isNullOrEmpty(s))
        {
            linearlayout.setVisibility(8);
            return;
        }
        s1 = fbfdata.getTitle();
        textview = (TextView)linearlayout.findViewById(0x7f0a01a3);
        if (!StringUtils.isNullOrEmpty(s1))
        {
            textview.setText(s1);
        }
        ((NetworkImageView)linearlayout.findViewById(0x7f0a01a4)).setImageUrl(s, imageloader);
        if (action == null)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        linearlayout.setTag(action);
        linearlayout.setOnClickListener(onclicklistener);
        linearlayout.setVisibility(0);
        return;
        linearlayout.setVisibility(8);
        return;
    }
}
