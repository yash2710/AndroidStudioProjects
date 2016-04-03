// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            et

public final class at
{

    public static final String DEVICE_ID_EMULATOR = et.y("emulator");
    private final Date d;
    private final Set f;
    private final Location h;
    private final String mk;
    private final int ml;
    private final boolean mm;
    private final Bundle mn;
    private final Map mo;
    private final String mp;
    private final SearchAdRequest mq;
    private final int mr;
    private final Set ms;

    public at(a a)
    {
        this(a, null);
    }

    public at(a a1, SearchAdRequest searchadrequest)
    {
        d = a.a(a1);
        mk = a.b(a1);
        ml = a.c(a1);
        f = Collections.unmodifiableSet(a.d(a1));
        h = a.e(a1);
        mm = a.f(a1);
        mn = a.g(a1);
        mo = Collections.unmodifiableMap(a.h(a1));
        mp = a.i(a1);
        mq = searchadrequest;
        mr = a.j(a1);
        ms = Collections.unmodifiableSet(a.k(a1));
    }

    public final SearchAdRequest aH()
    {
        return mq;
    }

    public final Map aI()
    {
        return mo;
    }

    public final Bundle aJ()
    {
        return mn;
    }

    public final int aK()
    {
        return mr;
    }

    public final Date getBirthday()
    {
        return d;
    }

    public final String getContentUrl()
    {
        return mk;
    }

    public final Bundle getCustomEventExtrasBundle(Class class1)
    {
        Bundle bundle = mn.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null)
        {
            return bundle.getBundle(class1.getClass().getName());
        } else
        {
            return null;
        }
    }

    public final int getGender()
    {
        return ml;
    }

    public final Set getKeywords()
    {
        return f;
    }

    public final Location getLocation()
    {
        return h;
    }

    public final boolean getManualImpressionsEnabled()
    {
        return mm;
    }

    public final NetworkExtras getNetworkExtras(Class class1)
    {
        return (NetworkExtras)mo.get(class1);
    }

    public final Bundle getNetworkExtrasBundle(Class class1)
    {
        return mn.getBundle(class1.getName());
    }

    public final String getPublisherProvidedId()
    {
        return mp;
    }

    public final boolean isTestDevice(Context context)
    {
        return ms.contains(et.r(context));
    }


    private class a
    {

        private Date d;
        private Location h;
        private String mk;
        private int ml;
        private boolean mm;
        private final Bundle mn = new Bundle();
        private String mp;
        private int mr;
        private final HashSet mt = new HashSet();
        private final HashMap mu = new HashMap();
        private final HashSet mv = new HashSet();

        static Date a(a a1)
        {
            return a1.d;
        }

        static String b(a a1)
        {
            return a1.mk;
        }

        static int c(a a1)
        {
            return a1.ml;
        }

        static HashSet d(a a1)
        {
            return a1.mt;
        }

        static Location e(a a1)
        {
            return a1.h;
        }

        static boolean f(a a1)
        {
            return a1.mm;
        }

        static Bundle g(a a1)
        {
            return a1.mn;
        }

        static HashMap h(a a1)
        {
            return a1.mu;
        }

        static String i(a a1)
        {
            return a1.mp;
        }

        static int j(a a1)
        {
            return a1.mr;
        }

        static HashSet k(a a1)
        {
            return a1.mv;
        }

        public final void a(Location location)
        {
            h = location;
        }

        public final void a(NetworkExtras networkextras)
        {
            if (networkextras instanceof AdMobExtras)
            {
                a(com/google/ads/mediation/admob/AdMobAdapter, ((AdMobExtras)networkextras).getExtras());
                return;
            } else
            {
                mu.put(networkextras.getClass(), networkextras);
                return;
            }
        }

        public final void a(Class class1, Bundle bundle)
        {
            mn.putBundle(class1.getName(), bundle);
        }

        public final void a(Date date)
        {
            d = date;
        }

        public final void b(Class class1, Bundle bundle)
        {
            if (mn.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null)
            {
                mn.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
            }
            mn.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(class1.getName(), bundle);
        }

        public final void e(int l)
        {
            ml = l;
        }

        public final void g(String s)
        {
            mt.add(s);
        }

        public final void g(boolean flag)
        {
            mm = flag;
        }

        public final void h(String s)
        {
            mv.add(s);
        }

        public final void h(boolean flag)
        {
            int l;
            if (flag)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            mr = l;
        }

        public final void i(String s)
        {
            mk = s;
        }

        public final void j(String s)
        {
            mp = s;
        }

        public a()
        {
            ml = -1;
            mm = false;
            mr = -1;
        }
    }

}
