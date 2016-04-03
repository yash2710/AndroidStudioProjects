// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.result;

import java.util.Map;

// Referenced classes of package com.google.zxing.client.result:
//            ParsedResult, ParsedResultType

public final class ExpandedProductParsedResult extends ParsedResult
{

    public static final String KILOGRAM = "KG";
    public static final String POUND = "LB";
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final Map o;

    public ExpandedProductParsedResult(String s, String s1, String s2, String s3, String s4, String s5, String s6, 
            String s7, String s8, String s9, String s10, String s11, String s12, String s13, 
            Map map)
    {
        super(ParsedResultType.PRODUCT);
        a = s;
        b = s1;
        c = s2;
        d = s3;
        e = s4;
        f = s5;
        g = s6;
        h = s7;
        i = s8;
        j = s9;
        k = s10;
        l = s11;
        m = s12;
        n = s13;
        o = map;
    }

    private static int a(Object obj)
    {
        if (obj == null)
        {
            return 0;
        } else
        {
            return obj.hashCode();
        }
    }

    private static boolean a(Object obj, Object obj1)
    {
        if (obj == null)
        {
            return obj1 == null;
        } else
        {
            return obj.equals(obj1);
        }
    }

    public final boolean equals(Object obj)
    {
        ExpandedProductParsedResult expandedproductparsedresult;
        if (obj instanceof ExpandedProductParsedResult)
        {
            if (a(b, (expandedproductparsedresult = (ExpandedProductParsedResult)obj).b) && a(c, expandedproductparsedresult.c) && a(d, expandedproductparsedresult.d) && a(e, expandedproductparsedresult.e) && a(g, expandedproductparsedresult.g) && a(h, expandedproductparsedresult.h) && a(i, expandedproductparsedresult.i) && a(j, expandedproductparsedresult.j) && a(k, expandedproductparsedresult.k) && a(l, expandedproductparsedresult.l) && a(m, expandedproductparsedresult.m) && a(n, expandedproductparsedresult.n) && a(o, expandedproductparsedresult.o))
            {
                return true;
            }
        }
        return false;
    }

    public final String getBestBeforeDate()
    {
        return g;
    }

    public final String getDisplayResult()
    {
        return String.valueOf(a);
    }

    public final String getExpirationDate()
    {
        return h;
    }

    public final String getLotNumber()
    {
        return d;
    }

    public final String getPackagingDate()
    {
        return f;
    }

    public final String getPrice()
    {
        return l;
    }

    public final String getPriceCurrency()
    {
        return n;
    }

    public final String getPriceIncrement()
    {
        return m;
    }

    public final String getProductID()
    {
        return b;
    }

    public final String getProductionDate()
    {
        return e;
    }

    public final String getRawText()
    {
        return a;
    }

    public final String getSscc()
    {
        return c;
    }

    public final Map getUncommonAIs()
    {
        return o;
    }

    public final String getWeight()
    {
        return i;
    }

    public final String getWeightIncrement()
    {
        return k;
    }

    public final String getWeightType()
    {
        return j;
    }

    public final int hashCode()
    {
        return 0 ^ a(b) ^ a(c) ^ a(d) ^ a(e) ^ a(g) ^ a(h) ^ a(i) ^ a(j) ^ a(k) ^ a(l) ^ a(m) ^ a(n) ^ a(o);
    }
}
