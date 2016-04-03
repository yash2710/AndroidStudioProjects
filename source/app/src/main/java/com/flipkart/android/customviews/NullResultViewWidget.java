// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.customwidget.RecommendationsWidget;
import com.flipkart.android.utils.NullResultWidgetState;
import com.flipkart.android.utils.ScreenMathUtils;

public class NullResultViewWidget extends LinearLayout
{

    private static LinearLayout a;
    private static LinearLayout b;
    private android.view.View.OnClickListener c;
    private NullResultWidgetState d;
    private Activity e;
    private Context f;
    public LayoutInflater inflater;

    public NullResultViewWidget(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        d = NullResultWidgetState.None;
        f = context;
        e = (Activity)context;
        inflater = ((Activity)context).getLayoutInflater();
        b = this;
        setOrientation(1);
    }

    private void a(String s, Activity activity)
    {
        RecommendationsWidget recommendationswidget;
        RecommendationsWidget recommendationswidget1;
        try
        {
            recommendationswidget = (RecommendationsWidget)b.findViewById(RecommendationsWidget.Id);
        }
        catch (Exception exception)
        {
            return;
        }
        if (recommendationswidget == null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        recommendationswidget.removeAllViews();
        b.removeView(recommendationswidget);
        recommendationswidget1 = new RecommendationsWidget(activity.getApplicationContext(), null, c, activity, s);
        recommendationswidget1.setId(RecommendationsWidget.Id);
        recommendationswidget1.downloadRecoData();
        b.addView(recommendationswidget1);
        return;
    }

    private void a(boolean flag)
    {
        b.removeAllViews();
        if (d == NullResultWidgetState.ShowContinueShopping)
        {
            a = (LinearLayout)inflater.inflate(0x7f030061, null);
        } else
        if (d == NullResultWidgetState.ShowWrongQuery)
        {
            LinearLayout linearlayout1 = (LinearLayout)inflater.inflate(0x7f030065, null);
            a = linearlayout1;
            TextView textview1 = (TextView)linearlayout1.findViewById(0x7f0a0140);
            Drawable drawable1 = getResources().getDrawable(0x7f02014f).getConstantState().newDrawable().mutate();
            drawable1.setColorFilter(getResources().getColor(0x7f090006), android.graphics.PorterDuff.Mode.SRC_ATOP);
            textview1.setCompoundDrawablesWithIntrinsicBounds(drawable1, null, null, null);
        } else
        {
            if (d != NullResultWidgetState.ShowLargeError)
            {
                continue;
            }
            a = (LinearLayout)inflater.inflate(0x7f030062, null);
        }
        do
        {
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
            LinearLayout linearlayout;
            TextView textview;
            Drawable drawable;
            int i;
            if (flag)
            {
                i = ScreenMathUtils.dpToPx(10, f);
            } else
            {
                i = 0;
            }
            layoutparams.setMargins(i, i, i, i);
            a.setLayoutParams(layoutparams);
            b.addView(a);
            b.setVisibility(0);
            do
            {
                return;
            } while (d != NullResultWidgetState.ShowBarCodeError);
            linearlayout = (LinearLayout)inflater.inflate(0x7f030065, null);
            a = linearlayout;
            textview = (TextView)linearlayout.findViewById(0x7f0a0140);
            drawable = getResources().getDrawable(0x7f02014f).getConstantState().newDrawable().mutate();
            drawable.setColorFilter(getResources().getColor(0x7f090006), android.graphics.PorterDuff.Mode.SRC_ATOP);
            textview.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            a.setPadding(0, ScreenMathUtils.dpToPx(60, f), 0, 0);
        } while (true);
    }

    public NullResultWidgetState getState()
    {
        return d;
    }

    public void setOnClickOnViews(android.view.View.OnClickListener onclicklistener)
    {
        c = onclicklistener;
    }

    public void setState(NullResultWidgetState nullresultwidgetstate, String s, String s1)
    {
        d = nullresultwidgetstate;
        if (nullresultwidgetstate == NullResultWidgetState.ShowContinueShopping)
        {
            a(true);
            Button button = (Button)a.findViewById(0x7f0a0139);
            ((TextView)a.findViewById(0x7f0a0138)).setText(s);
            button.setOnClickListener(c);
        } else
        {
            if (nullresultwidgetstate == NullResultWidgetState.ShowWrongQuery)
            {
                a(false);
                LinearLayout linearlayout = (LinearLayout)a.findViewById(0x7f0a013f);
                TextView textview = (TextView)a.findViewById(0x7f0a013c);
                textview.setText((new StringBuilder()).append(textview.getText().toString()).append("\"").append(s).append("\"\n").toString());
                linearlayout.setOnClickListener(c);
                Context _tmp = f;
                android.view.View.OnClickListener _tmp1 = c;
                a(s1, e);
                return;
            }
            if (nullresultwidgetstate == NullResultWidgetState.ShowLargeError)
            {
                a(true);
                ((Button)a.findViewById(0x7f0a013a)).setOnClickListener(c);
                return;
            }
            if (nullresultwidgetstate == NullResultWidgetState.ShowBarCodeError)
            {
                a(false);
                ((TextView)a.findViewById(0x7f0a013c)).setText("We couldn't find anything for product scanned\"\n");
                ((TextView)a.findViewById(0x7f0a013d)).setText("Please type in what you are looking for");
                ((TextView)a.findViewById(0x7f0a013e)).setVisibility(8);
                ((LinearLayout)a.findViewById(0x7f0a013f)).setOnClickListener(c);
                Context _tmp2 = f;
                android.view.View.OnClickListener _tmp3 = c;
                a(s1, e);
                return;
            }
        }
    }
}
