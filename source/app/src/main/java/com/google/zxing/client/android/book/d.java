// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.book;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.flipkart.logging.FkLogger;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.LocaleManager;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.zxing.client.android.book:
//            SearchBookContentsActivity, f, a, e

final class d extends AsyncTask
    implements TraceFieldInterface
{

    public Trace _nr_trace;
    private SearchBookContentsActivity a;

    private d(SearchBookContentsActivity searchbookcontentsactivity)
    {
        a = searchbookcontentsactivity;
        super();
    }

    d(SearchBookContentsActivity searchbookcontentsactivity, byte byte0)
    {
        this(searchbookcontentsactivity);
    }

    private f a(JSONObject jsonobject)
    {
        String s;
        String s1;
        s = jsonobject.getString("page_id");
        s1 = jsonobject.getString("page_number");
        if (s1.length() <= 0) goto _L2; else goto _L1
_L1:
        String s2 = (new StringBuilder()).append(a.getString(com.google.zxing.client.android.R.string.msg_sbc_page)).append(' ').append(s1).toString();
_L7:
        String s3 = jsonobject.optString("snippet_text");
        if (s3.length() <= 0) goto _L4; else goto _L3
_L3:
        String s5;
        String s6 = SearchBookContentsActivity.c().matcher(s3).replaceAll("");
        String s7 = SearchBookContentsActivity.d().matcher(s6).replaceAll("<");
        String s8 = SearchBookContentsActivity.e().matcher(s7).replaceAll(">");
        String s9 = SearchBookContentsActivity.f().matcher(s8).replaceAll("'");
        s5 = SearchBookContentsActivity.g().matcher(s9).replaceAll("\"");
        boolean flag = true;
_L5:
        return new f(s, s2, s5, flag);
_L2:
        s2 = a.getString(com.google.zxing.client.android.R.string.msg_sbc_unknown_page);
        continue; /* Loop/switch isn't completed */
_L4:
        String s4 = (new StringBuilder("(")).append(a.getString(com.google.zxing.client.android.R.string.msg_sbc_snippet_unavailable)).append(')').toString();
        s5 = s4;
        flag = false;
          goto _L5
        JSONException jsonexception;
        jsonexception;
        return new f(a.getString(com.google.zxing.client.android.R.string.msg_sbc_no_page_returned), "", "", false);
        if (true) goto _L7; else goto _L6
_L6:
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
        TraceMachine.enterMethod(_nr_trace, "d#doInBackground", null);
_L1:
        JSONObject jsonobject = doInBackground((String[])aobj);
        TraceMachine.exitMethod();
        TraceMachine.unloadTraceContext(this);
        return jsonobject;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "d#doInBackground", null);
          goto _L1
    }

    protected final transient JSONObject doInBackground(String as[])
    {
        String s;
        String s1;
        s = as[0];
        s1 = as[1];
        if (!LocaleManager.isBookSearchUrl(s1)) goto _L2; else goto _L1
_L1:
        String s3;
        String s4 = s1.substring(1 + s1.indexOf('='));
        s3 = (new StringBuilder("http://www.google.com/books?id=")).append(s4).append("&jscmd=SearchWithinVolume2&q=").append(s).toString();
_L3:
        CharSequence charsequence = HttpHelper.downloadViaHttp(s3, com.google.zxing.client.android.HttpHelper.ContentType.JSON);
        JVM INSTR new #28  <Class JSONObject>;
        return JSONObjectInstrumentation.init(charsequence.toString());
_L2:
        String s2 = (new StringBuilder("http://www.google.com/books?vid=isbn")).append(s1).append("&jscmd=SearchWithinVolume2&q=").append(s).toString();
        s3 = s2;
          goto _L3
        IOException ioexception;
        ioexception;
        FkLogger.warn(SearchBookContentsActivity.b(), "Error accessing book search", ioexception);
        return null;
        JSONException jsonexception;
        jsonexception;
        FkLogger.warn(SearchBookContentsActivity.b(), "Error accessing book search", jsonexception);
        return null;
    }

    protected final volatile void onPostExecute(Object obj)
    {
        TraceMachine.enterMethod(_nr_trace, "d#onPostExecute", null);
_L1:
        onPostExecute((JSONObject)obj);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "d#onPostExecute", null);
          goto _L1
    }

    protected final void onPostExecute(JSONObject jsonobject)
    {
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        SearchBookContentsActivity.b(a).setText(com.google.zxing.client.android.R.string.msg_sbc_failed);
_L5:
        SearchBookContentsActivity.c(a).setEnabled(true);
        SearchBookContentsActivity.c(a).selectAll();
        SearchBookContentsActivity.d(a).setEnabled(true);
        return;
_L2:
        int i;
        i = jsonobject.getInt("number_of_results");
        SearchBookContentsActivity.b(a).setText((new StringBuilder()).append(a.getString(com.google.zxing.client.android.R.string.msg_sbc_results)).append(" : ").append(i).toString());
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_266;
        }
        JSONArray jsonarray;
        ArrayList arraylist;
        jsonarray = jsonobject.getJSONArray("search_results");
        f.setQuery(SearchBookContentsActivity.c(a).getText().toString());
        arraylist = new ArrayList(i);
        int j = 0;
_L4:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        arraylist.add(a(jsonarray.getJSONObject(j)));
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        try
        {
            SearchBookContentsActivity.e(a).setOnItemClickListener(new a(a, arraylist));
            SearchBookContentsActivity.e(a).setAdapter(new e(a, arraylist));
        }
        catch (JSONException jsonexception)
        {
            FkLogger.warn(SearchBookContentsActivity.b(), "Bad JSON from book search", jsonexception);
            SearchBookContentsActivity.e(a).setAdapter(null);
            SearchBookContentsActivity.b(a).setText(com.google.zxing.client.android.R.string.msg_sbc_failed);
        }
          goto _L5
        if ("false".equals(jsonobject.optString("searchable")))
        {
            SearchBookContentsActivity.b(a).setText(com.google.zxing.client.android.R.string.msg_sbc_book_not_searchable);
        }
        SearchBookContentsActivity.e(a).setAdapter(null);
          goto _L5
    }
}
