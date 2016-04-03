// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, URIParsedResult, ParsedResult

public final class URIResultParser extends ResultParser
{

    private static final Pattern a = Pattern.compile("[a-zA-Z0-9]{2,}:");
    private static final Pattern b = Pattern.compile("([a-zA-Z0-9\\-]+\\.)+[a-zA-Z0-9\\-]{2,}(:\\d{1,5})?(/|\\?|$)");

    public URIResultParser()
    {
    }

    static boolean a(String s)
    {
        if (!s.contains(" "))
        {
            Matcher matcher = a.matcher(s);
            if (matcher.find() && matcher.start() == 0)
            {
                return true;
            }
            Matcher matcher1 = b.matcher(s);
            if (matcher1.find() && matcher1.start() == 0)
            {
                return true;
            }
        }
        return false;
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }

    public final URIParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        if (s.startsWith("URL:") || s.startsWith("URI:"))
        {
            return new URIParsedResult(s.substring(4).trim(), null);
        }
        String s1 = s.trim();
        if (a(s1))
        {
            return new URIParsedResult(s1, null);
        } else
        {
            return null;
        }
    }

}
