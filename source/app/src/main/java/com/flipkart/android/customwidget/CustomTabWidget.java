// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.customwidget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.customviews.ViewPagerFixed;
import com.flipkart.android.fragments.MultiWidgetFragment;
import com.flipkart.android.response.component.data.WidgetItem;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.data.customvalues.TabValue;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.FontCache;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.TabContextCache;
import com.viewpagerindicator.SmoothTabPageIndicator;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.customwidget:
//            BaseWidget

public class CustomTabWidget extends BaseWidget
{

    public static final String DEFAULT_TAB = "defaultTab";
    public static final String WIDGET_COMMON_NAME = "TAB";
    private final String CURRENT_TAB_POSITION = "currentTabPosition";
    MultiWidgetFragment aliveFragmentArray[];
    ArrayList bundleArrayList;
    SmoothTabPageIndicator customViewPagerTitleStrip;
    public int defaultTabPosition;
    private boolean isTabPositionAvailable;
    CustomViewPagerAdapter mAdapter;
    Context mContext;
    ViewPagerFixed mViewPager;
    public com.viewpagerindicator.SmoothTabPageIndicator.PageChangedListener pageChangedListener;
    private ArrayList pageTitleList;

    public CustomTabWidget(Context context, LayoutDetails layoutdetails, BaseWidget.WidgetTheme widgettheme, android.view.View.OnClickListener onclicklistener, WidgetItem widgetitem, Activity activity, ArrayList arraylist, 
            int i)
    {
        super(context, layoutdetails, widgettheme, onclicklistener, widgetitem, activity, i);
        mViewPager = null;
        pageTitleList = new ArrayList();
        isTabPositionAvailable = false;
        defaultTabPosition = 0;
        bundleArrayList = new ArrayList();
        checkAndBuildTabData(arraylist);
        if (pageTitleList.size() > 0)
        {
            mContext = context;
            initializeViews();
            mAdapter = new CustomViewPagerAdapter(((HomeFragmentHolderActivity)activity).getSupportFragmentManager(), pageTitleList);
            mViewPager.setAdapter(mAdapter);
            aliveFragmentArray = new MultiWidgetFragment[pageTitleList.size()];
            if (isTabPositionAvailable)
            {
                mViewPager.setCurrentItem(defaultTabPosition);
                TrackingHelper.sendTabWidgetChange((String)pageTitleList.get(defaultTabPosition));
            } else
            {
                int j;
                if (TabContextCache.getInstance().checkIfAvailable("currentTabPosition"))
                {
                    j = ((Integer)TabContextCache.getInstance().getResponse("currentTabPosition")).intValue();
                    TabContextCache.getInstance().putResponse("currentTabPosition", Integer.valueOf(j));
                } else
                {
                    j = 0;
                }
                mViewPager.setCurrentItem(j);
                TrackingHelper.sendTabWidgetChange((String)pageTitleList.get(j));
            }
            customViewPagerTitleStrip.setScrollOffset(ScreenMathUtils.dpToPx(60, context));
            customViewPagerTitleStrip.setViewPager(mViewPager);
        }
    }

    private void checkAndBuildTabData(ArrayList arraylist)
    {
        String s = (String)TabContextCache.getInstance().getResponse("defaultTab");
        if (arraylist != null && arraylist.size() > 0)
        {
            for (int i = 0; i < arraylist.size(); i++)
            {
                TabValue tabvalue = (TabValue)((WidgetItem)arraylist.get(i)).getValue();
                Action action = ((WidgetItem)arraylist.get(i)).getAction();
                if (tabvalue == null || action == null)
                {
                    continue;
                }
                String s1 = tabvalue.getDisplayText();
                String s2 = tabvalue.getTabKey();
                Map map = action.getParams();
                String s3 = "";
                String s4 = "";
                if (map != null)
                {
                    s3 = fetchString(map, "screenName");
                    s4 = fetchString(map, "pageKey");
                }
                if (StringUtils.isNullOrEmpty(s1) || StringUtils.isNullOrEmpty(s2) || StringUtils.isNullOrEmpty(s3) && StringUtils.isNullOrEmpty(s4))
                {
                    continue;
                }
                Bundle bundle = new Bundle();
                bundle.putString("OMNITURE_DATA", null);
                bundle.putString("MULTI_WIDGET_SCREEN_NAME", s3);
                bundle.putString("MULTI_WIDGET_SCREEN_ID", s4);
                bundle.putBoolean("MULTI_WIDGET_SCREEN_FILL_ACTION_BAR", false);
                bundleArrayList.add(bundle);
                if (!StringUtils.isNullOrEmpty(s) && s.equalsIgnoreCase(s2))
                {
                    defaultTabPosition = pageTitleList.size();
                    isTabPositionAvailable = true;
                    TabContextCache.getInstance().putResponse("currentTabPosition", Integer.valueOf(defaultTabPosition));
                }
                pageTitleList.add(s1);
            }

        }
    }

