// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.book:
//            b, c, d

public final class SearchBookContentsActivity extends Activity
    implements TraceFieldInterface
{

    private static final String a = com/google/zxing/client/android/book/SearchBookContentsActivity.getSimpleName();
    private static final Pattern b = Pattern.compile("\\<.*?\\>");
    private static final Pattern c = Pattern.compile("&lt;");
    private static final Pattern d = Pattern.compile("&gt;");
    private static final Pattern e = Pattern.compile("&#39;");
    private static final Pattern f = Pattern.compile("&quot;");
    private String g;
    private EditText h;
    private Button i;
    private ListView j;
    private TextView k;
    private d l;
    private final AsyncTaskExecInterface m = (AsyncTaskExecInterface)(new AsyncTaskExecManager()).build();
    private final android.view.View.OnClickListener n = new b(this);
    private final android.view.View.OnKeyListener o = new c(this);

    public SearchBookContentsActivity()
    {
    }

    static void a(SearchBookContentsActivity searchbookcontentsactivity)
    {
        String s = searchbookcontentsactivity.h.getText().toString();
        if (s != null && s.length() > 0)
        {
            d d1 = searchbookcontentsactivity.l;
            if (d1 != null)
            {
                d1.cancel(true);
            }
            searchbookcontentsactivity.l = new d(searchbookcontentsactivity, (byte)0);
            AsyncTaskExecInterface asynctaskexecinterface = searchbookcontentsactivity.m;
            d d2 = searchbookcontentsactivity.l;
            String as[] = new String[2];
            as[0] = s;
            as[1] = searchbookcontentsactivity.g;
            asynctaskexecinterface.execute(d2, as);
            searchbookcontentsactivity.k.setText(com.google.zxing.client.android.R.string.msg_sbc_searching_book);
            searchbookcontentsactivity.j.setAdapter(null);
            searchbookcontentsactivity.h.setEnabled(false);
            searchbookcontentsactivity.i.setEnabled(false);
        }
    }

    static TextView b(SearchBookContentsActivity searchbookcontentsactivity)
    {
        return searchbookcontentsactivity.k;
    }

    static String b()
    {
        return a;
    }

    static EditText c(SearchBookContentsActivity searchbookcontentsactivity)
    {
        return searchbookcontentsactivity.h;
    }

    static Pattern c()
    {
        return b;
    }

    static Button d(SearchBookContentsActivity searchbookcontentsactivity)
    {
        return searchbookcontentsactivity.i;
    }

    static Pattern d()
    {
        return c;
    }

    static ListView e(SearchBookContentsActivity searchbookcontentsactivity)
    {
        return searchbookcontentsactivity.j;
    }

    static Pattern e()
    {
        return d;
    }

    static Pattern f()
    {
        return e;
    }

    static Pattern g()
    {
        return f;
    }

    final String a()
    {
        return g;
    }

    public final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("SearchBookContentsActivity");
        TraceMachine.enterMethod(_nr_trace, "SearchBookContentsActivity#onCreate", null);
_L2:
        Intent intent;
        super.onCreate(bundle);
        CookieSyncManager.createInstance(this);
        CookieManager.getInstance().removeExpiredCookie();
        intent = getIntent();
        if (intent == null || !intent.getAction().equals("com.google.zxing.client.android.SEARCH_BOOK_CONTENTS"))
        {
            finish();
            TraceMachine.exitMethod();
            return;
        }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SearchBookContentsActivity#onCreate", null);
        if (true) goto _L2; else goto _L1
_L1:
        g = intent.getStringExtra("ISBN");
        String s;
        if (LocaleManager.isBookSearchUrl(g))
        {
            setTitle(getString(com.google.zxing.client.android.R.string.sbc_name));
        } else
        {
            setTitle((new StringBuilder()).append(getString(com.google.zxing.client.android.R.string.sbc_name)).append(": ISBN ").append(g).toString());
        }
        setContentView(com.google.zxing.client.android.R.layout.search_book_contents);
        h = (EditText)findViewById(com.google.zxing.client.android.R.id.query_text_view);
        s = intent.getStringExtra("QUERY");
        if (s != null && s.length() > 0)
        {
            h.setText(s);
        }
        h.setOnKeyListener(o);
        i = (Button)findViewById(com.google.zxing.client.android.R.id.query_button);
        i.setOnClickListener(n);
        j = (ListView)findViewById(com.google.zxing.client.android.R.id.result_list_view);
        k = (TextView)LayoutInflater.from(this).inflate(com.google.zxing.client.android.R.layout.search_book_contents_header, j, false);
        j.addHeaderView(k);
        TraceMachine.exitMethod();
        return;
    }

    protected final void onPause()
    {
        d d1 = l;
        if (d1 != null)
        {
            d1.cancel(true);
            l = null;
        }
        super.onPause();
    }

    protected final void onResume()
    {
        super.onResume();
        h.selectAll();
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
