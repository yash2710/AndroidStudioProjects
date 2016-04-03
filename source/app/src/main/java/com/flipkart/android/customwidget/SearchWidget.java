// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.SearchValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.component.ComponentWidgetUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class SearchWidget extends BaseWidget
{

    private static final int DARK_COLOR = 0xffdedede;
    private static final int LIGHT_COLOR = 0xffababab;
    public static final String WIDGET_COMMON_NAME = "SEARCH";
    private Action action;
    private boolean isNokiaOEM;
    private String searchHint;
    private boolean showBarCode;
    private boolean showVoiceIcon;

    public SearchWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, WidgetItem widgetitem, ArrayList arraylist, android.view.View.OnClickListener onclicklistener, Activity activity, 
            int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        searchHint = null;
        showVoiceIcon = true;
        showBarCode = true;
        isNokiaOEM = false;
        action = null;
        if (FlipkartPreferenceManager.instance().isNokiaDevice())
        {
            isNokiaOEM = true;
        }
        LinearLayout linearlayout;
        TextView textview;
        if (arraylist != null && arraylist.size() > 0)
        {
            WidgetItem widgetitem1 = (WidgetItem)arraylist.get(0);
            if (widgetitem1 != null)
            {
                SearchValue searchvalue = (SearchValue)widgetitem1.getValue();
                Action action1 = widgetitem1.getAction();
                if (searchvalue != null)
                {
                    LayoutDetails layoutdetails1;
                    int j;
                    android.widget.LinearLayout.LayoutParams layoutparams;
                    ImageView imageview;
                    android.widget.LinearLayout.LayoutParams layoutparams1;
                    android.widget.LinearLayout.LayoutParams layoutparams2;
                    ImageView imageview1;
                    android.widget.LinearLayout.LayoutParams layoutparams3;
                    ImageView imageview2;
                    android.widget.LinearLayout.LayoutParams layoutparams4;
                    boolean flag;
                    if (!isNokiaOEM)
                    {
                        showVoiceIcon = searchvalue.isShowVoice();
                        showBarCode = searchvalue.isShowBarCode();
                    } else
                    {
                        showVoiceIcon = false;
                        showBarCode = false;
                    }
                    searchHint = searchvalue.getSearchHint();
                    action = action1;
                }
            }
        }
        if (activity != null)
        {
            FkLogger.debug("SearchWidget", "Search Widget disabled");
            HomeFragmentHolderActivity.isUpFrontSearchPresent = true;
        }
        layoutdetails1 = layoutDetails;
        j = 0;
        if (layoutdetails1 != null)
        {
            flag = layoutDetails.isFillActionBar();
            j = 0;
            if (flag)
            {
                j = ComponentWidgetUtils.getActionBarHeight(context);
            }
        }
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, j + ScreenMathUtils.dpToPx(60, context)));
        linearlayout = new LinearLayout(context);
        linearlayout.setOrientation(0);
        linearlayout.setBackgroundResource(0x7f020150);
        layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -1);
        layoutparams.setMargins(ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context), ScreenMathUtils.dpToPx(10, context));
        linearlayout.setPadding(ScreenMathUtils.dpToPx(10, context), 0, ScreenMathUtils.dpToPx(10, context), 0);
        linearlayout.setLayoutParams(layoutparams);
        imageview = new ImageView(context);
        layoutparams1 = new android.widget.LinearLayout.LayoutParams(-2, -2, 10F);
        layoutparams1.gravity = 16;
        imageview.setLayoutParams(layoutparams1);
        imageview.setImageResource(0x7f02014f);
        linearlayout.addView(imageview);
        textview = new TextView(context);
        layoutparams2 = new android.widget.LinearLayout.LayoutParams(-2, -2, 100F);
        layoutparams2.gravity = 16;
        textview.setLayoutParams(layoutparams2);
        if (StringUtils.isNullOrEmpty(searchHint))
        {
            textview.setText("  Search");
        } else
        {
            textview.setText((new StringBuilder(" ")).append(searchHint).toString());
        }
        textview.setContentDescription("Search_for_products");
        textview.setSingleLine(true);
        textview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        textview.setTextSize(2, 15F);
        if (theme.equals(BaseWidget.WidgetTheme.dark))
        {
            textview.setTextColor(0xffdedede);
        } else
        {
            textview.setTextColor(0xffababab);
        }
        textview.setTextSize(2, 15F);
        linearlayout.addView(textview);
        if (showVoiceIcon)
        {
            imageview1 = new ImageView(context);
            layoutparams3 = new android.widget.LinearLayout.LayoutParams(-2, -2, 15F);
            layoutparams3.gravity = 16;
            imageview1.setLayoutParams(layoutparams3);
            imageview1.setImageResource(0x7f020181);
            imageview1.setPadding(0, 0, ScreenMathUtils.dpToPx(5, context), 0);
            linearlayout.addView(imageview1);
        }
        if (showBarCode && context.getPackageManager().hasSystemFeature("android.hardware.camera.autofocus"))
        {
            imageview2 = new ImageView(context);
            layoutparams4 = new android.widget.LinearLayout.LayoutParams(-2, -2, 15F);
            layoutparams4.gravity = 16;
            imageview2.setLayoutParams(layoutparams4);
            imageview2.setImageResource(0x7f02013d);
            linearlayout.addView(imageview2);
        }
        if (action != null)
        {
            linearlayout.setTag(action);
        } else
        {
            linearlayout.setTag("open_search_page/upfront_search");
        }
        linearlayout.setOnClickListener(onClickListener);
        addView(linearlayout);
    }
}
