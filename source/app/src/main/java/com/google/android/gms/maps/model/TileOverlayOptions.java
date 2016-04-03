// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.v;
import com.google.android.gms.maps.model.internal.i;

// Referenced classes of package com.google.android.gms.maps.model:
//            TileOverlayOptionsCreator, j, TileProvider

public final class TileOverlayOptions
    implements SafeParcelable
{

    public static final TileOverlayOptionsCreator CREATOR = new TileOverlayOptionsCreator();
    private float aax;
    private boolean aay;
    private i abd;
    private TileProvider abe;
    private boolean abf;
    private final int xM;

    public TileOverlayOptions()
    {
        aay = true;
        abf = true;
        xM = 1;
    }

    TileOverlayOptions(int k, IBinder ibinder, boolean flag, float f, boolean flag1)
    {
        aay = true;
        abf = true;
        xM = k;
        abd = com.google.android.gms.maps.model.internal.i.a.bg(ibinder);
        Object obj;
        if (abd == null)
        {
            obj = null;
        } else
        {
            obj = new _cls1();
        }
        abe = ((TileProvider) (obj));
        aay = flag;
        aax = f;
        abf = flag1;
    }

    static i a(TileOverlayOptions tileoverlayoptions)
    {
        return tileoverlayoptions.abd;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final TileOverlayOptions fadeIn(boolean flag)
    {
        abf = flag;
        return this;
    }

    public final boolean getFadeIn()
    {
        return abf;
    }

    public final TileProvider getTileProvider()
    {
        return abe;
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final float getZIndex()
    {
        return aax;
    }

    public final boolean isVisible()
    {
        return aay;
    }

    final IBinder jQ()
    {
        return abd.asBinder();
    }

    public final TileOverlayOptions tileProvider(TileProvider tileprovider)
    {
        abe = tileprovider;
        Object obj;
        if (abe == null)
        {
            obj = null;
        } else
        {
            obj = new _cls2(tileprovider);
        }
        abd = ((i) (obj));
        return this;
    }

    public final TileOverlayOptions visible(boolean flag)
    {
        aay = flag;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int k)
    {
        if (v.jL())
        {
            j.a(this, parcel, k);
            return;
        } else
        {
            TileOverlayOptionsCreator.a(this, parcel, k);
            return;
        }
    }

    public final TileOverlayOptions zIndex(float f)
    {
        aax = f;
        return this;
    }


    private class _cls1
        implements TileProvider
    {

        private final i abg;
        final TileOverlayOptions abh;

        public Tile getTile(int k, int l, int i1)
        {
            Tile tile;
            try
            {
                tile = abg.getTile(k, l, i1);
            }
            catch (RemoteException remoteexception)
            {
                return null;
            }
            return tile;
        }

        _cls1()
        {
            abh = TileOverlayOptions.this;
            super();
            abg = TileOverlayOptions.a(abh);
        }
    }


    private class _cls2 extends com.google.android.gms.maps.model.internal.i.a
    {

        final TileOverlayOptions abh;
        final TileProvider abi;

        public Tile getTile(int k, int l, int i1)
        {
            return abi.getTile(k, l, i1);
        }

        _cls2(TileProvider tileprovider)
        {
            abh = TileOverlayOptions.this;
            abi = tileprovider;
            super();
        }
    }

}
