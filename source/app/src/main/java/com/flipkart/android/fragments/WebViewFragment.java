// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.flipkart.android.activity.HomeFragmentHolderActivity;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.config.FlipkartDeviceInfoProvider;
import com.flipkart.android.config.FlipkartPreferenceManager;
import com.flipkart.android.customviews.ActionBarView;
import com.flipkart.android.customviews.enums.ActionBarState;
import com.flipkart.android.log.CrashLoggerUtils;
import com.flipkart.android.utils.FkLoadingDialog;
import com.flipkart.android.utils.StringUtils;
import com.flipkart.android.utils.ToastMessageUtils;
import com.flipkart.android.utils.UrlUtils;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.flipkart.android.fragments:
//            FlipkartBaseFragment, bD, bF, bE, 
//            bB, bC

public class WebViewFragment extends FlipkartBaseFragment
    implements android.content.DialogInterface.OnCancelListener
{

    public static final String WEBVIEW_EXTRAS_DISABLE_LOADING_DIALOG = "WEBVIEW_EXTRAS_DISABLE_LOADING_DIALOG";
    public static final String WEBVIEW_EXTRAS_HIDE_MENU_ITEM = "WEBVIEW_EXTRAS_HIDE_MENU_ITEM";
    public static final String WEBVIEW_EXTRAS_POST_PARAMS = "WEBVIEW_EXTRAS_POST_PARAMS";
    public static final String WEBVIEW_EXTRAS_URL = "WEBVIEW_EXTRAS_URL";
    public static final String WEBVIEW_EXTRAS_VERB = "WEBVIEW_EXTRAS_VERB";
    private WebView a;
    private FkLoadingDialog b;
    private String c;
    private String d;
    private boolean e;
    private Boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private Map k;

    public WebViewFragment()
    {
        d = "";
        e = false;
        k = new HashMap();
    }

    static String a(WebViewFragment webviewfragment)
    {
        return webviewfragment.i;
    }

    static String a(WebViewFragment webviewfragment, String s)
    {
        webviewfragment.j = s;
        return s;
    }

    private void a()
    {
        changeSoftInputMode(34);
        updateCartItems();
    }

    static void a(WebViewFragment webviewfragment, bF bf, String s)
    {
        webviewfragment.a(bf, s);
    }

    private void a(bF bf, String s)
    {
        bD.a[bf.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 41
    //                   2 70
    //                   3 75
    //                   4 84;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        return;
_L2:
        if (b != null && !e)
        {
            b.showDlg("", "Loading...", this, true);
            return;
        }
          goto _L1
_L3:
        b();
        return;
_L4:
        b();
        popFragmentStack();
        return;
_L5:
        b();
        ToastMessageUtils.showErrorToastMessage(s, activity, true);
        a();
        popFragmentStack();
        return;
    }

    static boolean a(WebViewFragment webviewfragment, boolean flag)
    {
        webviewfragment.h = flag;
        return flag;
    }

    private void b()
    {
        try
        {
            if (b != null)
            {
                b.dismissDlg();
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            return;
        }
    }

    static void b(WebViewFragment webviewfragment)
    {
        webviewfragment.a();
    }

    static boolean b(WebViewFragment webviewfragment, boolean flag)
    {
        webviewfragment.g = flag;
        return flag;
    }

    static WebView c(WebViewFragment webviewfragment)
    {
        return webviewfragment.a;
    }

    private String c()
    {
        return UrlUtils.addPostParameter(UrlUtils.addPostParameter(UrlUtils.addPostParameter(UrlUtils.addPostParameter(d, "_vid_", FlipkartPreferenceManager.instance().getVid()), "_nsid_", FlipkartPreferenceManager.instance().getNsid()), "appVisitorId", FlipkartPreferenceManager.instance().getOmnitureVisitorId()), "_sn_", FlipkartPreferenceManager.instance().getSn());
    }

    static String d(WebViewFragment webviewfragment)
    {
        return webviewfragment.c();
    }

    static void e(WebViewFragment webviewfragment)
    {
        webviewfragment.b();
    }

    static boolean f(WebViewFragment webviewfragment)
    {
        return webviewfragment.g;
    }

    static String g(WebViewFragment webviewfragment)
    {
        return webviewfragment.j;
    }

    public void changeSoftInputMode(int l)
    {
        try
        {
            activity.getWindow().setSoftInputMode(l);
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    public boolean handleBackPress()
    {
        if (a != null && a.isFocused() && a.canGoBack() && !g && !h)
        {
            a.goBack();
            b();
            return true;
        }
        if (b != null)
        {
            if (!b.isShowing())
            {
                a();
            }
            b();
        }
        return false;
    }

    public boolean handleOnClick()
    {
        return false;
    }

    public void onCancel(DialogInterface dialoginterface)
    {
        b();
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        super.onCreateView(layoutinflater, viewgroup, bundle);
        ViewGroup viewgroup1 = (ViewGroup)layoutinflater.inflate(0x7f0300c3, viewgroup, false);
        if (FlipkartPreferenceManager.instance().isPoppingSearchFragment().booleanValue())
        {
            FlipkartPreferenceManager.instance().saveIsPoppingSearchFragment(Boolean.valueOf(false));
            return viewgroup1;
        }
        Bundle bundle1 = getArguments();
        if (bundle1 != null)
        {
            c = bundle1.getString("WEBVIEW_EXTRAS_URL");
            bundle1.getInt("WEBVIEW_EXTRAS_VERB");
            d = bundle1.getString("WEBVIEW_EXTRAS_POST_PARAMS");
            f = Boolean.valueOf(bundle1.getBoolean("WEBVIEW_EXTRAS_HIDE_MENU_ITEM"));
            e = bundle1.getBoolean("WEBVIEW_EXTRAS_DISABLE_LOADING_DIALOG");
            if (StringUtils.isNullOrEmpty(c))
            {
                a(bF.d, "Malformed Url");
            }
        }
        b = new FkLoadingDialog(activity);
        a = (WebView)viewgroup1.findViewById(0x7f0a0238);
        ActionBarView.setActionBarCustomView(activity, ActionBarState.Default_Back);
        if (f.booleanValue())
        {
            ActionBarView.setActionBarCustomView(activity, ActionBarState.Cart);
        }
        changeSoftInputMode(16);
        CookieSyncManager.createInstance(activity);
        CookieManager.getInstance().removeAllCookie();
        a.setVerticalScrollBarEnabled(true);
        a.setHorizontalScrollBarEnabled(true);
        a.clearView();
        WebSettings websettings = a.getSettings();
        websettings.setJavaScriptEnabled(true);
        websettings.setUserAgentString(FlipkartPreferenceManager.instance().getUserAgent());
        if (FlipkartDeviceInfoProvider.getAndroidSDKVersion() < 11)
        {
            a.getSettings().setSaveFormData(false);
            a.clearFormData();
        }
        a.setWebViewClient(new bE(this, (byte)0));
        a.setWebChromeClient(new bB(this));
        a.addJavascriptInterface(new WebAppInterface(activity), "Android");
        a.getSettings().setRenderPriority(android.webkit.WebSettings.RenderPriority.HIGH);
        a.requestFocus(130);
        a.setOnTouchListener(new bC(this));
        String s = c();
        k.put("Device-Id", FlipkartDeviceInfoProvider.getDeviceId());
        i = c;
        if (StringUtils.isNull(s))
        {
            s = "";
        }
        a.postUrl(c, s.getBytes(Charset.defaultCharset()));
        CrashLoggerUtils.pushAndUpdate((new StringBuilder("creating webview with url: ")).append(c).toString());
        TrackingHelper.sendPageView(PageName.WebPage.name(), PageType.Webview);
        return viewgroup1;
    }

    public void onDestroy()
    {
        b();
        super.onDestroy();
        b = null;
        a = null;
    }

    public void onFragmentPopped()
    {
    }

    public void onFragmentPushed()
    {
        a();
    }

    public void reloadWebview()
    {
        if (a != null && !StringUtils.isNullOrEmpty(i))
        {
            a.clearHistory();
            String s = c();
            if (StringUtils.isNull(s))
            {
                s = "";
            }
            a.postUrl(i, s.getBytes(Charset.defaultCharset()));
            return;
        } else
        {
            popFragmentStack();
            return;
        }
    }

    public void updateCartItems()
    {
        if (activity instanceof HomeFragmentHolderActivity)
        {
            ((HomeFragmentHolderActivity)activity).getAndUpdateCart(null);
        }
    }

    private class WebAppInterface
    {

        private WebViewFragment a;

        public void dismissDialog()
        {
            WebViewFragment.a(a, bF.b, "");
        }

        WebAppInterface(Context context)
        {
            a = WebViewFragment.this;
            super();
        }
    }

}
