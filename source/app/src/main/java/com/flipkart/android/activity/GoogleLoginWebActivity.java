// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.flipkart.android.activity.base.FlipkartBaseFragmentActivity;
import com.flipkart.android.analytics.PageName;
import com.flipkart.android.analytics.PageType;
import com.flipkart.android.analytics.TrackingHelper;
import com.flipkart.android.login.GoogleLoginConstants;
import com.flipkart.android.login.onGoogleLoginWebViewClientListener;
import com.flipkart.android.utils.FkLoadingDialog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.flipkart.android.activity:
//            b

public class GoogleLoginWebActivity extends FlipkartBaseFragmentActivity
    implements onGoogleLoginWebViewClientListener
{

    public static String KKeyAuthCode = "auth_code";
    public static String KKeyAuthCodeStatus = "auth_status";
    public static String KValueAuthCodeStatusFailure = "failure";
    public static String KValueAuthCodeStatusSuccess = "success";
    private WebView a;
    private FkLoadingDialog b;

    public GoogleLoginWebActivity()
    {
    }

    private static String a(String s, Map map)
    {
        if (!s.contains("?"))
        {
            s = (new StringBuilder()).append(s).append("?").toString();
        }
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            String s2 = URLEncoder.encode(s1, "UTF-8");
            String s3 = URLEncoder.encode((String)map.get(s1), "UTF-8");
            s = (new StringBuilder()).append(s).append(s2).append("=").append(s3).append("&").toString();
        }

        return s;
    }

    private void a(int i, Intent intent)
    {
        Activity activity = getParent();
        if (activity == null)
        {
            setResult(-1, intent);
        } else
        {
            activity.setResult(-1, intent);
        }
        finish();
    }

    private static void a(FkLoadingDialog fkloadingdialog)
    {
        if (fkloadingdialog == null)
        {
            break MISSING_BLOCK_LABEL_9;
        }
        fkloadingdialog.dismissDlg();
        return;
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
    }

    public void offerGoogleLoginWebViewClientError(com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientErrorType tgoogleloginwebclienterrortype)
    {
        a(b);
        Intent intent = new Intent();
        intent.putExtra(KKeyAuthCodeStatus, KValueAuthCodeStatusFailure);
        a(-1, intent);
    }

    public void offerGoogleLoginWebViewClientEvent(com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType tgoogleloginwebclienteventtype, String s)
    {
        if (tgoogleloginwebclienteventtype == com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType.EEventAuthCodeReceived)
        {
            a(b);
            Intent intent1 = new Intent();
            intent1.putExtra(KKeyAuthCodeStatus, KValueAuthCodeStatusSuccess);
            intent1.putExtra(KKeyAuthCode, s);
            a(-1, intent1);
            return;
        }
        if (tgoogleloginwebclienteventtype == com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType.EEventPageLoadStarted)
        {
            b.showDlg("", "Loading. Please wait...", null, false);
            return;
        }
        if (tgoogleloginwebclienteventtype == com.flipkart.android.login.onGoogleLoginWebViewClientListener.TGoogleLoginWebClientEventType.EEventPageLoadFinished)
        {
            a(b);
            return;
        } else
        {
            a(b);
            Intent intent = new Intent();
            intent.putExtra(KKeyAuthCodeStatus, KValueAuthCodeStatusFailure);
            a(-1, intent);
            return;
        }
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        setRequestedOrientation(1);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f0300c3);
        b = new FkLoadingDialog(this);
        a = (WebView)findViewById(0x7f0a0238);
        a.setWebViewClient(new b(this, this, this));
        a.getSettings().setJavaScriptEnabled(true);
        a.clearCache(true);
        a.clearHistory();
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeAllCookie();
        try
        {
            String s = GoogleLoginConstants.KValueAuthCodeBaseUrl;
            HashMap hashmap = new HashMap();
            hashmap.put(GoogleLoginConstants.KKeyResponseType, GoogleLoginConstants.KValueResponseTypeCode);
            hashmap.put(GoogleLoginConstants.KKeyClientId, GoogleLoginConstants.KValueClientId);
            hashmap.put(GoogleLoginConstants.KKeyRedirectUri, GoogleLoginConstants.KValueRedirectUriLocalhost);
            hashmap.put(GoogleLoginConstants.KKeyScope, GoogleLoginConstants.KValueScopeUserEmail);
            hashmap.put(GoogleLoginConstants.KKeyState, "");
            String s1 = a(s, hashmap);
            a.loadUrl(s1);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            Intent intent = new Intent();
            intent.putExtra(KKeyAuthCodeStatus, KValueAuthCodeStatusFailure);
            a(-1, intent);
        }
        TrackingHelper.sendPageView(PageName.GoogleWebLoginPage.name(), PageType.Login);
    }

}
