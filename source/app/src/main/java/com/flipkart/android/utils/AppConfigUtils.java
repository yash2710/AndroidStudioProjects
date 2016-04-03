// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils;

import android.content.Context;
import android.content.Intent;
import com.flipkart.android.activity.BlockActivity;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.response.PopUpResponse;
import com.flipkart.android.response.appconfig.AppRateData;
import com.flipkart.android.response.appconfig.AppTheme;
import com.flipkart.android.response.appconfig.AppUpgradeData;
import com.flipkart.android.response.appconfig.ConfigResponseData;
import com.flipkart.android.response.appconfig.DbTimeout;
import com.flipkart.android.response.appconfig.FBFData;
import com.flipkart.android.response.appconfig.LoggingTimeout;
import com.flipkart.android.response.appconfig.ServiceProfileData;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.response.component.layout.LayoutDetails;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.utils:
//            DDayParams

public class AppConfigUtils
{

    private static AppConfigUtils b = null;
    private static Object c = new Object();
    private Map A;
    private boolean B;
    private boolean C;
    private long D;
    private long E;
    private int F;
    private AppTheme G;
    private DDayParams H;
    private LayoutDetails I;
    private LayoutDetails J;
    private LayoutDetails K;
    private boolean L;
    private Action M;
    private Action N;
    private PopUpResponse O;
    private Map P;
    private boolean Q;
    private ConfigResponseData a;
    private long d;
    private long e;
    private long f;
    private long g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private String l;
    private Long m;
    private String n;
    private String o;
    private String p;
    private boolean q;
    private int r;
    private FBFData s;
    private boolean t;
    private boolean u;
    private Map v;
    private AppUpgradeData w;
    private AppRateData x;
    private ArrayList y;
    private ArrayList z;

    public AppConfigUtils()
    {
        a = null;
    }

    public static AppConfigUtils getInstance()
    {
        synchronized (c)
        {
            if (b == null)
            {
                b = new AppConfigUtils();
            }
        }
        return b;
    }

    public Map getActioToUrlMap()
    {
        return A;
    }

    public LayoutDetails getActionBarTheme()
    {
        return I;
    }

    public AppRateData getAppRateData()
    {
        return x;
    }

    public AppTheme getAppTheme()
    {
        return G;
    }

    public AppUpgradeData getAppUpgradeData()
    {
        return w;
    }

    public LayoutDetails getBrowsePageTheme()
    {
        return J;
    }

    public boolean getEnableBatch()
    {
        return Q;
    }

    public DDayParams getEnableDDayConfig()
    {
        return H;
    }

    public String getFacebookShareAppUrl()
    {
        return n;
    }

    public FBFData getFbfData()
    {
        return s;
    }

    public int getFetchNewProductsCount()
    {
        return h;
    }

    public Map getFilterImagesMap()
    {
        return v;
    }

    public Action getHomeWidgetAction()
    {
        return M;
    }

    public String getLocationSource()
    {
        return p;
    }

    public long getLoggingEntriesMaxCount()
    {
        return E;
    }

    public long getLoggingEntriesTimeoutCount()
    {
        return D;
    }

    public int getMaxAutoSuggestLength()
    {
        return i;
    }

    public Action getOfferWidgetAction()
    {
        return N;
    }

    public Long getPNImageDownloadTimeOut()
    {
        return m;
    }

    public ArrayList getPincodeSupportedStores()
    {
        return z;
    }

    public PopUpResponse getPopUpData()
    {
        return O;
    }

    public long getProductDiscoveryDbTimeout()
    {
        return g;
    }

    public long getProductInfoDbTimeout()
    {
        return f;
    }

    public LayoutDetails getProductPageTheme()
    {
        return K;
    }

    public int getPullOutShowCount()
    {
        return F;
    }

    public Map getRunning_out_logo()
    {
        return P;
    }

    public ArrayList getSearchSupportedFilters()
    {
        return y;
    }

    public long getSellerDbTimeout()
    {
        return e;
    }

    public String getShareAppUrl()
    {
        return l;
    }

    public int getShowOffersAtPosition()
    {
        return r;
    }

