// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.flipkart.android.utils.browser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.flipkart.android.utils.browser:
//            Manufacturer, BrowserType, RenderingEngine, Version

public final class Browser extends Enum
{

    public static final Browser APPLE_MAIL;
    public static final Browser BOT;
    public static final Browser CAMINO;
    public static final Browser CAMINO2;
    public static final Browser CFNETWORK;
    public static final Browser CHROME;
    public static final Browser CHROME10;
    public static final Browser CHROME8;
    public static final Browser CHROME9;
    public static final Browser DOWNLOAD;
    public static final Browser EUDORA;
    public static final Browser EVOLUTION;
    public static final Browser FIREFOX;
    public static final Browser FIREFOX1_5;
    public static final Browser FIREFOX2;
    public static final Browser FIREFOX3;
    public static final Browser FIREFOX3MOBILE;
    public static final Browser FIREFOX4;
    public static final Browser FLOCK;
    public static final Browser IE;
    public static final Browser IE5;
    public static final Browser IE5_5;
    public static final Browser IE6;
    public static final Browser IE7;
    public static final Browser IE8;
    public static final Browser IE9;
    public static final Browser IEMOBILE6;
    public static final Browser IEMOBILE7;
    public static final Browser KONQUEROR;
    public static final Browser LOTUS_NOTES;
    public static final Browser LYNX;
    public static final Browser MOBILE_SAFARI;
    public static final Browser MOZILLA;
    public static final Browser NETFRONT;
    public static final Browser OMNIWEB;
    public static final Browser OPERA;
    public static final Browser OPERA10;
    public static final Browser OPERA9;
    public static final Browser OPERA_MINI;
    public static final Browser OUTLOOK;
    public static final Browser OUTLOOK2007;
    public static final Browser OUTLOOK2010;
    public static final Browser OUTLOOK_EXPRESS7;
    public static final Browser POCOMAIL;
    public static final Browser SAFARI;
    public static final Browser SAFARI4;
    public static final Browser SAFARI5;
    public static final Browser SEAMONKEY;
    public static final Browser THEBAT;
    public static final Browser THUNDERBIRD;
    public static final Browser THUNDERBIRD2;
    public static final Browser THUNDERBIRD3;
    public static final Browser UNKNOWN;
    private static final Browser k[];
    private final short a;
    private final String b;
    private final String c[];
    private final String d[] = null;
    private final BrowserType e;
    private final Manufacturer f;
    private final RenderingEngine g;
    private final Browser h;
    private List i;
    private Pattern j;

    private Browser(String s, int l, Manufacturer manufacturer, Browser browser, int i1, String s1, String as[], 
            String as1[], BrowserType browsertype, RenderingEngine renderingengine, String s2)
    {
        super(s, l);
        a = (short)((manufacturer.getId() << 8) + (byte)i1);
        b = s1;
        h = browser;
        i = new ArrayList();
        if (h != null)
        {
            h.i.add(this);
        }
        c = as;
        e = browsertype;
        f = manufacturer;
        g = renderingengine;
        if (s2 != null)
        {
            j = Pattern.compile(s2);
        }
    }

    private Browser a(String s)
    {
        if (!isInUserAgentString(s)) goto _L2; else goto _L1
_L1:
        if (i.size() <= 0) goto _L4; else goto _L3
_L3:
        Iterator iterator = i.iterator();
_L7:
        if (!iterator.hasNext()) goto _L4; else goto _L5
_L5:
        Browser browser = ((Browser)iterator.next()).a(s);
        if (browser == null) goto _L7; else goto _L6
_L6:
        this = browser;
_L10:
        return this;
_L4:
        String as[];
        int l;
        int i1;
        if (d == null)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        as = d;
        l = as.length;
        i1 = 0;
_L11:
        String s1;
        if (i1 >= l)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        s1 = as[i1];
        if (s.toLowerCase().indexOf(s1.toLowerCase()) == -1) goto _L9; else goto _L8
_L8:
        boolean flag = true;
_L12:
        if (!flag) goto _L10; else goto _L2
_L2:
        return null;
_L9:
        i1++;
          goto _L11
        flag = false;
          goto _L12
    }

