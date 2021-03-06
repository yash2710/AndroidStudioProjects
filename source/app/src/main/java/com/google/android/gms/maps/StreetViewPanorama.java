// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.e;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class StreetViewPanorama
{

    private final IStreetViewPanoramaDelegate ZN;

    protected StreetViewPanorama(IStreetViewPanoramaDelegate istreetviewpanoramadelegate)
    {
        ZN = (IStreetViewPanoramaDelegate)hm.f(istreetviewpanoramadelegate);
    }

    public void animateTo(StreetViewPanoramaCamera streetviewpanoramacamera, long l)
    {
        try
        {
            ZN.animateTo(streetviewpanoramacamera, l);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public StreetViewPanoramaLocation getLocation()
    {
        StreetViewPanoramaLocation streetviewpanoramalocation;
        try
        {
            streetviewpanoramalocation = ZN.getStreetViewPanoramaLocation();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return streetviewpanoramalocation;
    }

    public StreetViewPanoramaCamera getPanoramaCamera()
    {
        StreetViewPanoramaCamera streetviewpanoramacamera;
        try
        {
            streetviewpanoramacamera = ZN.getPanoramaCamera();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return streetviewpanoramacamera;
    }

    public boolean isPanningGesturesEnabled()
    {
        boolean flag;
        try
        {
            flag = ZN.isPanningGesturesEnabled();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public boolean isStreetNamesEnabled()
    {
        boolean flag;
        try
        {
            flag = ZN.isStreetNamesEnabled();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public boolean isUserNavigationEnabled()
    {
        boolean flag;
        try
        {
            flag = ZN.isUserNavigationEnabled();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public boolean isZoomGesturesEnabled()
    {
        boolean flag;
        try
        {
            flag = ZN.isZoomGesturesEnabled();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    IStreetViewPanoramaDelegate jB()
    {
        return ZN;
    }

    public Point orientationToPoint(StreetViewPanoramaOrientation streetviewpanoramaorientation)
    {
        Point point;
        try
        {
            point = (Point)e.e(ZN.orientationToPoint(streetviewpanoramaorientation));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return point;
    }

    public StreetViewPanoramaOrientation pointToOrientation(Point point)
    {
        StreetViewPanoramaOrientation streetviewpanoramaorientation;
        try
        {
            streetviewpanoramaorientation = ZN.pointToOrientation(e.h(point));
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return streetviewpanoramaorientation;
    }

    public final void setOnStreetViewPanoramaCameraChangeListener(OnStreetViewPanoramaCameraChangeListener onstreetviewpanoramacamerachangelistener)
    {
        if (onstreetviewpanoramacamerachangelistener == null)
        {
            try
            {
                ZN.setOnStreetViewPanoramaCameraChangeListener(null);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }
        ZN.setOnStreetViewPanoramaCameraChangeListener(new _cls2(onstreetviewpanoramacamerachangelistener));
        return;
    }

    public final void setOnStreetViewPanoramaChangeListener(OnStreetViewPanoramaChangeListener onstreetviewpanoramachangelistener)
    {
        if (onstreetviewpanoramachangelistener == null)
        {
            try
            {
                ZN.setOnStreetViewPanoramaChangeListener(null);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }
        ZN.setOnStreetViewPanoramaChangeListener(new _cls1(onstreetviewpanoramachangelistener));
        return;
    }

    public final void setOnStreetViewPanoramaClickListener(OnStreetViewPanoramaClickListener onstreetviewpanoramaclicklistener)
    {
        if (onstreetviewpanoramaclicklistener == null)
        {
            try
            {
                ZN.setOnStreetViewPanoramaClickListener(null);
                return;
            }
            catch (RemoteException remoteexception)
            {
                throw new RuntimeRemoteException(remoteexception);
            }
        }
        ZN.setOnStreetViewPanoramaClickListener(new _cls3(onstreetviewpanoramaclicklistener));
        return;
    }

    public void setPanningGesturesEnabled(boolean flag)
    {
        try
        {
            ZN.enablePanning(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void setPosition(LatLng latlng)
    {
        try
        {
            ZN.setPosition(latlng);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void setPosition(LatLng latlng, int i)
    {
        try
        {
            ZN.setPositionWithRadius(latlng, i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void setPosition(String s)
    {
        try
        {
            ZN.setPositionWithID(s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void setStreetNamesEnabled(boolean flag)
    {
        try
        {
            ZN.enableStreetNames(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void setUserNavigationEnabled(boolean flag)
    {
        try
        {
            ZN.enableUserNavigation(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public void setZoomGesturesEnabled(boolean flag)
    {
        try
        {
            ZN.enableZoom(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    private class _cls2 extends com.google.android.gms.maps.internal.p.a
    {

        final StreetViewPanorama ZP;
        final OnStreetViewPanoramaCameraChangeListener ZQ;

        public void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetviewpanoramacamera)
        {
            ZQ.onStreetViewPanoramaCameraChange(streetviewpanoramacamera);
        }

        _cls2(OnStreetViewPanoramaCameraChangeListener onstreetviewpanoramacamerachangelistener)
        {
            ZP = StreetViewPanorama.this;
            ZQ = onstreetviewpanoramacamerachangelistener;
            super();
        }

        private class OnStreetViewPanoramaCameraChangeListener
        {

            public abstract void onStreetViewPanoramaCameraChange(StreetViewPanoramaCamera streetviewpanoramacamera);
        }

    }


    private class _cls1 extends com.google.android.gms.maps.internal.q.a
    {

        final OnStreetViewPanoramaChangeListener ZO;
        final StreetViewPanorama ZP;

        public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetviewpanoramalocation)
        {
            ZO.onStreetViewPanoramaChange(streetviewpanoramalocation);
        }

        _cls1(OnStreetViewPanoramaChangeListener onstreetviewpanoramachangelistener)
        {
            ZP = StreetViewPanorama.this;
            ZO = onstreetviewpanoramachangelistener;
            super();
        }

        private class OnStreetViewPanoramaChangeListener
        {

            public abstract void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetviewpanoramalocation);
        }

    }


    private class _cls3 extends com.google.android.gms.maps.internal.r.a
    {

        final StreetViewPanorama ZP;
        final OnStreetViewPanoramaClickListener ZR;

        public void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetviewpanoramaorientation)
        {
            ZR.onStreetViewPanoramaClick(streetviewpanoramaorientation);
        }

        _cls3(OnStreetViewPanoramaClickListener onstreetviewpanoramaclicklistener)
        {
            ZP = StreetViewPanorama.this;
            ZR = onstreetviewpanoramaclicklistener;
            super();
        }

        private class OnStreetViewPanoramaClickListener
        {

            public abstract void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetviewpanoramaorientation);
        }

    }

}
