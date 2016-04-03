// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.model.internal.c;

// Referenced classes of package com.google.android.gms.maps.model:
//            RuntimeRemoteException, BitmapDescriptor, LatLngBounds, LatLng

public final class GroundOverlay
{

    private final c aaz;

    public GroundOverlay(c c1)
    {
        aaz = (c)hm.f(c1);
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof GroundOverlay))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = aaz.a(((GroundOverlay)obj).aaz);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final float getBearing()
    {
        float f;
        try
        {
            f = aaz.getBearing();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final LatLngBounds getBounds()
    {
        LatLngBounds latlngbounds;
        try
        {
            latlngbounds = aaz.getBounds();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return latlngbounds;
    }

    public final float getHeight()
    {
        float f;
        try
        {
            f = aaz.getHeight();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final String getId()
    {
        String s;
        try
        {
            s = aaz.getId();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return s;
    }

    public final LatLng getPosition()
    {
        LatLng latlng;
        try
        {
            latlng = aaz.getPosition();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return latlng;
    }

    public final float getTransparency()
    {
        float f;
        try
        {
            f = aaz.getTransparency();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final float getWidth()
    {
        float f;
        try
        {
            f = aaz.getWidth();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final float getZIndex()
    {
        float f;
        try
        {
            f = aaz.getZIndex();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f;
    }

    public final int hashCode()
    {
        int i;
        try
        {
            i = aaz.hashCodeRemote();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final boolean isVisible()
    {
        boolean flag;
        try
        {
            flag = aaz.isVisible();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final void remove()
    {
        try
        {
            aaz.remove();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setBearing(float f)
    {
        try
        {
            aaz.setBearing(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setDimensions(float f)
    {
        try
        {
            aaz.setDimensions(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setDimensions(float f, float f1)
    {
        try
        {
            aaz.a(f, f1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setImage(BitmapDescriptor bitmapdescriptor)
    {
        try
        {
            aaz.l(bitmapdescriptor.jn());
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setPosition(LatLng latlng)
    {
        try
        {
            aaz.setPosition(latlng);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setPositionFromBounds(LatLngBounds latlngbounds)
    {
        try
        {
            aaz.setPositionFromBounds(latlngbounds);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setTransparency(float f)
    {
        try
        {
            aaz.setTransparency(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setVisible(boolean flag)
    {
        try
        {
            aaz.setVisible(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setZIndex(float f)
    {
        try
        {
            aaz.setZIndex(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }
}
