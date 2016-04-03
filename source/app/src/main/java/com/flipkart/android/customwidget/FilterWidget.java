// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.ButtonValue;
import com.flipkart.android.response.component.data.customvalues.FilterValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.response.discovery.FacetResponse;
import com.flipkart.android.response.discovery.FacetValue;
import com.flipkart.android.response.discovery.PerFilterMetaData;
import com.flipkart.android.response.discovery.ResourceResponse;
import com.flipkart.android.utils.FacetData;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.drawable.GradientDrawableUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class FilterWidget extends BaseWidget
{

    public static int Id = 0;
    public static final String WIDGET_COMMON_NAME = "FILTER";
    private String buttonTitle;
    private List facetResponse;
    private Action filterAction;
    private Map filterLayouts;
    private FkProductListContext fkContext;
    private int itemWidth;
    private int maxSize;
    private android.view.View.OnClickListener subBrandsClicked;
    private android.view.View.OnClickListener viewMoreClicked;

    public FilterWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist, Activity activity, 
            int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        filterAction = null;
        fkContext = new FkProductListContext();
        facetResponse = new ArrayList();
        filterLayouts = new LinkedHashMap();
        maxSize = 4;
        buttonTitle = "Find";
        viewMoreClicked = new _cls2();
        subBrandsClicked = new _cls3();
        if (arraylist != null && arraylist.size() > 0)
        {
            WidgetItem widgetitem1 = (WidgetItem)arraylist.get(0);
            if (widgetitem1 != null)
            {
                FilterValue filtervalue = (FilterValue)widgetitem1.getValue();
                filterAction = widgetitem1.getAction();
                if (filtervalue != null)
                {
                    facetResponse = filtervalue.getFacetResponses();
                    maxSize = filtervalue.getMaxItems();
                    buttonTitle = filtervalue.getButton().getTitle();
                }
            }
        }
        if (maxSize == 0)
        {
            maxSize = 4;
        }
        if (facetResponse.size() == 0)
        {
            setVisibility(8);
            return;
        }
        screenDpi = ScreenMathUtils.getScreenDpi(context);
        calculateWidthAndHeight();
        setOrientation(1);
        setId(Id);
        HorizontalScrollView horizontalscrollview = new HorizontalScrollView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        horizontalscrollview.setLayoutParams(layoutparams);
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(layoutparams);
        linearlayout.setOrientation(0);
        linearlayout.setBackgroundColor(Color.parseColor(getResources().getString(0x7f090070)));
        for (int j = 0; j < facetResponse.size(); j++)
        {
            ArrayList arraylist1 = new ArrayList();
            ArrayList arraylist2 = new ArrayList();
            String s = ((FacetResponse)facetResponse.get(j)).getTitle();
            ArrayList arraylist3 = ((FacetResponse)facetResponse.get(j)).getValue();
            ArrayList arraylist4 = new ArrayList();
            fkContext.getSelectedFilterMap().put(s, arraylist4);
            LinearLayout linearlayout1;
            if (arraylist3.size() < maxSize)
            {
                LinkedHashMap linkedhashmap = new LinkedHashMap();
                for (int k = 0; k < arraylist3.size(); k++)
                {
                    updateFilterInfo((FacetValue)arraylist3.get(k), arraylist1, linkedhashmap);
                }

                fkContext.getFilterMap().put(s, linkedhashmap);
            } else
            {
                LinkedHashMap linkedhashmap1 = new LinkedHashMap();
                for (int i1 = 0; i1 < maxSize; i1++)
                {
                    updateFilterInfo((FacetValue)arraylist3.get(i1), arraylist1, linkedhashmap1);
                }

                for (int j1 = maxSize; j1 < arraylist3.size(); j1++)
                {
                    updateFilterInfo((FacetValue)arraylist3.get(j1), arraylist2, linkedhashmap1);
                }

                fkContext.getFilterMap().put(s, linkedhashmap1);
            }
            if (arraylist3.size() <= 0)
            {
                continue;
            }
            linearlayout1 = getListLayout();
            if (arraylist1.size() > 0)
            {
                linearlayout1.addView(getTitle(s, false));
                linearlayout1.addView(getUnderLine());
            }
            for (int l = 0; l < arraylist1.size(); l++)
            {
                linearlayout1.addView(getSubBrands(s, (FacetValue)arraylist1.get(l)));
                if (l < -1 + arraylist1.size())
                {
                    linearlayout1.addView(getUnderLine());
                }
            }

            if (arraylist1.size() > 0 && arraylist3.size() > maxSize)
            {
                linearlayout1.addView(getUnderLine());
                linearlayout1.addView(getViewMore(s));
            }
            linearlayout1.setTag(arraylist1);
            filterLayouts.put(s, linearlayout1);
            linearlayout.addView(linearlayout1);
        }

        horizontalscrollview.addView(linearlayout);
        addView(horizontalscrollview);
        if (StringUtils.isNullOrEmpty(buttonTitle))
        {
            buttonTitle = "Find";
        }
        addView(getButton(buttonTitle, activity));
    }

    private void calculateWidthAndHeight()
    {
        itemWidth = (int)((double)getResources().getDisplayMetrics().widthPixels / 1.5D);
    }

    private View getButton(String s, final Activity activity)
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setBackgroundColor(Color.parseColor(getResources().getString(0x7f090070)));
        Button button = new Button(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(40, context));
        int i = ScreenMathUtils.dpToPx(10, context);
        layoutparams.setMargins(i, 0, i, i);
        button.setLayoutParams(layoutparams);
        button.setText(s);
        button.setTextColor(Color.parseColor(getResources().getString(0x7f090070)));
        button.setBackgroundDrawable(GradientDrawableUtils.getGradintDrawable(getResources().getColor(0x7f09003b), -1));
        button.setOnClickListener(new _cls1());
        linearlayout.addView(button);
        return linearlayout;
    }

    private TextView getGenericTextView()
    {
        TextView textview = new TextView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(itemWidth, -2);
        int i = ScreenMathUtils.dpToPx(10, context);
        textview.setLayoutParams(layoutparams);
        textview.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "robotoregular.ttf"));
        textview.setCompoundDrawablePadding(ScreenMathUtils.dpToPx(10, context));
        textview.setSingleLine(true);
        textview.setEllipsize(android.text.TextUtils.TruncateAt.END);
        textview.setPadding(i, i, i, i);
        return textview;
    }

    private LinearLayout getListLayout()
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(itemWidth, -2));
        linearlayout.setOrientation(1);
        return linearlayout;
    }

    private int getSelectedCount(String s)
    {
        if (fkContext != null)
        {
            Map map = fkContext.getSelectedFilterMap();
            if (map != null && !StringUtils.isNullOrEmpty(s))
            {
                ArrayList arraylist = (ArrayList)map.get(s);
                if (arraylist != null)
                {
                    return arraylist.size();
                }
            }
        }
        return 0;
    }

    private TextView getSubBrands(String s, FacetValue facetvalue)
    {
        TextView textview = getGenericTextView();
        int i = facetvalue.getCount();
        String s1 = facetvalue.getTitle();
        if (i == 0)
        {
            textview.setTextColor(Color.parseColor("#A4A4A4"));
        } else
        {
            textview.setTextColor(Color.parseColor("#565656"));
        }
        textview.setTextSize(14F);
        textview.setText(s1);
        if (((ArrayList)fkContext.getSelectedFilterMap().get(s)).contains(s1))
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008c, 0, 0, 0);
        } else
        if (i != 0)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008d, 0, 0, 0);
        }
        textview.setText(s1);
        if (i != 0)
        {
            textview.setTag((new StringBuilder()).append(s).append("/").append(facetvalue.getTitle()).toString());
        } else
        {
            textview.setTag("zeroCount");
        }
        textview.setOnClickListener(subBrandsClicked);
        return textview;
    }

    private TextView getTitle(String s, boolean flag)
    {
        TextView textview = getGenericTextView();
        textview.setTag(s);
        textview.setTextSize(16F);
        textview.setId(2013);
        if (flag)
        {
            int i = getSelectedCount(s);
            if (i != 0)
            {
                s = (new StringBuilder()).append(s).append("(").append(i).append(")").toString();
            }
        }
        textview.setText(s);
        textview.setTextColor(Color.parseColor(getResources().getString(0x7f090006)));
        return textview;
    }

    private View getUnderLine()
    {
        TextView textview = new TextView(context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(itemWidth, ScreenMathUtils.dpToPx(1, context));
        int i = ScreenMathUtils.dpToPx(10, context);
        layoutparams.setMargins(i, 0, i, 0);
        textview.setLayoutParams(layoutparams);
        textview.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#E5E5E5")));
        return textview;
    }

    private TextView getViewMore(String s)
    {
        TextView textview = getGenericTextView();
        textview.setText("more options");
        textview.setId(2014);
        textview.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0x7f020088, 0);
        textview.setTextSize(14F);
        textview.setTextColor(Color.parseColor("#565656"));
        textview.setGravity(5);
        textview.setOnClickListener(viewMoreClicked);
        textview.setTag(s);
        return textview;
    }

    private void refreshList(LinearLayout linearlayout)
    {
        ArrayList arraylist = (ArrayList)linearlayout.getTag();
        String s = "";
        boolean flag;
        TextView textview;
        int i;
        int j;
        if (linearlayout.findViewById(2014) != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        textview = (TextView)linearlayout.findViewById(2013);
        if (textview != null)
        {
            s = (String)textview.getTag();
        }
        linearlayout.removeAllViews();
        i = arraylist.size();
        j = 0;
        if (i > 0)
        {
            linearlayout.addView(getTitle(s, true));
            linearlayout.addView(getUnderLine());
        }
        for (; j < arraylist.size(); j++)
        {
            linearlayout.addView(getSubBrands(s, (FacetValue)arraylist.get(j)));
            if (j < -1 + arraylist.size())
            {
                linearlayout.addView(getUnderLine());
            }
        }

        if (arraylist.size() > 0 && flag)
        {
            linearlayout.addView(getUnderLine());
            linearlayout.addView(getViewMore(s));
        }
        linearlayout.setTag(arraylist);
    }

    private void refreshTitle(LinearLayout linearlayout)
    {
        TextView textview = (TextView)linearlayout.findViewById(2013);
        if (textview != null)
        {
            String s = (String)textview.getTag();
            int i = getSelectedCount(s);
            if (i != 0)
            {
                s = (new StringBuilder()).append(s).append("(").append(i).append(")").toString();
            }
            textview.setText(s);
        }
    }

    private void updateFilterInfo(FacetValue facetvalue, ArrayList arraylist, Map map)
    {
        String s = facetvalue.getTitle();
        String s1 = facetvalue.getMetadata().getId();
        String s2 = facetvalue.getMetadata().getDescription();
        int i = facetvalue.getCount();
        String s3 = "";
        if (facetvalue.getResource() != null)
        {
            s3 = facetvalue.getResource().getParams();
        }
        arraylist.add(facetvalue);
        FacetData facetdata = new FacetData();
        facetdata.setSelected(false);
        facetdata.setTitle(s);
        facetdata.setCount(i);
        facetdata.setParams(s3);
        facetdata.setOfferDescription(s2);
        facetdata.setOfferId(s1);
        map.put(s, facetdata);
    }

    public void refreshView(FkProductListContext fkproductlistcontext, String s)
    {
        LinearLayout linearlayout = (LinearLayout)filterLayouts.get(s);
        fkContext = fkproductlistcontext;
        if (linearlayout != null)
        {
            refreshList(linearlayout);
        }
    }

    static 
    {
        Id = 4582;
    }





    private class _cls2
        implements android.view.View.OnClickListener
    {

        final FilterWidget this$0;

        public void onClick(View view)
        {
            String s = (String)view.getTag();
            UUID uuid = UUID.randomUUID();
            FilterResponse filterresponse = new FilterResponse();
            filterresponse.setFilterKey(s);
            filterresponse.setSaveCheckedItemInFkContext(true);
            filterresponse.setFkContext(fkContext);
            ContextCache.getInstance().putResponse((new StringBuilder()).append(uuid).append("_filterResponse").toString(), filterresponse);
            Intent intent = new Intent(activity, com/flipkart/android/activity/FilterActivity);
            intent.putExtra("PRODUCT_LIST_EXTRAS_SCREEN_TYPE", 1);
            intent.putExtra("PRODUCT_PAGE_UUID", uuid.toString());
            activity.startActivityForResult(intent, 1);
        }

        _cls2()
        {
            this$0 = FilterWidget.this;
            super();
        }
    }


    private class _cls3
        implements android.view.View.OnClickListener
    {

        final FilterWidget this$0;

        public void onClick(View view)
        {
            String s = (String)view.getTag();
            String as[];
            if (!s.equals("zeroCount"))
            {
                if ((as = s.split("/")).length > 1)
                {
                    String s1 = as[0];
                    String s2 = as[1];
                    TextView textview = (TextView)view;
                    if (((ArrayList)fkContext.getSelectedFilterMap().get(s1)).contains(s2))
                    {
                        ((ArrayList)fkContext.getSelectedFilterMap().get(s1)).remove(s2);
                        textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008d, 0, 0, 0);
                    } else
                    {
                        ((ArrayList)fkContext.getSelectedFilterMap().get(s1)).add(s2);
                        textview.setCompoundDrawablesWithIntrinsicBounds(0x7f02008c, 0, 0, 0);
                    }
                    refreshTitle((LinearLayout)filterLayouts.get(s1));
                    return;
                }
            }
        }

        _cls3()
        {
            this$0 = FilterWidget.this;
            super();
        }
    }


    private class _cls1
        implements android.view.View.OnClickListener
    {

        final FilterWidget this$0;
        final Activity val$activity;

        public void onClick(View view)
        {
            if (filterAction != null)
            {
                Action action = filterAction;
                Map map = action.getParams();
                StringBuilder stringbuilder = new StringBuilder();
                Map map1 = fkContext.getSelectedFilterMap();
                if (map1 != null)
                {
                    Iterator iterator = map1.keySet().iterator();
                    do
                    {
                        if (!iterator.hasNext())
                        {
                            break;
                        }
                        String s1 = (String)iterator.next();
                        ArrayList arraylist = (ArrayList)map1.get(s1);
                        if (arraylist != null)
                        {
                            int i = 0;
                            while (i < arraylist.size()) 
                            {
                                String s2 = ((FacetData)((Map)fkContext.getFilterMap().get(s1)).get(arraylist.get(i))).getParams();
                                if (!StringUtils.isNullOrEmpty(s2))
                                {
                                    stringbuilder.append(s2);
                                    stringbuilder.append("&");
                                }
                                i++;
                            }
                        }
                    } while (true);
                }
                String s;
                if (stringbuilder.length() > 0)
                {
                    stringbuilder.setLength(-1 + stringbuilder.length());
                    s = stringbuilder.toString();
                } else
                {
                    s = "";
                }
                map.put("filter", s);
                action.setParams(map);
                WidgetAction.performAction(action, activity, PageTypeUtils.None);
            }
        }

        _cls1()
        {
            this$0 = FilterWidget.this;
            activity = activity1;
            super();
        }
    }

}