    public String getTwitterShareAppUrl()
    {
        return o;
    }

    public long getUgcDbTimeout()
    {
        return d;
    }

    public boolean isClearComponentDataDb()
    {
        return t;
    }

    public boolean isClearProductInfoDb()
    {
        return u;
    }

    public boolean isEnableAnimation()
    {
        return j;
    }

    public boolean isEnableCrashlyticsBreadCrumbs()
    {
        return q;
    }

    public boolean isEnableFlipkartFirst()
    {
        return k;
    }

    public boolean isEnableOverFlowMenu()
    {
        return L;
    }

    public boolean isLoggingEnabled()
    {
        return B;
    }

    public boolean isPerfLoggingEnabled()
    {
        return C;
    }

    public void putConfigData(ConfigResponseData configresponsedata)
    {
label0:
        {
            a = configresponsedata;
            if (a != null)
            {
                ArrayList arraylist = a.getBlockedAppVersions();
                String s1 = Integer.toString(FlipkartPreferenceManager.instance().getAppVersionNumber());
                if (arraylist == null || !arraylist.contains(s1))
                {
                    break label0;
                }
                Intent intent = new Intent(FlipkartApplication.getAppContext(), com/flipkart/android/activity/BlockActivity);
                intent.addFlags(0x14008000);
                FlipkartApplication.getAppContext().startActivity(intent);
            }
            return;
        }
        DbTimeout dbtimeout = a.getDbTimeout();
        if (dbtimeout != null)
        {
            setProductDiscoveryDbTimeout(dbtimeout.getDicoveryTimeout());
            setProductInfoDbTimeout(dbtimeout.getProdInfoTimeout());
            setUgcDbTimeout(dbtimeout.getUgcTimeout());
            setSellerDbTimeout(dbtimeout.getSellerTimeout());
        }
        setFetchNewProductsCount(a.getFetchNewProductsCount());
        setMaxAutoSuggestLength(a.getMaxAutoSuggestLength());
        setEnableAnimation(a.isEnableAnimation());
        setEnableFlipkartFirst(a.isEnableFlipkartFirst());
        setShareAppUrl(a.getShareAppUrl());
        setPNImageDownloadTimeOut(a.getPNImageDownloadTimeOut());
        setFacebookShareAppUrl(a.getShareAppUrlFacebook());
        setTwitterShareAppUrl(a.getShareAppUrlTwitter());
        setLocationSource(a.getLocationSource());
        setEnableCrashlyticsBreadCrumbs(a.isEnableCrashlyticsBreadcrumbs());
        setShowOffersAtPosition(a.getShowOffersAt());
        setPullOutShowCount(a.getPullOutShowCount());
        setPopUpData(a.getPopUpData());
        ServiceProfileData serviceprofiledata = a.getServiceProfileData();
        if (serviceprofiledata != null)
        {
            setFbfData(serviceprofiledata.getFBF());
        }
        setClearComponentDataDb(a.isClearComponentDataDb());
        setClearProductInfoDb(a.isClearProductInfoDb());
        setFilterImagesMap(a.getFilterImages());
        setAppRateData(a.getAppRateData());
        setAppUpgradeData(a.getAppUpgradeData());
        setSearchSupportedFilters(a.getSearchSupportedFilters());
        setPincodeSupportedStores(a.getPincodeStores());
        setActioToUrlMap(a.getUrlActionsMap());
        LoggingTimeout loggingtimeout = a.getLoggingTimeout();
        if (loggingtimeout != null)
        {
            setLoggingEnabled(loggingtimeout.isLoggingEnabled());
            setLoggingEntriesTimeoutCount(loggingtimeout.getTimeoutCount());
            setLoggingEntriesMaxCount(loggingtimeout.getMaxCount());
            setPerfLoggingEnabled(loggingtimeout.isPerfLoggingEnabled());
        }
        AppTheme apptheme = a.getAppTheme();
        if (apptheme != null)
        {
            setAppTheme(a.getAppTheme());
            setActionBarTheme(apptheme.getActionBar());
            setBrowsePageTheme(apptheme.getBrowsePage());
            setProductPageTheme(apptheme.getProductPage());
        }
        setEnableDDayConfig(a.getEnableDDayConfig());
        setEnableOverFlowMenu(a.isEnableOverFlowMenu());
        setHomeWidgetAction(a.getHomeWidgetAction());
        setOfferWidgetAction(a.getOfferWidgetAction());
        setRunning_out_logo(a.getRunning_out_logo());
        setEnableBatch(a.getEnableBatch());
    }

