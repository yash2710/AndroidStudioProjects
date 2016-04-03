// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.component;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.android.volley.toolbox.ImageLoader;
import com.flipkart.android.DB.BrowseHistoryDao;
import com.flipkart.android.customwidget.AnnouncementWidget;
import com.flipkart.android.customwidget.BalloonWidget;
import com.flipkart.android.customwidget.BaseWidget;
import com.flipkart.android.customwidget.BrowseHistoryWidget;
import com.flipkart.android.customwidget.CallUsWidget;
import com.flipkart.android.customwidget.CollapsedExpandableWidget;
import com.flipkart.android.customwidget.CustomTabWidget;
import com.flipkart.android.customwidget.CustomTitleWidget;
import com.flipkart.android.customwidget.DotdWidget;
import com.flipkart.android.customwidget.ExpandableSubcategoryWidget;
import com.flipkart.android.customwidget.FilterWidget;
import com.flipkart.android.customwidget.MerchandizeWidget;
import com.flipkart.android.customwidget.MetroTileExpandableWidget;
import com.flipkart.android.customwidget.MetroTileWidget;
import com.flipkart.android.customwidget.OMUInfiniteListView;
import com.flipkart.android.customwidget.OMUParentWidget;
import com.flipkart.android.customwidget.OfferZoneWidget;
import com.flipkart.android.customwidget.PMUWidget;
import com.flipkart.android.customwidget.PromotionsWidget;
import com.flipkart.android.customwidget.RecommendationsWidget;
import com.flipkart.android.customwidget.SearchWidget;
import com.flipkart.android.customwidget.SiteWideOffersWidget;
import com.flipkart.android.customwidget.SplitPanelWidget;
import com.flipkart.android.customwidget.SplitterWidget;
import com.flipkart.android.customwidget.SponsoredWidget;
import com.flipkart.android.customwidget.TitleWidget;
import com.flipkart.android.response.component.data.WidgetData;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.HeaderValue;
import com.flipkart.android.response.component.data.customvalues.TitleValue;
import com.flipkart.android.response.component.layout.LayoutContainer;
import com.flipkart.android.response.component.layout.LayoutData;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.response.component.layout.LayoutInfo;
import com.flipkart.android.response.customwidgetitemvalue.TrackingParams;
import com.flipkart.android.urlmanagement.AppAction;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils.component:
//            d, c

public class MultiWidgetPageBuilder
{

    public MultiWidgetPageBuilder()
    {
    }

