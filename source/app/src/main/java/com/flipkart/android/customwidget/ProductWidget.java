// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.customviews.CustomRobotoCondensedBoldTextView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.customviews.PmuProductLayout;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.data.customvalues.OMUImageValue;
import com.flipkart.android.response.component.data.customvalues.OMUValue;
import com.flipkart.android.response.component.data.customvalues.TimerValue;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.MiscUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TimerUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

public class ProductWidget extends PmuProductLayout
{

    public static final int OFFERDESC_ID = 8;
    public static final int OFFERTITLE_ID = 7;
    public static final String ORIENTATION_HORIZONTAL = "horizontal";
    public static final String ORIENTATION_VERTICAL = "vertical";
    public static final int OVERLAY_ID = 4;
    public static final int PRIMARYIMAGE_ID = 3;
    public static final int PRODUCT_IMAGE_ID = 12;
    public static final int PRODUCT_STATUS_ID = 2;
    public static final int SECONDIMAGE_ID = 11;
    public static final int SHARE_ID = 10;
    public static final int SOLDOUT_ID = 5;
    public static final int SPANNABLE_ID = 9;
    public static final int TITLE_ID = 6;
    private Activity activity;
    Timer countTimer;
    Handler handler;
    String shareUrl;
    long timeRemaining;
    private TimerValue timerValue;
    ViewInformation timerView;
    private int timerViewWidth;
    UpdateTimer updateTimer;

    public ProductWidget(Activity activity1, Context context, OMUValue omuvalue, ImageLoader imageloader, String s)
    {
        super(context, imageloader, s, false);
        timeRemaining = 0L;
        handler = null;
        timerView = null;
        timerViewWidth = ScreenMathUtils.dpToPx(120, FlipkartApplication.getAppContext());
        countTimer = new Timer();
        this.context = context;
        activity = activity1;
        shareUrl = omuvalue.getShareUrl();
        buildView(omuvalue, imageloader);
    }

