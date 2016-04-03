// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datahandler.param.BrowseParam;
import com.flipkart.android.utils.PinCodeWidgetState;
import com.flipkart.android.utils.ScreenMathUtils;

// Referenced classes of package com.flipkart.android.customviews:
//            E

public class PinCodeViewWidget extends LinearLayout
{

    private static LinearLayout a;
    private static LinearLayout b;
    private android.view.View.OnClickListener c;
    private PinCodeWidgetState d;
    private Context e;
    private int f;
    public LayoutInflater inflater;

    public PinCodeViewWidget(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        d = PinCodeWidgetState.None;
        f = -1;
        e = context;
        inflater = ((Activity)context).getLayoutInflater();
        b = this;
    }

    private void a()
    {
        b.removeAllViews();
        if (d == PinCodeWidgetState.EnterPin)
        {
            a = (LinearLayout)inflater.inflate(0x7f03006c, null);
        } else
        if (d == PinCodeWidgetState.AvailableAtPin)
        {
            a = (LinearLayout)inflater.inflate(0x7f030068, null);
        } else
        {
            if (d != PinCodeWidgetState.NotFoundShowAll)
            {
                continue;
            }
            a = (LinearLayout)inflater.inflate(0x7f03006b, null);
        }
        do
        {
            if (f == -1 && f == -1)
            {
                f = getResources().getDisplayMetrics().widthPixels;
            }
            a.setLayoutParams(new android.widget.LinearLayout.LayoutParams(f, ScreenMathUtils.dpToPx(70, e)));
            a.setClickable(true);
            b.addView(a);
            b.setVisibility(0);
            do
            {
                return;
            } while (d != PinCodeWidgetState.NotFound);
            a = (LinearLayout)inflater.inflate(0x7f030069, null);
        } while (true);
    }

    public PinCodeWidgetState getState()
    {
        return d;
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
    }

    public void setOnClickOnViews(android.view.View.OnClickListener onclicklistener)
    {
        c = onclicklistener;
    }

    public void setState(PinCodeWidgetState pincodewidgetstate, BrowseParam browseparam)
    {
        d = pincodewidgetstate;
        String s = FlipkartPreferenceManager.instance().getUserPinCode();
        if (pincodewidgetstate == PinCodeWidgetState.EnterPin)
        {
            a();
            ((ImageView)a.findViewById(0x7f0a0145)).setOnClickListener(c);
            Button button = (Button)a.findViewById(0x7f0a0149);
            button.setOnClickListener(c);
            EditText edittext = (EditText)a.findViewById(0x7f0a0148);
            if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() >= 11)
            {
                edittext.setTextColor(Color.parseColor("#ffffff"));
            }
            edittext.setOnEditorActionListener(new E(this, button));
        } else
        {
            if (pincodewidgetstate == PinCodeWidgetState.AvailableAtPin)
            {
                a();
                ((ImageView)a.findViewById(0x7f0a0145)).setOnClickListener(c);
                TextView textview2 = (TextView)a.findViewById(0x7f0a0143);
                ((ImageView)a.findViewById(0x7f0a0144)).setOnClickListener(c);
                textview2.setText((new StringBuilder()).append(textview2.getText()).append(" ").append(s).toString());
                textview2.setOnClickListener(c);
                return;
            }
            if (pincodewidgetstate == PinCodeWidgetState.NotFound)
            {
                a();
                TrackingHelper.sendNoSellerAvailableForPinCode();
                ImageView imageview1 = (ImageView)a.findViewById(0x7f0a0144);
                TextView textview1 = (TextView)a.findViewById(0x7f0a0146);
                textview1.setText((new StringBuilder()).append(textview1.getText()).append(" ").append(s).toString());
                imageview1.setOnClickListener(c);
                return;
            }
            if (pincodewidgetstate == PinCodeWidgetState.NotFoundShowAll)
            {
                a();
                ((ImageView)a.findViewById(0x7f0a0145)).setOnClickListener(c);
                ImageView imageview = (ImageView)a.findViewById(0x7f0a0144);
                TextView textview = (TextView)a.findViewById(0x7f0a0146);
                textview.setText((new StringBuilder()).append(textview.getText()).append(" ").append(s).toString());
                imageview.setOnClickListener(c);
                return;
            }
        }
    }
}
