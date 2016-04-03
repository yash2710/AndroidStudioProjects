// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.IViewPortView;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.RecommendationDataHandler;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.BrowseHistoryUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;

// Referenced classes of package com.flipkart.android.customwidget:
//            ScrollWidget

public class RecommendationsWidget extends ScrollWidget
    implements IViewPortView
{

    public static int Id = 0;
    private static final int MAX_ITEMS_TO_FETCH = 10;
    public static final String SOURCE_HOMEPAGE = "homePage";
    public static final String SOURCE_PRODUCT_LIST_PAGE = "productListPage";
    public static final String SOURCE_PRODUCT_PAGE = "productPage";
    public static final String WIDGET_COMMON_NAME = "RECOMMENDATIONS";
    private ArrayList browseHistoryProdIds;
    private View loadingView;
    private boolean recommendationsViewed;
    private String requestId;
    private String source;

    public RecommendationsWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, String s, Activity activity, 
            int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, true, true, activity, i);
        loadingView = null;
        recommendationsViewed = false;
        requestId = s;
        showRefreshingListItem();
        downloadRecoData();
    }

    public RecommendationsWidget(Context context, ArrayList arraylist, android.view.View.OnClickListener onclicklistener, Activity activity, String s)
    {
        super(context, null, null, onclicklistener, null, true, true, activity, 0);
        loadingView = null;
        recommendationsViewed = false;
        browseHistoryProdIds = arraylist;
        showRefreshingListItem();
        source = s;
    }

    public void downloadRecoData()
    {
        FkLogger.debug("Reco", "Download called.......");
        _cls1 _lcls1 = new _cls1();
        if (browseHistoryProdIds == null)
        {
            browseHistoryProdIds = BrowseHistoryUtils.getHistoryProdIds();
        }
        if (!StringUtils.isNullOrEmpty(browseHistoryProdIds))
        {
            recommendationsViewed = true;
            FkLogger.debug("Reco", "making reco calls.. hence null");
            if (!StringUtils.isNullOrEmpty(source))
            {
                _lcls1.getRecommendations(browseHistoryProdIds, 10, source, new AnalyticData(requestId, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
                return;
            } else
            {
                _lcls1.getRecommendations(browseHistoryProdIds, 10, "homePage", new AnalyticData(requestId, null, FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil()));
                return;
            }
        } else
        {
            FkLogger.debug("Reco", "no browsehistory products.. hence null");
            setVisibility(8);
            return;
        }
    }

    public View getView()
    {
        if (recommendationsViewed)
        {
            this = null;
        }
        return this;
    }

    public void isInViewPort()
    {
        downloadRecoData();
    }

    public void showRefreshingListItem()
    {
        horizontalScrollView.setVisibility(4);
        loadingView = (RelativeLayout)LayoutInflater.from(context).inflate(0x7f03005d, null);
        addView(loadingView);
    }

    public void updateWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, ArrayList arraylist)
    {
    }

    static 
    {
        Id = 12345;
    }


    private class _cls1 extends RecommendationDataHandler
    {

        final RecommendationsWidget this$0;

        public void errorReceived(int i, int j, String s)
        {
            FkLogger.debug("Reco", (new StringBuilder("response error ")).append(i).toString());
            removeView(loadingView);
            setVisibility(8);
            removeAllViews();
        }

        public volatile void resultReceived(Object obj, boolean flag)
        {
            resultReceived((Map)obj, flag);
        }

        public void resultReceived(Map map, boolean flag)
        {
            FkLogger.debug("Reco", (new StringBuilder("response ")).append(map).toString());
            removeView(loadingView);
            if (!StringUtils.isNullOrEmpty(map) && map.get("recommendations") != null)
            {
                horizontalScrollView.setVisibility(0);
                FkLogger.debug("Reco", "is not null");
                WidgetData widgetdata = (WidgetData)map.get("recommendations");
                FkLogger.debug("Reco", (new StringBuilder("widgetDataArray ")).append(widgetdata.getData()).toString());
                updateTitle(widgetdata.getHeader());
                if (!PmuWidgetBuilder.buildPmuWidget(context, onClickListener, FlipkartApplication.getImageLoader(), widgetdata.getData(), scrollLayout, true, null))
                {
                    setTitleGone();
                    return;
                } else
                {
                    requestLayout();
                    setVisibility(0);
                    return;
                }
            } else
            {
                setVisibility(8);
                setTitleGone();
                return;
            }
        }

        _cls1()
        {
            this$0 = RecommendationsWidget.this;
            super();
        }
    }

}
