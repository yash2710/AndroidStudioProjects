// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.model.internal.g;
import java.util.List;

// Referenced classes of package com.google.android.gms.maps.model:
//            RuntimeRemoteException

public final class Polygon
{

    private final g aaW;

    public Polygon(g g1)
    {
        aaW = (g)hm.f(g1);
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof Polygon))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = aaW.a(((Polygon)obj).aaW);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final int getFillColor()
    {
        int i;
        try
        {
            i = aaW.getFillColor();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final List getHoles()
    {
        List list;
        try
        {
            list = aaW.getHoles();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return list;
    }

    public final String getId()
    {
        String s;
        try
        {
            s = aaW.getId();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return s;
    }

    public final List getPoints()
    {
        List list;
        try
        {
            list = aaW.getPoints();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return list;
    }

    public final int getStrokeColor()
    {
        int i;
        try
        {
            i = aaW.getStrokeColor();
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
            f = aaW.getStrokeWidth();
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
            f = aaW.getZIndex();
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
            i = aaW.hashCodeRemote();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return i;
    }

    public final boolean isGeodesic()
    {
        boolean flag;
        try
        {
            flag = aaW.isGeodesic();
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
            flag = aaW.isVisible();
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
            aaW.remove();
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
            aaW.setFillColor(i);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setGeodesic(boolean flag)
    {
        try
        {
            aaW.setGeodesic(flag);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setHoles(List list)
    {
        try
        {
            aaW.setHoles(list);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setPoints(List list)
    {
        try
        {
            aaW.setPoints(list);
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
            aaW.setStrokeColor(i);
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
            aaW.setStrokeWidth(f);
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
            aaW.setVisible(flag);
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
            aaW.setZIndex(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }
}
