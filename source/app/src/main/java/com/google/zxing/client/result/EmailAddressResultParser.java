// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Map;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, EmailAddressParsedResult, EmailDoCoMoResultParser, ParsedResult

public final class EmailAddressResultParser extends ResultParser
{

    public EmailAddressResultParser()
    {
    }

    public final EmailAddressParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        if (s.startsWith("mailto:") || s.startsWith("MAILTO:"))
        {
            String s1 = s.substring(7);
            int i = s1.indexOf('?');
            if (i >= 0)
            {
                s1 = s1.substring(0, i);
            }
            String s2 = c(s1);
            Map map = b(s);
            String s3;
            String s4;
            String s5;
            if (map != null)
            {
                EmailAddressParsedResult emailaddressparsedresult;
                boolean flag;
                if (s2.length() == 0)
                {
                    s3 = (String)map.get("to");
                } else
                {
                    s3 = s2;
                }
                s4 = (String)map.get("subject");
                s5 = (String)map.get("body");
            } else
            {
                s3 = s2;
                s4 = null;
                s5 = null;
            }
            emailaddressparsedresult = new EmailAddressParsedResult(s3, s4, s5, s);
        } else
        {
            flag = EmailDoCoMoResultParser.a(s);
            emailaddressparsedresult = null;
            if (flag)
            {
                return new EmailAddressParsedResult(s, null, null, (new StringBuilder("mailto:")).append(s).toString());
            }
        }
        return emailaddressparsedresult;
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }
}