    private void addOverLayView()
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        relativelayout.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(-1, itemHeight));
        relativelayout.setBackgroundColor(Color.parseColor("#B3FFFFFF"));
        relativelayout.setId(4);
        addView(relativelayout);
    }

    private void addProductStatus(String s, Types types)
    {
        CustomRobotoMediumTextView customrobotomediumtextview = new CustomRobotoMediumTextView(context);
        customrobotomediumtextview.setId(2);
        customrobotomediumtextview.setTextSize(10F);
        customrobotomediumtextview.setText(s.toUpperCase());
        customrobotomediumtextview.setTextColor(context.getResources().getColor(0x7f090070));
        customrobotomediumtextview.setMaxLines(1);
        customrobotomediumtextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(10);
        layoutparams.addRule(9);
        int i;
        if (types == Types.UpcomingTitle)
        {
            customrobotomediumtextview.setBackgroundColor(getResources().getColor(0x7f090012));
        } else
        {
            customrobotomediumtextview.setBackgroundColor(getResources().getColor(0x7f090053));
        }
        customrobotomediumtextview.setLayoutParams(layoutparams);
        i = ScreenMathUtils.dpToPx(5, context);
        customrobotomediumtextview.setPadding(i, i, i, i);
        addView(customrobotomediumtextview);
    }

    private void addShare()
    {
        ImageView imageview = new ImageView(context);
        imageview.setImageResource(0x7f02015b);
        if (StringUtils.isNullOrEmpty(shareUrl))
        {
            imageview.setVisibility(8);
            return;
        } else
        {
            imageview.setVisibility(0);
            android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
            layoutparams.addRule(10);
            layoutparams.addRule(11);
            int i = ScreenMathUtils.dpToPx(8, context);
            imageview.setLayoutParams(layoutparams);
            imageview.setPadding(i, i, i, i);
            imageview.setBackgroundResource(0x7f0200f6);
            imageview.setId(10);
            addView(imageview);
            imageview.setOnClickListener(new _cls1());
            return;
        }
    }

    private void addSoldOutImage(String s, ProductStatusTypes productstatustypes)
    {
        CustomRobotoCondensedBoldTextView customrobotocondensedboldtextview = new CustomRobotoCondensedBoldTextView(context);
        LinearLayout linearlayout = new LinearLayout(context);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(30, context));
        layoutparams.addRule(13);
        android.widget.LinearLayout.LayoutParams layoutparams1;
        if (productstatustypes == ProductStatusTypes.Upcoming)
        {
            linearlayout.setBackgroundColor(getResources().getColor(0x7f090012));
        } else
        {
            linearlayout.setBackgroundColor(getResources().getColor(0x7f09004e));
        }
        layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams1.gravity = 17;
        customrobotocondensedboldtextview.setLayoutParams(layoutparams1);
        customrobotocondensedboldtextview.setText(s);
        customrobotocondensedboldtextview.setTextColor(getResources().getColor(0x7f090070));
        customrobotocondensedboldtextview.setTextSize(14F);
        customrobotocondensedboldtextview.setGravity(17);
        linearlayout.setLayoutParams(layoutparams);
        linearlayout.addView(customrobotocondensedboldtextview);
        linearlayout.setId(5);
        addView(linearlayout);
    }

    private void addSpannableTextView(SpannableString spannablestring)
    {
        CustomRobotoLightTextView customrobotolighttextview = new CustomRobotoLightTextView(context);
        customrobotolighttextview.setTextSize(11F);
        customrobotolighttextview.setTextColor(context.getResources().getColor(0x7f090008));
        customrobotolighttextview.setText(spannablestring);
        customrobotolighttextview.setMaxLines(2);
        customrobotolighttextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        customrobotolighttextview.setId(9);
        linearParentLayout.addView(customrobotolighttextview);
    }

    private void addTextView(String s, Types types, int i)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            if (types == Types.FreebieOrDiscount || types == Types.UpcomingTitle)
            {
                CustomRobotoCondensedBoldTextView customrobotocondensedboldtextview = new CustomRobotoCondensedBoldTextView(context);
                customrobotocondensedboldtextview.setId(i);
                customrobotocondensedboldtextview.setTextSize(14F);
                customrobotocondensedboldtextview.setText(s);
                customrobotocondensedboldtextview.setTextColor(context.getResources().getColor(0x7f090034));
                if (types == Types.UpcomingTitle)
                {
                    customrobotocondensedboldtextview.setMaxLines(2);
                } else
                {
                    customrobotocondensedboldtextview.setSingleLine();
                }
                customrobotocondensedboldtextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
                linearParentLayout.addView(customrobotocondensedboldtextview);
            } else
            {
                if (types == Types.SubTitle)
                {
                    CustomRobotoLightTextView customrobotolighttextview = new CustomRobotoLightTextView(context);
                    customrobotolighttextview.setTextSize(11F);
                    customrobotolighttextview.setId(i);
                    customrobotolighttextview.setTextColor(context.getResources().getColor(0x7f090008));
                    customrobotolighttextview.setText(Html.fromHtml(s));
                    customrobotolighttextview.setMaxLines(2);
                    customrobotolighttextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    linearParentLayout.addView(customrobotolighttextview);
                    return;
                }
                if (types == Types.Title)
                {
                    CustomRobotoMediumTextView customrobotomediumtextview = new CustomRobotoMediumTextView(context);
                    customrobotomediumtextview.setTextSize(12F);
                    customrobotomediumtextview.setId(i);
                    customrobotomediumtextview.setText(s);
                    customrobotomediumtextview.setTextColor(context.getResources().getColor(0x7f090008));
                    customrobotomediumtextview.setMaxLines(1);
                    customrobotomediumtextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
                    linearParentLayout.addView(customrobotomediumtextview);
                    return;
                }
            }
        }
    }

    private void addTimer()
    {
        long l = 0L;
        if (timerValue != null)
        {
            timeRemaining = timerValue.getTimeRemaining();
            if (timeRemaining != l)
            {
                timeRemaining = timeRemaining / 1000L;
            }
            long l1 = timerValue.getCurrentSystemTime();
            if (l1 != l)
            {
                l = (System.currentTimeMillis() - l1) / 1000L;
            }
            timeRemaining = timeRemaining - l;
            handler = new Handler();
            timerView = getTimerView();
        }
    }

    private void buildView(OMUValue omuvalue, ImageLoader imageloader)
    {
        if (omuvalue == null) goto _L2; else goto _L1
_L1:
        int i;
        linearParentLayout.setBackgroundResource(0x7f020122);
        linearParentLayout.setPadding(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(25, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context));
        String s = omuvalue.getLimitedStockText();
        if (omuvalue.isDisplayLimitedStock() && !StringUtils.isNullOrEmpty(s))
        {
            addProductStatus(s, Types.LimitedStock);
        }
        linearParentLayout.addView(getImageView(omuvalue, imageloader));
        String s1 = omuvalue.getName();
        String s2 = omuvalue.getProductStatus();
        Double double1 = omuvalue.getFsp();
        Double double2 = omuvalue.getMrp();
        String s3;
        int j;
        if (!StringUtils.isNullOrEmpty(s2))
        {
            if (s2.equalsIgnoreCase(ProductStatusTypes.Upcoming.toString()))
            {
                addOverLayView();
                addSoldOutImage("COMING SOON", ProductStatusTypes.Upcoming);
            } else
            {
                addTextView(s1, Types.Title, 6);
                if (s2.equalsIgnoreCase(ProductStatusTypes.SoldOut.toString()))
                {
                    addOverLayView();
                    addSoldOutImage("SOLD OUT", ProductStatusTypes.SoldOut);
                }
            }
        } else
        {
            addTextView(s1, Types.Title, 6);
        }
        s3 = omuvalue.getOfferTitle();
        if (!StringUtils.isNullOrEmpty(s3)) goto _L4; else goto _L3
_L3:
        j = MiscUtils.roundoffDecimal((float)(100D * ((double2.doubleValue() - double1.doubleValue()) / double2.doubleValue())));
        i = j;
_L6:
        if (i != 0)
        {
            s3 = (new StringBuilder()).append(i).append("% OFF").toString();
        }
_L4:
        if (!StringUtils.isNullOrEmpty(s3))
        {
            addTextView(s3, Types.FreebieOrDiscount, 7);
        }
        String s4 = omuvalue.getOfferDescription();
        if (double2 != null && double2.doubleValue() != 0.0D && double1 != null && double1.doubleValue() != 0.0D)
        {
            addSpannableTextView(getHtmlString(double2.doubleValue(), double1.doubleValue()));
        } else
        if (!StringUtils.isNullOrEmpty(s4))
        {
            addTextView(s4, Types.SubTitle, 8);
        }
        addShare();
_L2:
        return;
        Exception exception;
        exception;
        i = 0;
        if (true) goto _L6; else goto _L5
_L5:
    }

    private void fetchTimerValue(OMUValue omuvalue)
    {
        if (omuvalue != null)
        {
            timerValue = new TimerValue();
            timerValue.setTimeRemaining(omuvalue.getTimeLeft());
        }
    }

    private SpannableString getHtmlString(double d, double d1)
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append((new StringBuilder("Rs ")).append(d).toString());
        int i = stringbuilder.toString().length();
        stringbuilder.append((new StringBuilder(" Rs ")).append(d1).toString());
        SpannableString spannablestring = new SpannableString(stringbuilder.toString());
        spannablestring.setSpan(new StrikethroughSpan(), 0, i, 0);
        spannablestring.setSpan(new StyleSpan(1), i + 1, stringbuilder.toString().length(), 0);
        spannablestring.setSpan(new RelativeSizeSpan(1.1F), i + 1, stringbuilder.toString().length(), 0);
        return spannablestring;
    }

    private View getImageView(OMUValue omuvalue, ImageLoader imageloader)
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        relativelayout.setId(3);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1, 0.6F);
        relativelayout.setPadding(0, 0, 0, ScreenMathUtils.dpToPx(10, context));
        relativelayout.setLayoutParams(layoutparams);
        String s = null;
        if (omuvalue != null)
        {
            OMUImageValue omuimagevalue = omuvalue.getPrimaryImage();
            s = null;
            if (omuimagevalue != null)
            {
                s = omuvalue.getPrimaryImage().fetchBestImageUrl("ProductList page", "dynamic", screenDpi);
            }
        }
        NetworkImageView networkimageview = new NetworkImageView(context);
        networkimageview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        networkimageview.setBackgroundColor(0);
        networkimageview.setDefaultImageResId(0x7f0200d5);
        if (!StringUtils.isNullOrEmpty(s))
        {
            networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
            networkimageview.setImageUrl(s, imageloader);
        }
        networkimageview.setId(12);
        relativelayout.addView(networkimageview);
        if (omuvalue.getSecondaryImage() != null)
        {
            View view = getSecondaryImage(omuvalue.getSecondaryImage(), imageloader);
            view.setId(11);
            relativelayout.addView(view);
        }
        return relativelayout;
    }

    private View getSecondaryImage(ImageValue imagevalue, ImageLoader imageloader)
    {
        NetworkImageView networkimageview = new NetworkImageView(context);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(12);
        layoutparams.addRule(11);
        networkimageview.setLayoutParams(layoutparams);
        int i = ScreenMathUtils.dpToPx(10, context);
        networkimageview.setPadding(i, i, i, i);
        networkimageview.setBackgroundColor(0);
        networkimageview.setDefaultImageResId(0x7f0200d5);
        if (imagevalue != null)
        {
            String s = ImageUtils.getImageUrl(imagevalue.getImage());
            if (!StringUtils.isNullOrEmpty(s))
            {
                networkimageview.setImageUrl(s, imageloader);
            }
        }
        return networkimageview;
    }

    private ViewInformation getTimerView()
    {
        if (timeRemaining < 0L)
        {
            timeRemaining = 0L;
        }
        String s = TimerUtils.getTimerAsText(timeRemaining);
        CustomRobotoMediumTextView customrobotomediumtextview = new CustomRobotoMediumTextView(context);
        customrobotomediumtextview.setTextSize(10F);
        customrobotomediumtextview.setText(s);
        customrobotomediumtextview.setTextColor(context.getResources().getColor(0x7f090070));
        customrobotomediumtextview.setMaxLines(1);
        customrobotomediumtextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-2, -2);
        layoutparams.addRule(10);
        layoutparams.addRule(9);
        customrobotomediumtextview.setBackgroundColor(getResources().getColor(0x7f090053));
        customrobotomediumtextview.setLayoutParams(layoutparams);
        int i = ScreenMathUtils.dpToPx(5, context);
        customrobotomediumtextview.setPadding(i, i, i, i);
        addView(customrobotomediumtextview);
        if (timeRemaining > 0L)
        {
            updateTimer = new UpdateTimer(customrobotomediumtextview);
            countTimer.scheduleAtFixedRate(updateTimer, 0L, 1000L);
        }
        return new ViewInformation(customrobotomediumtextview, timerViewWidth);
    }

    private void updateProductStatus(View view, String s, Types types)
    {
        CustomRobotoMediumTextView customrobotomediumtextview = (CustomRobotoMediumTextView)view;
        customrobotomediumtextview.setText(s.toUpperCase());
        if (types == Types.UpcomingTitle)
        {
            customrobotomediumtextview.setBackgroundColor(getResources().getColor(0x7f090012));
            return;
        } else
        {
            customrobotomediumtextview.setBackgroundColor(getResources().getColor(0x7f090053));
            return;
        }
    }

    private void updateSpannableTextView(SpannableString spannablestring)
    {
        ((CustomRobotoLightTextView)linearParentLayout.findViewById(9)).setText(spannablestring);
    }

    private void updateTextView(String s, Types types, int i)
    {
        if (StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        if (types != Types.FreebieOrDiscount && types != Types.UpcomingTitle) goto _L4; else goto _L3
_L3:
        CustomRobotoCondensedBoldTextView customrobotocondensedboldtextview;
        customrobotocondensedboldtextview = (CustomRobotoCondensedBoldTextView)linearParentLayout.findViewById(i);
        customrobotocondensedboldtextview.setText(s);
        if (types != Types.UpcomingTitle) goto _L6; else goto _L5
_L5:
        customrobotocondensedboldtextview.setMaxLines(2);
_L2:
        return;
_L6:
        customrobotocondensedboldtextview.setSingleLine();
        return;
_L4:
        if (types == Types.SubTitle)
        {
            ((CustomRobotoLightTextView)linearParentLayout.findViewById(i)).setText(Html.fromHtml(s));
            return;
        }
        if (types == Types.Title)
        {
            ((CustomRobotoMediumTextView)linearParentLayout.findViewById(i)).setText(s);
            return;
        }
        if (true) goto _L2; else goto _L7
_L7:
    }

    public void refreshView()
    {
        if (activity != null && (activity instanceof HomeFragmentHolderActivity))
        {
            ((HomeFragmentHolderActivity)activity).refreshPage();
        }
    }

    protected void share()
    {
        ArrayList arraylist;
        Iterator iterator;
        if (activity == null || activity.isFinishing())
        {
            break MISSING_BLOCK_LABEL_353;
        }
        TrackingHelper.sendSocialSharing();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", "Check this out on Flipkart!");
        intent.putExtra("android.intent.extra.TEXT", shareUrl);
        arraylist = new ArrayList();
        iterator = activity.getApplicationContext().getPackageManager().queryIntentActivities(intent, 0).iterator();
_L2:
        String s;
        Intent intent2;
        do
        {
            if (!iterator.hasNext())
            {
                break MISSING_BLOCK_LABEL_277;
            }
            s = ((ResolveInfo)iterator.next()).activityInfo.packageName;
        } while (s.equalsIgnoreCase(activity.getPackageName()));
        intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("text/plain");
        if (!s.equalsIgnoreCase("com.android.mms") && !s.equalsIgnoreCase("com.facebook.orca") && !s.equalsIgnoreCase("com.google.android.talk"))
        {
            break; /* Loop/switch isn't completed */
        }
        intent2.putExtra("android.intent.extra.TEXT", (new StringBuilder()).append("Check this out on Flipkart!").append("\n").append(shareUrl).toString());
_L3:
        intent2.setPackage(s);
        arraylist.add(intent2);
        if (true) goto _L2; else goto _L1
_L1:
        intent2.putExtra("android.intent.extra.SUBJECT", "Check this out on Flipkart!");
        intent2.putExtra("android.intent.extra.TEXT", shareUrl);
          goto _L3
        try
        {
            Intent intent1 = Intent.createChooser((Intent)arraylist.remove(0), "Share on ..");
            intent1.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[])arraylist.toArray(new Parcelable[0]));
            context.startActivity(intent1);
            CrashLoggerUtils.pushAndUpdate((new StringBuilder("sharing product : ")).append(shareUrl).toString());
            return;
        }
        catch (Exception exception) { }
    }

    public void updateRecycledProductWidget(OMUValue omuvalue)
    {
        View view4;
        View view5;
        View view6;
        View view7;
        String s3;
        int i;
        LinearLayout linearlayout = (LinearLayout)findViewById(1);
        RelativeLayout relativelayout = (RelativeLayout)linearlayout.findViewById(3);
        View view = findViewById(2);
        View view1 = findViewById(4);
        View view2 = findViewById(5);
        View view3 = linearlayout.findViewById(6);
        view4 = linearlayout.findViewById(7);
        view5 = linearlayout.findViewById(8);
        view6 = linearlayout.findViewById(9);
        view7 = linearlayout.findViewById(10);
        if (view != null)
        {
            view.setVisibility(0);
        }
        if (view1 != null)
        {
            view1.setVisibility(0);
        }
        if (view2 != null)
        {
            view2.setVisibility(0);
        }
        if (view3 != null)
        {
            view3.setVisibility(0);
        }
        if (view4 != null)
        {
            view4.setVisibility(0);
        }
        if (view5 != null)
        {
            view5.setVisibility(0);
        }
        if (view6 != null)
        {
            view6.setVisibility(0);
        }
        if (view7 != null)
        {
            view7.setVisibility(0);
        }
        NetworkImageView networkimageview = (NetworkImageView)relativelayout.findViewById(12);
        NetworkImageView networkimageview1 = (NetworkImageView)relativelayout.findViewById(11);
        if (networkimageview != null)
        {
            networkimageview.setVisibility(0);
        }
        if (networkimageview1 != null)
        {
            networkimageview1.setVisibility(0);
        }
        String s = omuvalue.getLimitedStockText();
        String s1;
        String s2;
        if (omuvalue.isDisplayLimitedStock() && !StringUtils.isNullOrEmpty(s))
        {
            if (view == null)
            {
                addProductStatus(s, Types.LimitedStock);
            } else
            {
                updateProductStatus(view, s, Types.LimitedStock);
            }
        } else
        if (view != null)
        {
            view.setVisibility(8);
        }
        if (omuvalue != null && omuvalue.getPrimaryImage() != null)
        {
            String s6 = omuvalue.getPrimaryImage().fetchBestImageUrl("ProductList page", "dynamic", screenDpi);
            networkimageview.setDefaultImageResId(0x7f0200d5);
            networkimageview.setImageUrl(s6, customImageloader);
        } else
        {
            networkimageview.setVisibility(8);
        }
        if (omuvalue != null && omuvalue.getSecondaryImage() != null)
        {
            String s5 = ImageUtils.getImageUrl(omuvalue.getSecondaryImage().getImage());
            Double double1;
            Double double2;
            int j;
            if (!StringUtils.isNullOrEmpty(s5))
            {
                if (networkimageview1 == null)
                {
                    View view8 = getSecondaryImage(omuvalue.getSecondaryImage(), customImageloader);
                    view8.setId(11);
                    relativelayout.addView(view8);
                } else
                {
                    networkimageview1.setImageUrl(s5, customImageloader);
                }
            } else
            if (networkimageview1 != null)
            {
                networkimageview1.setVisibility(8);
            }
        } else
        if (networkimageview1 != null)
        {
            networkimageview1.setVisibility(8);
        }
        s1 = omuvalue.getName();
        s2 = omuvalue.getProductStatus();
        double1 = omuvalue.getFsp();
        double2 = omuvalue.getMrp();
        if (!StringUtils.isNullOrEmpty(s2))
        {
            if (s2.equalsIgnoreCase(ProductStatusTypes.Upcoming.toString()))
            {
                if (view1 == null)
                {
                    addOverLayView();
                }
                if (view2 == null)
                {
                    addSoldOutImage("COMING SOON", ProductStatusTypes.Upcoming);
                }
            } else
            {
                if (view3 == null)
                {
                    addTextView(s1, Types.Title, 6);
                } else
                {
                    updateTextView(s1, Types.Title, 6);
                }
                if (s2.equalsIgnoreCase(ProductStatusTypes.SoldOut.toString()))
                {
                    if (view1 == null)
                    {
                        addOverLayView();
                    }
                    if (view2 == null)
                    {
                        addSoldOutImage("SOLD OUT", ProductStatusTypes.SoldOut);
                    }
                } else
                {
                    if (view1 != null)
                    {
                        view1.setVisibility(8);
                    }
                    if (view2 != null)
                    {
                        view2.setVisibility(8);
                    }
                }
            }
        } else
        {
            if (view3 == null)
            {
                addTextView(s1, Types.Title, 6);
            } else
            {
                updateTextView(s1, Types.Title, 6);
            }
            if (view1 != null)
            {
                view1.setVisibility(8);
            }
            if (view2 != null)
            {
                view2.setVisibility(8);
            }
        }
        s3 = omuvalue.getOfferTitle();
        if (!StringUtils.isNullOrEmpty(s3)) goto _L2; else goto _L1
_L1:
        j = MiscUtils.roundoffDecimal((float)(100D * ((double2.doubleValue() - double1.doubleValue()) / double2.doubleValue())));
        i = j;
_L8:
        if (i != 0)
        {
            s3 = (new StringBuilder()).append(i).append("% OFF").toString();
        }
_L2:
        String s4;
        if (!StringUtils.isNullOrEmpty(s3))
        {
            if (view4 == null)
            {
                addTextView(s3, Types.FreebieOrDiscount, 7);
            } else
            {
                updateTextView(s3, Types.FreebieOrDiscount, 7);
            }
        } else
        if (view4 != null)
        {
            view4.setVisibility(8);
        }
        s4 = omuvalue.getOfferDescription();
        if (double2 != null && double2.doubleValue() != 0.0D && double1 != null && double1.doubleValue() != 0.0D)
        {
            SpannableString spannablestring = getHtmlString(double2.doubleValue(), double1.doubleValue());
            if (view6 == null)
            {
                addSpannableTextView(spannablestring);
            } else
            {
                updateSpannableTextView(spannablestring);
            }
            if (view5 != null)
            {
                view5.setVisibility(8);
            }
        } else
        if (!StringUtils.isNullOrEmpty(s4))
        {
            if (view5 == null)
            {
                addTextView(s4, Types.SubTitle, 8);
            } else
            {
                updateTextView(s4, Types.SubTitle, 8);
            }
            if (view6 != null)
            {
                view6.setVisibility(8);
            }
        }
        if (shareUrl == null || view7 != null) goto _L4; else goto _L3
_L3:
        addShare();
_L6:
        return;
_L4:
        if (shareUrl != null || view7 == null) goto _L6; else goto _L5
_L5:
        view7.setVisibility(8);
        return;
        Exception exception;
        exception;
        i = 0;
        if (true) goto _L8; else goto _L7
_L7:
    }

    private class Types extends Enum
    {

        private static final Types $VALUES[];
        public static final Types FreebieOrDiscount;
        public static final Types LimitedStock;
        public static final Types SubTitle;
        public static final Types Title;
        public static final Types UpcomingTitle;

        public static Types valueOf(String s)
        {
            return (Types)Enum.valueOf(com/flipkart/android/customwidget/ProductWidget$Types, s);
        }

        public static Types[] values()
        {
            return (Types[])$VALUES.clone();
        }

        static 
        {
            Title = new Types("Title", 0);
            FreebieOrDiscount = new Types("FreebieOrDiscount", 1);
            SubTitle = new Types("SubTitle", 2);
            UpcomingTitle = new Types("UpcomingTitle", 3);
            LimitedStock = new Types("LimitedStock", 4);
            Types atypes[] = new Types[5];
            atypes[0] = Title;
            atypes[1] = FreebieOrDiscount;
            atypes[2] = SubTitle;
            atypes[3] = UpcomingTitle;
            atypes[4] = LimitedStock;
            $VALUES = atypes;
        }

        private Types(String s, int i)
        {
            super(s, i);
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        final ProductWidget this$0;

        public void onClick(View view)
        {
            share();
        }

        _cls1()
        {
            this$0 = ProductWidget.this;
            super();
        }
    }


    private class ProductStatusTypes extends Enum
    {

        private static final ProductStatusTypes $VALUES[];
        public static final ProductStatusTypes SoldOut;
        public static final ProductStatusTypes Upcoming;

        public static ProductStatusTypes valueOf(String s)
        {
            return (ProductStatusTypes)Enum.valueOf(com/flipkart/android/customwidget/ProductWidget$ProductStatusTypes, s);
        }

        public static ProductStatusTypes[] values()
        {
            return (ProductStatusTypes[])$VALUES.clone();
        }

        static 
        {
            Upcoming = new ProductStatusTypes("Upcoming", 0);
            SoldOut = new ProductStatusTypes("SoldOut", 1);
            ProductStatusTypes aproductstatustypes[] = new ProductStatusTypes[2];
            aproductstatustypes[0] = Upcoming;
            aproductstatustypes[1] = SoldOut;
            $VALUES = aproductstatustypes;
        }

        private ProductStatusTypes(String s, int i)
        {
            super(s, i);
        }
    }


    private class UpdateTimer extends TimerTask
    {

        final ProductWidget this$0;
        TextView timerView;

        public void run()
        {
            class _cls1
                implements Runnable
            {

                final UpdateTimer this$1;

                public void run()
                {
                    timeRemaining = timeRemaining - 1L;
                    String s = TimerUtils.getTimerAsText(timeRemaining);
                    timerView.setText(s);
                    if (timeRemaining <= 0L)
                    {
                        cancel();
                        refreshView();
                    }
                }

                _cls1()
                {
                    this$1 = UpdateTimer.this;
                    super();
                }
            }

            if (handler != null)
            {
                handler.post(new _cls1());
            }
        }

        public UpdateTimer(TextView textview)
        {
            this$0 = ProductWidget.this;
            super();
            timerView = textview;
        }
    }


    private class ViewInformation
    {

        final ProductWidget this$0;
        private View view;
        private int width;

        public View getView()
        {
            return view;
        }

        public int getWidth()
        {
            return width;
        }

        public void setView(View view1)
        {
            view = view1;
        }

        public void setWidth(int i)
        {
            width = i;
        }

        public ViewInformation(View view1, int i)
        {
            this$0 = ProductWidget.this;
            super();
            view = view1;
            width = i;
        }
    }

}
