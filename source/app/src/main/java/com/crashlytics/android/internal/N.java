// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.crashlytics.android.internal;

import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

// Referenced classes of package com.crashlytics.android.internal:
//            aG, O, v, q, 
//            av

final class N
    implements X509TrustManager
{

    private final TrustManager a[];
    private final O b;
    private final long c = -1L;
    private final List d = new LinkedList();
    private final Set e = Collections.synchronizedSet(new HashSet());

    public N(O o, aG ag)
    {
        a = a(o);
        b = o;
        String as[] = ag.c();
        int i = as.length;
        for (int j = 0; j < i; j++)
        {
            String s = as[j];
            d.add(a(s));
        }

    }

    private boolean a(X509Certificate x509certificate)
    {
        byte abyte0[];
        Iterator iterator;
        abyte0 = MessageDigest.getInstance("SHA1").digest(x509certificate.getPublicKey().getEncoded());
        iterator = d.iterator();
_L1:
        boolean flag;
        if (!iterator.hasNext())
        {
            break MISSING_BLOCK_LABEL_62;
        }
        flag = Arrays.equals((byte[])iterator.next(), abyte0);
        if (flag)
        {
            return true;
        }
          goto _L1
        return false;
        NoSuchAlgorithmException nosuchalgorithmexception;
        nosuchalgorithmexception;
        throw new CertificateException(nosuchalgorithmexception);
    }

    private static byte[] a(String s)
    {
        int i = s.length();
        byte abyte0[] = new byte[i / 2];
        for (int j = 0; j < i; j += 2)
        {
            abyte0[j / 2] = (byte)((Character.digit(s.charAt(j), 16) << 4) + Character.digit(s.charAt(j + 1), 16));
        }

        return abyte0;
    }

    private static TrustManager[] a(O o)
    {
        TrustManager atrustmanager[];
        try
        {
            TrustManagerFactory trustmanagerfactory = TrustManagerFactory.getInstance("X509");
            trustmanagerfactory.init(o.a);
            atrustmanager = trustmanagerfactory.getTrustManagers();
        }
        catch (NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new AssertionError(nosuchalgorithmexception);
        }
        catch (KeyStoreException keystoreexception)
        {
            throw new AssertionError(keystoreexception);
        }
        return atrustmanager;
    }

    public final void checkClientTrusted(X509Certificate ax509certificate[], String s)
    {
        throw new CertificateException("Client certificates not supported!");
    }

    public final void checkServerTrusted(X509Certificate ax509certificate[], String s)
    {
        if (e.contains(ax509certificate[0]))
        {
            return;
        }
        TrustManager atrustmanager[] = a;
        int i = atrustmanager.length;
        for (int j = 0; j < i; j++)
        {
            ((X509TrustManager)atrustmanager[j]).checkServerTrusted(ax509certificate, s);
        }

        if (-1L == -1L || System.currentTimeMillis() + 1L <= 0x39ef8b000L) goto _L2; else goto _L1
_L1:
        v.a().b().c("Crashlytics", (new StringBuilder("Certificate pins are stale, (")).append(System.currentTimeMillis() + 1L).append(" millis vs 15552000000 millis) falling back to system trust.").toString());
_L4:
        e.add(ax509certificate[0]);
        return;
_L2:
        X509Certificate ax509certificate1[] = av.a(ax509certificate, b);
        int k = ax509certificate1.length;
        int l = 0;
label0:
        do
        {
label1:
            {
                if (l >= k)
                {
                    break label1;
                }
                if (a(ax509certificate1[l]))
                {
                    break label0;
                }
                l++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        throw new CertificateException("No valid pins found in chain!");
    }

    public final X509Certificate[] getAcceptedIssuers()
    {
        return null;
    }
}
