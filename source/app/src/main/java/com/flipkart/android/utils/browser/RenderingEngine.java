// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.browser;


public final class RenderingEngine extends Enum
{

    public static final RenderingEngine GECKO;
    public static final RenderingEngine KHTML;
    public static final RenderingEngine MOZILLA;
    public static final RenderingEngine OTHER;
    public static final RenderingEngine PRESTO;
    public static final RenderingEngine TRIDENT;
    public static final RenderingEngine WEBKIT;
    public static final RenderingEngine WORD;
    private static final RenderingEngine a[];

    private RenderingEngine(String s, int i, String s1)
    {
        super(s, i);
    }

    public static RenderingEngine valueOf(String s)
    {
        return (RenderingEngine)Enum.valueOf(com/flipkart/android/utils/browser/RenderingEngine, s);
    }

    public static RenderingEngine[] values()
    {
        return (RenderingEngine[])a.clone();
    }

    static 
    {
        TRIDENT = new RenderingEngine("TRIDENT", 0, "Trident");
        WORD = new RenderingEngine("WORD", 1, "Microsoft Office Word");
        GECKO = new RenderingEngine("GECKO", 2, "Gecko");
        WEBKIT = new RenderingEngine("WEBKIT", 3, "WebKit");
        PRESTO = new RenderingEngine("PRESTO", 4, "Presto");
        MOZILLA = new RenderingEngine("MOZILLA", 5, "Mozilla");
        KHTML = new RenderingEngine("KHTML", 6, "KHTML");
        OTHER = new RenderingEngine("OTHER", 7, "Other");
        RenderingEngine arenderingengine[] = new RenderingEngine[8];
        arenderingengine[0] = TRIDENT;
        arenderingengine[1] = WORD;
        arenderingengine[2] = GECKO;
        arenderingengine[3] = WEBKIT;
        arenderingengine[4] = PRESTO;
        arenderingengine[5] = MOZILLA;
        arenderingengine[6] = KHTML;
        arenderingengine[7] = OTHER;
        a = arenderingengine;
    }
}
