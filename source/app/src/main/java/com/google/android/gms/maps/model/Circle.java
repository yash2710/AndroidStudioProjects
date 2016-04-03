// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.model.internal.b;

// Referenced classes of package com.google.android.gms.maps.model:
//            RuntimeRemoteException, LatLng

public final class Circle
{

    private final b aar;

    public Circle(b b1)
    {
        aar = (b)hm.f(b1);
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof Circle))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = aar.a(((Circle)obj).aar);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final LatLng getCenter()
    {
        LatLng latlng;
        try
        {
            latlng = aar.getCenter();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return latlng;
    }

    public final int getFillColor()
    {
        int i;
        try
        {
            i = aar.getFillColor();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final String getId()
    {
        String s;
        try
        {
            s = aar.getId();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return s;
    }

    public final double getRadius()
    {
        double d;
        try
        {
            d = aar.getRadius();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return d;
    }

    public final int getStrokeColor()
    {
        int i;
        try
        {
            i = aar.getStrokeColor();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final float getStrokeWidth()
    {
        float f;
        try
        {
            f = aar.getStrokeWidth();
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
            f = aar.getZIndex();
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
            i = aar.hashCodeRemote();
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
            flag = aar.isVisible();
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
            aar.remove();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setCenter(LatLng latlng)
    {
        try
        {
            aar.setCenter(latlng);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setFillColor(int i)
    {
        try
        {
            aar.setFillColor(i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setRadius(double d)
    {
        try
        {
            aar.setRadius(d);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setStrokeColor(int i)
    {
        try
        {
            aar.setStrokeColor(i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setStrokeWidth(float f)
    {
        try
        {
            aar.setStrokeWidth(f);
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
            aar.setVisible(flag);
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
            aar.setZIndex(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }
}
