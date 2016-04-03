// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.PopUpResponse;

// Referenced classes of package com.flipkart.android.utils:
//            ImageUtils, StringUtils, c, d

public class CustomPopupDialog
{

    private Dialog a;

    public CustomPopupDialog()
    {
        a = null;
    }

    static Dialog a(CustomPopupDialog custompopupdialog)
    {
        return custompopupdialog.a;
    }

    public void showPopupDialog(PopUpResponse popupresponse, Activity activity)
    {
        if (popupresponse != null && activity != null)
        {
            String s = ImageUtils.getImageUrl(popupresponse.getImageMap());
            String s1 = popupresponse.getSubTitle();
            String s2 = popupresponse.getTitle();
            com.flipkart.android.response.customwidgetitemvalue.Action action = popupresponse.getAction1();
            com.flipkart.android.response.customwidgetitemvalue.Action action1 = popupresponse.getAction2();
            String s3 = popupresponse.getButtonText1();
            String s4 = popupresponse.getButtonText2();
            a = new Dialog(activity);
            a.requestWindowFeature(1);
            LinearLayout linearlayout = (LinearLayout)activity.getLayoutInflater().inflate(0x7f03003a, null);
            ((TextView)linearlayout.findViewById(0x7f0a0053)).setText(s2);
            if (!StringUtils.isNullOrEmpty(s4))
            {
                Button button = (Button)linearlayout.findViewById(0x7f0a00bb);
                button.setVisibility(0);
                button.setText(s4);
            }
            ((Button)linearlayout.findViewById(0x7f0a00bc)).setText(s3);
            NetworkImageView networkimageview = (NetworkImageView)linearlayout.findViewById(0x7f0a00b8);
            if (!StringUtils.isNullOrEmpty(s))
            {
                networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
                networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
            } else
            {
                networkimageview.setVisibility(8);
            }
            ((WebView)linearlayout.findViewById(0x7f0a00b9)).loadData(s1, "text/html", "UTF-8");
            a.setContentView(linearlayout);
            a.show();
            ((Button)linearlayout.findViewById(0x7f0a00bb)).setOnClickListener(new c(this, action1, activity));
            ((Button)linearlayout.findViewById(0x7f0a00bc)).setOnClickListener(new d(this, action, activity));
        }
    }
}