    public static void addView(LinearLayout linearlayout, View view, int i)
    {
        try
        {
            linearlayout.addView(view, i);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static void buildMultiWidgetPage(LayoutContainer layoutcontainer, Map map, LinearLayout linearlayout, Activity activity, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, String s, boolean flag, 
            String s1)
    {
        if (layoutcontainer == null) goto _L2; else goto _L1
_L1:
        ArrayList arraylist;
        int i;
        LinearLayout linearlayout1;
        arraylist = layoutcontainer.getChildren();
        i = arraylist.size();
        if (flag)
        {
            linearlayout1 = (LinearLayout)linearlayout.findViewById(0x7f0a008a);
        } else
        {
            linearlayout1 = linearlayout;
        }
        if (linearlayout1 != null) goto _L3; else goto _L2
_L2:
        return;
_L3:
        Iterator iterator;
        int j;
        int k;
        FkLogger.debug("Crash", (new StringBuilder("root view is ")).append(linearlayout1).append(" contaiver view is  ").append(linearlayout).toString());
        iterator = arraylist.iterator();
        j = 0;
        k = 0;
_L5:
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        LayoutInfo layoutinfo = (LayoutInfo)iterator.next();
        String s2 = layoutinfo.getWidgetType();
        FkLogger.debug("ViewUpdate", (new StringBuilder("layout type ")).append(layoutinfo.getWidgetType()).toString());
        WidgetData widgetdata = (WidgetData)map.get(layoutinfo.getData().getDataKey());
        FkLogger.debug("Test", (new StringBuilder("Widget data for ")).append(layoutinfo.getData().getDataKey()).append(" is ").append(widgetdata).toString());
        int l;
        View view;
        com.flipkart.android.customwidget.BaseWidget.WidgetTheme widgettheme;
        View view1;
        int i1;
        int j1;
        if (j == i - 1 && flag)
        {
            linearlayout1 = linearlayout;
            l = 1;
        } else
        {
            l = j;
        }
        view = linearlayout1.findViewWithTag(layoutinfo.getData().getDataKey());
        if (layoutcontainer.getLayoutDetails() == null)
        {
            widgettheme = null;
        } else
        {
            widgettheme = layoutcontainer.getLayoutDetails().getThemeObject();
        }
        if (view != null && widgetdata != null && ((BaseWidget)view).getHashTag() == widgetdata.getDataId())
        {
            FkLogger.debug("ViewUpdate", (new StringBuilder("skip view ")).append(widgetdata.getType()).toString());
            view1 = null;
        } else
        {
            if (widgetdata != null)
            {
                FkLogger.debug("ViewUpdate", (new StringBuilder("update view ")).append(widgetdata.getType()).toString());
            }
            view1 = buildWidget(view, linearlayout1, linearlayout, s2, layoutinfo.getData().getUpdatedBy(), layoutinfo.getData().getDataKey(), widgetdata, layoutinfo.getLayoutDetails(), widgettheme, linearlayout1.getContext(), activity, onclicklistener, imageloader, s, s1);
        }
        if (view == null)
        {
            if (view1 == null)
            {
                break MISSING_BLOCK_LABEL_641;
            }
            FkLogger.debug("Metrics", (new StringBuilder("adding the view ")).append(l).toString());
            FkLogger.debug("ViewUpdate", (new StringBuilder("Add view no view there ")).append(view1).toString());
            addView(linearlayout1, view1, l);
        } else
        {
            FkLogger.debug("ViewUpdate", "original view is not null");
            if (view1 != null)
            {
                linearlayout1.indexOfChild(view);
                if (view1 == view)
                {
                    FkLogger.debug("ViewUpdate", "original view is equal to updated view");
                } else
                {
                    linearlayout1.removeView(view);
                    FkLogger.debug("ViewUpdate", (new StringBuilder("Final view is ")).append(view1).toString());
                    addView(linearlayout1, view1, l);
                }
            }
        }
        i1 = l + 1;
_L6:
        j1 = k + 1;
        j = i1;
        k = j1;
        if (true) goto _L5; else goto _L4
_L4:
        FkLogger.debug("Test", (new StringBuilder("index ")).append(k).append(" ").append(j).append(" ").append(linearlayout1.getChildCount()).toString());
        if (linearlayout1.getChildCount() > j)
        {
            linearlayout1.removeViews(j, linearlayout1.getChildCount() - j);
            return;
        }
          goto _L2
        i1 = l;
          goto _L6
    }

    public static View buildWidget(View view, View view1, View view2, String s, String s1, String s2, WidgetData widgetdata, LayoutDetails layoutdetails, 
            com.flipkart.android.customwidget.BaseWidget.WidgetTheme widgettheme, Context context, Activity activity, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, String s3, String s4)
    {
        if (!s.equals("BANNER")) goto _L2; else goto _L1
_L1:
        if (view == null) goto _L4; else goto _L3
_L3:
        ((PromotionsWidget)view).updateWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData());
        Object obj = view;
_L11:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        ((View) (obj)).setTag(s2);
        return ((View) (obj));
_L4:
        obj = new PromotionsWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), null, activity, widgetdata.getDataId());
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        Exception exception1;
        exception1 = exception;
        obj = view;
_L9:
        FkLogger.printStackTrace(exception1);
        FkLogger.debug("Test", (new StringBuilder("Exception ")).append(exception1.toString()).toString());
        return ((View) (obj));
_L2:
        if (s.equals("METRO_EXPANDABLE"))
        {
            obj = new MetroTileExpandableWidget(context, widgetdata.getData(), layoutdetails, widgettheme, widgetdata.getHeader(), onclicklistener, activity, s3, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
          goto _L5
        Error error;
        error;
        Error error1;
        error1 = error;
        obj = view;
_L7:
        FkLogger.printStackTrace(error1);
        FkLogger.debug("Test", (new StringBuilder("Error ")).append(error1.toString()).toString());
        return ((View) (obj));
_L5:
        if (s.equals("SMU"))
        {
            obj = new MerchandizeWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), s3, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("OFFER"))
        {
            obj = new OfferZoneWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), null, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("EXPANDABLE"))
        {
            obj = new ExpandableSubcategoryWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), 2, false, s3, false, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("FILTER"))
        {
            obj = new FilterWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("CALL_US"))
        {
            obj = new CallUsWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("ANNOUNCEMENT"))
        {
            obj = new AnnouncementWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("SEARCH"))
        {
            obj = new SearchWidget(context, layoutdetails, widgettheme, widgetdata.getHeader(), widgetdata.getData(), onclicklistener, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("METRO_TILE3"))
        {
            obj = new MetroTileWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), s3, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (!s.equals("OMU"))
        {
            break MISSING_BLOCK_LABEL_724;
        }
        if (layoutdetails == null)
        {
            break MISSING_BLOCK_LABEL_686;
        }
        if (layoutdetails.getOrientation().equalsIgnoreCase("vertical"))
        {
            obj = new OMUInfiniteListView(context, widgetdata.getData(), onclicklistener, imageloader, activity, layoutdetails, widgettheme, widgetdata.getHeader(), new d(view1), widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        obj = new OMUParentWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), null, activity, widgetdata.getDataId());
        continue; /* Loop/switch isn't completed */
        if (s.equals("TAB"))
        {
            obj = new CustomTabWidget(context, null, null, onclicklistener, null, activity, widgetdata.getData(), widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("SPONSORED"))
        {
            obj = new SponsoredWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getData(), activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (!s.equals("PMU"))
        {
            break MISSING_BLOCK_LABEL_1126;
        }
        if (StringUtils.isNullOrEmpty(s1) || !s1.equalsIgnoreCase("client"))
        {
            break MISSING_BLOCK_LABEL_1088;
        }
        if (s2.equals("Recommendations"))
        {
            obj = new RecommendationsWidget(context, layoutdetails, widgettheme, onclicklistener, null, null, activity, 0);
            continue; /* Loop/switch isn't completed */
        }
        if (s2.equals("BrowseHistory"))
        {
            WidgetItem widgetitem = new WidgetItem();
            TitleValue titlevalue = new TitleValue("You Recently Viewed");
            (new ArrayList()).add(titlevalue);
            widgetitem.setValue(new HeaderValue(titlevalue, null, null));
            Action action = new Action();
            action.setScreenType(AppAction.productListView.toString());
            action.setOmnitureData("BrowseHistoryWidget");
            LinkedHashMap linkedhashmap = new LinkedHashMap();
            linkedhashmap.put("pids", (new BrowseHistoryDao(context)).getAllBrowseHistoryPids());
            linkedhashmap.put("title", "You Recently Viewed");
            action.setParams(linkedhashmap);
            TrackingParams trackingparams = new TrackingParams();
            trackingparams.setPageType("hp");
            trackingparams.setWidgetType("recently_viewed");
            trackingparams.setOtracker("hp_recently_viewed");
            action.setTracking(trackingparams);
            widgetitem.setAction(action);
            obj = new BrowseHistoryWidget(context, layoutdetails, widgettheme, onclicklistener, widgetitem, null, activity);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_1597;
        obj = new PMUWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), null, activity, widgetdata.getDataId());
        continue; /* Loop/switch isn't completed */
        if (!s.equals("SITE_WIDE_OFFERS"))
        {
            break MISSING_BLOCK_LABEL_1244;
        }
        if (s4.contains("homepage"))
        {
            obj = new SiteWideOffersWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), 2, false, s3, false, activity, s4, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        widgetdata.getHeader().setAction(null);
        obj = new SiteWideOffersWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), 2, false, s3, false, activity, s4, widgetdata.getDataId());
        continue; /* Loop/switch isn't completed */
        if (s.equals("BALLOON_NAVIGATION"))
        {
            obj = new BalloonWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), s3, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("SPLITPANEL"))
        {
            obj = new SplitPanelWidget(context, layoutdetails, widgettheme, onclicklistener, null, null, s3, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("SPLITTERVIEW"))
        {
            obj = new SplitterWidget(context, widgettheme);
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("COLLAPSE_EXPANDABLE"))
        {
            obj = new CollapsedExpandableWidget(context, layoutdetails, widgettheme, onclicklistener, new c(view2), widgetdata.getHeader(), widgetdata.getData(), 2, false, s3, true, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("TITLE"))
        {
            obj = new TitleWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals(CustomTitleWidget.COMMON_NAME_HOME))
        {
            obj = new CustomTitleWidget(context, layoutdetails, widgettheme, onclicklistener, null, CustomTitleWidget.TYPE_HOME, activity, 0);
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals(CustomTitleWidget.COMMON_NAME_OFFER))
        {
            obj = new CustomTitleWidget(context, layoutdetails, widgettheme, onclicklistener, null, CustomTitleWidget.TYPE_OFFER, activity, 0);
            continue; /* Loop/switch isn't completed */
        }
        if (s.equals("DOTD"))
        {
            obj = new DotdWidget(context, layoutdetails, widgettheme, onclicklistener, widgetdata.getHeader(), widgetdata.getData(), false, s3, false, activity, widgetdata.getDataId());
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_1597;
        error1;
        if (true) goto _L7; else goto _L6
_L6:
        exception1;
        if (true) goto _L9; else goto _L8
_L8:
        obj = view;
        if (true) goto _L11; else goto _L10
_L10:
    }
}
