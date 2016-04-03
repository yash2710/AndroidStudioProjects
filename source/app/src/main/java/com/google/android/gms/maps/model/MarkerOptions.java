// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.d;
import com.google.android.gms.maps.internal.v;

// Referenced classes of package com.google.android.gms.maps.model:
//            MarkerOptionsCreator, BitmapDescriptor, f, LatLng

public final class MarkerOptions
    implements SafeParcelable
{

    public static final MarkerOptionsCreator CREATOR = new MarkerOptionsCreator();
    private String HY;
    private LatLng ZX;
    private float aaG;
    private float aaH;
    private String aaP;
    private BitmapDescriptor aaQ;
    private boolean aaR;
    private boolean aaS;
    private float aaT;
    private float aaU;
    private float aaV;
    private boolean aay;
    private float mAlpha;
    private final int xM;

    public MarkerOptions()
    {
        aaG = 0.5F;
        aaH = 1.0F;
        aay = true;
        aaS = false;
        aaT = 0.0F;
        aaU = 0.5F;
        aaV = 0.0F;
        mAlpha = 1.0F;
        xM = 1;
    }

    MarkerOptions(int i, LatLng latlng, String s, String s1, IBinder ibinder, float f1, float f2, 
            boolean flag, boolean flag1, boolean flag2, float f3, float f4, float f5, float f6)
    {
        aaG = 0.5F;
        aaH = 1.0F;
        aay = true;
        aaS = false;
        aaT = 0.0F;
        aaU = 0.5F;
        aaV = 0.0F;
        mAlpha = 1.0F;
        xM = i;
        ZX = latlng;
        HY = s;
        aaP = s1;
        BitmapDescriptor bitmapdescriptor;
        if (ibinder == null)
        {
            bitmapdescriptor = null;
        } else
        {
            bitmapdescriptor = new BitmapDescriptor(com.google.android.gms.dynamic.d.a.ag(ibinder));
        }
        aaQ = bitmapdescriptor;
        aaG = f1;
        aaH = f2;
        aaR = flag;
        aay = flag1;
        aaS = flag2;
        aaT = f3;
        aaU = f4;
        aaV = f5;
        mAlpha = f6;
    }

    public final MarkerOptions alpha(float f1)
    {
        mAlpha = f1;
        return this;
    }

    public final MarkerOptions anchor(float f1, float f2)
    {
        aaG = f1;
        aaH = f2;
        return this;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final MarkerOptions draggable(boolean flag)
    {
        aaR = flag;
        return this;
    }

    public final MarkerOptions flat(boolean flag)
    {
        aaS = flag;
        return this;
    }

    public final float getAlpha()
    {
        return mAlpha;
    }

    public final float getAnchorU()
    {
        return aaG;
    }

    public final float getAnchorV()
    {
        return aaH;
    }

    public final BitmapDescriptor getIcon()
    {
        return aaQ;
    }

    public final float getInfoWindowAnchorU()
    {
        return aaU;
    }

    public final float getInfoWindowAnchorV()
    {
        return aaV;
    }

    public final LatLng getPosition()
    {
        return ZX;
    }

    public final float getRotation()
    {
        return aaT;
    }

    public final String getSnippet()
    {
        return aaP;
    }

    public final String getTitle()
    {
        return HY;
    }

    final int getVersionCode()
    {
        return xM;
    }

    public final MarkerOptions icon(BitmapDescriptor bitmapdescriptor)
    {
        aaQ = bitmapdescriptor;
        return this;
    }

    public final MarkerOptions infoWindowAnchor(float f1, float f2)
    {
        aaU = f1;
        aaV = f2;
        return this;
    }

    public final boolean isDraggable()
    {
        return aaR;
    }

    public final boolean isFlat()
    {
        return aaS;
    }

    public final boolean isVisible()
    {
        return aay;
    }

    final IBinder jO()
    {
        if (aaQ == null)
        {
            return null;
        } else
        {
            return aaQ.jn().asBinder();
        }
    }

    public final MarkerOptions position(LatLng latlng)
    {
        ZX = latlng;
        return this;
    }

    public final MarkerOptions rotation(float f1)
    {
        aaT = f1;
        return this;
    }

    public final MarkerOptions snippet(String s)
    {
        aaP = s;
        return this;
    }

    public final MarkerOptions title(String s)
    {
        HY = s;
        return this;
    }

    public final MarkerOptions visible(boolean flag)
    {
        aay = flag;
        return this;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        if (v.jL())
        {
            f.a(this, parcel, i);
            return;
        } else
        {
            MarkerOptionsCreator.a(this, parcel, i);
            return;
        }
    }

}
