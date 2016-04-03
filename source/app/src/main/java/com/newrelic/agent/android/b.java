// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.newrelic.agent.android;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

// Referenced classes of package com.newrelic.agent.android:
//            AndroidAgentImpl

final class b
    implements LocationListener
{

    private AndroidAgentImpl a;

    b(AndroidAgentImpl androidagentimpl)
    {
        a = androidagentimpl;
        super();
    }

    public final void onLocationChanged(Location location)
    {
        if (AndroidAgentImpl.a(a, location))
        {
            a.setLocation(location);
        }
    }

    public final void onProviderDisabled(String s)
    {
        if ("passive".equals(s))
        {
            AndroidAgentImpl.a(a);
        }
    }

    public final void onProviderEnabled(String s)
    {
    }

    public final void onStatusChanged(String s, int i, Bundle bundle)
    {
    }
}
