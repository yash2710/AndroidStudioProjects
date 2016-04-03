// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.encode:
//            a, d, e, f, 
//            b

final class c extends a
{

    private static final Pattern a = Pattern.compile("([\\\\:;])");
    private static final Pattern b = Pattern.compile("\\n");
    private static final Pattern c = Pattern.compile(",");
    private static final b d = new d();
    private static final Pattern e = Pattern.compile("[^0-9]+");

    c()
    {
    }

    static String a(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            return null;
        } else
        {
            return e.matcher(charsequence).replaceAll("");
        }
    }

    static Pattern a()
    {
        return a;
    }

    private static void a(StringBuilder stringbuilder, StringBuilder stringbuilder1, String s, Iterable iterable, int i, b b1)
    {
        a(stringbuilder, stringbuilder1, s, iterable, i, b1, d, ';');
    }

    private static void a(StringBuilder stringbuilder, StringBuilder stringbuilder1, String s, String s1)
    {
        a(stringbuilder, stringbuilder1, s, s1, d, ';');
    }

    static Pattern b()
    {
        return b;
    }

    static Pattern c()
    {
        return c;
    }

    public final String[] encode(Iterable iterable, String s, Iterable iterable1, Iterable iterable2, Iterable iterable3, Iterable iterable4, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder(100);
        stringbuilder.append("MECARD:");
        StringBuilder stringbuilder1 = new StringBuilder(100);
        a(stringbuilder, stringbuilder1, "N", iterable, 1, new e(this));
        a(stringbuilder, stringbuilder1, "ORG", s);
        a(stringbuilder, stringbuilder1, "ADR", iterable1, 1, null);
        a(stringbuilder, stringbuilder1, "TEL", iterable2, 0x7fffffff, new f(this));
        a(stringbuilder, stringbuilder1, "EMAIL", iterable3, 0x7fffffff, null);
        a(stringbuilder, stringbuilder1, "URL", iterable4, 0x7fffffff, null);
        a(stringbuilder, stringbuilder1, "NOTE", s1);
        stringbuilder.append(';');
        String as[] = new String[2];
        as[0] = stringbuilder.toString();
        as[1] = stringbuilder1.toString();
        return as;
    }

}
