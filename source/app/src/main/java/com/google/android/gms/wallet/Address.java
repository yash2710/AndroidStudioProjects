// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.gms.wallet;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

// Referenced classes of package com.google.android.gms.wallet:
//            a

public final class Address
    implements SafeParcelable
{

    public static final android.os.Parcelable.Creator CREATOR = new a();
    String UH;
    String UI;
    String UJ;
    String UO;
    String UQ;
    boolean UR;
    String US;
    String aiI;
    String aiJ;
    String name;
    String rf;
    private final int xM;

    Address()
    {
        xM = 1;
    }

    Address(int i, String s, String s1, String s2, String s3, String s4, String s5, 
            String s6, String s7, String s8, boolean flag, String s9)
    {
        xM = i;
        name = s;
        UH = s1;
        UI = s2;
        UJ = s3;
        rf = s4;
        aiI = s5;
        aiJ = s6;
        UO = s7;
        UQ = s8;
        UR = flag;
        US = s9;
    }

    public final int describeContents()
    {
        return 0;
    }

    public final String getAddress1()
    {
        return UH;
    }

    public final String getAddress2()
    {
        return UI;
    }

    public final String getAddress3()
    {
        return UJ;
    }

    public final String getCity()
    {
        return aiI;
    }

    public final String getCompanyName()
    {
        return US;
    }

    public final String getCountryCode()
    {
        return rf;
    }

    public final String getName()
    {
        return name;
    }

    public final String getPhoneNumber()
    {
        return UQ;
    }

    public final String getPostalCode()
    {
        return UO;
    }

    public final String getState()
    {
        return aiJ;
    }

    public final int getVersionCode()
    {
        return xM;
    }

    public final boolean isPostBox()
    {
        return UR;
    }

    public final void writeToParcel(Parcel parcel, int i)
    {
        a.a(this, parcel, i);
    }

}
