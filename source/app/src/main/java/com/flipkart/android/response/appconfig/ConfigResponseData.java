// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.response.appconfig;

import com.flipkart.android.response.PopUpResponse;
import com.flipkart.android.response.component.data.customvalues.Action;
import com.flipkart.android.utils.DDayParams;
import java.util.ArrayList;
import java.util.Map;

// Referenced classes of package com.flipkart.android.response.appconfig:
//            AppRateData, AppTheme, AppUpgradeData, DbTimeout, 
//            LoggingTimeout, ServiceProfileData

public class ConfigResponseData
{

    private Long PNImageDownloadTimeOut;
    private AppRateData appRateData;
    private AppTheme appTheme;
    private AppUpgradeData appUpgradeData;
    private ArrayList blockedAppVersions;
    private boolean clearComponentDataDb;
    private boolean clearProductInfoDb;
    private String codText;
    private DbTimeout dbTimeout;
    private String emiText;
    private boolean enableAnimation;
    private boolean enableBatch;
    private boolean enableCrashlyticsBreadcrumbs;
    private DDayParams enableDDayConfig;
    private boolean enableFlipkartFirst;
    private boolean enableOverFlowMenu;
    private int fetchNewProductsCount;
    private Map filterImages;
    private Action homeWidgetAction;
    private String locationSource;
    private LoggingTimeout loggingTimeout;
    private int maxAutoSuggestLength;
    private Action offerWidgetAction;
    private ArrayList pincodeStores;
    private PopUpResponse popUpData;
    private int pullOutShowCount;
    private Map running_out_logo;
    private ArrayList searchSupportedFilters;
    private ServiceProfileData serviceProfileData;
    private String shareAppUrl;
    private String shareAppUrlFacebook;
    private String shareAppUrlTwitter;
    private int showOffersAt;
    private Map urlActionsMap;

    public ConfigResponseData()
    {
    }

    public AppRateData getAppRateData()
    {
        return appRateData;
    }

    public AppTheme getAppTheme()
    {
        return appTheme;
    }

    public AppUpgradeData getAppUpgradeData()
    {
        return appUpgradeData;
    }

    public ArrayList getBlockedAppVersions()
    {
        return blockedAppVersions;
    }

    public String getCodText()
    {
        return codText;
    }

    public DbTimeout getDbTimeout()
    {
        return dbTimeout;
    }

    public String getEmiText()
    {
        return emiText;
    }

    public boolean getEnableBatch()
    {
        return enableBatch;
    }

    public DDayParams getEnableDDayConfig()
    {
        return enableDDayConfig;
    }

    public int getFetchNewProductsCount()
    {
        return fetchNewProductsCount;
    }

    public Map getFilterImages()
    {
        return filterImages;
    }

    public Action getHomeWidgetAction()
    {
        return homeWidgetAction;
    }

    public String getLocationSource()
    {
        return locationSource;
    }

    public LoggingTimeout getLoggingTimeout()
    {
        return loggingTimeout;
    }

    public int getMaxAutoSuggestLength()
    {
        return maxAutoSuggestLength;
    }

    public Action getOfferWidgetAction()
    {
        return offerWidgetAction;
    }

    public Long getPNImageDownloadTimeOut()
    {
        return PNImageDownloadTimeOut;
    }

    public ArrayList getPincodeStores()
    {
        return pincodeStores;
    }

    public PopUpResponse getPopUpData()
    {
        return popUpData;
    }

    public int getPullOutShowCount()
    {
        return pullOutShowCount;
    }

    public Map getRunning_out_logo()
    {
        return running_out_logo;
    }

    public ArrayList getSearchSupportedFilters()
    {
        return searchSupportedFilters;
    }

    public ServiceProfileData getServiceProfileData()
    {
        return serviceProfileData;
    }

    public String getShareAppUrl()
    {
        return shareAppUrl;
    }

    public String getShareAppUrlFacebook()
    {
        return shareAppUrlFacebook;
    }

    public String getShareAppUrlTwitter()
    {
        return shareAppUrlTwitter;
    }

    public int getShowOffersAt()
    {
        return showOffersAt;
    }

