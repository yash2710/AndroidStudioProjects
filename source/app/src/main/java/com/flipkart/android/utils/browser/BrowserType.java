// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.browser;


public final class BrowserType extends Enum
{

    public static final BrowserType EMAIL_CLIENT;
    public static final BrowserType MOBILE_BROWSER;
    public static final BrowserType ROBOT;
    public static final BrowserType TEXT_BROWSER;
    public static final BrowserType TOOL;
    public static final BrowserType UNKNOWN;
    public static final BrowserType WEB_BROWSER;
    private static final BrowserType b[];
    private String a;

    private BrowserType(String s, int i, String s1)
    {
        super(s, i);
        a = s1;
    }

    public static BrowserType valueOf(String s)
    {
        return (BrowserType)Enum.valueOf(com/flipkart/android/utils/browser/BrowserType, s);
    }

    public static BrowserType[] values()
    {
        return (BrowserType[])b.clone();
    }

    public final String getName()
    {
        return a;
    }

    static 
    {
        WEB_BROWSER = new BrowserType("WEB_BROWSER", 0, "Browser");
        MOBILE_BROWSER = new BrowserType("MOBILE_BROWSER", 1, "Browser (mobile)");
        TEXT_BROWSER = new BrowserType("TEXT_BROWSER", 2, "Browser (text only)");
        EMAIL_CLIENT = new BrowserType("EMAIL_CLIENT", 3, "Email Client");
        ROBOT = new BrowserType("ROBOT", 4, "Robot");
        TOOL = new BrowserType("TOOL", 5, "Downloading tool");
        UNKNOWN = new BrowserType("UNKNOWN", 6, "unknown");
        BrowserType abrowsertype[] = new BrowserType[7];
        abrowsertype[0] = WEB_BROWSER;
        abrowsertype[1] = MOBILE_BROWSER;
        abrowsertype[2] = TEXT_BROWSER;
        abrowsertype[3] = EMAIL_CLIENT;
        abrowsertype[4] = ROBOT;
        abrowsertype[5] = TOOL;
        abrowsertype[6] = UNKNOWN;
        b = abrowsertype;
    }
}
