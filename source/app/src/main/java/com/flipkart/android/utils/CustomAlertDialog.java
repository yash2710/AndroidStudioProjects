// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;

// Referenced classes of package com.flipkart.android.utils:
//            StringUtils, b, FontCache, a

public class CustomAlertDialog extends AlertDialog
{

    private Activity a;

    public CustomAlertDialog(Context context)
    {
        super(context);
    }

    private View a(String s)
    {
        View view = ((LayoutInflater)a.getApplicationContext().getSystemService("layout_inflater")).inflate(0x7f03003d, null);
        TextView textview = (TextView)view.findViewById(0x7f0a0053);
        View view1 = view.findViewById(0x7f0a00cb);
        if (StringUtils.isNullOrEmpty(s))
        {
            textview.setVisibility(8);
            view1.setVisibility(8);
            return view;
        } else
        {
            textview.setVisibility(0);
            textview.setText(s);
            return view;
        }
    }

    private void a(View view)
    {
        setView(view);
        if (a != null && !a.isFinishing())
        {
            show();
        }
    }

    private void a(Button button, String s)
    {
        button.setText(s);
        button.setOnClickListener(new b(this, s));
    }

    static void a(CustomAlertDialog customalertdialog, String s)
    {
        try
        {
            if (s.equalsIgnoreCase("ok"))
            {
                customalertdialog.dismiss();
                return;
            }
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
        catch (Exception exception)
        {
            return;
        }
        if (s.contains("UPGRADE"))
        {
            FlipkartPreferenceManager.instance().saveIsShowAppUpgradePopUp(Boolean.valueOf(false));
            TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade_Prompt");
            String s2 = customalertdialog.a.getPackageName();
            Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("https://play.google.com/store/apps/details?id=")).append(s2).toString()));
            customalertdialog.a.startActivity(intent1);
            customalertdialog.dismiss();
            return;
        }
        if (!s.contains("LATER"))
        {
            break MISSING_BLOCK_LABEL_126;
        }
        customalertdialog.dismiss();
        if (s.equals("LATER"))
        {
            TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade_Later");
            return;
        }
        TrackingHelper.sendRateAndUpgradeAppEvent("Rate_Later");
        return;
        if (s.contains("RATE"))
        {
            FlipkartPreferenceManager.instance().saveIsShowRateTheAppPopUp(Boolean.valueOf(false));
            TrackingHelper.sendRateAndUpgradeAppEvent("Rate_Prompt");
            String s1 = customalertdialog.a.getPackageName();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder("https://play.google.com/store/apps/details?id=")).append(s1).toString()));
            customalertdialog.a.startActivity(intent);
            customalertdialog.dismiss();
            return;
        }
        if (s.contains("THANKS"))
        {
            FlipkartPreferenceManager.instance().saveIsShowRateTheAppPopUp(Boolean.valueOf(false));
            TrackingHelper.sendRateAndUpgradeAppEvent("Rate_No_thanks");
            customalertdialog.dismiss();
        }
        return;
    }

    public void showDoubleButtonDialog(String s, String s1, Activity activity, String s2, String s3, String s4)
    {
        if (activity == null)
        {
            break MISSING_BLOCK_LABEL_218;
        }
        if (!activity.isFinishing())
        {
            a = activity;
            View view = a(s);
            TextView textview = (TextView)view.findViewById(0x7f0a0053);
            TextView textview1 = (TextView)view.findViewById(0x7f0a00cc);
            textview1.setText(s1);
            view.findViewById(0x7f0a00cb).setVisibility(8);
            if (!StringUtils.isNullOrEmpty(s2))
            {
                NetworkImageView networkimageview = (NetworkImageView)view.findViewById(0x7f0a00ca);
                networkimageview.setImageUrl(s2, FlipkartApplication.getImageLoader());
                networkimageview.setVisibility(0);
            }
            if (s4.contains("UPGRADE"))
            {
                TrackingHelper.sendRateAndUpgradeAppEvent("Upgrade Popup shown");
                FlipkartPreferenceManager.instance().saveAppUpgradePromptShownCount(1 + FlipkartPreferenceManager.instance().getAppUpgradePromptShownCount());
            }
            textview.setTextSize(18F);
            textview.setTypeface(FontCache.getFont("robotomedium.ttf"));
            textview1.setTextSize(16F);
            textview1.setText(Html.fromHtml(s1));
            textview1.setGravity(3);
            view.findViewById(0x7f0a00ba).setVisibility(0);
            a((Button)view.findViewById(0x7f0a00cd), s3);
            a((Button)view.findViewById(0x7f0a00ce), s4);
            a(view);
        }
        return;
        Exception exception;
        exception;
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
    }

    public void showSingleButtonDialog(String s, String s1, Activity activity, String s2)
    {
        if (activity == null)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        if (!activity.isFinishing())
        {
            a = activity;
            View view = a(s);
            ((TextView)view.findViewById(0x7f0a00cc)).setText(s1);
            setButton2(s2.toUpperCase(), new a(this, s2));
            a(view);
        }
        return;
        Exception exception;
        exception;
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
    }

    public void showTripleButtonDialog(String s, String s1, Activity activity, String s2, String s3, String s4, String s5)
    {
        if (activity == null)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        if (!activity.isFinishing())
        {
            a = activity;
            View view = a(s);
            TextView textview = (TextView)view.findViewById(0x7f0a0053);
            TextView textview1 = (TextView)view.findViewById(0x7f0a00cc);
            view.findViewById(0x7f0a00cb).setVisibility(8);
            if (!StringUtils.isNullOrEmpty(s2))
            {
                NetworkImageView networkimageview = (NetworkImageView)view.findViewById(0x7f0a00ca);
                networkimageview.setImageUrl(s2, FlipkartApplication.getImageLoader());
                networkimageview.setVisibility(0);
            }
            if (s3.contains("RATE"))
            {
                FlipkartPreferenceManager.instance().saveAppRatePromptShownCount(1 + FlipkartPreferenceManager.instance().getAppRatePromptShownCount());
                TrackingHelper.sendRateAndUpgradeAppEvent("Rate Popup shown");
            }
            textview1.setText(s1);
            textview.setTextSize(18F);
            textview.setTypeface(FontCache.getFont("robotomedium.ttf"));
            textview1.setTextSize(16F);
            textview1.setText(Html.fromHtml(s1));
            textview1.setGravity(1);
            view.findViewById(0x7f0a00cf).setVisibility(0);
            a((Button)view.findViewById(0x7f0a00d0), s3);
            a((Button)view.findViewById(0x7f0a00d1), s4);
            ((Button)view.findViewById(0x7f0a00d2)).setVisibility(8);
            a(view);
        }
        return;
        Exception exception;
        exception;
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
    }
}
