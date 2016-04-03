// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Message;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.urlmanagement.AppAction;
import com.flipkart.android.urlmanagement.AppActionUrlHelper;
import com.flipkart.android.urlmanagement.AppParams;
import com.flipkart.android.urlmanagement.UrlManagementConstants;
import com.flipkart.android.utils.NetworkMonitor;
import com.flipkart.android.utils.SessionUtils;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.volley.request.GsonRequest;
import com.flipkart.android.webview.CookieParser;
import com.flipkart.logging.FkLogger;

// Referenced classes of package com.flipkart.android.fragments:
//            WebViewFragment, bF, FlipkartBaseFragment

final class bE extends WebViewClient
{

    private WebViewFragment a;

    private bE(WebViewFragment webviewfragment)
    {
        a = webviewfragment;
        super();
    }

    bE(WebViewFragment webviewfragment, byte byte0)
    {
        this(webviewfragment);
    }

    public final void onFormResubmission(WebView webview, Message message, Message message1)
    {
        message1.sendToTarget();
    }

    public final void onPageFinished(WebView webview, String s)
    {
        boolean flag;
        flag = true;
        super.onPageFinished(webview, s);
        if (!s.equals("file:///android_asset/myerrorpage.html"))
        {
            break MISSING_BLOCK_LABEL_147;
        }
        WebBackForwardList webbackforwardlist = WebViewFragment.c(a).copyBackForwardList();
        if (webbackforwardlist != null)
        {
            try
            {
                if (webbackforwardlist.getSize() > 0)
                {
                    String s1 = webbackforwardlist.getItemAtIndex(-1 + WebViewFragment.c(a).copyBackForwardList().getCurrentIndex()).getOriginalUrl();
                    if (s1.equalsIgnoreCase(WebViewFragment.a(a)))
                    {
                        WebViewFragment.b(a, true);
                        WebViewFragment.c(a).postUrl(s1, WebViewFragment.d(a).getBytes());
                        WebViewFragment.a(a, true);
                        return;
                    }
                }
            }
            catch (Exception exception) { }
        }
        WebViewFragment.e(a);
        WebViewFragment.b(a);
        a.popFragmentStack();
        return;
        boolean flag1;
        if (!StringUtils.isNullOrEmpty(s) && s.contains("/orderresponse"))
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (flag1 && WebViewFragment.c(a) != null)
        {
            WebViewFragment.c(a).clearHistory();
        }
        GsonRequest.updateSessionFromWebview(CookieParser.parse(CookieManager.getInstance().getCookie(s)));
        WebViewFragment.a(a, s);
        if (!s.contains("handleLoginStage"))
        {
            flag = false;
        }
        if (flag)
        {
            SessionUtils.readSessionInfo();
        }
        if (WebViewFragment.f(a))
        {
            WebViewFragment.b(a, false);
            if (WebViewFragment.c(a) != null)
            {
                WebViewFragment.c(a).clearHistory();
            }
        }
        WebViewFragment.a(a, bF.b, "");
        return;
    }

    public final void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        super.onPageStarted(webview, s, bitmap);
        WebViewFragment.a(a, bF.a, "");
    }

    public final void onReceivedError(WebView webview, int i, String s, String s1)
    {
        if (s.contains("ERR_CACHE_MISS"))
        {
            WebViewFragment.c(a).loadUrl("file:///android_asset/myerrorpage.html");
        } else
        {
            super.onReceivedError(webview, i, s, s1);
            boolean flag;
            if (s1.contains("perfTracker"))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                FkLogger.debug(FlipkartBaseFragment.TAG, (new StringBuilder("Error recieved on webview:")).append(s).append(":").append(s1).append(" errocode :").append(i).toString());
                String s2;
                if (!NetworkMonitor.isNetworkAvailable())
                {
                    s2 = "Network not available. Please check your network settings and try again.";
                } else
                {
                    s2 = "Sorry! We are unable to reach Flipkart.com. Please try again later.";
                }
                WebViewFragment.a(a, bF.d, s2);
                return;
            }
        }
    }

    public final void onReceivedSslError(WebView webview, SslErrorHandler sslerrorhandler, SslError sslerror)
    {
        super.onReceivedSslError(webview, sslerrorhandler, sslerror);
        sslerrorhandler.proceed();
    }

    public final boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        super.shouldOverrideUrlLoading(webview, s);
        boolean flag;
        boolean flag1;
        if (WebViewFragment.g(a) != null && (WebViewFragment.g(a).contains("account/login") || WebViewFragment.g(a).contains("account/signup")))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            SessionUtils.readSessionInfo();
        }
        if (!s.equalsIgnoreCase(WebViewFragment.a(a)))
        {
            WebViewFragment.a(a, false);
        }
        if (s.startsWith("tel:"))
        {
            Intent intent = new Intent("android.intent.action.DIAL", Uri.parse(s));
            a.startActivity(intent);
            flag1 = true;
        } else
        {
            AppActionUrlHelper appactionurlhelper = new AppActionUrlHelper();
            if (appactionurlhelper.shouldAppHandleUrl(s))
            {
                AppParams appparams = appactionurlhelper.readAppActionUrl(s);
                WebViewFragment.b(a);
                if (appparams != null && appparams.getAction().equals(AppAction.back))
                {
                    if (a.activity instanceof HomeFragmentHolderActivity)
                    {
                        ((HomeFragmentHolderActivity)a.activity).popFragmentStack();
                    }
                } else
                {
                    FkLogger.debug("handleurl", (new StringBuilder("into the handle url ")).append(appparams.getAction()).toString());
                    appactionurlhelper.handleUrl(appparams, a.activity);
                }
                return true;
            }
            String s1 = StringUtils.trim(StringUtils.trim(StringUtils.trim(s, "?"), "#"), "/");
            boolean flag2;
            if (s1.endsWith("/m") || s1.endsWith("m.flipkart.com") || s1.contains("/m?") || s1.contains("/m/?") || s1.contains("m.flipkart.com?") || s1.contains("m.flipkart.com/?"))
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            flag1 = false;
            if (flag2)
            {
                WebViewFragment.b(a);
                appactionurlhelper.handleUrl(appactionurlhelper.readAppActionUrl((new StringBuilder()).append(UrlManagementConstants.ACTION_URL_PREFIX).append(AppAction.home_page).toString()), a.activity);
                return true;
            }
        }
        return flag1;
    }
}
