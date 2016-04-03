// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.customviews.CustomRobotoLightTextView;
import com.flipkart.android.customviews.CustomRobotoMediumTextView;
import com.flipkart.android.response.component.WidgetItem;
import com.flipkart.android.response.customwidgetitem.InstallCareWidgetItem;
import com.flipkart.android.response.customwidgetitemvalue.InstallCareWidgetItemValue;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            ITimeBound

public class InstallCareWidget extends LinearLayout
    implements ITimeBound
{

    public static int Id = 0;
    public static final String WIDGET_COMMON_NAME = "InstallCareWidget";
    private Context context;
    android.view.View.OnClickListener listner;
    private long timeStamp;

    public InstallCareWidget(Context context1)
    {
        super(context1);
        timeStamp = -1L;
        listner = null;
    }

    public InstallCareWidget(Context context1, ArrayList arraylist, android.view.View.OnClickListener onclicklistener, long l)
    {
        super(context1);
        timeStamp = -1L;
        listner = null;
        timeStamp = l;
        context = context1;
        listner = onclicklistener;
        setId(Id);
        setOrientation(1);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.setMargins(0, ScreenMathUtils.dpToPx(10, context1), 0, 0);
        setLayoutParams(layoutparams);
        if (arraylist != null && arraylist.size() > 0)
        {
            int i = 0;
            while (i < arraylist.size()) 
            {
                WidgetItem widgetitem = (WidgetItem)arraylist.get(i);
                if (widgetitem == null)
                {
                    continue;
                }
                InstallCareWidgetItem installcarewidgetitem = (InstallCareWidgetItem)widgetitem.getGenericWidgetItem();
                if (installcarewidgetitem != null)
                {
                    if (i < -1 + arraylist.size())
                    {
                        buildLayouts(installcarewidgetitem, true);
                    } else
                    {
                        buildLayouts(installcarewidgetitem, false);
                    }
                }
                i++;
            }
        }
    }

    public InstallCareWidget(Context context1, ArrayList arraylist, android.view.View.OnClickListener onclicklistener, long l, PageTypeUtils pagetypeutils)
    {
        super(context1);
        timeStamp = -1L;
        listner = null;
        timeStamp = l;
        context = context1;
        listner = onclicklistener;
        setId(Id);
        setOrientation(1);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        if (arraylist != null && arraylist.size() > 0)
        {
            int i = 0;
            while (i < arraylist.size()) 
            {
                InstallCareWidgetItem installcarewidgetitem = (InstallCareWidgetItem)arraylist.get(i);
                if (installcarewidgetitem != null)
                {
                    if (i < -1 + arraylist.size())
                    {
                        buildLayouts(installcarewidgetitem, true);
                        addView(getBorder(this, 100));
                    } else
                    {
                        buildLayouts(installcarewidgetitem, false);
                    }
                }
                i++;
            }
        }
    }

    private void buildLayouts(InstallCareWidgetItem installcarewidgetitem, boolean flag)
    {
        String s = "";
        String s1 = "";
        InstallCareWidgetItemValue installcarewidgetitemvalue = installcarewidgetitem.getValue();
        if (installcarewidgetitemvalue != null)
        {
            s = installcarewidgetitemvalue.getTitle();
            s1 = installcarewidgetitemvalue.getSubTitle();
        }
        com.flipkart.android.response.customwidgetitemvalue.Action action = installcarewidgetitem.getAction();
        LinearLayout linearlayout = new LinearLayout(context);
        if (flag)
        {
            android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
            layoutparams.setMargins(0, 0, 0, ScreenMathUtils.dpToPx(1, context));
            linearlayout.setLayoutParams(layoutparams);
        }
        int i = ScreenMathUtils.dpToPx(10, context);
        linearlayout.setPadding(i, i, i, i);
        linearlayout.setOrientation(0);
        LinearLayout linearlayout1 = new LinearLayout(context);
        linearlayout1.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -2, 15F));
        linearlayout1.setOrientation(1);
        linearlayout1.setPadding(0, 0, i, 0);
        linearlayout1.addView(getTextView(s, 18, true, null));
        linearlayout1.addView(getTextView(s1, 14, false, getResources().getString(0x7f090058)));
        linearlayout.addView(linearlayout1);
        if (action != null)
        {
            linearlayout.addView(getImageView());
            linearlayout.setTag(action);
            linearlayout.setOnClickListener(listner);
        }
        addView(linearlayout);
    }

    private static View getBorder(View view, int i)
    {
        View view1 = new View(view.getContext());
        view1.setBackgroundDrawable(view.getContext().getResources().getDrawable(0x7f020158));
        view1.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(2, view.getContext())));
        view1.setId(i);
        return view1;
    }

    private ImageView getImageView()
    {
        ImageView imageview = new ImageView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-2, -2, 1.0F);
        layoutparams.gravity = 21;
        imageview.setLayoutParams(layoutparams);
        imageview.setImageResource(0x7f02005d);
        return imageview;
    }

    private TextView getTextView(String s, int i, boolean flag, String s1)
    {
        Object obj;
        if (flag)
        {
            obj = new CustomRobotoMediumTextView(context, null);
        } else
        {
            obj = new CustomRobotoLightTextView(context, null);
        }
        ((TextView) (obj)).setTextSize(2, i);
        ((TextView) (obj)).setText(s);
        if (s1 == null)
        {
            ((TextView) (obj)).setTextColor(getResources().getColor(0x7f090006));
            return ((TextView) (obj));
        } else
        {
            ((TextView) (obj)).setTextColor(Color.parseColor(s1));
            return ((TextView) (obj));
        }
    }

    public long getTimeStamp()
    {
        return timeStamp;
    }

    public void setTimeStamp(long l)
    {
        timeStamp = l;
    }

    static 
    {
        Id = 1000;
    }
}
