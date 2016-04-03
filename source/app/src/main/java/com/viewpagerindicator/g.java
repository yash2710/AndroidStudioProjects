// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.os.Parcel;

final class g
    implements android.os.Parcelable.Creator
{

    g()
    {
    }

    public final SmoothTabPageIndicator.SavedState createFromParcel(Parcel parcel)
    {
        return new SmoothTabPageIndicator.SavedState(parcel, (byte)0);
    }

    public final volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public final SmoothTabPageIndicator.SavedState[] newArray(int i)
    {
        return new SmoothTabPageIndicator.SavedState[i];
    }

    public final volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
