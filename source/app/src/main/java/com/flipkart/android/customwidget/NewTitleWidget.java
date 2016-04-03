// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.data.customvalues.TimerValue;
import com.flipkart.android.response.component.data.customvalues.TitleValue;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TimerUtils;
import com.flipkart.logging.FkLogger;
import java.util.Timer;

public class NewTitleWidget extends LinearLayout
{

    public static int MORE_VIEW_ID = 1000;
    private Action action;
    private Activity activity;
    private int buttonColor;
    private Context context;
    private Timer countTimer;
    private Drawable drawable;
    private Handler handler;
    private int iconWidth;
    private ImageValue imageValue;
    private boolean isLarge;
    private int moreViewSize;
    private int moreViewWidth;
    private int screenDpi;
    private int textColor;
    private BaseWidget.WidgetTheme theme;
    private long timeRemaining;
    private Drawable timerDrawable;
    private int timerImageWidth;
    private int timerTextSize;
    private TimerValue timerValue;
    private int timerViewWidth;
    private int titleLayoutWidth;
    private int titleSize;
    private String titleString;
    private TitleValue titleValue;
    private UpdateTimer updateTimer;

    public NewTitleWidget(Context context1, TitleValue titlevalue, TimerValue timervalue, ImageValue imagevalue, BaseWidget.WidgetTheme widgettheme, Action action1, Activity activity1)
    {
        super(context1);
        titleLayoutWidth = 0;
        screenDpi = 320;
        iconWidth = ScreenMathUtils.dpToPx(20, FlipkartApplication.getAppContext());
        moreViewWidth = ScreenMathUtils.dpToPx(65, FlipkartApplication.getAppContext());
        timerViewWidth = ScreenMathUtils.dpToPx(120, FlipkartApplication.getAppContext());
        timerImageWidth = ScreenMathUtils.dpToPx(15, FlipkartApplication.getAppContext());
        moreViewSize = 13;
        titleSize = 18;
        timerTextSize = 15;
        countTimer = new Timer();
        handler = null;
        timeRemaining = 0L;
        context = context1;
        titleValue = titlevalue;
        imageValue = imagevalue;
        timerValue = timervalue;
        action = action1;
        theme = widgettheme;
        activity = activity1;
        buildView();
    }

    public NewTitleWidget(Context context1, String s, Drawable drawable1, BaseWidget.WidgetTheme widgettheme, Action action1, boolean flag, Activity activity1)
    {
        super(context1);
        titleLayoutWidth = 0;
        screenDpi = 320;
        iconWidth = ScreenMathUtils.dpToPx(20, FlipkartApplication.getAppContext());
        moreViewWidth = ScreenMathUtils.dpToPx(65, FlipkartApplication.getAppContext());
        timerViewWidth = ScreenMathUtils.dpToPx(120, FlipkartApplication.getAppContext());
        timerImageWidth = ScreenMathUtils.dpToPx(15, FlipkartApplication.getAppContext());
        moreViewSize = 13;
        titleSize = 18;
        timerTextSize = 15;
        countTimer = new Timer();
        handler = null;
        timeRemaining = 0L;
        isLarge = flag;
        context = context1;
        titleString = s;
        action = action1;
        theme = widgettheme;
        activity = activity1;
        drawable = drawable1;
        buildView();
    }

