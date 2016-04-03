// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.hm;
import com.google.android.gms.maps.model.internal.h;

// Referenced classes of package com.google.android.gms.maps.model:
//            RuntimeRemoteException

public final class TileOverlay
{

    private final h abc;

    public TileOverlay(h h1)
    {
        abc = (h)hm.f(h1);
    }

    public final void clearTileCache()
    {
        try
        {
            abc.clearTileCache();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final boolean equals(Object obj)
    {
        if (!(obj instanceof TileOverlay))
        {
            return false;
        }
        boolean flag;
        try
        {
            flag = abc.a(((TileOverlay)obj).abc);
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final boolean getFadeIn()
    {
        boolean flag;
        try
        {
            flag = abc.getFadeIn();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return flag;
    }

    public final String getId()
    {
        String s;
        try
        {
            s = abc.getId();
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
        return s;
    }

    public final float getZIndex()
    {
        float f;
        try
        {
            f = abc.getZIndex();
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
            i = abc.hashCodeRemote();
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
            flag = abc.isVisible();
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
            abc.remove();
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }

    public final void setFadeIn(boolean flag)
    {
        try
        {
            abc.setFadeIn(flag);
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
            abc.setVisible(flag);
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
            abc.setZIndex(f);
            return;
        }
        catch (RemoteException remoteexception)
        {
            throw new RuntimeRemoteException(remoteexception);
        }
    }
}
