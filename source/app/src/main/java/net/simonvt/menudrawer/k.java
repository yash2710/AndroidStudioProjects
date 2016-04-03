// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package net.simonvt.menudrawer;

import android.os.Parcel;

final class k
    implements android.os.Parcelable.Creator
{

    k()
    {
    }

    public final volatile Object createFromParcel(Parcel parcel)
    {
        return createFromParcel(parcel);
    }

    public final MenuDrawer.SavedState createFromParcel(Parcel parcel)
    {
        return new MenuDrawer.SavedState(parcel);
    }

    public final volatile Object[] newArray(int i)
    {
        return newArray(i);
    }

    public final MenuDrawer.SavedState[] newArray(int i)
    {
        return new MenuDrawer.SavedState[i];
    }
}
