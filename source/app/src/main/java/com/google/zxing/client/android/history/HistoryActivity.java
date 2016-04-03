// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.history;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.flipkart.logging.FkLogger;
import com.google.zxing.client.android.CaptureActivity;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.history:
//            HistoryManager, c, HistoryItem, b

public final class HistoryActivity extends ListActivity
    implements TraceFieldInterface
{

    private static final String a = com/google/zxing/client/android/history/HistoryActivity.getSimpleName();
    private HistoryManager b;
    private c c;

    public HistoryActivity()
    {
    }

    static HistoryManager a(HistoryActivity historyactivity)
    {
        return historyactivity.b;
    }

    private void a()
    {
        List list = b.buildHistoryItems();
        c.clear();
        HistoryItem historyitem;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); c.add(historyitem))
        {
            historyitem = (HistoryItem)iterator.next();
        }

        if (c.isEmpty())
        {
            c.add(new HistoryItem(null, null, null));
        }
    }

    public final boolean onContextItemSelected(MenuItem menuitem)
    {
        int i = menuitem.getItemId();
        b.deleteHistoryItem(i);
        a();
        return true;
    }

    protected final void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("HistoryActivity");
        TraceMachine.enterMethod(_nr_trace, "HistoryActivity#onCreate", null);
_L1:
        super.onCreate(bundle);
        b = new HistoryManager(this);
        c = new c(this);
        setListAdapter(c);
        registerForContextMenu(getListView());
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "HistoryActivity#onCreate", null);
          goto _L1
    }

    public final void onCreateContextMenu(ContextMenu contextmenu, View view, android.view.ContextMenu.ContextMenuInfo contextmenuinfo)
    {
        int i = ((android.widget.AdapterView.AdapterContextMenuInfo)contextmenuinfo).position;
        if (i >= c.getCount() || ((HistoryItem)c.getItem(i)).getResult() != null)
        {
            contextmenu.add(0, i, i, com.google.zxing.client.android.R.string.history_clear_one_history_text);
        }
    }

    public final boolean onCreateOptionsMenu(Menu menu)
    {
        if (b.hasHistoryItems())
        {
            getMenuInflater().inflate(com.google.zxing.client.android.R.menu.history, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    protected final void onListItemClick(ListView listview, View view, int i, long l)
    {
        if (((HistoryItem)c.getItem(i)).getResult() != null)
        {
            Intent intent = new Intent(this, com/google/zxing/client/android/CaptureActivity);
            intent.putExtra("ITEM_NUMBER", i);
            setResult(-1, intent);
            finish();
        }
    }

    public final boolean onOptionsItemSelected(MenuItem menuitem)
    {
        int i = menuitem.getItemId();
        if (i == com.google.zxing.client.android.R.id.menu_history_send)
        {
            Uri uri = HistoryManager.a(b.a().toString());
            if (uri == null)
            {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(this);
                builder1.setMessage(com.google.zxing.client.android.R.string.msg_unmount_usb);
                builder1.setPositiveButton(com.google.zxing.client.android.R.string.button_ok, null);
                builder1.show();
                return true;
            }
            Intent intent = new Intent("android.intent.action.SEND", Uri.parse("mailto:"));
            intent.addFlags(0x80000);
            String s = getResources().getString(com.google.zxing.client.android.R.string.history_email_title);
            intent.putExtra("android.intent.extra.SUBJECT", s);
            intent.putExtra("android.intent.extra.TEXT", s);
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setType("text/csv");
            try
            {
                startActivity(intent);
            }
            catch (ActivityNotFoundException activitynotfoundexception)
            {
                FkLogger.warn(a, activitynotfoundexception.toString());
                return true;
            }
            return true;
        }
        if (i == com.google.zxing.client.android.R.id.menu_history_clear_text)
        {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setMessage(com.google.zxing.client.android.R.string.msg_sure);
            builder.setCancelable(true);
            builder.setPositiveButton(com.google.zxing.client.android.R.string.button_ok, new b(this));
            builder.setNegativeButton(com.google.zxing.client.android.R.string.button_cancel, null);
            builder.show();
            return true;
        } else
        {
            return super.onOptionsItemSelected(menuitem);
        }
    }

    protected final void onResume()
    {
        super.onResume();
        a();
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
