// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.customviews.CustomRobotoCondensedTextView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.CaptionedImageValue;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class MetroTileWidget extends BaseWidget
{

    public static final String WIDGET_COMMON_NAME = "METRO_TILE3";
    private String requestId;
    private int screenDpi;
    private int widgetHeight;
    private int widgetWidth;

    public MetroTileWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, String s, 
            Activity activity, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        widgetHeight = 0;
        widgetWidth = 0;
        screenDpi = 320;
        requestId = s;
        if (arraylist != null && arraylist.size() > 0)
        {
            setParentLayoutParams();
            calculateWidggetHeight();
            screenDpi = ScreenMathUtils.getScreenDpi(context);
            ArrayList arraylist1 = new ArrayList();
            ArrayList arraylist2 = new ArrayList();
            ArrayList arraylist3 = new ArrayList();
            boolean flag = false;
            int ai[] = new int[4];
            int ai1[];
            boolean flag1;
            int j;
            if (layoutdetails != null)
            {
                ai1 = layoutdetails.getPaddingInInt();
            } else
            {
                ai1 = ai;
            }
            flag1 = true;
            j = 0;
            while (j < arraylist.size()) 
            {
                WidgetItem widgetitem1 = (WidgetItem)arraylist.get(j);
                if (widgetitem1 != null)
                {
                    CaptionedImageValue captionedimagevalue = (CaptionedImageValue)widgetitem1.getValue();
                    Action action = widgetitem1.getAction();
                    if (captionedimagevalue != null && action != null)
                    {
                        ImageValue imagevalue = captionedimagevalue.getImage();
                        com.flipkart.android.response.component.data.customvalues.Image image = null;
                        if (imagevalue != null)
                        {
                            image = imagevalue.getImage();
                        }
                        String s1 = captionedimagevalue.getCaption();
                        if (image != null && !StringUtils.isNullOrEmpty(s1))
                        {
                            String s2 = ImageUtils.getImageUrl(image);
                            if (!StringUtils.isNullOrEmpty(s2))
                            {
                                arraylist1.add(s2);
                                arraylist2.add(s1);
                                arraylist3.add(action);
                            }
                        }
                    }
                }
                boolean flag2;
                boolean flag3;
                if (arraylist1.size() == 3)
                {
                    LinearLayout linearlayout = getLinearLayout(0, -1, widgetHeight, ScreenMathUtils.dpToPx(ai1[0], context), 0, ScreenMathUtils.dpToPx(ai1[2], context), ScreenMathUtils.dpToPx(10, context));
                    int k;
                    if (flag1)
                    {
                        RelativeLayout relativelayout = getCompleteView((int)(0.66000000000000003D * (double)widgetWidth), widgetHeight, (String)arraylist1.get(0), (String)arraylist2.get(0), (Action)arraylist3.get(0), FlipkartApplication.getImageLoader());
                        linearlayout.addView(relativelayout);
                        ((android.widget.LinearLayout.LayoutParams)relativelayout.getLayoutParams()).rightMargin = ScreenMathUtils.dpToPx(10, context);
                        updateList(arraylist1, arraylist2, arraylist3);
                        linearlayout.addView(getTwoImageView(arraylist1, arraylist2, arraylist3, FlipkartApplication.getImageLoader()));
                        flag2 = false;
                    } else
                    {
                        LinearLayout linearlayout1 = getTwoImageView(arraylist1, arraylist2, arraylist3, FlipkartApplication.getImageLoader());
                        linearlayout.addView(linearlayout1);
                        ((android.widget.LinearLayout.LayoutParams)linearlayout1.getLayoutParams()).rightMargin = ScreenMathUtils.dpToPx(10, context);
                        linearlayout.addView(getCompleteView((int)(0.66000000000000003D * (double)widgetWidth), widgetHeight, (String)arraylist1.get(0), (String)arraylist2.get(0), (Action)arraylist3.get(0), FlipkartApplication.getImageLoader()));
                        updateList(arraylist1, arraylist2, arraylist3);
                        flag2 = true;
                    }
                    addView(linearlayout);
                    flag3 = true;
                } else
                {
                    flag2 = flag1;
                    flag3 = flag;
                }
                k = j + 1;
                flag1 = flag2;
                j = k;
                flag = flag3;
            }
            if (!flag)
            {
                setVisibility(8);
                setTitleGone();
            }
            return;
        } else
        {
            setVisibility(8);
            setTitleGone();
            return;
        }
    }

    private void addRequestIdToActionParamsExplicitly(Action action)
    {
        action.getParams().put("REQUEST_ID", requestId);
    }

    private void calculateWidggetHeight()
    {
        int i = getResources().getDisplayMetrics().widthPixels;
        screenDpi = ScreenMathUtils.getScreenDpi(context);
        widgetWidth = i - ScreenMathUtils.dpToPx(28, context);
        widgetHeight = (int)(0.65000000000000002D * (double)widgetWidth);
    }

    private RelativeLayout getCompleteView(int i, int j, String s, String s1, Action action, ImageLoader imageloader)
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        relativelayout.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(i, j));
        relativelayout.setBackgroundResource(0x7f0200f6);
        relativelayout.addView(getImageView(s, imageloader));
        relativelayout.addView(getTextView(s1));
        setHeading(action, s1);
        addRequestIdToActionParamsExplicitly(action);
        relativelayout.setTag(action);
        relativelayout.setOnClickListener(onClickListener);
        return relativelayout;
    }

    private NetworkImageView getImageView(String s, ImageLoader imageloader)
    {
        NetworkImageView networkimageview = new NetworkImageView(context);
        networkimageview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        networkimageview.setImageUrl(s, imageloader);
        return networkimageview;
    }

    private LinearLayout getLinearLayout(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        LinearLayout linearlayout = new LinearLayout(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(j, k);
        layoutparams.setMargins(l, i1, j1, k1);
        linearlayout.setLayoutParams(layoutparams);
        linearlayout.setOrientation(i);
        return linearlayout;
    }

    private TextView getTextView(String s)
    {
        CustomRobotoCondensedTextView customrobotocondensedtextview = new CustomRobotoCondensedTextView(context, null);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)(0.14000000000000001D * (double)widgetHeight));
        layoutparams.addRule(12);
        customrobotocondensedtextview.setLayoutParams(layoutparams);
        customrobotocondensedtextview.setText(s);
        customrobotocondensedtextview.setGravity(16);
        customrobotocondensedtextview.setTextColor(Color.parseColor(getResources().getString(0x7f090070)));
        customrobotocondensedtextview.setBackgroundDrawable(new ColorDrawable(0xcc000000));
        customrobotocondensedtextview.setPadding(ScreenMathUtils.dpToPx(5, context), 0, 0, 0);
        customrobotocondensedtextview.setTextSize(16F);
        return customrobotocondensedtextview;
    }

    private LinearLayout getTwoImageView(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, ImageLoader imageloader)
    {
        LinearLayout linearlayout = getLinearLayout(1, (int)(0.34000000000000002D * (double)widgetWidth), widgetHeight, 0, 0, 0, 0);
        int i = widgetHeight - ScreenMathUtils.dpToPx(3, context);
        linearlayout.addView(getCompleteView(-1, (int)(0.5D * (double)i), (String)arraylist.get(0), (String)arraylist1.get(0), (Action)arraylist2.get(0), imageloader));
        arraylist.remove(0);
        arraylist1.remove(0);
        arraylist2.remove(0);
        linearlayout.addView(getView());
        linearlayout.addView(getCompleteView(-1, (int)(0.5D * (double)i), (String)arraylist.get(0), (String)arraylist1.get(0), (Action)arraylist2.get(0), imageloader));
        arraylist.remove(0);
        arraylist1.remove(0);
        arraylist2.remove(0);
        return linearlayout;
    }

    private View getView()
    {
        View view = new View(context);
        view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(3, context)));
        return view;
    }

    private void setHeading(Action action, String s)
    {
        if (action != null && !StringUtils.isNullOrEmpty(s))
        {
            Map map = action.getParams();
            if (map != null)
            {
                map.put("heading", s);
                action.setParams(map);
            }
        }
    }

    private void setParentLayoutParams()
    {
        setOrientation(1);
    }

    private void updateList(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2)
    {
        arraylist.remove(0);
        arraylist1.remove(0);
        arraylist2.remove(0);
    }
}
