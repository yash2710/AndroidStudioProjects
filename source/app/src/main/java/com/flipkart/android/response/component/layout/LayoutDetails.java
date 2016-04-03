// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.component.layout;

import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.logging.FkLogger;

public class LayoutDetails
{

    String backgroundColor;
    ImageValue backgroundImage;
    String backgroundTileMode;
    boolean fillActionBar;
    Double heightFactor;
    String orientation;
    String padding;
    boolean showTitle;
    boolean stickyScroll;
    String theme;

    public LayoutDetails()
    {
        orientation = "";
        showTitle = true;
    }

    public String getBackgroundColor()
    {
        return backgroundColor;
    }

    public ImageValue getBackgroundImage()
    {
        return backgroundImage;
    }

    public String getBackgroundTileMode()
    {
        return backgroundTileMode;
    }

    public Double getHeightFactor()
    {
        return heightFactor;
    }

    public String getOrientation()
    {
        return orientation;
    }

    public String getPadding()
    {
        return padding;
    }

    public int[] getPaddingInInt()
    {
        if (padding != null) goto _L2; else goto _L1
_L1:
        int ai[] = null;
_L4:
        return ai;
_L2:
        padding = padding.replace(" ", "");
        ai = new int[4];
        String as[] = padding.split(",");
        if (as.length != 4) goto _L4; else goto _L3
_L3:
        ai[0] = Integer.parseInt(as[0]);
        ai[1] = Integer.parseInt(as[1]);
        ai[2] = Integer.parseInt(as[2]);
        ai[3] = Integer.parseInt(as[3]);
        return ai;
        Exception exception;
        exception;
        FkLogger.printStackTrace(exception);
        return ai;
    }

    public String getTheme()
    {
        return theme;
    }

    public com.flipkart.android.customwidget.BaseWidget.WidgetTheme getThemeObject()
    {
        if (theme != null)
        {
            if (theme.equalsIgnoreCase("dark"))
            {
                return com.flipkart.android.customwidget.BaseWidget.WidgetTheme.dark;
            } else
            {
                return com.flipkart.android.customwidget.BaseWidget.WidgetTheme.light;
            }
        } else
        {
            return com.flipkart.android.customwidget.BaseWidget.WidgetTheme.light;
        }
    }

    public boolean isFillActionBar()
    {
        return fillActionBar;
    }

    public boolean isShowTitle()
    {
        return showTitle;
    }

    public boolean isStickyScroll()
    {
        return stickyScroll;
    }

    public void setBackgroundColor(String s)
    {
        backgroundColor = s;
    }

    public void setBackgroundImage(ImageValue imagevalue)
    {
        backgroundImage = imagevalue;
    }

    public void setBackgroundTileMode(String s)
    {
        backgroundTileMode = s;
    }

    public void setFillActionBar(boolean flag)
    {
        fillActionBar = flag;
    }

    public void setHeightFactor(Double double1)
    {
        heightFactor = double1;
    }

    public void setOrientation(String s)
    {
        orientation = s;
    }

    public void setPadding(String s)
    {
        padding = s;
    }

    public void setShowTitle(boolean flag)
    {
        showTitle = flag;
    }

    public void setStickyScroll(boolean flag)
    {
        stickyScroll = flag;
    }

    public void setTheme(String s)
    {
        theme = s;
    }
}
