// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

// Referenced classes of package net.simonvt.menudrawer:
//            k

class a extends android.view.avedState
{

    public static final android.os..SavedState.a CREATOR = new k();
    Bundle a;

    public void writeToParcel(Parcel parcel, int i)
    {
        super.iteToParcel(parcel, i);
        parcel.writeBundle(a);
    }


    public (Parcel parcel)
    {
        super(parcel);
        a = parcel.readBundle();
    }

    public a(Parcelable parcelable)
    {
        super(parcelable);
    }
}