    private void buildView()
    {
label0:
        {
label1:
            {
                if ((titleValue == null || StringUtils.isNullOrEmpty(titleValue.getText())) && StringUtils.isNullOrEmpty(titleString))
                {
                    break label0;
                }
                initializeViewParams();
                ViewInformation viewinformation;
                Action action1;
                ViewInformation viewinformation1;
                ViewInformation viewinformation2;
                int i;
                int j;
                int k;
                String s;
                int l;
                int i1;
                String s1;
                int j1;
                int k1;
                long l1;
                long l2;
                String s2;
                View view;
                int i2;
                if (imageValue != null)
                {
                    viewinformation = getIconView(ImageUtils.getImageUrl(imageValue.getImage()));
                } else
                if (drawable != null)
                {
                    ViewInformation viewinformation3 = getIconView(drawable);
                    FkLogger.debug("Title", (new StringBuilder("left view is ")).append(viewinformation3).toString());
                    viewinformation = viewinformation3;
                } else
                {
                    viewinformation = null;
                }
                action1 = action;
                viewinformation1 = null;
                if (action1 == null)
                {
                    break label1;
                }
                s2 = action.getScreenType();
                if (!StringUtils.isNullOrEmpty(s2))
                {
                    boolean flag = s2.equalsIgnoreCase("infiniteScroll");
                    viewinformation1 = null;
                    if (flag)
                    {
                        break label1;
                    }
                }
                view = getTextView(moreViewWidth, "MORE", 0, 0, moreViewSize);
                view.setId(MORE_VIEW_ID);
                view.setBackgroundDrawable(context.getResources().getDrawable(0x7f020121));
                view.setContentDescription("MoreView");
                if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() > 10)
                {
                    view.setAlpha(0.9F);
                }
                ((TextView)view).setTextColor(-1);
                i2 = ScreenMathUtils.dpToPx(5, context);
                view.setPadding(ScreenMathUtils.dpToPx(13, context), i2, ScreenMathUtils.dpToPx(10, context), i2);
                viewinformation1 = new ViewInformation(view, moreViewWidth);
            }
            if (timerValue != null)
            {
                timeRemaining = timerValue.getTimeRemaining();
                if (timeRemaining != 0L)
                {
                    timeRemaining = timeRemaining / 1000L;
                }
                l1 = timerValue.getCurrentSystemTime();
                if (l1 != 0L)
                {
                    l2 = (System.currentTimeMillis() - l1) / 1000L;
                } else
                {
                    l2 = 0L;
                }
                timeRemaining = timeRemaining - l2;
                handler = new Handler();
                viewinformation2 = getTimerView();
            } else
            {
                viewinformation2 = viewinformation1;
            }
            if (viewinformation != null)
            {
                titleLayoutWidth = titleLayoutWidth - viewinformation.getWidth();
                k1 = ScreenMathUtils.dpToPx(10, context);
                addView(viewinformation.getView());
                i = k1;
            } else
            {
                i = 0;
            }
            if (viewinformation2 != null)
            {
                titleLayoutWidth = titleLayoutWidth - viewinformation2.getWidth();
                j = ScreenMathUtils.dpToPx(10, context);
            } else
            {
                j = 0;
            }
            if (titleValue == null)
            {
                i1 = titleLayoutWidth;
                s1 = titleString;
                j1 = titleSize;
                addView(getTextView(i1, s1, i, j, j1));
            } else
            {
                k = titleLayoutWidth;
                s = titleValue.getText();
                l = titleSize;
                addView(getTextView(k, s, i, j, l));
            }
            if (viewinformation2 != null)
            {
                addView(viewinformation2.getView());
            }
        }
    }

    private ViewInformation getIconView(Drawable drawable1)
    {
        if (drawable1 != null)
        {
            ImageView imageview = new ImageView(context);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(iconWidth, -2);
            layoutparams.gravity = 16;
            imageview.setLayoutParams(layoutparams);
            imageview.setImageDrawable(drawable1);
            return new ViewInformation(imageview, iconWidth);
        } else
        {
            return null;
        }
    }

    private ViewInformation getIconView(String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            NetworkImageView networkimageview = new NetworkImageView(context);
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(iconWidth, -2);
            layoutparams.gravity = 16;
            networkimageview.setLayoutParams(layoutparams);
            networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
            return new ViewInformation(networkimageview, iconWidth);
        } else
        {
            return null;
        }
    }

    private View getTextView(int i, String s, int j, int k, int l)
    {
        CustomRobotoRegularTextView customrobotoregulartextview = new CustomRobotoRegularTextView(context);
        customrobotoregulartextview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(i, -2));
        customrobotoregulartextview.setPadding(j, 0, k, 0);
        customrobotoregulartextview.setTextSize(l);
        customrobotoregulartextview.setText(s);
        customrobotoregulartextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        customrobotoregulartextview.setTextColor(textColor);
        customrobotoregulartextview.setContentDescription("Title_");
        customrobotoregulartextview.setSingleLine(true);
        return customrobotoregulartextview;
    }

    private ViewInformation getTimerView()
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setOrientation(0);
        linearlayout.setGravity(16);
        ImageView imageview = new ImageView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(timerImageWidth, -2);
        layoutparams.setMargins(0, 0, ScreenMathUtils.dpToPx(4, context), 0);
        imageview.setLayoutParams(layoutparams);
        imageview.setImageDrawable(timerDrawable);
        linearlayout.addView(imageview);
        if (timeRemaining < 0L)
        {
            timeRemaining = 0L;
        }
        String s = TimerUtils.getTimerAsText(timeRemaining);
        TextView textview = (TextView)getTextView(timerViewWidth - timerImageWidth, s, 0, 0, timerTextSize);
        linearlayout.addView(textview);
        if (timeRemaining > 0L)
        {
            updateTimer = new UpdateTimer(textview);
            countTimer.scheduleAtFixedRate(updateTimer, 1000L, 1000L);
        }
        return new ViewInformation(linearlayout, timerViewWidth);
    }

    private void initializeViewParams()
    {
        int i = ScreenMathUtils.getScreenWidth();
        int j = ScreenMathUtils.dpToPx(10, context);
        if (isLarge)
        {
            setPadding(j, (int)(1.5F * (float)j), j, (int)(1.5F * (float)j));
        } else
        {
            setPadding(j, j, j, j);
        }
        setOrientation(0);
        screenDpi = ScreenMathUtils.getScreenDpi(context);
        titleLayoutWidth = i - j * 2;
        if (theme != null && theme.equals(BaseWidget.WidgetTheme.dark))
        {
            textColor = context.getResources().getColor(0x7f090070);
            buttonColor = 0xaa171717;
            timerDrawable = getResources().getDrawable(0x7f020174);
            return;
        } else
        {
            textColor = context.getResources().getColor(0x7f090006);
            buttonColor = 0xaabbbbbb;
            Drawable drawable1 = getResources().getDrawable(0x7f020174).getConstantState().newDrawable().mutate();
            drawable1.setColorFilter(getResources().getColor(0x7f090006), android.graphics.PorterDuff.Mode.SRC_ATOP);
            timerDrawable = drawable1;
            return;
        }
    }

    public String getTitleString()
    {
        return titleString;
    }

    public void refreshView()
    {
        if (activity != null && (activity instanceof HomeFragmentHolderActivity))
        {
            ((HomeFragmentHolderActivity)activity).refreshPage();
        }
    }

    public void setTitleString(String s)
    {
        titleString = s;
    }

    public void updateView(TitleValue titlevalue, ImageValue imagevalue, TimerValue timervalue, BaseWidget.WidgetTheme widgettheme, Action action1, Activity activity1)
    {
        titleValue = titlevalue;
        timerValue = timervalue;
        imageValue = imagevalue;
        action = action1;
        theme = widgettheme;
        activity = activity1;
        removeAllViews();
        buildView();
    }

    public void updateView(String s, Drawable drawable1, BaseWidget.WidgetTheme widgettheme, Action action1, boolean flag, Activity activity1)
    {
        titleString = s;
        action = action1;
        theme = widgettheme;
        activity = activity1;
        drawable = drawable1;
        isLarge = flag;
        removeAllViews();
        buildView();
    }





/*
    static long access$102(NewTitleWidget newtitlewidget, long l)
    {
        newtitlewidget.timeRemaining = l;
        return l;
    }

*/

    private class ViewInformation
    {

        final NewTitleWidget this$0;
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
            this$0 = NewTitleWidget.this;
            super();
            view = view1;
            width = i;
        }
    }


    private class UpdateTimer extends TimerTask
    {

        final NewTitleWidget this$0;
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
            this$0 = NewTitleWidget.this;
            super();
            timerView = textview;
        }
    }

}
