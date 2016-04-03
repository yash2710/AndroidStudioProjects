// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.flipkart.android.DB.ComponentWidgetDataDao;
import com.flipkart.android.DB.ComponentWidgetLayoutDao;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.SearchMode;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.ConfigHelper;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.ScrollViewFixed;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.customwidget.FilterWidget;
import com.flipkart.android.customwidget.WidgetAction;
import com.flipkart.android.datahandler.AnalyticData.AnalyticData;
import com.flipkart.android.datahandler.ComponentDataDataHandler;
import com.flipkart.android.datahandler.ComponentLayoutDatahandler;
import com.flipkart.android.datahandler.param.CompoentParams;
import com.flipkart.android.datahandler.param.ComponentDataParams;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.appconfig.AppTheme;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.layout.LayoutContainer;
import com.flipkart.android.response.component.layout.LayoutDetails;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.FkProductListContext;
import com.flipkart.android.utils.PageTypeUtils;
import com.flipkart.android.utils.ScreenMathUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.component.ComponentUtils;
import com.flipkart.android.utils.component.ComponentWidgetUtils;
import com.flipkart.logging.FkLogger;
import java.util.ArrayList;
import net.simonvt.menudrawer.ColorDrawable;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, L, M, N

public class MultiWidgetFragment extends FlipkartBaseFragment
    implements android.view.View.OnClickListener
{

    public static final String FILL_ACTION_BAR = "MULTI_WIDGET_SCREEN_FILL_ACTION_BAR";
    public static final String SCREEN_ID = "MULTI_WIDGET_SCREEN_ID";
    public static final String SCREEN_NAME = "MULTI_WIDGET_SCREEN_NAME";
    public static final String TAG = "MultiWidget";
    String a;
    boolean b;
    int c;
    boolean d;
    boolean e;
    boolean f;
    View g;
    View h;
    LinearLayout i;
    LayoutContainer j;
    ComponentLayoutDatahandler k;
    ComponentDataDataHandler l;
    private boolean m;
    private LinearLayout n;
    private ScrollViewFixed o;
    private PageTypeUtils p;
    private boolean q;
    private ArrayList r;
    private boolean s;
    private int t;

    public MultiWidgetFragment()
    {
        m = true;
        b = false;
        c = 127;
        d = false;
        e = false;
        f = false;
        g = null;
        h = null;
        i = null;
        n = null;
        o = null;
        q = false;
        k = null;
        l = null;
        s = false;
        t = 0;
    }

    private boolean a()
    {
        if (j != null && activity != null)
        {
            LayoutDetails layoutdetails;
            if (((HomeFragmentHolderActivity)activity).getSupportActionBar() != null && !((HomeFragmentHolderActivity)activity).isFromKilledState)
            {
                if (((HomeFragmentHolderActivity)activity).getCurrentFragment() instanceof MultiWidgetFragment)
                {
                    if (a != null && (a.contains("homepage") || a.contains("flyout")))
                    {
                        ((HomeFragmentHolderActivity)activity).getSupportActionBar().setCustomView(ActionBarView.setActionBarCustomView(activity, ActionBarState.Home));
                    } else
                    {
                        ((HomeFragmentHolderActivity)activity).getSupportActionBar().setCustomView(ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back));
                    }
                }
            } else
            {
                ((HomeFragmentHolderActivity)activity).isFromKilledState = false;
                ((HomeFragmentHolderActivity)activity).getSupportActionBar().setCustomView(ActionBarView.setActionBarCustomView(activity, ((HomeFragmentHolderActivity)activity).getActionBarState()));
            }
            layoutdetails = j.getLayoutDetails();
            if (layoutdetails != null && !a.contains("flyout") && m && isVisible())
            {
                if (j.getLayoutDetails().getTheme().equalsIgnoreCase("dark") && !j.getLayoutDetails().isFillActionBar())
                {
                    c = 0;
                } else
                {
                    c = 127;
                    FkLogger.debug("ActionBarTest", (new StringBuilder("xtheme is light  theme is ")).append(c).toString());
                }
                if (!((HomeFragmentHolderActivity)activity).isDrawerOpen())
                {
                    ((HomeFragmentHolderActivity)activity).setActionBarBgAlpha(c);
                } else
                {
                    ((HomeFragmentHolderActivity)activity).setActionBarMinAlpha(c);
                }
                FkLogger.debug("Test", (new StringBuilder("init alpha with ")).append(c).toString());
            }
            if (layoutdetails != null && layoutdetails.getTheme() != null && layoutdetails.getTheme().equalsIgnoreCase("dark"))
            {
                s = true;
            } else
            {
                s = false;
            }
            r = ComponentUtils.fetchDataIdsFromLayout(j.getChildren(), a);
            FkLogger.debug("Test", (new StringBuilder("DataIds count is ")).append(r.size()).toString());
            b = ComponentUtils.checkIfFullScreenIsTrue(j.getChildren());
            d = ComponentUtils.isSearchWidgetPresent(j.getChildren());
            FkLogger.debug("Crash", (new StringBuilder("Screen id is ")).append(a).append(" view is type ful view ").append(b).toString());
            if (b)
            {
                o.setVisibility(8);
                n.setVisibility(0);
                i = n;
            } else
            {
                o.setVisibility(0);
                n.setVisibility(8);
                i = (LinearLayout)o.findViewById(0x7f0a0088);
            }
            FkLogger.debug("Crash", (new StringBuilder("container view is  ")).append(i).toString());
            ComponentWidgetUtils.setWidgetBackground(j.getLayoutDetails(), g);
            if (i != null)
            {
                if (j.getLayoutDetails() != null && j.getLayoutDetails().isFillActionBar() && m)
                {
                    i.setPadding(0, ComponentWidgetUtils.getActionBarHeight(activity), 0, 0);
                } else
                {
                    i.setPadding(0, 0, 0, 0);
                }
            }
            return b();
        } else
        {
            return false;
        }
    }

    static boolean a(MultiWidgetFragment multiwidgetfragment)
    {
        return multiwidgetfragment.a();
    }

    static void b(MultiWidgetFragment multiwidgetfragment)
    {
        multiwidgetfragment.c();
    }

    private boolean b()
    {
        if (r.size() <= 0)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        FkLogger.debug("Perf", "Data handler called from genData");
        l.getComponentData((ComponentDataParams[])r.toArray(new ComponentDataParams[r.size()]), null, null);
        return true;
        Exception exception;
        exception;
        return false;
    }

    private void c()
    {
        try
        {
            View view = g.findViewById(0x7f0a00d6);
            ((ViewGroup)g).removeView(view);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    static void c(MultiWidgetFragment multiwidgetfragment)
    {
        try
        {
            if (((HomeFragmentHolderActivity)multiwidgetfragment.activity).getSupportFragmentManager().getBackStackEntryCount() == 0)
            {
                if (FlipkartPreferenceManager.instance().getAppLaunchCounts() <= AppConfigUtils.getInstance().getPullOutShowCount() && FlipkartPreferenceManager.instance().isShowPullOUt().booleanValue())
                {
                    ((HomeFragmentHolderActivity)multiwidgetfragment.activity).openDrawerLayout();
                    FlipkartPreferenceManager.instance().saveIsShowPullOut(false);
                }
                if (FlipkartPreferenceManager.instance().isFirstTimeHomepageLoad().booleanValue() && multiwidgetfragment.activity != null && ((HomeFragmentHolderActivity)multiwidgetfragment.activity).getcurrentFragment() == multiwidgetfragment)
                {
                    FlipkartPreferenceManager.instance().saveFirstTimeHomepageLoad(Boolean.valueOf(false));
                    multiwidgetfragment.loadCueTips(0x7f030050);
                }
            }
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    static boolean d(MultiWidgetFragment multiwidgetfragment)
    {
        return multiwidgetfragment.s;
    }

    public PageTypeUtils getPageType()
    {
        return p;
    }

    public boolean handleBackPress()
    {
        super.handleBackPress();
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onClick(View view)
    {
        if (q) goto _L2; else goto _L1
_L1:
        q = true;
        if (view.getTag() != null && (view.getTag() instanceof Action))
        {
            q = WidgetAction.performAction((Action)view.getTag(), activity, PageTypeUtils.HomePage);
        }
        if (view.getTag() == null || !(view.getTag() instanceof String)) goto _L4; else goto _L3
_L3:
        String s1 = (String)view.getTag();
        if (!s1.contains("open_search_page")) goto _L6; else goto _L5
_L5:
        if (s1.contains("upfront_search"))
        {
            TrackingHelper.sendSearchMode(SearchMode.UpFrontSearch);
        } else
        {
            TrackingHelper.sendSearchMode(SearchMode.CLP);
        }
        ((HomeFragmentHolderActivity)activity).openSearchPage();
        q = false;
_L13:
        if (a.equals("flyout"))
        {
            q = false;
        }
_L2:
        return;
_L6:
        if (!s1.contains("try_again")) goto _L8; else goto _L7
_L7:
        c();
        h.setVisibility(0);
        FkLogger.debug("Perf", "make layout call fetchmetadata");
        if (j != null) goto _L10; else goto _L9
_L9:
        k.getComponentLayoutInfo(new CompoentParams(a, null), "", "");
_L11:
        q = false;
        continue; /* Loop/switch isn't completed */
_L10:
        try
        {
            a();
        }
        catch (Exception exception) { }
        if (true) goto _L11; else goto _L8
_L8:
        if (o != null)
        {
            View view1 = o.findViewWithTag(s1);
            if (view1 != null)
            {
                if (view1.getTop() != 0)
                {
                    o.scrollTo(0, view1.getTop() - ScreenMathUtils.dpToPx(40, activity.getApplicationContext()));
                } else
                {
                    o.scrollTo(0, view1.getTop());
                }
                q = false;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        q = false;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        g = layoutinflater.inflate(0x7f030024, viewgroup, false);
        if (AppConfigUtils.getInstance().getAppTheme() != null)
        {
            LayoutDetails layoutdetails = AppConfigUtils.getInstance().getAppTheme().getMultiWidgetFragment();
            if (layoutdetails != null && !StringUtils.isNullOrEmpty(layoutdetails.getBackgroundColor()))
            {
                g.setBackgroundDrawable(new ColorDrawable(Color.parseColor(layoutdetails.getBackgroundColor())));
            }
        }
        if (j == null)
        {
            Bundle bundle1 = getArguments();
            if (bundle1 != null)
            {
                a = bundle1.getString("MULTI_WIDGET_SCREEN_NAME");
                m = bundle1.getBoolean("MULTI_WIDGET_SCREEN_FILL_ACTION_BAR", true);
                String s1 = bundle1.getString("MULTI_WIDGET_SCREEN_ID");
                if (!StringUtils.isNullOrEmpty(s1))
                {
                    a = (new StringBuilder()).append(a).append("/").append(s1).toString();
                }
                FkLogger.debug("MultiWidget", (new StringBuilder("screen name is ")).append(a).toString());
                bundle1.getString("REQUEST_ID");
                bundle1.getString("OMNITURE_DATA");
            } else
            {
                FkLogger.debug("MultiWidget", "extras are null");
            }
        }
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return g;
        }
        long l1 = FlipkartPreferenceManager.instance().getTTLSavedTime();
        if (l1 != 0L && FlipkartPreferenceManager.instance().getTTLTime() - (System.currentTimeMillis() - l1) <= 0L)
        {
            (new ComponentWidgetDataDao(FlipkartApplication.getAppContext())).deleteAll();
            (new ComponentWidgetLayoutDao(FlipkartApplication.getAppContext())).deleteAll();
            (new ConfigHelper(FlipkartApplication.getAppContext())).readConfig();
        }
        if (a.startsWith("clp"))
        {
            p = PageTypeUtils.CLP;
            TrackingHelper.sendCLPPage("");
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.CLP);
        } else
        if (a.startsWith("homepage"))
        {
            p = PageTypeUtils.HomePage;
            TrackingHelper.sendHomePage();
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.HomePage);
        } else
        if (a.startsWith("storefront"))
        {
            p = PageTypeUtils.StoreFront;
            TrackingHelper.sendStoreFrontPage(null);
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.StoreFront);
        } else
        if (a.startsWith("foz"))
        {
            p = PageTypeUtils.Foz;
            TrackingHelper.sendFoz(a);
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.Foz);
        } else
        if (a.startsWith("flyout"))
        {
            p = PageTypeUtils.Flyout;
        } else
        {
            p = PageTypeUtils.None;
            FlipkartPreferenceManager.instance().saveLastPageType(PageTypeUtils.None);
        }
        if (((HomeFragmentHolderActivity)activity).getcurrentFragment() == this && isVisible())
        {
            ((HomeFragmentHolderActivity)activity).setActionBarBgAlpha(c);
        }
        Activity _tmp = activity;
        HomeFragmentHolderActivity.isUpFrontSearchPresent = false;
        h = g.findViewById(0x7f0a008b);
        h.setVisibility(0);
        o = (ScrollViewFixed)g.findViewById(0x7f0a0087);
        g.findViewById(0x7f0a0086);
        n = (LinearLayout)g.findViewById(0x7f0a0089);
        if (o != null)
        {
            o.setOnScrollChangedListener(new L(this));
        }
        k = new M(this);
        l = new N(this);
        FkLogger.debug("MultiWidget", "init datahandler");
        if (j != null)
        {
            a();
        }
        k.getComponentLayoutInfo(new CompoentParams(a, null), "", "");
        return g;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        if (activity != null)
        {
            ((HomeFragmentHolderActivity)activity).closeRefreshing();
        }
        analyticData.setPageTypeUtils(FlipkartPreferenceManager.instance().getLastPageTypeInPageTypeUtil());
        isRefreshing = false;
        if (k != null)
        {
            k.cancelRequests();
            k = null;
        }
        if (l != null)
        {
            l.cancelRequests();
            l = null;
        }
        ScreenMathUtils.unbindDrawables(g);
        FlipkartApplication.setShowBallonAnimation(false);
        g = null;
    }

    public void refreshFilterWidget(FkProductListContext fkproductlistcontext, String s1)
    {
        if (g != null)
        {
            View view = g.findViewById(FilterWidget.Id);
            if (view != null && (view instanceof FilterWidget))
            {
                ((FilterWidget)view).refreshView(fkproductlistcontext, s1);
            }
        }
    }

    public void refreshPage()
    {
        if (activity != null && (activity instanceof HomeFragmentHolderActivity))
        {
            ((HomeFragmentHolderActivity)activity).initRefreshing();
        }
        try
        {
            k.getComponentLayoutInfo(new CompoentParams(a, null), "", "");
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public void setCurrentPosition()
    {
        if (o != null)
        {
            t = o.getScrollY();
        }
    }

    public void setScrollPosition()
    {
        if (o != null)
        {
            o.smoothScrollTo(0, t);
        }
    }

    public void tabViewScrollChanged(int i1)
    {
        if (i1 != 0)
        {
            if (!e)
            {
                e = true;
            }
            return;
        }
        if (e && f)
        {
            a();
            f = false;
        }
        e = false;
    }
}
