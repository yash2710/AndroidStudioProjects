// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result.supplement;

import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.history.HistoryManager;
import com.google.zxing.client.result.URIParsedResult;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.result.supplement:
//            SupplementalInfoRetriever

final class d extends SupplementalInfoRetriever
{

    private static final Pattern a = Pattern.compile("<title>([^<]+)");
    private final String b;

    d(TextView textview, URIParsedResult uriparsedresult, HistoryManager historymanager)
    {
        super(textview, historymanager);
        b = uriparsedresult.getURI();
    }

    final void a()
    {
        CharSequence charsequence;
        try
        {
            charsequence = HttpHelper.downloadViaHttp(b, com.google.zxing.client.android.HttpHelper.ContentType.HTML, 4096);
        }
        catch (IOException ioexception)
        {
            return;
        }
        if (charsequence != null && charsequence.length() > 0)
        {
            Matcher matcher = a.matcher(charsequence);
            if (matcher.find())
            {
                String s = matcher.group(1);
                if (s != null && s.length() > 0)
                {
                    if (s.length() > 100)
                    {
                        s = (new StringBuilder()).append(s.substring(0, 100)).append("...").toString();
                    }
                    a(b, null, new String[] {
                        s
                    }, b);
                }
            }
        }
    }

}
