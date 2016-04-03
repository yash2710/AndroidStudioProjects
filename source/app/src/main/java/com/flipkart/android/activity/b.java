// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.flipkart.android.login.GoogleLoginConstants;
import com.flipkart.android.login.onGoogleLoginWebViewClientListener;
import com.flipkart.android.utils.StringUtils;

// Referenced classes of package com.flipkart.android.activity:
//            c, d, GoogleLoginWebActivity

final class b extends WebViewClient
{

    private Activity a;
    private onGoogleLoginWebViewClientListener b;

    b(GoogleLoginWebActivity googleloginwebactivity, Activity activity, onGoogleLoginWebViewClientListener ongoogleloginwebviewclientlistener)
    {
        a = activity;
        b = ongoogleloginwebviewclientlistener;
    }

    static onGoogleLoginWebViewClientListener a(b b1)
    {
        return b1.b;
    }

    private void a(com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType tgoogleloginwebclienteventtype, String s)
    {
        a.runOnUiThread(new c(this, tgoogleloginwebclienteventtype, s));
    }

    public final void onPageFinished(WebView webview, String s)
    {
        super.onPageFinished(webview, s);
        a(com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType.EEventPageLoadFinished, "");
    }

    public final void onPageStarted(WebView webview, String s, Bitmap bitmap)
    {
        super.onPageStarted(webview, s, bitmap);
        a(com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType.EEventPageLoadStarted, "");
        Uri uri = Uri.parse(s);
        if (uri == null || StringUtils.isNullOrEmpty(uri.getHost()))
        {
            return;
        }
        if (uri.getHost().equals(GoogleLoginConstants.KValueRedirectUriLocalhostDomain))
        {
            String s1 = uri.getQueryParameter(GoogleLoginConstants.KKeyCode);
            if (s1 != null)
            {
                a(com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType.EEventAuthCodeReceived, s1);
                return;
            } else
            {
                com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientErrorType tgoogleloginwebclienterrortype = com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientErrorType.EErrorUnableToParseCode;
                a.runOnUiThread(new d(this, tgoogleloginwebclienterrortype));
                return;
            }
        } else
        {
            super.onPageStarted(webview, s, bitmap);
            return;
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        webview.loadUrl(s);
        return true;
    }
}
