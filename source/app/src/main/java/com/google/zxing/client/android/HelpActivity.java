// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.zxing.client.android:
//            LocaleManager, j, k, l

public final class HelpActivity extends Activity
    implements TraceFieldInterface
{

    public static final String DEFAULT_PAGE = "index.html";
    public static final String REQUESTED_PAGE_KEY = "requested_page_key";
    public static final String WHATS_NEW_PAGE = "whatsnew.html";
    private static final String a = (new StringBuilder("file:///android_asset/html-")).append(LocaleManager.getTranslatedAssetLanguage()).append('/').toString();
    private WebView b;
    private Button c;
    private final android.view.View.OnClickListener d = new j(this);
    private final android.view.View.OnClickListener e = new k(this);

    public HelpActivity()
    {
    }

    static WebView a(HelpActivity helpactivity)
    {
        return helpactivity.b;
    }

    static Button b(HelpActivity helpactivity)
    {
        return helpactivity.c;
    }

    protected final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("HelpActivity");
        TraceMachine.enterMethod(_nr_trace, "HelpActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        setContentView(R.layout.help);
        b = (WebView)findViewById(R.id.help_contents);
        b.setWebViewClient(new l(this, (byte)0));
        Intent intent = getIntent();
        NoSuchFieldError nosuchfielderror;
        if (bundle != null && bundle.getBoolean("webview_state_present", false))
        {
            b.restoreState(bundle);
        } else
        if (intent != null)
        {
            String s = intent.getStringExtra("requested_page_key");
            if (s != null && s.length() > 0)
            {
                b.loadUrl((new StringBuilder()).append(a).append(s).toString());
            } else
            {
                b.loadUrl((new StringBuilder()).append(a).append("index.html").toString());
            }
        } else
        {
            b.loadUrl((new StringBuilder()).append(a).append("index.html").toString());
        }
        c = (Button)findViewById(R.id.back_button);
        c.setOnClickListener(d);
        findViewById(R.id.done_button).setOnClickListener(e);
        TraceMachine.exitMethod();
        return;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "HelpActivity#onCreate", null);
          goto _L1
    }

    public final boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if (i == 4 && b.canGoBack())
        {
            b.goBack();
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    protected final void onSaveInstanceState(Bundle bundle)
    {
        String s = b.getUrl();
        if (s != null && s.length() > 0)
        {
            b.saveState(bundle);
            bundle.putBoolean("webview_state_present", true);
        }
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    protected void onStop()
    {
        super.onStop();
        ApplicationStateMonitor.getInstance().activityStopped();
    }

}
