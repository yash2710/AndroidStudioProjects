// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.share;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.share:
//            a, d

final class c extends AsyncTask
    implements TraceFieldInterface
{

    private static final String a[] = {
        "com.google.android.apps."
    };
    private static final String b[] = {
        "com.android.", "android", "com.google.android.", "com.htc"
    };
    public Trace _nr_trace;
    private final ListActivity c;

    c(ListActivity listactivity)
    {
        c = listactivity;
    }

    private static boolean a(String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        String as[] = a;
        for (int i = 0; i <= 0; i++)
        {
            if (s.startsWith(as[0]))
            {
                return false;
            }
        }

        String as1[] = b;
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= 4)
                {
                    break label1;
                }
                if (s.startsWith(as1[j]))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public void _nr_setTrace(Trace trace)
    {
        try
        {
            _nr_trace = trace;
            return;
        }
        catch (Exception exception)
        {
            return;
        }
    }

    protected final volatile Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "c#doInBackground", null);
_L1:
        List list = doInBackground((Void[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return list;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "c#doInBackground", null);
          goto _L1
    }

    protected final transient List doInBackground(Void avoid[])
    {
        ArrayList arraylist = new ArrayList();
        PackageManager packagemanager = c.getPackageManager();
        Iterator iterator = packagemanager.getInstalledApplications(0).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            ApplicationInfo applicationinfo = (ApplicationInfo)iterator.next();
            String s = applicationinfo.packageName;
            if (!a(s))
            {
                CharSequence charsequence = applicationinfo.loadLabel(packagemanager);
                android.graphics.drawable.Drawable drawable = applicationinfo.loadIcon(packagemanager);
                if (charsequence != null)
                {
                    arraylist.add(new a(s, charsequence.toString(), drawable));
                }
            }
        } while (true);
        Collections.sort(arraylist);
        return arraylist;
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "c#onPostExecute", null);
_L1:
        onPostExecute((List)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "c#onPostExecute", null);
          goto _L1
    }

    protected final void onPostExecute(List list)
    {
        d d1 = new d(this, c, com.google.zxing.client.android.R.layout.app_picker_list_item, com.google.zxing.client.android.R.id.app_picker_list_item_label, list, list);
        c.setListAdapter(d1);
    }

}