    private static String fetchString(Map map, String s)
    {
        String s1 = "";
        if (map != null && !StringUtils.isNullOrEmpty(s))
        {
            s1 = (String)map.get(s);
        }
        return s1;
    }

    private void initializeViews()
    {
        setOrientation(1);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-2, -2));
        pageChangedListener = new _cls1();
        customViewPagerTitleStrip = new SmoothTabPageIndicator(mContext, pageChangedListener);
        customViewPagerTitleStrip.setAllCaps(false);
        customViewPagerTitleStrip.setShouldExpand(true);
        customViewPagerTitleStrip.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, ScreenMathUtils.dpToPx(48, mContext)));
        customViewPagerTitleStrip.setForegroundGravity(17);
        customViewPagerTitleStrip.setTextSize(ScreenMathUtils.dpToPx(14, mContext));
        customViewPagerTitleStrip.setIndicatorColor(mContext.getResources().getColor(0x7f090052));
        customViewPagerTitleStrip.setIndicatorHeight(ScreenMathUtils.dpToPx(7, mContext));
        customViewPagerTitleStrip.setDividerColor(mContext.getResources().getColor(0x7f090051));
        customViewPagerTitleStrip.setTextColor(mContext.getResources().getColor(0x7f090054));
        customViewPagerTitleStrip.setCyclicEnabeled(false);
        android.graphics.Typeface typeface = FontCache.getFont("RobotoCondensed-Bold.ttf");
        customViewPagerTitleStrip.setTypeface(typeface, 1);
        customViewPagerTitleStrip.setBackgroundColor(mContext.getResources().getColor(0x7f090070));
        addView(customViewPagerTitleStrip);
        mViewPager = new ViewPagerFixed(mContext);
        mViewPager.setBackgroundColor(mContext.getResources().getColor(0x7f090070));
        mViewPager.setId(0x6eba7);
        mViewPager.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        addView(mViewPager);
    }


    private class CustomViewPagerAdapter extends FragmentStatePagerAdapter
    {

        ArrayList pageTitleList;
        final CustomTabWidget this$0;

        public void destroyItem(ViewGroup viewgroup, int i, Object obj)
        {
            super.destroyItem(viewgroup, i, obj);
            if (aliveFragmentArray != null)
            {
                aliveFragmentArray[i] = null;
            }
        }

        public int getCount()
        {
            return pageTitleList.size();
        }

        public Fragment getItem(int i)
        {
            MultiWidgetFragment multiwidgetfragment = new MultiWidgetFragment();
            multiwidgetfragment.setArguments((Bundle)bundleArrayList.get(i));
            if (aliveFragmentArray != null)
            {
                aliveFragmentArray[i] = multiwidgetfragment;
            }
            return multiwidgetfragment;
        }

        public CharSequence getPageTitle(int i)
        {
            return (CharSequence)pageTitleList.get(i);
        }

        public CustomViewPagerAdapter(FragmentManager fragmentmanager, ArrayList arraylist)
        {
            this$0 = CustomTabWidget.this;
            super(fragmentmanager);
            pageTitleList = arraylist;
        }
    }


    private class _cls1
        implements com.viewpagerindicator.SmoothTabPageIndicator.PageChangedListener
    {

        final CustomTabWidget this$0;

        public void pageChanged(int i)
        {
            TabContextCache.getInstance().putResponse("currentTabPosition", Integer.valueOf(i));
            TrackingHelper.sendTabWidgetChange((String)pageTitleList.get(i));
            if (aliveFragmentArray != null && i < aliveFragmentArray.length && aliveFragmentArray[i] != null)
            {
                aliveFragmentArray[i].refreshPage();
            }
        }

        public void pageStateChanged(int i)
        {
            MultiWidgetFragment amultiwidgetfragment[];
            int j;
            int k;
            MultiWidgetFragment multiwidgetfragment;
            try
            {
                amultiwidgetfragment = aliveFragmentArray;
                j = amultiwidgetfragment.length;
            }
            catch (Exception exception)
            {
                break; /* Loop/switch isn't completed */
            }
            k = 0;
_L2:
            if (k >= j)
            {
                break; /* Loop/switch isn't completed */
            }
            multiwidgetfragment = amultiwidgetfragment[k];
            if (multiwidgetfragment == null)
            {
                break MISSING_BLOCK_LABEL_39;
            }
            multiwidgetfragment.tabViewScrollChanged(i);
            k++;
            continue; /* Loop/switch isn't completed */
            if (true) goto _L2; else goto _L1
_L1:
        }

        _cls1()
        {
            this$0 = CustomTabWidget.this;
            super();
        }
    }

}
