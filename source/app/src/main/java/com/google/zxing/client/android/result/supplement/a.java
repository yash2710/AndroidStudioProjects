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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

// Referenced classes of package com.google.zxing.client.android.result.supplement:
//            SupplementalInfoRetriever

final class a extends SupplementalInfoRetriever
{

    private final String a;
    private final String b;
    private final String c;

    a(TextView textview, String s, String s1, HistoryManager historymanager, Context context)
    {
        super(textview, historymanager);
        String s2 = LocaleManager.getCountry(context);
        if ("ISBN".equals(s) && !Locale.US.getCountry().equals(s2))
        {
            s = "EAN";
        }
        a = s;
        b = s1;
        c = s2;
    }

    private static void a(XmlPullParser xmlpullparser)
    {
        if (xmlpullparser.next() != 4)
        {
            throw new IOException();
        } else
        {
            return;
        }
    }

    final void a()
    {
        int i;
        ArrayList arraylist;
        XmlPullParser xmlpullparser;
        String s;
        String s1;
        String s2;
        int l;
        String s3;
        i = 1;
        CharSequence charsequence = HttpHelper.downloadViaHttp((new StringBuilder("https://bsplus.srowen.com/ss?c=")).append(c).append("&t=").append(a).append("&i=").append(b).toString(), com.google.zxing.client.android.HttpHelper.ContentType.XML);
        arraylist = new ArrayList();
        int j;
        int k;
        try
        {
            XmlPullParserFactory xmlpullparserfactory = XmlPullParserFactory.newInstance();
            xmlpullparserfactory.setNamespaceAware(true);
            xmlpullparser = xmlpullparserfactory.newPullParser();
            xmlpullparser.setInput(new StringReader(charsequence.toString()));
            j = xmlpullparser.getEventType();
        }
        catch (XmlPullParserException xmlpullparserexception)
        {
            throw new IOException(xmlpullparserexception.toString());
        }
        k = 0;
        s = null;
        s1 = null;
        s2 = null;
        l = 0;
        if (j == i)
        {
            break MISSING_BLOCK_LABEL_349;
        }
        if (j != 2) goto _L2; else goto _L1
_L1:
        s3 = xmlpullparser.getName();
        if (!"Item".equals(s3)) goto _L4; else goto _L3
_L3:
        if (k != 0)
        {
            break MISSING_BLOCK_LABEL_349;
        }
        k = i;
_L2:
        j = xmlpullparser.next();
        break MISSING_BLOCK_LABEL_122;
_L4:
label0:
        {
            if (!"DetailPageURL".equals(s3))
            {
                break label0;
            }
            a(xmlpullparser);
            s2 = xmlpullparser.getText();
        }
        continue; /* Loop/switch isn't completed */
label1:
        {
            if (!"Author".equals(s3))
            {
                break label1;
            }
            a(xmlpullparser);
            arraylist.add(xmlpullparser.getText());
        }
        continue; /* Loop/switch isn't completed */
label2:
        {
            if (!"Title".equals(s3))
            {
                break label2;
            }
            a(xmlpullparser);
            s1 = xmlpullparser.getText();
        }
        continue; /* Loop/switch isn't completed */
        if (!"LowestNewPrice".equals(s3))
        {
            break MISSING_BLOCK_LABEL_290;
        }
        l = i;
        continue; /* Loop/switch isn't completed */
        if (!"FormattedPrice".equals(s3))
        {
            break MISSING_BLOCK_LABEL_325;
        }
        if (l == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        a(xmlpullparser);
        s = xmlpullparser.getText();
        l = 0;
        if (true) goto _L2; else goto _L5
_L5:
        boolean flag = "Errors".equals(s3);
        if (!flag) goto _L2; else goto _L6
_L6:
        if (i != 0 || s2 == null)
        {
            return;
        } else
        {
            ArrayList arraylist1 = new ArrayList();
            a(s1, ((Collection) (arraylist1)));
            a(((Collection) (arraylist)), ((Collection) (arraylist1)));
            a(s, ((Collection) (arraylist1)));
            a(b, "Amazon", (String[])arraylist1.toArray(new String[arraylist1.size()]), s2);
            return;
        }
        i = 0;
          goto _L6
    }
}
