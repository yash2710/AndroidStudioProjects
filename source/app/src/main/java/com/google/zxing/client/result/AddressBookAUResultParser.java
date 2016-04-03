// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.zxing.client.result:
//            ResultParser, AddressBookParsedResult, ParsedResult

public final class AddressBookAUResultParser extends ResultParser
{

    public AddressBookAUResultParser()
    {
    }

    private static String[] a(String s, int i, String s1, boolean flag)
    {
        int j = 1;
        ArrayList arraylist = null;
        for (; j <= 3; j++)
        {
            String s2 = b((new StringBuilder()).append(s).append(j).append(':').toString(), s1, '\r', true);
            if (s2 == null)
            {
                break;
            }
            if (arraylist == null)
            {
                arraylist = new ArrayList(3);
            }
            arraylist.add(s2);
        }

        if (arraylist == null)
        {
            return null;
        } else
        {
            return (String[])arraylist.toArray(new String[arraylist.size()]);
        }
    }

    public final AddressBookParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        if (!s.contains("MEMORY") || !s.contains("\r\n"))
        {
            return null;
        }
        String s1 = b("NAME1:", s, '\r', true);
        String s2 = b("NAME2:", s, '\r', true);
        String as[] = a("TEL", 3, s, true);
        String as1[] = a("MAIL", 3, s, true);
        String s3 = b("MEMORY:", s, '\r', false);
        String s4 = b("ADD:", s, '\r', true);
        String as2[];
        if (s4 == null)
        {
            as2 = null;
        } else
        {
            as2 = (new String[] {
                s4
            });
        }
        return new AddressBookParsedResult(maybeWrap(s1), null, s2, as, null, as1, null, null, s3, as2, null, null, null, null, null, null);
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }
}
