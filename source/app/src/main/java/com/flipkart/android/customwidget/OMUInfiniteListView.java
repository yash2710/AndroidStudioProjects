// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.flipkart.android.datahandler.InfiniteProductListDataHandler;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.OmuProdData;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.HeaderValue;
import com.flipkart.android.response.component.data.customvalues.OMUImageValue;
import com.flipkart.android.response.component.data.customvalues.OMUValue;
import com.flipkart.android.response.component.data.customvalues.TitleValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.OmuListCache;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.TabContextCache;
import com.flipkart.android.utils.component.ComponentWidgetUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import java.util.Iterator;
import net.simonvt.menudrawer.ColorDrawable;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget, BucketWidget, ProductWidget, ActionPerformer, 
//            WidgetAction, NewTitleWidget

public class OMUInfiniteListView extends BaseWidget
    implements android.view.View.OnClickListener
{

    public static final String screenType = "infiniteScroll";
    private Activity activity;
    private ArrayList bannerIndexList;
    public String baseURL;
    private int currentDataListIndex;
    ArrayList dataList;
    View headerTitleView;
    private ImageLoader imageDownloader;
    private boolean isNoMoreDataToDownload;
    protected boolean isRefreshing;
    private LayoutDetails layoutDetails;
    ListAdapterInfinite listAdapterInfinite;
    InfiniteProductListDataHandler listDataHandler;
    private RelativeLayout listFooterViewLayout;
    private RelativeLayout listHeaderViewLayout;
    ListOnScrollListener listOnScrollListener;
    ListView listView;
    Context mContext;
    private OmuListCache omuListCache;
    private android.view.View.OnClickListener onClickListener;
    private int prodC;
    ProgressBar progressBar;
    private int rowCount;
    private int screenWidth;
    private int screenheight;
    private ScrollListListener scrollListListener;
    private BaseWidget.WidgetTheme theme;
    private String title;
    ArrayList widgetItems;

    public OMUInfiniteListView(Context context, ArrayList arraylist, android.view.View.OnClickListener onclicklistener, ImageLoader imageloader, Activity activity1, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, 
            WidgetItem widgetitem, ScrollListListener scrolllistlistener, int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity1, i);
        isRefreshing = false;
        dataList = new ArrayList();
        currentDataListIndex = 0;
        rowCount = 0;
        isNoMoreDataToDownload = false;
        baseURL = "CATEGORY";
        screenWidth = 0;
        screenheight = 0;
        prodC = 0;
        mContext = context;
        widgetItems = arraylist;
        imageDownloader = imageloader;
        scrollListListener = scrolllistlistener;
        activity = activity1;
        theme = widgettheme;
        onClickListener = onclicklistener;
        layoutDetails = layoutdetails;
        title = ((HeaderValue)widgetitem.getValue()).getTitleValue().getText();
        setOrientation(1);
        getBaseURL(widgetitem);
        renderTitle(widgetitem);
        omuListCache = (OmuListCache)TabContextCache.getInstance().getResponse(baseURL);
        if (omuListCache == null)
        {
            omuListCache = new OmuListCache();
        }
        initialiseViews();
    }

    private void generateDataList()
    {
        if (omuListCache != null && omuListCache.getDataList().size() > 0)
        {
            dataList = new ArrayList(omuListCache.getDataList());
            currentDataListIndex = dataList.size();
            updateRowCountIfFromCache();
            refreshListView();
            listView.setSelectionFromTop(omuListCache.getCurrentDataListIndex(), omuListCache.getDy());
        } else
        if (widgetItems != null && widgetItems.size() > 0)
        {
            int i;
            for (i = 0; i < widgetItems.size(); i++)
            {
                WidgetItem widgetitem = (WidgetItem)widgetItems.get(i);
                if (widgetitem == null)
                {
                    continue;
                }
                OMUValue omuvalue = (OMUValue)widgetitem.getValue();
                Action action = widgetitem.getAction();
                if (omuvalue != null)
                {
                    dataList.add(i, new OmuProdData(omuvalue, action));
                }
            }

            updateRowCount(i);
            currentDataListIndex = i;
            return;
        }
    }

    private View getBannerView(int i, View view)
    {
        BucketWidget bucketwidget = (BucketWidget)view;
        if (bucketwidget == null)
        {
            BucketWidget bucketwidget1 = new BucketWidget(mContext, ((OmuProdData)dataList.get(i)).getValue());
            bucketwidget1.setTag(((OmuProdData)dataList.get(i)).getAction());
            bucketwidget1.setBackgroundDrawable(new ColorDrawable(-1));
            bucketwidget1.setLayoutParams(new android.widget.AbsListView.LayoutParams(screenWidth, (int)(0.39000000000000001D * (double)screenWidth)));
            bucketwidget1.setOnClickListener(this);
            return bucketwidget1;
        }
        NetworkImageView networkimageview = (NetworkImageView)view.findViewById(0x7f0a0142);
        OMUImageValue omuimagevalue = ((OmuProdData)dataList.get(i)).getValue().getPrimaryImage();
        if (omuimagevalue != null)
        {
            String s = omuimagevalue.fetchUrl(screenDpi);
            if (s != null)
            {
                networkimageview.setImageUrl(s, FlipkartApplication.getImageLoader());
                networkimageview.setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
                return bucketwidget;
            } else
            {
                networkimageview.setVisibility(8);
                return bucketwidget;
            }
        } else
        {
            networkimageview.setVisibility(8);
            return bucketwidget;
        }
    }

    private int getCurrentDataListIndex(int i)
    {
        int j = getBannerCount(i);
        return j + (i - j << 1);
    }

    private View getProductWidget(int i)
    {
        OMUValue omuvalue = ((OmuProdData)dataList.get(i)).getValue();
        ProductWidget productwidget = new ProductWidget(activity, mContext, omuvalue, imageDownloader, "vertical");
        productwidget.setTag(((OmuProdData)dataList.get(i)).getAction());
        productwidget.setContentDescription((new StringBuilder("Product_")).append(i).toString());
        productwidget.setOnClickListener(this);
        return productwidget;
    }

    private View getTwoView(int i, View view)
    {
        LinearLayout linearlayout = (LinearLayout)view;
        OMUValue omuvalue = ((OmuProdData)dataList.get(i)).getValue();
        int j = dataList.size();
        int k = i + 1;
        OMUValue omuvalue1 = null;
        if (j > k)
        {
            omuvalue1 = ((OmuProdData)dataList.get(i + 1)).getValue();
        }
        if (linearlayout == null)
        {
            LinearLayout linearlayout1 = new LinearLayout(mContext);
            if (omuvalue != null)
            {
                linearlayout1.setOrientation(0);
                if (!omuvalue.isBanner())
                {
                    linearlayout1.addView(getProductWidget(i));
                }
            }
            if (omuvalue1 != null)
            {
                if (!omuvalue1.isBanner())
                {
                    linearlayout1.addView(getProductWidget(i + 1));
                }
                return linearlayout1;
            } else
            {
                View view1 = getProductWidget(i);
                view1.setVisibility(8);
                linearlayout1.addView(view1);
                return linearlayout1;
            }
        }
        updateRecycledView((ProductWidget)linearlayout.getChildAt(0), i);
        ProductWidget productwidget = (ProductWidget)linearlayout.getChildAt(1);
        if (i + 1 == dataList.size())
        {
            productwidget.setVisibility(8);
            return linearlayout;
        } else
        {
            updateRecycledView(productwidget, i + 1);
            return linearlayout;
        }
    }

    private void initDataHandler()
    {
        listDataHandler = new _cls1();
    }

    private void initialiseViews()
    {
        screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        screenheight = mContext.getResources().getDisplayMetrics().heightPixels;
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        bannerIndexList = new ArrayList();
        listView = new ListView(mContext);
        listFooterViewLayout = new RelativeLayout(mContext);
        listFooterViewLayout.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
        if (listHeaderViewLayout != null)
        {
            listHeaderViewLayout = new RelativeLayout(mContext);
            setWidgetBackground(layoutDetails, listHeaderViewLayout);
        }
        listHeaderViewLayout.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
        progressBar = new ProgressBar(mContext);
        progressBar.setLayoutParams(new android.widget.AbsListView.LayoutParams(-1, -2));
        progressBar.setVisibility(8);
        listFooterViewLayout.addView(progressBar);
        setBackgroundColor(mContext.getResources().getColor(0x7f090070));
        if (headerTitleView != null)
        {
            listHeaderViewLayout.addView(headerTitleView);
        }
        listView.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        listAdapterInfinite = new ListAdapterInfinite(null);
        listOnScrollListener = new ListOnScrollListener(null);
        listView.setOnScrollListener(listOnScrollListener);
        if (listFooterViewLayout != null)
        {
            listView.addFooterView(listFooterViewLayout);
        }
        if (listHeaderViewLayout != null)
        {
            listView.addHeaderView(listHeaderViewLayout);
        }
        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(null);
        listView.setAdapter(listAdapterInfinite);
        generateDataList();
        initDataHandler();
        addView(listView);
    }

    private void refreshListView()
    {
        if (listAdapterInfinite != null)
        {
            listAdapterInfinite.notifyDataSetChanged();
        }
        isRefreshing = false;
    }

    private void savePosition()
    {
        int i = listView.getFirstVisiblePosition();
        View view = listView.getChildAt(0);
        int j = 0;
        if (view != null)
        {
            j = view.getTop();
        }
        omuListCache.setCurrentDataListIndex(i);
        omuListCache.setDy(j);
    }

    private void triggerRequest()
    {
        isRefreshing = true;
        progressBar.setVisibility(0);
        int i = 10 - currentDataListIndex % 10;
        if (listDataHandler != null)
        {
            listDataHandler.getNextSet(currentDataListIndex, i, baseURL);
        }
    }

    private void updateRecycledView(ProductWidget productwidget, int i)
    {
        OMUValue omuvalue = ((OmuProdData)dataList.get(i)).getValue();
        productwidget.setVisibility(0);
        productwidget.setTag(((OmuProdData)dataList.get(i)).getAction());
        productwidget.setContentDescription((new StringBuilder("Product_")).append(i).toString());
        productwidget.updateRecycledProductWidget(omuvalue);
    }

    private void updateRowCount(int i)
    {
        int j = i + currentDataListIndex;
        int k = currentDataListIndex;
        while (k < j) 
        {
            OMUValue omuvalue = ((OmuProdData)dataList.get(k)).getValue();
            if (omuvalue != null)
            {
                if (omuvalue.isBanner())
                {
                    omuvalue.setBanner(true);
                    rowCount = 1 + rowCount;
                    bannerIndexList.add(Integer.valueOf(-1 + rowCount));
                } else
                {
                    prodC = 1 + prodC;
                    if (prodC == 2 || k == j - 1)
                    {
                        rowCount = 1 + rowCount;
                        prodC = 0;
                    }
                }
            }
            k++;
        }
    }

    private void updateRowCountIfFromCache()
    {
        rowCount = 0;
        int i = 0;
        int j = 0;
        while (i < dataList.size()) 
        {
            if (((OmuProdData)dataList.get(i)).getValue().isBanner())
            {
                rowCount = 1 + rowCount;
                bannerIndexList.add(Integer.valueOf(-1 + rowCount));
            } else
            if (++j == 2 || i == -1 + dataList.size())
            {
                rowCount = 1 + rowCount;
                j = 0;
            }
            i++;
        }
    }

    int getBannerCount(int i)
    {
        Iterator iterator = bannerIndexList.iterator();
        int j;
        for (j = 0; iterator.hasNext() && i > ((Integer)iterator.next()).intValue(); j++) { }
        return j;
    }

    public void getBaseURL(WidgetItem widgetitem)
    {
        if (widgetitem != null)
        {
            baseURL = ActionPerformer.fetchString(widgetitem.getAction().getParams(), "dataPath");
        }
    }

    public boolean isNoMoreDataToDownload()
    {
        return isNoMoreDataToDownload;
    }

    public void onClick(View view)
    {
        if (view.getTag() != null && (view.getTag() instanceof Action))
        {
            savePosition();
            omuListCache.setDataList(dataList);
            TabContextCache.getInstance().putResponse(baseURL, omuListCache);
            WidgetAction.performAction((Action)view.getTag(), activity, PageTypeUtils.ProductPage);
        }
    }

    protected void renderTitle(WidgetItem widgetitem)
    {
label0:
        {
            if (widgetitem != null)
            {
                FkLogger.debug("Title", (new StringBuilder("render title widget ")).append(getClass().toString()).toString());
                TitleValue titlevalue = ComponentWidgetUtils.fetchTitleValue((HeaderValue)widgetitem.getValue());
                if (titlevalue == null)
                {
                    break label0;
                }
                renderTitle(widgetitem, titlevalue.getText());
            }
            return;
        }
        renderTitle(widgetitem, "");
    }

    protected void renderTitle(WidgetItem widgetitem, String s)
    {
        FkLogger.debug("Title", (new StringBuilder("render title widget ")).append(getClass().toString()).append("title text is ").append(s).toString());
        View view = getChildAt(0);
        if (view instanceof NewTitleWidget)
        {
            FkLogger.debug("Title", "titleWidget is there...");
            NewTitleWidget newtitlewidget = (NewTitleWidget)view;
            ComponentWidgetUtils.buildTitleWidget(newtitlewidget, getContext(), (HeaderValue)widgetitem.getValue(), widgetitem.getAction(), theme, activity);
            newtitlewidget.setOnClickListener(onClickListener);
        } else
        {
            View view1 = ComponentWidgetUtils.buildTitleWidget(null, getContext(), (HeaderValue)widgetitem.getValue(), widgetitem.getAction(), theme, activity);
            FkLogger.debug("Title", "titleWidget is not there...");
            if (view1 != null)
            {
                view1.setOnClickListener(onClickListener);
                view1.setTag(widgetitem.getAction());
                headerTitleView = view1;
                return;
            }
        }
    }

    public void resetListViewParams(String s)
    {
        FkLogger.debug("Title", (new StringBuilder("List view Base Url is ")).append(s).toString());
        baseURL = s;
        rowCount = 0;
        currentDataListIndex = 0;
        dataList.clear();
        isRefreshing = true;
        progressBar.setVisibility(0);
        omuListCache.getDataList().clear();
        TabContextCache.getInstance().clearCache();
        if (listAdapterInfinite != null)
        {
            listAdapterInfinite.notifyDataSetChanged();
        }
        listDataHandler.getNextSet(currentDataListIndex, 10, baseURL);
        requestLayout();
    }

    public void setNoMoreDataToDownload(boolean flag)
    {
        isNoMoreDataToDownload = flag;
    }

    protected void setWidgetBackground(LayoutDetails layoutdetails, View view)
    {
        listHeaderViewLayout = new RelativeLayout(context);
        ComponentWidgetUtils.setWidgetBackground(layoutdetails, listHeaderViewLayout);
    }

    protected void setWidgetPadding(LayoutDetails layoutdetails, View view)
    {
        if (headerTitleView != null)
        {
            ComponentWidgetUtils.setWidgetPadding(layoutdetails, headerTitleView);
        }
    }





