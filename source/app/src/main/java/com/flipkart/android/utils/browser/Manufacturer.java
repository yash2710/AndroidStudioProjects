// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.browser;


public final class Manufacturer extends Enum
{

    public static final Manufacturer AOL;
    public static final Manufacturer APPLE;
    public static final Manufacturer BLACKBERRY;
    public static final Manufacturer COMPUSERVE;
    public static final Manufacturer GOOGLE;
    public static final Manufacturer MICROSOFT;
    public static final Manufacturer MMC;
    public static final Manufacturer MOZILLA;
    public static final Manufacturer NINTENDO;
    public static final Manufacturer NOKIA;
    public static final Manufacturer OPERA;
    public static final Manufacturer OTHER;
    public static final Manufacturer PALM;
    public static final Manufacturer SONY;
    public static final Manufacturer SONY_ERICSSON;
    public static final Manufacturer SUN;
    public static final Manufacturer SYMBIAN;
    public static final Manufacturer YAHOO;
    private static final Manufacturer c[];
    private final byte a;
    private final String b;

    private Manufacturer(String s, int i, int j, String s1)
    {
        super(s, i);
        a = (byte)j;
        b = s1;
    }

    public static Manufacturer valueOf(String s)
    {
        return (Manufacturer)Enum.valueOf(com/flipkart/android/utils/browser/Manufacturer, s);
    }

    public static Manufacturer[] values()
    {
        return (Manufacturer[])c.clone();
    }

    public final byte getId()
    {
        return a;
    }

    public final String getName()
    {
        return b;
    }

    static 
    {
        OTHER = new Manufacturer("OTHER", 0, 1, "Other");
        MICROSOFT = new Manufacturer("MICROSOFT", 1, 2, "Microsoft Corporation");
        APPLE = new Manufacturer("APPLE", 2, 3, "Apple Inc.");
        SUN = new Manufacturer("SUN", 3, 4, "Sun Microsystems, Inc.");
        SYMBIAN = new Manufacturer("SYMBIAN", 4, 5, "Symbian Ltd.");
        NOKIA = new Manufacturer("NOKIA", 5, 6, "Nokia Corporation");
        BLACKBERRY = new Manufacturer("BLACKBERRY", 6, 7, "Research In Motion Limited");
        PALM = new Manufacturer("PALM", 7, 8, "Palm, Inc. ");
        SONY_ERICSSON = new Manufacturer("SONY_ERICSSON", 8, 9, "Sony Ericsson Mobile Communications AB");
        SONY = new Manufacturer("SONY", 9, 10, "Sony Computer Entertainment, Inc.");
        NINTENDO = new Manufacturer("NINTENDO", 10, 11, "Nintendo");
        OPERA = new Manufacturer("OPERA", 11, 12, "Opera Software ASA");
        MOZILLA = new Manufacturer("MOZILLA", 12, 13, "Mozilla Foundation");
        GOOGLE = new Manufacturer("GOOGLE", 13, 15, "Google Inc.");
        COMPUSERVE = new Manufacturer("COMPUSERVE", 14, 16, "CompuServe Interactive Services, Inc.");
        YAHOO = new Manufacturer("YAHOO", 15, 17, "Yahoo Inc.");
        AOL = new Manufacturer("AOL", 16, 18, "AOL LLC.");
        MMC = new Manufacturer("MMC", 17, 19, "Mail.com Media Corporation");
        Manufacturer amanufacturer[] = new Manufacturer[18];
        amanufacturer[0] = OTHER;
        amanufacturer[1] = MICROSOFT;
        amanufacturer[2] = APPLE;
        amanufacturer[3] = SUN;
        amanufacturer[4] = SYMBIAN;
        amanufacturer[5] = NOKIA;
        amanufacturer[6] = BLACKBERRY;
        amanufacturer[7] = PALM;
        amanufacturer[8] = SONY_ERICSSON;
        amanufacturer[9] = SONY;
        amanufacturer[10] = NINTENDO;
        amanufacturer[11] = OPERA;
        amanufacturer[12] = MOZILLA;
        amanufacturer[13] = GOOGLE;
        amanufacturer[14] = COMPUSERVE;
        amanufacturer[15] = YAHOO;
        amanufacturer[16] = AOL;
        amanufacturer[17] = MMC;
        c = amanufacturer;
    }
}
