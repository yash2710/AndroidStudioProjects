// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;

import android.app.Activity;
import com.AdX.tag.AdXConnect;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.init.FlipkartApplication;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.urlmanagement.actionloader.DefaultJsonActionLoader;
import com.flipkart.android.urlmanagement.actionloader.FacebookShareAppLoader;
import com.flipkart.android.urlmanagement.actionloader.HomePageActionLoader;
import com.flipkart.android.urlmanagement.actionloader.OpenEncodedUrlExternalActionLoader;
import com.flipkart.android.urlmanagement.actionloader.ProductPageActionLoader;
import com.flipkart.android.urlmanagement.actionloader.TwitterShareAppLoader;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.logging.FkLogger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

// Referenced classes of package com.flipkart.android.urlmanagement:
//            a, AppParams, AppAction, UrlManagementConstants

public class AppActionUrlHelper
{

    public AppActionUrlHelper()
    {
    }

    public void handleUrl(AppParams appparams, Activity activity)
    {
        if (appparams != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch (a.a[appparams.getAction().ordinal()])
        {
        default:
            return;

        case 13: // '\r'
            continue; /* Loop/switch isn't completed */

        case 1: // '\001'
            (new HomePageActionLoader(appparams, activity)).load();
            return;

        case 2: // '\002'
            (new ProductPageActionLoader(appparams, activity)).load();
            return;

        case 3: // '\003'
            String s = appparams.getParams();
            if (!StringUtils.isNullOrEmpty(s))
            {
                String as[] = s.split("=");
                if (as.length > 1)
                {
                    String s1 = as[1];
                    if (!StringUtils.isNullOrEmpty(s1))
                    {
                        try
                        {
                            AdXConnect.getAdXConnectEventInstance(FlipkartApplication.getAppContext(), "Sale", s1, "INR", URLEncoder.encode(FlipkartDeviceInfoProvider.getMakeModelAndPreburn(), "UTF-8"));
                            return;
                        }
                        catch (UnsupportedEncodingException unsupportedencodingexception)
                        {
                            FkLogger.printStackTrace(unsupportedencodingexception);
                        }
                        return;
                    }
                }
            }
            break;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
            (new DefaultJsonActionLoader(appparams, activity)).load();
            return;

        case 11: // '\013'
            (new OpenEncodedUrlExternalActionLoader(appparams, activity)).load();
            return;

        case 12: // '\f'
            if (activity instanceof HomeFragmentHolderActivity)
            {
                break; /* Loop/switch isn't completed */
            }
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
        FkLogger.debug("handleurl", (new StringBuilder("params ")).append(appparams.getParams()).toString());
        (new FacebookShareAppLoader(appparams, activity)).load();
        return;
        if (!(activity instanceof HomeFragmentHolderActivity)) goto _L1; else goto _L4
_L4:
        (new TwitterShareAppLoader(appparams, activity)).load();
        return;
    }

    public AppParams readAppActionUrl(String s)
    {
        FkLogger.debug("HandleUrlForApp", (new StringBuilder("url is ")).append(s).toString());
        if (!StringUtils.isNullOrEmpty(s) && s.startsWith(UrlManagementConstants.ACTION_URL_PREFIX))
        {
            AppParams appparams = new AppParams();
            String as[] = s.substring(UrlManagementConstants.ACTION_URL_PREFIX.length()).split("\\?");
            if (as.length <= 2)
            {
                if (as.length > 0)
                {
                    AppAction appaction;
                    try
                    {
                        String s1 = as[0].trim().replace("/", "").replace("/", "");
                        FkLogger.debug("handleurl", (new StringBuilder("app action string is ")).append(s1).toString());
                        appaction = AppAction.valueOf(s1);
                        FkLogger.debug("handleurl", (new StringBuilder("app action string is after parse ")).append(appaction).toString());
                    }
                    catch (Exception exception)
                    {
                        HashMap hashmap = new HashMap();
                        hashmap.put("AppUrl", s);
                        ApiLogger.log(LoggerTag.Webview, "Not able to map app url to app action", hashmap);
                        return null;
                    }
                    appparams.setAction(appaction);
                }
                if (as.length == 2)
                {
                    appparams.setParams(as[1]);
                }
                return appparams;
            }
        }
        return null;
    }

    public boolean shouldAppHandleUrl(String s)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            return false;
        } else
        {
            return s.startsWith(UrlManagementConstants.ACTION_URL_PREFIX);
        }
    }
}
