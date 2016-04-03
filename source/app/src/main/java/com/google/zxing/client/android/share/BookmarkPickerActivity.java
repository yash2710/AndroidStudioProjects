// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.ListView;
import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;

// Referenced classes of package com.google.zxing.client.android.share:
//            b

public final class BookmarkPickerActivity extends ListActivity
    implements TraceFieldInterface
{

    private static final String a = com/google/zxing/client/android/share/BookmarkPickerActivity.getSimpleName();
    private static final String b[] = {
        "title", "url"
    };
    private Cursor c;

    public BookmarkPickerActivity()
    {
        c = null;
    }

    protected final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("BookmarkPickerActivity");
        TraceMachine.enterMethod(_nr_trace, "BookmarkPickerActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        c = getContentResolver().query(Browser.BOOKMARKS_URI, b, "bookmark = 1 AND url IS NOT NULL", null, null);
        NoSuchFieldError nosuchfielderror;
        if (c == null)
        {
            FkLogger.warn(a, "No cursor returned for bookmark query");
            finish();
            TraceMachine.exitMethod();
            return;
        } else
        {
            startManagingCursor(c);
            setListAdapter(new b(this, c));
            TraceMachine.exitMethod();
            return;
        }
        nosuchfielderror;
        TraceMachine.enterMethod(null, "BookmarkPickerActivity#onCreate", null);
          goto _L1
    }

    protected final void onListItemClick(ListView listview, View view, int i, long l)
    {
        if (!c.isClosed() && c.moveToPosition(i))
        {
            Intent intent = new Intent();
            intent.addFlags(0x80000);
            intent.putExtra("title", c.getString(0));
            intent.putExtra("url", c.getString(1));
            setResult(-1, intent);
        } else
        {
            setResult(0);
        }
        finish();
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
