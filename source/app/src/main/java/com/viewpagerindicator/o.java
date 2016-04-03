// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.viewpagerindicator;

import android.os.Parcel;

final class o
    implements android.os.Parcelable.Creator
{

    o()
    {
    }

    public final UnderlinePageIndicator.SavedState createFromParcel(Parcel parcel)
    {
        return new UnderlinePageIndicator.SavedState(parcel, (byte)0);
    }

    public final volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public final UnderlinePageIndicator.SavedState[] newArray(int i)
    {
        return new UnderlinePageIndicator.SavedState[i];
    }

    public final volatile Object[] newArray(int i)
    {
        return newArray(i);
    }
}
