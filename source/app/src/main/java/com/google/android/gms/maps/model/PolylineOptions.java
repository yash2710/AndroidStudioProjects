// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.gms.maps.model:
//            PolylineOptionsCreator, LatLng, h

public final class PolylineOptions
    implements SafeParcelable
{

    public static final PolylineOptionsCreator CREATOR = new PolylineOptionsCreator();
    private int Dj;
    private float aaC;
    private final List aaX;
    private boolean aaZ;
    private float aax;
    private boolean aay;
    private final int xM;

    public PolylineOptions()
    {
        aaC = 10F;
        Dj = 0xff000000;
        aax = 0.0F;
        aay = true;
        aaZ = false;
        xM = 1;
        aaX = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int j, float f1, boolean flag, boolean flag1)
    {
        aaC = 10F;
        Dj = 0xff000000;
        aax = 0.0F;
        aay = true;
        aaZ = false;
        xM = i;
        aaX = list;
        aaC = f;
        Dj = j;
        aax = f1;
        aay = flag;
        aaZ = flag1;
    }

    public final PolylineOptions add(LatLng latlng)
    {
        aaX.add(latlng);
        return this;
    }

    public final transient PolylineOptions add(LatLng alatlng[])
    {
        aaX.addAll(Arrays.asList(alatlng));
        return this;
    }

    public final PolylineOptions addAll(Iterable iterable)
    {
        LatLng latlng;
        for (Iterator iterator = iterable.iterator(); iterator.hasNext(); aaX.add(latlng))
        {
            latlng = (LatLng)iterator.next();
        }

        return this;
    }

    public final PolylineOptions color(int i)
    {
        Dj = i;
        return this;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final PolylineOptions geodesic(boolean flag)
    {
        aaZ = flag;
        return this;
    }

    public final int getColor()
    {
        return Dj;
    }

    public final List getPoints()
    {
        return aaX;
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final float getWidth()
    {
        return aaC;
    }

    public final float getZIndex()
    {
        return aax;
    }

    public final boolean isGeodesic()
    {
        return aaZ;
    }

    public final boolean isVisible()
    {
        return aay;
    }

    public final PolylineOptions visible(boolean flag)
    {
        aay = flag;
        return this;
    }

    public final PolylineOptions width(float f)
    {
        aaC = f;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (v.jL())
        {
            h.a(this, parcel, i);
            return;
        } else
        {
            PolylineOptionsCreator.a(this, parcel, i);
            return;
        }
    }

    public final PolylineOptions zIndex(float f)
    {
        aax = f;
        return this;
    }

}
