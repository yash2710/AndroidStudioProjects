// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.analytics;

import android.app.Activity;
import com.adobe.adms.measurement.ADMS_Measurement;
import com.flipkart.android.config.FlipkartConfigurationProvider;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.login.LoginType;
import com.flipkart.android.utils.StringUtils;
import java.util.Hashtable;

// Referenced classes of package com.flipkart.android.analytics:
//            a, PageType

public class TrackingUtil
{

    public static String TRACKING_SERVER = "flipkart.d1.sc.omtrdc.net";
    public static Boolean trackingEnabled = Boolean.valueOf(false);

    public TrackingUtil()
    {
    }

    private static String a()
    {
        if (!FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            return "logout";
        }
        switch (a.a[FlipkartPreferenceManager.instance().getLastLoginType().ordinal()])
        {
        default:
            return "login:flipkart";

        case 1: // '\001'
            return "login:google";

        case 2: // '\002'
            return "login:facebook";
        }
    }

    public static void configureAppMeasurement()
    {
        String s;
        if (!trackingEnabled.booleanValue())
        {
            return;
        }
        s = FlipkartPreferenceManager.instance().getOmnitureVisitorId();
        if (!StringUtils.isNullOrEmpty(s)) goto _L2; else goto _L1
_L1:
        s = FlipkartDeviceInfoProvider.getDeviceId();
        FlipkartPreferenceManager.instance().saveOmnitureVisitorId(s);
        FlipkartPreferenceManager.instance().saveIsNewOmnitureVisitorId(true);
_L4:
        ADMS_Measurement adms_measurement = ADMS_Measurement.sharedInstance(FlipkartApplication.getAppContext());
        adms_measurement.clearVars();
        adms_measurement.setVisitorID(s);
        adms_measurement.setEvar(10, s);
        adms_measurement.setEvar(26, "android");
        adms_measurement.setEvar(72, Integer.toString(FlipkartConfigurationProvider.getAppVersionNumber(FlipkartApplication.getAppContext())));
        adms_measurement.configureMeasurement("flipkart-mob-androidapp", TRACKING_SERVER);
        adms_measurement.setCurrencyCode("INR");
        adms_measurement.setOfflineTrackingEnabled(false);
        adms_measurement.setSSL(false);
        return;
_L2:
        if (!FlipkartPreferenceManager.instance().getIsNewOmnitureVisitorId())
        {
            s = FlipkartDeviceInfoProvider.getDeviceId();
            FlipkartPreferenceManager.instance().saveOmnitureVisitorId(s);
            FlipkartPreferenceManager.instance().saveIsNewOmnitureVisitorId(true);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static ADMS_Measurement getMeasurement()
    {
        if (!trackingEnabled.booleanValue())
        {
            return null;
        } else
        {
            ADMS_Measurement adms_measurement = ADMS_Measurement.sharedInstance(FlipkartApplication.getAppContext());
            adms_measurement.clearVars();
            return adms_measurement;
        }
    }

    public static void setEvent(ADMS_Measurement adms_measurement, String s)
    {
        if (!StringUtils.isNullOrEmpty(s))
        {
            String s1 = adms_measurement.getEvents();
            if (StringUtils.isNullOrEmpty(s1))
            {
                adms_measurement.setEvents(s);
                return;
            }
            if (!s1.matches((new StringBuilder("(.*)")).append(s).append("(\\s*)(,{1})(\\s*)(.*)").toString()) && !s1.matches((new StringBuilder("(.*)")).append(s).append("(\\s*)").toString()))
            {
                adms_measurement.setEvents((new StringBuilder()).append(s1).append(",").append(s).toString());
                return;
            }
        }
    }

    public static void setPageParams(ADMS_Measurement adms_measurement, String s, PageType pagetype, String s1)
    {
        setEvent(adms_measurement, "event1");
        adms_measurement.setProp(3, pagetype.name());
        adms_measurement.setEvar(3, pagetype.name());
        adms_measurement.setEvar(50, s);
        adms_measurement.setProp(4, (new StringBuilder()).append(a()).append(":").append(s).toString());
        adms_measurement.setEvar(4, a());
        if (!StringUtils.isNullOrEmpty(s1))
        {
            adms_measurement.setEvar(1, s1);
            adms_measurement.setProp(1, s1);
        }
        if (FlipkartPreferenceManager.instance().isLoggedIn().booleanValue())
        {
            String s2 = FlipkartPreferenceManager.instance().getUserAccountId();
            adms_measurement.setProp(5, s2);
            adms_measurement.setEvar(5, s2);
        }
        adms_measurement.setAppState(s);
    }

    public static void startActivity(Activity activity)
    {
        if (!trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            ADMS_Measurement.sharedInstance(activity).startActivity(activity);
            return;
        }
    }

    public static void stopActivity()
    {
        if (!trackingEnabled.booleanValue())
        {
            return;
        } else
        {
            ADMS_Measurement.sharedInstance().stopActivity();
            return;
        }
    }

    public static void trackEvent(ADMS_Measurement adms_measurement, String s)
    {
        if (!trackingEnabled.booleanValue())
        {
            return;
        }
        try
        {
            adms_measurement.trackEvents(s);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static void trackLink(ADMS_Measurement adms_measurement)
    {
        if (!trackingEnabled.booleanValue())
        {
            return;
        }
        try
        {
            adms_measurement.trackLink("", "o", "", new Hashtable(), null);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public static void trackPage(ADMS_Measurement adms_measurement)
    {
        if (!trackingEnabled.booleanValue())
        {
            return;
        }
        try
        {
            adms_measurement.track();
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

}
