// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import com.flipkart.android.customviews.CircleLayout;
import com.flipkart.android.customviews.CustomRobotoCondensedBoldTextView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.BalloonNavigationValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.drawable.GradientDrawableUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class BalloonWidget extends BaseWidget
{

    private static int SHOW_IN_TIME = 0;
    public static final String WIDGET_COMMON_NAME = "BALLOON_NAVIGATION";
    private static int buttonTextColor = 0;
    private Handler handler;
    int numOfLayers;
    private String requestId;

    public BalloonWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        numOfLayers = 0;
        requestId = s;
        handler = new Handler();
        ArrayList arraylist1;
        ArrayList arraylist2;
        if (theme != null && theme.equals(BaseWidget.WidgetTheme.dark))
        {
            buttonTextColor = -1;
        } else
        {
            buttonTextColor = 0xff000000;
        }
        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        if (arraylist != null && arraylist.size() > 0)
        {
            for (int j = 0; j < arraylist.size(); j++)
            {
                WidgetItem widgetitem1 = (WidgetItem)arraylist.get(j);
                if (widgetitem1 == null)
                {
                    continue;
                }
                BalloonNavigationValue balloonnavigationvalue = (BalloonNavigationValue)widgetitem1.getValue();
                Action action = widgetitem1.getAction();
                if (balloonnavigationvalue == null || action == null)
                {
                    continue;
                }
                String s1 = balloonnavigationvalue.getText();
                if (!StringUtils.isNullOrEmpty(s1))
                {
                    arraylist1.add(s1);
                    arraylist2.add(action);
                }
            }

        }
        if (arraylist1.size() > 0)
        {
            setOrientation(1);
            buildView(arraylist1, arraylist2, (ScreenMathUtils.getScreenWidth() - 4 * ScreenMathUtils.dpToPx(10, context)) / 3);
            setPadding(getPaddingLeft(), getPaddingTop() + ScreenMathUtils.dpToPx(10, context), getPaddingRight(), getPaddingBottom());
        }
    }

    private void animatedIfAllowed(final View ballon, int i, int j)
    {
        if (FlipkartApplication.isShowBallonAnimation())
        {
            ballon.setVisibility(4);
            handler.postDelayed(new _cls1(), i * SHOW_IN_TIME + j * 3 * SHOW_IN_TIME);
        }
    }

    private void buildView(ArrayList arraylist, ArrayList arraylist1, int i)
    {
        int j = 0;
        while (j < arraylist.size()) 
        {
            LinearLayout linearlayout = new LinearLayout(context);
            linearlayout.setPadding(ScreenMathUtils.dpToPx(10, context), 0, 0, ScreenMathUtils.dpToPx(10, context));
            linearlayout.setOrientation(0);
            linearlayout.setGravity(1);
            int k = ScreenMathUtils.dpToPx(10, context);
            try
            {
                View view = getButton((String)arraylist.get(j), (Action)arraylist1.get(j), i, k);
                linearlayout.addView(view);
                animatedIfAllowed(view, 1, numOfLayers);
                View view1 = getButton((String)arraylist.get(j + 1), (Action)arraylist1.get(j + 1), i, k);
                linearlayout.addView(view1);
                animatedIfAllowed(view1, 2, numOfLayers);
                View view2 = getButton((String)arraylist.get(j + 2), (Action)arraylist1.get(j + 2), i, k);
                linearlayout.addView(view2);
                animatedIfAllowed(view2, 3, numOfLayers);
            }
            catch (Exception exception) { }
            addView(linearlayout);
            numOfLayers = 1 + numOfLayers;
            j += 3;
        }
    }

    private View getButton(String s, Action action, int i, int j)
    {
        CircleLayout circlelayout = new CircleLayout(context);
        circlelayout.setBackgroundDrawable(GradientDrawableUtils.getRoundedDrawable(0xfff1c05c, 0x66000000, context));
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(i, i);
        layoutparams.setMargins(0, 0, j, 0);
        circlelayout.setLayoutParams(layoutparams);
        circlelayout.setTag(action);
        int k = ScreenMathUtils.dpToPx(3, context);
        circlelayout.setPadding(k, 0, k, 0);
        circlelayout.setOnClickListener(onClickListener);
        CustomRobotoCondensedBoldTextView customrobotocondensedboldtextview = new CustomRobotoCondensedBoldTextView(context);
        customrobotocondensedboldtextview.setText(s.replaceFirst(" ", "\n"));
        customrobotocondensedboldtextview.setMaxLines(2);
        customrobotocondensedboldtextview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        customrobotocondensedboldtextview.setGravity(17);
        customrobotocondensedboldtextview.setTextColor(buttonTextColor);
        customrobotocondensedboldtextview.setTextSize(18F);
        circlelayout.addView(customrobotocondensedboldtextview);
        return circlelayout;
    }

    static 
    {
        SHOW_IN_TIME = 300;
    }

    private class _cls1
        implements Runnable
    {

        final BalloonWidget this$0;
        final View val$ballon;

        public void run()
        {
            ballon.setVisibility(0);
            FkAnimationUtils.performScaleYAnimation(context, ballon);
        }

        _cls1()
        {
            this$0 = BalloonWidget.this;
            ballon = view;
            super();
        }
    }

}
