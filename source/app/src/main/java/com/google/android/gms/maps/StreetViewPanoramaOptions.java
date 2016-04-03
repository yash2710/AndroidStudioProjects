// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.a;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

// Referenced classes of package com.google.android.gms.maps:
//            StreetViewPanoramaOptionsCreator

public final class StreetViewPanoramaOptions
    implements SafeParcelable
{

    public static final StreetViewPanoramaOptionsCreator CREATOR = new StreetViewPanoramaOptionsCreator();
    private StreetViewPanoramaCamera ZV;
    private String ZW;
    private LatLng ZX;
    private Integer ZY;
    private Boolean ZZ;
    private Boolean Zt;
    private Boolean Zz;
    private Boolean aaa;
    private Boolean aab;
    private final int xM;

    public StreetViewPanoramaOptions()
    {
        ZZ = Boolean.valueOf(true);
        Zz = Boolean.valueOf(true);
        aaa = Boolean.valueOf(true);
        aab = Boolean.valueOf(true);
        xM = 1;
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetviewpanoramacamera, String s, LatLng latlng, Integer integer, byte byte0, byte byte1, 
            byte byte2, byte byte3, byte byte4)
    {
        ZZ = Boolean.valueOf(true);
        Zz = Boolean.valueOf(true);
        aaa = Boolean.valueOf(true);
        aab = Boolean.valueOf(true);
        xM = i;
        ZV = streetviewpanoramacamera;
        ZX = latlng;
        ZY = integer;
        ZW = s;
        ZZ = a.a(byte0);
        Zz = a.a(byte1);
        aaa = a.a(byte2);
        aab = a.a(byte3);
        Zt = a.a(byte4);
    }

    public final int describeContents()
    {
        return 0;
    }

    public final Boolean getPanningGesturesEnabled()
    {
        return aaa;
    }

    public final String getPanoramaId()
    {
        return ZW;
    }

    public final LatLng getPosition()
    {
        return ZX;
    }

    public final Integer getRadius()
    {
        return ZY;
    }

    public final Boolean getStreetNamesEnabled()
    {
        return aab;
    }

    public final StreetViewPanoramaCamera getStreetViewPanoramaCamera()
    {
        return ZV;
    }

    public final Boolean getUseViewLifecycleInFragment()
    {
        return Zt;
    }

    public final Boolean getUserNavigationEnabled()
    {
        return ZZ;
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final Boolean getZoomGesturesEnabled()
    {
        return Zz;
    }

    final byte jD()
    {
        return a.c(ZZ);
    }

    final byte jE()
    {
        return a.c(aaa);
    }

    final byte jF()
    {
        return a.c(aab);
    }

    final byte jr()
    {
        return a.c(Zt);
    }

    final byte jv()
    {
        return a.c(Zz);
    }

    public final StreetViewPanoramaOptions panningGesturesEnabled(boolean flag)
    {
        aaa = Boolean.valueOf(flag);
        return this;
    }

    public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetviewpanoramacamera)
    {
        ZV = streetviewpanoramacamera;
        return this;
    }

    public final StreetViewPanoramaOptions panoramaId(String s)
    {
        ZW = s;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latlng)
    {
        ZX = latlng;
        return this;
    }

    public final StreetViewPanoramaOptions position(LatLng latlng, Integer integer)
    {
        ZX = latlng;
        ZY = integer;
        return this;
    }

    public final StreetViewPanoramaOptions streetNamesEnabled(boolean flag)
    {
        aab = Boolean.valueOf(flag);
        return this;
    }

    public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean flag)
    {
        Zt = Boolean.valueOf(flag);
        return this;
    }

    public final StreetViewPanoramaOptions userNavigationEnabled(boolean flag)
    {
        ZZ = Boolean.valueOf(flag);
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        com.google.android.gms.maps.StreetViewPanoramaOptionsCreator.a(this, parcel, i);
    }

    public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean flag)
    {
        Zz = Boolean.valueOf(flag);
        return this;
    }

}