    private Pattern a()
    {
        while (j == null) 
        {
            if (getGroup() != this)
            {
                this = getGroup();
            } else
            {
                return null;
            }
        }
        return j;
    }

    public static Browser parseUserAgentString(String s)
    {
        Browser abrowser[] = values();
        int l = abrowser.length;
        for (int i1 = 0; i1 < l; i1++)
        {
            Browser browser = abrowser[i1];
            if (browser.h != null)
            {
                continue;
            }
            Browser browser1 = browser.a(s);
            if (browser1 != null)
            {
                return browser1;
            }
        }

        return UNKNOWN;
    }

    public static Browser valueOf(String s)
    {
        return (Browser)Enum.valueOf(com/flipkart/android/utils/browser/Browser, s);
    }

    public static Browser valueOf(short word0)
    {
        Browser abrowser[] = values();
        int l = abrowser.length;
        for (int i1 = 0; i1 < l; i1++)
        {
            Browser browser = abrowser[i1];
            if (browser.getId() == word0)
            {
                return browser;
            }
        }

        throw new IllegalArgumentException((new StringBuilder("No enum const for id ")).append(word0).toString());
    }

    public static Browser[] values()
    {
        return (Browser[])k.clone();
    }

    public final BrowserType getBrowserType()
    {
        return e;
    }

    public final Browser getGroup()
    {
        for (; h != null; this = h) { }
        return this;
    }

    public final short getId()
    {
        return a;
    }

    public final Manufacturer getManufacturer()
    {
        return f;
    }

    public final String getName()
    {
        return b;
    }

    public final RenderingEngine getRenderingEngine()
    {
        return g;
    }

