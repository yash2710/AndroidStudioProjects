// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;


// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class WifiParsedResult extends ParsedResult
{

    private final String a;
    private final String b;
    private final String c;
    private final boolean d;

    public WifiParsedResult(String s, String s1, String s2)
    {
        this(s, s1, s2, false);
    }

    public WifiParsedResult(String s, String s1, String s2, boolean flag)
    {
        super(ParsedResultType.WIFI);
        a = s1;
        b = s;
        c = s2;
        d = flag;
    }

    public final String getDisplayResult()
    {
        StringBuilder stringbuilder = new StringBuilder(80);
        maybeAppend(a, stringbuilder);
        maybeAppend(b, stringbuilder);
        maybeAppend(c, stringbuilder);
        maybeAppend(Boolean.toString(d), stringbuilder);
        return stringbuilder.toString();
    }

    public final String getNetworkEncryption()
    {
        return b;
    }

    public final String getPassword()
    {
        return c;
    }

    public final String getSsid()
    {
        return a;
    }

    public final boolean isHidden()
    {
        return d;
    }
}
