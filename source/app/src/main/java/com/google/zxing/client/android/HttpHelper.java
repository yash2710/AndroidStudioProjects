// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.zxing.client.android;

import com.flipkart.logging.FkLogger;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

// Referenced classes of package com.google.zxing.client.android:
//            m

public final class HttpHelper
{

    private static final String a = com/google/zxing/client/android/HttpHelper.getSimpleName();
    private static final Collection b = new HashSet(Arrays.asList(new String[] {
        "amzn.to", "bit.ly", "bitly.com", "fb.me", "goo.gl", "is.gd", "j.mp", "lnkd.in", "ow.ly", "R.BEETAGG.COM", 
        "r.beetagg.com", "SCN.BY", "su.pr", "t.co", "tinyurl.com", "tr.im"
    }));

    private HttpHelper()
    {
    }

    private static int a(String s, HttpURLConnection httpurlconnection)
    {
        int i;
        try
        {
            httpurlconnection.connect();
        }
        catch (NullPointerException nullpointerexception)
        {
            FkLogger.warn(a, (new StringBuilder("Bad URI? ")).append(s).toString());
            throw new IOException(nullpointerexception.toString());
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            FkLogger.warn(a, (new StringBuilder("Bad URI? ")).append(s).toString());
            throw new IOException(illegalargumentexception.toString());
        }
        catch (SecurityException securityexception)
        {
            FkLogger.warn(a, (new StringBuilder("Restricted URI? ")).append(s).toString());
            throw new IOException(securityexception.toString());
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            FkLogger.warn(a, (new StringBuilder("Bad URI? ")).append(s).toString());
            throw new IOException(indexoutofboundsexception.toString());
        }
        try
        {
            i = httpurlconnection.getResponseCode();
        }
        catch (NullPointerException nullpointerexception1)
        {
            FkLogger.warn(a, (new StringBuilder("Bad URI? ")).append(s).toString());
            throw new IOException(nullpointerexception1.toString());
        }
        catch (IllegalArgumentException illegalargumentexception1)
        {
            FkLogger.warn(a, (new StringBuilder("Bad server status? ")).append(s).toString());
            throw new IOException(illegalargumentexception1.toString());
        }
        return i;
    }

    private static CharSequence a(String s, String s1, int i)
    {
        int j = 0;
_L8:
        if (j >= 5) goto _L2; else goto _L1
_L1:
        HttpURLConnection httpurlconnection;
        httpurlconnection = a(new URL(s));
        httpurlconnection.setInstanceFollowRedirects(true);
        httpurlconnection.setRequestProperty("Accept", s1);
        httpurlconnection.setRequestProperty("Accept-Charset", "utf-8,*");
        httpurlconnection.setRequestProperty("User-Agent", "ZXing (Android)");
        int k = a(s, httpurlconnection);
        k;
        JVM INSTR lookupswitch 2: default 88
    //                   200: 123
    //                   302: 139;
           goto _L3 _L4 _L5
_L3:
        throw new IOException((new StringBuilder("Bad HTTP response: ")).append(k).toString());
        Exception exception;
        exception;
        httpurlconnection.disconnect();
        throw exception;
_L4:
        CharSequence charsequence = a(((URLConnection) (httpurlconnection)), i);
        httpurlconnection.disconnect();
        return charsequence;
_L5:
        String s2 = httpurlconnection.getHeaderField("Location");
        s = s2;
        if (s == null) goto _L7; else goto _L6
_L6:
        j++;
        httpurlconnection.disconnect();
          goto _L8
_L7:
        throw new IOException("No Location");
_L2:
        throw new IOException("Too many redirects");
    }

    private static CharSequence a(URLConnection urlconnection, int i)
    {
        String s = urlconnection.getHeaderField("Content-Type");
        if (s == null) goto _L2; else goto _L1
_L1:
        int k = s.indexOf("charset=");
        if (k < 0) goto _L2; else goto _L3
_L3:
        String s1 = s.substring(k + 8);
_L8:
        StringBuilder stringbuilder = new StringBuilder();
        InputStreamReader inputstreamreader = new InputStreamReader(urlconnection.getInputStream(), s1);
        char ac[] = new char[1024];
_L7:
        if (stringbuilder.length() >= i) goto _L5; else goto _L4
_L4:
        int j = inputstreamreader.read(ac);
        if (j <= 0) goto _L5; else goto _L6
_L6:
        stringbuilder.append(ac, 0, j);
          goto _L7
        Exception exception;
        exception;
_L9:
        NullPointerException nullpointerexception1;
        IOException ioexception1;
        if (inputstreamreader != null)
        {
            try
            {
                inputstreamreader.close();
            }
            catch (IOException ioexception) { }
            catch (NullPointerException nullpointerexception) { }
        }
        throw exception;
_L2:
        s1 = "UTF-8";
          goto _L8
_L5:
        try
        {
            inputstreamreader.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception1)
        {
            return stringbuilder;
        }
        // Misplaced declaration of an exception variable
        catch (NullPointerException nullpointerexception1)
        {
            return stringbuilder;
        }
        return stringbuilder;
        exception;
        inputstreamreader = null;
          goto _L9
    }