/*
    static int access$212(OMUInfiniteListView omuinfinitelistview, int i)
    {
        int j = i + omuinfinitelistview.currentDataListIndex;
        omuinfinitelistview.currentDataListIndex = j;
        return j;
    }

*/




/*
    static boolean access$502(OMUInfiniteListView omuinfinitelistview, boolean flag)
    {
        omuinfinitelistview.isNoMoreDataToDownload = flag;
        return flag;
    }

*/





    private class _cls1 extends InfiniteProductListDataHandler
    {

        final OMUInfiniteListView this$0;

        public void errorReceived(int i, int j, String s)
        {
            progressBar.setVisibility(8);
            isRefreshing = false;
            isNoMoreDataToDownload = true;
            if (rowCount == 0)
            {
                CustomDialog.showErrorMessage(i, j, s, activity);
            }
        }

        public volatile void resultReceived(Object obj, boolean flag)
        {
            resultReceived((Map)obj, flag);
        }

        public void resultReceived(Map map, boolean flag)
        {
            FkLogger.debug("Test", (new StringBuilder("Result received for component data req ")).append(map).toString());
            if (map != null && map.size() != 0)
            {
                WidgetData widgetdata = (WidgetData)((java.util.Map.Entry)map.entrySet().iterator().next()).getValue();
                if (widgetdata != null && widgetdata.getData() != null && widgetdata.getData().size() > 0)
                {
                    int i = widgetdata.getData().size();
                    for (int j = 0; j < i; j++)
                    {
                        OMUValue omuvalue = (OMUValue)((WidgetItem)widgetdata.getData().get(j)).getValue();
                        Action action = ((WidgetItem)widgetdata.getData().get(j)).getAction();
                        dataList.add(new OmuProdData(omuvalue, action));
                    }

                    if (10 - currentDataListIndex % 10 != i)
                    {
                        setNoMoreDataToDownload(true);
                    }
                    updateRowCount(i);
                    refreshListView();
                    int j = map + ((com.flipkart.logging) (this)).;
                }
                progressBar.setVisibility(8);
            }
        }

        _cls1()
        {
            this$0 = OMUInfiniteListView.this;
            super();
        }
    }


    private class ListAdapterInfinite extends BaseAdapter
    {

        final OMUInfiniteListView this$0;

        public int getCount()
        {
            return rowCount;
        }

        public Object getItem(int i)
        {
            return dataList.get(i);
        }

        public long getItemId(int i)
        {
            return (long)i;
        }

        public View getView(int i, View view, ViewGroup viewgroup)
        {
            View view1;
            int j;
            Exception exception;
            View view2;
            boolean flag;
            View view3;
            if (view == null)
            {
                view1 = new View(mContext);
            } else
            {
                view1 = view;
            }
            j = getCurrentDataListIndex(i);
            if (j >= dataList.size())
            {
                break MISSING_BLOCK_LABEL_130;
            }
            if (((OmuProdData)dataList.get(j)).getValue().isBanner())
            {
                break MISSING_BLOCK_LABEL_95;
            }
            flag = view instanceof BucketWidget;
            view3 = null;
            if (!flag)
            {
                view3 = view;
            }
            return getTwoView(j, view3);
            if (!(view instanceof BucketWidget))
            {
                view = null;
            }
            try
            {
                view2 = getBannerView(j, view);
            }
            // Misplaced declaration of an exception variable
            catch (Exception exception)
            {
                return view1;
            }
            return view2;
            return view1;
        }

        private ListAdapterInfinite()
        {
            this$0 = OMUInfiniteListView.this;
            super();
        }

        ListAdapterInfinite(_cls1 _pcls1)
        {
            this();
        }
    }


    private class ListOnScrollListener
        implements android.widget.AbsListView.OnScrollListener
    {

        boolean isDownAnimationDone;
        boolean isUpAnimationDone;
        final OMUInfiniteListView this$0;

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
            if (k != 0 && i + j > k - 2 && !isRefreshing && !isNoMoreDataToDownload())
            {
                triggerRequest();
            }
        }

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
        }

        private ListOnScrollListener()
        {
            this$0 = OMUInfiniteListView.this;
            super();
            isUpAnimationDone = false;
            isDownAnimationDone = false;
        }

        ListOnScrollListener(_cls1 _pcls1)
        {
            this();
        }
    }

}
