// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.init;

import android.content.Context;
import android.content.res.Resources;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.crashlytics.android.Crashlytics;
import com.crittercism.app.Crittercism;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.analytics.TrackingUtil;
import com.flipkart.android.config.FlipkartConfigurationProvider;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.datagovernance.BatchNetworkingHandler;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.browser.Browser;
import com.flipkart.fk_android_batchnetworking.BatchNetworking;
import com.flipkart.logging.FkLogger;
import org.json.JSONObject;

// Referenced classes of package com.flipkart.android.init:
//            a, FlipkartApplication

public class AppStartup
{

    private static final String a = com.flipkart.android.init.AppStartup.getSimpleName();
    private static AppStartup b;
    private static String c = "fk_android_app";
    private static String d = "fk_android_app";

    public AppStartup()
    {
    }

    public static AppStartup getInstance()
    {
        com/flipkart/android/init/AppStartup;
        JVM INSTR monitorenter ;
        AppStartup appstartup;
        if (b == null)
        {
            b = new AppStartup();
        }
        appstartup = b;
        com/flipkart/android/init/AppStartup;
        JVM INSTR monitorexit ;
        return appstartup;
        Exception exception;
        exception;
        throw exception;
    }

    public void performInitTasks(Context context)
    {
        FlipkartPreferenceManager.instance().initialize(context);
        Crittercism.init(context, FlipkartConfigurationProvider.getCrittercismKey(context), new JSONObject[0]);
        Crittercism.setUsername(FlipkartDeviceInfoProvider.getDeviceId());
        a.a[FlipkartConfigurationProvider.getEnvironment(context).ordinal()];
        JVM INSTR tableswitch 1 3: default 64
    //                   1 64
    //                   2 64
    //                   3 398;
           goto _L1 _L1 _L1 _L2
_L1:
        TrackingHelper.configureAppMeasurement();
        NetworkMonitor.initialize(context);
        Crashlytics.start(context);
        String s2;
        String s3;
        StringBuilder stringbuilder;
        String s4;
        try
        {
            int i = FlipkartConfigurationProvider.getAppVersionNumber(context);
            String s5 = FlipkartConfigurationProvider.getAppVersionCode(context);
            FlipkartPreferenceManager.instance().saveAppVersionNumber(i);
            FlipkartPreferenceManager.instance().saveAppVersionCode(s5);
        }
        catch (Exception exception)
        {
            Object aobj[] = new Object[1];
            String s;
            String s1;
            if (exception.toString() == null)
            {
                s = "Unknown Error";
            } else
            {
                s = exception.toString();
            }
            aobj[0] = s;
            s1 = String.format("An error occured while trying to get the version number [%s]", aobj);
            FkLogger.debug(a, s1);
        }
        s2 = (new WebView(context)).getSettings().getUserAgentString();
        s3 = Browser.parseUserAgentString(s2).getName();
        stringbuilder = new StringBuilder();
        stringbuilder.append("FKUA/Retail");
        stringbuilder.append("/").append(FlipkartDeviceInfoProvider.getAppVersionNumber());
        stringbuilder.append("/Android");
        if (FlipkartApplication.getAppContext().getResources().getBoolean(0x7f08000b))
        {
            stringbuilder.append("/Tablet");
        } else
        {
            stringbuilder.append("/Mobile");
        }
        stringbuilder.append(" (").append(FlipkartDeviceInfoProvider.getManufacturer());
        stringbuilder.append("/").append(FlipkartDeviceInfoProvider.getModel());
        stringbuilder.append("/").append(FlipkartDeviceInfoProvider.getDeviceId());
        stringbuilder.append(")");
        d = stringbuilder.toString();
        FlipkartPreferenceManager.instance().saveUserAgent((new StringBuilder()).append(s2).append(" appVersion:").append(FlipkartPreferenceManager.instance().getAppVersionNumber()).append(" ").append(c).append(" ").append(d).toString());
        FlipkartPreferenceManager.instance().saveBrowserFamily(s3);
        s4 = FlipkartDeviceInfoProvider.getManufacturer();
        if (!StringUtils.isNullOrEmpty(s4) && s4.equalsIgnoreCase("nokia"))
        {
            FlipkartPreferenceManager.instance().saveNokiaDevice(true);
        } else
        {
            FlipkartPreferenceManager.instance().saveNokiaDevice(false);
        }
        Crashlytics.setUserName(FlipkartDeviceInfoProvider.getDeviceId());
        Crashlytics.setString("ConnectionType", Integer.toString(NetworkMonitor.isNetworkFast()));
        Crashlytics.setString("LoginStatus at Startup", FlipkartPreferenceManager.instance().isLoggedIn().toString());
        BatchNetworking.getDefaultInstance().initialize(context);
        BatchNetworkingHandler.initNewBatchHandler("perf", "/data/collector/business");
        BatchNetworkingHandler.initNewBatchHandler("debug_logger", "/data/collector/debug");
        return;
_L2:
        TrackingUtil.trackingEnabled = Boolean.valueOf(true);
          goto _L1
    }

}
