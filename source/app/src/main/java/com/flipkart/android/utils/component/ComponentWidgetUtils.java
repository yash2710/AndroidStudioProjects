// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.activity.base.FlipkartBaseSherlockFragmentActivity;
import com.flipkart.android.customwidget.NewTitleWidget;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.HeaderValue;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.data.customvalues.TimerValue;
import com.flipkart.android.response.component.data.customvalues.TitleValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.utils.component:
//            a, b

public class ComponentWidgetUtils
{

    public ComponentWidgetUtils()
    {
    }

    public static View buildTitleWidget(NewTitleWidget newtitlewidget, Context context, HeaderValue headervalue, Action action, com.flipkart.android.customwidget.BaseWidget.WidgetTheme widgettheme, Activity activity)
    {
        TitleValue titlevalue = fetchTitleValue(headervalue);
        if (headervalue == null) goto _L2; else goto _L1
_L1:
        ImageValue imagevalue1 = headervalue.getImageValue();
        if (!(imagevalue1 instanceof ImageValue)) goto _L2; else goto _L3
_L3:
        ImageValue imagevalue = (ImageValue)imagevalue1;
_L4:
        TimerValue timervalue;
        if (headervalue == null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        TimerValue timervalue1 = headervalue.getTimerValue();
        if (!(timervalue1 instanceof TimerValue))
        {
            break MISSING_BLOCK_LABEL_88;
        }
        timervalue = (TimerValue)timervalue1;
_L5:
        if (titlevalue != null)
        {
            if (newtitlewidget != null)
            {
                newtitlewidget.updateView(titlevalue, imagevalue, timervalue, widgettheme, action, activity);
                return newtitlewidget;
            } else
            {
                return new NewTitleWidget(context, titlevalue, timervalue, imagevalue, widgettheme, action, activity);
            }
        } else
        {
            FkLogger.debug("Title", "title value is null ");
            return null;
        }
_L2:
        imagevalue = null;
          goto _L4
        timervalue = null;
          goto _L5
    }

    public static View buildTitleWidget(NewTitleWidget newtitlewidget, Context context, String s, Drawable drawable, Action action, com.flipkart.android.customwidget.BaseWidget.WidgetTheme widgettheme, boolean flag, Activity activity)
    {
        if (s != null)
        {
            if (newtitlewidget != null)
            {
                newtitlewidget.updateView(s, drawable, widgettheme, action, flag, activity);
                return newtitlewidget;
            } else
            {
                return new NewTitleWidget(context, s, drawable, widgettheme, action, flag, activity);
            }
        } else
        {
            FkLogger.debug("Title", "title value is null ");
            return null;
        }
    }

    public static TitleValue fetchTitleValue(HeaderValue headervalue)
    {
        if (headervalue != null)
        {
            TitleValue titlevalue = headervalue.getTitleValue();
            if (titlevalue instanceof TitleValue)
            {
                return (TitleValue)titlevalue;
            }
        }
        return null;
    }

    public static int getActionBarHeight(Context context)
    {
        TypedValue typedvalue = new TypedValue();
        int i = -1;
        if (context.getTheme().resolveAttribute(0x10102eb, typedvalue, true))
        {
            i = TypedValue.complexToDimensionPixelSize(typedvalue.data, context.getResources().getDisplayMetrics());
        }
        return i;
    }

    public static void setActionBarDrawable(String s, FlipkartBaseSherlockFragmentActivity flipkartbasesherlockfragmentactivity)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            android.support.v7.app.ActionBar actionbar = flipkartbasesherlockfragmentactivity.getSupportActionBar();
            ImageLoader imageloader = FlipkartApplication.getImageLoader();
            if (imageloader != null)
            {
                imageloader.get(s, new a(actionbar, flipkartbasesherlockfragmentactivity));
            }
        }
    }

    public static void setWidgetBackground(LayoutDetails layoutdetails, View view)
    {
        if (layoutdetails != null && view != null)
        {
            ImageLoader imageloader = FlipkartApplication.getImageLoader();
            FkLogger.debug("Test", (new StringBuilder("Image url is ")).append(layoutdetails.getBackgroundImage()).toString());
            ImageValue imagevalue = layoutdetails.getBackgroundImage();
            String s = null;
            if (imagevalue != null)
            {
                s = ImageUtils.getImageUrl(layoutdetails.getBackgroundImage().getImage());
                FkLogger.debug("Image", (new StringBuilder("Image url is ")).append(s).toString());
            }
            if (!StringUtils.isNullOrEmpty(layoutdetails.getBackgroundColor()))
            {
                try
                {
                    view.setBackgroundDrawable(new ColorDrawable(Color.parseColor(layoutdetails.getBackgroundColor())));
                }
                catch (Exception exception) { }
            }
            if (!StringUtils.isNullOrEmpty(s))
            {
                imageloader.get(s, new b(view, layoutdetails));
            }
        }
    }

    public static void setWidgetPadding(LayoutDetails layoutdetails, View view)
    {
        if (layoutdetails != null && view != null)
        {
            int i;
            int ai[];
            if (layoutdetails.isFillActionBar())
            {
                FkLogger.debug("Test", "fill action bar is true.");
                i = getActionBarHeight(view.getContext());
            } else
            {
                i = 0;
            }
            ai = new int[4];
            if (layoutdetails.getPaddingInInt() != null)
            {
                int ai1[] = layoutdetails.getPaddingInInt();
                ai[0] = ScreenMathUtils.dpToPx(ai1[0], view.getContext());
                ai[1] = ScreenMathUtils.dpToPx(ai1[1], view.getContext());
                ai[2] = ScreenMathUtils.dpToPx(ai1[2], view.getContext());
                ai[3] = ScreenMathUtils.dpToPx(ai1[3], view.getContext());
            }
            ai[1] = i + ai[1];
            FkLogger.debug("Test", (new StringBuilder("Action bar padding top is ")).append(ai[1]).toString());
            view.setPadding(ai[0], ai[1], ai[2], ai[3]);
        }
    }
}
