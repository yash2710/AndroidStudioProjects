// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.zxing.client.result:
//            a, AddressBookParsedResult, ParsedResult

public final class BizcardResultParser extends a
{

    public BizcardResultParser()
    {
    }

    public final AddressBookParsedResult parse(Result result)
    {
        String s = getMassagedText(result);
        if (!s.startsWith("BIZCARD:"))
        {
            return null;
        }
        String s1 = b("N:", s, true);
        String s2 = b("X:", s, true);
        String s3;
        String s4;
        String as[];
        String s5;
        String s6;
        String s7;
        String s8;
        String as1[];
        ArrayList arraylist;
        int i;
        String as2[];
        if (s1 == null)
        {
            s1 = s2;
        } else
        if (s2 != null)
        {
            s1 = (new StringBuilder()).append(s1).append(' ').append(s2).toString();
        }
        s3 = b("T:", s, true);
        s4 = b("C:", s, true);
        as = a("A:", s, true);
        s5 = b("B:", s, true);
        s6 = b("M:", s, true);
        s7 = b("F:", s, true);
        s8 = b("E:", s, true);
        as1 = maybeWrap(s1);
        arraylist = new ArrayList(3);
        if (s5 != null)
        {
            arraylist.add(s5);
        }
        if (s6 != null)
        {
            arraylist.add(s6);
        }
        if (s7 != null)
        {
            arraylist.add(s7);
        }
        i = arraylist.size();
        if (i == 0)
        {
            as2 = null;
        } else
        {
            as2 = (String[])arraylist.toArray(new String[i]);
        }
        return new AddressBookParsedResult(as1, null, null, as2, null, maybeWrap(s8), null, null, null, as, null, s4, null, s3, null, null);
    }

    public final volatile ParsedResult parse(Result result)
    {
        return parse(result);
    }
}
