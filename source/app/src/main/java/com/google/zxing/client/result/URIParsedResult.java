// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class URIParsedResult extends ParsedResult
{

    private static final Pattern a = Pattern.compile(":/*([^/@]+)@[^/]+");
    private final String b;
    private final String c;

    public URIParsedResult(String s, String s1)
    {
        String s2;
        int i;
        super(ParsedResultType.URI);
        s2 = s.trim();
        i = s2.indexOf(':');
        if (i >= 0) goto _L2; else goto _L1
_L1:
        s2 = (new StringBuilder("http://")).append(s2).toString();
_L4:
        b = s2;
        c = s1;
        return;
_L2:
        if (a(s2, i))
        {
            s2 = (new StringBuilder("http://")).append(s2).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static boolean a(String s, int i)
    {
        int j = s.indexOf('/', i + 1);
        int k;
        int l;
        if (j < 0)
        {
            k = s.length();
        } else
        {
            k = j;
        }
        if (k > i + 1) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        l = i + 1;
label0:
        do
        {
label1:
            {
                if (l >= k)
                {
                    break label1;
                }
                if (s.charAt(l) < '0' || s.charAt(l) > '9')
                {
                    break label0;
                }
                l++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    public final String getDisplayResult()
    {
        StringBuilder stringbuilder = new StringBuilder(30);
        maybeAppend(c, stringbuilder);
        maybeAppend(b, stringbuilder);
        return stringbuilder.toString();
    }

    public final String getTitle()
    {
        return c;
    }

    public final String getURI()
    {
        return b;
    }

    public final boolean isPossiblyMaliciousURI()
    {
        return a.matcher(b).find();
    }

}
