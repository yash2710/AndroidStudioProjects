// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.a;
import com.google.android.gms.location.b;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

// Referenced classes of package com.google.android.gms.internal:
//            jd, jk, ka, jm, 
//            jy, jo, jq, ju

public interface je
    extends IInterface
{

    public abstract void a(long l, boolean flag, PendingIntent pendingintent);

    public abstract void a(PendingIntent pendingintent);

    public abstract void a(PendingIntent pendingintent, jd jd, String s);

    public abstract void a(Location location, int i);

    public abstract void a(jd jd, String s);

    public abstract void a(jk jk, ka ka, PendingIntent pendingintent);

    public abstract void a(jm jm, ka ka, jy jy);

    public abstract void a(jo jo, ka ka);

    public abstract void a(jq jq, ka ka, PendingIntent pendingintent);

    public abstract void a(ju ju, ka ka, jy jy);

    public abstract void a(ka ka, PendingIntent pendingintent);

    public abstract void a(LocationRequest locationrequest, PendingIntent pendingintent);

    public abstract void a(LocationRequest locationrequest, a a1);

    public abstract void a(LocationRequest locationrequest, a a1, String s);

    public abstract void a(a a1);

    public abstract void a(LatLng latlng, jm jm, ka ka, jy jy);

    public abstract void a(LatLngBounds latlngbounds, int i, jm jm, ka ka, jy jy);

    public abstract void a(LatLngBounds latlngbounds, int i, String s, jm jm, ka ka, jy jy);

    public abstract void a(String s, ka ka, jy jy);

    public abstract void a(String s, LatLngBounds latlngbounds, jm jm, ka ka, jy jy);

    public abstract void a(String s, List list, List list1, ka ka, jy jy);

    public abstract void a(List list, PendingIntent pendingintent, jd jd, String s);

    public abstract void a(String as[], jd jd, String s);

    public abstract void b(ka ka, PendingIntent pendingintent);

    public abstract void b(String s, ka ka, jy jy);

    public abstract Location bo(String s);

    public abstract b bp(String s);

    public abstract Location iW();

    public abstract IBinder iX();

    public abstract void removeActivityUpdates(PendingIntent pendingintent);

    public abstract void setMockLocation(Location location);

    public abstract void setMockMode(boolean flag);
}