    public void setActioToUrlMap(Map map)
    {
        A = map;
    }

    public void setActionBarTheme(LayoutDetails layoutdetails)
    {
        I = layoutdetails;
    }

    public void setAppRateData(AppRateData appratedata)
    {
        x = appratedata;
    }

    public void setAppTheme(AppTheme apptheme)
    {
        G = apptheme;
    }

    public void setAppUpgradeData(AppUpgradeData appupgradedata)
    {
        w = appupgradedata;
    }

    public void setBrowsePageTheme(LayoutDetails layoutdetails)
    {
        J = layoutdetails;
    }

    public void setClearComponentDataDb(boolean flag)
    {
        t = flag;
    }

    public void setClearProductInfoDb(boolean flag)
    {
        u = flag;
    }

    public void setEnableAnimation(boolean flag)
    {
        j = flag;
    }

    public void setEnableBatch(boolean flag)
    {
        Q = flag;
    }

    public void setEnableCrashlyticsBreadCrumbs(boolean flag)
    {
        q = flag;
    }

    public void setEnableDDayConfig(DDayParams ddayparams)
    {
        H = ddayparams;
    }

    public void setEnableFlipkartFirst(boolean flag)
    {
        k = flag;
    }

    public void setEnableOverFlowMenu(boolean flag)
    {
        L = flag;
    }

    public void setFacebookShareAppUrl(String s1)
    {
        n = s1;
    }

    public void setFbfData(FBFData fbfdata)
    {
        s = fbfdata;
    }

    public void setFetchNewProductsCount(int i1)
    {
        h = i1;
    }

    public void setFilterImagesMap(Map map)
    {
        v = map;
    }

    public void setHomeWidgetAction(Action action)
    {
        M = action;
    }

    public void setLocationSource(String s1)
    {
        p = s1;
    }

    public void setLoggingEnabled(boolean flag)
    {
        B = flag;
    }

    public void setLoggingEntriesMaxCount(long l1)
    {
        E = l1;
    }

    public void setLoggingEntriesTimeoutCount(long l1)
    {
        D = l1;
    }

    public void setMaxAutoSuggestLength(int i1)
    {
        i = i1;
    }

    public void setOfferWidgetAction(Action action)
    {
        N = action;
    }

    public void setPNImageDownloadTimeOut(Long long1)
    {
        m = long1;
    }

    public void setPerfLoggingEnabled(boolean flag)
    {
        C = flag;
    }

    public void setPincodeSupportedStores(ArrayList arraylist)
    {
        z = arraylist;
    }

    public void setPopUpData(PopUpResponse popupresponse)
    {
        O = popupresponse;
    }

    public void setProductDiscoveryDbTimeout(long l1)
    {
        g = l1;
    }

    public void setProductInfoDbTimeout(long l1)
    {
        f = l1;
    }

    public void setProductPageTheme(LayoutDetails layoutdetails)
    {
        K = layoutdetails;
    }

    public void setPullOutShowCount(int i1)
    {
        F = i1;
    }

    public void setRunning_out_logo(Map map)
    {
        P = map;
    }

    public void setSearchSupportedFilters(ArrayList arraylist)
    {
        y = arraylist;
    }

    public void setSellerDbTimeout(long l1)
    {
        e = l1;
    }

    public void setShareAppUrl(String s1)
    {
        l = s1;
    }

    public void setShowOffersAtPosition(int i1)
    {
        r = i1;
    }

    public void setTwitterShareAppUrl(String s1)
    {
        o = s1;
    }

    public void setUgcDbTimeout(long l1)
    {
        d = l1;
    }

}
