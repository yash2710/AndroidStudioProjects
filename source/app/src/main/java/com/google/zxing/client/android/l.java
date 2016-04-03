// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

// Referenced classes of package com.google.zxing.client.android:
//            HelpActivity

final class l extends WebViewClient
{

    private HelpActivity a;

    private l(HelpActivity helpactivity)
    {
        a = helpactivity;
        super();
    }

    l(HelpActivity helpactivity, byte byte0)
    {
        this(helpactivity);
    }

    public final void onPageFinished(WebView webview, String s)
    {
        a.setTitle(webview.getTitle());
        HelpActivity.b(a).setEnabled(webview.canGoBack());
    }

    public final boolean shouldOverrideUrlLoading(WebView webview, String s)
    {
        if (s.startsWith("file"))
        {
            return false;
        } else
        {
            a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
            return true;
        }
    }
}
