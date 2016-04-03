// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;
import com.flipkart.logging.FkLogger;
import com.google.zxing.client.android.common.executor.AsyncTaskExecInterface;
import com.google.zxing.client.android.common.executor.AsyncTaskExecManager;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.ISBNParsedResult;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ProductParsedResult;
import com.google.zxing.client.result.URIParsedResult;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.zxing.client.android.result.supplement:
//            e, d, c, a, 
//            b

public abstract class SupplementalInfoRetriever extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private final WeakReference a;
    private final WeakReference b;
    private final List c = new ArrayList();
    private final List d = new ArrayList();

    SupplementalInfoRetriever(TextView textview, HistoryManager historymanager)
    {
        a = new WeakReference(textview);
        b = new WeakReference(historymanager);
    }

    static void a(String s, Collection collection)
    {
        if (s != null && s.length() > 0)
        {
            collection.add(s);
        }
    }

    static void a(Collection collection, Collection collection1)
    {
        if (collection != null && !collection.isEmpty())
        {
            StringBuilder stringbuilder = new StringBuilder();
            Iterator iterator = collection.iterator();
            boolean flag = true;
            while (iterator.hasNext()) 
            {
                String s = (String)iterator.next();
                if (flag)
                {
                    flag = false;
                } else
                {
                    stringbuilder.append(", ");
                }
                stringbuilder.append(s);
            }
            collection1.add(stringbuilder.toString());
        }
    }

    public static void maybeInvokeRetrieval(TextView textview, ParsedResult parsedresult, HistoryManager historymanager, Context context)
    {
        AsyncTaskExecInterface asynctaskexecinterface = (AsyncTaskExecInterface)(new AsyncTaskExecManager()).build();
        if (parsedresult instanceof URIParsedResult)
        {
            asynctaskexecinterface.execute(new e(textview, (URIParsedResult)parsedresult, historymanager, context), new Object[0]);
            asynctaskexecinterface.execute(new d(textview, (URIParsedResult)parsedresult, historymanager), new Object[0]);
        } else
        {
            if (parsedresult instanceof ProductParsedResult)
            {
                String s1 = ((ProductParsedResult)parsedresult).getProductID();
                asynctaskexecinterface.execute(new c(textview, s1, historymanager, context), new Object[0]);
                switch (s1.length())
                {
                default:
                    return;

                case 12: // '\f'
                    asynctaskexecinterface.execute(new a(textview, "UPC", s1, historymanager, context), new Object[0]);
                    return;

                case 13: // '\r'
                    asynctaskexecinterface.execute(new a(textview, "EAN", s1, historymanager, context), new Object[0]);
                    break;
                }
                return;
            }
            if (parsedresult instanceof ISBNParsedResult)
            {
                String s = ((ISBNParsedResult)parsedresult).getISBN();
                asynctaskexecinterface.execute(new c(textview, s, historymanager, context), new Object[0]);
                asynctaskexecinterface.execute(new b(textview, s, historymanager, context), new Object[0]);
                asynctaskexecinterface.execute(new a(textview, "ISBN", s, historymanager, context), new Object[0]);
                return;
            }
        }
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

    abstract void a();

    final void a(String s, String s1, String as[], String s2)
    {
        int i;
        int l;
        String s3;
        SpannableString spannablestring;
        StringBuilder stringbuilder = new StringBuilder();
        if (s1 != null)
        {
            stringbuilder.append(s1).append(' ');
        }
        i = stringbuilder.length();
        int j = as.length;
        int k = 0;
        boolean flag = true;
        while (k < j) 
        {
            String s4 = as[k];
            if (flag)
            {
                stringbuilder.append(s4);
                flag = false;
            } else
            {
                stringbuilder.append(" [");
                stringbuilder.append(s4);
                stringbuilder.append(']');
            }
            k++;
        }
        l = stringbuilder.length();
        s3 = stringbuilder.toString();
        spannablestring = new SpannableString((new StringBuilder()).append(s3).append("\n\n").toString());
        if (s2 == null) goto _L2; else goto _L1
_L1:
        if (!s2.startsWith("HTTP://")) goto _L4; else goto _L3
_L3:
        s2 = (new StringBuilder("http")).append(s2.substring(4)).toString();
_L6:
        spannablestring.setSpan(new URLSpan(s2), i, l, 33);
_L2:
        c.add(spannablestring);
        d.add(new String[] {
            s, s3
        });
        return;
_L4:
        if (s2.startsWith("HTTPS://"))
        {
            s2 = (new StringBuilder("https")).append(s2.substring(5)).toString();
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final transient Object doInBackground(Object aobj[])
    {
        TraceMachine.enterMethod(_nr_trace, "SupplementalInfoRetriever#doInBackground", null);
_L1:
        NoSuchFieldError nosuchfielderror;
        try
        {
            a();
        }
        catch (IOException ioexception)
        {
            FkLogger.warn("SupplementalInfo", ioexception);
        }
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return null;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupplementalInfoRetriever#doInBackground", null);
          goto _L1
    }

    protected final void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "SupplementalInfoRetriever#onPostExecute", null);
_L2:
        TextView textview;
        textview = (TextView)a.get();
        if (textview == null)
        {
            break MISSING_BLOCK_LABEL_81;
        }
        for (Iterator iterator1 = c.iterator(); iterator1.hasNext(); textview.append((Spannable)iterator1.next())) { }
        break; /* Loop/switch isn't completed */
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "SupplementalInfoRetriever#onPostExecute", null);
        if (true) goto _L2; else goto _L1
_L1:
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        HistoryManager historymanager = (HistoryManager)b.get();
        if (historymanager != null)
        {
            String as[];
            for (Iterator iterator = d.iterator(); iterator.hasNext(); historymanager.addHistoryItemDetails(as[0], as[1]))
            {
                as = (String[])iterator.next();
            }

        }
        TraceMachine.exitMethod();
        return;
    }
}
