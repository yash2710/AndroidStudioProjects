// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class AddressBookParsedResult extends ParsedResult
{

    private final String a[];
    private final String b[];
    private final String c;
    private final String d[];
    private final String e[];
    private final String f[];
    private final String g[];
    private final String h;
    private final String i;
    private final String j[];
    private final String k[];
    private final String l;
    private final String m;
    private final String n;
    private final String o[];
    private final String p[];

    public AddressBookParsedResult(String as[], String as1[], String s, String as2[], String as3[], String as4[], String as5[], 
            String s1, String s2, String as6[], String as7[], String s3, String s4, String s5, 
            String as8[], String as9[])
    {
        super(ParsedResultType.ADDRESSBOOK);
        a = as;
        b = as1;
        c = s;
        d = as2;
        e = as3;
        f = as4;
        g = as5;
        h = s1;
        i = s2;
        j = as6;
        k = as7;
        l = s3;
        m = s4;
        n = s5;
        o = as8;
        p = as9;
    }

    public AddressBookParsedResult(String as[], String as1[], String as2[], String as3[], String as4[], String as5[], String as6[])
    {
        this(as, null, null, as1, as2, as3, as4, null, null, as5, as6, null, null, null, null, null);
    }

    public final String[] getAddressTypes()
    {
        return k;
    }

    public final String[] getAddresses()
    {
        return j;
    }

    public final String getBirthday()
    {
        return m;
    }

    public final String getDisplayResult()
    {
        StringBuilder stringbuilder = new StringBuilder(100);
        maybeAppend(a, stringbuilder);
        maybeAppend(b, stringbuilder);
        maybeAppend(c, stringbuilder);
        maybeAppend(n, stringbuilder);
        maybeAppend(l, stringbuilder);
        maybeAppend(j, stringbuilder);
        maybeAppend(d, stringbuilder);
        maybeAppend(f, stringbuilder);
        maybeAppend(h, stringbuilder);
        maybeAppend(o, stringbuilder);
        maybeAppend(m, stringbuilder);
        maybeAppend(p, stringbuilder);
        maybeAppend(i, stringbuilder);
        return stringbuilder.toString();
    }

    public final String[] getEmailTypes()
    {
        return g;
    }

    public final String[] getEmails()
    {
        return f;
    }

    public final String[] getGeo()
    {
        return p;
    }

    public final String getInstantMessenger()
    {
        return h;
    }

    public final String[] getNames()
    {
        return a;
    }

    public final String[] getNicknames()
    {
        return b;
    }

    public final String getNote()
    {
        return i;
    }

    public final String getOrg()
    {
        return l;
    }

    public final String[] getPhoneNumbers()
    {
        return d;
    }

    public final String[] getPhoneTypes()
    {
        return e;
    }

    public final String getPronunciation()
    {
        return c;
    }

    public final String getTitle()
    {
        return n;
    }

    public final String[] getURLs()
    {
        return o;
    }
}
