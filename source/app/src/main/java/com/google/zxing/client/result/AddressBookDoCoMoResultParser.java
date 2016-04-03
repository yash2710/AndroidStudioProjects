// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;

// Referenced classes of package com.google.zxing.client.result:
//            a, AddressBookParsedResult, ParsedResult

public final class AddressBookDoCoMoResultParser extends a
{

    public AddressBookDoCoMoResultParser()
    {
    }

    public final AddressBookParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        if (!s.startsWith("MECARD:"))
        {
            return null;
        }
        String as[] = a("N:", s, true);
        if (as == null)
        {
            return null;
        }
        String s1 = as[0];
        int i = s1.indexOf(',');
        String s2;
        String s3;
        String as1[];
        String as2[];
        String s4;
        String as3[];
        String s5;
        String as4[];
        String s6;
        if (i >= 0)
        {
            s2 = (new StringBuilder()).append(s1.substring(i + 1)).append(' ').append(s1.substring(0, i)).toString();
        } else
        {
            s2 = s1;
        }
        s3 = b("SOUND:", s, true);
        as1 = a("TEL:", s, true);
        as2 = a("EMAIL:", s, true);
        s4 = b("NOTE:", s, false);
        as3 = a("ADR:", s, true);
        s5 = b("BDAY:", s, true);
        if (s5 != null && !isStringOfDigits(s5, 8))
        {
            s5 = null;
        }
        as4 = a("URL:", s, true);
        s6 = b("ORG:", s, true);
        return new AddressBookParsedResult(maybeWrap(s2), null, s3, as1, null, as2, null, null, s4, as3, null, s6, s5, null, as4, null);
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }
}