    public Map getUrlActionsMap()
    {
        return urlActionsMap;
    }

    public boolean isClearComponentDataDb()
    {
        return clearComponentDataDb;
    }

    public boolean isClearProductInfoDb()
    {
        return clearProductInfoDb;
    }

    public boolean isEnableAnimation()
    {
        return enableAnimation;
    }

    public boolean isEnableCrashlyticsBreadcrumbs()
    {
        return enableCrashlyticsBreadcrumbs;
    }

    public boolean isEnableFlipkartFirst()
    {
        return enableFlipkartFirst;
    }

    public boolean isEnableOverFlowMenu()
    {
        return enableOverFlowMenu;
    }

    public void setAppRateData(AppRateData appratedata)
    {
        appRateData = appratedata;
    }

    public void setAppTheme(AppTheme apptheme)
    {
        appTheme = apptheme;
    }

    public void setAppUpgradeData(AppUpgradeData appupgradedata)
    {
        appUpgradeData = appupgradedata;
    }

    public void setBlockedAppVersions(ArrayList arraylist)
    {
        blockedAppVersions = arraylist;
    }

    public void setClearComponentDataDb(boolean flag)
    {
        clearComponentDataDb = flag;
    }

    public void setClearProductInfoDb(boolean flag)
    {
        clearProductInfoDb = flag;
    }

    public void setCodText(String s)
    {
        codText = s;
    }

    public void setDbTimeout(DbTimeout dbtimeout)
    {
        dbTimeout = dbtimeout;
    }

    public void setEmiText(String s)
    {
        emiText = s;
    }

    public void setEnableAnimation(boolean flag)
    {
        enableAnimation = flag;
    }

    public void setEnableBatch(boolean flag)
    {
        enableBatch = flag;
    }

    public void setEnableCrashlyticsBreadcrumbs(boolean flag)
    {
        enableCrashlyticsBreadcrumbs = flag;
    }

    public void setEnableDDayConfig(DDayParams ddayparams)
    {
        enableDDayConfig = ddayparams;
    }

    public void setEnableFlipkartFirst(boolean flag)
    {
        enableFlipkartFirst = flag;
    }

    public void setEnableOverFlowMenu(boolean flag)
    {
        enableOverFlowMenu = flag;
    }

    public void setFetchNewProductsCount(int i)
    {
        fetchNewProductsCount = i;
    }

    public void setFilterImages(Map map)
    {
        filterImages = map;
    }

    public void setHomeWidgetAction(Action action)
    {
        homeWidgetAction = action;
    }

    public void setLocationSource(String s)
    {
        locationSource = s;
    }

    public void setLoggingTimeout(LoggingTimeout loggingtimeout)
    {
        loggingTimeout = loggingtimeout;
    }

    public void setMaxAutoSuggestLength(int i)
    {
        maxAutoSuggestLength = i;
    }

    public void setOfferWidgetAction(Action action)
    {
        offerWidgetAction = action;
    }

    public void setPNImageDownloadTimeOut(Long long1)
    {
        PNImageDownloadTimeOut = long1;
    }

    public void setPincodeStores(ArrayList arraylist)
    {
        pincodeStores = arraylist;
    }

    public void setPopUpData(PopUpResponse popupresponse)
    {
        popUpData = popupresponse;
    }

    public void setPullOutShowCount(int i)
    {
        pullOutShowCount = i;
    }

    public void setRunning_out_logo(Map map)
    {
        running_out_logo = map;
    }

    public void setSearchSupportedFilters(ArrayList arraylist)
    {
        searchSupportedFilters = arraylist;
    }

    public void setServiceProfileData(ServiceProfileData serviceprofiledata)
    {
        serviceProfileData = serviceprofiledata;
    }

    public void setShareAppUrl(String s)
    {
        shareAppUrl = s;
    }

    public void setShareAppUrlFacebook(String s)
    {
        shareAppUrlFacebook = s;
    }

    public void setShareAppUrlTwitter(String s)
    {
        shareAppUrlTwitter = s;
    }

    public void setShowOffersAt(int i)
    {
        showOffersAt = i;
    }

    public void setUrlActionsMap(Map map)
    {
        urlActionsMap = map;
    }
}
