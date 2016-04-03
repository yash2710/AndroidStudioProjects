// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.result.supplement;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;
import com.google.zxing.client.android.HttpHelper;
import com.google.zxing.client.android.LocaleManager;
import com.google.zxing.client.android.history.HistoryManager;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.result.supplement:
//            SupplementalInfoRetriever

final class c extends SupplementalInfoRetriever
{

    private static final Pattern a[];
    private final String b;
    private final String c;
    private final Context d;

    c(TextView textview, String s, HistoryManager historymanager, Context context)
    {
        super(textview, historymanager);
        b = s;
        c = context.getString(com.google.zxing.client.android.R.string.msg_google_product);
        d = context;
    }

    final void a()
    {
        String s = URLEncoder.encode(b, "UTF-8");
        String s1 = (new StringBuilder("http://www.google.")).append(LocaleManager.getProductSearchCountryTLD(d)).append("/m/products?ie=utf8&oe=utf8&scoring=p&source=zxing&q=").append(s).toString();
        CharSequence charsequence = HttpHelper.downloadViaHttp(s1, com.google.zxing.client.android.HttpHelper.ContentType.HTML);
        Pattern apattern[] = a;
        int i = 0;
        do
        {
label0:
            {
                if (i < 2)
                {
                    Matcher matcher = apattern[i].matcher(charsequence);
                    if (!matcher.find())
                    {
                        break label0;
                    }
                    String s2 = b;
                    String s3 = c;
                    String as[] = new String[2];
                    as[0] = Html.fromHtml(matcher.group(1)).toString();
                    as[1] = Html.fromHtml(matcher.group(2)).toString();
                    a(s2, s3, as, s1);
                }
                return;
            }
            i++;
        } while (true);
    }

    static 
    {
        Pattern apattern[] = new Pattern[2];
        apattern[0] = Pattern.compile(",event\\)\">([^<]+)</a></h3>.+<span class=psrp>([^<]+)</span>");
        apattern[1] = Pattern.compile("owb63p\">([^<]+).+zdi3pb\">([^<]+)");
        a = apattern;
    }
}
