// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package com.crashlytics.android.internal:
//            r, O, N, aG, 
//            q, ac, ax, ay

public class av
{

    private final q a;
    private aG b;
    private SSLSocketFactory c;
    private boolean d;

    public av()
    {
        this(((q) (new r())));
    }

    public av(q q1)
    {
        a = q1;
    }

    private void a()
    {
        this;
        JVM INSTR monitorenter ;
        d = false;
        c = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private static boolean a(X509Certificate x509certificate, X509Certificate x509certificate1)
    {
        if (!x509certificate.getSubjectX500Principal().equals(x509certificate1.getIssuerX500Principal()))
        {
            return false;
        }
        try
        {
            x509certificate1.verify(x509certificate.getPublicKey());
        }
        catch (GeneralSecurityException generalsecurityexception)
        {
            return false;
        }
        return true;
    }

    public static X509Certificate[] a(X509Certificate ax509certificate[], O o)
    {
        boolean flag = true;
        LinkedList linkedlist = new LinkedList();
        boolean flag1;
        boolean flag2;
        int i;
        X509Certificate x509certificate;
        if (o.a(ax509certificate[0]))
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        linkedlist.add(ax509certificate[0]);
        flag2 = flag1;
        i = ((flag) ? 1 : 0);
        do
        {
            if (i >= ax509certificate.length)
            {
                break;
            }
            if (o.a(ax509certificate[i]))
            {
                flag2 = flag;
            }
            if (!a(ax509certificate[i], ax509certificate[i - 1]))
            {
                break;
            }
            linkedlist.add(ax509certificate[i]);
            i++;
        } while (true);
        x509certificate = o.b(ax509certificate[i - 1]);
        if (x509certificate != null)
        {
            linkedlist.add(x509certificate);
        } else
        {
            flag = flag2;
        }
        if (flag)
        {
            return (X509Certificate[])linkedlist.toArray(new X509Certificate[linkedlist.size()]);
        } else
        {
            throw new CertificateException("Didn't find a trust anchor in chain cleanup!");
        }
    }

    private SSLSocketFactory b()
    {
        this;
        JVM INSTR monitorenter ;
        SSLSocketFactory sslsocketfactory;
        if (c == null && !d)
        {
            c = c();
        }
        sslsocketfactory = c;
        this;
        JVM INSTR monitorexit ;
        return sslsocketfactory;
        Exception exception;
        exception;
        throw exception;
    }

    private SSLSocketFactory c()
    {
        this;
        JVM INSTR monitorenter ;
        d = true;
        SSLSocketFactory sslsocketfactory;
        aG ag = b;
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[] {
            new N(new O(ag.a(), ag.b()), ag)
        }, null);
        sslsocketfactory = sslcontext.getSocketFactory();
        a.a("Crashlytics", "Custom SSL pinning enabled");
_L2:
        this;
        JVM INSTR monitorexit ;
        return sslsocketfactory;
        Exception exception1;
        exception1;
        a.a("Crashlytics", "Exception while validating pinned certs", exception1);
        sslsocketfactory = null;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    public ay a(ax ax1, String s)
    {
        return a(ax1, s, Collections.emptyMap());
    }

    public ay a(ax ax1, String s, Map map)
    {
        ac.a[ax1.ordinal()];
        JVM INSTR tableswitch 1 4: default 40
    //                   1 50
    //                   2 104
    //                   3 115
    //                   4 124;
           goto _L1 _L2 _L3 _L4 _L5
_L5:
        break MISSING_BLOCK_LABEL_124;
_L1:
        throw new IllegalArgumentException("Unsupported HTTP method!");
_L2:
        ay ay1 = ay.a(s, map, true);
_L6:
        boolean flag;
        if (s == null)
        {
            flag = false;
        } else
        {
            flag = s.toLowerCase().startsWith("https");
        }
        if (flag && b != null)
        {
            SSLSocketFactory sslsocketfactory = b();
            if (sslsocketfactory != null)
            {
                ((HttpsURLConnection)ay1.a()).setSSLSocketFactory(sslsocketfactory);
            }
        }
        return ay1;
_L3:
        ay1 = ay.b(s, map, true);
          goto _L6
_L4:
        ay1 = ay.a(s);
          goto _L6
        ay1 = ay.b(s);
          goto _L6
    }

    public void a(aG ag)
    {
        if (b != ag)
        {
            b = ag;
            a();
        }
    }
}
