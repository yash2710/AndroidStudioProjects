// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android.encode;

import java.util.regex.Pattern;

// Referenced classes of package com.google.zxing.client.android.encode:
//            a, i, j, b

final class h extends a
{

    private static final Pattern a = Pattern.compile("([\\\\,;])");
    private static final Pattern b = Pattern.compile("\\n");
    private static final b c = new i();

    h()
    {
    }

    static Pattern a()
    {
        return a;
    }

    private static void a(StringBuilder stringbuilder, StringBuilder stringbuilder1, String s, Iterable iterable, int k, b b1)
    {
        a(stringbuilder, stringbuilder1, s, iterable, k, b1, c, '\n');
    }

    private static void a(StringBuilder stringbuilder, StringBuilder stringbuilder1, String s, String s1)
    {
        a(stringbuilder, stringbuilder1, s, s1, c, '\n');
    }

    static Pattern b()
    {
        return b;
    }

    public final String[] encode(Iterable iterable, String s, Iterable iterable1, Iterable iterable2, Iterable iterable3, Iterable iterable4, String s1)
    {
        StringBuilder stringbuilder = new StringBuilder(100);
        stringbuilder.append("BEGIN:VCARD\n");
        stringbuilder.append("VERSION:3.0\n");
        StringBuilder stringbuilder1 = new StringBuilder(100);
        a(stringbuilder, stringbuilder1, "N", iterable, 1, null);
        a(stringbuilder, stringbuilder1, "ORG", s);
        a(stringbuilder, stringbuilder1, "ADR", iterable1, 1, null);
        a(stringbuilder, stringbuilder1, "TEL", iterable2, 0x7fffffff, new j(this));
        a(stringbuilder, stringbuilder1, "EMAIL", iterable3, 0x7fffffff, null);
        a(stringbuilder, stringbuilder1, "URL", iterable4, 0x7fffffff, null);
        a(stringbuilder, stringbuilder1, "NOTE", s1);
        stringbuilder.append("END:VCARD\n");
        String as[] = new String[2];
        as[0] = stringbuilder.toString();
        as[1] = stringbuilder1.toString();
        return as;
    }

}
