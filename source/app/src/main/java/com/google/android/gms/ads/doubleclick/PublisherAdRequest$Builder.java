// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.ads.doubleclick;

import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.internal.hm;
import java.util.Date;

// Referenced classes of package com.google.android.gms.ads.doubleclick:
//            PublisherAdRequest

public final class 
{

    private final com.google.android.gms.internal.uilder.kt kt = new com.google.android.gms.internal.uilder.kt();

    static com.google.android.gms.internal.uilder a( )
    {
        return .kt;
    }

    public final kt addCustomEventExtrasBundle(Class class1, Bundle bundle)
    {
        kt.kt(class1, bundle);
        return this;
    }

    public final kt addKeyword(String s)
    {
        kt.kt(s);
        return this;
    }

    public final kt addNetworkExtras(NetworkExtras networkextras)
    {
        kt.kt(networkextras);
        return this;
    }

    public final kt addNetworkExtrasBundle(Class class1, Bundle bundle)
    {
        kt.kt(class1, bundle);
        return this;
    }

    public final kt addTestDevice(String s)
    {
        kt.kt(s);
        return this;
    }

    public final PublisherAdRequest build()
    {
        return new PublisherAdRequest(this, null);
    }

    public final kt setBirthday(Date date)
    {
        kt.kt(date);
        return this;
    }

    public final kt setContentUrl(String s)
    {
        hm.b(s, "Content URL must be non-null.");
        hm.b(s, "Content URL must be non-empty.");
        boolean flag;
        Object aobj[];
        if (s.length() <= 512)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        aobj = new Object[2];
        aobj[0] = Integer.valueOf(512);
        aobj[1] = Integer.valueOf(s.length());
        hm.b(flag, "Content URL must not exceed %d in length.  Provided length was %d.", aobj);
        kt.kt(s);
        return this;
    }

    public final kt setGender(int i)
    {
        kt.kt(i);
        return this;
    }

    public final kt setLocation(Location location)
    {
        kt.kt(location);
        return this;
    }

    public final kt setManualImpressionsEnabled(boolean flag)
    {
        kt.kt(flag);
        return this;
    }

    public final kt setPublisherProvidedId(String s)
    {
        kt.kt(s);
        return this;
    }

    public final kt tagForChildDirectedTreatment(boolean flag)
    {
        kt.kt(flag);
        return this;
    }

    public ()
    {
    }
}
