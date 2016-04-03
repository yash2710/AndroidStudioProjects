// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.measurement;

import java.util.ArrayList;

// Referenced classes of package com.adobe.adms.measurement:
//            a, b, ADMS_Measurement

public final class ADMS_DefaultValues
{

    protected static final String CAMPAIGN_REFERRER_CAMPAIGN = "a.referrer.campaign.name";
    protected static final String CAMPAIGN_REFERRER_CONTENT = "a.referrer.campaign.content";
    protected static final String CAMPAIGN_REFERRER_MEDIUM = "a.referrer.campaign.medium";
    protected static final String CAMPAIGN_REFERRER_SOURCE = "a.referrer.campaign.source";
    protected static final String CAMPAIGN_REFERRER_TERM = "a.referrer.campaign.term";
    protected static final String FB_ATTRIBUTION_ID = "a.fb.attrib";
    private static boolean a = false;
    protected static final String kADMS_CD_CarrierName = "a.CarrierName";
    protected static final String kADMS_CD_DeviceName = "a.DeviceName";
    protected static final String kADMS_CD_NetworkConnection = "a.NetworkConnection";
    protected static final String kADMS_CD_OSVersion = "a.OSVersion";
    protected static final String kADMS_CD_Resolution = "a.Resolution";
    protected static final String kADMS_Campaign = "v0";
    protected static final String kADMS_Channel = "ch";
    protected static final String kADMS_CharacterSet = "ce";
    protected static final String kADMS_ContextData = "c";
    protected static final String kADMS_ContextDataString = "contextdata";
    protected static final String kADMS_CookieDomainPeriods = "cookieDomainPeriods";
    protected static final String kADMS_CookieLifetime = "cookieLifetime";
    protected static final String kADMS_CurrencyCode = "cc";
    protected static final int kADMS_DefaultOfflineHitLimit = 1000;
    protected static final boolean kADMS_DefaultOfflineTrackingEnabled = false;
    protected static final int kADMS_DefaultPauseSessionTimeout = 300;
    protected static final boolean kADMS_DefaultSSLOn = false;
    protected static final String kADMS_DeleteLightProfiles = "mtsd";
    protected static final String kADMS_DynamicVariablePrefix = "D";
    protected static final String kADMS_Events = "events";
    protected static final String kADMS_HitTimestamp = "t";
    protected static final String kADMS_InstallDate = "ADMS_InstallDate";
    protected static final String kADMS_LastDateUsed = "ADMS_LastDateUsed";
    protected static final String kADMS_LastVersion = "ADMS_LastVersion";
    protected static final String kADMS_LastVersionUsed = "ADMS_LastVersion";
    protected static final String kADMS_Launches = "ADMS_Launches";
    protected static final String kADMS_LaunchesAfterUpgrade = "ADMS_LaunchesAfterUpgrade";
    protected static final String kADMS_LightIncrementBy = "mti";
    protected static final String kADMS_LightProfileID = "mtp";
    protected static final String kADMS_LightStoreForSeconds = "mtss";
    protected static final String kADMS_LightTrackVars = "lightTrackVars";
    protected static final String kADMS_LinkTrackEvents = "linkTrackEvents";
    protected static final String kADMS_LinkTrackVars = "linkTrackVars";
    protected static final String kADMS_PageEvent = "pe";
    protected static final String kADMS_PageName = "pageName";
    protected static final String kADMS_PageType = "pageType";
    protected static final String kADMS_PageURL = "g";
    protected static final String kADMS_ParamPrefixEvar = "v";
    protected static final String kADMS_ParamPrefixEvent = "event";
    protected static final String kADMS_ParamPrefixHier = "h";
    protected static final String kADMS_ParamPrefixList = "l";
    protected static final String kADMS_ParamPrefixPev = "pev";
    protected static final String kADMS_ParamPrefixProp = "c";
    protected static final String kADMS_PauseDate = "ADMS_PauseDate";
    protected static final String kADMS_PrevSessionLength = "a.PrevSessionLength";
    protected static final String kADMS_Products = "products";
    protected static final String kADMS_PurchaseID = "purchaseID";
    protected static final String kADMS_Referrer = "r";
    protected static final String kADMS_ReferrerProcessed = "ADMS_ReferrerProcessed";
    protected static final String kADMS_RetrieveLightData = "mts";
    protected static final String kADMS_RetrieveLightProfiles = "mtsr";
    protected static final String kADMS_Server = "server";
    protected static final String kADMS_SessionOpen = "ADMS_SessionOpen";
    protected static final String kADMS_SessionStart = "ADMS_SessionStart";
    protected static final String kADMS_State = "state";
    protected static final String kADMS_SuccessfulClose = "ADMS_SuccessfulClose";
    protected static final String kADMS_Timestamp = "ts";
    protected static final String kADMS_TransactionID = "xact";
    protected static final String kADMS_UpgradeDate = "ADMS_UpgradeDate";
    protected static final String kADMS_VisitorID = "vid";
    protected static final String kADMS_VisitorNamespace = "ns";
    protected static final String kADMS_Zip = "zip";
    protected static final String kAppCrashEvent = "a.CrashEvent";
    protected static final String kAppEnvironmentVar = "a.OSEnvironment";
    protected static final String kAppIDVar = "a.AppID";
    protected static final String kAppInstallDateVar = "a.InstallDate";
    protected static final String kAppInstallEventName = "a.InstallEvent";
    protected static final String kAppLaunchEvent = "a.LaunchEvent";
    protected static final String kAppLaunchNumberSinceLastUpgradeVar = "a.LaunchesSinceUpgrade";
    protected static final String kAppLaunchNumberVar = "a.Launches";
    protected static final String kAppUpgradeEventName = "a.UpgradeEvent";
    protected static final String kDailyEngagedUserEventName = "a.DailyEngUserEvent";
    protected static final String kDayOfWeekVar = "a.DayOfWeek";
    protected static final String kDaysSinceFirstUseVar = "a.DaysSinceFirstUse";
    protected static final String kDaysSinceLastUpgradeVar = "a.DaysSinceLastUpgrade";
    protected static final String kDaysSinceLastUseVar = "a.DaysSinceLastUse";
    protected static final String kEngagedDaysLastUpgradeVar = "a.EngDaysSinceUpgrade";
    protected static final String kEngagedDaysLifetimeVar = "a.EngDaysLifetime";
    protected static final String kEngagedDaysMonthVar = "a.EngDaysMonth";
    protected static final String kHourOfDayVar = "a.HourOfDay";
    protected static final String kMonthlyEngagedUserEventName = "a.MonthlyEngUserEvent";
    protected static final int kOMConfigTypeInstall = 1;
    protected static final int kOMConfigTypeLaunch = 3;
    protected static final int kOMConfigTypeUpgrade = 2;
    protected static final ArrayList requiredLightVarList = new b();
    protected static final ArrayList requiredVarList = new a();

