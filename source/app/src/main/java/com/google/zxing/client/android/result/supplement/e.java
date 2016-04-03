// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.net.URI;
import java.net.URISyntaxException;

// Referenced classes of package com.google.zxing.client.android.result.supplement:
//            SupplementalInfoRetriever

final class e extends SupplementalInfoRetriever
{

    private final URIParsedResult a;
    private final String b;

    e(TextView textview, URIParsedResult uriparsedresult, HistoryManager historymanager, Context context)
    {
        super(textview, historymanager);
        b = context.getString(com.google.zxing.client.android.R.string.msg_redirect);
        a = uriparsedresult;
    }

    final void a()
    {
        URI uri = new URI(a.getURI());
        URI uri1 = HttpHelper.unredirect(uri);
        URI uri2 = uri;
        URI uri3 = uri1;
        int i = 0;
        do
        {
            int j = i + 1;
            if (i >= 5 || uri2.equals(uri3))
            {
                break;
            }
            String s = a.getDisplayResult();
            String as[] = new String[1];
            as[0] = (new StringBuilder()).append(b).append(" : ").append(uri3).toString();
            a(s, null, as, uri3.toString());
            URI uri4 = HttpHelper.unredirect(uri3);
            uri2 = uri3;
            uri3 = uri4;
            i = j;
        } while (true);
        break MISSING_BLOCK_LABEL_132;
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
    }
}
