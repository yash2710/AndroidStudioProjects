// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.internal.a;

// Referenced classes of package com.google.android.gms.maps.internal:
//            IMapViewDelegate, IStreetViewPanoramaViewDelegate, IMapFragmentDelegate, IStreetViewPanoramaFragmentDelegate, 
//            ICameraUpdateFactoryDelegate

public interface c
    extends IInterface
{

    public abstract IMapViewDelegate a(d d, GoogleMapOptions googlemapoptions);

    public abstract IStreetViewPanoramaViewDelegate a(d d, StreetViewPanoramaOptions streetviewpanoramaoptions);

    public abstract void a(d d, int k);

    public abstract void h(d d);

    public abstract IMapFragmentDelegate i(d d);

    public abstract IStreetViewPanoramaFragmentDelegate j(d d);

    public abstract ICameraUpdateFactoryDelegate jH();

    public abstract a jI();
}
