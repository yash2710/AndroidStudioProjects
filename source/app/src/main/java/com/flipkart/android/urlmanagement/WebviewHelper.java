// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.urlmanagement;

import android.os.Bundle;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.log.ApiLogger;
import com.flipkart.android.log.LoggerTag;
import com.flipkart.android.utils.AppConfigUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.UrlUtils;
import com.flipkart.android.volley.request.GsonRequest;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.urlmanagement:
//            WebviewParams, WebviewAction

public class WebviewHelper
{

    public WebviewHelper()
    {
    }

    private static WebviewParams a(String s)
    {
        if (StringUtils.isNullOrEmpty(s))
        {
            break MISSING_BLOCK_LABEL_159;
        }
        String as[] = s.split("@");
        if (as.length < 3)
        {
            return null;
        }
        WebviewParams webviewparams = new WebviewParams();
        boolean flag;
        int i;
        if (as[0].equalsIgnoreCase("https"))
        {
            webviewparams.setProtocol("HTTPS");
        } else
        {
            webviewparams.setProtocol("HTTP");
        }
        boolean flag1;
        try
        {
            flag = as[1].equalsIgnoreCase("GET");
        }
        catch (Exception exception)
        {
            HashMap hashmap = new HashMap();
            hashmap.put("Verb", as[1]);
            ApiLogger.log(LoggerTag.Init, "Not a valid verb", hashmap);
            return null;
        }
        i = 0;
        if (!flag) goto _L2; else goto _L1
_L1:
        webviewparams.setVerb(i);
        webviewparams.setUrl(as[2]);
        if (as.length == 4)
        {
            webviewparams.setPostParams(as[3]);
        }
        return webviewparams;
_L2:
        flag1 = as[1].equalsIgnoreCase("POST");
        i = 0;
        if (flag1)
        {
            i = 1;
        }
        if (true) goto _L1; else goto _L3
_L3:
        return null;
    }

    private static String a(WebviewParams webviewparams)
    {
        if (webviewparams.getProtocol().equalsIgnoreCase("https"))
        {
            return UrlUtils.getFullUrl(GsonRequest.BASE_WEB_URL_SECURE, webviewparams.getUrl());
        } else
        {
            return UrlUtils.getFullUrl(GsonRequest.BASE_WEB_URL, webviewparams.getUrl());
        }
    }

    public Bundle getBuyNowBundle(String s)
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(WebviewAction.BUY_NOW.name()));
        if (webviewparams == null)
        {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
        bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
        bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", true);
        String as[] = webviewparams.getPostParams().split(",");
        if (as.length <= 0)
        {
            return null;
        } else
        {
            bundle.putString("WEBVIEW_EXTRAS_POST_PARAMS", UrlUtils.addPostParameter("", as[0], s));
            return bundle;
        }
    }

    public Bundle getFlipkartFirstBundle()
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        String s;
        WebviewParams webviewparams;
        if (FlipkartPreferenceManager.instance().getUsersFfStatus().booleanValue())
        {
            s = (String)map.get(WebviewAction.FLIPKART_FIRST_MANAGE.name());
        } else
        {
            s = (String)map.get(WebviewAction.FLIPKART_FIRST.name());
        }
        webviewparams = a(s);
        if (webviewparams == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
            bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
            bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", false);
            return bundle;
        }
    }

    public Bundle getForgotPasswordBundle()
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(WebviewAction.FORGOT_PASSWORD.name()));
        if (webviewparams == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
            bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
            bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", false);
            return bundle;
        }
    }

    public Bundle getMyAddresssBundle()
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(WebviewAction.MY_ADDRESS.name()));
        if (webviewparams == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
            bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
            bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", false);
            return bundle;
        }
    }

    public Bundle getMyOrdersBundle()
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(WebviewAction.MY_ORDERS.name()));
        if (webviewparams == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
            bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
            bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", false);
            return bundle;
        }
    }

    public Bundle getShowCartBundle()
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(WebviewAction.VIEW_CART.name()));
        if (webviewparams == null)
        {
            return null;
        }
        Bundle bundle = new Bundle();
        if (StringUtils.isNullOrEmpty(FlipkartPreferenceManager.instance().getUserPinCode()))
        {
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
        } else
        {
            bundle.putString("WEBVIEW_EXTRAS_URL", (new StringBuilder()).append(a(webviewparams)).append("&pin=").append(FlipkartPreferenceManager.instance().getUserPinCode()).toString());
        }
        bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
        bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", true);
        return bundle;
    }

    public Bundle getStaticPageBundle(WebviewAction webviewaction)
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(webviewaction.name()));
        if (webviewparams == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
            bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
            bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", false);
            return bundle;
        }
    }

    public Bundle getTrackOrderBundle()
    {
        Map map = AppConfigUtils.getInstance().getActioToUrlMap();
        if (map == null)
        {
            return null;
        }
        WebviewParams webviewparams = a((String)map.get(WebviewAction.TRACK_ORDER.name()));
        if (webviewparams == null)
        {
            return null;
        } else
        {
            Bundle bundle = new Bundle();
            bundle.putString("WEBVIEW_EXTRAS_URL", a(webviewparams));
            bundle.putInt("WEBVIEW_EXTRAS_VERB", webviewparams.getVerb());
            bundle.putBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM", false);
            return bundle;
        }
    }
}
