// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.history.HistoryManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

// Referenced classes of package com.google.zxing.client.android.result.supplement:
//            SupplementalInfoRetriever

final class b extends SupplementalInfoRetriever
{

    private final String a;
    private final String b;
    private final Context c;

    b(TextView textview, String s, HistoryManager historymanager, Context context)
    {
        super(textview, historymanager);
        a = s;
        b = context.getString(com.google.zxing.client.android.R.string.msg_google_books);
        c = context;
    }

    final void a()
    {
        int i;
        CharSequence charsequence;
        i = 0;
        charsequence = HttpHelper.downloadViaHttp((new StringBuilder("https://www.googleapis.com/books/v1/volumes?q=isbn:")).append(a).toString(), com.google.zxing.client.android.HttpHelper.ContentType.JSON);
        if (charsequence.length() != 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        JSONArray jsonarray;
        JSONObject jsonobject;
        String s;
        String s1;
        JSONArray jsonarray1;
        ArrayList arraylist;
        try
        {
            jsonarray = ((JSONObject)(new JSONTokener(charsequence.toString())).nextValue()).optJSONArray("items");
        }
        catch (JSONException jsonexception)
        {
            throw new IOException(jsonexception.toString());
        }
        if (jsonarray == null) goto _L1; else goto _L3
_L3:
        if (jsonarray.isNull(0)) goto _L1; else goto _L4
_L4:
        jsonobject = ((JSONObject)jsonarray.get(0)).getJSONObject("volumeInfo");
        if (jsonobject == null) goto _L1; else goto _L5
_L5:
        s = jsonobject.optString("title");
        s1 = jsonobject.optString("pageCount");
        jsonarray1 = jsonobject.optJSONArray("authors");
        if (jsonarray1 == null)
        {
            break MISSING_BLOCK_LABEL_196;
        }
        if (jsonarray1.isNull(0))
        {
            break MISSING_BLOCK_LABEL_196;
        }
        arraylist = new ArrayList(jsonarray1.length());
_L6:
        if (i >= jsonarray1.length())
        {
            break MISSING_BLOCK_LABEL_199;
        }
        arraylist.add(jsonarray1.getString(i));
        i++;
          goto _L6
        arraylist = null;
        ArrayList arraylist1 = new ArrayList();
        a(s, ((Collection) (arraylist1)));
        a(((Collection) (arraylist)), ((Collection) (arraylist1)));
        String s2 = null;
        if (s1 != null)
        {
            int j = s1.length();
            s2 = null;
            String s3;
            if (j != 0)
            {
                s2 = (new StringBuilder()).append(s1).append("pp.").toString();
            }
        }
        a(s2, ((Collection) (arraylist1)));
        s3 = (new StringBuilder("http://www.google.")).append(LocaleManager.getBookSearchCountryTLD(c)).append("/search?tbm=bks&source=zxing&q=").toString();
        a(a, b, (String[])arraylist1.toArray(new String[arraylist1.size()]), (new StringBuilder()).append(s3).append(a).toString());
        return;
    }
}
