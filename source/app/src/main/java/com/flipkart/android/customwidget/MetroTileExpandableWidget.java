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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.CustomRobotoCondensedTextView;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.ExpandableCaptionedImageValue;
import com.flipkart.android.response.component.data.customvalues.ImageValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.ImageUtils;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget, ExpandableSubcategoryWidget, WidgetAction

public class MetroTileExpandableWidget extends BaseWidget
    implements android.view.View.OnClickListener
{

    public static final String WIDGET_COMMON_NAME = "METRO_EXPANDABLE";
    private String requestId;
    private int widgetHeight;
    private int widgetWidth;

    public MetroTileExpandableWidget(Context context, ArrayList arraylist, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, WidgetItem widgetitem, android.view.View.OnClickListener onclicklistener, Activity activity, 
            String s, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        widgetHeight = 0;
        widgetWidth = 0;
        requestId = s;
        layoutDetails = layoutdetails;
        if (arraylist != null && arraylist.size() > 0)
        {
            setParentLayoutParams();
            calculateWidggetHeight();
            screenDpi = ScreenMathUtils.getScreenDpi(context);
            ArrayList arraylist1 = new ArrayList();
            ArrayList arraylist2 = new ArrayList();
            ArrayList arraylist3 = new ArrayList();
            ArrayList arraylist4 = new ArrayList();
            for (int j = 0; j < arraylist.size(); j++)
            {
                WidgetItem widgetitem1 = (WidgetItem)arraylist.get(j);
                if (widgetitem1 == null)
                {
                    continue;
                }
                ExpandableCaptionedImageValue expandablecaptionedimagevalue = (ExpandableCaptionedImageValue)widgetitem1.getValue();
                Action action = widgetitem1.getAction();
                if (expandablecaptionedimagevalue == null || action == null)
                {
                    continue;
                }
                ImageValue imagevalue = expandablecaptionedimagevalue.getImageValue();
                com.flipkart.android.response.component.data.customvalues.Image image = null;
                if (imagevalue != null)
                {
                    image = imagevalue.getImage();
                }
                String s1 = expandablecaptionedimagevalue.getTitle();
                if (image == null || StringUtils.isNullOrEmpty(s1))
                {
                    continue;
                }
                String s2 = ImageUtils.getImageUrl(image);
                if (StringUtils.isNullOrEmpty(s2))
                {
                    continue;
                }
                arraylist1.add(s2);
                arraylist2.add(s1);
                arraylist3.add(action);
                Object obj = expandablecaptionedimagevalue.getItems();
                if (obj == null)
                {
                    obj = new ArrayList();
                }
                FkLogger.debug("Test", "add expandable list");
                arraylist4.add(obj);
            }

            if (arraylist1.size() != 0)
            {
                if (arraylist1.size() % 2 == 0)
                {
                    int l = ScreenMathUtils.dpToPx(10, context);
                    setPadding(l, 0, l, l);
                    while (arraylist1.size() > 0) 
                    {
                        LinearLayout linearlayout = getTwoImageView(arraylist1, arraylist2, arraylist3, arraylist4);
                        addView(linearlayout);
                        ((android.widget.LinearLayout.LayoutParams)linearlayout.getLayoutParams()).bottomMargin = ScreenMathUtils.dpToPx(10, context);
                    }
                } else
                {
                    int k = ScreenMathUtils.dpToPx(10, context);
                    setPadding(k, k, ScreenMathUtils.dpToPx(3, context), k);
                    RelativeLayout relativelayout = getCompleteView(widgetWidth, widgetHeight, (String)arraylist1.get(0), (String)arraylist2.get(0), (Action)arraylist3.get(0), (List)arraylist4.get(0));
                    addView(relativelayout);
                    ((android.widget.LinearLayout.LayoutParams)relativelayout.getLayoutParams()).bottomMargin = ScreenMathUtils.dpToPx(10, context);
                    updateList(arraylist1, arraylist2, arraylist3, arraylist4);
                    for (; arraylist1.size() > 0; addView(getTwoImageView(arraylist1, arraylist2, arraylist3, arraylist4))) { }
                }
            } else
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
        widgetWidth = i - ScreenMathUtils.dpToPx(20, context);
        widgetHeight = (int)(0.52000000000000002D * (double)widgetWidth);
    }

    private RelativeLayout getCompleteView(int i, int j, String s, String s1, Action action, List list)
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        relativelayout.setLayoutParams(new android.widget.RelativeLayout.LayoutParams(i, j));
        relativelayout.setBackgroundResource(0x7f0200f6);
        relativelayout.addView(getImageView(s));
        relativelayout.addView(getTextView(s1, list));
        addRequestIdToActionParamsExplicitly(action);
        setHeading(action, s1);
        relativelayout.setTag(action);
        relativelayout.setOnClickListener(this);
        return relativelayout;
    }

    private RelativeLayout getDividerView()
    {
        RelativeLayout relativelayout = new RelativeLayout(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(widgetWidth, -2);
        layoutparams.setMargins(0, 10, 0, 0);
        relativelayout.setLayoutParams(layoutparams);
        relativelayout.setTag("divider");
        View view = new View(context);
        android.widget.RelativeLayout.LayoutParams layoutparams1 = new android.widget.RelativeLayout.LayoutParams(-1, 2);
        layoutparams1.addRule(12, 1);
        view.setLayoutParams(layoutparams1);
        view.setBackgroundColor(getContext().getResources().getColor(0x7f090046));
        relativelayout.addView(view);
        return relativelayout;
    }

    private ExpandableSubcategoryWidget getExpandableWidget(List list)
    {
        if (list.size() == 0)
        {
            return null;
        }
        LayoutDetails layoutdetails = new LayoutDetails();
        ExpandableSubcategoryWidget expandablesubcategorywidget;
        if (layoutDetails != null)
        {
            layoutdetails.setTheme(layoutDetails.getTheme());
        } else
        {
            layoutdetails.setTheme(theme.name());
        }
        expandablesubcategorywidget = new ExpandableSubcategoryWidget(context, layoutdetails, theme, onClickListener, null, list, 2, false, requestId, true, activity, hashTag);
        expandablesubcategorywidget.setTag("Expanded");
        return expandablesubcategorywidget;
    }

    private NetworkImageView getImageView(String s)
    {
        NetworkImageView networkimageview = new NetworkImageView(context);
        networkimageview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
        networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
        networkimageview.setId(0x14567);
        networkimageview.setTag("view_expanded_false");
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

    private TextView getTextView(String s, List list)
    {
        CustomRobotoCondensedTextView customrobotocondensedtextview = new CustomRobotoCondensedTextView(context, null);
        android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, (int)(0.17000000000000001D * (double)widgetHeight));
        layoutparams.addRule(12);
        customrobotocondensedtextview.setLayoutParams(layoutparams);
        customrobotocondensedtextview.setText(s);
        customrobotocondensedtextview.setTextColor(Color.parseColor(getResources().getString(0x7f090070)));
        customrobotocondensedtextview.setBackgroundDrawable(new ColorDrawable(0xcc000000));
        customrobotocondensedtextview.setPadding(ScreenMathUtils.dpToPx(5, context), 0, 0, 0);
        customrobotocondensedtextview.setGravity(16);
        customrobotocondensedtextview.setTextSize(16F);
        customrobotocondensedtextview.setId(291);
        customrobotocondensedtextview.setTag(list);
        return customrobotocondensedtextview;
    }

    private LinearLayout getTwoImageView(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, ArrayList arraylist3)
    {
        LinearLayout linearlayout = getLinearLayout(0, widgetWidth, widgetHeight, 0, 0, 0, 0);
        int i = widgetWidth;
        RelativeLayout relativelayout = getCompleteView((int)(0.5D * (double)i), widgetHeight, (String)arraylist.get(0), (String)arraylist1.get(0), (Action)arraylist2.get(0), (List)arraylist3.get(0));
        updateList(arraylist, arraylist1, arraylist2, arraylist3);
        linearlayout.addView(relativelayout);
        ((android.widget.LinearLayout.LayoutParams)relativelayout.getLayoutParams()).rightMargin = ScreenMathUtils.dpToPx(5, context);
        RelativeLayout relativelayout1 = getCompleteView((int)(0.5D * (double)i), widgetHeight, (String)arraylist.get(0), (String)arraylist1.get(0), (Action)arraylist2.get(0), (List)arraylist3.get(0));
        linearlayout.addView(relativelayout1);
        ((android.widget.LinearLayout.LayoutParams)relativelayout1.getLayoutParams()).leftMargin = ScreenMathUtils.dpToPx(5, context);
        updateList(arraylist, arraylist1, arraylist2, arraylist3);
        return linearlayout;
    }

    private View getView()
    {
        View view = new View(context);
        view.setLayoutParams(new android.widget.LinearLayout.LayoutParams(ScreenMathUtils.dpToPx(3, context), -1));
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

    private void updateList(ArrayList arraylist, ArrayList arraylist1, ArrayList arraylist2, ArrayList arraylist3)
    {
        arraylist.remove(0);
        arraylist1.remove(0);
        arraylist2.remove(0);
        arraylist3.remove(0);
    }

    public void onClick(View view)
    {
        Action action = (Action)view.getTag();
        if (action == null) goto _L2; else goto _L1
_L1:
        String s = action.getScreenType();
        if (StringUtils.isNullOrEmpty(s) || activity == null || s.equals("expand")) goto _L2; else goto _L3
_L3:
        WidgetAction.performAction(action, activity, PageTypeUtils.values()[FlipkartPreferenceManager.instance().getLastPageType()]);
_L5:
        return;
_L2:
        RelativeLayout relativelayout = (RelativeLayout)view;
        TextView textview;
        ImageView imageview;
        View view1;
        View view2;
        View view3;
        View view4;
        int i;
        ExpandableSubcategoryWidget expandablesubcategorywidget;
        int j;
        ExpandableSubcategoryWidget expandablesubcategorywidget1;
        android.widget.LinearLayout.LayoutParams layoutparams;
        if (relativelayout != null)
        {
            TextView textview1 = (TextView)relativelayout.findViewById(291);
            ImageView imageview1 = (ImageView)relativelayout.findViewById(0x14567);
            textview = textview1;
            imageview = imageview1;
        } else
        {
            textview = null;
            imageview = null;
        }
        if (textview == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        view1 = findViewWithTag("Expanded");
        if (view1 != null)
        {
            removeView(view1);
        }
        view2 = findViewWithTag("divider");
        if (view2 != null)
        {
            removeView(view2);
        }
        if (imageview != null && ((String)imageview.getTag()).equals("view_expanded_true"))
        {
            imageview.setTag("view_expanded_false");
            return;
        }
        view3 = (View)view.getParent();
        if (view3 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!(view3 instanceof MetroTileExpandableWidget))
        {
            break; /* Loop/switch isn't completed */
        }
        j = ((MetroTileExpandableWidget)view3).indexOfChild(view);
        expandablesubcategorywidget1 = getExpandableWidget((ArrayList)textview.getTag());
        layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.setMargins(0, 0, ScreenMathUtils.dpToPx(7, context), 0);
        expandablesubcategorywidget1.setLayoutParams(layoutparams);
        if (expandablesubcategorywidget1 != null)
        {
            imageview.setTag("view_expanded_true");
            addView(getDividerView(), j + 1);
            addView(expandablesubcategorywidget1, j + 2);
            expandablesubcategorywidget1.setFocusableInTouchMode(true);
            expandablesubcategorywidget1.requestFocus();
            return;
        }
        if (true) goto _L5; else goto _L4
_L4:
        view4 = (View)view3.getParent();
        if (view4 != null && (view4 instanceof MetroTileExpandableWidget))
        {
            i = ((MetroTileExpandableWidget)view4).indexOfChild(view3);
            expandablesubcategorywidget = getExpandableWidget((ArrayList)textview.getTag());
            if (expandablesubcategorywidget != null)
            {
                imageview.setTag("view_expanded_true");
                addView(getDividerView(), i + 1);
                addView(expandablesubcategorywidget, i + 2);
                expandablesubcategorywidget.setFocusableInTouchMode(true);
                expandablesubcategorywidget.requestFocus();
                return;
            }
        }
        if (true) goto _L5; else goto _L6
_L6:
    }
}