    public ADMS_DefaultValues()
    {
    }

    protected static boolean evarValid(int i)
    {
        if (i <= 0 || i > 75)
        {
            ADMS_Measurement.sharedInstance().debugLog("The eVar Number you have entered is not possible.  Correct values are integers 1-75.");
            return false;
        } else
        {
            return true;
        }
    }

    protected static void exceptionLog(Exception exception)
    {
    }

    protected static boolean hierValid(int i)
    {
        if (i <= 0 || i > 5)
        {
            ADMS_Measurement.sharedInstance().debugLog("The Hier Number you have entered is not possible.  Correct values are integers 1-5.");
            return false;
        } else
        {
            return true;
        }
    }

    protected static boolean listValid(int i)
    {
        if (i <= 0 || i > 3)
        {
            ADMS_Measurement.sharedInstance().debugLog("The List Number you have entered is not possible.  Correct values are integers 1-3.");
            return false;
        } else
        {
            return true;
        }
    }

    protected static boolean propValid(int i)
    {
        if (i <= 0 || i > 75)
        {
            ADMS_Measurement.sharedInstance().debugLog("The Prop Number you have entered is not possible.  Correct values are integers 1-75.");
            return false;
        } else
        {
            return true;
        }
    }

    static 
    {
        a = false;
    }
}