    public final Version getVersion(String s)
    {
        Pattern pattern = a();
        if (s != null && pattern != null)
        {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find())
            {
                String s1 = matcher.group(1);
                String s2 = matcher.group(2);
                String s3 = "0";
                if (matcher.groupCount() > 2)
                {
                    s3 = matcher.group(3);
                }
                return new Version(s1, s2, s3);
            }
        }
        return null;
    }

    public final boolean isInUserAgentString(String s)
    {
        String as[] = c;
        int l = as.length;
        int i1 = 0;
        do
        {
label0:
            {
                boolean flag = false;
                if (i1 < l)
                {
                    String s1 = as[i1];
                    if (s.toLowerCase().indexOf(s1.toLowerCase()) == -1)
                    {
                        break label0;
                    }
                    flag = true;
                }
                return flag;
            }
            i1++;
        } while (true);
    }

    static 
    {
        OPERA = new Browser("OPERA", 0, Manufacturer.OPERA, null, 1, "Opera", new String[] {
            "Opera"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Opera\\/(([\\d]+)\\.([\\w]+))");
        OPERA_MINI = new Browser("OPERA_MINI", 1, Manufacturer.OPERA, OPERA, 20, "Opera Mini", new String[] {
            "Opera Mini"
        }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.PRESTO, null);
        OPERA10 = new Browser("OPERA10", 2, Manufacturer.OPERA, OPERA, 10, "Opera 10", new String[] {
            "Opera/9.8"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, "Version\\/(([\\d]+)\\.([\\w]+))");
        OPERA9 = new Browser("OPERA9", 3, Manufacturer.OPERA, OPERA, 5, "Opera 9", new String[] {
            "Opera/9"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.PRESTO, null);
        KONQUEROR = new Browser("KONQUEROR", 4, Manufacturer.OTHER, null, 1, "Konqueror", new String[] {
            "Konqueror"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.KHTML, "Konqueror\\/(([0-9]+)\\.?([\\w]+)?(-[\\w]+)?)");
        OUTLOOK = new Browser("OUTLOOK", 5, Manufacturer.MICROSOFT, null, 100, "Outlook", new String[] {
            "MSOffice"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, "MSOffice (([0-9]+))");
        OUTLOOK2007 = new Browser("OUTLOOK2007", 6, Manufacturer.MICROSOFT, OUTLOOK, 107, "Outlook 2007", new String[] {
            "MSOffice 12"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null);
        OUTLOOK2010 = new Browser("OUTLOOK2010", 7, Manufacturer.MICROSOFT, OUTLOOK, 108, "Outlook 2010", new String[] {
            "MSOffice 14"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WORD, null);
        IE = new Browser("IE", 8, Manufacturer.MICROSOFT, null, 1, "Internet Explorer", new String[] {
            "MSIE"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, "MSIE (([\\d]+)\\.([\\w]+))");
        OUTLOOK_EXPRESS7 = new Browser("OUTLOOK_EXPRESS7", 9, Manufacturer.MICROSOFT, IE, 110, "Windows Live Mail", new String[] {
            "Outlook-Express/7.0"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.TRIDENT, null);
        IEMOBILE7 = new Browser("IEMOBILE7", 10, Manufacturer.MICROSOFT, IE, 121, "IE Mobile 7", new String[] {
            "IEMobile 7"
        }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, null);
        IEMOBILE6 = new Browser("IEMOBILE6", 11, Manufacturer.MICROSOFT, IE, 120, "IE Mobile 6", new String[] {
            "IEMobile 6"
        }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.TRIDENT, null);
        IE9 = new Browser("IE9", 12, Manufacturer.MICROSOFT, IE, 90, "Internet Explorer 9", new String[] {
            "MSIE 9"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
        IE8 = new Browser("IE8", 13, Manufacturer.MICROSOFT, IE, 80, "Internet Explorer 8", new String[] {
            "MSIE 8"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
        IE7 = new Browser("IE7", 14, Manufacturer.MICROSOFT, IE, 70, "Internet Explorer 7", new String[] {
            "MSIE 7"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
        IE6 = new Browser("IE6", 15, Manufacturer.MICROSOFT, IE, 60, "Internet Explorer 6", new String[] {
            "MSIE 6"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
        IE5_5 = new Browser("IE5_5", 16, Manufacturer.MICROSOFT, IE, 55, "Internet Explorer 5.5", new String[] {
            "MSIE 5.5"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
        IE5 = new Browser("IE5", 17, Manufacturer.MICROSOFT, IE, 50, "Internet Explorer 5", new String[] {
            "MSIE 5"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.TRIDENT, null);
        CHROME = new Browser("CHROME", 18, Manufacturer.GOOGLE, null, 1, "Chrome", new String[] {
            "Chrome"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Chrome\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
        CHROME10 = new Browser("CHROME10", 19, Manufacturer.GOOGLE, CHROME, 15, "Chrome 10", new String[] {
            "Chrome/10"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null);
        CHROME9 = new Browser("CHROME9", 20, Manufacturer.GOOGLE, CHROME, 10, "Chrome 9", new String[] {
            "Chrome/9"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null);
        CHROME8 = new Browser("CHROME8", 21, Manufacturer.GOOGLE, CHROME, 5, "Chrome 8", new String[] {
            "Chrome/8"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null);
        OMNIWEB = new Browser("OMNIWEB", 22, Manufacturer.OTHER, null, 2, "Omniweb", new String[] {
            "OmniWeb"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null);
        SAFARI = new Browser("SAFARI", 23, Manufacturer.APPLE, null, 1, "Safari", new String[] {
            "Safari"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, "Version\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)");
        SAFARI5 = new Browser("SAFARI5", 24, Manufacturer.APPLE, SAFARI, 3, "Safari 5", new String[] {
            "Version/5"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null);
        MOBILE_SAFARI = new Browser("MOBILE_SAFARI", 25, Manufacturer.APPLE, SAFARI, 2, "Mobile Safari", new String[] {
            "Mobile Safari", "Mobile/"
        }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.WEBKIT, null);
        SAFARI4 = new Browser("SAFARI4", 26, Manufacturer.APPLE, SAFARI, 4, "Safari 4", new String[] {
            "Version/4"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.WEBKIT, null);
        APPLE_MAIL = new Browser("APPLE_MAIL", 27, Manufacturer.APPLE, null, 50, "Apple Mail", new String[] {
            "AppleWebKit"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.WEBKIT, null);
        LOTUS_NOTES = new Browser("LOTUS_NOTES", 28, Manufacturer.OTHER, null, 3, "Lotus Notes", new String[] {
            "Lotus-Notes"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, "Lotus-Notes\\/(([\\d]+)\\.([\\w]+))");
        THUNDERBIRD = new Browser("THUNDERBIRD", 29, Manufacturer.MOZILLA, null, 110, "Thunderbird", new String[] {
            "Thunderbird"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, "Thunderbird\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
        THUNDERBIRD3 = new Browser("THUNDERBIRD3", 30, Manufacturer.MOZILLA, THUNDERBIRD, 130, "Thunderbird 3", new String[] {
            "Thunderbird/3"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null);
        THUNDERBIRD2 = new Browser("THUNDERBIRD2", 31, Manufacturer.MOZILLA, THUNDERBIRD, 120, "Thunderbird 2", new String[] {
            "Thunderbird/2"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.GECKO, null);
        CAMINO = new Browser("CAMINO", 32, Manufacturer.OTHER, null, 5, "Camino", new String[] {
            "Camino"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Camino\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)");
        CAMINO2 = new Browser("CAMINO2", 33, Manufacturer.OTHER, CAMINO, 17, "Camino 2", new String[] {
            "Camino/2"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null);
        FLOCK = new Browser("FLOCK", 34, Manufacturer.OTHER, null, 4, "Flock", new String[] {
            "Flock"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Flock\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)");
        FIREFOX = new Browser("FIREFOX", 35, Manufacturer.MOZILLA, null, 10, "Firefox", new String[] {
            "Firefox"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "Firefox\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?(\\.[\\w]+)?)");
        FIREFOX3MOBILE = new Browser("FIREFOX3MOBILE", 36, Manufacturer.MOZILLA, FIREFOX, 31, "Firefox 3 Mobile", new String[] {
            "Firefox/3.5 Maemo"
        }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.GECKO, null);
        FIREFOX4 = new Browser("FIREFOX4", 37, Manufacturer.MOZILLA, FIREFOX, 40, "Firefox 4", new String[] {
            "Firefox/4"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null);
        FIREFOX3 = new Browser("FIREFOX3", 38, Manufacturer.MOZILLA, FIREFOX, 30, "Firefox 3", new String[] {
            "Firefox/3"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null);
        FIREFOX2 = new Browser("FIREFOX2", 39, Manufacturer.MOZILLA, FIREFOX, 20, "Firefox 2", new String[] {
            "Firefox/2"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null);
        FIREFOX1_5 = new Browser("FIREFOX1_5", 40, Manufacturer.MOZILLA, FIREFOX, 15, "Firefox 1.5", new String[] {
            "Firefox/1.5"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, null);
        SEAMONKEY = new Browser("SEAMONKEY", 41, Manufacturer.OTHER, null, 15, "SeaMonkey", new String[] {
            "SeaMonkey"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.GECKO, "SeaMonkey\\/(([0-9]+)\\.?([\\w]+)?(\\.[\\w]+)?)");
        BOT = new Browser("BOT", 42, Manufacturer.OTHER, null, 12, "Robot/Spider", new String[] {
            "Googlebot", "bot", "spider", "crawler", "Feedfetcher", "Slurp", "Twiceler", "Nutch", "BecomeBot"
        }, null, BrowserType.ROBOT, RenderingEngine.OTHER, null);
        MOZILLA = new Browser("MOZILLA", 43, Manufacturer.MOZILLA, null, 1, "Mozilla", new String[] {
            "Mozilla", "Moozilla"
        }, null, BrowserType.WEB_BROWSER, RenderingEngine.OTHER, null);
        CFNETWORK = new Browser("CFNETWORK", 44, Manufacturer.OTHER, null, 6, "CFNetwork", new String[] {
            "CFNetwork"
        }, null, BrowserType.UNKNOWN, RenderingEngine.OTHER, null);
        EUDORA = new Browser("EUDORA", 45, Manufacturer.OTHER, null, 7, "Eudora", new String[] {
            "Eudora", "EUDORA"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null);
        POCOMAIL = new Browser("POCOMAIL", 46, Manufacturer.OTHER, null, 8, "PocoMail", new String[] {
            "PocoMail"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null);
        THEBAT = new Browser("THEBAT", 47, Manufacturer.OTHER, null, 9, "The Bat!", new String[] {
            "The Bat"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null);
        NETFRONT = new Browser("NETFRONT", 48, Manufacturer.OTHER, null, 10, "NetFront", new String[] {
            "NetFront"
        }, null, BrowserType.MOBILE_BROWSER, RenderingEngine.OTHER, null);
        EVOLUTION = new Browser("EVOLUTION", 49, Manufacturer.OTHER, null, 11, "Evolution", new String[] {
            "CamelHttpStream"
        }, null, BrowserType.EMAIL_CLIENT, RenderingEngine.OTHER, null);
        LYNX = new Browser("LYNX", 50, Manufacturer.OTHER, null, 13, "Lynx", new String[] {
            "Lynx"
        }, null, BrowserType.TEXT_BROWSER, RenderingEngine.OTHER, "Lynx\\/(([0-9]+)\\.([\\d]+)\\.?([\\w-+]+)?\\.?([\\w-+]+)?)");
        DOWNLOAD = new Browser("DOWNLOAD", 51, Manufacturer.OTHER, null, 16, "Downloading Tool", new String[] {
            "cURL", "wget"
        }, null, BrowserType.TEXT_BROWSER, RenderingEngine.OTHER, null);
        UNKNOWN = new Browser("UNKNOWN", 52, Manufacturer.OTHER, null, 14, "Unknown", new String[0], null, BrowserType.UNKNOWN, RenderingEngine.OTHER, null);
        Browser abrowser[] = new Browser[53];
        abrowser[0] = OPERA;
        abrowser[1] = OPERA_MINI;
        abrowser[2] = OPERA10;
        abrowser[3] = OPERA9;
        abrowser[4] = KONQUEROR;
        abrowser[5] = OUTLOOK;
        abrowser[6] = OUTLOOK2007;
        abrowser[7] = OUTLOOK2010;
        abrowser[8] = IE;
        abrowser[9] = OUTLOOK_EXPRESS7;
        abrowser[10] = IEMOBILE7;
        abrowser[11] = IEMOBILE6;
        abrowser[12] = IE9;
        abrowser[13] = IE8;
        abrowser[14] = IE7;
        abrowser[15] = IE6;
        abrowser[16] = IE5_5;
        abrowser[17] = IE5;
        abrowser[18] = CHROME;
        abrowser[19] = CHROME10;
        abrowser[20] = CHROME9;
        abrowser[21] = CHROME8;
        abrowser[22] = OMNIWEB;
        abrowser[23] = SAFARI;
        abrowser[24] = SAFARI5;
        abrowser[25] = MOBILE_SAFARI;
        abrowser[26] = SAFARI4;
        abrowser[27] = APPLE_MAIL;
        abrowser[28] = LOTUS_NOTES;
        abrowser[29] = THUNDERBIRD;
        abrowser[30] = THUNDERBIRD3;
        abrowser[31] = THUNDERBIRD2;
        abrowser[32] = CAMINO;
        abrowser[33] = CAMINO2;
        abrowser[34] = FLOCK;
        abrowser[35] = FIREFOX;
        abrowser[36] = FIREFOX3MOBILE;
        abrowser[37] = FIREFOX4;
        abrowser[38] = FIREFOX3;
        abrowser[39] = FIREFOX2;
        abrowser[40] = FIREFOX1_5;
        abrowser[41] = SEAMONKEY;
        abrowser[42] = BOT;
        abrowser[43] = MOZILLA;
        abrowser[44] = CFNETWORK;
        abrowser[45] = EUDORA;
        abrowser[46] = POCOMAIL;
        abrowser[47] = THEBAT;
        abrowser[48] = NETFRONT;
        abrowser[49] = EVOLUTION;
        abrowser[50] = LYNX;
        abrowser[51] = DOWNLOAD;
        abrowser[52] = UNKNOWN;
        k = abrowser;
    }
}
