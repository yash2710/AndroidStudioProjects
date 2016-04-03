// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.telephony.TelephonyManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoRegularTextView;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.ButtonValue;
import com.flipkart.android.response.component.data.customvalues.CallUsValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.drawable.GradientDrawableUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class CallUsWidget extends BaseWidget
{

    public static int CallUsSubTitleId = 0;
    public static int CallUsTitleId = 0;
    public static int Id = 0;
    public static final String WIDGET_COMMON_NAME = "CALL_US";
    private android.view.View.OnClickListener listner;
    private int textColor;

    public CallUsWidget(Context context, WidgetItem widgetitem, android.view.View.OnClickListener onclicklistener, Activity activity, PageTypeUtils pagetypeutils)
    {
        super(context, null, null, onclicklistener, null, activity, -1);
        textColor = -1;
        setId(Id);
        this.context = context;
        listner = onclicklistener;
        if (widgetitem != null)
        {
            buildView(widgetitem);
        }
    }

    public CallUsWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, Activity activity, 
            int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        textColor = -1;
        setId(Id);
        FkLogger.debug("CALL_US", "into call us");
        if (arraylist != null && arraylist.size() > 0)
        {
            WidgetItem widgetitem1 = (WidgetItem)arraylist.get(0);
            if (widgetitem1 != null)
            {
                buildView(widgetitem1);
            }
        }
    }

    private void buildView(WidgetItem widgetitem)
    {
        CallUsValue callusvalue = (CallUsValue)widgetitem.getValue();
        Action action = widgetitem.getAction();
        String s;
        String s1;
        String s2;
        android.widget.LinearLayout.LayoutParams layoutparams;
        int i;
        int j;
        LinearLayout linearlayout;
        TextView textview;
        TextView textview1;
        Exception exception;
        Map map;
        Exception exception1;
        String s3;
        if (callusvalue != null)
        {
            String s4 = callusvalue.getTitle();
            String s5 = callusvalue.getSubTitle();
            s = s4;
            s1 = s5;
        } else
        {
            s = "";
            s1 = "";
        }
        if (theme.equals(BaseWidget.WidgetTheme.dark))
        {
            textColor = -1;
        } else
        {
            textColor = 0xff000000;
        }
        s2 = "";
        if (action == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        map = action.getParams();
        if (map == null)
        {
            break MISSING_BLOCK_LABEL_102;
        }
        s3 = ((String)map.get("number")).trim().replace("\"", "");
        s2 = s3;
_L2:
        layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        i = ScreenMathUtils.dpToPx(10, context);
        layoutparams.setMargins(0, i, 0, i);
        setLayoutParams(layoutparams);
        j = ScreenMathUtils.dpToPx(10, context);
        setPadding(j, j, j, j);
        setOrientation(0);
        linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -2, 7F));
        linearlayout.setOrientation(1);
        linearlayout.setPadding(0, 0, j, 0);
        textview = getTextView(s, 14, false);
        textview.setTextColor(textColor);
        textview.setId(CallUsTitleId);
        linearlayout.addView(textview);
        textview1 = getTextView(s1, 20, true);
        textview1.setTextColor(textColor);
        textview1.setId(CallUsSubTitleId);
        linearlayout.addView(textview1);
        addView(linearlayout);
        try
        {
            if (((TelephonyManager)getContext().getSystemService("phone")).getPhoneType() != 0 && !StringUtils.isNullOrEmpty(s2))
            {
                addView(getButton(callusvalue.getCallButton(), s2));
            }
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Exception exception)
        {
            return;
        }
        exception1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private Button getButton(ButtonValue buttonvalue, final String telNumber)
    {
        Button button = new Button(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, ScreenMathUtils.dpToPx(40, context), 3F);
        layoutparams.gravity = 16;
        button.setLayoutParams(layoutparams);
        button.setBackgroundDrawable(GradientDrawableUtils.getGradintDrawable(getResources().getColor(0x7f090009), -1));
        button.setText(buttonvalue.getTitle());
        button.setGravity(19);
        button.setCompoundDrawablesWithIntrinsicBounds(0x7f020083, 0, 0, 0);
        button.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(7, context));
        button.setPadding(ScreenMathUtils.dpToPx(10, context), 0, ScreenMathUtils.dpToPx(10, context), 0);
        button.setTextSize(18F);
        button.setTextColor(getResources().getColor(0x7f090070));
        button.setOnClickListener(new _cls1());
        return button;
    }

    private TextView getTextView(String s, int i, boolean flag)
    {
        Object obj;
        if (flag)
        {
            obj = new CustomRobotoRegularTextView(context, null);
        } else
        {
            obj = new CustomRobotoLightTextView(context, null);
        }
        ((TextView) (obj)).setText(s);
        ((TextView) (obj)).setTextSize(i);
        ((TextView) (obj)).setTextColor(getResources().getColor(0x7f090006));
        return ((TextView) (obj));
    }

    static 
    {
        Id = 1111;
        CallUsTitleId = 5206;
        CallUsSubTitleId = 17185;
    }

    private class _cls1
        implements android.view.View.OnClickListener
    {

        final CallUsWidget this$0;
        final String val$telNumber;

        public void onClick(View view)
        {
            TrackingHelper.sendCallUsClicked();
            Intent intent = new Intent("android.intent.action.DIAL");
            intent.setData(Uri.parse((new StringBuilder("tel:")).append(telNumber).toString()));
            activity.startActivity(intent);
        }

        _cls1()
        {
            this$0 = CallUsWidget.this;
            telNumber = s;
            super();
        }
    }

}
