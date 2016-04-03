// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.os.Parcel;

final class c
    implements android.os.Parcelable.Creator
{

    c()
    {
    }

    public final LinePageIndicator.SavedState createFromParcel(Parcel parcel)
    {
        return new LinePageIndicator.SavedState(parcel, (byte)0);
    }

    public final volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public final LinePageIndicator.SavedState[] newArray(int i)
    {
        return new LinePageIndicator.SavedState[i];
    }

    public final volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
