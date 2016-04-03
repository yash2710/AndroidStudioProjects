// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.adobe.adms.testandtarget;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.newrelic.agent.android.instrumentation.HttpInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.adobe.adms.testandtarget:
//            Mbox

public class MboxFactory
{

    protected static final String USER_AGENT = "Apache-HttpClient (Test&Target Android SDK)";
    private String a;
    private String b;
    private long c;
    private boolean d;
    private ConcurrentHashMap e;
    private String f;
    private Context g;
    private SharedPreferences h;

    public MboxFactory(Context context, String s)
    {
        e = new ConcurrentHashMap();
        g = context;
        a = s;
        c = 0xdbba0L;
        d = true;
        f = (new StringBuilder("http://")).append(s).append(".tt.omtrdc.net").toString();
        h = context.getSharedPreferences((new StringBuilder("TestAndTarget")).append(s).toString(), 0);
        b("mboxPC");
        b("mboxSession");
    }

    private void a(String s)
    {
        android.content.SharedPreferences.Editor editor = h.edit();
        editor.remove((new StringBuilder()).append(s).append("_Value").toString());
        editor.remove((new StringBuilder()).append(s).append("_Expires").toString());
        editor.commit();
    }

    private void a(CookieStore cookiestore)
    {
        Iterator iterator = cookiestore.getCookies().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Cookie cookie = (Cookie)iterator.next();
            String s = cookie.getName();
            if (s.equals("mboxSession") || s.equals("mboxPC"))
            {
                android.content.SharedPreferences.Editor editor = h.edit();
                editor.putString((new StringBuilder()).append(s).append("_Value").toString(), cookie.getValue());
                editor.putLong((new StringBuilder()).append(s).append("_Expires").toString(), cookie.getExpiryDate().getTime());
                editor.commit();
            }
        } while (true);
        b("mboxPC");
        b("mboxSession");
    }

    private void b(String s)
    {
label0:
        {
            String s1;
label1:
            {
                long l = System.currentTimeMillis();
                long l1 = h.getLong((new StringBuilder()).append(s).append("_Expires").toString(), 0L);
                if (l1 > 0L)
                {
                    if (l1 <= l)
                    {
                        break label0;
                    }
                    s1 = h.getString((new StringBuilder()).append(s).append("_Value").toString(), "");
                    if (b != null)
                    {
                        break label1;
                    }
                    b = (new StringBuilder()).append(s).append("=").append(s1).toString();
                }
                return;
            }
            b = (new StringBuilder()).append(b).append("; ").append(s).append("=").append(s1).toString();
            return;
        }
        a(s);
    }

    public void clearCookies()
    {
        this;
        JVM INSTR monitorenter ;
        b = null;
        a("mboxPC");
        a("mboxSession");
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Mbox create(String s)
    {
        Mbox mbox = new Mbox(this, s);
        e.put(s, mbox);
        return mbox;
    }

    public void disable()
    {
        this;
        JVM INSTR monitorenter ;
        d = false;
        long l = System.currentTimeMillis();
        android.content.SharedPreferences.Editor editor = h.edit();
        editor.putLong("DisableTime", l);
        editor.commit();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    protected String encode(String s)
    {
        String s1;
        try
        {
            s1 = URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            Log.e("MboxFactory", unsupportedencodingexception.toString());
            return s;
        }
        return s1;
    }

    protected String getBaseRequestURL()
    {
        return (new StringBuilder()).append(f).append("/m2/").append(a).append("/ubox/raw?").toString();
    }

    protected String getCookies()
    {
        return b;
    }

    protected String getMboxResponse(Mbox mbox, String s)
    {
        this;
        JVM INSTR monitorenter ;
        DefaultHttpClient defaulthttpclient;
        HttpGet httpget;
        defaulthttpclient = new DefaultHttpClient();
        httpget = new HttpGet(s);
        httpget.setHeader("User-Agent", "Apache-HttpClient (Test&Target Android SDK)");
        if (b != null)
        {
            httpget.setHeader("Cookie", b);
        }
        if (defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        HttpResponse httpresponse = defaulthttpclient.execute(httpget);
_L6:
        int i;
        String s2;
        i = httpresponse.getStatusLine().getStatusCode();
        s2 = httpresponse.getFirstHeader("Content-Type").getValue();
        a(defaulthttpclient.getCookieStore());
        if (i != 200) goto _L4; else goto _L3
_L3:
        boolean flag = s2.equals("image/gif");
        if (!flag) goto _L5; else goto _L4
_L4:
        String s1 = null;
_L8:
        this;
        JVM INSTR monitorexit ;
        return s1;
_L2:
        httpresponse = HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
          goto _L6
_L5:
        InputStream inputstream;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte0[];
        inputstream = httpresponse.getEntity().getContent();
        bytearrayoutputstream = new ByteArrayOutputStream();
        abyte0 = new byte[512];
_L7:
        int j = inputstream.read(abyte0);
        if (j == -1)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        bytearrayoutputstream.write(abyte0, 0, j);
          goto _L7
        IOException ioexception;
        ioexception;
        Log.e("MboxFactory", (new StringBuilder("ERROR: ")).append(ioexception.toString()).toString());
        s1 = null;
          goto _L8
        s1 = new String(bytearrayoutputstream.toByteArray(), "UTF-8");
          goto _L8
        Exception exception;
        exception;
        throw exception;
          goto _L6
    }

    protected boolean isEnabled()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        if (h == null)
        {
            h = g.getSharedPreferences((new StringBuilder("TestAndTarget")).append(a).toString(), 0);
        }
        l = h.getLong("DisableTime", 0L);
        if (l <= 0L)
        {
            break MISSING_BLOCK_LABEL_119;
        }
        long l1 = System.currentTimeMillis() - l;
        if (l1 >= c)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        Log.w("MboxFactory", (new StringBuilder("WARNING: ")).append(String.valueOf(c - l1)).append("ms until MboxFactory is re-enabled.").toString());
        d = false;
_L1:
        boolean flag = d;
        this;
        JVM INSTR monitorexit ;
        return flag;
        d = true;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public void recordEvent(String s)
    {
        this;
        JVM INSTR monitorenter ;
        DefaultHttpClient defaulthttpclient;
        HttpGet httpget;
        String s1 = String.valueOf(System.currentTimeMillis());
        String s2 = (new StringBuilder()).append(getBaseRequestURL()).append("mbox=").append(encode(s)).append("&mboxDefault=").append(encode("/images/log.gif")).append("&mboxTime=").append(s1).toString();
        defaulthttpclient = new DefaultHttpClient();
        httpget = new HttpGet(s2);
        httpget.setHeader("User-Agent", "Apache-HttpClient (Test&Target Android SDK)");
        if (b != null)
        {
            httpget.setHeader("Cookie", b);
        }
        if (defaulthttpclient instanceof HttpClient) goto _L2; else goto _L1
_L1:
        defaulthttpclient.execute(httpget);
_L3:
        a(defaulthttpclient.getCookieStore());
_L4:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        HttpInstrumentation.execute((HttpClient)defaulthttpclient, httpget);
          goto _L3
        ClientProtocolException clientprotocolexception;
        clientprotocolexception;
        Log.e("MboxFactory", (new StringBuilder("EXCEPTION: ")).append(clientprotocolexception.toString()).toString());
          goto _L4
        Exception exception;
        exception;
        throw exception;
        IOException ioexception;
        ioexception;
        Log.e("MboxFactory", (new StringBuilder("EXCEPTION: ")).append(ioexception.toString()).toString());
          goto _L4
    }

    public void setDisableDuration(long l)
    {
        this;
        JVM INSTR monitorenter ;
        c = l;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setMboxServer(String s)
    {
        String s1 = s.toLowerCase();
        if (s1.startsWith("http://") || s1.startsWith("https://"))
        {
            f = s1;
            return;
        } else
        {
            throw new IllegalArgumentException("ERROR: mbox server URL must begin with \"http://\" or \"https://\".");
        }
    }
}
