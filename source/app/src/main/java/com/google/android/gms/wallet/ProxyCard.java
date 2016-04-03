// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            o

public final class ProxyCard
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new o();
    String ajV;
    String ajW;
    int ajX;
    int ajY;
    private final int xM;

    ProxyCard(int i, String s, String s1, int j, int k)
    {
        xM = i;
        ajV = s;
        ajW = s1;
        ajX = j;
        ajY = k;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String getCvn()
    {
        return ajW;
    }

    public final int getExpirationMonth()
    {
        return ajX;
    }

    public final int getExpirationYear()
    {
        return ajY;
    }

    public final String getPan()
    {
        return ajV;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        o.a(this, parcel, i);
    }

}