    private static HttpURLConnection a(URL url)
    {
        URLConnection urlconnection;
        try
        {
            urlconnection = HttpInstrumentation.openConnection(url.openConnection());
        }
        catch (NullPointerException nullpointerexception)
        {
            FkLogger.warn(a, (new StringBuilder("Bad URI? ")).append(url).toString());
            throw new IOException(nullpointerexception.toString());
        }
        if (!(urlconnection instanceof HttpURLConnection))
        {
            throw new IOException();
        } else
        {
            return (HttpURLConnection)urlconnection;
        }
    }

    public static CharSequence downloadViaHttp(String s, ContentType contenttype)
    {
        return downloadViaHttp(s, contenttype, 0x7fffffff);
    }

    public static CharSequence downloadViaHttp(String s, ContentType contenttype, int i)
    {
        m.a[contenttype.ordinal()];
        JVM INSTR tableswitch 1 3: default 36
    //                   1 46
    //                   2 52
    //                   3 58;
           goto _L1 _L2 _L3 _L4
_L1:
        String s1 = "text/*,*/*";
_L6:
        return a(s, s1, i);
_L2:
        s1 = "application/xhtml+xml,text/html,text/*,*/*";
        continue; /* Loop/switch isn't completed */
_L3:
        s1 = "application/json,text/*,*/*";
        continue; /* Loop/switch isn't completed */
_L4:
        s1 = "application/xml,text/*,*/*";
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static URI unredirect(URI uri)
    {
        HttpURLConnection httpurlconnection;
        if (!b.contains(uri.getHost()))
        {
            return uri;
        }
        httpurlconnection = a(uri.toURL());
        httpurlconnection.setInstanceFollowRedirects(false);
        httpurlconnection.setDoInput(false);
        httpurlconnection.setRequestMethod("HEAD");
        httpurlconnection.setRequestProperty("User-Agent", "ZXing (Android)");
        int i = a(uri.toString(), httpurlconnection);
        i;
        JVM INSTR tableswitch 300 307: default 108
    //                   300 114
    //                   301 114
    //                   302 114
    //                   303 114
    //                   304 108
    //                   305 108
    //                   306 108
    //                   307 114;
           goto _L1 _L2 _L2 _L2 _L2 _L1 _L1 _L1 _L2
_L1:
        httpurlconnection.disconnect();
        return uri;
_L2:
        String s = httpurlconnection.getHeaderField("Location");
        if (s == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        URI uri1 = new URI(s);
        httpurlconnection.disconnect();
        return uri1;
        Exception exception;
        exception;
        httpurlconnection.disconnect();
        throw exception;
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
        if (true) goto _L1; else goto _L3
_L3:
    }


    private class ContentType extends Enum
    {

        public static final ContentType HTML;
        public static final ContentType JSON;
        public static final ContentType TEXT;
        public static final ContentType XML;
        private static final ContentType a[];

        public static ContentType valueOf(String s)
        {
            return (ContentType)Enum.valueOf(com/google/zxing/client/android/HttpHelper$ContentType, s);
        }

        public static ContentType[] values()
        {
            return (ContentType[])a.clone();
        }

        static 
        {
            HTML = new ContentType("HTML", 0);
            JSON = new ContentType("JSON", 1);
            XML = new ContentType("XML", 2);
            TEXT = new ContentType("TEXT", 3);
            ContentType acontenttype[] = new ContentType[4];
            acontenttype[0] = HTML;
            acontenttype[1] = JSON;
            acontenttype[2] = XML;
            acontenttype[3] = TEXT;
            a = acontenttype;
        }

        private ContentType(String s, int i)
        {
            super(s, i);
        }
    }

}
