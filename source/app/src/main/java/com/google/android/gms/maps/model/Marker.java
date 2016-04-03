// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.model.internal.f;

// Referenced classes of package com.google.android.gms.maps.model:
//            RuntimeRemoteException, BitmapDescriptor, LatLng

public final class Marker
{

    private final f aaO;

    public Marker(f f1)
    {
        aaO = (f)hm.f(f1);
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof Marker))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = aaO.h(((Marker)obj).aaO);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final float getAlpha()
    {
        float f1;
        try
        {
            f1 = aaO.getAlpha();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f1;
    }

    public final String getId()
    {
        String s;
        try
        {
            s = aaO.getId();
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
            latlng = aaO.getPosition();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return latlng;
    }

    public final float getRotation()
    {
        float f1;
        try
        {
            f1 = aaO.getRotation();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return f1;
    }

    public final String getSnippet()
    {
        String s;
        try
        {
            s = aaO.getSnippet();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return s;
    }

    public final String getTitle()
    {
        String s;
        try
        {
            s = aaO.getTitle();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return s;
    }

    public final int hashCode()
    {
        int i;
        try
        {
            i = aaO.hashCodeRemote();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final void hideInfoWindow()
    {
        try
        {
            aaO.hideInfoWindow();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final boolean isDraggable()
    {
        boolean flag;
        try
        {
            flag = aaO.isDraggable();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean isFlat()
    {
        boolean flag;
        try
        {
            flag = aaO.isFlat();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean isInfoWindowShown()
    {
        boolean flag;
        try
        {
            flag = aaO.isInfoWindowShown();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean isVisible()
    {
        boolean flag;
        try
        {
            flag = aaO.isVisible();
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
            aaO.remove();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setAlpha(float f1)
    {
        try
        {
            aaO.setAlpha(f1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setAnchor(float f1, float f2)
    {
        try
        {
            aaO.setAnchor(f1, f2);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setDraggable(boolean flag)
    {
        try
        {
            aaO.setDraggable(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setFlat(boolean flag)
    {
        try
        {
            aaO.setFlat(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setIcon(BitmapDescriptor bitmapdescriptor)
    {
        try
        {
            aaO.m(bitmapdescriptor.jn());
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setInfoWindowAnchor(float f1, float f2)
    {
        try
        {
            aaO.setInfoWindowAnchor(f1, f2);
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
            aaO.setPosition(latlng);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setRotation(float f1)
    {
        try
        {
            aaO.setRotation(f1);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setSnippet(String s)
    {
        try
        {
            aaO.setSnippet(s);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setTitle(String s)
    {
        try
        {
            aaO.setTitle(s);
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
            aaO.setVisible(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void showInfoWindow()
    {
        try
        {
            aaO.showInfoWindow();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }
}
